package lambda;
public class Boolean extends Type {
	@Override
	public boolean equals(Object o) {
		return o instanceof Boolean;
	}
}