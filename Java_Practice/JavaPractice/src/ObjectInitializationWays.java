

/*There are 3 ways to initialize object in java.

	1. By reference variable
	2. By method
	3. By constructor

*/

//Ex 1 : By reference Variable

/*class Way1_ByReference
{
	int id;
	String name;
}

public class ObjectInitializationWays
{
	public static void main(String[] args)
	{
		Way1_ByReference w1=new Way1_ByReference();
		w1.id=1;
		w1.name="Sonoo";
		
		System.out.println(w1.id+" "+w1.name);
	}
}*/

//Ex 2 : By Method

class Way2_Method
{
	int id;
	String name;
	
	void initialize(int i,String n) 
	{
		id=i;
		name=n;
	}

	void displayInformation() {
		// TODO Auto-generated method stub
		System.out.println(id+" "+name);
	}
}


//Initilizing using constructor will be see in constructors	








