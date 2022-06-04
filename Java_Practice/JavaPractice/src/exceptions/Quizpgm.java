package exceptions;

public class Quizpgm {
	
	public static void main(String[] args) {
		int i=0;
		/*
		 * try {
		 * 
		 * //JVM control goes to finally block and then comes back to try block return;
		 * 
		 * 
		 * } finally {
		 * 
		 * System.out.println("Finally "+ i);
		 * 
		 * }
		 */
		
		try {
			String s="dinesh";
			s.length();
		}catch(Exception e) {
			return;
			//e.getMessage();
		}
		finally {
			
		}
	}
}