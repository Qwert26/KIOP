package lambda;
import java.util.*;
public abstract class Ausdruck {
	public Ausdruck() {
		super();
	}
	/**
	 * @return
	 * Die Namen der freien Variablen
	 */
	public abstract Set<String> freieVariablen();
	/**
	 * F�hrt eine Ersetzung durch, in dem Variablen, die den �bergebenen Namen besitzen, sich durch den �bergebenen Ausdruck ersetzen.
	 * @param name
	 * Was ersetzt werden soll.
	 * @param ersatz
	 * Womit ersetzt werden soll.
	 * @return
	 */
	public abstract Ausdruck substitution(String name,Ausdruck ersatz);
	/**
	 * @return
	 * <tt>true</tt> wenn sich der Ausdruck reduzieren kann.
	 */
	public abstract boolean istReduzierbar();
	/**
	 * @return
	 */
	public abstract Ausdruck reduziere();
	@Override
	public abstract int hashCode();
	@Override
	public abstract boolean equals(Object o);
}