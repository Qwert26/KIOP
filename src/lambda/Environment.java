package lambda;
import java.util.HashMap;
public final class Environment {
	public static final HashMap<String, Type> env = new HashMap<String, Type>();
	public static final Environment INSTANCE=new Environment();
	private Environment() {}
	static {
		env.put("true", new Boolean());
		env.put("false", new Boolean());
		env.put("0", new Number());
		env.put("1", new Number());
		env.put("2", new Number());
		env.put("not", new FunctionType(new Boolean(),new Boolean()));
		env.put("largerThan0", new FunctionType(new Number(),new Boolean()));
	}
}