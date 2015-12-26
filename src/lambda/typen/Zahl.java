package lambda.typen;
public class Zahl extends Typ {
	public Zahl() {
		super();
	}
	@Override
	public int hashCode() {
		return 1;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null) {
			return false;
		} else {
			return obj instanceof Zahl;
		}
	}
	@Override
	public String toString() {
		return "Zahl";
	}
}