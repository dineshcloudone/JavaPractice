package this_practice;

class B{
	
	B(){		
		
		System.out.println("Empty constructor");		
	}
	
	B(int x){
		this();
		System.out.println("Parameterized constructor : "+x);
	}
}

public class Invoke_Parameterized_Constructor {
	
	public static void main(String[] args) {
		
		B b=new B(6);
		
		
	}
}
