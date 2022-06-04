package collections;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/*// https://javaconceptoftheday.com/differences-between-enumeration-vs-iterator-in-java/
 * 1. Insertion
 * 2. Deletion
 * 3. Copying
 * 4. Manipulating
 * 5. Sort
 * */

//ArrayList is fast in get while LinkedList is fast in add and remove

//LinkedList vs ArrayList vs Vector
//* 1. Vector is synchronized
//* https://dzone.com/articles/arraylist-vs-linkedlist-vs

//Below link is for : 	  
//http://javajee.com/enumeration-iterator-and-listiterator-in-java

/*
 *  ArrayList : It uses a dynamic array for storing the elements. It inherits AbstractList class and implements List interface
 *  
 *  1) As explained above the insert and remove operations give good performance (O(1)) in LinkedList compared to ArrayList(O(n)). 
 *  Hence if there is a requirement of frequent addition and deletion in application then LinkedList is a best choice.

	2) Search (get method) operations are fast in Arraylist (O(1)) but not in LinkedList (O(n)) 
	so If there are less add and remove operations and more search operations requirement, ArrayList would be your best bet.
 *  
 *  
 *  ArrayList allows null values
 *  
 * Link to convert arrays to List : https://www.programcreek.com/2014/04/check-if-array-contains-a-value-java/
 *  
 * Legacy collections : https://www.studytonight.com/java/legacy-classes-and-interface.php
 *  
 *  Enumeration vs Iterator :
 *  https://javahungry.blogspot.com/2013/06/difference-between-iterator-and-enumeration-collections-java-interview-question-with-example.html#:~:text=The%20main%20difference%20between%20Iterator,not%20have%20remove()%20method.
 *  
 *  
 */

class Constructor1
{
	ArrayList<String> al1;	
	HashSet<String> hs;
	LinkedHashSet<String> lhs;
	
	Constructor1(ArrayList<String> al1)
	{		
		this.al1=al1;
		al1=new ArrayList<String>();		
	}
	
	void mergeTwoList()
	{
		List<String> listone=new ArrayList<String>();
		listone.add("listoneDinesh");
		listone.add("listoneGurram");
		listone.add(null);
		
		List<String> listtwo=new ArrayList<String>();
		listtwo.add("listtwoDinesh");
		listtwo.add("listtwoGurram");
		
		
		
		//List<String> listThree=new ArrayList<String>();
		List<String> newList = Stream.concat(listone.stream(), listtwo.stream())
                .collect(Collectors.toList());
		
		newList.forEach(x->System.out.println("New List Values :"+x));
		
	}
	
	//add(String e): boolean - ArrayList - 10%
	//add(int index, Object element) - void - ArrayList
	void addEx(ArrayList<String> al)
	{
		
		al.add("test");
		System.out.println("Size of the arrayList : "+al.size());
		//al.add(al1.size(), "added this element using index");
		al.add("added this element using index");
		
		al.forEach(x->System.out.println("Array list elements :"+x));
		System.out.println("Size of the arrayList : "+al.size());
		
		System.out.println("Element at index 4: "+al.get(4));
	}
	
	//public boolean addAll(Collection<? extends E> c)
	//addAll(int index, Collection c) - boolean - ArrayList
	void addAllEx(ArrayList<String> al)
	{	
		
		String[] s= {"fasfsd","afdas","afsdf"};		
		
		System.out.println("Data in ArrayList before adding values:"+al);
		
		al.addAll(Arrays.asList(s));
		
		Collections.addAll(al, "suresh","girish");
		
		System.out.println("Data in ArrayList after adding 2 values:"+al);
		
		ArrayList<String> al2=new ArrayList<String>();
		al2.add("al2:Dinesh");
		al2.add("al2:Kumar");
		al2.add("al2:Gurram");
		
		al.addAll(al.size(), al2);
		
		System.out.println("Data in ArrayList after adding al2:"+al);
		
		LinkedList<String> ll=new LinkedList<String>();
		
		
	}
	
	//public void forEach(Consumer<? super E> action)
	void forEachEx(ArrayList<String> al)
	{	
		al.forEach(x->
		{
			if(x.toString().toLowerCase().trim().equals("dinesh"))
				{x.toString().concat(" : Checking For Each");}
			System.out.println(x);
		}
		);
		
		System.out.println("method reference");
		al.forEach(System.out::println);
	}
	
	//clone() - Object - ArrayList
	void cloneEx(ArrayList<String> al)
	{	
		ArrayList<String> al2;
		//al2=new ArrayList<String>();	
		
		al2=(ArrayList<String>)al.clone();
		al2.add("fjlsdkjfa");
		al2.forEach(x->System.out.println("ArrayList 2 vaues after cloning : "+x));
		
		al.forEach(x->System.out.println("ArrayList one vaues after cloning : "+x));
	}
	
	//display ArrayList using for each
	void displayArrayListUisngForEach(ArrayList<String> al)
	{
		al.forEach(x->System.out.println("ArrayList values : "+x));		
	}

	//We can remove all the duplicates of ArrayList by adding it to HashSet
	void removeArrayListDuplicatesUsingHashSet(ArrayList<String> al)
	{
		hs=new HashSet<String>(al);
		//hs.addAll(al);
		for(String s:hs)
		{
			System.out.println("hs elements: "+s);
		}
		
	}
		
