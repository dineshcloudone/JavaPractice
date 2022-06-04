package overriding;

import package1.*;
import package2.*;

//http://www.geeksforgeeks.org/can-we-overload-or-override-static-methods-in-java/
//https://stackoverflow.com/questions/10291949/are-static-methods-inherited-in-java/10292034#10292034

/*  1. We can call a static method from non-static method directly without object reference if both are in same class
 *  2. We can call a non-static method from a static method using object reference of non-static method class
 *  3. We can declare static methods with same signature in subclass, but it is not considered overriding as there won’t be any run-time polymorphism. Hence the answer is ‘No’.
 *	   If a derived class defines a static method with same signature as a static method in base class, the method in the derived class hides the method in the base class. This is also called method hiding.
 *  4. We can overload the static method by changing the number of parameters in a same class
 *  5. 1) For class (or static) methods, the method according to the type of reference is called, not according to the abject being referred, which means method call is decided at compile time.

	   2) For instance (or non-static) methods, the method is called according to the type of object being referred, not according to the type of reference, which means method calls is decided at run time.

       3) An instance method cannot override a static method, and a static method cannot hide an instance method. For example, the following program has two compiler errors.
 *      Java program to show that if static method is redefined by a derived class, then it is not overriding. 
 *  6. Interfaces are not allowed to have static methods till Java7 and from Java8 interfaces can have static methods 
 *  7. When we have to use static method
 *  	a. when you need a common operation through out the class
 *  	b. when you don't need to create a instance for the class
 *  
 *  */

//Superclass
class Base {
  
 // Static method in base class which will be hidden in subclass 
 public static void display() {
     System.out.println("Static or class method from Base");
 }
  
  // Non-static method which will be overridden in derived class 
  public void print1()  {
      System.out.println("Non-static or Instance method from Base");
 }
}

//Subclass
class Derived extends Base {
  
 // This method hides display() in Base 
 public static void display() {
	 
      System.out.println("Static or class method from Derived");
 }
  
 // This method overrides print() in Base 
 public void print1() {
      System.out.println("Non-static or Instance method from Derived");
}
}

//Driver class
public class OverrideStaticMethod {
	
 public static void main(String args[ ])  {
    Base obj1 = new Derived();
     
    // As per overriding rules this should call to class Derive's static 
    // overridden method. Since static method can not be overridden, it 
    // calls Base's display() 
    obj1.display();  
     
    // Here overriding works and Derive's print() is called 
    obj1.print1();
    
 }
}