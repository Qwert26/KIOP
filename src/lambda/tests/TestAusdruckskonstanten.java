package lambda.tests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
@RunWith(Suite.class)
@SuiteClasses({TestWahrheitsVerknüpfung.class,TestZahlenVerknüpfung.class})
public class TestAusdruckskonstanten {
	public TestAusdruckskonstanten() {
		super();
	}
}