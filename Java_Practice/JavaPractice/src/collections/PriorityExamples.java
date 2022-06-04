
/*
 * PriorityQueue : It won't allow null values
 */

//https://www.programcreek.com/2009/02/using-the-priorityqueue-class-example/
package collections;
import java.util.*;

class PQPractice
{
	
}

public class PriorityExamples {
	public static void main(String[] args)
	{
		PriorityQueue<String> pq=new PriorityQueue<String>();
		
		System.out.println("Is Priority Queue is empty: "+pq.isEmpty());
		
		pq.add("Dinesh");
		pq.add("Kumar");
		pq.add("gurram");
		
		System.out.println("peek :"+pq.peek());
		System.out.println("poll :"+pq.poll());
		
		pq.forEach(x->System.out.println("Priority Queue Elements after poll :"+ x));
		
		System.out.println("offerind Dinesh :"+pq.offer("Dinesh"));
		pq.forEach(x->System.out.println("Priority Queue Elements after offer :"+ x));
		
		
		
	}
	
}
