package com.benefitfocus.robot.vista.groups;

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

@RobotKeywords
public class Basics {

	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected Utilities utils;
	@Autowired
	protected ManageBrowser browser;
	@Autowired
	protected Screenshot scr;
	@Autowired
	protected Logging logger;

	// Identification Information 
	By basics=By.xpath("//a[@id='innerLinkGroupNavMenubasics']");
	By editIdentification=By.xpath("//h2[text()='Identification']/../..//a");
	By phoneNumber=By.id("phoneNumber");
	By groupName=By.id("groupName");
	By situsState=By.id("situsState");
	By save=By.xpath("//*[text()='Save']");
	By cancel=By.xpath("//strong[text()='Cancel']");

	By benefits=By.xpath("//a[@id='innerLinkGroupNavMenubenefit']");
	By rateFactorSurvey=By.linkText("Rate Factor Survey");

	// System Information
	By editSystems=By.xpath("//h2[text()='System']/../..//a");
	By editUserAccess=By.xpath("//h2[text()='User Access']/../..//a");
	By privateLabel=By.id("privateLabelKey");
	By groupStatus=By.id("groupStatus");
	By secureMessaging=By.id("secureMessagingForVistaInd");
	By secureMessagingEmailNotification=By.id("secureMessagingEmailNotification");
	By secureMessagingEmailFromAddress=By.id("secureMessagingEmailFromAddress");
	By emailSubject=By.id("secureMessagingEmailSubject");
	By sponsoringCarrierKey=By.id("sponsoringCarrierKey");
	By supportLevelType= By.id("supportLevelType");
	By groupClassificationType=By.id("groupClassificationType");
	By insuredType=By.id("selfInsured");
	By accountConsultant=By.id("accountConsultantKey");
	By implementationConsultantKey=By.id("implementationConsultantKey");
	By rateFactorSurveyEnabled=By.id("rateFactorSurveyEnabled");

	// Basics tab in eEnrollment Group search page
	private void clickBasics(){
		performAction.click(basics, "BASICS TAB");
	}
	// Rate Factor Survey in Benefits Tab
	private void clickRateFactorSurvey(){
		performAction.click(rateFactorSurvey, "Rate Factor Survey");
	}
	// Edit System Icon
	private void clickEditSystems()
	{
		performAction.click(editSystems, "Edit System Information");
	}
	// Edit User Access
	private void clickEditUserAccess()
	{
		performAction.click(editUserAccess, "Edit User Access");
	}
	// Edit Identification Icon
	private void clickIdentification(){
		performAction.click(editIdentification, "Edit Identification Icon");
	}
	// Phone Number text box in Group search page
	private void setPhoneNumer(String Phnumber){

		performAction.clearEnter(phoneNumber, Phnumber, "Phone Number");
	}
	// State selection 
	private void selectSitusState(String state)	{
		performAction.select(situsState, state," State selection");
	}
	// click on Save Button
	private void clickSave()
	{
		performAction.click(save, "Save Button");
	}
	// select Private Label
	private void selectPrivateLabel(String labelName)
	{
		performAction.select(privateLabel, labelName, "Private Label");
	}
	// select group status live or test live or Not live etc..
	private void selectStatus(String status)
	{
		performAction.select(groupStatus, status, "Group status");
	}
	// select Sponsoring Carrier
	private void selectSponsoringCarrier(String sponsoringCarrier){
		performAction.select(sponsoringCarrierKey, sponsoringCarrier, "Sponsoring Carrier");
	}
	// select RateFactorSurveyEnabled Enabled or Disabled
	private void selectRateFactorSurveyEnabled(String rateFactor)
	{
		performAction.select(rateFactorSurveyEnabled, rateFactor, "RateFactorSurveyEnabled");
	}
	// select Support Level
	private void selectSupportLevelType(String supportLevel)
	{
		performAction.select(supportLevelType, supportLevel, "Support Level Type");
	}
	// select groupClassificationType
	private void selectGroupClassificationType(String classificationType)
	{
		performAction.select(groupClassificationType, classificationType, "groupClassificationType");
	}
	// select Insured Type
	private void selectInsuredType(String insureType)
	{
		performAction.select(insuredType, insureType, "Insured Type");
	}
	// select Account Consultant Key
	private void selectaccountConsultantKey(String accountConsultantKey)
	{
		performAction.select(accountConsultant, accountConsultantKey, "Account Consultant");
	}
	// select Implementation Consultant
	private void selectimplementationConsultant(String implementationConsultant)
	{
		performAction.select(implementationConsultantKey, implementationConsultant, "Implementation Consultant");
	}
	/**
	 * 'updateIdentification' keyword used to update the Identification information 
	 * in vista role
	 * 
	 * <pre>
	 * <b>Parameters :</b>
	 * | strPhoneNumer - phone Number |strSitusState - state Name |
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({"strPhoneNumer","strSitusState" })
	public void updateIdentification(String strPhoneNumer,String strSitusState)
	{
		try{
			this.clickBasics();
			this.clickIdentification();
			this.setPhoneNumer(strPhoneNumer);
			this.selectSitusState(strSitusState);
			this.clickSave();
			this.verifyIdentificTion(strPhoneNumer,strSitusState);
		} catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			throw new CustomException("Exception in performing update identification information "
					+ e.getMessage());
		}
	}
	// verify the Updated Identification Information
	private void verifyIdentificTion(String strPhoneNumer,String strSitusState)
	{
		this.clickIdentification();
		String expText=browser.getCurrentWebDriver().findElement(By.xpath("//h1[text()='Identification Information']")).getText();
		if(browser.getCurrentWebDriver().findElement(By.xpath("//h1[text()='Identification Information']")).isDisplayed()) {
			
			logger.info(expText+" is present in the page");			
		}
		String expPhoneNumber=browser.getCurrentWebDriver().findElement(phoneNumber).getAttribute("value");
		logger.info("Value of Phone Numebr : "+expPhoneNumber);		
		if(expPhoneNumber.equalsIgnoreCase(strPhoneNumer)){
			logger.info(strPhoneNumer+" Phone Number is updated successfully");
			
		}		
		String expSitusState=browser.getCurrentWebDriver().findElement(situsState).getAttribute("value");
		logger.info("Value of SITUS status : "+expSitusState);
		if(expSitusState.equalsIgnoreCase(strSitusState)){
			logger.info(strSitusState+" SITUS status is updated successfully");
		}
	}


	
}
