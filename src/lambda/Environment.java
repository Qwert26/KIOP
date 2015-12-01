package lambda;
import java.util.HashMap;
import java.util.Map;
public final class Environment implements Cloneable {
	public final HashMap<String, Type> env = new HashMap<String, Type>();
	public Environment() {
		env.put("true", new Boolean());
		env.put("false", new Boolean());
		env.put("0", new Number());
		env.put("1", new Number());
		env.put("2", new Number());
		env.put("not", new FunctionType(new Boolean(),new Boolean()));
		env.put("largerThan0", new FunctionType(new Number(),new Boolean()));
	}
	@SuppressWarnings("unchecked")
	public Environment clone() {
		try {
			Environment ret=(Environment)super.clone();
			ret.env.clear();
			ret.env.putAll((Map<? extends String,? extends Type>)env.clone());
			return ret;
		} catch (CloneNotSupportedException cnse) {
			cnse.printStackTrace();
			return null;
		}
	}
}