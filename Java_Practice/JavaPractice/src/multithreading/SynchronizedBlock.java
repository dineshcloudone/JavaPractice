package multithreading;

/*
 * URL : http://tutorials.jenkov.com/java-concurrency/synchronized.html
 * Synchronized Static Methods(Explanation for this in synchronized block):
 * 
Static methods are marked as synchronized just like instance methods using the synchronized keyword. Here is a Java synchronized static method example:

  public static synchronized void add(int value){
      count += value;
  }
Also here the synchronized keyword tells Java that the method is synchronized.

Synchronized static methods are synchronized on the class object of the class the synchronized static method belongs to. Since only one class object exists in the Java VM per class, only one thread can execute inside a static synchronized method in the same class.

If the static synchronized methods are located in different classes, then one thread can execute inside the static synchronized methods of each class. One thread per class regardless of which static synchronized method it calls.
 */

class Table_SB{
	void printTable(int n) {
		synchronized (this) {
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
}

class MyThread1_SB extends Thread{
	
	Table_SB t;
	MyThread1_SB(Table_SB t) {
		// TODO Auto-generated constructor stub
		this.t=t;
	}
	
	public void run() {
		t.printTable(5);
	}
	
}

class MyThread2_SB extends Thread{
	
	Table_SB t;
	MyThread2_SB(Table_SB t) {
		// TODO Auto-generated constructor stub
		this.t=t;
	}
	
	public void run() {
		t.printTable(100);
	}
	
}

public class SynchronizedBlock {
	
	public static void main(String[] args) {
		Table_SB t=new Table_SB();
		
		MyThread1_SB sb1=new MyThread1_SB(t);
		MyThread2_SB sb2=new MyThread2_SB(t);
		
		sb1.start();
		sb2.start();
	}

}
