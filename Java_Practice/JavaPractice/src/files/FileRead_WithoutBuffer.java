package files;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

public class FileRead_WithoutBuffer {
	
	int i;
	FileReader fr;
	
	void readfile() {
		
		try {
			 fr=new FileReader("C:\\Users\\dg185171\\Desktop\\ColdStart.txt");
			
			while((i=fr.read())!=-1) {
				
				System.out.println((char)i);
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
			try {
				
				fr.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		FileRead_WithoutBuffer fr_wb=new FileRead_WithoutBuffer();
		fr_wb.readfile();
		
	}

}
