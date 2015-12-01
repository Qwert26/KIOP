package lambda;
import java.util.Arrays;
public class SumType extends Type {
	public Type[] types;
	public SumType(Type...types) {
		this.types=types;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(types);
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
		if (!Arrays.equals(types, other.types)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SumType [");
		if (types != null)
			builder.append("types=").append(Arrays.toString(types));
		builder.append("]");
		return builder.toString();
	}
}