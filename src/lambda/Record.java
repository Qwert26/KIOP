package lambda;
import java.util.HashMap;
import java.util.Set;
/*
  zB { x = 12}, {x = + 1 1, y= AND true false} 
 */
public class Record extends Expression {

	HashMap<String, Expression> elements = new HashMap<String, Expression>();

	
	public Record() {
	}

	/**
	 *                   ti -> ti'
	 *     ===================================================
	 * 		{l1=t1, ....ln=tn} -> {l1=t1, ...li=ti'.....ln=tn} 
	 */
	public Record reduce() {
		for (String label : elements.keySet()) {
			Expression ti = elements.get(label);
			if (ti.isReducible()) {
				elements.put(label, ti.reduce());
				return this;
			}
		}
		return this;
	}

	@Override
	public Expression substituteWith(String aName, Expression exp) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public boolean isReducible() {
		for (String label : elements.keySet()) {
			Expression ti = elements.get(label);
			if (ti.isReducible()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Set FI() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *                   E |- ti: Ti
	 *     =================================================
	 * 		E |- {l1=t1, ....ln=tn}: {l1: T1, .... ln: Tn} 
	 */
	@Override
	public RecordType getType(Environment e) {
		RecordType type = new RecordType();
		
		for (String label : elements.keySet()) {
			Expression ti = elements.get(label);
			Type t = ti.getType(e);
			type.elements.put(label, t);
		}

		return type;
	}

}