	//We can maintain order and remove duplicates of ArrayList by adding it to LinkedHashSet
	void removeArrayListDuplicatesUsingLinkedHashSet()
	{
		lhs=new LinkedHashSet<String>(al1);
		//lhs.addAll(al1);
		System.out.println("\n");
		for(String s:lhs)
		{
			System.out.println("lhs elements: "+s);
		}
	}
	
	void removeEx()
	{
		al1.remove(2);
		for(String s:al1)
		{
			System.out.println("al1 elements: "+s);
		}
	}
	
	void removeDuplicateswithoutusingColl()
	{
		
		ArrayList<String> al2=new ArrayList<String>();
		Iterator it=al1.iterator();
		while(it.hasNext())
		{
			String s=(String)it.next();
			if(!al2.contains(s)) 
				al2.add(s);
		}
		
		System.out.println("\n");
		
		for(String s:al2)
		{
			System.out.println("al2 elements after removing duplicates: "+s);
		}		
	}
	
	void removeIfMethod(String remove)
	{
		Predicate<String> p= x -> x.toUpperCase().equals(remove.toUpperCase());
		al1.removeIf(p);
		al1.forEach(x->System.out.println("elements from the array list after removal :" +x));
	}
	
	void updateOneArrayListElement(ArrayList<String> al) 
	{
	
		System.out.println("Array Element Size :"+ al.size());
		int count=0;
		for(String str:al)
		{
			System.out.println("count value before if :"+count);
			if(str.toUpperCase().equals("DINESH"))
			{
				al.set(count, "Dinesh_Updated");
			}
			count++;
		}
		
		al.forEach(x->System.out.println("Array List Elements "+x));		
		System.out.println("Index value of Dinesh_Updated : "+al.indexOf("Dinesh_Updated"));
		
		
	}

	void sortIntegerArrayListElements() 
	{
		//defined a class for this
		
		ArrayList<Integer> ali=new ArrayList<Integer>();
		ali.add(12);
		ali.add(10);
		ali.add(13);
		ali.add(13);	
		
		System.out.println("Before Sorting");
		ali.forEach(x->System.out.println("value :"+ x));
		Collections.sort(ali);
		System.out.println("After Sorting");
		ali.forEach(x->System.out.println("value :"+ x));
	}
	
	void copyArrayListUsingCopyConstructor(ArrayList al) {
		ArrayList<String> newAL=new ArrayList(al);
		newAL.forEach(x->System.out.println("ArrayList values copied using copy constructor"+x));		
	}
	
	void getAndSet(ArrayList al)
	{
		System.out.println(al.get(0));
		al.set(0, "modified this element using set");
		System.out.println(al.get(0));		
	}
	
	void convertArraytoList(ArrayList al) {
		
		String[] str= {"Array : Dinesh","Array : Kumar","Array : Gurram"};
		
		List<String> al2=Arrays.asList(str);
		
		System.out.println("Printing array in the form of arraylist : "+al2);
		
		  al.addAll(Arrays.asList(str));
		  
		  al.forEach(x->System.out.println("Converted array to list :"+x));
		  
		  System.out.println("Array List vales :"+al.size());
		  
		  String[] str2=(String[]) al.toArray(new String[al.size()]);
		  
		  System.out.println("String array values==");
		  
		  for(String s:str2) { System.out.println(s); }
		 
	}
	
}

public class ArrayListExamples {
	
	public static void main(String[] args)
	{
		//ArrayList<String> al1=new ArrayList<String>();
		
		ArrayList<String> al1=new ArrayList<String>();
		Constructor1 o=new Constructor1(al1);
	
		
		//c.Copy();
		/*c.displayArrayList();s
		c.displayHashset();
		c.displayLinkedHashset();*/
		//c.removeDuplicateswithoutusingColl();
		//c.removeArrayListElements();
		//c.removeIfMethod("gurram");
		
		al1.add("dinesh");
		al1.add("kumar");
		al1.add("gurram");
		
		//System.out.println("ArrayList details : "+al1);
		
		//al1.add(null);		
		//System.out.println("Size of the list :"+al1.size()+" Index 0 :"+al1.get(0));
		
		//al1.add(null);
		//al1.forEach(x->System.out.println(x));
		
				
		/*for(Object s:al1)
		{
			if(!(s.getClass().toString().equals("java.util.TreeSet")))
			{
				TreeSet ts=(TreeSet)s;
				ts.forEach(x->System.out.println("TreeSet values"+x));
			}
			else
			{
				System.out.println("ArrayList Values :"+ s.toString());
			}
		}*/
		
		
		//o.addEx(al1);
		
		//o.addAllEx(al1);
		//o.updateOneArrayListElement(al1);
		//o.forEachEx(al1);
		//o.sortIntegerArrayListElements();
		//o.mergeTwoList();
		//o.copyArrayListUsingCopyConstructor(al1);
		//o.cloneEx(al1);
		//o.getAndSet(al1);
		o.convertArraytoList(al1);
		//o.removeIfMethod("gurram");
		
	}

}



// 



