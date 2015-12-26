package lambda;
import java.util.Set;
public class Anwendung extends Ausdruck {
	/**
	 * Der Ausdruck, in dem etwas angewendet werden soll. Die linke Seite.
	 */
	private Ausdruck anwendung;
	/**
	 * Der Ausdruck, der in der {@link #anwendung} angewendet werden soll. Die rechte Seite.
	 */
	private Ausdruck anwender;
	public Anwendung(Ausdruck anwendung,Ausdruck anwender) {
		this.anwender=anwender;
		this.anwendung=anwendung;
	}
	public synchronized final Ausdruck getAnwendung() {
		return anwendung;
	}
	public synchronized final Ausdruck getAnwender() {
		return anwender;
	}
	@Override
	public Set<String> freieVariablen() {
		Set<String> ret=anwender.freieVariablen();
		ret.retainAll(anwendung.freieVariablen());
		return ret;
	}
	@Override
	public Ausdruck substitution(String name, Ausdruck ersatz) {
		anwender=anwender.substitution(name, ersatz);
		anwendung=anwendung.substitution(name, ersatz);
		return this;
	}
	@Override
	public boolean istReduzierbar() {
		return anwender.istReduzierbar()||anwendung instanceof Abstraktion;
	}
	@Override
	public Ausdruck reduziere() {
		if(anwender.istReduzierbar()) {
			anwender=anwender.reduziere();
			return this;
		} else if (anwendung instanceof Abstraktion) {
			Abstraktion abs=(Abstraktion)anwendung;
			return abs.getTerm().substitution(abs.getName(),anwender);
		} else {
			return this;
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anwender == null) ? 0 : anwender.hashCode());
		result = prime * result + ((anwendung == null) ? 0 : anwendung.hashCode());
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
		if (!(obj instanceof Anwendung)) {
			return false;
		}
		Anwendung other = (Anwendung) obj;
		if (anwender == null) {
			if (other.anwender != null) {
				return false;
			}
		} else if (!anwender.equals(other.anwender)) {
			return false;
		}
		if (anwendung == null) {
			if (other.anwendung != null) {
				return false;
			}
		} else if (!anwendung.equals(other.anwendung)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Anwendung [");
		if (anwendung != null)
			builder.append("anwendung=").append(anwendung).append(", ");
		if (anwender != null)
			builder.append("anwender=").append(anwender);
		builder.append("]");
		return builder.toString();
	}
}