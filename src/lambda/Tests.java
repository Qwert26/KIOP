package lambda;
import org.junit.*;
import static org.junit.Assert.*;
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
	@Test
	public void testReduce() {
		// (λx.x) y
		Expression ex = APP(ABS("x", VAR("x")), VAR("y"));
		Expression ex2 = ex.reduce();
		System.out.println(ex2);
		assertTrue(ex2 instanceof Variable);
		assertEquals("y", ((Variable) ex2).varName);
	}
}