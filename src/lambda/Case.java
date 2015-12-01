package lambda;
import java.util.Set;
public class Case extends Expression {
	public SumType sumType;
	public Variable[] variables;
	public Expression[] expressions;
	public Case() {}
	@Override
	public Expression reduce() {}
	@Override
	public Expression substituteWith(String aName, Expression exp) {}
	@Override
	public boolean isReducible() {}
	@Override
	public Set<String> FI() {}
	@Override
	public Type getType(Environment e) {}
	@Override
	public boolean isFunction() {
		return true;
	}
	@Override
	public boolean isExpressionConstant() {}
}