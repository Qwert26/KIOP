package lambda;
public class Number extends Type {
	@Override
	public boolean equals(Object o) {
		return o instanceof Number;
	}
	@Override
	public String toString() {
		return "Num";
	}
}