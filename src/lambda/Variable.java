package lambda;
public class Variable extends Ausdruck {
	/**
	 * Der Name der Variablen.
	 */
	private String name;
	public Variable(String name) {
		this.name=name;
	}
	public synchronized final String getName() {
		return name;
	}
}