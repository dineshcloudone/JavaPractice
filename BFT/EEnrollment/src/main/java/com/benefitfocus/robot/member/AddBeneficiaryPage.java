package com.benefitfocus.robot.member;

import junit.framework.Assert;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
public class AddBeneficiaryPage {

	@Autowired
	protected ActionKeywords performAction;

	@Autowired
	protected ManageBrowser browser;

	@Autowired
	protected Logging logger;
	
	@Autowired
	protected Screenshot screen;
	
	@Autowired
	protected Utilities utils;
	
	//Locators
	By benPerson = By.id("beneficiaryPerson");
	By benOrganization = By.id("beneficiaryOrganization");
	By benTrust = By.id("beneficiaryTrust");
	By nextButton = By.xpath("//button[contains(text(),'Next')]");
	By firstName = By.id("firstName");
	By lastName = By.id("lastName");
	By birthDate = By.id("dateOfBirth");
	By relationship = By.id("relationship");
	By ssn = By.id("ssnOrOrganizationId");
	By address1 = By.id("address1");
	By city = By.id("city");
	By state = By.id("state");
	By zip = By.id("zip");
	By country = By.id("country");
	By phone = By.id("phoneNumber");
	
	By bentable = By.xpath("//div[@class='legacy-table-container']");
	By ShowDetails = By.xpath("//span[contains(text(),'Show details')]");
	By verifyRelation = By.xpath("//dt[text()='Relationship']/following::dd[1]");
	By saveEnrollment = By.id("submitForm");
	By completeEnrollment = By.id("completeEnrollment");
	By addBeneficiary = By.xpath("//button[text()='Add Beneficiary ']");
	By hideBenficiary = By.id("hidden-nativeHtmlElement");
	String benficList = "//div[@class='legacy-table-container']//tbody";
	String dependentsList = "//div[@class='legacy-table-container']/table/tbody";
	By newBeneficiary = By.id("selectedDependentId[ADD_NEW_BENEFICIARY]");
	By previousButton = By.xpath("//button[contains(text(),'Previous ')]");
	By cancelButton = By.xpath("//button[contains(text(),'Cancel ')]");
	By yesButton = By.xpath("//div[@class='modal-dialog']//button[contains(text(),'Yes')]");
	By beginEnrollmentButton = By.linkText("Begin enrollment");
	
	//Private Methods
	
	// Perform click on Person beneficiary
	private void clickOnPerson() {
		performAction.click(benPerson, "Person Radio Butto");
	}
	
	// perform click on Organization radio button
	private void clickonOrganization() {
		performAction.click(benOrganization, "Organization Radio Button");
	}
	//perform click on trust radio button
	private void clickOnTrust() {
		performAction.click(benTrust, "Trust Radio Button");
	}
	//perform click on Next Button
	private void clickOnNext() {
		performAction.click(nextButton, "Next Button");
	}
	// set the firstName text box
	private void setFirstNameField(String strfirstName) {
		performAction.clearEnter(firstName, strfirstName, "First Name Text Box");
	}
	// set the lastName text box
	private void setLastNameField(String strlastName) {
		performAction.clearEnter(lastName, strlastName, "Last Name Text Box");
	}
	// set the birthDate text box
	private void setBirthDateField(String strbirthDate) {
		performAction.clearEnter(birthDate, strbirthDate, "Date of Birth Text Box");
	}
	// set the birthDate text box
	private void setSSNField(String strSSN) {
		performAction.clearEnter(ssn, strSSN, "SSN Text Box");
	}
	// set the relationship text box
	private void setRelationship(String strRelationshipValue) {
		performAction.clearEnter(relationship, strRelationshipValue,
					"Relationship Text Box");
		}
	
	// set the address1 textbox
	private void setAddress1Field(String straddress1) {
		performAction.clearEnter(address1, straddress1, "Address Text Box");
	}

	// set the city text box
	private void setCityField(String strcity) {
		performAction.clearEnter(city, strcity, "City Text Box");
	}

	// set the state combo box
	private void selectStateField(String strValuetoSelect) {
		performAction.select(state, strValuetoSelect, "State Combo Box");
	}

	// set the zip text box
	private void setZipField(String strzip) {
		performAction.clearEnter(zip, strzip, "Zip Code Text Box");
	}

