package files;

import java.io.*;

public class FileWrite_WithoutBuffer {
	
	FileWriter fr;
	
	void writeToFile() {
		
		try {
			
			fr=new FileWriter("C:\\Users\\dg185171\\Desktop\\ColdStart2.txt");
			
			fr.write("this is dinesh from class FileWrite_WithoutBuffer ");
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		finally {
			
			try {
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Successfully written text to file");
		}
		
	}
	
	public static void main(String[] args) {
		
		FileWrite_WithoutBuffer fw=new FileWrite_WithoutBuffer();
		fw.writeToFile();
	}

}
