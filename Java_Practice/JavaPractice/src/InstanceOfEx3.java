interface Printable{}

class TestA1 implements Printable{
	
	public void a() {
		System.out.println("a method");
	}
}

class TestA2 implements Printable{
	
	public void a2() {
		System.out.println("a2 method");
	}
}

class Call{
	
	void invoke(Printable p) {
		if(p instanceof TestA1) {
			
			TestA1 ta1=(TestA1)p;
			
			ta1.a();
		}
		
		if(p instanceof TestA2) {
			
			TestA2 ta2=(TestA2)p;
			
			ta2.a2();
		}
		
	}
}

public class InstanceOfEx3 {

	public static void main(String[] args) {
		
		Printable p=new TestA2();
		
		Call c=new Call();
		
		c.invoke(p);
	}
}
