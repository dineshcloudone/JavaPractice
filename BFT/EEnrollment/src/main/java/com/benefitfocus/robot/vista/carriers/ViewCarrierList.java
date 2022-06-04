package com.benefitfocus.robot.vista.carriers;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.ReadJsonTestData;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;

@RobotKeywords
public class ViewCarrierList {
	
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
	
	
	By bcbsfl = By.linkText("BCBSFL");
	By editMedicalPlan = By.xpath("//tr[@class='dtr dtrOddPassive'][1]//a[contains(text(),'Edit')]");
	
	// Private Methods
	
	private void editEmployeeAgreementsforMedical() {
		performAction.click(editMedicalPlan, "Click Edit Medical Plan Link");
	}
	
	private void clickBCBSFLCarrier() {
		performAction.click(bcbsfl, "Click Carrier BCBSFL Link");
	}
	
	// Robot Methods
	
	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Click BCBSFL Link in Vista Role
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition : View carrier List page Opened in Vista Role
	 * 
	 * PostCondition : BCBSFL Page Opened in Vista Role
	 * 
	 * Java File Name : ViewCarrierList.java
	 * 
	 * | |
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void clickBCBSFLCarrierLink() {
		try {
			this.clickBCBSFLCarrier();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("No Link Found"
					+ e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Edit Employee Agreements for Medical Plan in Vista Role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : Employee Agreements Page Opened in Vista Role
	 * 
	 * PostCondition : Updated Employee Agreements Configurations Settings for Member in Vista Role
	 * 
	 * Java File Name : ViewCarrierList.java
	 * 
	 * | |
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void editEmployeeAgreementsforMedicalInVistaRole() {
		try {
			this.editEmployeeAgreementsforMedical();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("No Save Button Found"
					+ e.getMessage());
		}
	}
}
