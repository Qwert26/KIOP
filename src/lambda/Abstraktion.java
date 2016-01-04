package lambda;
import java.util.*;
import lambda.typen.*;
public class Abstraktion extends Ausdruck {
	/**
	 * Der Name der Variablen.
	 */
	private String name;
	/**
	 * Der Typ der Variablen.
	 */
	private Typ typ;
	/**
	 * Der Ausdruck in dem die Variable gilt.
	 */
	private Ausdruck term;
	public Abstraktion(String name,Ausdruck term) {
		super();
		this.name=name;
		this.term=term;
	}
	public Abstraktion(String name,Typ typ,Ausdruck term) {
		this(name,term);
		this.typ=typ;
	}
	public synchronized final String getName() {
		return name;
	}
	public synchronized final Ausdruck getTerm() {
		return term;
	}
	public synchronized final Typ getTyp() {
		return typ;
	}
	@Override
	public Set<String> freieVariablen() {
		Set<String> ret=term.freieVariablen();
		ret.remove(name);
		return ret;
	}
	@Override
	public Abstraktion substitution(String name, Ausdruck ersatz) {
		if((!this.name.equals(name))&&(freieVariablen().contains(name))) {
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((term == null) ? 0 : term.hashCode());
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
		if (!(obj instanceof Abstraktion)) {
			return false;
		}
		Abstraktion other = (Abstraktion) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (term == null) {
			if (other.term != null) {
				return false;
			}
		} else if (!term.equals(other.term)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Abstraktion [");
		if (name != null)
			builder.append("name=").append(name).append(", ");
		if (term != null)
			builder.append("term=").append(term);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public boolean umbenennen(String von,String zu) {
		boolean ret=false;
		if(name.equals(von)) {
			name=zu;
			ret=true;
		}
		return ret|term.umbenennen(von,zu);
	}
	@Override
	public Set<String> gebundeneVariablen() {
		Set<String> ret=term.gebundeneVariablen();
		ret.add(name);
		return ret;
	}
	@Override
	public Umgebung extrahiereUmgebung() {
		return term.extrahiereUmgebung();
	}
	@Override
	public FunktionsTyp bestimmeTyp(Umgebung e) {
		Typ term=this.term.bestimmeTyp(e.fügeHinzu(name,typ));
		return new FunktionsTyp(typ,term);
	}
}