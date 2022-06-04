package test.common;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Logging {
	
	public static ExtentReports report;
	public static ExtentTest logger;
	protected static ManageBrowser browser;
	/*
	 * #############################################################################
	 * ######################### Method Name: LogEvent Description: Method to Log
	 * Events at run time to the log file Author: Testing Masters Organization:
	 * Testing Master Technologies Date Created: 10-Aug-2016
	 * #############################################################################
	 * #########################
	 */
	
	public static void LogEvent(String Status, String Description) {
		
		if (Status.equalsIgnoreCase("pass")) {
			logger.log(LogStatus.PASS, Description);
			System.out.println("\t " + "StepStatus:" + Status + "; StepDesciption:" + Description);
		} else if (Status.equalsIgnoreCase("fail")) {
			logger.log(LogStatus.FAIL, Description);
			System.out.println("\t " + "StepStatus:" + Status + "; StepDesciption:" + Description);
		} else if (Status.equalsIgnoreCase("warning")) {
			logger.log(LogStatus.WARNING, Description);
			System.out.println("\t " + "StepStatus:" + Status + "; StepDesciption:" + Description);
		} else if (Status.equalsIgnoreCase("info")) {
			logger.log(LogStatus.INFO, Description);
			System.out.println("\t " + "StepStatus:" + Status + "; StepDesciption:" + Description);
		}
	}
	
	public static void LogEventWithScreeshot(String Status, String Description) {
		//WebLibrary.wait(0.5);
		String ScreenShotPath = getscreenshot(browser.getCurrentWebDriver());
		String scn = logger.addScreenCapture(ScreenShotPath);
		if (Status.equalsIgnoreCase("pass")) {
			logger.log(LogStatus.PASS, Description + scn);
			System.out.println("\t " + "StepStatus:" + Status + "; StepDesciption:" + Description);
		} else if (Status.equalsIgnoreCase("FAIL")) {
			logger.log(LogStatus.FAIL, Description + scn);
			System.out.println("\t " + "StepStatus:" + Status + "; StepDesciption:" + Description);
		} else if (Status.equalsIgnoreCase("warning")) {
			logger.log(LogStatus.WARNING, Description + scn);
			System.out.println("\t " + "StepStatus:" + Status + "; StepDesciption:" + Description);
		} else if (Status.equalsIgnoreCase("info")) {
			logger.log(LogStatus.INFO, Description + scn);
			System.out.println("\t " + "StepStatus:" + Status + "; StepDesciption:" + Description);
		}
	}

	/*
	 * #############################################################################
	 * ######################### Method Name: getscreenshot Description: Method to
	 * Capture Screenshot of the current driver instance Author: Testing Masters
	 * Organization: Testing Master Technologies Date Created: 10-Aug-2016
	 * #############################################################################
	 * #########################
	 */
	public static String getscreenshot(WebDriver driver) {
		try {
			String ScreenshotName;
			DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance();
			String DateTimeStamp = dateTimeInstance.format(Calendar.getInstance().getTime());
			DateTimeStamp = DateTimeStamp.replace(",", "");
			DateTimeStamp = DateTimeStamp.replace(" ", "_");
			DateTimeStamp = DateTimeStamp.replace(":", "_");
			ScreenshotName = "Screenshot" + "_" + DateTimeStamp;
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String Dest = System.getProperty("user.dir") + System.getProperty("file.separator")+"results"+"ScreenshotName" + ".png";
			File destination = new File(Dest);
			FileUtils.copyFile(source, destination);
			return Dest;
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public void startExtentReport() {		
		logger=report.startTest("testing");
	}
	
}
