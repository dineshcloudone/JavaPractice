package testngTutorials;

import java.util.*;
import org.testng.TestNG;

public class FailedTestRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TestNG runner=new TestNG();
		List<String> list=new ArrayList<String>();
		
		list.add("C:\\Dinesh\\Java\\Eclipse_Projects\\Selenium_Practice\\TestNGProject_MukeshOtwani\\test-output\\testng-failed.xml");
		runner.setTestSuites(list);
		runner.run();
		
	}

}
