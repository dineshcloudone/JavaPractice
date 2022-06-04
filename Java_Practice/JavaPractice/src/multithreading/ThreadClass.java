package multithreading;

public class ThreadClass extends Thread{

	public void run() {
			System.out.println("Running thread...");
	}
	public static void main(String[] args) {
		ThreadClass tc1=new ThreadClass();
		ThreadClass tc2=new ThreadClass();
		
		tc1.start();
		tc2.start();
		
	}
}
