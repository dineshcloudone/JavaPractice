package multithreading;

class Table_1 {
	void printTable(int n) {
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

class MyThread1 extends Thread{
	Table_1 nst;
	MyThread1(Table_1 t){
		this.nst=t;
	}
	
	public void run() {
		nst.printTable(5);		
	}
}

class MyThread2 extends Thread{
	Table_1 nst;
	MyThread2(Table_1 nst){
		this.nst=nst;
	}
	
	public void run() {
		nst.printTable(10);
	}
	
}


public class NoSynchronizationEx{
	public static void main(String[] args) {
		Table_1 nst=new Table_1();
		MyThread1 mt1=new MyThread1(nst);
		MyThread2 mt2=new MyThread2(nst);
		
		mt1.start();
		mt2.start();
	}
}
