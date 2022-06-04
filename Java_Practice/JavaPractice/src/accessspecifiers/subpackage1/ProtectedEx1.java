package accessspecifiers.subpackage1;

public class ProtectedEx1 {
	
	protected static void test_protected() {
		System.out.println("protected form test method of package accessspecifiers.subpackage2");
	}

	void test_default() {
		System.out.println("default form test method of package accessspecifiers.subpackage2");
	}
	
	public void test_public() {
		System.out.println("public form test method of package accessspecifiers.subpackage2");
	}
}
