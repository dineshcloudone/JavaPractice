package exceptions;

import java.io.FileReader;
import java.io.IOException;

import files.FileRead_WithoutBuffer;

// Note : In a try-with-resources statement, catch or finally block executes after closing of the declared resources.

public class Try_With_Resource {
	
	static int i;	
	
	public static void main(String[] args) {
		
    try(FileReader fr=new FileReader("C:\\Users\\dg185171\\Desktop\\ColdStart.txt")) {			 
			
			while((i=fr.read())!=-1) {
				
				System.out.print((char)i);
				
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
