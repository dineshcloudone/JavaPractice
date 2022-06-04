package ThisKeywordExamples;

public class ThisMethodArgument
{
	int i=0;
	void a(ThisMethodArgument tm)
	{
		System.out.println("I value is :"+ i);
		System.out.println(tm);
	}
	
	void b()
	{
		a(this);
	}
	
	public static void main(String[] args) 
	{
		new ThisMethodArgument().b();
	}
}