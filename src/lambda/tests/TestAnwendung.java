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
}