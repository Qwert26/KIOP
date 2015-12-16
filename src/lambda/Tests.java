package lambda;
import junit.framework.TestCase;
public class Tests extends TestCase {

	public Application APP(Expression left, Expression right) {
		return new Application(left, right);
	}

	public Abstraction ABS(String paramName, Type paramType, Expression body) {
		return new Abstraction(paramName, paramType, body);
	}

	public Variable VAR(String varName) {
		return new Variable(varName);
	}

	public Record simpleRecord() {
		Record exp = new Record();
		exp.elements.put("x", new Variable("1"));
		exp.elements.put("y", new Variable("true"));
		return exp;
	}

	public void test_recSubType() {
		Environment e = Environment.createEnvironment();
		
		RecordType recT1 = new RecordType();
		recT1.elements.put("x", new Number());
		
		RecordType recT2 = new RecordType();
		recT2.elements.put("x", new Number());
		
		assertTrue(recT2.isSubtypeOf(recT1));
		
	}
	
	
	
	public void test_proj01() {
		Environment e = Environment.createEnvironment();
		
		Projection p = new Projection(simpleRecord(), "x");
		
		assertEquals(new Number(), p.getType(e));

		p = new Projection(simpleRecord(), "y");
		assertEquals(new Boolean(), p.getType(e));

		p = new Projection(simpleRecord(), "x");

		assertTrue(p.isReducible());	
		Expression ex = p.reduce();
		assertEquals(new Variable("1"), ex);
		
	}
	

	
	public void test_recordReduce02() {

		// { x = 1, y = (λx:Num.(λy:Num.y) x) 1}
		Record rec = new Record();
		rec.elements.put("x", new Variable("1"));
		rec.elements.put("y", 
				new Application(
					new Application(
						new Abstraction("x", new Number(), 
								new Abstraction("y", new Number(), new Variable("y"))),
						new Variable("x")
					),
					new Variable("1")));
		
		assertTrue(rec.isReducible());
		rec = rec.reduce();
		
		// { x = 1, y = (λx:Num.((λy:Num.y) x) 1} -> { x = 1, y = (λy:Num.y) 1} 
		assertTrue(rec.isReducible());
		rec = rec.reduce();
		assertEquals(new Variable("1"), rec.elements.get("y"));
		
	}	
	
	
	public void test_recordReduce01() {

		// { x = 1, y = (λx:Num.x) 1}
		Record rec = new Record();
		rec.elements.put("x", new Variable("1"));
		rec.elements.put("y", new Application(new Abstraction("x", new Number(), new Variable("x")), new Variable("1")));
		
		assertTrue(rec.isReducible());
		rec = rec.reduce();
		
		// { x = 1, y = (λx:Num.x) 1} -> { x = 1, y = 1} 
		assertFalse(rec.isReducible());
		assertEquals(new Variable("1"), rec.elements.get("y"));
		
	}	
	
	public void test_recordType03() {
		Environment e = Environment.createEnvironment();
		
		RecordType type = new RecordType();
		type.elements.put("x", new Boolean());
		type.elements.put("y", new Boolean());
		
		
		Record exp = new Record();
		exp.elements.put("x", new Variable("1"));
		exp.elements.put("y", new Variable("true"));
		
		RecordType resultType = exp.getType(e);
		assertTrue(!type.equals(resultType));
		
	}	
	
	public void test_recordType02() {
		Environment e = Environment.createEnvironment();
		Record exp = new Record();
		
		RecordType type = new RecordType();
		type.elements.put("x", new Number());
		type.elements.put("y", new Boolean());
		
		
		exp.elements.put("x", new Variable("1"));
		exp.elements.put("y", new Variable("true"));
		
		RecordType resultType = exp.getType(e);
		assertEquals(type, resultType);
		
	}

	public void test_recordType01() {
		Environment e = Environment.createEnvironment();
		Record exp = new Record();
		exp.elements.put("x", new Variable("1"));
		exp.elements.put("y", new Variable("true"));
		
		RecordType type = exp.getType(e);
		assertEquals(new Number(), type.elements.get("x"));
		assertEquals(new Boolean(), type.elements.get("y"));
		
	}

