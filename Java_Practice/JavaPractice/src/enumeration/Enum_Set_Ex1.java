package enumeration;

import java.util.EnumSet;
import java.util.Set;

//The Java enum constants are static and final implicitly.

enum days2{
	SUNDAY,MONDAY,TUESDAY;
}

public class Enum_Set_Ex1 {

	public static void main(String[] args) {
		
		System.out.println("Directly printing enum value : "+days2.MONDAY);
				
		Set<days2> s=EnumSet.of(days2.SUNDAY,days2.MONDAY);
		
		for(days2 d:s) {
			System.out.println("enum values from enumset.of : " +d);
		}
		
		Set<days2> s2=EnumSet.allOf(days2.class);
		for(days2 d:s2) {
			System.out.println("enum values from enumset.allOf : " +d);
		}
				
		for(days2 d:days2.values()) {
			System.out.println("using values method : "+d);
		}
		
	}
}
