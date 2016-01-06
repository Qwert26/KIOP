package lambda;
import java.util.Set;
import lambda.typen.*;
public class Als extends Ausdruck {
	private Typ zielTyp;
	private Ausdruck ausdruck;
	public Als(Typ zielTyp,Ausdruck ausdruck) {
		this.zielTyp=zielTyp;
		this.ausdruck=ausdruck;
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
	public Ausdruck substitution(String name, Ausdruck ersatz) {
		ausdruck=ausdruck.substitution(name, ersatz);
		return this;
	}
	@Override
	public boolean istReduzierbar() {
		return true;
	}
	@Override
	public Ausdruck reduziere() {
		return ausdruck;
	}
	@Override
	public boolean umbenennen(String von, String zu) {
		return ausdruck.umbenennen(von,zu);
	}
	@Override
	public Umgebung extrahiereUmgebung() {
		return ausdruck.extrahiereUmgebung();
	}
	@Override
	public Typ bestimmeTyp(Umgebung e) {
		ausdruck.bestimmeTyp(e);
		return zielTyp;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Als [");
		if (zielTyp != null)
			builder.append("zielTyp=").append(zielTyp).append(", ");
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
		result = prime * result + ((zielTyp == null) ? 0 : zielTyp.hashCode());
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
		if (!(obj instanceof Als)) {
			return false;
		}
		Als other = (Als) obj;
		if (ausdruck == null) {
			if (other.ausdruck != null) {
				return false;
			}
		} else if (!ausdruck.equals(other.ausdruck)) {
			return false;
		}
		if (zielTyp == null) {
			if (other.zielTyp != null) {
				return false;
			}
		} else if (!zielTyp.equals(other.zielTyp)) {
			return false;
		}
		return true;
	}
}