package lambda;
import java.util.*;
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
	@Override
	public Set<String> freieVariablen() {
		Set<String> ret=term.freieVariablen();
		ret.remove(name);
		return ret;
	}
	@Override
	public Ausdruck substitution(String name, Ausdruck ersatz) {
		if(!freieVariablen().contains(name)) {
			term=term.substitution(name,ersatz);
		}
		return this;
	}
	@Override
	public boolean istReduzierbar() {
		return term.istReduzierbar();
	}
	@Override
	public Ausdruck reduziere() {
		if(istReduzierbar()) {
			term=term.reduziere();
		}
		return this;
	}
}