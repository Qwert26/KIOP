package lambda;
import org.junit.*;
import static org.junit.Assert.*;
public class Tests {
	public Application APP(Expression left, Expression right) {
		return new Application(left, right);
	}
	public Abstraction ABS(String paramName, Expression body) {
		return new Abstraction(paramName, body);
	}
	public Variable VAR(String varName) {
		return new Variable(varName);
	}
	@Test
	public void testApplicationReduce() {
		// (λx.x) y
		Expression ex = APP(ABS("x", VAR("x")), VAR("y"));
		Expression ex2 = ex.reduce();
		System.out.println(ex2);
		assertTrue(ex2 instanceof Variable);
		assertEquals("y", ((Variable) ex2).varName);
	}
}