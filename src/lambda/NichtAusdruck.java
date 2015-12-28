package lambda;
import java.util.*;
import lambda.typen.*;
class NichtAusdruck extends Ausdruck {
	private Ausdruck ausdruck;
	public NichtAusdruck() {
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
		if(ausdruck==null) {
			ausdruck=ersatz;
		}
		return this;
	}
	@Override
	public boolean istReduzierbar() {
		return ausdruck!=null;
	}
	@Override
	public Ausdruck reduziere() {
		if(ausdruck.istReduzierbar()) {
			ausdruck=ausdruck.reduziere();
			return this;
		} else {
			Variable v=(Variable)ausdruck;
			Ausdruckskonstanten wert=Ausdruckskonstanten.valueOf(v.getName());
			if(wert==Ausdruckskonstanten.FALSE) {
				return new Variable(Ausdruckskonstanten.TRUE);
			} else {
				return new Variable(Ausdruckskonstanten.FALSE);
			}
		}
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
		return Ausdruckskonstanten.NOT.erhalteTyp();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ausdruck == null) ? 0 : ausdruck.hashCode());
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
		if (!(obj instanceof NichtAusdruck)) {
			return false;
		}
		NichtAusdruck other = (NichtAusdruck) obj;
		if (ausdruck == null) {
			if (other.ausdruck != null) {
				return false;
			}
		} else if (!ausdruck.equals(other.ausdruck)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NichtAusdruck [");
		if (ausdruck != null)
			builder.append("ausdruck=").append(ausdruck);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public boolean istAusdruckskonstante() {
		return true;
	}
}