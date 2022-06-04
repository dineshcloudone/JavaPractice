package this_practice;

public class Proving_This_Keyword {
	
	void method() {
		System.out.println(this);
	}
	
	public static void main(String[] args) {
		
		Proving_This_Keyword ptk=new Proving_This_Keyword();
		
		System.out.println(ptk);
		ptk.method();
		
		
		
	}

}
