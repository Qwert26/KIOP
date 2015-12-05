package lambda;
public class SumType extends Type {
<<<<<<< HEAD
	public Type left;
	public Type right;
	public SumType(Type left, Type right) {
		super();
		this.left = left;
		this.right = right;
	}
	@Override
	public String toString() {
		return left.toString() + " + " + right.toString();
=======
	public Type typeLeft,typeRight;
	public SumType(Type left,Type right) {
		typeLeft=left;
		typeRight=right;
>>>>>>> origin/work
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
<<<<<<< HEAD
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
=======
		result = prime * result + ((typeLeft == null) ? 0 : typeLeft.hashCode());
		result = prime * result + ((typeRight == null) ? 0 : typeRight.hashCode());
>>>>>>> origin/work
		return result;
	}
	@Override
	public boolean equals(Object obj) {
<<<<<<< HEAD
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
=======
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SumType)) {
			return false;
		}
		SumType other = (SumType) obj;
		if (typeLeft == null) {
			if (other.typeLeft != null) {
				return false;
			}
		} else if (!typeLeft.equals(other.typeLeft)) {
			return false;
		}
		if (typeRight == null) {
			if (other.typeRight != null) {
				return false;
			}
		} else if (!typeRight.equals(other.typeRight)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SumType [");
		if (typeLeft != null)
			builder.append("typeLeft=").append(typeLeft).append(", ");
		if (typeRight != null)
			builder.append("typeRight=").append(typeRight);
		builder.append("]");
		return builder.toString();
	}
>>>>>>> origin/work
}