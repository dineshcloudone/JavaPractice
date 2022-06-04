package com.benefitfocus.robot.hradmin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import org.openqa.selenium.support.ui.Select;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;


import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.*;
import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;

@RobotKeywords
public class DateExplanationPage {

	@Autowired
	protected ManageBrowser browser;
	@Autowired
	protected Utilities utils;
	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected Logging logger;
	@Autowired
	protected Screenshot scr;

	By nextButton = By.xpath("//strong[text()='Next']");
	By dateReason = By.id("reasonEntry");

	/**
	 * <pre>
	 * Author  : Teja P
	 * source : DateExplanationPage
	 *
	 * Description : This Keyword is used to enter DateExplanation in HR-Admin Role
	 * and clicks Next button
	 *
	 * PreCondition : DateExplanation page should be displayed
	 *
	 * PostCondition : Page will be displayed as per the Group configuration.Plan selection page will be displayed
	 *
	 * <b>Parameters & Example </b>
	 *
	 * <b>strDateExplanation </b>
	 * |DateExplantion for giving different Date|
	 *
	 *
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strDateExplanation"})
	public void provideDateExplantionInHrRole(String strDateExplanation) {

		try {
			performAction.enter(dateReason, strDateExplanation, "Giving data for Date Explanation");
			scr.capturePageScreenshot();
			performAction.click(nextButton, "Next Button in Medicare page");
			scr.capturePageScreenshot();

		} catch (Exception e) {
			logger.info("Exception in editing Date Explanation ");
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while entering data for date explanation"
					+ " " + e.getMessage());
		}
	}
}
