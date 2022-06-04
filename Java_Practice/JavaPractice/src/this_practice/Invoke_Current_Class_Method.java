package this_practice;

class A{
	
	A(){
		
		System.out.println("Constructor ");
	}
	
	void m() {
		System.out.println("hello m");
	}
	
	void n() {		
		
		System.out.println("hello n");		
		this.m();
	}
}


public class Invoke_Current_Class_Method {
	
	public static void main(String[] args) {
		
		A a=new A();
		
		a.n();
	}

}
