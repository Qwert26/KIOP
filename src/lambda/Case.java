package lambda;
import java.util.*;
import lambda.typen.*;
public class Case extends Ausdruck {
	private Ausdruck in;
	private Abstraktion[]aktionen;
	public Case(Ausdruck in,Abstraktion...aktionen) {
		this.in=in;
		this.aktionen=aktionen;
	}
	public synchronized final Ausdruck getIn() {
		return in;
	}
	public synchronized final Abstraktion[] getAktionen() {
		return aktionen;
	}
	@Override
	public Set<String> freieVariablen() {
		Set<String> ret=in.freieVariablen();
		for(Abstraktion abs:aktionen) {
			ret.addAll(abs.freieVariablen());
		}
		return ret;
	}
	@Override
	public Set<String> gebundeneVariablen() {
		Set<String> ret=in.gebundeneVariablen();
		for(Abstraktion abs:aktionen) {
			ret.addAll(abs.gebundeneVariablen());
		}
		return ret;
	}
	@Override
	public Case substitution(String name, Ausdruck ersatz) {
		in=in.substitution(name,ersatz);
		return this;
	}
	@Override
	public boolean istReduzierbar() {
		return in.istReduzierbar()||in instanceof In;
	}
	@Override
	public Ausdruck reduziere() {
		if(in.istReduzierbar()) {
			in=in.reduziere();
			return this;
		} else if(in instanceof In) {
			return new Anwendung(aktionen[((In)in).getPosition()],((In)in).getAusdruck());
		} else {
			return this;
		}
	}
	@Override
	public boolean umbenennen(String von, String zu) {
		boolean ret=in.umbenennen(von, zu);
		for(Abstraktion abs:aktionen) {
			ret|=abs.umbenennen(von, zu);
		}
		return ret;
	}
	@Override
	public Umgebung extrahiereUmgebung() {
		Umgebung ret=in.extrahiereUmgebung();
		for(Abstraktion a:aktionen) {
			ret.erweitereUmUmgebung(a.extrahiereUmgebung());
		}
		return ret;
	}
	@Override
	public Typ bestimmeTyp(Umgebung e) {
		SummenTyp inTyp=(SummenTyp)in.bestimmeTyp(e);
		if(inTyp.getTypen().length==aktionen.length) {
			for(int i=0;i<aktionen.length;i++) {
				if(!aktionen[i].getTyp().equals(inTyp.getTypen()[i])) {
					throw new RuntimeException("Der Typ des Parameters der "+i+".Abstraktion stimmt nicht mit dem "+i+".Typen des Summentypen überein!");
				}
			}
			FunktionsTyp[] aktionsTypen=new FunktionsTyp[aktionen.length];
			for(int i=0;i<aktionsTypen.length;i++) {
				aktionsTypen[i]=aktionen[i].bestimmeTyp(e);
			}
			for(int i=1;i<aktionsTypen.length;i++) {
				if(!aktionsTypen[i-1].equals(aktionsTypen[i])) {
					throw new RuntimeException("Die Typen der "+(i-1)+". und "+i+". Abstraktion stimmen nicht überein!");
				}
			}
			return aktionsTypen[0].getOutput();
		} else {
			throw new RuntimeException("Unerwarteter Längenunterschied!");
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(aktionen);
		result = prime * result + ((in == null) ? 0 : in.hashCode());
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
		if (!Arrays.equals(aktionen, other.aktionen)) {
			return false;
		}
		if (in == null) {
			if (other.in != null) {
				return false;
			}
		} else if (!in.equals(other.in)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Case [");
		if (in != null)
			builder.append("in=").append(in).append(", ");
		if (aktionen != null)
			builder.append("aktionen=").append(Arrays.toString(aktionen));
		builder.append("]");
		return builder.toString();
	}
}