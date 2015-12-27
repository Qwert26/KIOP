package lambda.tests;
import static lambda.tests.TestHelfer.*;
import static org.junit.Assert.*;
import org.junit.*;
import lambda.*;
public class TestAnwendung {
	public TestAnwendung() {}
	/**
	 * Reduktionsbeispiel 1 von Folie 200.
	 */
	@Test
	public void testReduktion1() {
		Abstraktion lx=ABS("x",VAR("x"));
		Abstraktion ly=ABS("y",VAR("y"));
		Variable vz=VAR("z");
		Ausdruck redex=APP(lx,APP(ly,vz));
		while(redex.istReduzierbar()) {
			redex=redex.reduziere();
		}
		assertEquals(vz,redex);
	}
	/**
	 * Reduktionsbeispiel 2 von Folie 201.
	 */
	@Test
	public void testReduktion2() {
		Abstraktion lx=ABS("x",APP(VAR("x"),VAR("y")));
		Abstraktion lz=ABS("z",APP(VAR("z"),VAR("z")));
		Ausdruck redex=APP(lx,lz);
		Anwendung result=APP(VAR("y"),VAR("y"));
		while(redex.istReduzierbar()) {
			redex=redex.reduziere();
		}
		assertEquals(result,redex);
	}
	/**
	 * Reduktionsbeispiel 3 von Folie 201.
	 */
	@Test
	public void testReduktion3() {
		Abstraktion lxyz=ABS("x",ABS("y",ABS("z",APP(APP(VAR("x"),VAR("y")),VAR("z")))));
		Abstraktion ltf=ABS("t",ABS("f",VAR("t")));
		Ausdruck redex=APP(APP(APP(lxyz,ltf),VAR("v")),VAR("w"));
		System.out.println(redex);
		while(redex.istReduzierbar()) {
			redex=redex.reduziere();
			System.out.println(redex);
		}
		assertEquals(VAR("v"),redex);
	}
}