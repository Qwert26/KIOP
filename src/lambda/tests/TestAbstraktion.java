package lambda.tests;
import static lambda.tests.TestHelfer.*;
import static org.junit.Assert.*;
import org.junit.*;
import lambda.*;
public class TestAbstraktion {
	@Test
	public void testFreieVariablenLeer() {
		final String name="x";
		Abstraktion a=abs(name,var(name));
		assertTrue("Menge der freien Variablen ist nicht leer!",a.freieVariablen().isEmpty());
	}
	@Test
	public void testFreieVariablenVorhanden() {
		final String name="x";
		Abstraktion a=abs("y",var(name));
		assertTrue("x ist keine freie Variable, sollte aber eine sein!",a.freieVariablen().contains(name));
	}
}