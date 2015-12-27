package lambda.tests;
import static lambda.tests.TestHelfer.*;
import static org.junit.Assert.*;
import org.junit.*;
import lambda.*;
import lambda.typen.*;
public class TestVariable {
	public TestVariable() {
		super();
	}
	@Test
	public void testTypZahl() {
		Variable v=VAR(1);
		assertEquals(new Zahl(),v.bestimmeTyp(v.extrahiereUmgebung()));
	}
	@Test
	public void testTypWahrheitswertTrue() {
		Variable v=VAR(Ausdruckskonstanten.TRUE);
		assertEquals(new Wahrheitswert(),v.bestimmeTyp(v.extrahiereUmgebung()));
	}
	@Test
	public void testTypWahrheitswertFalse() {
		Variable v=VAR(Ausdruckskonstanten.FALSE);
		assertEquals(new Wahrheitswert(),v.bestimmeTyp(v.extrahiereUmgebung()));
	}
	@Test
	public void testTyplos() {
		Variable v=VAR("x");
		assertNull(v.bestimmeTyp(v.extrahiereUmgebung()));
	}
}