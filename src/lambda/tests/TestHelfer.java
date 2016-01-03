package lambda.tests;
import lambda.*;
import lambda.typen.*;
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
	public static Variable VAR(Ausdruckskonstanten konstante) {
		return new Variable(konstante);
	}
	public static Variable VAR(boolean wert) {
		if(wert) {
			return new Variable(Ausdruckskonstanten.TRUE);
		} else {
			return new Variable(Ausdruckskonstanten.FALSE);
		}
	}
	public static Abstraktion ABS(String name,Ausdruck term) {
		return new Abstraktion(name,term);
	}
	public static Abstraktion ABS(String name,Typ typ,Ausdruck term) {
		return new Abstraktion(name,typ,term);
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
	public static Verzweigung IF(Ausdruck wenn,Ausdruck dann,Ausdruck sonst) {
		return new Verzweigung(wenn,dann,sonst);
	}
}