package lambda;
import java.util.Set;
public class Projection extends Expression{
	public Expression target;
	public String label;
	public Projection() {}
	@Override
	public Expression reduce() {
		if(target.isReducible()) {
			target=target.reduce();
			return this;
		} else {
			Record rec=(Record)target;
			return rec.contents.get(label);
		}
	}
	@Override
	public Projection substituteWith(String aName, Expression exp) {
		target=target.substituteWith(aName, exp);
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
	@Override
	public Type getType(Environment e) {
		Type targetType=target.getType(e);
		if(targetType instanceof RecordType) {
			final RecordType recordType = (RecordType) targetType;
			if(recordType.elements.containsKey(label)) {
				return recordType.elements.get(label);
			} else {
				throw new RuntimeException("Label not found!");
			}
		} else {
			throw new RuntimeException("No RecordType for Projection!");
		}
	}
	@Override
	public boolean isFunction() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isExpressionConstant() {
		// TODO Auto-generated method stub
		return false;
	}
}