	public void test_typeOfSumTypeApp() {
		Environment e = Environment.createEnvironment();

		Case c = new Case(new Variable("x"), new SumType(new Number(),
				new Boolean()), new Variable("y"), new Variable("true"),
				new Variable("z"), new Variable("z"));

		Abstraction abs = ABS("x", new SumType(new Number(), new Boolean()), c);

		Inl value = new Inl(new SumType(new Number(), new Boolean()),
				new Variable("1"));

		Inr value2 = new Inr(new SumType(new Number(), new Boolean()),
				new Variable("true"));

		Application app = APP(abs, value);
		Type t = app.getType(e);
		assertEquals(new Boolean(), t);

		Application app2 = APP(abs, value2);
		Type t2 = app2.getType(e);
		assertEquals(new Boolean(), t);
	}

	public void test_typeOfCase() {
		Environment e = Environment.createEnvironment();
		e.env.put("x", new SumType(new Number(), new Boolean()));

		Case c = new Case(new Variable("x"), new SumType(new Number(),
				new Boolean()), new Variable("y"), new Variable("true"),
				new Variable("z"), new Variable("z"));

		Type t = c.getType(e);
		assertEquals(new Boolean(), t);

	}

	public void test_typeOfIfSumType() {
		Environment e = Environment.createEnvironment();
		If ifExpr = new If(new Variable("true"), new Variable("1"),
				new Variable("false"));

		Type type = ifExpr.getType(e);

		// ErgebnisTyp: SumType: Num+Bool

		SumType sT = new SumType(new Number(), new Boolean());

		assertEquals(sT, type);
	}

	public void test_typeOfAbstraction() {
		Environment e = Environment.createEnvironment();
		Abstraction abst = ABS("x", new Number(), VAR("x"));

		// ErgebnisTyp: Num -> Num

		FunctionType fT = new FunctionType(new Number(), new Number());

		assertEquals(fT, abst.getType(e));
	}

	public void test_IfEval() {
		If ifExpr = new If(new Variable("true"), new Variable("true"),
				new Variable("false"));
		// if true then true else false -> true

		Inl inl = (Inl) ifExpr.reduce();
		Variable v = (Variable) inl.body;

		assertEquals("true", v.varName);

		If ifExpr2 = new If(new Variable("false"), new Variable("true"),
				new Variable("false"));

		// if true then true else false -> true
		Inr inr = (Inr) ifExpr2.reduce();
		Variable v2 = (Variable) inr.body;
		assertEquals("false", v2.varName);

	}

	public void test_IfTyped() {
		Environment e = Environment.createEnvironment();

		// if (true) then true else true
		If ifExpr = new If(new Variable("true"), new Variable("true"),
				new Variable("true"));
		assertEquals(new SumType(new Boolean(), new Boolean()),
				ifExpr.getType(e));

		ifExpr = new If(new Variable("true"), new Variable("1"), new Variable(
				"2"));
		assertEquals(new SumType(new Number(), new Number()), ifExpr.getType(e));

		final If ifExpr3 = new If(new Variable("true"), new Variable("1"),
				new Variable("true"));
		assertEquals(new SumType(new Number(), new Boolean()),
				ifExpr3.getType(e));

		// ConditionType is Boolean
		final If ifExpr4 = new If(new Variable("1"), new Variable("1"),
				new Variable("true"));

		try {
			ifExpr4.getType(e);
			assertTrue(false);
		} catch (Exception ex) {
			assertTrue(true);
		}

		// if (not true) then 1 else 2
		final If ifExpr5 = new If(new Application(new Variable("not"),
				new Variable("true")), new Variable("1"), new Variable("2"));

		assertEquals(new SumType(new Number(), new Number()),
				ifExpr5.getType(e));

		final If ifExpr6 = new If(new Application(new Variable("largerThan0"),
				new Variable("1")), new Variable("1"), new Variable("2"));

		assertEquals(new SumType(new Number(), new Number()),
				ifExpr6.getType(e));

	}

	public void test_TypedExpression() {
		Environment e = Environment.createEnvironment();
		Variable v = new Variable("true");
		assertEquals(new Boolean(), v.getType(e));

		v = new Variable("false");
		assertEquals(new Boolean(), v.getType(e));

		v = new Variable("0");
		assertEquals(new Number(), v.getType(e));

	}

}
