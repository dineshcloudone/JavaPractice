package ConstructorsPractice;


public class ConstructorOverloading
{
	int id;
	String name;
	float age;
	
	ConstructorOverloading(int i,String nm)
	{
		id=i;
		name=nm;
	}
	ConstructorOverloading(int i,String nm, float ag)
	{
		id=i;
		name=nm;
		age=ag;
	}
	
	void display()
	{
		System.out.println(" id :"+id+" name :"+name+" age "+age);
	}
		
	public static void main(String[] args)
	{
		ConstructorOverloading co=new ConstructorOverloading(12, "Dinesh1");
		ConstructorOverloading co2=new ConstructorOverloading(123, "Dinesh2", 25);
		
		co.display();
		co2.display();
	}
}