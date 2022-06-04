package streamspractice;

// link : https://www.journaldev.com/2774/java-8-stream
// link : https://www.javatpoint.com/java-8-stream
// link : http://data-structure-learning.blogspot.com/search/label/lambda

import java.util.stream.Stream;

public class StreamEx {
	public static void main(String[] args) {
		
		//iteration through stream for multiple of 5
		Stream.iterate(1,element->element+1)
			.filter(element->element%5==0)
			.limit(5)
			.forEach(element->System.out.println("5 to 25 : "+element));
		
		//iteration through stream for multiple of 5
				Stream.iterate(1,element->element+1)
					.filter(element->element%5==0)
					.limit(5)
					.forEach(element->System.out.println("5 to 25 : "+element));
		
		//just iterating stream
		Stream.iterate(5, element->element*5)
			.limit(3)
			.forEach(System.out::println);
	}

}
