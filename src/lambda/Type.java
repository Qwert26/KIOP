package lambda;
public class Type {
	public boolean isSubtypeOf(Type other) {
		return getClass().equals(other.getClass());
	}
}