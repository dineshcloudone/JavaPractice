package generics;

import java.util.*;
import java.util.stream.*;

public class GenericExHashMap {

	public static void main(String[] args) {
		
		HashMap<Integer,String> hm=new HashMap<Integer,String>();
		
		hm.put(1, "Dinesh");
		hm.put(2,"Kumar");
		
		Set<Map.Entry<Integer,String>> s=hm.entrySet();
		Iterator<Map.Entry<Integer, String>> i=s.iterator();
		
		while(i.hasNext()) {
			Map.Entry e=i.next();
			System.out.println("Key :"+e.getKey()+","+ "Value :"+e.getValue());
		}
		
		
	}
}
