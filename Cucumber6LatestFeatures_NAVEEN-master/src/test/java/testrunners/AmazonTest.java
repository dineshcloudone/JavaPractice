package testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/AppFeatures/Search.feature"},
		glue = {"stepdefinitions", "MyHooks"}, //stepdefinitions and MyHooks are packages
		tags = "@Smoke or @Regression",
		plugin = {"pretty"},
		monochrome = true
		)
public class AmazonTest {

}
