package collections;
import java.util.*;

/*
 * URL : HashMap vs HashTable - http://javahungry.blogspot.com/2014/03/hashmap-vs-hashtable-difference-with-example-java-interview-questions.html
 * http://javahungry.blogspot.com/2013/08/difference-between-comparable-and.html
 *  
 */

class HashMapPractice {	
	
	HashMap<String,String> hm;	
	
	void printNonGenericHashMap()
	{
		Map mp=new HashMap();
		mp.put(1, "Dinesh");
		mp.put(2,"Kumar");
		mp.put(3,"Gurram");
		mp.put(null, null);
		mp.put(null, null);
		
		
		Set s=mp.entrySet();
		Iterator it=s.iterator();
		
		while(it.hasNext()) {
			Map.Entry entry=(Map.Entry)it.next();
			System.out.println("Using Iterator Key value :"+ entry.getKey()+" Vaue :"+ entry.getValue());			
		}
	}
	
	void printGenericHashMap() {
		
		HashMap<Integer,String> hm=new HashMap<Integer,String>();
		
		hm.put(1, "Dinesh");
		hm.put(2,"Kumar");
		hm.put(3, "Gurram");	
				
		for(Map.Entry<Integer,String> entry:hm.entrySet())
		{
			System.out.println("Using for Key value :"+ entry.getKey()+" Vaue :"+ entry.getValue());
		}
	}
	
	void printHashMapusingLambda() {
		
		Map mp=new HashMap();
		mp.put(1, "Dinesh");
		mp.put(2,"Kumar");
		mp.put(3,"Gurram");
		mp.put(1, "venkat");
		mp.put(5, "abc");
		mp.put(6, "def");
		//mp.forEach((x,y)->System.out.println("Using for each - >Key :"+x+" value :"+y));
		System.out.println("Printing hashmap using lambda : "+mp);
		
	}
	
	void hashMapBasicMethods(HashMap hm)
	{
		this.hm=hm;
		
		//Object get(Object key)
		System.out.println(hm.get(1));
				
		//boolean containsKey(Object key)
		System.out.println("HashMap contains the Key 1 :"+hm.containsKey(1));
		
		//boolean ContainsValue(Object value)
		System.out.println("HashMap contains the value dinesh :"+hm.containsValue("Dinesh"));
		
		//Clearing the hashmap entrysets
		//hm.clear();
		
		//boolean isEmpty
		System.out.println("Is HashMap empty :"+hm.isEmpty());
		
		//Object clone()
		HashMap<Integer,String> hm2=(HashMap)hm.clone();
		System.out.println("hm2 cloned from hm : "+hm2);
		
	}

	void printHashMapUsingIteratorInterface(HashMap hm) {
		this.hm=hm;
		Set st=hm.entrySet();
		Iterator it=st.iterator();
		while(it.hasNext()) {
			Map.Entry me=(Map.Entry)it.next();
			System.out.println("Key : "+me.getKey()+" Value : "+me.getValue());
		}
	}
}


class Book{
	int id;
	String name, author, publisher;
	int quantity;
	public Book(int id, String name,String author,String publisher,int quantity){
		this.id=id;
		this.name=name;
		this.author=author;
		this.publisher=publisher;
		this.quantity=quantity;
		
	}
}

public class HashMapExamples
{
	public static void main(String[] args)
	{
		HashMap<Integer,String> hm=new HashMap<Integer,String>();
		
		hm.put(1, "Dinesh");
		hm.put(2,"Kumar");
		hm.put(3, "Gurram");
		hm.put(3, "Gur3434ram");
		hm.put(4, null);
		hm.put(5, null);
		hm.put(null, null);
		hm.put(null, null);
		
		HashMapPractice hmpO=new HashMapPractice();
		//hmpO.hashMapBasicMethods(hm);
		//hmpO.printNonGenericHashMap();
		//hmpO.printHashMapusingLambda();
		
		/*
		 * Book b1=new Book(101,"Let us C","Yashwant Kanetkar","BPB",8); Book b2=new
		 * Book(102,"Data Communications & Networking","Forouzan","Mc Graw Hill",4);
		 * Book b3=new Book(103,"Operating System","Galvin","Wiley",6);
		 * 
		 * HashMap<Integer,Book> hmb=new HashMap<Integer,Book>(); hmb.put(1, b1);
		 * hmb.put(2, b2); hmb.put(3, b3);
		 */
	    
	   // hmb.forEach((x,y)->System.out.println(x+" Details:"+"\n Book detais :"+y.id+","+y.author+","+y.quantity));
		
		for(Map.Entry<Integer, String> h:hm.entrySet())
		{
			//System.out.println("Key :"+h.getKey()+" Value :"+h.getValue());
			
		}
		//hmpO.printHashMapUsingIteratorInterface(hm);
		hmpO.printHashMapusingLambda();
	}
}