package accessspecifiers.subpackage2;

import accessspecifiers.subpackage1.*;

public class ProtectedEx2 extends ProtectedEx1{
	
	ProtectedEx2(){
		test_protected();
	}
		
	public static void main(String[] args) {
		
		ProtectedEx1 pe=new ProtectedEx1();
		
		//pe.test();
		
		pe.test_protected();
		
	}

}
