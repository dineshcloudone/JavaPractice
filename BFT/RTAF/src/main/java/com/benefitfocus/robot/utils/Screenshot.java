package com.benefitfocus.robot.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.python.core.PyString;
import org.python.core.PyNone;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;

@RobotKeywords
public class Screenshot {

	/**
	 * Instantiated Logging keyword bean
	 */
	@Autowired
	protected Logging logging;

	@Autowired
	protected ManageBrowser browser;

	// ##############################
	// Keywords
	// ##############################
	/*
	 * capturePageScreenshot(); }
	 */

	/**
	 * Author: Phani Srikar Ch
	 * 
	 * Take a screenshot with current timestamp of the current page and embed it
	 * into the log.<br>
	 * 
	 */
	@RobotKeyword
	public void capturePageScreenshot() {

		SimpleDateFormat format = new SimpleDateFormat("MMddyyyy_HHmmssSSS");
		PyString buildnumber =  (PyString) logging.getLoggingPythonInterpreter()
				.get().eval("BuiltIn().get_variable_value('${build}')");
		
		String filename = format.format(new Date());
		
		System.out.println("buildnumber : "+buildnumber);
		
		if (!buildnumber.asString().equalsIgnoreCase("nobuild")) {
			filename = "Build" + buildnumber.toString() + "_" + filename;
		}
		String logdirpath = logging.getLogDir().toString() + System.getProperty("file.separator")
				+ browser.configurations[0].toString();
		logdirpath = logdirpath.replace("\\pabot_results","").replace("/pabot_results","");
		File folder_path = new File(logdirpath);
		if (!folder_path.exists()) {
			folder_path.mkdir();
		}
		File path = new File(folder_path, normalizeFilename(filename));
		TakesScreenshot takesScreenshot = ((TakesScreenshot) browser
				.getCurrentWebDriver());
		if (takesScreenshot == null) {
			logging.warn("Can't take screenshot. No open browser found");
			return;
		}
		
		byte[] png = takesScreenshot.getScreenshotAs(OutputType.BYTES);
		writeScreenshot(path, png);

		logging.html(String
				.format("</td></tr><tr><td colspan=\"3\"><a href=\"%s/%s\"><img src=\"%s/%s\" width=\"400px\"></a>",
						browser.configurations[0].toString(), filename,
						browser.configurations[0].toString(), filename));
	}

	// ##############################
	// Internal Methods
	// ##############################

	protected int screenshotIndex = 0;

	protected void writeScreenshot(File path, byte[] png) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path);
			fos.write(png);
			fos.flush();
		} catch (IOException e) {
			logging.warn(String.format("Can't write screenshot '%s'",
					path.getAbsolutePath()));
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					logging.warn("Can't even close stream");
				}
			}
		}
	}

	protected String normalizeFilename(String filename) {
		if (filename == null) {
			screenshotIndex++;
			filename = String.format("selenium-screenshot-%d.png",
					screenshotIndex);
		} else {
			filename = filename.replace('/', File.separatorChar);
		}
		return filename;
	}
}
