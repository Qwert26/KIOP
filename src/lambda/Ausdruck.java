package lambda;
import java.util.*;
public abstract class Ausdruck {
	public Ausdruck() {
		super();
	}
	public abstract Set<String> freieVariablen();
}