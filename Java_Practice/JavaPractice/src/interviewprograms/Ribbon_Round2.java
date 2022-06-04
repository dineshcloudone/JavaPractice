package interviewprograms;

import java.util.HashMap;
import java.util.TreeSet;

public class Ribbon_Round2 {
		
	
	static void second_highest_value() {
		
		int[] i= {1,65,87,26,98,27592};
		
		TreeSet<Integer> ts=new TreeSet<Integer>();
		
		for(int j:i) {
			ts.add(j);	
		}
		System.out.println("Second Highest Value :"+ ts.toArray()[ts.size()-2]);
	}
	
	static void remove_special_characters() {
		
		String s ="adfgfhfjygyu      dytfyuuy #23$%^ ffghjcx";		
		System.out.println("After removing special chars :"+s.replaceAll("[^0-9a-zA-Z]", ""));
		
		System.out.println("After removing word[a-zA-Z_0-9] chars and spaces: "+s.replaceAll("[\\w\\s]", "")+"EOD");
		
		/*
		 * char[] ch=s.toCharArray();
		 * 
		 * for(char c:ch) { System.out.println(c); }
		 */
	}
	
	static void print_pattern() {
		
		//input = 1,2,9,28,65,126,....
				
		//	1 2 9 28 65 126,..........
		//	0 1 2 3  4
		//	  0+1=1
		//	  1+1 =2
		//	  2*2*2=8+1
		//	  3*3*3=27+1
		//	  4*4*4=64+1
		//	  5*5*5=125+1
	
		for(int j=0;j<=5;j++) {
			
				int value=j*j*j+1;
			
			 System.out.println("Value at index "+j+" :"+value);
		}
	
	}
	
	static void dict_common_key() {
		
		/*dict1 ={"a:1",b:2,c:4,k:5}
		  dict2={d:9,f:78,k:98}
		  dict1= {a:1",b:2,c:4,k:(5,98),d:9,f:78}
		 */		  
		  
		  HashMap<String,String> hm1=new HashMap<String,String>();
		  
		  hm1.put("a","1");
		  hm1.put("b","2");
		  hm1.put("c","4");
		  hm1.put("k","5");
		  
		  
		  HashMap<String,String> hm2=new HashMap<String,String>();
		  hm2.put("d","9");
		  hm2.put("f","78");
		  hm2.put("k","98");
		  
		  for(HashMap.Entry<String,String> entry:hm1.entrySet()){

		        if(hm2.containsKey(entry.getKey())){
		            
		        hm2.put(entry.getKey(), "("+hm2.get(entry.getKey())+","+entry.getValue()+")" );
		    
		    }

		}
		  
		  hm2.forEach((x,y)->System.out.println("Key : "+x+"  Value :"+y));
	}
	
	public static void main(String[] args) {
		
		//second_highest_value();
		remove_special_characters();
		
		//print_pattern();
		
		//dict_common_key();
	}
	
}
