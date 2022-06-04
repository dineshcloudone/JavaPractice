package StaticKeywordExamples;

class StaticBlock
{
	static int i;
	static {System.out.println("static block invoked"); i=2;}
	
	StaticBlock() {
		// TODO Auto-generated constructor stub
	System.out.println("Constructor Invoked "+i);
	}
		
	void test()
	{
		System.out.println("test method is invoked : " + i);		
	}
	
	public static void main(String[] args)
	{
		StaticBlock sb=new StaticBlock();
		sb.test();
	}
	
}