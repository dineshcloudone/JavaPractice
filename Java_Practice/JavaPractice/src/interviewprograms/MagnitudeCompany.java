package interviewprograms;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MagnitudeCompany {

	public static void separAlpahNumeric(String str) {

		char[] ch = str.toCharArray();
		ArrayList<Integer> al1 = new ArrayList<Integer>();
		ArrayList<Character> al2 = new ArrayList<Character>();

		int i = 0;

		for (char c : ch) {

			if (Character.isDigit(c)) {

				al1.add(Character.getNumericValue(c));
			} else {
				al2.add(c);
			}
		}

		al1.forEach(x -> System.out.print(x));

		/*
		 * for(Integer a:al2){ i+=a; }
		 */

		System.out.println();
		al2.forEach(x -> System.out.print(x));

	}

	public static void printReverseWords() {

		
		  String s="My       name     is  Dinesh";
		  
		  String[] sa=s.split(" ");
		  
		  for(int i=sa.length-1;i>=0;i--){
		  
		  System.out.print(sa[i]+" ");
		  }

		/*
		 * String s = "My       name     is  Dinesh";
		 * 
		 * String[] sa = s.split(" ");
		 * 
		 * Pattern p = Pattern.compile("[\\s]+");
		 * 
		 * Matcher m = p.matcher(s);
		 * 
		 * ArrayList<Integer> al = new ArrayList<Integer>();
		 * 
		 * while (m.find()) {
		 * 
		 * al.add(m.end() - m.start());
		 * 
		 * System.out.println("In while loop");
		 * System.out.println("start : "+m.start());
		 * System.out.println("end : "+m.end());
		 * 
		 * }
		 * 
		 * System.out.println(al);
		 * 
		 * for (int i = sa.length - 1, j = 0; i >= 0; i--, j++) {
		 * 
		 * System.out.print(sa[i]);
		 * 
		 * if (j < al.size()) {
		 * 
		 * for (int k = 0; k < al.get(j); k++) {
		 * 
		 * System.out.print(" "); }
		 * 
		 * } }
		 */
	}

	public static void main(String[] args) {

		// separAlpahNumeric("abc641def82ghi1");
		printReverseWords();

	}
}
