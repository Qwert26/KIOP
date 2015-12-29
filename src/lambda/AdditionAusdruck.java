package lambda;
import java.util.*;
import lambda.typen.*;
class AdditionAusdruck extends Ausdruck {
	private Ausdruck links,rechts;
	public AdditionAusdruck() {
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
		if(links==null) {
			links=ersatz;
		} else if(rechts==null) {
			rechts=ersatz;
		}
		return this;
	}
	@Override
	public boolean istReduzierbar() {
		return links!=null&rechts!=null;
	}
	@Override
	public Ausdruck reduziere() {
		if(links.istReduzierbar()) {
			links=links.reduziere();
			return this;
		} else if(rechts.istReduzierbar()) {
			rechts=rechts.reduziere();
			return this;
		} else {
			Variable vl=(Variable)links,vr=(Variable)rechts;
			long wertl=Long.parseLong(vl.getName()),wertr=Long.parseLong(vr.getName());
			return new Variable(wertl+wertr);
		}
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdditionAusdruck [");
		if (links != null)
			builder.append("links=").append(links).append(", ");
		if (rechts != null)
			builder.append("rechts=").append(rechts);
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
		return Ausdruckskonstanten.PLUS.erhalteTyp();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((links == null) ? 0 : links.hashCode());
		result = prime * result + ((rechts == null) ? 0 : rechts.hashCode());
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
		if (!(obj instanceof AdditionAusdruck)) {
			return false;
		}
		AdditionAusdruck other = (AdditionAusdruck) obj;
		if (links == null) {
			if (other.links != null) {
				return false;
			}
		} else if (!links.equals(other.links)) {
			return false;
		}
		if (rechts == null) {
			if (other.rechts != null) {
				return false;
			}
		} else if (!rechts.equals(other.rechts)) {
			return false;
		}
		return true;
	}
	@Override
	public boolean istAusdruckskonstante() {
		return true;
	}
}