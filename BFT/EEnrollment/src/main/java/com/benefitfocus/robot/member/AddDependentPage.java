package com.benefitfocus.robot.member;

import junit.framework.Assert;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywordOverload;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.ReadJsonTestData;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;

@RobotKeywords
public class AddDependentPage {

	@Autowired
	protected ActionKeywords performAction;

	@Autowired
	protected ManageBrowser browser;

	@Autowired
	protected Utilities utils;

	@Autowired
	protected Screenshot scr;

	@Autowired
	protected Logging logger;

	// Locators
	By addDependentsPageNextButton = By.id("next");
	By addDependentButton = By.id("addButton");
	By firstName = By.id("firstName");
	By lastName = By.id("lastName");
	By birthDate = By.id("birthDate");
	By mGender = By.xpath("//input[@name='gender' and @value='M']");
	By fGender = By.xpath("//input[@name='gender' and @value='F']");
    By ssn = By.id("ssn");
	By relationship = By.id("defaultRelationship");
	By savebutton = By.xpath("//button[contains(text(),'Save')]");
	By saveDependent = By.xpath("//button[@class='btn btn-success']");
	By saveAndAddAnotherDependent = By.id("addAnother");
	By continueAlert = By.xpath("//div[@id='remindEnrollNewDependentModal']//button[contains(text(),'Continue')]");
	By dependentsTable = By.xpath("//div[@id='dependentSummary']");
	By depAdress = By.id("useEmployeeAddress-nativeHtmlElement");
	By address1 = By.id("physical_address1");
	By city = By.id("physical_city");
	By state = By.id("physical_state");
	By country = By.id("physical_country");
	By zip = By.id("physical_zip");
	String dependentsList = "//div[@class='legacy-table-container']//tbody//tr";
	By error = By.xpath("//div[@class='alert alert-error']");

	String depTable = "//table[@class='table table-hover table-bordered table-striped']";
	By remindAddDependent = By.id("remindEnrollNewDependentModal");
	By nextButtonAddDependents = By.xpath("//button[contains(text(),'Next')]");

	By editCoverage = By.xpath("//a[contains(text(),'Edit coverage')]");
	By personsCovered = By.xpath("//input[@id='selectedPersons-1']");
	By nextBenefitsPage = By.xpath("//button[contains(text(),'Next')]");
	By clickNext = By.xpath("(//button[contains(text(),'Next')]");
	By nextAgreementScreen = By.xpath("//button[contains(text(), 'Next')]");
	By saveBenefitChanges = By.id("completeEnrollment");
	By clickSaveMemberRole = By.id("submitForm");
	By agreementYes = By.id("answer[true]");

	By dependentActions = By.id("editHide");
	By removeDependent = By.linkText("Remove");
	By yesButton = By.xpath("//div[@id='hideDependentModal']//button[contains(text(),'Yes')]");

	//Private Methods

	//set address text box
	private void setAddress(String strAddress) {
		performAction.clearEnter(address1, strAddress, "Address Text Box");
	}

	//set City text box
	private void setCity(String strCity) {
		performAction.clearEnter(city, strCity, "city text Box");
	}
	//set state text box
	private void selectState(String strState) {
		performAction.select(state, strState, "Select State box");
	}
	//set country box
	private void selectCountry(String strCountry) {
		performAction.select(country, strCountry, "Selectig country");
	}
	//Set zip box
	private void setZip(String strZip) {
		performAction.clearEnter(zip, strZip, "Zip Text Box");
	}
	//perofrm click on employee address button
	private void clickEmployeeAddress() {
		performAction.click(depAdress, "Dependent Address Radio button");
	}
	// Click Add Dependent Button
	private void clickAddDependentButton() {
		performAction.click(addDependentButton, "Add Dependent Button");
	}

	// Click Add Dependent page Next Button
	private void clickAddDependentsPageNextButton() {
		System.out.println("Entered into the click");
		performAction.click(addDependentsPageNextButton,
				"Add Dependents Page Next Button");
		if(performAction.isAlertPresent()){
			performAction.acceptAlert();
		}
	}

	// set the firstName text box
	private void setFirstNameField(String strfirstName) {
		performAction.enter(firstName, strfirstName, "First Name Text Box");
	}

	// set the lastName text box
	private void setLastNameField(String strlastName) {
		performAction.enter(lastName, strlastName, "Last Name Text Box");
	}

	// set the birthDate text box
	private void setBirthDateField(String strbirthDate) {
		performAction.enter(birthDate, strbirthDate, "Date of Birth Text Box");
	}

	// click the gender radio button
	private void selectGenderField(String strgender) {
		if (strgender.equalsIgnoreCase("male")) {
			performAction.jsclick(mGender, "Gender Select Box");
		} else if (strgender.equalsIgnoreCase("female")) {
			performAction.jsclick(fGender, "Gender Select Box");
		}
	}

