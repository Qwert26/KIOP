package lambda;
import java.util.Set;
import java.util.Objects;
import org.junit.*;
import static org.junit.Assert.*;
public class Case extends Expression {
	public static class CaseTestUnit {
		@Test
		public void testCaseType() {
			Environment e=new Environment();
			e.env.put("x",new SumType(new Number(),new Boolean()));
			Case c=new Case(new SumType(new Number(),new Boolean()),
							new Variable("x"),
							new Variable("y"),
							new Variable("z"),
							new Variable("true"),
							new Variable("z"));
			assertEquals(new Boolean(),c.getType(e));
		}
	}
	public SumType sumType;
	public Expression sumExpression;
	public Variable varLeft,varRight;
	public Expression expLeft,expRight;
	public Case(SumType sumType,Expression sumExpression,Variable varLeft,Variable varRight,Expression expLeft,Expression expRight) {
		super();
		this.sumType = sumType;
		this.sumExpression = sumExpression;
		this.varLeft = varLeft;
		this.varRight = varRight;
		this.expLeft = expLeft;
		this.expRight = expRight;
	}
	@Override
	public Expression reduce() {
		if(sumExpression.isReducible()) {
			sumExpression=sumExpression.reduce();
			return this;
		} else {
			return null;
		}
	}
	@Override
	public Expression substituteWith(String aName,Expression exp) {
		sumExpression=sumExpression.substituteWith(aName, exp);
		expLeft=expLeft.substituteWith(aName, exp);
		expRight=expRight.substituteWith(aName, exp);
		return this;
	}
	@Override
	public boolean isReducible() {
		throw new NoSuchMethodError();
	}
	@Override
	public Set<String> FI() {
		throw new NoSuchMethodError();
	}
	@Override
	public Type getType(Environment e) {
		Environment eLeft=e.clone();
		eLeft.env.put(varLeft.varName,sumType.left);
		Type left=expLeft.getType(eLeft);
		
		Environment eRight=e.clone();
		eRight.env.put(varLeft.varName,sumType.right);
		Type right=expLeft.getType(eRight);
		
		if(Objects.equals(left,right)) {
			return right;
		}
		throw new RuntimeException("rechter Typ und linker Typ sind ungleich!");
	}
	@Override
	public boolean isFunction() {
		throw new NoSuchMethodError();
	}
	@Override
	public boolean isExpressionConstant() {
		throw new NoSuchMethodError();
	}
}