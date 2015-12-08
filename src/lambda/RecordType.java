package lambda;
import java.util.*;
import java.util.Map.Entry;
public class RecordType extends Type {
	public Map<String,Type> elements;
	public RecordType() {
		elements=new HashMap<String,Type>();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elements == null) ? 0 : elements.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof RecordType)) {
			return false;
		}
		RecordType other = (RecordType) obj;
		if (elements == null) {
			if (other.elements != null) {
				return false;
			}
		} else if (!elements.equals(other.elements)) {
			return false;
		}
		return true;
	}
	@Override
	public boolean isSubtypeOf(Type other) {
		if (other instanceof RecordType) {
			RecordType rec=(RecordType)other;
			for (Entry<String,Type>entry:rec.elements.entrySet()) {
				if(elements.containsKey(entry.getKey())) {
					if(!elements.get(entry.getKey()).isSubtypeOf(entry.getValue())) {
						return false;
					}
				} else {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
}