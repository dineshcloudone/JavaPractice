package multithreading;

//Example of interrupting a Thread that stops working

public class InterruptEx1 extends Thread {

	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println("task");
		}catch(InterruptedException e) {
			throw new RuntimeException("Thread Interrupted..."+e);
		}
	}
		
	public static void main(String[] args) {
		InterruptEx1 ie1=new InterruptEx1();
		ie1.start();
		ie1.interrupt();
	}
}
