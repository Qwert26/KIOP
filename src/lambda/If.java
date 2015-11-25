package lambda;
import java.util.Set;
public class If extends Expression {
	public Expression conditionExpression;
	public Expression thenExpression;
	public Expression elseExpression;
	public If(Expression conditionExpression, Expression thenExpression,
			Expression elseExpression) {
		super();
		this.conditionExpression = conditionExpression;
		this.thenExpression = thenExpression;
		this.elseExpression = elseExpression;
	}
	@Override
	public Expression reduce() {
		if (conditionExpression.isReducible()) {
			conditionExpression = conditionExpression.reduce();
			return this;
		}
		// E-ifTrue
		if (((Variable) conditionExpression).varName.equals("true")) {
			return thenExpression;
		}
		if (((Variable) conditionExpression).varName.equals("false")) {
			return elseExpression;
		}
		throw new RuntimeException("Condition der If-Bedingung hat sich nicht richtig reduziert -- weder true noch false");
	}
	@Override
	public Expression substituteWith(String aName, Expression exp) {
		return null;
	}
	@Override
	public boolean isReducible() {
		return true;
	}
	@Override
	public Set<String> FI() {
		return null;
	}
	@Override
	public Type getType(Environment e) {
		Type conditionType = conditionExpression.getType(e);
		Type thenType = thenExpression.getType(e);
		Type elseType = elseExpression.getType(e);
		if (!new Boolean().equals(conditionType)) throw new RuntimeException("Geh wech");
		if (!thenType.equals(elseType)) throw new RuntimeException("Geh wech");
		return thenType;
	}
}