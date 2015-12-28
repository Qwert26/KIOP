package lambda.tests;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import lambda.*;
import lambda.typen.Ausdruckskonstanten;
import static lambda.typen.Ausdruckskonstanten.*;
import static lambda.tests.TestHelfer.*;
@RunWith(Parameterized.class)
public class TestWahrheitsVerknüpfung {
	@Parameters(name="{index}:{0} {1} {2}={3}")
	public static Object[][]data() {
		return new Object[][]{	{FALSE,AND,FALSE,FALSE},{FALSE,AND,TRUE,FALSE},{TRUE,AND,FALSE,FALSE},{TRUE,AND,TRUE,TRUE},
								{FALSE,OR,FALSE,FALSE},{FALSE,OR,TRUE,TRUE},{TRUE,OR,FALSE,TRUE},{TRUE,OR,TRUE,TRUE}};
	}
	@Parameter(0)
	public Ausdruckskonstanten links;
	@Parameter(1)
	public Ausdruckskonstanten operation;
	@Parameter(2)
	public Ausdruckskonstanten rechts;
	@Parameter(3)
	public Ausdruckskonstanten ergebnis;
	public TestWahrheitsVerknüpfung() {}
	@Test
	public void testUndFunktion() {
		Ausdruck funktion=APP(VAR(operation),VAR(links),VAR(rechts));
		while(funktion.istReduzierbar()) {
			funktion=funktion.reduziere();
		}
		assertEquals(VAR(ergebnis),funktion);
	}
}