package testngTutorials;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class FactoryAnnotationPractice {
	
	
	int param;	
	
	public FactoryAnnotationPractice(int param) {
		// TODO Auto-generated constructor stub
		this.param=param;
	}
	
	@Test
	public void f1()
	{
		System.out.println("function 1 param :"+ param);
	}

	@Test
	public void f2()
	{
		System.out.println("function 2 param :"+ param);
	}
	
	@Test
	public void f3()
	{
		System.out.println("function 3 param :"+ param);
	}
}
