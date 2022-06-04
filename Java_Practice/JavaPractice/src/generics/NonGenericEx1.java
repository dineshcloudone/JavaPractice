package generics;

import java.util.*;

public class NonGenericEx1 {

	public static void main(String[] args) {
		
		List l=new ArrayList();
		l.add("dinesh");
		
		String a=(String)l.get(0);
		
		System.out.println(a);
	}
	
}
