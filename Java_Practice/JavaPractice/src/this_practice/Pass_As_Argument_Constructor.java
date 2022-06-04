package this_practice;


class A2{
	
	Pass_As_Argument_Constructor obj;
	
	A2(Pass_As_Argument_Constructor obj){
		this.obj=obj;
	}
	
	void display() {
		System.out.println(obj.data);
	}
	
}

public class Pass_As_Argument_Constructor {

	int data=10;
	public Pass_As_Argument_Constructor() {
	A2 a2=new A2(this);
	a2.display();
	}
	
	public static void main(String[] args) {
		Pass_As_Argument_Constructor paa=new Pass_As_Argument_Constructor();
	}
}
