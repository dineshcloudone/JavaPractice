package regexpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class RegularExp2 {

	public static void main(String[] args) throws Exception {

		MainReg re = new MainReg();

		String d="dinesh";		
		
		//System.out.println("String cointains only alphabets :"+re.stringContainsOnlyAphabets("dinesh"));

		//System.out.println("String removing white spaces :" + re.removeWhiteSpaces("this is dinesh"));
		
		re.readMobileNoFile();
	}
}

class MainReg {

	public boolean stringContainsOnlyAphabets(String s) {

		return ((!s.equals("")) && (s != null) && (s.matches("^[a-zA-Z]*")));
	}

	public String removeWhiteSpaces(String s) {

		Pattern p = Pattern.compile("[\\s]");
		Matcher m = p.matcher(s);
		
		return m.replaceAll("");

	}
	
	public void readMobileNoFile() throws Exception {
		
		BufferedReader br=new BufferedReader(new FileReader("C:\\Dinesh\\Receipt_Viewer_Fix.txt"));
		
		String line;

		while((line=br.readLine())!=null) {
			//Matcher m=Pattern.compile("(0/91)?[7-9][0-9]{9}").matcher(line);			
			Matcher m=Pattern.compile("^(0/91)?[978][0-9]{9}").matcher(line);
			while(m.find()) {
				System.out.println(" mob no : "+m.group());// to find the mob no from a file
				System.out.println(" mob no : "+m.group().contains("74164022"));// to find given mob no is valid or not				
			}
			
		}
	}

}
