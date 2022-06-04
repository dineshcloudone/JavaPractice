package innerclasses;

/*Inner Class : Java inner class or nested class is a class which is declared inside the class or interface
	
	We use inner classes to logically group classes and interfaces in one place so that it can be more readable and maintainable.
	Additionally, it can access all the members of outer class including private data members and methods.	 
	
	-- > Inner class is a part of nested class. Non-static nested classes are known as inner classes
	
*/
public class MemberInnerClass {
	
	private int data=30;
	
	class Inner{
		void msg() {
			System.out.println("data is : "+data);
		}
	}
	
	public static void main(String[] args)
	{
		MemberInnerClass mic=new MemberInnerClass();
		MemberInnerClass.Inner in=mic.new Inner();
		
		in.msg();
	}

}
