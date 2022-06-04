
/* Overriding Link : https://www.dineshonjava.com/overriding-in-java/ 
 * 
 *  Note : Constructors cannot be overridden. you can define constructor as a method by adding return type in subclass
 * 
 * 
 * Notes : return type should be the same or a subtype of the return type declared in the original overridden method in the super class.
 * 
 * Note : The access level cannot be more restrictive than the overridden method’s access level. 
 * 		For example: if the super class method is declared public then the overridding method in the sub class cannot be either private or public. 
 * 		However the access level can be less restrictive than the overridden method’s access level.
 * 
 * 
	Rules for method overriding:
	
	1.The argument list should be exactly the same as that of the overridden method.
	2.The return type should be the same or a subtype of the return type declared in the original overridden method in the super class.
	3.The access level cannot be more restrictive than the overridden method’s access level. For example: if the super class method is declared public then the overridding method in the sub class cannot be either private or public. However the access level can be less restrictive than the overridden method’s access level.
	4.Instance methods can be overridden only if they are inherited by the subclass.
	5.A method declared final cannot be overridden.
	6.A method declared static cannot be overridden but can be re-declared.
	7.If a method cannot be inherited then it cannot be overridden.
	8.You will get a compile-time error if you attempt to change an instance method in the superclass to a class method in the subclass, and vice versa.
	
*/

//Runtime Polymorphism or Dynamic Method Dispatch Best Example : https://www.geeksforgeeks.org/dynamic-method-dispatch-runtime-polymorphism-java/ 

class Base {
		
	public static int interest(int a, int b) {
		System.out.println("interest rate from base clase ");
		return (a + b);
	}
}

class Derived extends Base {
			
		public static int interest(int c, int d) {
		System.out.println("interest rate from derived clase ");
		return (c + d);
	}
}

public class Overriding extends Derived {

	int a = 10;
	long b = 10;

	public static void main(String[] args) {

		Base b = new Derived();
		System.out.println(b.interest(10, 11));

	}
}

/*
 * class Animal { public static void testClassMethod() {
 * System.out.println("The class" + " method in Animal."); } public void
 * testInstanceMethod() { System.out.println("The instance " +
 * " method in Animal."); } }
 * 
 * 
 * public class Overriding extends Animal { public static void testClassMethod()
 * { System.out.println("The class method" + " in Cat."); } public void
 * testInstanceMethod() { System.out.println("The instance method" +
 * " in Cat."); }
 * 
 * public static void main(String[] args) { Overriding myCat = new Overriding();
 * //myCat.testClassMethod(); Animal myAnimal = myCat; Animal.testClassMethod();
 * myAnimal.testInstanceMethod(); } }
 */
