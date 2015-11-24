package lambda;
import java.util.HashSet;
import java.util.Set;
public class Variable extends Expression {
	@Override
	public boolean isReducible() {
		return false;
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
}