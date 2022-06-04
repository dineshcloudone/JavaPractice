package collections;

/*
 * LinkedHashSet doesn't null values
 */

import java.util.*;

class LHSPractice
{
	void displayUsingForEach(LinkedHashSet lhs)
	{
		lhs.forEach(x->System.out.println("Values :"+x));
		System.out.println("Linked HashSet Size :"+ lhs.size());
	}
}

public class LinkedHashSetExamples {
	
	public static void main(String[] args)
	{
		LHSPractice lhsp=new LHSPractice();
		
		LinkedHashSet<String> lhs=new LinkedHashSet<String>(); 
		
		lhs.add("Dinesh");
		lhs.add("Kumar");
		lhs.add("Gurram");
		lhs.add(null);
		//lhs.addAll(null);
		
		
		lhsp.displayUsingForEach(lhs);
		
		LinkedHashSet<Integer> lhsi=new LinkedHashSet<Integer>(); 
		
		lhsi.add(15);
		lhsi.add(10);
		lhsi.add(5);
		lhsi.add(5);
		lhsi.add(null);
		lhsi.add(null);
		//lhsp.displayUsingForEach(lhsi);
	}

}
