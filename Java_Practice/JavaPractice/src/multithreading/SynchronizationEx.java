package multithreading;

class Table_2 {
	 synchronized static void printTable(int n) {
		for(int i=1;i<=5;i++) {
			System.out.println(n*i);
			try {
			Thread.sleep(400);
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
}

class MyThread3 extends Thread{
	Table_2 nst;
	MyThread3(Table_2 t){
		this.nst=t;
	}
	
	public void run() {
		nst.printTable(5);		
	}
}

class MyThread4 extends Thread{
	Table_2 nst;
	MyThread4(Table_2 nst){
		this.nst=nst;
	}
	
	public void run() {
		nst.printTable(100);
	}
	
}


public class SynchronizationEx{
	public static void main(String[] args) {
		Table_2 nst=new Table_2();
		MyThread3 mt1=new MyThread3(nst);
		MyThread4 mt2=new MyThread4(nst);
		
		mt1.start();
		mt2.start();
	}
}
