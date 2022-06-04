package ThisKeywordExamples;

public class InvokeMethodUsingThis
{
	int id;
	String name;
	
	InvokeMethodUsingThis(int i,String n) {
		// TODO Auto-generated constructor stub	
	id=i;
	name=n;
	}
		
	void display()
	{
		System.out.println("id value :"+id+" name value :"+name);
	}
	
	void callDisplay()
	{
		this.display();
		System.out.println("After display");
	}
	
	public static void main(String[] args)
	{
		InvokeMethodUsingThis ce1=new InvokeMethodUsingThis(12, "Dinesh");
		//ce1.display();
		
		ce1.callDisplay();
	}

}