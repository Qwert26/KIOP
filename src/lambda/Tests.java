package lambda; 
import junit.framework.TestCase;
public class Tests extends TestCase {
	public Application APP(Expression left, Expression right) {
		return new Application(left, right);
	}
	public Abstraction ABS(String paramName, Expression body) {
		return new Abstraction(paramName, body);
	}
	public Variable VAR(String varName) {
		return new Variable(varName);
	}
	public void test_IfEval() {
		If ifExpr = new If(new Variable("true"), new Variable("true"), new Variable("false"));
		// if true then true else false -> true
		assertTrue(((Variable) ifExpr.reduce()).varName.equals("true"));

		If ifExpr2 = new If(new Variable("false"), new Variable("true"), new Variable("false"));
		// if true then true else false -> true
		assertTrue(((Variable) ifExpr2.reduce()).varName.equals("false"));
	
		If ifExpr3 = 
				new If(
					new Application(new Variable("not"), new Variable("true")), 
					new Variable("true"), 
					new Variable("false"));
		// if true then true else false -> true
		assertTrue(((Variable) ifExpr3.reduce()).varName.equals("false"));
	}
	public void test_IfTyped() {
		Environment e = Environment.createEnvironment();

		// if (true) then true else true
		If ifExpr = new If(new Variable("true"), new Variable("true"), new Variable("true"));
		assertEquals(new Boolean(), ifExpr.getType(e));
		
		ifExpr = new If(new Variable("true"), new Variable("1"), new Variable("2"));
		assertEquals(new Number(), ifExpr.getType(e));
		
		final If ifExpr3 = new If(new Variable("true"), new Variable("1"), new Variable("true"));

		try {
			ifExpr3.getType(e);
			assertTrue(false);
		} catch (Exception ex) {
			assertTrue(true);
		}
		
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
		
		ifExpr5.getType(e);
		
		final If ifExpr6 = 
				new If(
					new Application(new Variable("largerThan0"), new Variable("1")), 
					new Variable("1"), new Variable("2"));
		
		ifExpr6.getType(e);
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