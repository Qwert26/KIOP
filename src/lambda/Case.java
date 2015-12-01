package lambda;
import java.util.Objects;
import java.util.Set;
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
		throw new NoSuchMethodError();
	}
	@Override
	public Expression substituteWith(String aName,Expression exp) {
		throw new NoSuchMethodError();
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
		eLeft.env.put(varLeft.varName,sumType.typeLeft);
		Type left=expLeft.getType(eLeft);
		
		Environment eRight=e.clone();
		eRight.env.put(varLeft.varName,sumType.typeLeft);
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