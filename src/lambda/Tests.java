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
	public void test_typeOfSumTypeApp() {
		Environment e = Environment.createEnvironment();
		Case c = new Case(
				new Variable("x"), 
				new SumType(new Number(), new Boolean()), 
				new Variable("y"), 
				new Variable("true"), 
				new Variable("z"), 
				new Variable("z"));
		Abstraction abs = ABS("x", new SumType(new Number(), new Boolean()), c);
		Inl value = new Inl(
			new SumType(new Number(), new Boolean()),
			new Variable("1"));
		Inr value2 = new Inr(
				new SumType(new Number(), new Boolean()),
				new Variable("true"));
		Application app = APP(abs, value);
		Type t = app.getType(e);
		assertEquals(new Boolean(), t);
		
		Application app2 = APP(abs, value2);
		Type t2 = app2.getType(e);
		assertEquals(new Boolean(),t2);
	}	
	public void test_typeOfCase() {
		Environment e = Environment.createEnvironment();
		e.env.put("x", new SumType(new Number(), new Boolean()));
		Case c = new Case(
				new Variable("x"), 
				new SumType(new Number(), new Boolean()), 
				new Variable("y"), 
				new Variable("true"), 
				new Variable("z"), 
				new Variable("z"));
		Type t = c.getType(e);
		assertEquals(new Boolean(), t);
	}
	public void test_typeOfIfSumType() {
		Environment e = Environment.createEnvironment();
		If ifExpr = 
				new If(
						new Variable("true"), 
						new Variable("1"), 
						new Variable("false"));
		
		Type type = ifExpr.getType(e);
		// ErgebnisTyp: SumType: Num+Bool
		SumType sT = new SumType(new Number(), new Boolean()); 
		assertEquals(sT,type);
	}
	public void test_typeOfAbstraction() {
		Environment e = Environment.createEnvironment();
		Abstraction abst = ABS("x", new Number(), VAR("x"));
		// ErgebnisTyp: Num -> Num
		FunctionType fT = new FunctionType(new Number(), new Number()); 
		assertEquals(fT,abst.getType(e));
	}
	public void test_IfEval() {
		If ifExpr = new If(new Variable("true"), new Variable("true"), new Variable("false"));
		// if true then true else false -> true
		Inl inl = (Inl) ifExpr.reduce();
		Variable v = (Variable) inl.body;
		assertEquals("true", v.varName);
		If ifExpr2 = new If(
				new Variable("false"), 
				new Variable("true"), 
				new Variable("false"));
		// if true then true else false -> true
		Inr inr = (Inr) ifExpr2.reduce();
		Variable v2 = (Variable) inr.body;
		assertEquals("false", v2.varName);
	}
	public void test_IfTyped() {
		Environment e = Environment.createEnvironment();
		// if (true) then true else true
		If ifExpr = new If(new Variable("true"), new Variable("true"), new Variable("true"));
		assertEquals(new SumType(new Boolean(), new Boolean()), ifExpr.getType(e));
		ifExpr = new If(new Variable("true"), new Variable("1"), new Variable("2"));
		assertEquals(new SumType(new Number(), new Number()), ifExpr.getType(e));
		final If ifExpr3 = new If(new Variable("true"), new Variable("1"), new Variable("true"));
		assertEquals(new SumType(new Number(), new Boolean()), ifExpr3.getType(e));
		// ConditionType is Boolean
		final If ifExpr4 = new If(new Variable("1"), new Variable("1"), new Variable("true"));
		try {
			ifExpr4.getType(e);
			assertTrue(false);
		} catch (Exception ex) {
			assertTrue(true);
		}
		// if (not true) then 1 else 2
		final If ifExpr5 = 
				new If(
					new Application(new Variable("not"), new Variable("true")), 
					new Variable("1"), new Variable("2"));
		
		assertEquals(new SumType(new Number(),new Number()), ifExpr5.getType(e));
		final If ifExpr6 = 
				new If(
					new Application(new Variable("largerThan0"), new Variable("1")), 
					new Variable("1"), new Variable("2"));
		assertEquals(new SumType(new Number(),new Number()), ifExpr6.getType(e));
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