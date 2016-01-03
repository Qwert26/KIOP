package lambda.tests;
import static lambda.tests.TestHelfer.*;
import org.junit.*;
import lambda.*;
import lambda.typen.*;
import static lambda.typen.Ausdruckskonstanten.*;
import static org.junit.Assert.*;
public class TestCase {
	public TestCase() {
		super();
	}
	@Test
	public void testCase0() {
		Abstraktion mySum=ABS("x",new SummenTyp(new Zahl(),new FunktionsTyp(new Zahl(),new Zahl())),
				CASE(VAR("x"),APP(VAR(PLUS),VAR("x"),VAR(1)),APP(VAR("x"),VAR(0))));
		assertEquals(new Zahl(),mySum.bestimmeTyp(mySum.extrahiereUmgebung()));
	}
}