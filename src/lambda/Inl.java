package lambda;
import java.util.Set;
public class Inl extends Expression {
	public SumType sumType;
	public Expression body;
	public Inl(SumType sumType, Expression body) {
		this.sumType = sumType;
		this.body = body;
	}
	/*                    t1 -> t'
	 *   ----------------------------------
	 *   inl (T) t1 -> inl (T) t' 
	 * 
	 */
	public Expression reduce() {
		if (body.isReducible())
			body = body.reduce();
		return this;
	}
	@Override
	public Expression substituteWith(String aName, Expression exp) {
		body = body.substituteWith(aName, exp);
		return this;
	}
	@Override
	public boolean isReducible() {
		return body.isReducible();
	}
	@Override
	public Set<String> FI() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 *     E |- t: T1
	 *     =========================
	 * 		E |- inl(T1+T2) t: T1+T2
	 */
	@Override
	public Type getType(Environment e) {
		Type t = body.getType(e);
		if (!sumType.left.equals(t)) throw new RuntimeException("blabla");
		return sumType;
	}
}