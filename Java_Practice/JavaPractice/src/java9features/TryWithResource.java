package java9features;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class TryWithResource {

	private void printFileData() {

		String filepath = "C:\\Users\\dg185171\\Desktop\\ColdStart.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {

			StringBuilder sb = new StringBuilder();

			String s = br.readLine();

			while (s != null) {
				sb.append(s);
				sb.append("\n");
				s=br.readLine();
			}
			
			System.out.println("Text read from the file : "+ sb);

		} catch (Exception e) {

		}
	}

	public static void main(String[] args) {
		
		TryWithResource twr=new TryWithResource();
		twr.printFileData();
	}
}
