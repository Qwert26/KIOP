package lambda;
import java.util.Set;
public class Anwendung extends Ausdruck {
	/**
	 * Der Ausdruck, in dem etwas angewendet werden soll.
	 */
	private Ausdruck anwendung;
	/**
	 * Der Ausdruck, der in der {@link #anwendung} angewendet werden soll.
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
}