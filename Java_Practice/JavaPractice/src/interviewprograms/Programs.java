package interviewprograms;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Programs {
	
	public static void diff_array() {
		
		/*Actual output : 
			  
			  Difference Value : 3
			  
			  a[3] : 11
			  a[4] : 5
		 */
		
		  int[] a={1,3,15,11,2};
		  int[] b={23,127,235,19,8};		  
		  
		  HashMap<Integer,Integer> arrValue;
		  TreeMap<Integer,HashMap<Integer,Integer>> tm=new TreeMap<Integer,HashMap<Integer,Integer>>();	
		  
		  TreeMap<Integer,HashMap<Integer,Integer>> tm2=new TreeMap<Integer,HashMap<Integer,Integer>>();
		  TreeMap<Integer,HashMap<Integer,Integer>> tm3=new TreeMap<Integer,HashMap<Integer,Integer>>();
		  
		  for(int i=0;i<a.length;i++){
			  
			  arrValue=new HashMap<Integer,Integer>();
			  for(int j=0;j<b.length;j++) {
				  
				  
				  arrValue.put(a[i], b[j]);
				  tm.put(Integer.valueOf(a[i]-b[j]),arrValue);
			  }
		      System.out.println(tm);
		  }
		  
		  tm.forEach((x,y)->{ 
			  				if(x>0) { System.out.println("diff value : "+x+" Pair :"+y) ; 			  				          
			  				        }
			  				});
		  
		  for(Map.Entry<Integer, HashMap<Integer,Integer>> me: tm.entrySet()) {
			  
			  if(me.getKey()>0) {
				  tm2.put(me.getKey(), me.getValue());				  
			  }	
			  else {
				  tm3.put(me.getKey(), me.getValue());
			  }
		  }	  
		  
		  System.out.println("Negative TreeMap: "+tm2.firstEntry());
		  System.out.println("Negative TreeMap: "+tm2.lastEntry());
		  System.out.println("Positieve TreeMap: "+tm3.firstEntry());
		  System.out.println("Positive TreeMap: "+tm3.lastEntry());
	}
	
	public static void factorial() {
		
		int n=6;
		int temp=1;
		for(int i=1;i<=n;i++) {
			temp=temp*i;
		}
		
		System.out.println("factorial value :"+temp);
		
	}
	
	static int factorial_rec2(int n) {
		
		if(n==1)
		{
			return n;
		}else 
		{
		return n*factorial_rec2(n-1);
		}
		
	}
	
	public static void factorial_rec() {
		System.out.printf("factorial value: %d ",factorial_rec2(5));
	}
	
	public static void Palindrome(int n) {
		
		int r,sum=0,temp=n;
		
		String d="dinesh";
		System.out.println("steing length ; "+ d.length());
		
		while(n>0) {
			r=n%10;
			sum=sum*10+r;
			n=n/10;		
		}
		
		if(temp==sum)
			System.out.println("Plaindrome");
		else
			System.out.println("not palidrome");
		
	}

	public static void stringReverseWithoutExtraVarible() {
		
		String s="dinesh";
		for(int i=s.length()-1;i>=0;i--) {
			s=s+s.charAt(i);
		}
		
		s=s.substring(s.length()/2, s.length());
		
		System.out.println("Revered string : "+s);
	}
	
	static void sortArray() {
		int[] n=new int[] {3,1,2,5,4,7,6};
		
		for(int i=0;i<n.length;++i) {
			for(int j=i+1;j<n.length;++j) {
				int temp=0;
				if(n[i]<n[j]) {
					temp=n[j];
					n[j]=n[i];
					n[i]=temp;
				}
			}
		}
		
		for(int in:n) {
			System.out.println("integer values :"+ in);
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//factorial();
		//factorial_rec();
		
		//Palindrome(538);
			
		//stringReverseWithoutExtraVarible();
		//sortArray();
		
		diff_array();
		
		System.gc();
	}
	
	/*
	 * public void finalize() { System.out.println("Garbage Collected"); }
	 */

}
