package multithreading;

class Table_SS{
	synchronized static void printTable(int n){
		for(int i=0;i<5;i++) {
			System.out.println(i*n);
			try {
				Thread.sleep(500);
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
}

class MyThread1_SS extends Thread{
	
	public void run() {
		Table_SS.printTable(5);
	}
}

class MyThread2_SS extends Thread{
	public void run() {
		Table_SS.printTable(6);
	}
}

class MyThread3_SS extends Thread{
	public void run() {
		Table_SS.printTable(7);
	}
}

class MyThread4_SS extends Thread{
	public void run() {
		Table_SS.printTable(8);
	}
}

public class StaticSynchronization {

	public static void main(String[] args) {
		MyThread1_SS mt1=new MyThread1_SS();
		MyThread2_SS mt2=new MyThread2_SS();
		MyThread3_SS mt3=new MyThread3_SS();
		MyThread4_SS mt4=new MyThread4_SS();
		
		mt1.start();
		mt2.start();
		mt3.start();
		mt4.start();
				
	}
	
}
