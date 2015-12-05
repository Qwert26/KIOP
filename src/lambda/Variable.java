package lambda;
import java.util.HashSet;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;
public class Variable extends Expression {
	public static final class VariableTestUnit {
		@Test
		public void testBooleanTypeTrue() {
			Variable v = new Variable("true");
			assertEquals(new Boolean(), v.getType(new Environment()));
		}
		@Test
		public void testBooleanTypeFalse() {
			Variable v = new Variable("false");
			assertEquals(new Boolean(), v.getType(new Environment()));
		}
		@Test
		public void testNumberType() {
			Variable v = new Variable("0");
			assertEquals(new Number(), v.getType(new Environment()));	
		}
		@Test
		public void testFunctionTypeBooleanBoolean() {
			Variable v=new Variable("not");
			assertEquals(new FunctionType(new Boolean(),new Boolean()),v.getType(new Environment()));
		}
	}
	public final String varName;
	public Variable(String varName) {
		super();
		this.varName = varName;
	}
	@Override
	public Expression reduce() {
		return null;
	}
	/**
	 * [x:=s] x = s
	 * substituteWith("x", exp) "x" = exp
	 * @param aName
	 * @param exp
	 * @return
	 */
	@Override
	public Expression substituteWith(String aName, Expression exp) {
		if (aName.equals(varName)) {
			return exp;
		} else {
			return this;
		}
	}
	@Override
	public Set<String> FI() {
		Set<String> ret = new HashSet<String>();
		ret.add(varName);
		return ret; 
	}
	@Override
	public boolean isReducible() {
		return false;
	}
	@Override
	public Type getType(Environment e) {
		return e.env.get(this.varName);
	}
	@Override
	public boolean isFunction() {
		return false;
	}
	@Override
	public boolean isExpressionConstant() {
		return true;
	}
}