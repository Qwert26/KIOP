package lambda;
import java.util.Arrays;
import java.util.Set;
import lambda.typen.*;
public class Sequenz extends Ausdruck {
	private Ausdruck[] ausdr�cke;
	public Sequenz(Ausdruck...ausdr�cke) {
		this.ausdr�cke=ausdr�cke;
	}
	public synchronized final Ausdruck[] getAusdr�cke() {
		return ausdr�cke;
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
		for(int i=0;i<ausdr�cke.length;i++) {
			ausdr�cke[i]=ausdr�cke[i].substitution(name, ersatz);
		}
		return this;
	}
	@Override
	public boolean istReduzierbar() {
		for(int i=0;i<ausdr�cke.length;i++) {
			if(ausdr�cke[i].istReduzierbar()) {
				return true;
			}
		}
		return false;
	}
	@Override
	public Ausdruck reduziere() {
		for(int i=0;i<ausdr�cke.length;i++) {
			if(ausdr�cke[i].istReduzierbar()) {
				ausdr�cke[i]=ausdr�cke[i].reduziere();
				break;
			}
		}
		return this;
	}
	@Override
	public boolean umbenennen(String von, String zu) {
		boolean ret=false;
		for(int i=0;i<ausdr�cke.length;i++) {
			ret|=ausdr�cke[i].umbenennen(von,zu);
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
		for(int i=0;i<ausdr�cke.length;i++) {
			ret=ausdr�cke[i].bestimmeTyp(e);
		}
		return ret;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sequenz [");
		if (ausdr�cke != null)
			builder.append("ausdr�cke=").append(Arrays.toString(ausdr�cke));
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(ausdr�cke);
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
		if (!Arrays.equals(ausdr�cke, other.ausdr�cke)) {
			return false;
		}
		return true;
	}
}