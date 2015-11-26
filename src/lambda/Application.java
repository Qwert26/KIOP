package lambda;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;
public class Application extends Expression {
	public static final class ApplicationTestUnit {
		@Test
		public void testReduce() {
			Expression ex = new Application(new Abstraction("x",new Variable("x")),new Variable("y"));
			Expression ex2 = ex.reduce();
			assertTrue(ex2 instanceof Variable);
			assertEquals("y", ((Variable) ex2).varName);
		}
	}
	Expression left;
	Expression right;
	public Application(Expression left, Expression right) {
		super();
		this.left = left;
		this.right = right;
	}
	@Override
	public Expression reduce() {
		if (left.isReducible()) {
			left = left.reduce();
			return this;
		}
		if (left instanceof Abstraction) {
			return ((Abstraction) left).reduceWith(right);
		}
		return this;
	}
	@Override
	public Expression substituteWith(String aName, Expression exp) {
		left = left.substituteWith(aName, exp);
		right = right.substituteWith(aName, exp);
		return this;
	}
	@Override
	public Set<String> FI() {
		Set<String> s = left.FI();
		s.addAll(right.FI());
		return s;
	}
	@Override
	public boolean isReducible() {
		return
			left instanceof Abstraction ||
			left.isReducible() 
			||left.isExpressionConstant()&&left.isFunction()
			;
	}
	@Override
	public Type getType(Environment e) {
		if (left.getType(e) instanceof FunctionType) {
			Type t2 = right.getType(e);
			FunctionType f = (FunctionType) left.getType(e);
			Type inFunctionType = f.left;
			if (t2.equals(inFunctionType)) {
				return f.right;
			}
		}
		return null;
	}
	@Override
	public boolean isFunction() {
		return false;
	}
	@Override
	public boolean isExpressionConstant() {
		// TODO Auto-generated method stub
		return false;
	}
}