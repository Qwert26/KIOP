package lambda;
import java.util.Set;
/*
   Syntax: {l=1}.l 
 */
public class Projection extends Expression {
	public Expression expression;
	public String label;
	public Projection(Expression target, String label) {
		super();
		this.expression = target;
		this.label = label;
	}
	@Override
	public Expression reduce() {
		if (expression.isReducible()) {
			expression = expression.reduce();
			return this;
		}
		Record rec = (Record) expression;
		return rec.elements.get(label);
	}
	@Override
	public Expression substituteWith(String aName, Expression exp) {
		expression = expression.substituteWith(aName, exp);
		return this;
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
	/**
	 *     E |- t:{l1: T1.....ln:Tn}
	 *     =========================
	 * 		E |- t.li : Ti 
	 */
	@Override
	public Type getType(Environment e) {
		Type t = expression.getType(e);
		if (!(t instanceof RecordType))
			throw new RuntimeException("Invalid Type");
		RecordType recType = (RecordType) t;
		if (!recType.elements.containsKey(label))
			throw new RuntimeException("Label in Projection not contained");
		return recType.elements.get(label);
	}
}