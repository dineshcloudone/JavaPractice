package collections;

/*
 * TreeSet doesn't allows null values
 * 
 */

import java.util.*;

class TSPractice
{
	void display(TreeSet ts)
	{
		ts.forEach(x->System.out.println("TreeSet Values :"+x));
		System.out.println("\n");
	}
	
	void display(ArrayList al)
	{
		al.forEach(x->System.out.println("TreeSet Values :"+x));
		System.out.println("\n");
	}
	
	TreeSet getSort(ArrayList list)
	{
		TreeSet ts=new TreeSet(list);
		return ts;
	}
}

public class TreeSetEx {
	public static void main(String[] args)
	{
		TSPractice tsp=new TSPractice();
		
		/*
		 * TreeSet ts=new TreeSet(); ts.add(1); ts.add(11); ts.add(9); tsp.display(ts);
		 * 
		 * 
		 * TreeSet<String> ts2=new TreeSet<String>(); ts2.add("c"); ts2.add("b");
		 * ts2.add("a"); ts2.add(null); tsp.display(ts2);
		 */
		ArrayList<Integer> list=new ArrayList<Integer>();
		list.add(4);
		list.add(5);
		list.add(3);
		//list.add(null);
		
				
		tsp.display(list);
		
		TreeSet ts2=tsp.getSort(list);			
		
		tsp.display(ts2);
	}
}
