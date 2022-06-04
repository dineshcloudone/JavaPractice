package stringpractice;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class ConcatenationClasses{
	
	void concatenation_Operator() {
		
		String s="Sachin"+"Tendulkar";		
		System.out.println(s);
	}

	void concat_method() {
		
		String s1="Sachin";
		String s2="Tendulkar";		
		String s3=s1.concat(s2);
		
		System.out.println(s3);
	}
		
	void concat_usingStringBuilderClass() {
		
		StringBuilder s1=new StringBuilder("Hello");
		StringBuilder s2=new StringBuilder("World");
		
		StringBuilder s=s1.append(s2);
		
		System.out.println(s);
		
		s=s1.append("abc");
		System.out.println("After String appending : "+s);
	}
	
	void stringJoinerClass() {
		
		StringJoiner sj=new StringJoiner(",");
		sj.add("hello");
		sj.add("world");
		
		System.out.println(sj);
	}
	
	void stringJoin() {
		
		String s1=new String("Hello");
		String s2=new String("World");
		
		String s=String.join("", s1,s2);
		
		System.out.println(s.toString());
	}
	
	void collectorsJoining() {
		
		List<String> liststr=Arrays.asList("abc","xyz","pqr");
		String strval=liststr.stream().collect(Collectors.joining(","));
		System.out.println(strval.toString());
	}
}



public class StringConcatenation {
	
	public static void main(String[] args) {
		
		ConcatenationClasses concat=new ConcatenationClasses();
		concat.concat_usingStringBuilderClass();
		
	}

}
