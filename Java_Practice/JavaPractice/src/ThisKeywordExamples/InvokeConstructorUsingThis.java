package ThisKeywordExamples;

public class InvokeConstructorUsingThis
{
	int id;
	String name;
	
	InvokeConstructorUsingThis()
	{	
		this(1,"Dinesh");
		
		System.out.println("This is default constructor");
		System.out.println("id value :"+id+" name value :"+name);
	}
	
	InvokeConstructorUsingThis(int i,String n) {
		// TODO Auto-generated constructor stub	
	id=i;
	name=n;
	}
	
	public static void main(String[] args)
	{
		InvokeConstructorUsingThis ce1=new InvokeConstructorUsingThis();
		//ce1.display();
		//ce1.display();
	}

}