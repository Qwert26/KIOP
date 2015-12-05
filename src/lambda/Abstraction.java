package lambda;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;
public class Abstraction extends Expression {
	public static class AbstractionTestUnit {
		@Test
		public void testAbstractionType() {
			Abstraction abs=new Abstraction("x",new Number(),new Variable("x"));
			assertEquals(new FunctionType(new Number(),new Number()),abs.getType(new Environment()));
		}
	}
	public String paramName;
	public Type paramType;
	public Expression body;
	public Abstraction(String paramName,Type paramType,Expression body) {
		super();
		this.paramName = paramName;
		this.paramType=paramType;
		this.body = body;
	}
	@Override
	public Expression reduce() {
		return null;
	}
	public Expression reduceWith(Expression appliedParameter) {
		return body.substituteWith(paramName, appliedParameter);
	}	
	public Set<String> FI() {
		Set<String> ret = body.FI();
		ret.remove(paramName);
		return ret;
	}
	@Override
	public Expression substituteWith(String aName, Expression exp) {
		if (!paramName.equals(aName) &&  !exp.FI().contains(paramName))
			body = body.substituteWith(aName, exp);
		return this;
	}
	@Override
	public boolean isReducible() {
		return false;
	}
	@Override
	public Type getType(Environment e) {
		Environment c=e.clone();
		c.env.put(paramName,paramType);
		return new FunctionType(paramType,body.getType(c));
	}
	@Override
	public boolean isFunction() {
		return true;
	}
	@Override
	public boolean isExpressionConstant() {
		return false;
	}
}