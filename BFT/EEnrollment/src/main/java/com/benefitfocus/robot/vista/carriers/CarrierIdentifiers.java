package com.benefitfocus.robot.vista.carriers;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.python.antlr.ast.Compare;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.ReadJsonTestData;
import org.openqa.selenium.JavascriptExecutor;

@RobotKeywords
public class CarrierIdentifiers {

	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected Utilities utils;
	@Autowired
	protected Screenshot scr;
	@Autowired
	protected ManageBrowser browser;

	By clickNext = By.linkText("Next");
	By clickSave = By.linkText("Save");
	By yesbutton = By.linkText("Yes");
	By editDates = By.cssSelector("img[alt='Edit Contents']");
	By participationDates = By.id("selectedParticipationPeriodUrl");
	By groupCarrierNumber = By.xpath("//div[contains(@id,'navCarrierNbrGROUP_NBR')]");
	By createNewCarrierNumber = By.linkText("Create New Group Number");
	By userSelectionHR = By.id("allowUserSelection");
	By benefitDetails = By.id("calculationBenefitTypes");
	By userPermissions = By.id("selectedTemplateKey");
	By effectiveDates = By.id("selectedEffectiveDate");
	By expirationDates = By.id("selectedExpirationDate");
	By allBenefits = By.id("useAllBenefitTypes-nativeHtmlElement");
	By carrierNumbersValue = By.id("carrierNumberValue");
	By newgroupnumbers = By.xpath("//tr[@class='dtr dtrOddPassive']//td[@class='cc']//a[@class='buttonInnerLink']");
	By deletenewnumbers = By.xpath("//tr[@class='dtr dtrOddPassive']//td[@class='cmcc']//a[contains(text(),'Delete')]");
	By groupNumbers = By.xpath("//div[@class='secondaryRegion'][2]//div[3]//tr[@class='dtr dtrEvenPassive']//td[@class='cc']");
	By viewEdit = By.xpath("//div[@class='secondaryRegion'][2]//div[3]//td[@class='cmcc']//a[contains(text(),'View / Edit')]");

	//Private Methods

	private void deleteCarrierNumbers(String strEffectiveDate, String strExpirationDate_1) {
		//Delete If any Modified Carrier Numbers
		WebElement element = browser.getCurrentWebDriver().findElement(newgroupnumbers);
		((JavascriptExecutor) browser.getCurrentWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		browser.getCurrentWebDriver().findElement(newgroupnumbers).click();
		browser.getCurrentWebDriver().findElement(deletenewnumbers).click();
		performAction.click(yesbutton, "Click Yes to Delete");
		//Change Existing Carrier Numbers Participation Dates
		browser.getCurrentWebDriver().findElement(groupNumbers).click();
		browser.getCurrentWebDriver().findElement(viewEdit).click();
		performAction.click(editDates, "Click Edit Dates");
		performAction.select(participationDates, "----Please Select----", "Select Participation Dates");
		performAction.enter(effectiveDates, strEffectiveDate, "Enter Effective Dates");
		performAction.enter(expirationDates, strExpirationDate_1, "Enter Expiration Dates");
		performAction.click(clickNext, "Click Next");
		performAction.click(clickSave, "Click Save");
	}

	private void editCarrierNumbers() {
		//Edit Existing Carrier Numbers for Group 
		browser.getCurrentWebDriver().findElement(groupNumbers).click();
		browser.getCurrentWebDriver().findElement(viewEdit).click();
	}

	private void editDates() {
		//Change Participation Dates
		performAction.click(editDates, "Click Edit Dates");
	}

	private void selectParticipation() {
		performAction.select(participationDates, "----Please Select----", "Select Participation Dates");
	}

	private void enterEffectiveDates(String strEffectiveDate) {
		performAction.enter(effectiveDates, strEffectiveDate, "Enter Effective Dates");
	}

	private void enterExpirationDates(String strExpirationDate) {
		if (strExpirationDate.startsWith("d:")) {
			strExpirationDate = strExpirationDate.substring(2);
		}
		String date = utils.getDate(strExpirationDate);
		performAction.enter(expirationDates, date, "Enter Expiration Dates");
		performAction.click(clickNext, "Click Next");
		performAction.click(clickSave, "Click Save");
	}

	private void createNewNumbers() {
		//Create New Carrier Numbers
		browser.getCurrentWebDriver().findElement(groupCarrierNumber).click();
		browser.getCurrentWebDriver().findElement(createNewCarrierNumber).click();
	}

	private void enterNewEffectiveDates(String strEffectiveDate_1) {
		if (strEffectiveDate_1.startsWith("d:")) {
			strEffectiveDate_1 = strEffectiveDate_1.substring(2);
		}
		String date_1 = utils.getDate(strEffectiveDate_1);
		performAction.enter(effectiveDates, date_1, "Enter Effective Dates");
	}

	private void enterNewExpirationDates(String strExpirationDate_1){
		performAction.enter(expirationDates, strExpirationDate_1, "Enter Expiration Dates");
		performAction.click(clickNext, "Click Next");
	}

	private void changeHRPermissions() {
		//Change HR Permissions for Group to Enroll to Medical Benefit Only
		performAction.select(userSelectionHR, "Yes", "Select Yes Click Change HR Permissions");
		performAction.click(clickNext, "Click Next");
		performAction.click(clickNext, "Click Next");
		performAction.click(allBenefits, "All Benefits");
		performAction.select(benefitDetails, "Medical", "Select Medical Benefit Only");
		performAction.click(clickNext, "Click Next");
	}

	private void selectUserPermissions() {
		//Select User Value as One Value for All Employees
		performAction.select(userPermissions, "One value for all employees", "Change User Permissions to One Value for ALL");
		performAction.click(clickNext, "Click Next");
	}

	private void enterNewCarrierNumber() {
		//Insert New Carrier Numbers for Group
		performAction.enter(carrierNumbersValue, "09385", "Enter Carrier Number Value");
		performAction.click(clickNext, "Click Next");
	}

	//Robot Keywords

	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Edit and Add Carrier Numbers and Change HR Permissions in Vista Role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : Group Search Page in Vista Role
	 * 
	 * PostCondition : Added Carrier Numbers for the Group Successfully.
	 * 
	 * Java File: CarrierIdentifiers.java
	 * 
	 * | dates : td:dates |
	 * | Expiration Date: Current Date +1 |
	 * | Effective Date : Current Date +2 |
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "dates", "strExpirationDate", "strEffectiveDate_1"})
	public void editAndAddNewCarrierNumbers(String dates, String strExpirationDate, String strEffectiveDate_1) {
		try {
			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(dates.toLowerCase());
			object = fields.get("effectivedate");
			String effectivedate=utils.getValue(object.toString());
			object = fields.get("expirationdates");
			String expirationdates=utils.getValue(object.toString());
			if (performAction.isElementPresent(newgroupnumbers)) {
				this.deleteCarrierNumbers(strEffectiveDate_1, expirationdates);
			}
			this.editCarrierNumbers();
			this.editDates();
			this.selectParticipation();
			this.enterEffectiveDates(effectivedate);
			this.enterExpirationDates(strExpirationDate);
			this.createNewNumbers();
			this.enterNewEffectiveDates(strEffectiveDate_1);
			this.enterNewExpirationDates(expirationdates);
			this.changeHRPermissions();
			this.selectUserPermissions();
			this.enterNewCarrierNumber();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Exception occured while adding new carrier numbers"
					+ e.getMessage());
		}
	}


}
