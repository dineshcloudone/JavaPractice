package com.benefitfocus.robot.vista.groups.benefits;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.vista.groups.basics.BasicCommon;
import com.benefitfocus.robot.utils.ReadJsonTestData;

@RobotKeywords
public class EditOfferDetails {
	
	@Autowired
	protected ActionKeywords performAction;

	@Autowired
	protected Utilities utils;

	@Autowired
	protected ManageBrowser browser;

	@Autowired
	protected Screenshot scr;
	
	@Autowired
	protected BasicCommon common;
	@Autowired
	protected Logging logger;
	
	//Locators on the Page
	By offerOptions=By.linkText("Offer Options");
	By editOfferDetailsLink=By.linkText("Edit Offer Details");
	
	By gatheringAdditionalInfo = By.id("selectedConfigProcessStrategyType-0-ADDITIONALINSURANCECONFIG");  
	By additionalInfo = By.xpath("//div[contains(text(),'Make Additional Insurance details optional')]/div/select[@name='attributeValue']");
	By timelyEnrollees = By.xpath("//div[contains(text(),'Prior Period for Timely Enrollees')]/div/select[@name='attributeValue']");
	By lateEnrollees = By.xpath("//div[contains(text(),'Prior Period for Late Enrollees')]/div/select[@name='attributeValue']");
	By specialEnrollees = By.xpath("//div[contains(text(),'Prior Period for Special Enrollees')]/div/select[@name='attributeValue']");
	By credibleCoverage = By.xpath("//div[contains(text(),'Calculate Creditable Coverage')]/div/select[@name='attributeValue']");
	
	By employeeAgreements = By.id("selectedConfigProcessStrategyType-0-EMPLOYEEAGREEMENTSCONFIG");
	
	By beneficiaryConfig = By.id("selectedConfigProcessStrategyType-0-BENEFICIARYCONFIG");
	By dateOfBirth = By.xpath("//div[@class=(contains(text(),'Capture Person Date of Birth'))]/div/select[@name='attributeValue']");
	By printBeneficiary = By.xpath("//div[@class=(contains(text(),'Print Beneficiary Form Text'))]/div/select[@name='attributeValue']");

	// click on Edit Offer Details on Offers Page
	private void clickEditOfferDetails()
	{
		performAction.mouseOver(offerOptions, "Edit Options");
		performAction.click(editOfferDetailsLink, "Edit Offer Details Link");
	}
	
	private void gatheringAdditionalInfo() {
		performAction.select(gatheringAdditionalInfo, "Ask user for this information (standard)", "Gathering Required Information");
		performAction.select(additionalInfo, "Yes", "Make Additional Details Optional");
		performAction.select(timelyEnrollees, "Collect prior insurance from past 63 days", "Prior Period for Timely Enrollees");
		performAction.select(lateEnrollees, "Collect prior insurance from past 63 days", "Prior Period for Late Enrollees");
		performAction.select(specialEnrollees, "Collect prior insurance from past 63 days", "Prior Period for Special Enrollees");
		performAction.select(credibleCoverage, "Yes", "Calculate Credible Coverage");
	}
	
	private void selectEmployeeAgreements() {
		//performAction.click(employeeAgreements, "Employee Agreements");
		performAction.select(employeeAgreements, "Ask for acknowledgement", "Ask for acknowledgement");
	}
	
	private void selectBenConfig(String strbenconfigs){
		performAction.select(beneficiaryConfig, strbenconfigs, "Select Required Option");
		logger.info("Changed to Require user to provide this information");
	}
	
	private void selectDateOfBirth(String strdateofbirths) {
		performAction.select(dateOfBirth, strdateofbirths, "Select Required Option");
		logger.info("Changed to Optional");
	}
	
	private void selectPrintBen(String strPrintBens) {
		performAction.select(printBeneficiary, strPrintBens, "Select Required Option");
		logger.info("Changed to Please print and sign your beneficiary form for your records.");
	}
	
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'updateEditOfferDetails' keyword used to update Offer Details in 'Edit Offer Details' page Vista role  
	 * 
	 * PreCondition : user should be in Edit Offer Details Page 
	 * 
	 * PostCondition : user  will be redirected Current Offers page.
	 *   
	 * <pre>
	 * <b>Parameters :</b>
	 * | labelName - Label Name |strValue - Value in Drop down or Enter a value into the Text Box |
	 * </pre> 
	 **/
	
	@RobotKeyword
	@ArgumentNames({"labelName ","strValue" })
	public void updateEditOfferDetails(String labelName,String strValue){
	try	{	
		//this.clickEditOfferDetails();
		common.updateInfo(labelName,strValue);
		}catch (Exception e) {
			System.out.println("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception in performing action in Edit Offer Detials"
					+ e.getMessage());
		}
	}	
	
	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Change Settings to Gather Additional Information Details for Member in Vista Role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : Edit Offer Page Opened in Vista Role
	 * 
	 * PostCondition : Offers Page Opened in Vista Role
	 * 
	 * Java File Name : EditOfferDetails.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void gatheringAdditionalInformation() {
		try {
			this.gatheringAdditionalInfo();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("No Save Button Found"
					+ e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Change Employee Agreements in Vista Role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : Edit Offer Details Page Opened in Vista Role
	 * 
	 * PostCondition : Updated Employee Agreements Configurations Settings for Member in Vista Role
	 * 
	 * Java File Name : EditOfferDetails.java
	 * 
	 * | |
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void changeEmployeeAgreementsInVistaRole() {
		try {
			this.selectEmployeeAgreements();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("No Save Button Found"
					+ e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Update Entering Beneficiaries Configuration with DOB s Optional and Text to Enter in Beneficiary Form
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : Edit Offer Details Page Opened in Vista Admin Role
	 * 
	 * PostCondition : Beneficiaries Configuration Changed with Few More Dependent Fields
	 * 
	 * Java File: EditOfferDetails.java
	 * 
	 * | beneficiariesconfiguration |
	 * | td:beneficiariesconfiguration (Require user to provide this information, Optional, Please print and sign your beneficiary form for your records.) | 
	 * </pre>
	 **/
    @RobotKeyword
    @ArgumentNames({ "beneficiariesconfiguration" })
    public void addBeneficiaryConfigurationsChangesUpdate(String beneficiariesconfiguration){
        try{
        	Object object = null;
            JSONObject fields = ReadJsonTestData.getTestData(beneficiariesconfiguration.toLowerCase());
            object = fields.get("benconfig");
            String benconfig=utils.getValue(object.toString());
        	this.selectBenConfig(benconfig);
        	object = fields.get("dateofbirth");
            String dateofbirth=utils.getValue(object.toString());
        	this.selectDateOfBirth(dateofbirth);
        	object = fields.get("printbeneficiary");
            String printben=utils.getValue(object.toString());
        	this.selectPrintBen(printben);
	} catch (Exception e) {
		scr.capturePageScreenshot();
		throw new CustomException("Exception occured while adding beneficiary changes"
				+ e.getMessage()); 
    	}
    }
}
