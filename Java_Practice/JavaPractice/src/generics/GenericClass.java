package generics;

public class GenericClass
{
	public static void main(String[] args)
	{
		BasicGeneric<String> str1=new BasicGeneric<String>(new String("Dinesh"));
		str1.displayReference();
		
		BasicGeneric<String> str2=new BasicGeneric<String>("Kumar");
		str2.displayReference();
		
		BasicGeneric<Integer> int1=new BasicGeneric<Integer>(new Integer(22));
		int1.displayReference();
		
		BasicGeneric<Integer> int2=new BasicGeneric<Integer>(25);
		int2.displayReference();
		
		BasicGeneric<Object> obj=new BasicGeneric<Object>(new Object());
		obj.displayReference();	
		
	}
}


class BasicGeneric<T> {

	T refVariable;
	BasicGeneric(T param)
	{
		refVariable=param;
	}
	void displayReference()
	{
		System.out.println("Generic Initiated Variable :"+ refVariable);
	}
}
