package lambda;
public class SumType extends Type {
	public Type left;
	public Type right;
	public SumType(Type left, Type right) {
		super();
		this.left = left;
		this.right = right;
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
		SumType other = (SumType) obj;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SumType [");
		if (left != null)
			builder.append("typeLeft=").append(left).append(", ");
		if (right != null)
			builder.append("typeRight=").append(right);
		builder.append("]");
		return builder.toString();
	}
}