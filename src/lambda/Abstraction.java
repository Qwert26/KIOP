package lambda;
import java.util.HashSet;
import java.util.Set;
public class Abstraction extends Expression {
	public String paramName;
	public Type paramType;
	public Expression body;
	
	public Abstraction(String paramName, Type paramType, Expression body) {
		super();
		this.paramName = paramName;
		this.paramType = paramType;
		this.body = body;
	}

	@Override
	public Expression reduce() {
		return null;
	}
	
	public Expression reduceWith(Expression appliedParameter) {
		return body.substituteWith(paramName, appliedParameter);
	}	
	
	public Set FI() {
		Set ret = new HashSet();
		body.FI().remove(paramName);
		return ret;
	}

	@Override
	public Expression substituteWith(String aName, Expression exp) {
		if (!paramName.equals(aName) &&  !exp.FI().contains(paramName))
			body = body.substituteWith(aName, exp);
		return this;
	}

	@Override
	public boolean isReducible() {
		return false;
	}

	/**
	 *               e1 = (E, x:T) |-  body: tbody
	 * T-Abs ========================================
	 * 		        E |- λx:T.body : T -> tbody

	 *
	 *               body.getType(E, x:T) = tbody
	 * T-Abs ========================================
	 * 		        (λx:T.body).getType(E) = new FunctionType(T,tbody)
	 *
	 */
	@Override
	public Type getType(Environment e) {
		// 1. Erzeuge eine Kopie von e
		// Füge der Kopie die Info x:T hinzu
		// ab jetzt mach alles "unterhalb" mit Kopie
		
		Environment e1 = e.clone();
		e1.env.put(this.paramName, this.paramType);
		
		Type tbody = body.getType(e1);
		
//		System.out.println("paramType: " + this.paramType);
//		System.out.println("tBody: " + tbody);
		
		return new FunctionType(this.paramType, tbody);
	}
}
