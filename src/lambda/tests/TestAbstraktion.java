package lambda.tests;
import static lambda.tests.TestHelfer.*;
import static org.junit.Assert.*;
import org.junit.*;
import lambda.*;
public class TestAbstraktion {
	@Test
	public void testFreieVariablenLeer() {
		final String name="x";
		Abstraktion a=ABS(name,VAR(name));
		assertTrue("Menge der freien Variablen ist nicht leer!",a.freieVariablen().isEmpty());
	}
	@Test
	public void testFreieVariablenVorhanden() {
		final String name="x";
		Abstraktion a=ABS("y",VAR(name));
		assertTrue("x ist keine freie Variable, sollte aber eine sein!",a.freieVariablen().contains(name));
	}
	@Test
	public void testSubstitutionSuccess() {
		final String name="x";
		Variable original=VAR(name),ersatz=VAR("y");
		Abstraktion abs=ABS("z",original);
		abs.substitution(name,ersatz);
		assertEquals("Der Term wurde nicht ersetzt!",ersatz,abs.getTerm());
	}
	@Test
	public void testSubstitutionFailure() {
		final String name="x";
		Variable original=VAR(name),ersatz=VAR("y");
		Abstraktion abs=ABS("z",original);
		abs.substitution("z",ersatz);
		assertEquals("Der Term wurde ersetzt!",original,abs.getTerm());
	}
	@Test
	public void testUmbennen() {
		Abstraktion lxyz=ABS("x",ABS("y",ABS("z",APP(VAR("x"),VAR("y"),VAR("x")))));
		Abstraktion result=ABS("h",ABS("y",ABS("z",APP(VAR("h"),VAR("y"),VAR("h")))));
		assertTrue("Es wurde nichts umbenannt!",lxyz.umbenennen("x","h"));
		assertEquals("Das Umbennen lieferte nicht das korrekte Ergebnis!",result,lxyz);
	}
}