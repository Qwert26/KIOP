package lambda;
import java.util.Set;
public class Case extends Expression {
	public Case(Expression sumExpression, SumType sumType,
			Variable inlVariable, Expression inlExpression,
			Variable inrVariable, Expression inrExpression) {
		super();
		this.sumExpression = sumExpression;
		this.sumType = sumType;
		this.inlVariable = inlVariable;
		this.inlExpression = inlExpression;
		this.inrVariable = inrVariable;
		this.inrExpression = inrExpression;
	}
	@Override
	/*
	 *               t1 -> t1'
	 * =================================================================
	 * case t1                           case t1'  
	 *   inl(T) y => t2 |     ->           inl(T) y => t2 |     ->  
	 *   inr(T) z => t3                    inr(T) z => t3
	 * 
	 * 
	 * 
	 * 
	 * case inl(T) t1                      
	 *   inl(T) y => t2 |     ->           [y := t1] t2 
	 *   inr(T) z => t3
	 *   
	 * case inr(T) t1                      
	 *   inl(T) y => t2 |     ->           [z := t1] t3 
	 *   inr(T) z => t3                    
	 * 
	 */
	public Expression reduce() {
		throw new RuntimeException("not yet implemented");
	}
	public Expression reduceWith(Expression appliedParameter) {
		throw new RuntimeException("not yet implemented");
	}	
	public Set<String> FI() {
		throw new RuntimeException("not yet implemented");
	}
	@Override
	public Expression substituteWith(String aName, Expression exp) {
		throw new RuntimeException("not yet implemented");
	}
	@Override
	public boolean isReducible() {
		return false;
	}
	/**
	 *          E, inlVar: T1 |- inlExpression: T   
	 *          E, inrVar: T2 |- inrExpression: T
	 * T-Abs =====================================================================
	 * 		        E |- case sumExpression
	 *                      inl (T1+T2) inlVar => inlExpression
	 *                      inr (T1+T2) inrVariable => inrExpression : T
	 *
	 */
	public Expression sumExpression;
	public SumType sumType;
	public Variable inlVariable;
	public Expression inlExpression;
	public Variable inrVariable;
	public Expression inrExpression;
	@Override
	public Type getType(Environment e) {
		Environment envLeft = e.clone();
		envLeft.env.put(inlVariable.varName, sumType.left);
		Type tLeft= inlExpression.getType(envLeft);
		Environment envRight = e.clone();
		envRight.env.put(inrVariable.varName, sumType.right);
		Type tRight= inrExpression.getType(envRight);
		if(!tLeft.equals(tRight)) new RuntimeException("blabla");
		return tRight;
	}
}