package lambda;
import java.util.Set;
import lambda.typen.*;
public class In extends Ausdruck {
	private SummenTyp kapsel;
	private int position;
	private Ausdruck ausdruck;
	public In(SummenTyp kapsel,int position,Ausdruck ausdruck) {
		this.kapsel=kapsel;
		this.position=position;
		this.ausdruck=ausdruck;
	}
	public synchronized final SummenTyp getKapsel() {
		return kapsel;
	}
	public synchronized final int getPosition() {
		return position;
	}
	public synchronized final Ausdruck getAusdruck() {
		return ausdruck;
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
		return ausdruck.istReduzierbar();
	}
	@Override
	public In reduziere() {
		if(ausdruck.istReduzierbar()) {
			ausdruck=ausdruck.reduziere();
		}
		return this;
	}
	@Override
	public boolean umbenennen(String von, String zu) {
		return ausdruck.umbenennen(von,zu);
	}
	@Override
	public Umgebung extrahiereUmgebung() {
		return ausdruck.extrahiereUmgebung();
	}
	@Override
	public Typ bestimmeTyp(Umgebung e) {
		if(ausdruck.bestimmeTyp(e).equals(kapsel.getTypen()[position])) {
			return kapsel;
		} else {
			throw new RuntimeException("Der Typ des Ausdrucks und der "+position+".Typ des Summentyps stimmen nicht überein!");
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ausdruck == null) ? 0 : ausdruck.hashCode());
		result = prime * result + ((kapsel == null) ? 0 : kapsel.hashCode());
		result = prime * result + position;
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
		if (ausdruck == null) {
			if (other.ausdruck != null) {
				return false;
			}
		} else if (!ausdruck.equals(other.ausdruck)) {
			return false;
		}
		if (kapsel == null) {
			if (other.kapsel != null) {
				return false;
			}
		} else if (!kapsel.equals(other.kapsel)) {
			return false;
		}
		if (position != other.position) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("In [");
		if (kapsel != null)
			builder.append("kapsel=").append(kapsel).append(", ");
		builder.append("position=").append(position).append(", ");
		if (ausdruck != null)
			builder.append("ausdruck=").append(ausdruck);
		builder.append("]");
		return builder.toString();
	}
}