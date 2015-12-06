package lambda;
import java.util.*;
import java.util.Map.Entry;
public class Record extends Expression {
	public static final String PROJECTION_OPERATOR=".";
	public Map<String,Expression> contents;
	public String name;
	public Record(String name) {
		this.name=name;
		contents=new HashMap<String,Expression>();
	}
	@Override
	public Expression reduce() {
		return this;
	}
	@Override
	public Expression substituteWith(String aName,Expression exp) {
		return null;
	}
	@Override
	public boolean isReducible() {
		return false;
	}
	@Override
	public Set<String> FI() {
		return null;
	}
	@Override
	public Type getType(Environment e) {
		Environment eClone=e.clone();
		eClone.env.put(name,new RecordType());
		int resolves=0;
		//Der letzte Durchlauf hat mindestens einen weiteren Typen bestimmen können.
		boolean lastPassSucessful;
		if(contents.containsKey(null)) {
			throw new RuntimeException("mögliche Expression ohne Label!");
		}
		do {
			lastPassSucessful=false;
			for (Entry<String,Expression>entry:contents.entrySet()) {
				if(entry.getValue()==null) {
					throw new RuntimeException("Label "+entry.getKey()+" ohne Expression!");
				} else {
					if(eClone.env.containsKey(entry.getKey())) {
						continue;
					} else {
						try {
							Type t=entry.getValue().getType(eClone);
							eClone.env.put(name+PROJECTION_OPERATOR+entry.getKey(),t);
							resolves++;
							lastPassSucessful=true;
						} catch(RuntimeException re){
							//Nicht in der Lage gewesen den Typen zu bestimmen, vielleicht beim nächsten Durchlauf.
						}
					}
				}
			}
		} while(lastPassSucessful);
		if(resolves==contents.size()) {
			return eClone.env.get(name);
		} else {
			throw new RuntimeException("Konnte von "+(contents.size()-resolves)+" Einträgen den Typ nicht bestimmen!");
		}
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