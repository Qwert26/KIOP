package lambda.typen;
import java.util.Arrays;
public class SummenTyp extends Typ {
	private Typ[] typen;
	public SummenTyp(Typ...typen) {
		this.typen=typen;
	}
	public synchronized final Typ[] getTypen() {
		return typen;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(typen);
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
		if (!(obj instanceof SummenTyp)) {
			return false;
		}
		SummenTyp other = (SummenTyp) obj;
		if (!Arrays.equals(typen, other.typen)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SummenTyp [");
		if (typen != null)
			builder.append("typen=").append(Arrays.toString(typen));
		builder.append("]");
		return builder.toString();
	}
	@Override
	public boolean istUntertypVon(Typ t) {
		if(t instanceof SummenTyp) {
			SummenTyp other=(SummenTyp)t;
			if(other.typen.length==typen.length) {
				for(int i=0;i<typen.length;i++) {
					if(!typen[i].istUntertypVon(other.typen[i])) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}