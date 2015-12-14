package lambda;
import java.util.Set;
public class Fix extends Expression {
	public Expression body;
	public Fix(Expression body) {
		this.body=body;
	}
	@Override
	public Expression reduce() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Expression substituteWith(String aName, Expression exp) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isReducible() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Set<String> FI() {
		return null;
	}
	@Override
	public Type getType(Environment e) {
		Type ret= body.getType(e);
		if(ret instanceof FunctionType) {
			return ret;
		} else {
			throw new RuntimeException("Type of body is not a Function!");
		}
	}
	@Override
	public boolean isFunction() {
		return true;
	}
	@Override
	public boolean isExpressionConstant() {
		return true;
	}
}