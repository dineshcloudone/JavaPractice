package ThisKeywordExamples;

class B
{
	ThisConstructorArgument obj;
	B(ThisConstructorArgument obj)
	{
		this.obj=obj;
	}
	
	void display()
	{System.out.println(obj.data);}
}

class ThisConstructorArgument
{
	int data=10;
	ThisConstructorArgument()
	{
		B b=new B(this);
		b.display();
	}
	public static void main(String[] args)
	{
		ThisConstructorArgument a=new ThisConstructorArgument();
	}
}