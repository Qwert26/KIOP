package lambda;
public class RecordType extends Type {
	public RecordType() {}
	@Override
	public boolean equals(Object o) {
		return o instanceof RecordType;
	}
}