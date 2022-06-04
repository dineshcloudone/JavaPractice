
public class Without_Main_Also_FinalizeEx {

	static {
		System.out.println("Static Block");
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * Without_Main_Also_FinalizeEx wm=new Without_Main_Also_FinalizeEx();
	 * System.out.println("Hash Code of object : "+wm.hashCode()); wm=null;
	 * System.gc(); }
	 * 
	 * protected void finalize() {
	 * System.out.println("called the finalize() method"); }
	 */

}
