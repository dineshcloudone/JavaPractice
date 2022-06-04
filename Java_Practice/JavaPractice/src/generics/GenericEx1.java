package generics;

/*
 * Generics : Java generics were introduced in Java5 to deal with "type-safety" of the objects.
 * 
 * Advantages : 
 * 	1. Type Safety - We can hold only single type of objects in generics. It doesn't allow to store other objects.
 *  2. Type Casting - There is no need to type cast the object.
 *  3. Compile-Time checking - Type Safety will be checked at compile time so problem will not occur at runtime. Good Programming strategy always says that it is
 *  always better to handle problem at compile time rather than at runtime.
 *   
 */


import java.util.*;

public class GenericEx1 {

	public static void main(String[] args) {
		
		List<String> l=new ArrayList<String>();
		l.add("dinesh");
		
		String s=l.get(0);
	}
}
