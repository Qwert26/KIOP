package lambda;
import java.util.*;
import lambda.typen.*;
public class In extends Ausdruck {
	private SummenTyp kapsel;
	private int index;
	private Ausdruck kapselung;
	public In(SummenTyp kapsel,int index,Ausdruck kapselung) {
		this.kapsel=kapsel;
		this.index=index;
		this.kapselung=kapselung;
	}
	public synchronized final SummenTyp getKapsel() {
		return kapsel;
	}
	public synchronized final int getIndex() {
		return index;
	}
	public synchronized final Ausdruck getKapselung() {
		return kapselung;
	}
	@Override
	public Set<String> freieVariablen() {
		return kapselung.freieVariablen();
	}
	@Override
	public Set<String> gebundeneVariablen() {
		return kapselung.gebundeneVariablen();
	}
	@Override
	public Ausdruck substitution(String name, Ausdruck ersatz) {
		kapselung=kapselung.substitution(name,ersatz);
		return this;
	}
	@Override
	public boolean istReduzierbar() {
		return false;
	}
	@Override
	public Ausdruck reduziere() {
		throw new RuntimeException("Der Ausdruck In kann nur durch den Ausdruck Case reduziert werden!");
	}
	@Override
	public boolean umbenennen(String von, String zu) {
		return kapselung.umbenennen(von,zu);
	}
	@Override
	public Umgebung extrahiereUmgebung() {
		return kapselung.extrahiereUmgebung();
	}
	@Override
	public Typ bestimmeTyp(Umgebung e) {
		if(kapsel.getTypen()[index].equals(kapselung.bestimmeTyp(e))) {
			return kapsel;
		} else {
			throw new RuntimeException("Der Typ des gekapselten Ausdrucks ist nicht der selbe wie der "+index+". Typ des SummenTypen!");
		}
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("In [");
		if (kapsel != null)
			builder.append("kapsel=").append(kapsel).append(", ");
		builder.append("index=").append(index).append(", ");
		if (kapselung != null)
			builder.append("kapselung=").append(kapselung);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		result = prime * result + ((kapsel == null) ? 0 : kapsel.hashCode());
		result = prime * result + ((kapselung == null) ? 0 : kapselung.hashCode());
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
		if (!(obj instanceof In)) {
			return false;
		}
		In other = (In) obj;
		if (index != other.index) {
			return false;
		}
		if (kapsel == null) {
			if (other.kapsel != null) {
				return false;
			}
		} else if (!kapsel.equals(other.kapsel)) {
			return false;
		}
		if (kapselung == null) {
			if (other.kapselung != null) {
				return false;
			}
		} else if (!kapselung.equals(other.kapselung)) {
			return false;
		}
		return true;
	}
}