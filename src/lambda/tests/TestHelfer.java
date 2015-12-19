package lambda.tests;
import lambda.*;
final class TestHelfer {
	private TestHelfer() {
		super();
	}
	public static Variable VAR(String name) {
		return new Variable(name);
	}
	public static Abstraktion ABS(String name,Ausdruck term) {
		return new Abstraktion(name,term);
	}
	public static Anwendung APP(Ausdruck links,Ausdruck rechts) {
		return new Anwendung(links,rechts);
	}
}