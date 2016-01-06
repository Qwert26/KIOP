package lambda;
import java.util.Arrays;
import java.util.Set;
import lambda.typen.*;
public class Sequenz extends Ausdruck {
	private Ausdruck[] ausdrücke;
	public Sequenz(Ausdruck...ausdrücke) {
		this.ausdrücke=ausdrücke;
	}
	public synchronized final Ausdruck[] getAusdrücke() {
		return ausdrücke;
	}
	@Override
	@Deprecated
	public Set<String> freieVariablen() {
		return null;
	}
	@Override
	@Deprecated
	public Set<String> gebundeneVariablen() {
		return null;
	}
	@Override
	public Ausdruck substitution(String name,Ausdruck ersatz) {
		for(int i=0;i<ausdrücke.length;i++) {
			ausdrücke[i]=ausdrücke[i].substitution(name, ersatz);
		}
		return this;
	}
	@Override
	public boolean istReduzierbar() {
		for(int i=0;i<ausdrücke.length;i++) {
			if(ausdrücke[i].istReduzierbar()) {
				return true;
			}
		}
		return false;
	}
	@Override
	public Ausdruck reduziere() {
		for(int i=0;i<ausdrücke.length;i++) {
			if(ausdrücke[i].istReduzierbar()) {
				ausdrücke[i]=ausdrücke[i].reduziere();
				break;
			}
		}
		return this;
	}
	@Override
	public boolean umbenennen(String von, String zu) {
		boolean ret=false;
		for(int i=0;i<ausdrücke.length;i++) {
			ret|=ausdrücke[i].umbenennen(von,zu);
		}
		return ret;
	}
	@Override
	@Deprecated
	public Umgebung extrahiereUmgebung() {
		return null;
	}
	@Override
	public Typ bestimmeTyp(Umgebung e) {
		Typ ret=null;
		for(int i=0;i<ausdrücke.length;i++) {
			ret=ausdrücke[i].bestimmeTyp(e);
		}
		return ret;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sequenz [");
		if (ausdrücke != null)
			builder.append("ausdrücke=").append(Arrays.toString(ausdrücke));
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(ausdrücke);
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
		if (!(obj instanceof Sequenz)) {
			return false;
		}
		Sequenz other = (Sequenz) obj;
		if (!Arrays.equals(ausdrücke, other.ausdrücke)) {
			return false;
		}
		return true;
	}
}