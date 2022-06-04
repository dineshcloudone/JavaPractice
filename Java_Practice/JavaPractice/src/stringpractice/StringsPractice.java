package stringpractice;
import java.util.Arrays;

class String_Concepts{
	
	void charArrayToString() {
		char[] ch=new char[] {'a','b','c','d'};
		
		String s=new String(ch);
		
		System.out.println("char array : "+Arrays.toString(ch));
		System.out.println("char array converted to string : "+s);
		System.out.println("char array length : "+ch.length);
		System.out.println("lenght of string : "+s.length());
		
	}
	
	void String_Lieteral_Ex() {
		
		String s1="welcome";
		char ch[]= {'s','t','r','i','n','g','s'};
		String s2=new String(ch);
		String s3=new String("example");
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);		
	}
	
	void immutable_String() {
		
		String s="Sachin";
		s.concat("Tendulkar");
		System.out.println("immutable_String not assigned to reference variable: "+s);
		
		s=s.concat(" Tendulkar");
		System.out.println("immutable_String assigned to reference variable: "+s);
		
		
		String s2="Dinesh";
		String s3="Dinesh";
		s3="Dinesh_Kumar";
		System.out.println("s2 : "+s2+", "+" s3 : "+s3);
	}
}

public class StringsPractice {

	public static void main(String[] args) {
		
		String_Concepts sc=new String_Concepts();
		sc.charArrayToString();
		//sc.String_Lieteral_Ex();
		//sc.immutable_String();
		
	}
	
}
