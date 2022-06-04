
public class Command_Line {
	
	public static void main(String[] args) {
		
		for(String s:args) {
			System.out.print(s);
		}
		
		System.out.println();
		
		for(int i=0;i<args.length;i++) {
			
			System.out.println("Command Line Args "+args[i]);
		}
	}

}
