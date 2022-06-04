package ThisKeywordExamples;

public class ThisReturnInstance
{
	ThisReturnInstance a()
	{
		return this;
	}
	
	void display()
	{
		System.out.println("this was returned");
	}
	
	public static void main(String[] args)
	{	
		new ThisReturnInstance().a().display();
	}
}