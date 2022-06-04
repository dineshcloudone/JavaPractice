package com.benefitfocus.robot.vista.groups.basics;

import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;

@RobotKeywords
public class UniquePersonValidation {

	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected ManageBrowser browser;
	@Autowired
	protected Logging logger;
	@Autowired
	protected Utilities utils;
	@Autowired
	protected Screenshot scr;

	By uniquePersonValidationRules = By.id("enableUniquePersonValidation");
	By validateFor = By.id("validateRelationships");
	By whenEnrollIn = By.id("benefitsToEnroll");
	By validateAgainst = By.id("validateBenefits");
	By enforceValidationsForPersons = By.id("enforceValidation");
	By identifiedPersonsShouldBe = By.id("identifyDualEnrollment");
	By add = By.linkText("Add");
	By delete = By.linkText("Delete");

	private void setUniqueValidation() {
		performAction.select(uniquePersonValidationRules, "Enabled", "Enabled");
	}

	private void setSubscriberValidation() {
		performAction.select(validateFor, "Subscriber", "Subscriber");
		performAction.select(whenEnrollIn, "Medical", "Medical");
		performAction.select(validateAgainst, "Medical", "Medical");
		performAction.select(enforceValidationsForPersons, "Not Enrolled", "Not Enrolled");
		performAction.enter(identifiedPersonsShouldBe, "Restricted", "Restricted");
		performAction.click(add, "Add Button");
	}

	private void setDependentValidation() {
		performAction.select(validateFor, "Spouse", "Spouse");
		performAction.select(whenEnrollIn, "Medical", "Medical");
		performAction.select(validateAgainst, "Medical", "Medical");
		performAction.select(enforceValidationsForPersons, "Not Enrolled", "Not Enrolled");
		performAction.enter(identifiedPersonsShouldBe, "Restricted", "Restricted");
		performAction.click(add, "Add Button");
	}

	//Robot Methods

	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Set Unique Personal Identification for Member to Identify Duplicacy in Member Role
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition : Enrollment Page Opened in Member Role
	 * 
	 * PostCondition : Error Message if Duplicacy Found or Member Enrolled Page in Member Role
	 * 
	 * Java File Name : UniquePersonValidation.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void setUniquePersonValidationRule() {
		try {

			if (!performAction.isElementPresent(delete)) {
				this.setUniqueValidation();
				this.setSubscriberValidation();
				this.setDependentValidation();
			}
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("No Add Button Found"
					+ e.getMessage());
		}
	}
}
