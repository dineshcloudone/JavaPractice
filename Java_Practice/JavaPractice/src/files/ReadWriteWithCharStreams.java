package files;

import java.io.*;
	
public class ReadWriteWithCharStreams {

	public static void main(String[] args) throws IOException{
		
	FileReader in=null;
	FileWriter out=null;
	//BufferedReader br=null;
	//BufferedWriter bw=null;
	
	try {
		
		in=new FileReader("C:\\Users\\dg185171\\Desktop\\ColdStart.txt");
		//br=new BufferedReader(in);
		
		out=new FileWriter("C:\\Users\\dg185171\\Desktop\\ColdStart2.txt");
		//bw=new BufferedWriter(out);
		
		String k="dinesh";
		
		
		int c;
		String s="";
		while((c=in.read())!=-1)
		{
			s+=c;
			out.write(c);
		}
		System.out.println("String value: "+s);
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	finally{
		
			in.close();
			out.close();
		
			System.out.println("successfully written all the lines using char Streams");
	}
	
	}
}
