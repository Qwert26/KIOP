package lambda;
public class FunctionType extends Type {
	public final Type left;
	public final Type right;
	public FunctionType(Type left, Type right) {
		super();
		this.left = left;
		this.right = right;
	}
	
	public String toString() {
//		System.out.println(left);
//		System.out.println(right);
		return left.toString() + " -> " + right.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FunctionType other = (FunctionType) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}
	
	public boolean isSubtypeOf(Type T) {
		if (!super.isSubtypeOf(T)) return false;
		
		FunctionType superTypeFuncType = (FunctionType) T;
		
		return this.right.isSubtypeOf(superTypeFuncType.right) &&
				superTypeFuncType.left.isSubtypeOf(this.left);
		
	}
}
