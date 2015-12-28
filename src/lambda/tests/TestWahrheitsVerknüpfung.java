package lambda.tests;
import static lambda.tests.TestHelfer.APP;
import static lambda.tests.TestHelfer.VAR;
import static lambda.typen.Ausdruckskonstanten.AND;
import static lambda.typen.Ausdruckskonstanten.FALSE;
import static lambda.typen.Ausdruckskonstanten.OR;
import static lambda.typen.Ausdruckskonstanten.TRUE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import lambda.Ausdruck;
import lambda.typen.Ausdruckskonstanten;
import lambda.typen.Wahrheitswert;
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
	public void testVerknüpfung() {
		Ausdruck funktion=APP(VAR(operation),VAR(links),VAR(rechts));
		assumeTrue(funktion.bestimmeTyp(funktion.extrahiereUmgebung()).equals(new Wahrheitswert()));
		while(funktion.istReduzierbar()) {
			funktion=funktion.reduziere();
		}
		assertEquals(VAR(ergebnis),funktion);
	}
}