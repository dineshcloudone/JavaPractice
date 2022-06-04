package innerclasses;

public class LocalInnerClass {

	private int data = 30;

	void test() {
		class Local {
			void msg() {
				
				System.out.println("Accessing outer class data :"+data);
			}
		}
		Local l=new Local();
		l.msg();
	}

	public static void main(String[] args) {
		LocalInnerClass lic=new LocalInnerClass();
		lic.test();

	}

}
