
import java.time.LocalTime;  

public class LocalTimeEx {

	public static void main(String[] args) {  
	
		
		LocalTime time = LocalTime.now();  
	    System.out.println(time);
	    
	    String s=time.toString().replace(":", "").replace(".", "");
	    System.out.println(s);
	    System.out.println(s.substring(4));
	    
	}

}