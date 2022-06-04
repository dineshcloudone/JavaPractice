package innerclasses;

//link : https://www.geeksforgeeks.org/anonymous-inner-class-java/ 

interface Employee{
	int x=10000;
	void salary();
}

interface organisation{
	void revenue();
}

public class AnonymousInnerClass2 implements Employee,organisation {
	
	@Override
	public void salary() {
		System.out.println("salary : "+x);
	}
	
	@Override
	public void revenue() {
		System.out.println("revenue");
	}
	
	public static void main(String[] args)
	{
		AnonymousInnerClass2 an=new AnonymousInnerClass2();
		an.salary();
		an.revenue();
		System.out.println("Test");
	}

}
