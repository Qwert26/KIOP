package lambda.tests;
import lambda.*;
final class TestHelfer {
	private TestHelfer() {
		super();
	}
	public static Variable VAR(String name) {
		return new Variable(name);
	}
	public static Variable VAR(long zahl) {
		return new Variable(zahl);
	}
	public static Variable VAR(boolean wert) {
		return new Variable(wert);
	}
	public static Abstraktion ABS(String name,Ausdruck term) {
		return new Abstraktion(name,term);
	}
	public static Anwendung APP(Ausdruck links,Ausdruck...rechteSeiten) {
		if(rechteSeiten.length==0) {
			throw new IllegalArgumentException("Keinen rechten Ausdruck angegeben!");
		} else if(rechteSeiten.length==1) {
			return new Anwendung(links,rechteSeiten[0]);
		} else {
			Ausdruck[]next=new Ausdruck[rechteSeiten.length-1];
			System.arraycopy(rechteSeiten,0,next,0,next.length);
			return new Anwendung(APP(links,next),rechteSeiten[next.length]);
		}
	}
}