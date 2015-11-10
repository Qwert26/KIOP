package lambda;
import java.util.Set;
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Stefan Hanenberg (stefan.hanenberg@gmail.com)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this 
 * software and associated documentation files (the "Software"), to deal in the Software 
 * without restriction, including without limitation the rights to use, copy, modify, 
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to 
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all copies or 
 * substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE 
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **/
public class Application extends Expression {
	Expression left;
	Expression right;
	public Application(Expression left, Expression right) {
		super();
		this.left = left;
		this.right = right;
	}
	@Override
	public Expression reduce() {
		if (left instanceof Abstraction) {
			return ((Abstraction) left).reduceWith(right);
		}
		return this;
	}
	@Override
	public Expression substituteWith(String aName, Expression exp) {
		left = left.substituteWith(aName, exp);
		right = right.substituteWith(aName, exp);
		return this;
	}
	@Override
	public Set<String> FI() {
		Set<String> s = left.FI();
		s.addAll(right.FI());
		return s;
	}
}