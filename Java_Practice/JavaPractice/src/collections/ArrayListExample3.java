package collections;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListExample3 {

	public static void main(String[] args) {
		
		ArrayList<Integer> al=new ArrayList<Integer>();
		
		al.add(34);
		al.add(45);
		
		al.forEach(x->System.out.println("Array list values :"+ x));
		
		
	}
}
