package com.benefitfocus.robot.hradmin;

import org.json.simple.JSONObject;

import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.CustomException;

@RobotKeywords
public class CarrierNumbers {
	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected Screenshot scr;
	
	By manageEmployeeButton = By.xpath("//button[contains(text(),'Manage employee')]");
	By assigneditcarriernumbers = By.linkText("Assign/Edit Carrier Numbers");
	By carrierIdentifierValue = By.id("identifierValue");
	
	// Click on Assign and Edit Carrier Numbers in Manage Employee
	private void clickAssignEditCarrierNumbers() {
		performAction.click(manageEmployeeButton, "Manage Employee Button");
		performAction.click(assigneditcarriernumbers, "Assign Edit Carrier Numbers link");
	}
	
	private void selectCarrierValue(String strCarrierNumber) {
		performAction.click(carrierIdentifierValue, "Select Carrier Number");
		performAction.select(carrierIdentifierValue, strCarrierNumber, "Select Carrier Number");
	}
	
	
	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Assign Edit Carrier Numbers in HR Role for Carrier Numbers
	 * 
	 * Role : HR Admin
	 * 
	 * PreCondition : Employee Overview Page in HR Admin Role
	 * 
	 * PostCondition : Carrier Number is Changed successfully in HR Role.
	 * 
	 * Java File: CarrierNumbers.java
	 * 
	 * |  strCarrierNumber |
	 * | ex: 09385 |
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strCarrierNumber" })
	public void changeCarrierNumbersForMemberInHRRole(String strCarrierNumber) {
		try {
			this.clickAssignEditCarrierNumbers();
			this.selectCarrierValue(strCarrierNumber);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Exception occured in changing carrier number for member in HR role"
							+ e.getMessage());
		}
	}
}
