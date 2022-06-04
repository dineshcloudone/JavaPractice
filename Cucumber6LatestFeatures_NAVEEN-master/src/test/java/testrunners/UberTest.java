package testrunners;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/AppFeatures/Uber.feature"},
		glue = {"stepdefinitions", "MyHooks"},
		tags = "@Smoke", // "@Regression or @Smoke"  "@Regression and @Smoke" "not @Prod" "@All"
		plugin = {"pretty",
					"json:target/MyReports/report.json",
					"junit:target/MyReports/report.xml"
					},
		//publish = true,
		monochrome = true,
		dryRun = false
		//strict = true
		
		)

public class UberTest {

}

