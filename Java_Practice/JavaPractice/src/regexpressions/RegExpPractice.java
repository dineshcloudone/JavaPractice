package regexpressions;

import java.util.regex.*;
import java.util.Scanner;

class RegExamples{

	void match() {
		System.out.println("abc with world : "+Pattern.matches("[abc]{1}","ab"));
		
		Pattern p=Pattern.compile("[^abc]*");
		
		Matcher m=p.matcher("dinesh");
		System.out.println("Is patter matches: "+m.matches());
		System.out.println("Find Pattern :"+m.find());
		System.out.println("Start Matched subsequence index : "+m.start());
		System.out.println("End Matched subsequence index : "+m.start());		
	}
	
	void findEx() {
		
		Pattern p = Pattern.compile("ab");
		Matcher m = p.matcher("abaaaba");
		System.out.println("Group Count : "+ m.groupCount());	
		
		while(m.find()) {
			
			System.out.print("Start Position : "+m.start() + "  \n");
			System.out.println("End Position : "+m.end());
			System.out.println("Group :"+m.group(0));
					
			}
		}
	
	void quantifiersEx() {
		
	}
	
	void findIntegers() {
		
	}
	
	void findCharactersOnly() {
		
	}
}

public class RegExpPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RegExamples re=new RegExamples();
		//re.match();
		re.findEx();		
	}

}
