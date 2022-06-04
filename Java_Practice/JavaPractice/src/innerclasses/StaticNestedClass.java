package innerclasses;

public class StaticNestedClass {

	static int data = 50;

	static class Nested {
		void msg() {
			System.out.println("Accessing outer class data : " + data);
		}
	}
	
	public static void main(String[] args)
	{
		StaticNestedClass.Nested nstd=new StaticNestedClass.Nested();
		nstd.msg();
	}

}
