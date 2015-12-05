package lambda;
import java.util.HashMap;
public class Environment {
	public HashMap<String, Type> env = new HashMap<String, Type>();
	public static Environment createEnvironment() {
		Environment e = new Environment();
		e.env.put("true", new Boolean());
		e.env.put("false", new Boolean());
		e.env.put("0", new Number());
		e.env.put("1", new Number());
		e.env.put("2", new Number());
		e.env.put("not", new FunctionType(new  Boolean(),new  Boolean()));
		e.env.put("largerThan0", new FunctionType(new  Number(),new  Boolean()));
		return e;
	}
	@Override
	public Environment clone() {
		Environment e = new Environment();
		@SuppressWarnings("unchecked")
		HashMap<String, Type> env2 = (HashMap<String, Type>) env.clone();
		e.env = env2;
		return e;
	}
}