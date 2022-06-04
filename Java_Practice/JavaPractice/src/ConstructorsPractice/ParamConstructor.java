package ConstructorsPractice;

//Constructor Ex1
class ParamConstructorEx1
{
	int id;
	String name;
	
	ParamConstructorEx1(int i,String n) {
		// TODO Auto-generated constructor stub	
	id=i;
	name=n;
	}
		
	void display()
	{
		System.out.println("id value :"+id+" name value :"+name);
	}
	
	public static void main(String[] args)
	{
		ParamConstructorEx1 ce1=new ParamConstructorEx1(12, "Dinesh");
		ce1.display();
	}

}