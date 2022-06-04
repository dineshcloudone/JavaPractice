package java8newfeatures;

interface Test1{
	public void testInstanceMethodReference();
}

public class InstanceMethodReference {
	
	public void instanceTest() {
		
		System.out.println("Testing the instance method");
	}
	
	public static void main(String[] args) {
		
		InstanceMethodReference imr=new InstanceMethodReference();
		
		//Test1 it=imr::instanceTest();
		
		
	}

}
