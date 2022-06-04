package files;

import java.io.*;

public class ReadWriteWithByteStreams {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream in=null;
		FileOutputStream out=null;
		
		try
		{
		
		in=new FileInputStream("C:\\Users\\dg185171\\Desktop\\ColdStart.txt");
		out=new FileOutputStream("C:\\Users\\dg185171\\Desktop\\ColdStart2.txt");
		
		//File f=new File("");
		
		int c;
		String s=null;
		while((c=in.read())!=-1)
		{
			s+=(char)c;
			//System.out.println(c);
			out.write(c);
		}
		
		System.out.println("String Value:  "+ s);		
		
		}
		catch(Exception e) {
			
			//System.out.println(e);
		}
		finally {
			
			if(in!=null) {
				in.close();
			}
			if(out!=null) {
				out.close();
			}
			
			System.out.println("successfully written all the lines using Byte Streams");
		}
	}	
}
