package lambda;
import java.util.Set;
public class Inr extends Expression {
	public SumType sumType;
	public Expression body;
	public Inr(SumType sumType, Expression body) {
		this.sumType = sumType;
		this.body = body;
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
	public Set FI() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 *     E |- t: T2
	 *     =========================
	 * 		E |- inr(T1+T2) t: T1+T2
	 */
	@Override
	public Type getType(Environment e) {
		Type t = body.getType(e);
		if (!sumType.right.equals(t)) throw new RuntimeException("blabla");
		return sumType;
	}
}