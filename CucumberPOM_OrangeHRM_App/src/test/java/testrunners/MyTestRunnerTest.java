package testrunners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/com/features"},
		glue = {"stepdefinitions", "AppHooks"},
		plugin = {"pretty"},
				//"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
				//"timeline:test-output-thread/"}//,rerun:target/failedrerun.txt //(it is testng plugin, it will work for both junit and testng)
		
		monochrome=true,
		tags="@All"		
		)

public class MyTestRunnerTest {

}
