//Below link is having examples for chaging return type and number of parameters
//https://www.dineshonjava.com/compile-time-polymorphism-in-java/

//Compile time polymorphism or static meLink : https://www.dineshonjava.com/compile-time-polymorphism-in-java/

//Compile time polymorphism : It is also known as Static binding, Early binding and overloading as well.
//If there is any private, final or static method in a class, there is static binding.

//Runtime polymorphism : It is also known as Dynamic binding, Late binding and overriding as well.

/*
 * Different ways to overload the method:
	There are two ways to overload the method in java

	1.By changing number of arguments
	2.By changing the data type
*/

public class OverloadEx {

	// static float f = 10.00f;
	

	/*
	 * private int add(int a, int b) { return a + b; }
	 * 
	 * private float add(int a, int b, int c) { return (a + b + c); }
	 */
	
	private void add(int...c) {
		System.out.println("var args");
	}
	
	private void add(int a,int b) {
		System.out.println("a b");
	}

	/*
	 * private void add(int a, float b) {
	 * 
	 * System.out.println("Variables got here : "+ a +" "+b); }
	 * 
	 * private void add(float a,int b) {
	 * System.out.println("variables got here are : "+a+" "+b); }
	 */

	public static void main(String[] args) {

		OverloadEx ol = new OverloadEx();
		//System.out.println(ol.add(4, 3));
		//System.out.println(ol.add(4, 3, 6));
		
		ol.add(4, 3);
		//ol.add(4, 3, 6);

		// System.out.println("Value of float value f: "+f);
	}
}

/*
 * public class OverloadEx{ static int add(int a, int b) {return a+b;} static
 * int add(int a,int b,int c) {return a+b+c;}
 * 
 * }
 */