package multithreading;

public class StartThreadTwice extends Thread{
	public void run()
	{
		System.out.println("Running a thread... ");
	}
	public static void main(String[] args) {
		StartThreadTwice stt=new StartThreadTwice();
		stt.start();
		stt.start();// throws IllegalThreadStateException
	}

}
