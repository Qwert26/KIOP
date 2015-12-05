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
			return new Inl(
					new SumType(
						thenExpression.getType(Environment.createEnvironment()),
						elseExpression.getType(Environment.createEnvironment())
					), thenExpression);
		}
		if (((Variable) conditionExpression).varName.equals("false")) {
			return new Inr(
					new SumType(
						thenExpression.getType(Environment.createEnvironment()),
						elseExpression.getType(Environment.createEnvironment())
					), elseExpression);
		}
		throw new RuntimeException("Condition der If-Bedingung hat sich nicht richtig reduziert -- weder true noch false");
	}
	@Override
	public Expression substituteWith(String aName, Expression exp) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isReducible() {
		return true;
	}
	@Override
	public Set<String> FI() {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * 
	 *        E |- v:Bool   E|- t1: T1   E|-t2:T2
	 * T-If ===========================================
	 *        E |- if v then t1 t else t2: T1+T2
	 */
	
	@Override
	public Type getType(Environment e) {
		Type conditionType = conditionExpression.getType(e);
		Type thenType = thenExpression.getType(e);
		Type elseType = elseExpression.getType(e);
		
		if (!new Boolean().equals(conditionType)) throw new RuntimeException("Geh wech");
		
		return new SumType(thenType, elseType);
	}
}