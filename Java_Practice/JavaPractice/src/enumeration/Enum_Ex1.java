package enumeration;

import java.util.*;

//The Java enum constants are static and final implicitly.

//enum outside the class

enum days{
	SUNDAY,MONDAY,TUESDAY;
}

public class Enum_Ex1 {

	public static void main(String[] args) {
		
		days d=days.SUNDAY;
		
		System.out.println(d);
	}
	
}
