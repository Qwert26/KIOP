package lambda.tests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
@RunWith(Suite.class)
@SuiteClasses({TestAbstraktion.class,TestAnwendung.class,TestVariable.class,TestAusdruckskonstanten.class,TestVerzweigung.class})
public class TestLamba {
	public TestLamba() {
		super();
	}
}