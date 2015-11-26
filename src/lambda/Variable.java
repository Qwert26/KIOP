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
			assertEquals(new Boolean(), v.getType(Environment.INSTANCE));
		}
		@Test
		public void testBooleanTypeFalse() {
			Variable v = new Variable("false");
			assertEquals(new Boolean(), v.getType(Environment.INSTANCE));
		}
		@Test
		public void testNumberType() {
			Variable v = new Variable("0");
			assertEquals(new Number(), v.getType(Environment.INSTANCE));	
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
	public Expression substituteWith(String aName, Expression exp) {
		if (aName.equals(varName)) {
			return exp;
		} else {
			return this;
		}
	}
	public Set<String> FI() {
		Set<String> ret = new HashSet<String>();
		ret.add(varName);
		return ret; 
	}
	@Override
	public boolean isReducible() {
		return false;
	}
	/*
	 * Regel: Wenn this.varName in E, dann E => this: (this.varName in E) 
	 * @see v02SimpleLambda_bugFix.Expression#getType(v02SimpleLambda_bugFix.Environment)
	 */
	public Type getType(Environment e) {
		return Environment.env.get(this.varName);
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