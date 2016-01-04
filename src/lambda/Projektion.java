package lambda;
import java.util.Set;
import lambda.typen.*;
public class Projektion extends Ausdruck {
	private String label;
	private Ausdruck ausdruck;
	public Projektion(String label,Ausdruck ausdruck) {
		this.label=label;
		this.ausdruck=ausdruck;
	}
	public synchronized final String getLabel() {
		return label;
	}

	public synchronized final Ausdruck getAusdruck() {
		return ausdruck;
	}
	@Override
	public Set<String> freieVariablen() {
		return ausdruck.freieVariablen();
	}
	@Override
	public Set<String> gebundeneVariablen() {
		return ausdruck.gebundeneVariablen();
	}
	@Override
	public Projektion substitution(String name,Ausdruck ersatz) {
		ausdruck=ausdruck.substitution(name,ersatz);
		return this;
	}
	@Override
	public boolean istReduzierbar() {
		return ausdruck.istReduzierbar()||ausdruck instanceof Struktur;
	}
	@Override
	public Ausdruck reduziere() {
		if(ausdruck.istReduzierbar()) {
			ausdruck=ausdruck.reduziere();
			return this;
		} else if (ausdruck instanceof Struktur) {
			return ((Struktur)ausdruck).getInhalt().get(label);
		} else {
			return this;
		}
	}
	@Override
	public boolean umbenennen(String von,String zu) {
		if(label.equals(von)) {
			label=zu;
			ausdruck.umbenennen(von,zu);
			return true;
		} else {
			return ausdruck.umbenennen(von,zu);
		}
	}
	@Override
	public Umgebung extrahiereUmgebung() {
		return ausdruck.extrahiereUmgebung();
	}
	@Override
	public Typ bestimmeTyp(Umgebung e) {
		Typ t=ausdruck.bestimmeTyp(e);
		if(t instanceof StrukturTyp) {
			return((StrukturTyp)t).getTypen().get(label);
		} else {
			throw new RuntimeException("Der übergebene Ausdruck ist kein Strukturtyp!");
		}
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Projektion [");
		if (label != null)
			builder.append("label=").append(label).append(", ");
		if (ausdruck != null)
			builder.append("ausdruck=").append(ausdruck);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ausdruck == null) ? 0 : ausdruck.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
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
		if (!(obj instanceof Projektion)) {
			return false;
		}
		Projektion other = (Projektion) obj;
		if (ausdruck == null) {
			if (other.ausdruck != null) {
				return false;
			}
		} else if (!ausdruck.equals(other.ausdruck)) {
			return false;
		}
		if (label == null) {
			if (other.label != null) {
				return false;
			}
		} else if (!label.equals(other.label)) {
			return false;
		}
		return true;
	}
}