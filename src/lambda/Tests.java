package lambda;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.SuiteClasses;
@RunWith(Suite.class)
@SuiteClasses(value={If.IfTestUnit.class,Variable.VariableTestUnit.class,Application.ApplicationTestUnit.class})
public class Tests {}