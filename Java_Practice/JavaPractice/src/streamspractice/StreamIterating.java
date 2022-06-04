package streamspractice;

import java.util.List;
import java.util.stream.*;

public class StreamIterating {

	public static void main(String[] args) {
		List<Integer> list=Stream.iterate(1, element->element+1)
		.filter(element->element%5==0)
		.limit(5)
		.collect(Collectors.toList());
		
		System.out.println("Integers in the list :"+ list);
	}
	
}
