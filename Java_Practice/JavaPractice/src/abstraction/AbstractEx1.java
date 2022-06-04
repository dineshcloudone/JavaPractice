package abstraction;

/*
 * Default methods are defined with the default modifier, and static methods with the static keyword. 
 * All abstract, default and static methods in an interface are implicitly public , so you can omit the public modifier. ... 
 * All constant values defined in an interface are implicitly public , static , and final .
 * 
 */

interface A {
	
	void a();

	void b();

	void c();

	void d();
	
	static void interface_static_method() {
		System.out.println("this is static method");
	}
	
	default void default_overrideEx() {
		System.out.println("override ex from abstract class B");
	}

// from Java 9 we can have private methods	
//	private void interface_private_method() {
//		System.out.println("private method from interface");
//	}
	
}

abstract class B implements A {

	B() {
		System.out.println("This is constructor of class B");
	}

	public final void c() {
		System.out.println("I am c");
	}

	static void static_method() {
		System.out.println("this is static method");
	}

	final void final_method() {
		System.out.println("this is final method");
	}
	
	void overrideEx() {
		System.out.println("override ex from abstract class B");
	}

}

class M extends B {

	public void a() {
		System.out.println("I am a");
	}

	public void b() {
		System.out.println("I am b");
	}

	public void d() {
		System.out.println("I am d");
	}
	
	void overrideEx() {
		System.out.println("override ex from M class");
	}
}

class AbstractEx1 {
	
	public static void main(String args[]) {

		A a = new M();
		a.a();
		a.b();
		a.c();
		a.d();
		M m=new M();
		
		m.overrideEx();
		
	}
	
}