package lambda;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.SuiteClasses;
@RunWith(Suite.class)
@SuiteClasses(value={If.IfTestUnit.class,Variable.VariableTestUnit.class})
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
}