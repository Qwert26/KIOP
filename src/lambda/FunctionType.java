package lambda;
public class FunctionType extends Type {
	public final Type left;
	public final Type right;
	public FunctionType(Type left, Type right) {
		super();
		this.left = left;
		this.right = right;
	}
}