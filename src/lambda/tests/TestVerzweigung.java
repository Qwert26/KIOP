package lambda.tests;
import org.junit.*;
import lambda.*;
import static org.junit.Assert.*;
import static lambda.tests.TestHelfer.*;
public class TestVerzweigung {
	public TestVerzweigung() {
		super();
	}
	@Test
	public void testWahr() {
		Variable result=VAR("j");
		Ausdruck redex=IF(VAR(true),result,VAR("n"));
		while(redex.istReduzierbar()) {
			redex=redex.reduziere();
		}
		assertEquals(result,redex);
	}
	@Test
	public void testFalsch() {
		Variable result=VAR("n");
		Ausdruck redex=IF(VAR(false),VAR("j"),result);
		while(redex.istReduzierbar()) {
			redex=redex.reduziere();
		}
		assertEquals(result,redex);
	}
}