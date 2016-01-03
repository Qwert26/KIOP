package lambda;
import java.util.*;
import lambda.typen.*;
class UndAusdruck extends Ausdruck {
	private Ausdruck links,rechts;
	public UndAusdruck() {
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
	public Ausdruck substitution(String name,Ausdruck ersatz) {
		if(links==null) {
			links=ersatz;
		} else if(rechts==null) {
			rechts=ersatz;
		} else {
			links=links.substitution(name,ersatz);
			rechts=rechts.substitution(name,ersatz);
		}
		return this;
	}
	@Override
	public boolean istReduzierbar() {
		if(links==null||rechts==null) {
			return false;
		} else {
			return (links.istAusdruckskonstante()&rechts.istAusdruckskonstante())|links.istReduzierbar()|rechts.istReduzierbar();
		}
	}
	@Override
	public Ausdruck reduziere() {
		if(links.istReduzierbar()) {
			links=links.reduziere();
			return this;
		} else if(rechts.istReduzierbar()) {
			rechts=rechts.reduziere();
			return this;
		} else if(links.istAusdruckskonstante()&rechts.istAusdruckskonstante()) {
			Variable vl=(Variable)links;
			Variable vr=(Variable)rechts;
			Ausdruckskonstanten konl=Ausdruckskonstanten.valueOf(vl.getName()),konr=Ausdruckskonstanten.valueOf(vr.getName());
			if(konl==Ausdruckskonstanten.FALSE) {
				return new Variable(konl);
			} else {
				return new Variable(konr);
			}
		} else {
			return this;
		}
	}
	@Override
	public boolean umbenennen(String von,String zu) {
		return false;
	}
	@Override
	public Umgebung extrahiereUmgebung() {
		return null;
	}
	@Override
	public Typ bestimmeTyp(Umgebung e) {
		FunktionsTyp t=(FunktionsTyp)Ausdruckskonstanten.AND.erhalteTyp();
		if(links==null) {
			return t;
		} else {
			if(rechts==null) {
				return t.getOutput();
			} else {
				return ((FunktionsTyp)t.getOutput()).getOutput();
			}
		}
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
		if (!(obj instanceof UndAusdruck)) {
			return false;
		}
		UndAusdruck other = (UndAusdruck) obj;
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
	/**
	 * @return
	 * <tt>true</tt>
	 */
	@Override
	public boolean istAusdruckskonstante() {
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UndAusdruck [");
		if (links != null)
			builder.append("links=").append(links).append(", ");
		if (rechts != null)
			builder.append("rechts=").append(rechts);
		builder.append("]");
		return builder.toString();
	}
}