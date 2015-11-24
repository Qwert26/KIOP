package lambda;
import java.util.Set;
public abstract class Expression {
	public abstract Expression reduce();
	public abstract Expression substituteWith(String aName, Expression exp);
	public abstract Set<String>FI();
	public abstract boolean isReducible();
}