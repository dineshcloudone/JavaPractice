package stringpractice;

/* 1. Strings can be compared basing on content and reference
 * 2. It is used in authentication(by equals() method), sorting (by compareTo() method), reference matching(by == operator) etc.
 *  
 *    
 */

class ComparisonPractice
{
	void equalsEx()
	{
		String s1="Sachin";
		String s2="Sachin";
		String s3=new String("Sachin");
		String s4="Saurav";
		
		/*System.out.println(s1.equals(s2));
		System.out.println(s1.equals(s3));
		System.out.println(s1.equals(s4));*/
		
		System.out.println(s1==s2);
		System.out.println(s4.compareTo(s1));
	}	
	
	void equalsIgnoreCaseEx()
	{
		String s1="Sachin";
		String s2="SACHIN";
		
		System.out.println(s1.equals(s2));
		System.out.println(s1.equalsIgnoreCase(s2));
		
	}	
	
	void doubleEqualEx()
	{
		String s1="Sachin";
		String s2="Sachin";
		String s3=new String("Sachin");	
		
	}
	
	void strinBuilderEx() {
		 StringBuilder s1 = new StringBuilder("Hello");    //String 1  
	        StringBuilder s2 = new StringBuilder(" World");    //String 2  
	        StringBuilder s = s1.append(s2);   //String 3 to store the result  
	            System.out.println(s.toString());  //Displays result  
	}
	
	void compareTo_Ex() {
		String s1="Sachin";
		String s2="Sachin";
		String s3=new String("Sachin");
		String s4="Saurav";
		
		System.out.println("Comparing Strings :"+s1.compareTo(s4));
	}
	
}

public class StringComparison {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ComparisonPractice cp=new ComparisonPractice();
		cp.equalsEx();		
		//cp.equalsIgnoreCaseEx();
		//cp.compareTo_Ex();

	}

}
