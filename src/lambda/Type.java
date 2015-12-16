package lambda;
public class Type {
	public boolean isSubtypeOf(Type superType) {
		return this.getClass().equals(superType.getClass());
	}
}