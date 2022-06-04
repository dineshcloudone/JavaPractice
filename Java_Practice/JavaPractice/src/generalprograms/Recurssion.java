package generalprograms;

public class Recurssion {
	
	static int n1=0,n2=1,n3;
	
	static int factorial_recursion(int n){
		
		if(n==1) {
			return 1;
		}
		else {
			
			return(n*factorial_recursion(n-1));
		}
		
	}
	
	static void print_fibonacci(int n) {
		
		
		//System.out.println(n1+" "+n2);
		
		if(n>0) {
			n3=n1+n2;
			n1=n2;
			n2=n3;
			
			System.out.print(" "+n3);
			print_fibonacci(n-1);
		}
	}
	
	
	public static void main(String[] args) {
		
		int a=10;
		Integer i=Integer.valueOf(a);
		Integer A=a;
		
		//System.out.println("Factorial Value : "+factorial_recursion(5));
		//System.out.print(0+" "+1);
		//print_fibonacci(15);
		
		System.out.println(a+" "+i+" "+A);
	}

}
