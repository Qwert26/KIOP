package lambda;
import java.util.*;
import java.util.Map.Entry;
import lambda.typen.*;
public class Struktur extends Ausdruck {
	private Map<String,Ausdruck> inhalt;
	public Struktur() {
		inhalt=new TreeMap<String,Ausdruck>();
	}
	public synchronized final Map<String, Ausdruck> getInhalt() {
		return inhalt;
	}
	@Override
	public Set<String> freieVariablen() {
		return new TreeSet<String>(inhalt.keySet());
	}
	@Override
	public Set<String> gebundeneVariablen() {
		return new TreeSet<String>();
	}
	@Override
	public Ausdruck substitution(String name,Ausdruck ersatz) {
		return this;
	}
	@Override
	public boolean istReduzierbar() {
		for(Ausdruck ausdruck:inhalt.values()) {
			if(ausdruck.istReduzierbar()) {
				return true;
			}
		}
		return false;
	}
	@Override
	public Struktur reduziere() {
		for(Entry<String,Ausdruck> entry:inhalt.entrySet()) {
			Ausdruck temp=entry.getValue();
			if(temp.istReduzierbar()) {
				temp=temp.reduziere();
				entry.setValue(temp);
				return this;
			}
		}
		return this;
	}
	@Override
	public boolean umbenennen(String von,String zu) {
		return false;
	}
	@Override
	public Umgebung extrahiereUmgebung() {
		Umgebung ret=new Umgebung();
		for(Ausdruck ausdruck:inhalt.values()) {
			ret.erweitereUmUmgebung(ausdruck.extrahiereUmgebung());
		}
		return ret;
	}
	@Override
	public StrukturTyp bestimmeTyp(Umgebung e) {
		StrukturTyp ret=new StrukturTyp();
		for(Entry<String,Ausdruck>entry:inhalt.entrySet()) {
			Typ t=entry.getValue().bestimmeTyp(e);
			ret.getTypen().put(entry.getKey(),t);
		}
		return ret;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inhalt == null) ? 0 : inhalt.hashCode());
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
		if (!(obj instanceof Struktur)) {
			return false;
		}
		Struktur other = (Struktur) obj;
		if (inhalt == null) {
			if (other.inhalt != null) {
				return false;
			}
		} else if (!inhalt.equals(other.inhalt)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Struktur [");
		if (inhalt != null)
			builder.append("inhalt=").append(inhalt);
		builder.append("]");
		return builder.toString();
	}
}