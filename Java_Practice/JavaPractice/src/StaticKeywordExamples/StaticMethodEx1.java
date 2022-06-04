package StaticKeywordExamples;

public class StaticMethodEx1
{
	int rollno;
	String name;
	static String college="ABC";
	
	//static {college="xyz";}
	
	static void change()
	{
		college="DEF";
	}
	
	StaticMethodEx1(int rn,String nm)
	{
		rollno=rn;
		name=nm;
		college="abc";
	}
	
	void display()
	{
		
		System.out.println("Roll no: "+rollno+" name : "+name+" college : "+college);
	}
	
	public static void main(String[] args)
	{
		//StaticMethodEx1.change();
		
		StaticMethodEx1 sm1=new StaticMethodEx1(1,"Dinesh1");
		StaticMethodEx1 sm2=new StaticMethodEx1(2,"Dinesh2");		
		
		sm1.display();
		
		sm1.change();
		
		sm2.display();
		
		
		System.out.println(" ");
	}
}