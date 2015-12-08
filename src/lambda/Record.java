package lambda;
import java.util.*;
import java.util.Map.Entry;
public class Record extends Expression {
	public static class RecordTestUnit {
		
	}
	public Map<String,Expression> contents;
	public String name;
	public Record(String name) {
		this.name=name;
		contents=new HashMap<String,Expression>();
	}
	@Override
	public Record reduce() {
		for(String label:contents.keySet()) {
			if(contents.get(label).isReducible()) {
				contents.put(label,contents.get(label).reduce());
				return this;
			}
		}
		return this;
	}
	@Override
	public Expression substituteWith(String aName,Expression exp) {
		return null;
	}
	@Override
	public boolean isReducible() {
		for(Expression exp:contents.values()) {
			if(exp.isReducible()) {
				return true;
			}
		}
		return false;
	}
	@Override
	public Set<String> FI() {
		return null;
	}
	@Override
	public Type getType(Environment e) {
		RecordType type=new RecordType();
		for(Entry<String,Expression> entry:contents.entrySet()) {
			type.elements.put(entry.getKey(),entry.getValue().getType(e));
		}
		return type;
	}
	@Override
	public boolean isFunction() {
		return false;
	}
	@Override
	public boolean isExpressionConstant() {
		return false;
	}
}