package lambda;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;
public class If extends Expression {
	public static final class IfTestUnit {
		@Test
		public void testEvaluationTrue() {
			If ifExpr = new If(new Variable("true"), new Variable("true"), new Variable("false"));
			// if true then true else false -> true
			assertTrue(((Variable) ifExpr.reduce()).varName.equals("true"));
		}
		@Test
		public void testEvaluationFalse() {
			If ifExpr2 = new If(new Variable("false"), new Variable("true"), new Variable("false"));
			// if true then true else false -> true
			assertTrue(((Variable) ifExpr2.reduce()).varName.equals("false"));
		}
		@Test
		public void testEvaluationFunction() {
			If ifExpr3 = 
					new If(
						new Application(new Variable("not"), new Variable("true")), 
						new Variable("true"), 
						new Variable("false"));
			// if true then true else false -> true
			assertTrue(((Variable) ifExpr3.reduce()).varName.equals("false"));
		}
		@Test
		public void testTypeBoolean() {
			// if (true) then true else true
			If ifExpr = new If(new Variable("true"), new Variable("true"), new Variable("true"));
			assertEquals(new Boolean(), ifExpr.getType(Environment.INSTANCE));
		}
		@Test
		public void testTypeNumber() {
			If ifExpr = new If(new Variable("true"), new Variable("1"), new Variable("2"));
			assertEquals(new Number(), ifExpr.getType(Environment.INSTANCE));
		}
		@Test(expected=RuntimeException.class)
		public void testTypeUnequal() {
			final If ifExpr3 = new If(new Variable("true"), new Variable("1"), new Variable("true"));
			ifExpr3.getType(Environment.INSTANCE);
		}
		@Test(expected=RuntimeException.class)
		public void testWrongConditionType() {
			final If ifExpr4 = new If(new Variable("1"), new Variable("1"), new Variable("true"));
			ifExpr4.getType(Environment.INSTANCE);
		}
		@Test
		@Ignore("No asserts!")
		public void test() {
			final If ifExpr5 = 
					new If(
						new Application(new Variable("not"), new Variable("true")), 
						new Variable("1"), new Variable("2"));
			ifExpr5.getType(Environment.INSTANCE);
			
			final If ifExpr6 = 
					new If(
						new Application(new Variable("largerThan0"), new Variable("1")), 
						new Variable("1"), new Variable("2"));
			ifExpr6.getType(Environment.INSTANCE);
		}
	}
	public Expression conditionExpression;
	public Expression thenExpression;
	public Expression elseExpression;
	public If(Expression conditionExpression, Expression thenExpression,
			Expression elseExpression) {
		super();
		this.conditionExpression = conditionExpression;
		this.thenExpression = thenExpression;
		this.elseExpression = elseExpression;
	}
	@Override
	public Expression reduce() {
		if (conditionExpression.isReducible()) {
			conditionExpression = conditionExpression.reduce();
			return this;
		}
		// E-ifTrue
		if (((Variable) conditionExpression).varName.equals("true")) {
			return thenExpression;
		}
		if (((Variable) conditionExpression).varName.equals("false")) {
			return elseExpression;
		}
		throw new RuntimeException("Condition der If-Bedingung hat sich nicht richtig reduziert -- weder true noch false");
	}
	@Override
	public Expression substituteWith(String aName, Expression exp) {
		return null;
	}
	@Override
	public boolean isReducible() {
		return true;
	}
	@Override
	public Set<String> FI() {
		return null;
	}
	@Override
	public Type getType(Environment e) {
		Type conditionType = conditionExpression.getType(e);
		Type thenType = thenExpression.getType(e);
		Type elseType = elseExpression.getType(e);
		if (!new Boolean().equals(conditionType)) throw new RuntimeException("Geh wech");
		if (!thenType.equals(elseType)) throw new RuntimeException("Geh wech");
		return thenType;
	}
}