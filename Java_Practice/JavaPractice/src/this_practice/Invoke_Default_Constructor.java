package this_practice;

class C{
	
	C(){
		this(5);
		System.out.println("default constructor");
	}
	C(int a){
		System.out.println("parameterized constructor :"+a);
	}

}

public class Invoke_Default_Constructor {

	public static void main(String[] args) {
		
		C c=new C();
		
	}
}
