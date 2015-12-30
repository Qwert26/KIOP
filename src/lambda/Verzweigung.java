package lambda;
import java.util.*;
import lambda.typen.*;
public class Verzweigung extends Ausdruck {
	private Ausdruck wenn,dann,sonst;
	public Verzweigung(Ausdruck w,Ausdruck d,Ausdruck s) {
		wenn=w;
		dann=d;
		sonst=s;
	}
	@Override
	public Set<String> freieVariablen() {
		Set<String> ret=wenn.freieVariablen();
		ret.addAll(dann.freieVariablen());
		ret.addAll(sonst.freieVariablen());
		return ret;
	}
	@Override
	public Set<String> gebundeneVariablen() {
		Set<String> ret=wenn.gebundeneVariablen();
		ret.retainAll(dann.gebundeneVariablen());
		ret.retainAll(sonst.gebundeneVariablen());
		return ret;
	}
	@Override
	public Ausdruck substitution(String name, Ausdruck ersatz) {
		wenn=wenn.substitution(name,ersatz);
		dann=dann.substitution(name,ersatz);
		sonst=sonst.substitution(name,ersatz);
		return this;
	}
	@Override
	public boolean istReduzierbar() {
		return true;
	}
	@Override
	public Ausdruck reduziere() {
		if(wenn.istReduzierbar()) {
			wenn=wenn.reduziere();
			return this;
		} else {
			Variable v=(Variable)wenn;
			if(Ausdruckskonstanten.valueOf(v.getName())==Ausdruckskonstanten.TRUE) {
				return dann;
			} else {
				return sonst;
			}
		}
	}
	@Override
	public boolean umbenennen(String von, String zu) {
		return wenn.umbenennen(von, zu)|dann.umbenennen(von, zu)|sonst.umbenennen(von, zu);
	}
	@Override
	public Umgebung extrahiereUmgebung() {
		Umgebung ret=wenn.extrahiereUmgebung();
		ret.erweitereUmUmgebung(dann.extrahiereUmgebung());
		ret.erweitereUmUmgebung(sonst.extrahiereUmgebung());
		return ret;
	}
	@Override
	public Typ bestimmeTyp(Umgebung e) {
		if(wenn.bestimmeTyp(e) instanceof Wahrheitswert) {
			Typ dann=this.dann.bestimmeTyp(e);
			if(dann.equals(sonst.bestimmeTyp(e))) {
				return dann;
			} else {
				throw new RuntimeException("Der Typ des Dann-Teil ist nicht der selbe wie der Sonst-Teil!");
			}
		} else {
			throw new RuntimeException("Der Typ der Bedingung ist kein Wahrheitswert!");
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dann == null) ? 0 : dann.hashCode());
		result = prime * result + ((sonst == null) ? 0 : sonst.hashCode());
		result = prime * result + ((wenn == null) ? 0 : wenn.hashCode());
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
		if (!(obj instanceof Verzweigung)) {
			return false;
		}
		Verzweigung other = (Verzweigung) obj;
		if (dann == null) {
			if (other.dann != null) {
				return false;
			}
		} else if (!dann.equals(other.dann)) {
			return false;
		}
		if (sonst == null) {
			if (other.sonst != null) {
				return false;
			}
		} else if (!sonst.equals(other.sonst)) {
			return false;
		}
		if (wenn == null) {
			if (other.wenn != null) {
				return false;
			}
		} else if (!wenn.equals(other.wenn)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Verzweigung [");
		if (wenn != null)
			builder.append("wenn=").append(wenn).append(", ");
		if (dann != null)
			builder.append("dann=").append(dann).append(", ");
		if (sonst != null)
			builder.append("sonst=").append(sonst);
		builder.append("]");
		return builder.toString();
	}
}