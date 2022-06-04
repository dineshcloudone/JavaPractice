package parallel;

// This is testng runner

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/"//,"rerun:target/failedrerun.txt"
				}, 
		tags="not @sanity", // to skip the tests
		monochrome = true,
		glue = { "parallel" },
		features = { "src/test/resources/parallel" }
		
		//features = { "@target/failedrerun.txt" } to use in a separate runner file to re run the failed tests
)

public class ParallelRun extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)

	/*What is a DataProvider in TestNG? Similar to TestNG Parameters, DataProviders are a means to pass data to test scripts in TestNG.
	 *  Using DataProvider in TestNG, we can easily inject multiple values into the same test case. 
	 * It comes inbuilt in TestNG and is popularly used in data-driven frameworks.*/
	
	public Object[][] scenarios() {
		return super.scenarios();
	}
}