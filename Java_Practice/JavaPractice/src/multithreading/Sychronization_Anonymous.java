package multithreading;

class Table3{
	synchronized void printTable(int n) {
		for(int i=1;i<=5;i++) {
			System.out.println(i*n);
			try {
				Thread.sleep(500);
			}catch(Exception e) {
				System.out.println(e);
			}
			
		}
	}	
}


public class Sychronization_Anonymous {
	
	public static void main(String[] args) {
		Table3 obj=new Table3();
		Thread t1=new Thread() {
			public void run() {
				obj.printTable(5);
			}
		};
		
		Thread t2=new Thread() {
			public void run() {
				obj.printTable(100);
			}
		};
		
		t1.start();
		t2.start();
	}

}
