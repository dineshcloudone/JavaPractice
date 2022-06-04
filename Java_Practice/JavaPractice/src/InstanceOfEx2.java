
class Animal{
	
	/* Constructor should not be final as it can't be inherited
	 * final Animal() {
	 * 
	 * }
	 */
	
	void test1() {
		System.out.println("test method1");
	}
}

public class InstanceOfEx2 extends Animal {
	
	
	  void test2() { System.out.println("test2 method"); }
	 
	
	public static void main(String[] args) {
		
		//UsingInstanceOfEx2 uio=new UsingInstanceOfEx2();
		//UsingInstanceOfEx2 uio=null;
		
		Animal a=new InstanceOfEx2();
		
		a.test1();
		
		System.out.println(a instanceof Animal);
		
		System.out.println(a instanceof InstanceOfEx2);
		
	}

}
