class AZ {
	AZ() {
		System.out.println("parent class constructor invoked");
	}
}

class Instance_Initialize_Block extends AZ {
	
	static {System.out.println("static block");}
	
	Instance_Initialize_Block() {
		super();
		System.out.println("child class constructor invoked");
	}

	Instance_Initialize_Block(int a) {
		super();
		System.out.println("child class constructor invoked " + a);
	}

	{
		System.out.println("instance initializer block is invoked");
	}

	public static void main(String args[]) {
		Instance_Initialize_Block b1 = new Instance_Initialize_Block();
		Instance_Initialize_Block b2 = new Instance_Initialize_Block(10);
	}
}