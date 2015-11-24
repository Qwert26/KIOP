package lambda;
import java.util.Set;
public class Application extends Expression {
	@Override
	public boolean isReducible() {
		return right.isReducible() || left instanceof Abstraction || left.isReducible();
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
		if (right.isReducible()) {
			right = right.reduce();
			return this;
		} else if (left instanceof Abstraction) {
			return ((Abstraction) left).reduceWith(right);
		} else if (left.isReducible()) {
			left = left.reduce();
			return this;
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
}