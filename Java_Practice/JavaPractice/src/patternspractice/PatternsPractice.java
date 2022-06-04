package patternspractice;

import java.util.Scanner;

//Patterns Link : https://www.javatpoint.com/how-to-print-pattern-in-java

public class PatternsPractice {

	static void printStar_righttriangle(int n) {
		
		for(int i=0;i<n;i++) {
			
			for(int j=i;j>0;j--) {
				
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
	static void mirror_triangle(int n) {
		
		for(int i=1;i<=n;i++) {
						
			for(int j=n-i;j>0;j--) {
				System.out.print(" ");				
			}
			
			for(int j=1;j<=i;j++) 
			{ 
				System.out.print("*"); 			  
			}
			System.out.println();
		}
	}
	
	static void diamond() {
		int row, i, j, space = 1;  
		System.out.print("Enter the number of rows you want to print: ");  
		Scanner sc = new Scanner(System.in);  
			
		row = sc.nextInt();  
		space = row - 1;  
		for (j = 1; j<= row; j++)  
		{  
			for (i = 1; i<= space; i++)  
			{  
			System.out.print(" ");  
			}  
			space--;  
			for (i = 1; i <= 2 * j - 1; i++)  
			{  
			System.out.print("*");  
			}  
			System.out.println("");  
		}
				//========================================
		
		space = 1;  
		for (j = 1; j<= row - 1; j++)  
		{  
			for (i = 1; i<= space; i++)  
			{  
			System.out.print(" ");  
			}  
			space++;  
			for (i = 1; i<= 2 * (row - j) - 1; i++)  
			{  
			System.out.print("*");  
			}  
			System.out.println("");  
		}  
	}
	
	public static void main(String[] args) {
		//mirror_triangle(5);
		//diamond();
		//printStar_righttriangle(6);
	}
	
}
