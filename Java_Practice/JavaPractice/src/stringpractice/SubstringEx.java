package stringpractice;

import java.util.Arrays;

public class SubstringEx {
	
	static void sub_ex1() {
	
		String s="SachinTendulkar";    
		 System.out.println("Original String: " + s);  
		 System.out.println("Substring starting from index 6: " +s.substring(6));//Tendulkar    
		 System.out.println("Substring starting from index 0 to 6: "+s.substring(0,6));
	}
	
	static void sub_ex2() {
		
		String text= new String("Hello, My name is Sachin");  
        /* Splits the sentence by the delimeter passed as an argument */  
        String[] sentences = text.split(" ");  
       // System.out.println(Arrays.toString(sentences));
        
        System.out.println(sentences.toString());
        System.out.println("Using Arrays to String: "+Arrays.toString(sentences));
        StringBuilder sb=new StringBuilder();
        for(String s:sentences)
        {	
        	sb=sb.append(s).append(" ");
        	
        }
        
        System.out.println("string array converted to string :"+ sb.toString().trim());
        
	}
	
	static void string_swap() {
		
		String a = "Love";  
        String b = "You";  
        System.out.println("Before swap: " + a + " " + b);  
        a = a + b;  
        b = a.substring(0, a.length() - b.length());  
        a = a.substring(b.length());  
        System.out.println("After : " + a + " " + b);  
	}
	
	public static void main(String[] args) {
		
		sub_ex2();
		//string_swap();
		
	}

}
