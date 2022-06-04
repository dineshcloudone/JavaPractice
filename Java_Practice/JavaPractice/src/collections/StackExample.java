package collections;

import java.util.*;

public class StackExample {

	public static void main(String[] args) {
		
		Stack<Integer> stk=new Stack<>();
		
		boolean result=stk.isEmpty();
		
		System.out.println("Is stack empty:"+ result);
		
		stk.push(1);
		stk.push(2);
		
		System.out.println("Printing as generics");
		stk.forEach(x->System.out.println(x));
		
		System.out.println();
		
		stk.forEach(x->System.out.println("values printed through for each : "+x));
		
		Iterator<Integer> it=stk.iterator();
		
		while(it.hasNext()) {
			
			System.out.println(stk.pop());
			//int i=it.next();
		}
		
		System.out.println("is stack empty : "+stk.isEmpty());
	}
}
