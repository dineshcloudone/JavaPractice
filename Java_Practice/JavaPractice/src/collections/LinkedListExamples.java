package collections;

/*
 *  LinkedList allows null values
 */

import java.util.function.*;
import java.util.*;

class Practice
{	
	void addNewElements(LinkedList<String> ll)
	{
		ll.addFirst("First");
		ll.add("added String");
		ll.add(ll.size(), "added using index");
		
		ll.forEach(x->System.out.println("Linked List(ll) elements : "+x));
		
		LinkedList<String> ll2=new LinkedList<String>();
		ll2.add("ll2Dinesh");
		ll2.add("ll2Gurram");		
		
		ll.addAll(ll2);
		ll.addLast("Last");	
		
	}
	
	void usingQueueMethods(LinkedList<String> ll)
	{
		System.out.println("Elements in the list Before queue method");
		
		ll.forEach(x->System.out.println("values in linked list : "+x));
		
		//System.out.println("Peeking the element of linked list: "+ll.peek());
		
		System.out.println("Poll the first element : "+ll.poll());
		
		//System.out.println("FirstPeek the element of linked list: "+ll.peekFirst());
		
		ll.offerLast("Offerintlast string");
		System.out.println("LastPeek the element of linked list: "+ll.peekLast());
		
		System.out.println("List Empty: "+ll.isEmpty());		
		
		System.out.println("Elements in the list after queue method");
		ll.forEach(x->System.out.println("values in linked list : "+x));
	}
	
	void sortAndDisplayIntegerElements() 
	{
		LinkedList<Integer> ll2=new LinkedList<Integer>();
		ll2.add(12);
		ll2.add(10);
		ll2.add(15);
		
		ll2.set(2, 20);
		
		ll2.forEach(x->System.out.println("Integer Values \n "+x));
		
		ll2.remove(1);
		
		//Collections.sort(ll2);
		ll2.forEach(x->System.out.println("Integer Values "+x));		
	}
	
	void displayLinkedListusingForEach(LinkedList<String> ll)
	{
		ll.forEach(x->System.out.println(" "+ x));		
	}

	void displayUsingIterator(LinkedList<String> ll)
	{
		Iterator<String> it=ll.iterator();
		System.out.println("\n");
		while(it.hasNext())
		{
			System.out.println("LinkedList elements using iterator :"+it.next());
		}
		
	}
	
	void displayListIterator(LinkedList<String> ll)
	{
		ListIterator<String> it=ll.listIterator();
		System.out.println("\n");
		while(it.hasNext())
		{
			System.out.println("LinkedList elements using iterator :"+it.next());
		}
		System.out.println("\n");
		while(it.hasPrevious())
		{
			System.out.println("LinkedList elements using iterator :"+it.previous());
		}
	}
	
}

public class LinkedListExamples {

	public static void main(String[] args)
	{
		Practice p=new Practice();
		LinkedList<String> ll=new LinkedList<String>();
		ll.add("Dinesh");
		ll.add("Kumar");
		ll.add("Gurram");
		ll.add("test");
		
		ll.add(null);
		ll.add(null);
		//p.displayFor();
		//p.displayIterator();
		p.addNewElements(ll);
		//p.displayLinkedListusingForEach(ll);
		
		//p.sortAndDisplayIntegerElements();		
		//p.usingQueueMethods(ll);
	}
	
}
