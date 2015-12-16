package lambda;
import java.util.Set;
public abstract class Expression {
	public abstract Expression reduce();
	public abstract Expression substituteWith(String aName, Expression exp);
	public abstract boolean isReducible();
	public abstract Set FI();
	public abstract Type getType(Environment e);
}