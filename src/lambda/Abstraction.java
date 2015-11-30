package lambda;
import java.util.Set;
public class Abstraction extends Expression {
	public String paramName;
	public Expression body;
	public Abstraction(String paramName, Expression body) {
		super();
		this.paramName = paramName;
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
		// TODO Auto-generated method stub
		return null;
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