package testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/AppFeatures/registration.feature"},
		glue = {"stepdefinitions"}, //glue={"stepdefinitions","MyHooks"} both stepdefinitions and MyHooks are packages
		plugin = {"pretty",
					"json:target/MyReports/report.json",
					"junit:target/MyReports/report.xml"
					},
		monochrome = true,
		dryRun = false // to check any step definition file is missing for the feature file
		//strict = true // it is deprecated , it actually means that ignore if any step definition file is missing
		
		)
public class UserRegTest {

}
