package lambda;
import java.util.*;
import lambda.typen.*;
class NachfolgerAusdruck extends Ausdruck {
	private Ausdruck zahl;
	public NachfolgerAusdruck() {
		super();
	}
	@Override
	public Set<String> freieVariablen() {
		return new TreeSet<String>();
	}
	@Override
	public Set<String> gebundeneVariablen() {
		return new TreeSet<String>();
	}
	@Override
	public Ausdruck substitution(String name, Ausdruck ersatz) {
		if(zahl==null) {
			zahl=ersatz;
		}
		return this;
	}
	@Override
	public boolean istReduzierbar() {
		return zahl!=null;
	}
	@Override
	public Ausdruck reduziere() {
		if(zahl.istReduzierbar()) {
			zahl=zahl.reduziere();
			return this;
		} else {
			Variable v=(Variable)zahl;
			return new Variable(1+Long.parseLong(v.getName()));
		}
	}
	@Override
	public boolean istAusdruckskonstante() {
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NachfolgerAusdruck [");
		if (zahl != null)
			builder.append("zahl=").append(zahl);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public boolean umbenennen(String von, String zu) {
		return false;
	}
	@Override
	public Umgebung extrahiereUmgebung() {
		return null;
	}
	@Override
	public Typ bestimmeTyp(Umgebung e) {
		return Ausdruckskonstanten.SUCC.erhalteTyp();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((zahl == null) ? 0 : zahl.hashCode());
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
		if (!(obj instanceof NachfolgerAusdruck)) {
			return false;
		}
		NachfolgerAusdruck other = (NachfolgerAusdruck) obj;
		if (zahl == null) {
			if (other.zahl != null) {
				return false;
			}
		} else if (!zahl.equals(other.zahl)) {
			return false;
		}
		return true;
	}
}