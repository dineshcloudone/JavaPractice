package generalprograms;

public class Test {
	
	public void factorial() {
		
		int n=6;
		int temp=1;
		for(int i=1;i<=n;i++) {
			temp=temp*i;
		}
		
		System.out.println("fact value :"+temp);
	}
	
	public void palindrome() {
		
		int n=838,r=0,p=0,temp=n;
		
		while(n>0) {
			r=n%10;
			p=p*10+r;
			n=n/10;
		}
		
		if(temp==p) {
			System.out.println("palindrome");
		}
		else {System.out.println("Not Palindrome");
		}		
	}
	
	public void fibonacciSeries(int n) {
		int n1=0,n2=1,n3=0;
		for(int i=0;i<n;i++) {
			n3=n1+n2;
			n1=n2;
			n2=n3;
			
			System.out.print(" "+n3);
		}
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Test t=new Test();
		//t.fibonacciSeries(13);
		t.palindrome();

	}

}
