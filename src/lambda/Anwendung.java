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
			anwendung=anwendung.substitution(((Abstraktion)anwendung).getName(),anwender);
			return ((Abstraktion)anwendung).getTerm();
		} else {
			return this;
		}
	}
}