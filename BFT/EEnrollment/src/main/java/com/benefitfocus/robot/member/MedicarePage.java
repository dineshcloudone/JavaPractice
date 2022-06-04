package com.benefitfocus.robot.member;

import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.Utilities;

@RobotKeywords
public class MedicarePage {

	@Autowired
	protected ActionKeywords performAction;

	@Autowired
	protected ManageBrowser browser;

	@Autowired
	protected Utilities utils;
	
	@Autowired
	protected Logging logger;

	// Locators on this Page
	By MedicareQuestion = By.xpath("//h1");
	By MedicareYes = By.id("hasMedicare[YES]");
	By MedicareNo = By.xpath("//span[contains(text(),'No')]");
	By NextButton = By.xpath("//button[contains(text(),'Next')]");


	// Click Medicare Yes Radio Button
	private void ClickMedicareYes() {
		performAction.click(MedicareYes, "Medicare Yes Radio Button");
	}

	// Click Medicare No Radio Button

	private void ClickMedicareNO() {
		performAction.click(MedicareNo, "Medicare No Radio Button");
	}

	// Click Next Button to HSA Plans Page

	private void ClickNext() {
		performAction.click(NextButton, "Next Button");
	}

	//select other Option in the medicare page
	private void selectOption(String strOption) {
		performAction.click(By.xpath("//span[text()=" + "\'"
				+ strOption + "\'" + ")]"),"customized option");
	}
	/**
	 * <pre> 
	  * Author  : Nagarjuna Thallam
	 *  
	  * Description : SelectMedicare keyword or method  to select an option in for medicare Question deisplaed in the Member role
	  * 
	  * Role: Member Role
	 *
	 *
	  * PreCondition : Member must be in Medicare page.
	 * 
	  * PostCondition : member successfully selects the option and navigates to the next page
	 *  
	  * <b>Parameters & Example </b> 
	  * 
	  * | StrOption | 
	  * | Yes / No  |
	  * 
	  * Java file Name :  MedicarePage.java
	  * </pre> 
	  **/
	

	@RobotKeyword
	@ArgumentNames({ "StrOption" })
	public void SelectMedicare(String StrOption) {

		try {
			if (StrOption.startsWith("td:")) {
				StrOption = utils.getValue(StrOption);
			}
			if((performAction.isElementPresent(MedicareQuestion))&&(StrOption != null )){

					if (StrOption.equalsIgnoreCase("Yes")) {
						this.ClickMedicareYes();
						this.ClickNext();
					} else if (StrOption.equalsIgnoreCase("No")) {

						performAction.isElementPresent(MedicareQuestion);
						this.ClickMedicareNO();
						this.ClickNext();

					} else {
						this.selectOption(StrOption);
					}
				} else {
					logger.info("Medicare Question not found ");
					throw new CustomException("Medicare Question not found ");
				}

		} catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			throw new RuntimeException(
					"Exception while Selecting Medicare Radio Button "
							+ e.getMessage());
		}
	}

}
