package lambda;
public class SumType extends Type {
	public Type typeLeft,typeRight;
	public SumType(Type left,Type right) {
		typeLeft=left;
		typeRight=right;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((typeLeft == null) ? 0 : typeLeft.hashCode());
		result = prime * result + ((typeRight == null) ? 0 : typeRight.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
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
}