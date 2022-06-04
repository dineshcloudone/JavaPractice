package files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//https://www.techiedelight.com/how-to-read-a-file-using-bufferedreader-in-java/


public class FileUsingFileReader {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		BufferedReader br=null;
		BufferedWriter bw=null;
		
		try {
						
			FileReader fr=new FileReader("C:\\Users\\dg185171\\Desktop\\ColdStart.txt");
			br=new BufferedReader(fr);
			
			//opens the file in append mode
			
			//C:\Users\dg185171\Desktop
			
			FileWriter fw=new FileWriter("C:\\Users\\dg185171\\Desktop\\ColdStart2.txt",true);
			
			//FileWriter fw=new FileWriter("C:\\Users\\dg185171\\Desktop\\ColdStart2.txt");
			bw=new BufferedWriter(fw);
			
			StringBuilder sb=new StringBuilder();
			String line=br.readLine();
			
			
			while(line!=null) {
				sb.append(line+"\n");
				bw.write(line+'\n');
				line=br.readLine();				
			}
			
			
			
			System.out.println("text in string builder : \n"+sb.toString());
			
		}catch(IOException ioe) {
			System.out.println(ioe);
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			try {
				
				if(br!=null) {
				br.close();
				}
				if(bw!=null) {
				bw.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("successfully completed writing text to the file");
			
		}
		
	}

}