	// select the relationship combo box
	private void selectRelationship(String strValuetoSelect) {
		performAction.select(relationship, strValuetoSelect,
				"Relationship Select Box");
	}
    // set SSN Number
    private void setSSN(String strSSN) {
        performAction.enter(ssn, strSSN, "Enter SSN Number");
    }

	// click on save button
	private void clickSaveButton() {
		performAction.click(savebutton, "Save Button");
	}

    // click on save Dependent
    private void clickSaveDependent() {
        performAction.click(saveDependent, "Save Dependent Button");
    }

    // click on save and Add Another Dependent
    private void clickSaveAndAddAnotherDependent() {
        performAction.click(saveAndAddAnotherDependent, "Save and Add Another Dependent Button");
    }

	private void editCoverage() {
		performAction.waitUntilElementPresent(editCoverage);
		performAction.click(editCoverage, "Click Edit Coverage Link");
	}

	private void personsCovered() {
        performAction.waitForPageLoad();
		performAction.waitUntilElementPresent(personsCovered);
		performAction.click(personsCovered, "Click Persons Coveraged Radio Button");
	}

	private void saveBenefit() {
		//Thread.sleep(1000);
		performAction.waitUntilElementPresent(nextBenefitsPage);
		performAction.click(nextBenefitsPage, "Click Next");
		scr.capturePageScreenshot();
		performAction.waitForPageLoad();
		performAction.click(clickSaveMemberRole, "Click Save Member UI");
	}

	private void agreement() {
		performAction.waitUntilElementPresent(agreementYes);
		performAction.click(agreementYes, "Agree Agreement");
		performAction.click(nextAgreementScreen, "Click Next Agreement Screen");
	}

	private void completeEnrollment() {
		performAction.waitUntilElementPresent(saveBenefitChanges);
		scr.capturePageScreenshot();
		performAction.verifyMessage("Enrollment Complete!");
		performAction.click(saveBenefitChanges, "Click Save Benefit Changes Member UI");
	}

	private void deleteDependentFromMember() {
		performAction.click(dependentActions, "Click Actions for Dependent");
        performAction.waitForPageLoad();
		performAction.click(removeDependent, "Click Remove Dependent");
        performAction.waitForPageLoad();
		performAction.click(yesButton, "Click Yes Button for Deleting Dependent");
        performAction.waitForPageLoad();
		performAction.verifyMessage("Dependents");
	}

	private void clickContinueAlertForSaveDependent()
	{
		if (performAction.isElementPresent(remindAddDependent)) {
			String alertStatus = browser.getCurrentWebDriver()
					.findElement(remindAddDependent)
					.getAttribute("aria-hidden");
			System.out.println("Alert is..." + alertStatus);

			if (performAction.isDisplayed(nextButtonAddDependents, "Next")) {

				if (performAction.isElementPresent(continueAlert)
						|| alertStatus.toLowerCase() == "false") {
					performAction.click(continueAlert, "Continue Alert Box");

				}
			}
		}
	}

