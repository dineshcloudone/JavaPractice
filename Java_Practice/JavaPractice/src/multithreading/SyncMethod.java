package multithreading;

class Table{
	synchronized public void printTable(int n) {
		for(int i=1;i<=5;i++) {
			System.out.println(i*n);
			try {
				Thread.sleep(4000);
			}catch(Exception e) {
				System.out.println("Exception handled");
			}
			
		}
	}
}

public class SyncMethod {

	public static void main(String[] args) {
		
		 Table obj=new Table();
		
		//Table obj2=new Table();
		
		Thread t1=new Thread() {
			public void run()  {
				obj.printTable(5);
			}
		};
		
		Thread t2=new Thread() {
			public void run() {
				obj.printTable(10);
			}
		};
		
		Thread t3=new Thread() {
			public void run() {
				obj.printTable(20);
			}
		};
		
		Thread t4=new Thread() {
			public void run() {
				obj.printTable(30);
			}
		};
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
	
}
