package lambda.typen;
public class FunktionsTyp extends Typ {
	private Typ input;
	private Typ output;
	public FunktionsTyp(Typ i,Typ o) {
		super();
		input=i;
		output=o;
	}
	public synchronized final Typ getInput() {
		return input;
	}
	public synchronized final Typ getOutput() {
		return output;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((input == null) ? 0 : input.hashCode());
		result = prime * result + ((output == null) ? 0 : output.hashCode());
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
		if (!(obj instanceof FunktionsTyp)) {
			return false;
		}
		FunktionsTyp other = (FunktionsTyp) obj;
		if (input == null) {
			if (other.input != null) {
				return false;
			}
		} else if (!input.equals(other.input)) {
			return false;
		}
		if (output == null) {
			if (other.output != null) {
				return false;
			}
		} else if (!output.equals(other.output)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FunktionsTyp [");
		if (input != null)
			builder.append("input=").append(input).append(", ");
		if (output != null)
			builder.append("output=").append(output);
		builder.append("]");
		return builder.toString();
	}
}