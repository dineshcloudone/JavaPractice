package multithreading;

public class RunnableInterface implements Runnable{
	public void run() {
		System.out.println("Running thread... ");
	}
	
	public static void main(String[] args) {
		RunnableInterface ri=new RunnableInterface();
		Thread t=new Thread(ri);
		t.start();
	}
}
