package com.benefitfocus.robot.vista.global;



import junit.framework.Assert;
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
import com.benefitfocus.robot.vista.groups.basics.BasicCommon;



@RobotKeywords
public class AccessTrustee {


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
	@Autowired	
	protected BasicCommon common;


	// Locators on the Page
	By trusteeNameSearchBox = By.name("searchCriteria");
	By searchButton = By.xpath("//a[text()='Search']");
	By trusteeSearchText = By.tagName("h1");
	By trusteeNameSearchBoxInTrusteeSerachPage = By.id("searchCriteria");
	By searchButtonInTrusteeSerachPage = By.xpath("//strong[text()='Search']");
	By viewTrusteeListMessage = By.xpath("//div[contains(text(),'View Trus')]");
	By 	viewTrusteeProfileText= By.tagName("h1");
	By 	viewTrusteeNameText= By.tagName("h2");

	//Instance Variables
	String strTrusteeName="";
	String getOfferName="";
	String strOutTrusteeName ;



	// Search Button
	private void clickSearchButton() throws Exception {
		performAction.click(searchButton, "Search Button");
	}


	// Trustee name Search Box
	private void setTrusteeName(String strTrusteeName,String strOutTrusteeName) throws Exception {
		try {

			if (performAction.isElementPresent(trusteeSearchText)) {
				//Enter the TrusteeName in the Search Box Field in 	Trustee Search Page.
				performAction.clearEnter(trusteeNameSearchBoxInTrusteeSerachPage, strTrusteeName, "Trustee Search Box");
				//Return Trustee Name by using Hashmap
				browser.hMap.put(strOutTrusteeName, strTrusteeName);
				//Click on Search Button in 	Trustee Search Page.
				performAction.click(searchButtonInTrusteeSerachPage, "Search Button");
				//Verify the View Trustee List Message
				Assert.assertEquals("View Trustee List", browser.getCurrentWebDriver().findElement(viewTrusteeListMessage).getText());
				scr.capturePageScreenshot();
			} else {
				//Enter the TrusteeName in the Search Box Field in 	View Trustee List Page.
				performAction.clearEnter(trusteeNameSearchBox, strTrusteeName, "TrusteeName");
				//Return Trustee Name by using Hashmap
				browser.hMap.put(strOutTrusteeName, strTrusteeName);
				//Click on Search Button in 	View Trustee List Page.
				this.clickSearchButton();
				//Verify the View Trustee List Message
				Assert.assertEquals("View Trustee List", browser.getCurrentWebDriver().findElement(viewTrusteeListMessage).getText());
				scr.capturePageScreenshot();
			}
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while Perform an operation on Trustee Serach Box "
					+ e.getMessage());
		}
	}

	//TrusteeName Link
	private void trusteeLink( String strOutTrusteeName){

		try {
			String enteredtrusteeName=utils.getValue(strOutTrusteeName);
			performAction.click(By.xpath("//a[contains(text(),'"+enteredtrusteeName+"')]"), "Trustee Link");
			Assert.assertEquals("View Trustee Profile", browser.getCurrentWebDriver().findElement(viewTrusteeProfileText).getText().trim());
			Assert.assertEquals(enteredtrusteeName, browser.getCurrentWebDriver().findElement(viewTrusteeNameText).getText().trim());
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while  Click on  TrusteeName Link"
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * 
	 * Author  : Sekhar Tirumala
	 * 
	 * Description  : trusteeSearchInHrRole Keyword or method is used to perform Search the TrusteeName in   Vista Admin Role.
	 * 
	 * Role : Vista Role
	 * 
	 * Precondition : View Trustee List Page in Vista Admin  Role.
	 * 
	 * PostConditions : View Trustee List Page  .
	 * 
	 * <b>Parameters & Example </b>
	 * 
	 * | classification |
	 * 
	 * | strTrusteeName | strOutTrusteeName |
	 * 
	 * | HSA Bank / 2014 Wellness | 2014 Wellness |
	 * 
	 * Java FileName : AccessTrustee.java
	 * 
	 * </pre>
	 * 
	 **/
	@RobotKeyword
	@ArgumentNames({ "strTrusteeName" , "strOutTrusteeName"})
	public void trusteeSearchInVistaRole(String strTrusteeName,String strOutTrusteeName) {

		try {

			this.setTrusteeName(strTrusteeName, strOutTrusteeName);

		} catch (Exception e) {
			logger.info("Exception occured while perform on Trustee Search Box"
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while perform on Trustee Search Box"
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 * 
	 * Description  : clickTrusteeNameInHrRole Keyword or method is used to perform click on TrusteeName Link  in   Vista Admin Role.
	 * 
	 * Role : Vista Role
	 * 
	 * Precondition : View Trustee List Page in Vista Admin  Role.
	 * 
	 * PostConditions : View Trustee Profile .
	 * 
	 * <b>Parameters & Example </b>
	 * 
	 * | classification |
	 * 
	 * | strTrusteeName | 
	 * 
	 * | *HSA Bank* / 2014 Wellness | 
	 * 
	 * Java FileName : AccessTrustee.java
	 * 
	 * </pre>
	 * 
	 **/

	@RobotKeyword
	@ArgumentNames({ "strTrusteeName" })
	public void clickTrusteeNameInVistaRole(String strTrusteeName) {

		try {

			this.trusteeLink(strTrusteeName);

		} catch (Exception e) {
			logger.info("Exception occured while perform on Trustee Link"
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception occured while perform on Trustee Link"
					+ e.getMessage());
		}
	}















}
