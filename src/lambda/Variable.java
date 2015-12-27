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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (!(obj instanceof Variable)) {
			return false;
		}
		Variable other = (Variable) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Variable [");
		if (name != null)
			builder.append("name=").append(name);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public boolean umbenennen(String von,String zu) {
		if(name.equals(von)) {
			name=zu;
			return true;
		} else {
			return false;
		}
	}
	@Override
	public Set<String> gebundeneVariablen() {
		return new TreeSet<String>();
	}
}