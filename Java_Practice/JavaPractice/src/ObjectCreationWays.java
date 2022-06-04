/*
 * There are many ways to create an object some of them are below
 *  
 *  1. By new keyword
 *  2. By newInstance()
 *  3. By clone()
 *  4. By deserialization
 *  5. By factory method
 * 
 */



//Anonymous Object creation

class Calculation
{
	
	void fact(int n)
	{
		int fact=1;
		for(int j=1;j<=n;j++)
		{
			fact=fact*j;
		}
		System.out.println(fact);
	}
	public static void main(String[] args)
	{
		new Calculation().fact(10);
	}
	
}

 