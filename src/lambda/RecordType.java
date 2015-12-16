package lambda;
import java.util.HashMap;
import java.util.HashSet;
import javax.security.auth.Subject;
/*
 * 
 * zB { x:Num }, { x: Bool-> Num, y: Bool}
 * 
 * 
 */
public class RecordType extends Type {

	HashMap<String, Type> elements = new HashMap<String, Type>();
	
	
	public boolean equals(Object o) {
		if (!(o instanceof RecordType)) return false;
		
		RecordType rt = (RecordType) o;
		
		return elements.equals(rt.elements);
		
	}
	
	@Override
	public String toString() {
		return "Record....";
	}

	public boolean isSubtypeOf(Type superType) {
		if (!(superType instanceof RecordType)) return false;
		
		RecordType superTypeRec = (RecordType) superType;
		
		for (String label : superTypeRec.elements.keySet()) {
			if(!this.elements.containsKey(label))
				return false;
			
			Type elementType = this.elements.get(label);
			Type superTypeElementType = superTypeRec.elements.get(label);
			
			if(!(elementType.isSubtypeOf(superTypeElementType)))
				return false;
		}
		
		return true;
	}
}