	// set the Country combo box
	private void selectcountryField(String strValuetoSelect) {
		performAction.select(country, strValuetoSelect, "Country Combo Box");
	}
	
	// Set Home Phone Number for the Employee
	private void setPhone(String strValuetoSelect) {
		performAction.clearEnter(phone, strValuetoSelect,
				" Employer Phone number");
	}
	
	// Select Beneficiary Type
	private void clickShowDetails() {
		performAction.click(ShowDetails, "Show Details");
		}
	//Perform Add Beneficiary Button
		private void clickAddBeneficiary(){	
			performAction.click(addBeneficiary, "Add Another Beneficiary");
		}
	//Perform Complete Enrollment Button
		private void clickCompleteEnrollemnt() {
			//performAction.click(completeEnrollment, "Complete Enrollment");
			WebDriverWait wait = new WebDriverWait(browser.getCurrentWebDriver(), 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(completeEnrollment));
			wait.until(ExpectedConditions.elementToBeClickable(completeEnrollment));
			logger.info("Wait for completeEnrollment");
			//browser.getCurrentWebDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			performAction.jsclick(completeEnrollment, "Begin Enrollment button in Your Benefits page");
		}
		
	//selects another primary beneficiary
		private void selectBeneficiary() {
			try {
				System.out.println("Entered Select Beneficiary");
				int BenficiaryList = browser.getCurrentWebDriver().findElements(By.xpath(benficList+"/tr")).size();
				System.out.println("Beneficiary List..." +BenficiaryList);
				for(int i = 0 ; i<BenficiaryList; i++)
				{
					System.out.println("Entered loop");
					By memberCheck = By.id("beneficiaryList["+ i +"].selectedToBeCovered-nativeHtmlElement");
					String empValue = browser.getCurrentWebDriver().findElement(memberCheck).getAttribute("value");
					System.out.println("member value is....."  +empValue);
					if(empValue.equalsIgnoreCase("true")){
					performAction.click(memberCheck, "Member Check");	
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	private void selectNewBeneficiary() {
		if(performAction.isElementPresent(newBeneficiary)){
			performAction.click(newBeneficiary, "Selecting new Beneficiary");
			performAction.click(nextButton, "Next Button");
		}
	}

	//Click on previous Button and verify
	private void clickPreviousButton(){
		performAction.click(previousButton, "Previous Button");
		performAction.waitForPageLoad();
		screen.capturePageScreenshot();
		performAction.verifyMessage("You have no beneficiaries either populated with their Beneficiary Type, allocation amount, and/or selected to be covered.");
	}
	private void clickCancelButton(){
		performAction.click(cancelButton, "Cancel Button");
		performAction.waitForPageLoad();
		performAction.click(yesButton, "Click Yes Button for canceling Beneficiary data");
		performAction.waitForPageLoad();
		screen.capturePageScreenshot();
		Assert.assertEquals(true, performAction.isElementPresent(beginEnrollmentButton));
	}

	private void editBeneficiary(String StrBenificiaryType){
		if (StrBenificiaryType.startsWith("HMV")) {
			StrBenificiaryType = utils.getValue(StrBenificiaryType);
		}
		performAction.waitForPageLoad();
		By editButton = By.xpath("//label[contains(text(),'"+ StrBenificiaryType + "')]/parent::td//following-sibling::td//button[contains(text(),'Edit')]");
		performAction.click(editButton, "Edit Button");
	}

	private void verifyEditBeneficiaryData(String outSecLastName){
		performAction.verifyMessage(browser.hMap.get(outSecLastName));
		screen.capturePageScreenshot();
	}

	//Robot KeyWords
		/**
	     * <pre> 
	      * Author  : Nagarjuna Thallam
	     *  
	      * Description : selectBeneficiayType keyword or method is used  to select Beneficiary Type for a Life plan while adding Beneficiary in New member role
	     * 
	     * Role : New Member Role
	     * 
	      * PreCondition : Member should be in Beneficiary Type Listing page 
	     * 
	      * PostCondition : member will be redirected to the beneficiary information page.
	     *  
	      * <b>Parameters & Example </b> 
	      * 
	      * | StrBenType |
	      * | Person / Organization / Trust  | 
	      * 
	      *   JavaFileName : AddBeneficiaryPage.java
	      * </pre> 
	      **/
		
	@RobotKeyword
	@ArgumentNames({"StrBenType"})
	public void selectBeneficiayType(String StrBenType){
		try {
			if(StrBenType.toLowerCase().equalsIgnoreCase("person")){
				this.clickOnPerson();
				this.clickOnNext();
			}	
			else if(StrBenType.toLowerCase().equalsIgnoreCase("organization"))
			{
				this.clickonOrganization();
				this.clickOnNext();
			}
			else if(StrBenType.toLowerCase().equalsIgnoreCase("trust")){
				this.clickOnTrust();
				this.clickOnNext();
			}
			else{
				logger.info("No Beneficiarys available ");
				throw new CustomException("No Beneficiarys available");
			}
		} catch (Exception e) {
			throw new CustomException("Exception in Selecting beneficiary Type "
					+ e.getMessage());
		}
		
		
	}
	/**
      * <pre> 
      * Author  : Nagarjuna Thallam
      *  
      * Description : addBeneficiaryToMember keyword or method is used Add new Beneficiary to the Employee in New member role
      *  
      * Role : New Member Role
      * 
      * PreCondition : Member should be in Beneficiary Information page
      * 
      * PostCondition : New Beneficiary is saved successfully.
      *
      * <b>Parameters & Example </b> 
      * 
      * | StrRelationship | outLastName | 
      * | Spouse / Child etc - is used to get the data set from the Json |lastname - is a variable name and it will be used to store the LastName of the Employee  |
      * 
      *  JavaFileName : AddBeneficiaryPage.java
      * </pre> 
      **/
	
	@RobotKeyword
	@ArgumentNames({"strRelationship","outLastName"})
	public void addBeneficiaryToMember(String strRelationship,
			String outLastName) {
		try {
			
			if (strRelationship != null) {
				Object object = null;
				JSONObject fields = ReadJsonTestData
						.getTestData(strRelationship.toLowerCase());
				
				object = fields.get("firstname");
				if (object != null) {
					this.setFirstNameField(object.toString().toString());
				}

				object = fields.get("lastname");
				if (object != null) {
					String value = utils
							.generateRandomNumber(object.toString());
					this.setLastNameField(value);
					browser.hMap.put(outLastName, value);

					object = fields.get("relationship");
					if (object != null) {
						this.setRelationship(object.toString().toString());
					} else {
						this.setRelationship(strRelationship);
					}

					object = fields.get("ssn");
					if (object != null) {
						this.setSSNField(object.toString().toString());
					}

					object = fields.get("birthdate");
					if (object != null) {
						this.setBirthDateField(utils.getDate(fields.get(
								"birthdate").toString()));
					}
					
					object = fields.get("address1");
					if (object != null) {
						this.setAddress1Field(object.toString());
					}

					object = fields.get("city");
					if (object != null) {
						this.setCityField(object.toString());
					}

					object = fields.get("state");
					if (object != null) {
						this.selectStateField(object.toString());
					}

					object = fields.get("zipcode");
					if (object != null) {
						this.setZipField(object.toString());
					}

					object = fields.get("country");
					if (object != null) {
						this.selectcountryField(object.toString());
					}
					
					object = fields.get("phone");
					if (object != null) {
						this.setPhone(object.toString());
					}
					object = fields.get("buttonname");
					if (object != null) {
						String buttonName=object.toString();
						if (buttonName.equalsIgnoreCase("previous")){
							this.clickPreviousButton();

						}
						else if(buttonName.equalsIgnoreCase("cancel")){
							this.clickCancelButton();
						}
						else {
							this.clickOnNext();
						}
					}
					else
					this.clickOnNext();
					screen.capturePageScreenshot();
				}
			}
		} catch (Exception e) {
			screen.capturePageScreenshot();
			throw new CustomException("Exception in while adding beneficiary "
					+ e.getMessage());
		}
	}
	/**
     * <pre> 
      * Author  : Nagarjuna Thallam
     *  
      * Description : addBeneficiaryAllocations keyword or method is used Add Beneficiary Allocation to the added beneficiary in New member role 
      *  
     * Role : New Member Role
     * 
      * PreCondition : Member should be in Beneficiary Information page
     * 
      * PostCondition : New Beneficiary is saved successfully.
	 *
     *  
      * <b>Parameters & Example </b> 
      * 
      * | StrRelationship | StrBeneficiaryType | StrAlloctaion |
      * | Spouse / Child - String type argument takes relationship as input to add beneficiary  |Primary / Secondary  - String type argument takes Beneficiary type as input value | % allocation for beneficiary |
      *
	 *   JavaFileName : AddBeneficiaryPage.java
	 *
      * </pre> 
      **/
	
	@RobotKeyword
	@ArgumentNames({"StrRelationship","StrBeneficiaryType","StrAlloctaion"})
	public void addBeneficiaryAllocations(String StrRelationship, String StrBeneficiaryType, String StrAlloctaion ){
		try {
			if(performAction.isElementPresent(bentable)){
				
				int BenficiaryList = browser.getCurrentWebDriver().findElements(By.xpath(benficList+"/tr")).size();
				this.selectBeneficiary();
				for(int i =1 ; i<BenficiaryList; i++)
				{	
					
					String Relationship = browser.getCurrentWebDriver().findElement(By.xpath(benficList+"/tr[" + i +"]//td[3]")).getText();
					System.out.println("Relationship is..." +Relationship);
					if(StrRelationship.toLowerCase().equalsIgnoreCase(Relationship.toLowerCase())){
						for(int x =i-1 ; x<i; x++)
						{
							By memberCheck = By.id("beneficiaryList["+ x +"].selectedToBeCovered-nativeHtmlElement");
							String empValue = browser.getCurrentWebDriver().findElement(memberCheck).getAttribute("value");
							if(empValue.equalsIgnoreCase("false")){
							performAction.click(memberCheck, "Member Check");	
							}
						}
						By BeneficiaryType = By.xpath(benficList+"/tr[" + i +"]//select");
						performAction.select(BeneficiaryType, StrBeneficiaryType, "Beneficiary Type");
						By Allocation = By.xpath(benficList+"/tr[" + i +"]//input[@type='text']");
						performAction.clearEnter(Allocation, StrAlloctaion, "Allocation Amount");
						break;
					}
				}
				screen.capturePageScreenshot();
				this.clickOnNext();
			}
		} catch (Exception e) {
			screen.capturePageScreenshot();
			throw new CustomException("Exception in while allocating beneficiary "
					+ e.getMessage());
		}
	}
	/**
     * <pre> 
      * Author  : Nagarjuna Thallam
     *  
      * Description : verifyBeneficiaryAndSave keyword or method is used  to verify the Beneficiary relationship and save the beneficiary in New member role
     * 
     * Role: New Member Role
     * 
      * PreCondition : Member should be in Beneficiary's Information page 
     * 
      * PostCondition : member will be redirected to offers page.
     *  
      * <b>Parameters & Example </b> 
      * 
      * | StrRelationship |
      * |  Spouse / Child - is a relationship variable to verify |   
      * 
      *  JavaFileName : AddBeneficiaryPage.java
      * </pre> 
      * 
      **/
	
	@RobotKeyword
	@ArgumentNames({"StrRelationship"})
	public void verifyBeneficiaryAndSave(String StrRelationship) {
		try {
			this.clickShowDetails();
			performAction.verify(verifyRelation, StrRelationship , "RelationShip Verify");
			screen.capturePageScreenshot();
			performAction.click(saveEnrollment, "Save Enrollment");
			this.clickCompleteEnrollemnt();
			screen.capturePageScreenshot();
		} catch (Exception e) {
			screen.capturePageScreenshot();
			throw new CustomException("Exception in while saving beneficiary "
					+ e.getMessage());
		}
	}
	/**
     * <pre> 
      * Author  : Nagarjuna Thallam
     *  
      * Description : addAnotherPrimaryBeneficiary keyword or method is used Add Another new Beneficiary to the Employee in New member role 
      *  
     * Role: New Member Role
     * 
      * PreCondition : Member should be in Beneficiary Information page
     * 
      * PostCondition : New Beneficiary is saved successfully.
     *  
      * <b>Parameters & Example </b> 
      * 
      * | StrBenType | strRelationship | outLastName |
      * | Person / Organization  |  Spouse / Child etc - is used to get the data set from the Json | lastname - is a variable name and it will be used to store the LastName of the Employee | 
      * 
      *  JavaFileName : AddBeneficiaryPage.java
      * </pre> 
      **/
	
	@RobotKeyword
	@ArgumentNames({"StrBenType","strRelationship","outLastName"})
	public void addAnotherPrimaryBeneficiary(String StrBenType, String strRelationship, String outLastName ) {
		try {
			this.clickAddBeneficiary();
			this.selectBeneficiayType(StrBenType);
			this.addBeneficiaryToMember(strRelationship, outLastName);
			JSONObject fields = ReadJsonTestData
					.getTestData(strRelationship.toLowerCase());
			String relation = fields.get("relationship").toString();
			this.addBeneficiaryAllocations(relation, "Primary", "100");
			screen.capturePageScreenshot();
			
		} catch (Exception e) {
			screen.capturePageScreenshot();
			throw new CustomException("Exception in while Adding another primary Beneficiary"
					+ e.getMessage());
		}
	}
	/**
     * <pre> 
      * Author  : Nagarjuna Thallam
     *  
      * Description : hideBeneficiary keyword or method is used to Hide the Beneficiary in New member role in New member role 
      *  
      *  Role: NEw Member Role
     * 
      * PreCondition : Member should be in Beneficiary Information page
     * 
      * PostCondition : Beneficiary will be hidden successfully.
     *  
      * <b>Parameters & Example </b> 
      * 
      * | StrRelationship |
      * |  Spouse / Child - String type argument takes Relationship type as input value  |
      * 
      *  JavaFileName : AddBeneficiaryPage.java
      * <pre>
      **/
	
	@RobotKeyword
	@ArgumentNames({"StrRelationship"})
	public void hideBeneficiary(String StrRelationship) {
		try {
			int BenficiaryList = browser.getCurrentWebDriver().findElements(By.xpath(benficList+"/tr")).size();
			logger.info("Beneficiary List..." +BenficiaryList);
			logger.info("StrRelationship is ....."  +StrRelationship);
			if(StrRelationship.equalsIgnoreCase(null)){
			for(int i = 0 ; i<BenficiaryList; i++)
			{		
				By memberCheck = By.id("beneficiaryList["+ i +"].selectedToBeCovered-nativeHtmlElement");
				// taking the beneficiary status value
				String empValue = browser.getCurrentWebDriver().findElement(memberCheck).getAttribute("value");
				// if that beneficiary is not selected in the list
				if(empValue.equalsIgnoreCase("false")){
					By editBenefciary = By.xpath(benficList+"/tr[" + i+1 +"]//td[8]//button");
					performAction.click(editBenefciary, "Member Check");	
					performAction.waitUntilElementPresent(hideBenficiary);
					performAction.click(hideBenficiary, "Hide beneficiary Check box");
					this.clickOnNext();
					this.clickOnNext();
					screen.capturePageScreenshot();
					break;
					}
				}
			}
			else{
				for(int i =0 ; i<BenficiaryList; i++)
				{	
					
					String Relationship = browser.getCurrentWebDriver().findElement(By.xpath(benficList+"/tr[" + (i+1) +"]//td[3]")).getText();
					System.out.println("Relationship is..." +Relationship);
					if(StrRelationship.toLowerCase().equalsIgnoreCase(Relationship.toLowerCase())){
						By editBenefciary = By.xpath(benficList+"/tr[" + (i+1) +"]//td[8]//button");
						performAction.click(editBenefciary, "Member Check");	
						performAction.waitUntilElementPresent(hideBenficiary);
						performAction.click(hideBenficiary, "Hide beneficiary Check box");
						this.clickOnNext();
						this.clickOnNext();
						screen.capturePageScreenshot();
						break;
					}
				}
				
			}
		} catch (Exception e) {
			screen.capturePageScreenshot();
			throw new CustomException("Exception in while Editing the Beneficiary"
					+ e.getMessage());
		}
	}
	/**
     * <pre> 
      * Author  : Nagarjuna Thallam
     *  
      * Description : 'selectDependentAsBeneficiary' keyword or method is used to select existed dependent as beneficiary in life plans in New member role 
      *  
     * Role	: New Member role
     * 
      * PreCondition : Member should be in Beneficiary Information page
     * 
      * PostCondition : Beneficiary will be redirected to Beneficiary allocations page.
     *  
      * <b>Parameters & Example </b> 
      * 
      * | StrRelationship |
      * |  Spouse / Child - String type argument takes Relationship type as input value  |
      * 
      *  JavaFileName : AddBeneficiaryPage.java
      * <pre>
      **/
	@RobotKeyword
	@ArgumentNames({"strRelationship"})
	public void selectDependentAsBeneficiaryInMemberrole(String strRelationship) {
		try {
			if(strRelationship.toLowerCase().equalsIgnoreCase("enter new beneficiary")){
				performAction.click(newBeneficiary, "Selecting new Beneficiary");
				performAction.click(nextButton, "Next Button");
				
			}
			else{
			int depCount = browser.getCurrentWebDriver().findElements(By.xpath(dependentsList)).size();
			
			for(int i=1 ;i<=depCount;i++){
				String depRelation = browser.getCurrentWebDriver().findElement(By.xpath(dependentsList+"/tr["+i+"]/td[3]//span[1]")).getText();
				if(depRelation.toLowerCase().equalsIgnoreCase(strRelationship.toLowerCase())){
					By selectDependent = By.xpath(dependentsList+"/tr["+i+"]//input");
					performAction.click(selectDependent, "Selecting dependent");
				}
			}
			performAction.click(nextButton, "Next Button");
			performAction.waitUntilElementPresent(nextButton);
			performAction.click(nextButton, "Next Button");
			}
			
		} catch (Exception e) {
			screen.capturePageScreenshot();
			throw new CustomException("Exception in while Editing the Beneficiary"
					+ e.getMessage());
		}

	}

	/**
	 * <pre>
	 * Author  : Srilatha A
	 *
	 * Description : clickPreviousCancelButtonToAddBeneficiaryData keyword or method is used verify new Beneficiary data
	 * is not saved when we click on Previous/Cancel Button. Button type (Previous/Cancel) should be passed in Json file.
	 *
	 * Role: New Member Role
	 *
	 * PreCondition : Member should be in Enter Beneficiary Information page
	 *
	 * PostCondition : Beneficiary information should not be saved if Cancel/Previous buttons are clicked.
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | StrBenificiaryType | strBeneficiaryData | outLastName |
	 * | Person / Organization  |  Spouse / Child etc - is used to get the data set from the Json | lastname - is a variable name and it will be used to store the LastName of the Employee |
	 *
	 *  JavaFileName : AddBeneficiaryPage.java
	 * </pre>
	 **/

	@RobotKeyword
	@ArgumentNames({"StrBenificiaryType","strBeneficiaryData","outLastName"})
	public void verifyPreviousCancelButtonInAddBeneficiaryPage(String StrBenificiaryType, String strBeneficiaryData, String outLastName ) {
		try {
			this.clickAddBeneficiary();
			this.selectBeneficiayType(StrBenificiaryType);
			this.selectNewBeneficiary();
			this.addBeneficiaryToMember(strBeneficiaryData, outLastName);
			screen.capturePageScreenshot();
		} catch (Exception e) {
			screen.capturePageScreenshot();
			throw new CustomException("Exception while clicking on Previous Cancel Button To Add Beneficiary Data"
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Srilatha A
	 *
	 * Description : editdBeneficiaryData keyword or method is used edit Beneficiary data and Click on Next Button.
	 *
	 * Role: New Member Role
	 *
	 * PreCondition : Member should be in Beneficiary Information page after clicking on Edit Button
	 *
	 * PostCondition : Updated Beneficiary information should be saved
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | StrBenificiaryType | strBeneficiaryData | outLastName |
	 * | Person / Organization  |  Spouse / Child etc - is used to get the data set from the Json | lastname - is a variable name and it will be used to store the LastName of the Employee |
	 *
	 *  JavaFileName : AddBeneficiaryPage.java
	 * </pre>
	 **/

	@RobotKeyword
	@ArgumentNames({"StrBenificiaryType", "strBeneficiaryData","outLastName"})
	public void editBeneficiaryData(String StrBenificiaryType, String strBeneficiaryData, String outSecLastName ) {
		try {

			this.editBeneficiary(StrBenificiaryType);
			this.addBeneficiaryToMember(strBeneficiaryData, outSecLastName);
			screen.capturePageScreenshot();
			this.verifyEditBeneficiaryData(outSecLastName);
		} catch (Exception e) {
			screen.capturePageScreenshot();
			throw new CustomException("Exception while editing primary Beneficiary data"
					+ e.getMessage());
		}
	}
	
}

