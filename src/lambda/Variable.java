package lambda;
import java.util.*;
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
	@Override
	public Set<String> freieVariablen() {
		Set<String> ret=new TreeSet<String>();
		ret.add(name);
		return ret;
	}
	@Override
	public Ausdruck substitution(String name, Ausdruck ersatz) {
		if(this.name.equals(name)) {
			return ersatz;
		} else {
			return this;
		}
	}
	/**
	 * Variablen lassen sich nicht reduzieren.
	 */
	@Override
	public boolean istReduzierbar() {
		return false;
	}
	/**
	 * Variablen geben immer sich selbst zurück.
	 * @return
	 * <tt>this</tt>
	 */
	@Override
	public Variable reduziere() {
		return this;
	}
}