package lambda;
import java.util.HashSet;
import java.util.Set;
public class Variable extends Expression {
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
	@Override
	public boolean isReducible() {
		return false;
	}
	/*
	 * Regel: Wenn this.varName in E, dann E => this: (this.varName in E) 
	 * @see v02SimpleLambda_bugFix.Expression#getType(v02SimpleLambda_bugFix.Environment)
	 */
	public Type getType(Environment e) {
		return e.env.get(this.varName);
	}
}