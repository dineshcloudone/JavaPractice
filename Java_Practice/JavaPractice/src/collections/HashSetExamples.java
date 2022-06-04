package collections;

import java.util.*;

/* HashSet:
 * 
 * 1. It won't allows duplicate values.
 * 2. HashSet stores the elements by using the Hashing mechanism
 * 3. HasSet doesn't maintain the insertion order
 * 4. HasSet allows one null value
 * 
 * LinkedHashSet:
 * 1. It allows null values
 * 2. It maintains the insertion order
 */

//Set is not having get and set methods
//http://www.geeksforgeeks.org/comparable-vs-comparator-in-java/

public class HashSetExamples {
		
	void addEx(HashSet<String> hs)
	{
		hs.add("Dinesh");
		hs.add("Kumar");
		hs.add("Gurram");	
			
	}
	
	void displayUsingForEach(HashSet hs) {
		hs.forEach(x->System.out.println("HashSet values :"+x));
		System.out.println("HashSet size :"+ hs.size());		
	}
	
	public static void main(String[] args)
	{
		HashSet<String> hs=new HashSet<String>();
		hs.add("dinesh");
		hs.add("kumar");
		hs.add("gurram");
		hs.add(null);
		hs.add(null);
		
		
		HashSetExamples hse=new HashSetExamples();
		//hse.displayUsingForEach(hs);
		
		HashSet<Integer> hsi=new HashSet<Integer>();
		hsi.add(12);
		hsi.add(9);
		hsi.add(null);
		hsi.add(null);
		hsi.add(7);
		hsi.add(4);		
		hse.displayUsingForEach(hsi);
		
		Vector<String> v=new Vector<String>();
		v.addElement("a");
		
	}

}
