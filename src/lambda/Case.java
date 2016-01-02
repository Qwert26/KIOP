package lambda;
import java.util.*;
import lambda.typen.*;
public class Case extends Ausdruck {
	private Ausdruck ausdruck;
	private Ausdruck[] terme;
	public Case(Ausdruck ausdruck,Ausdruck...terme) {
		this.ausdruck=ausdruck;
		this.terme=terme;
	}
	public synchronized final Ausdruck getAusdruck() {
		return ausdruck;
	}

	public synchronized final Ausdruck[] getTerme() {
		return terme;
	}
	@Override
	public Set<String> freieVariablen() {
		return ausdruck.freieVariablen();
	}
	@Override
	public Set<String> gebundeneVariablen() {
		return ausdruck.gebundeneVariablen();
	}
	@Override
	public Ausdruck substitution(String name,Ausdruck ersatz) {
		ausdruck=ausdruck.substitution(name, ersatz);
		return this;
	}
	@Override
	public boolean istReduzierbar() {
		return true;
	}
	@Override
	public Ausdruck reduziere() {
		if(ausdruck.istReduzierbar()) {
			ausdruck=ausdruck.reduziere();
			return this;
		} else {
			In i=(In)ausdruck;
			if(i.getKapsel().getTypen().length==terme.length) {
				return new Anwendung(terme[i.getIndex()],i.getKapselung());
			} else {
				throw new RuntimeException("Unerwarteter Längenunterschied!");
			}
		}
	}
	@Override
	public boolean umbenennen(String von, String zu) {
		boolean a=ausdruck.umbenennen(von,zu);
		for(Ausdruck t:terme) {
			a|=t.umbenennen(von, zu);
		}
		return a;
	}
	@Override
	public Umgebung extrahiereUmgebung() {
		Umgebung ret=ausdruck.extrahiereUmgebung();
		for(Ausdruck t:terme) {
			ret.erweitereUmUmgebung(t.extrahiereUmgebung());
		}
		return ret;
	}
	@Override
	public Typ bestimmeTyp(Umgebung e) {
		Typ[] typen=new Typ[terme.length];
		for(int i=0;i<typen.length;i++) {
			typen[i]=terme[i].bestimmeTyp(e);
		}
		for(int i=1;i<typen.length;i++) {
			if(!typen[i].equals(typen[i-1])) {
				throw new RuntimeException("Die einzelnen Terme haben nicht alle den selben Typen!");
			}
		}
		if(ausdruck.bestimmeTyp(e)instanceof SummenTyp) {
			return typen[0];
		} else {
			throw new RuntimeException("Der übergebene Ausdruck ist kein Summentyp!");
		}
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Case [");
		if (ausdruck != null)
			builder.append("ausdruck=").append(ausdruck).append(", ");
		if (terme != null)
			builder.append("terme=").append(Arrays.toString(terme));
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ausdruck == null) ? 0 : ausdruck.hashCode());
		result = prime * result + Arrays.hashCode(terme);
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
		if (!(obj instanceof Case)) {
			return false;
		}
		Case other = (Case) obj;
		if (ausdruck == null) {
			if (other.ausdruck != null) {
				return false;
			}
		} else if (!ausdruck.equals(other.ausdruck)) {
			return false;
		}
		if (!Arrays.equals(terme, other.terme)) {
			return false;
		}
		return true;
	}
}