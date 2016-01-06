package lambda.typen;
import java.util.*;
import java.util.Map.Entry;
public class StrukturTyp extends Typ {
	private Map<String,Typ> typen;
	public StrukturTyp() {
		typen=new TreeMap<String,Typ>();
	}
	public synchronized final Map<String,Typ> getTypen() {
		return typen;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((typen == null) ? 0 : typen.hashCode());
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
		if (!(obj instanceof StrukturTyp)) {
			return false;
		}
		StrukturTyp other = (StrukturTyp) obj;
		if (typen == null) {
			if (other.typen != null) {
				return false;
			}
		} else if (!typen.equals(other.typen)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StrukturTyp [");
		if (typen != null)
			builder.append("typen=").append(typen);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public boolean istUntertypVon(Typ t) {
		if(t instanceof StrukturTyp) {
			StrukturTyp other=(StrukturTyp)t;
			for(Entry<String,Typ>eintrag:other.typen.entrySet()) {
				Typ ourEntry=typen.get(eintrag.getKey());
				if(!ourEntry.istUntertypVon(eintrag.getValue())) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
}