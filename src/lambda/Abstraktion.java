package lambda;
public class Abstraktion extends Ausdruck {
	/**
	 * Der Name der Variablen.
	 */
	private String name;
	/**
	 * Der Ausdruck in dem die Variable gilt.
	 */
	private Ausdruck term;
	public Abstraktion(String name,Ausdruck term) {
		this.name=name;
		this.term=term;
	}
	public synchronized final String getName() {
		return name;
	}
	public synchronized final Ausdruck getTerm() {
		return term;
	}
}