package multithreading;

import java.io.IOException;

public class RunTimeClass {
	
	public void openNotePad() throws IOException, InterruptedException {
		Process p=Runtime.getRuntime().exec("notepad");
		Thread.sleep(2000);
		p.destroy();
	}
	
	public void displayPrcesses() {
		System.out.println(Runtime.getRuntime().availableProcessors());
	}
	
	public static void main(String[] args) throws IOException, InterruptedException	
	{
		RunTimeClass rtc=new RunTimeClass();
		
		//rtc.openNotePad();
		rtc.displayPrcesses();
	}

}
