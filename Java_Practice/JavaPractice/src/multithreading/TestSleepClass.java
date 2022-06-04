package multithreading;

public class TestSleepClass extends Thread {

	public void run() {
		for(int i=1;i<5;i++) {
			try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}  
			System.out.println("Running a thread... : "+i );			
		}
	}
		
		public static void main(String[] args) {
			TestSleepClass tsc1=new TestSleepClass();
			tsc1.setName("TOne");
			TestSleepClass tsc2=new TestSleepClass();
			tsc2.setName("TTwo");			
			
			tsc1.start();
			tsc2.start();
			
		}
}	
	

