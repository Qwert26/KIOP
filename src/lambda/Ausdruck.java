package lambda;
import java.util.*;
import lambda.typen.*;
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
	 * @return
	 * Die Namen der gebundenen Variablen
	 */
	public abstract Set<String> gebundeneVariablen();
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
	/**
	 * Bennent Sachen um, wenn sie entsprechende Namen haben.
	 * @param von
	 * Was umbenannt werden soll.
	 * @param zu
	 * Was der neue Name sein soll.
	 * @return
	 * <tt>true</tt>, wenn irgendwas umbenannt wurde sonst <tt>false</tt>.
	 */
	public abstract boolean umbenennen(String von,String zu);
	/**
	 * @return
	 * <tt>false</tt>, nur Variablen k�nnen Ausdruckskonstanten sein.
	 */
	public boolean istAusdruckskonstante() {
		return false;
	}
	/**
	 * @return
	 * Eine Umgebung, welche nur die Typen von Ausdruckskonstanten enth�lt.
	 */
	public abstract Umgebung extrahiereUmgebung();
	/**
	 * @param e
	 * @return
	 */
	public abstract Typ bestimmeTyp(Umgebung e);
	@Override
	public abstract int hashCode();
	@Override
	public abstract boolean equals(Object o);
}