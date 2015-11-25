package lambda;
public class Number extends Type {
	public boolean equals(Object o) {
		return o instanceof Number;
	}
}