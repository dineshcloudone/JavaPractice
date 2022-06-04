package this_practice;

class A3{
	
	A3 getA3() {
		
		return this;
	}
	
	void msg() {
		System.out.println("Hello Java");
	}
}

public class Return_As_Current_Class_Instance {
	
	public static void main(String[] args) {
		new A3().getA3().msg();
	}

}
