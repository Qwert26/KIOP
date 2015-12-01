package lambda;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
@RunWith(Suite.class)
@SuiteClasses(value={	If.IfTestUnit.class,
						Variable.VariableTestUnit.class,
						Application.ApplicationTestUnit.class,
						Abstraction.AbstractionTestUnit.class,
						Case.CaseTestUnit.class})
public class Tests {}