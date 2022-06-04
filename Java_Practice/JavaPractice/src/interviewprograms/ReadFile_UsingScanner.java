package interviewprograms;

import java.util.*;
import java.io.*;

public class ReadFile_UsingScanner {
	
	public static void main(String[] args) {
		
		Scanner s=null;
		
		String s2;
		
		try {
			
		System.out.println("Program starts");
		
		File f=new File("C:\\Dinesh\\vodafone.txt");
		
		s =new Scanner(f);
		
		int count=0;
		
		while(s.hasNextLine()) {
			count++;
			
			System.out.println("line no : "+count+" "+s.nextLine());
		}

		System.out.println("Total number of lines "+count);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			s.close();
		}
		
	}

}