	private void clickContinueAlertForSaveAndAddAnotherDependent()
 {
		if (performAction.isElementPresent(remindAddDependent)) {
			String alertStatus = browser.getCurrentWebDriver()
					.findElement(remindAddDependent)
					.getAttribute("aria-hidden");
			System.out.println("Alert is..." + alertStatus);

			if (performAction.isElementPresent(continueAlert)
					|| alertStatus.toLowerCase() == "false") {
				performAction.click(continueAlert, "Continue Alert Box");

			}
			performAction.waitForPageLoad();
			performAction.verifyMessage("Use Employee Address");

		}
	}
	/*	// Method to navigate through Member initial login pages
	private void clickAddDependentsPageNextButton() {

		try {
			this.clickAddDependentsPageNextButton();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : Keyword or method used to add a dependent to member in Member role.
	 * For Save and Add Another Dependent, Pass buttonname within jsonkey as 'Save and Add Another'.
	 * For Save Dependent, passing the buttonname is not required.
	 * 
	 * PreCondition : Add Dependent page, test data should be provided as jsonkey. 
	 * If 'none' is provided as relationshiptype no dependent is added.
	 * 
	 * PostCondition : Dependent is added to the member and lastname is stored in out variable provided
	 *  
	 * <b>Parameters & Example </b> 
	 * | depRelationshipType | outLastName | 
	 * | td:spouse | spouselastname | 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "depRelationshipType", "outLastName" })
	public void addDependentToMember(String depRelationshipType,
			String outLastName) {
		String temp = depRelationshipType.substring(0, 3);
		try {
			if (depRelationshipType.startsWith("td:"))
				depRelationshipType = depRelationshipType.substring(3);

			if (depRelationshipType.equalsIgnoreCase("none")
					|| (depRelationshipType == null)) {
				this.clickAddDependentsPageNextButton();
			} else {

				Object object = null;
				JSONObject fields = ReadJsonTestData
						.getTestData(depRelationshipType.toLowerCase());

				this.clickAddDependentButton();

				object = fields.get("firstname");
				if (object != null) {
					this.setFirstNameField(object.toString().toString());
				}

				object = fields.get("lastname");
				if (object != null) {
					String value = utils
							.getValue(object.toString());
					this.setLastNameField(value);
					browser.hMap.put(outLastName, value);
				}

				object = fields.get("birthdate");
				if (object != null) {
					this.setBirthDateField(utils.getDate(fields
							.get("birthdate").toString()));
				}

				object = fields.get("gender");
				if (object != null) {
					this.selectGenderField(object.toString());
				}
				Thread.sleep(2000);
                object = fields.get("ssn");
                if (object != null) {
					String strSSN = object.toString();
                    this.setSSN(strSSN);
                }
				Thread.sleep(2000);
				if (temp.equalsIgnoreCase("td:")) {
					object = fields.get("relationship");
					if (object != null) {
						this.selectRelationship(object.toString());
					}
				} else {
					this.selectRelationship(depRelationshipType);
				}

				object = fields.get("buttonname");
				String strButton = "";
				if (object != null) {
					strButton = object.toString();
					if (strButton.equalsIgnoreCase("Save and Add Another")) {
						this.clickSaveAndAddAnotherDependent();
						this.clickContinueAlertForSaveAndAddAnotherDependent();
						scr.capturePageScreenshot();
					}
				} else {
				// submit the add new member form
                if (performAction.isElementPresent(saveDependent)) {
                    this.clickSaveDependent();
						this.clickContinueAlertForSaveDependent();
				}
				Thread.sleep(1000);
				this.clickAddDependentsPageNextButton();
				if(performAction.isAlertPresent()){
					performAction.acceptAlert();

				}
				}

			}

			scr.capturePageScreenshot();
		} catch (Exception e) {
			e.printStackTrace();
			scr.capturePageScreenshot();
			System.out.println("Exception occured " + e.getMessage());
			throw new CustomException("Exception while adding dependent "
					+ e.getMessage());
		}
	}

	/**
	 * Keyword to add a dependent to member in Member role Overloaded method to
	 * skip dependent addition by click on next button
	 * 
	 * <pre>
	 * <b>Parameters :</b>
	 * | depRelationshipType - String type argument used to input the Relation of the dependent | 
	 * <b>Example :</b>
	 * | none |
	 * </pre>
	 */
	/*	@RobotKeywordOverload
	@ArgumentNames({ "depRelationshipType" })
	public void addDependentToMember(String depRelationshipType) {
		System.out.println("No dependent will be added.");
		if (depRelationshipType.equalsIgnoreCase("none")
				|| (depRelationshipType == null)) {
			this.clickAddDependentsPageNextButton();
		}
	}*/

	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 * 
	 * Role : Member Role
	 * 
	 * Description : 'verifyDependentData' Keyword is used to verify a dependent for a member in Member role.
	 * 
	 * PreCondition : User should be in member home page.
	 * 
	 * PostCondition : Keyword should return whether the Dependent exist or not.
	 *  
	 * <pre>
	 * <b>Parameters :</b>
	 * | columnName - name of the column in the dependent table | value to be verified in the specified column | fieldName description of the verification |
	 * </pre> 
	 * 
	 * Java file Name : AddDependentPage.java
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "columnName", "value", "fieldName" })
	public void verifyDependentData(String columnName, String value,
			String fieldName) {

		performAction.verifyTableRowExists(depTable, columnName, value,
				fieldName);
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : addDependentWithAddress keyword or method is used to add a dependent with dependent's address to member in Member role 
	 *  
	 * 
	 * PreCondition : Member should be in Add Dependent page
	 * 
	 * PostCondition : New Dependent is saved with dependent's address successfully.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | depRelationshipType | outLastName |
	 * | Spouse / Child etc - is used to get the data set from the Json | lastname - is a variable name and it will be used to store the LastName of the Employee | 
	 * </pre> 
	 **/

	@RobotKeyword
	@ArgumentNames({ "depRelationshipType", "outLastName" })
	public void addDependentWithAddress(String depRelationshipType,
			String outLastName) {
		String temp = depRelationshipType.substring(0, 3);
		try {
			if (depRelationshipType.startsWith("td:"))
				depRelationshipType = depRelationshipType.substring(3);

			if (depRelationshipType.equalsIgnoreCase("none")
					|| (depRelationshipType == null)) {
				this.clickAddDependentsPageNextButton();
			} else {

				Object object = null;
				JSONObject fields = ReadJsonTestData
						.getTestData(depRelationshipType.toLowerCase());

				this.clickAddDependentButton();

				object = fields.get("firstname");
				if (object != null) {
					this.setFirstNameField(object.toString().toString());
				}

				object = fields.get("lastname");
				if (object != null) {
					String value = utils
							.generateRandomNumber(object.toString());
					this.setLastNameField(object.toString());
					browser.hMap.put(outLastName, value);
				}

				object = fields.get("birthdate");
				if (object != null) {
					this.setBirthDateField(utils.getDate(fields
							.get("birthdate").toString()));
				}

				object = fields.get("gender");
				if (object != null) {
					this.selectGenderField(object.toString());
				}

				if (temp.equalsIgnoreCase("td:")) {
					object = fields.get("relationship");
					if (object != null) {
						this.selectRelationship(object.toString());
					}
				} else {
					this.selectRelationship(depRelationshipType);
				}
				this.clickEmployeeAddress();

				object = fields.get("address");
				if (object != null) {
					this.setAddress(object.toString());
				}

				object = fields.get("city");
				if (object != null) {
					this.setCity(object.toString());
				}

				object = fields.get("state");
				if (object != null) {
					this.selectState(object.toString());
				}

				object = fields.get("country");
				if (object != null) {
					this.selectCountry(object.toString());
				}

				object = fields.get("zip");
				if (object != null) {
					this.setZip(object.toString());
				}

				scr.capturePageScreenshot();
				// submit the add new member form
				this.clickSaveButton();
				Thread.sleep(1000);
				if(performAction.isElementPresent(continueAlert)){
					performAction.click(continueAlert, "Continue Alert Box");
				}

				Assert.assertTrue("Dependents table", performAction
						.isElementPresent(dependentsTable, "Dependents Table"));
			}
			if(performAction.isElementPresent(addDependentsPageNextButton)){
				this.clickAddDependentsPageNextButton();
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception occured " + e.getMessage());
			throw new CustomException("Exception while adding dependent "
					+ e.getMessage());
		}
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : 'selectDependentInAddDependentsPage' keyword or method is used to select  dependent from the existed dependents in the add dependents page in New Member role 
	 *  
	 * Role :	New Member role
	 * 
	 * PreCondition : Member should be in Add Dependent page
	 * 
	 * PostCondition : Member will be redirected to plans page or guided shopping page after selecting the dependent.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | depRelationshipType |
	 * | Spouse / Child etc - is used to get the data set from the Json |
	 * 
	 *  JavaFileName : AddDependentPage.java
	 *  
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({"strRelationship"})
	public void selectDependentInAddDependentsPage(String strRelationship) {
		try {
			int dependentsCount = browser.getCurrentWebDriver().findElements(By.xpath(dependentsList)).size();

			for(int i=1 ; i<=dependentsCount; i++){
				String depRelation = browser.getCurrentWebDriver().findElement(By.xpath(dependentsList+"["+i+"]/td[3]//span[1]")).getText();
				if(depRelation.toLowerCase().equalsIgnoreCase(strRelationship)){
					By selectDependent = By.xpath("//input[@id='selectedDependent_"+(i-1)+"-nativeHtmlElement']");
					performAction.click(selectDependent, "Selecting dependent");
				}
			}
			scr.capturePageScreenshot();
			this.clickAddDependentsPageNextButton();
			scr.capturePageScreenshot();

		} catch (Exception e) {
			scr.capturePageScreenshot();
			System.out.println("Exception occured " + e.getMessage());
			throw new CustomException("Exception while selecting dependent "
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Remove Dependent From Benefits and Delete Dependent for Member in Member Role
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition : Benefits Page Opened in Member Role
	 * 
	 * PostCondition : Dependent Removed and Delete Dependent for Member in Member Role
	 * 
	 * Java File Name : AddDependentPage.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void removeDependentFromBenefits() {
		try {
			//Remove Dependent from Benefits
			this.editCoverage();
			this.personsCovered();
			performAction.waitForPageLoad();
			this.saveBenefit();
			performAction.waitForPageLoad();
			//this.agreement();
			this.completeEnrollment();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Exception in removing dependent from benefits"
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Remove Dependent From Benefits and Delete Dependent for Member in Member Role
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition : Benefits Page Opened in Member Role
	 * 
	 * PostCondition : Dependent Removed and Delete Dependent for Member in Member Role
	 * 
	 * Java File Name : AddDependentPage.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void deleteDependentForMember() {
		try {
			//Remove Dependent from Benefits
			this.deleteDependentFromMember();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Exception in deleting dependent for member"
					+ e.getMessage());
		}
	}
}
