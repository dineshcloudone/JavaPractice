package ConstructorsPractice;

class CopyConstructorEx1
{
	int id;
	String name;
	
	CopyConstructorEx1(int i,String nm) {
		// TODO Auto-generated constructor stub
	id=i;
	name=nm;
	}
	
	CopyConstructorEx1(CopyConstructorEx1 cc1)
	{
		id=cc1.id;
		name=cc1.name;
	}
	
	void display()
	{
		System.out.println("id: "+id+"name: "+name);
	}

	public static void main(String[] args)
	{
		CopyConstructorEx1 cc=new CopyConstructorEx1(12,"Dinesh");
		cc.display();
		
		CopyConstructorEx1 cc2=new CopyConstructorEx1(cc);
		cc2.display();
		
	}
	

}



