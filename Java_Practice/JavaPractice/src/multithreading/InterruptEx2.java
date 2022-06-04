package multithreading;

public class InterruptEx2 extends Thread{

	public void run() {
		try{
			Thread.sleep(1000);
			System.out.println("task");
		}catch(InterruptedException e) {
			System.out.println("Exception Handled : "+e);
		}
		
		System.out.println("Thread is running...");
	}
	
	public static void main(String[] args) {
		InterruptEx2 ie2=new InterruptEx2();
		ie2.start();
		ie2.interrupt();
	}
	
}
