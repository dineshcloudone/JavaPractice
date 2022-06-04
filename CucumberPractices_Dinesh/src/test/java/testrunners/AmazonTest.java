package testrunners;

import org.junit.runner.RunWith;


import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;


/*
 * <dependency>
    		<groupId>io.cucumber</groupId>
    		<artifactId>cucumber-junit</artifactId>
    		<version>2.1.0</version>
    		<scope>test</scope>
		</dependency>
		
		<dependency>
    		<groupId>io.cucumber</groupId>
    		<artifactId>cucumber-java</artifactId>
    		<version>2.1.0</version>
    		<scope>test</scope>
		</dependency>
 */


@RunWith(Cucumber.class)
@CucumberOptions(
		features= {"src/test/resources/AppFeatures"},
		glue= {"stepdefinition"},
		plugin= {"pretty"},
		monochrome=true
		)

public class AmazonTest {

}
