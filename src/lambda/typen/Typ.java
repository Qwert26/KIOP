package lambda.typen;
public abstract class Typ {
	public Typ() {}
	@Override
	public abstract int hashCode();
	@Override
	public abstract boolean equals(Object obj);
	@Override
	public abstract String toString();
}