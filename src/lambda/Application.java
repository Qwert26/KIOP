package lambda;
import java.util.Set;
public class Application extends Expression {
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
			//TODO Hier in Vorlesung aufgehört:
			//|| left.isExpressionConstant() && left.isFunction()
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
}