package lambda.tests;
import static lambda.tests.TestHelfer.APP;
import static lambda.tests.TestHelfer.VAR;
import static lambda.typen.Ausdruckskonstanten.MULT;
import static lambda.typen.Ausdruckskonstanten.PLUS;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import lambda.Ausdruck;
import lambda.Variable;
import lambda.typen.Ausdruckskonstanten;
@RunWith(Parameterized.class)
public class TestZahlenVerknüpfung {
	@Parameter(0)
	public long a;
	@Parameter(1)
	public Ausdruckskonstanten operation;
	@Parameter(2)
	public long b;
	@Parameter(3)
	public long result;
	public TestZahlenVerknüpfung() {
		super();
	}
	@Parameters(name="{index}:{0} {1} {2}={3}")
	public static Object[][]data() {
		return new Object[][]{	{1,PLUS,1,2},{1,PLUS,2,3},{3,PLUS,4,7},
											 {2,PLUS,1,3},{4,PLUS,3,7},
								{1,MULT,1,1},{1,MULT,2,2},{3,MULT,4,12},
											 {2,MULT,1,2},{4,MULT,3,12}};
	}
	@Test
	public void testVerknüpfung() {
		Variable vResult=VAR(result);
		Ausdruck redex=APP(VAR(operation),VAR(a),VAR(b));
		while(redex.istReduzierbar()) {
			redex=redex.reduziere();
		}
		assertEquals(vResult,redex);
	}
}