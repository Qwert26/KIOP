package lambda.typen;
public class Wahrheitswert extends Typ {
	public Wahrheitswert() {
		super();
	}
	@Override
	public int hashCode() {
		return 0;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null) {
			return false;
		} else {
			return obj instanceof Wahrheitswert;
		}
	}
	@Override
	public String toString() {
		return "Wahrheitswert";
	}
	@Override
	public boolean istUntertypVon(Typ t) {
		return equals(t);
	}
}