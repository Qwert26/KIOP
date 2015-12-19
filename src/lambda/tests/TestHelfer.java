package lambda.tests;
import lambda.*;
final class TestHelfer {
	private TestHelfer() {
		super();
	}
	public static Variable var(String name) {
		return new Variable(name);
	}
	public static Abstraktion abs(String name,Ausdruck term) {
		return new Abstraktion(name,term);
	}
	public static Anwendung app(Ausdruck links,Ausdruck rechts) {
		return new Anwendung(links,rechts);
	}
}