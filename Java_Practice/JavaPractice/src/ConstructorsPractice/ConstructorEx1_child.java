package ConstructorsPractice;
//pacakge ConstructorsPractice;

//Example 1
class ConstructorEx1
{
	int id;
	String name;	
	
	  ConstructorEx1(){	  
	  } 
	
	  ConstructorEx1(int i,String n) {
	  
	  // TODO Auto-generated constructor stub
	  System.out.println("Java Default Constructor Example1");
	  
	  System.out.println("Variables of class :"+i+" "+n);
	  
	  }
	 
	/*
	 * public static void main(String[] args) { new ConstructorEx1(); }
	 */
}


//Example 2
public class ConstructorEx1_child extends ConstructorEx1
{
	int id;
	String name;
	
	/*
	 * void ConstructorEx1(){
	 * 
	 * System.out.println("ConstructorEx1 from subclass"); }
	 */
	
	ConstructorEx1_child(int i,String n){
		
		System.out.println("Default Constructor");
		
		ConstructorEx1 ce1=new ConstructorEx1(2,"def");
		
		System.out.println("Variables of class :"+i+" "+n);
	}
	
	public static void main(String[] args)
	{
		ConstructorEx1_child ce2=new ConstructorEx1_child(1,"abc");
		//ce2.ConstructorEx1();
		
	}
}