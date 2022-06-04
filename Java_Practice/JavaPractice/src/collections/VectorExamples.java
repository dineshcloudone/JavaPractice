package collections;

import java.util.*;

/*
 * LinkedList vs ArrayList vs Vector
 * 1. Vector is synchronized
 * https://dzone.com/articles/arraylist-vs-linkedlist-vs
 * 
 *  Vector is allowing null values
 */

/*The Iterators returned by the Vector class are fail-fast. In case of concurrent modification, it fails and throws the ConcurrentModificationException.

It is similar to the ArrayList, but with two differences-

    Vector is synchronized.
    Java Vector contains many legacy methods that are not the part of a collections framework.
*/

class VectorPractice
{
	void displayVector(Vector v)
	{
		v.forEach(x->System.out.println("Vector vaules: "+x));
		System.out.println("\n");
	}
}

public class VectorExamples {
	
	public static void main(String[] args)
{
	Vector<String> v=new  Vector<String>();
	v.add("kumar");
	v.add("dinesh");
	v.add("gurram");
	v.add("dinesh");
	v.add(null);
	
	
	
	VectorPractice vstr=new VectorPractice();
	
//	vstr.displayVector(v);
	
	Iterator<String> vtr2=v.iterator();

	Vector<Integer> vint=new  Vector<Integer>();
	vint.add(4);
	vint.add(2);
	vint.add(3);
	
	vstr.displayVector(vint);
}

}
