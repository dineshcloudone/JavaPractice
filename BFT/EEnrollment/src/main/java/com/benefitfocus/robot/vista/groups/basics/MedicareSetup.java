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
public class MedicareSetup {

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

	By enableMedicareManager = By.id("enableMedicareManager");
	By medicareAutoPopulate = By.id("medicareAutoPopulate");
	By medicareAutoEnrollment = By.id("medicareAutoEnrollment");
	By clickSave = By.linkText("Save");


	//Private Mothods

	private void enableFullMedicarePolicy() {
		performAction.select(enableMedicareManager, "Yes", "Yes");
		performAction.select(medicareAutoPopulate, "Auto-populate part A only", "Auto-populate part A only");
		performAction.select(medicareAutoEnrollment, "Yes", "Yes");
		performAction.click(clickSave, "Click Save Button");
	}

	private void enablePartialMedicarePolicy() {
		performAction.select(enableMedicareManager, "Yes", "Yes");
		performAction.select(medicareAutoPopulate, "Do Not Auto-populate", "Do Not Auto-populate");
		performAction.click(clickSave, "Click Save Button");
	}

	private void enableNoMedicarePolicy() {
		performAction.select(enableMedicareManager, "Yes", "Yes");
		performAction.click(clickSave, "Click Save Button");
	}

	//Robot Methods

	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Update Medicare Policy Medicare Manager for Member in Vista Role
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition : Medicare Policy Page Opened in Vista Role
	 * 
	 * PostCondition : Updated Medicare Manager Settings for Member in Vista Role
	 * 
	 * Java File Name : MedicareSetup.java
	 * 
	 * | Medicare Type |
	 * | ex: Full/Partial/No |
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strMedicareType" })
	public void updateMedicarePolicyForMemberInVistaRole(String strMedicareType) {
		try {

			if (strMedicareType.equalsIgnoreCase("Full")) {
				this.enableFullMedicarePolicy();
			}else if (strMedicareType.equalsIgnoreCase("Partial")) {
				this.enablePartialMedicarePolicy();
			}else if (strMedicareType.equalsIgnoreCase("No")) {
				this.enableNoMedicarePolicy();
			}
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("No Save Button Found"
					+ e.getMessage());
		}
	}
}