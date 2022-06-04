package com.benefitfocus.robot.vista.groups.users;

import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
public class AddMemberPage {

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

    // Locators on the page
    By ssn = By.id("SSN");
    By firstName = By.id("firstName");
    By lastName = By.id("lastName");
    By birthDate = By.id("birthDate");
    By gender = By.id("gender");
    By maritalStatus = By.id("maritalStatus");
    By address1 = By.id("address1");
    By city = By.id("city");
    By state = By.id("state");
    By zip = By.id("zip");
    By country = By.id("country");
    By doNotCreateLoginAccount = By.id("doNotCreateLoginAccount");
    By memberID = By.id("memberID");
    By password = By.id("password");
    By confirmPassword = By.id("confirmPassword");
    By hireDate = By.id("hireDate");
    By earnings = By.id("earnings");
    By earningsFrequency = By.id("payFrequency");
    By payFrequency = By.id("payPeriod");
    By calendarSetUrl = By.id("calendarSetUrl");
    By plan = By.id("categorySelections");
    By catogories = By.id("categorySelections");
    By employerId = By.id("employerID");
    By savebutton = By.linkText("Save");
    By errorList = By.id("errorListContainerForEntirePage");
    By homePhoneNum = By.id("homePhone");
    By eMail = By.id("homeEmail");
    By race = By.id("race");
    By continueAddressbutton = By
            .xpath("//b[contains(text(),'Continue with address you entered')]");
    //Payroll
    By eeoc = By.id("eeoc");
    By hoursPerWeek = By.id("weeklyHours");
    By salaryBasedBenefitsEarningsAmount = By.id("salaryBasedBenefitsEarningsAmount");
    By benefitSalaryFrequency = By.id("salaryBasedBenefitsEarningsClass");
    By occupation=By.id("occupation");
    String memberResultsRowCount="//tr[contains(@class,'Passive')]";
    By updateMemberIdentifier=By.linkText("Update Member Identifiers");
    String carrierRowCount="//div[@class='secondaryRegion']";

    // set the ssn text box
    private void setSSNField(String strssn) {
        performAction.enter(ssn, strssn, "ssn textbox");
    }

    // set the firstName text box
    private void setFirstNameField(String strfirstName) {
        performAction.enter(firstName, strfirstName, "firstname textbox");
    }

    // set the lastName text box
    private void setLastNameField(String strlastName) {
        performAction.enter(lastName, strlastName, "lastname textbox");
    }

    // set the birthDate text box
    private void setBirthDateField(String strbirthDate) {
        performAction.enter(birthDate, strbirthDate, "brithdate textbox");
    }

    // set the gender combo box
    private void selectGenderField(String strValuetoSelect) {
        performAction.select(gender, strValuetoSelect, "gender combobox");
    }

    // set the address1 textbox
    private void setAddress1Field(String straddress1) {
        performAction.enter(address1, straddress1, "address field textbox");
    }

    // set the city text box
    private void setCityField(String strcity) {
        performAction.enter(city, strcity, "city field textbox");
    }

    // set the state combo box
    private void selectStateField(String strValuetoSelect) {
        performAction.select(state, strValuetoSelect, "state combobox");
    }

    // set the state combo box
    private void selectMaritalStatusField(String strValuetoSelect) {
        performAction.select(maritalStatus, strValuetoSelect,
                "Marital Status combobox");
    }

    // set the zip text box
    private void setZipField(String strzip) {
        performAction.enter(zip, strzip, "zip field textbox");
    }

    // set the state combo box
    private void selectCountryField(String strValuetoSelect) {
        performAction.select(country, strValuetoSelect, "country combobox");
    }

    // set the doNotCreateLoginAccount combo box
    private void selectCreateLoginField(String strValuetoSelect) {
        performAction.select(doNotCreateLoginAccount, strValuetoSelect,
                "create login account combobox");
    }

    // set the memberID text box
    private void setMemberIDField(String strmemberID) {
        performAction.clearEnter(memberID, strmemberID,
                "memberid field textbox");
    }

    // set the password text box
    private void setPasswordField(String strpassword) throws Exception {
        performAction.clearEnter(password, strpassword,
                "password field textbox");
    }

    // set the confirmPassword text box
    private void setConfirmPasswordField(String strconfirmPassword) {
        performAction.clearEnter(confirmPassword, strconfirmPassword,
                "confirmpassword field textbox");
    }

    // set the hireDate text box
    private void setHireDateField(String strhireDate) throws Exception {
        performAction.enter(hireDate, strhireDate, "hiredate textbox");
    }

    // set the hireDate text box
    private void setEarnings(String strearnings) throws Exception {
        performAction.enter(earnings, strearnings, "earnings textbox");
    }

    // set the calendar text box
    private void selectCalendarSet(String strValuetoSelect) throws Exception {
        performAction.select(calendarSetUrl, strValuetoSelect,
                "calendarSet combobox");
    }

    // set the pay frequency text box
    private void selectEarningsFrequency(String strValuetoSelect)
            throws Exception {
        performAction.select(earningsFrequency, strValuetoSelect,
                "earnings combobox");
    }

    // set the pay period text box
    private void selectPayFrequency(String strValuetoSelect) throws Exception {
        performAction.select(payFrequency, strValuetoSelect,
                "payfrequency combobox");
    }

    // click on save button
    private void clickSavebutton() throws Exception {
        performAction.click(savebutton, "save button");
    }

    // Enter Employer Assign id
    private void enterEmployerId(String strValuetoSelect) {
        performAction.enter(employerId, strValuetoSelect,
                "Employer Assigned ID");
    }

    // Set Home Phone Number for the Employee
    private void setHomePhone(String strValuetoSelect) {
        performAction.clearEnter(homePhoneNum, strValuetoSelect,
                " Employer Phone number");
    }

    // Set Personal Email
    private void setPersonalEmail(String strValuetoSelect) {
        performAction.clearEnter(eMail, strValuetoSelect, "Personal Email");
    }

    //Race
    private void selectRace(String strRace) {
        performAction.select(race, strRace, "Race");
    }

    // set the pay period text box
    private void selectEEOCValue(String strEEOCValue) throws Exception {
        performAction.select(eeoc, strEEOCValue,
                "Select EEOC value");
    }

    // set the hours worked per week text box
    private void enterHoursPerWeek(String strhours) throws Exception {
        performAction.enter(hoursPerWeek, strhours, "hours Per week");
    }

    // set the benefit Salary text box
    private void setBenefitSalary(String strbenefitsalary) throws Exception {
        performAction.enter(salaryBasedBenefitsEarningsAmount, strbenefitsalary, "benefit salary textbox");
    }

    // select the benefit salary frequency list
    private void selectBenefitSalaryFrequency(String strValuetoSelect)
            throws Exception {
        performAction.select(benefitSalaryFrequency, strValuetoSelect,
                "BenefitSalary combobox");
    }
    // set the occupation text box
    private void setOccupation(String strOccupation) {
        performAction.enter(occupation, strOccupation, "ssn textbox");
    }

    /**
     * selectCategories keyword to get the list of categories available under
     * 'work information' section on the Add member page
     * <p/>
     * like Benefits / Classification eg : Plan / location - for BF QA test
     * Automation Group
     * <p/>
     * <pre>
     * <b>Parameters :</b>
     * | strCategory - categories to select |
     * </pre>
     */
    @RobotKeyword
    @ArgumentNames({"*strCategory"})
    private void selectCategories(String strCategory[]) {

        try {

            List<WebElement> categories = browser.getCurrentWebDriver()
                    .findElements(catogories);

            if (categories.size() > 0) {
                int cateIndex = 0;

                if (categories.size() == strCategory.length) {

                    for (int i = 0; i < categories.size(); i++) {

                        Select cate = new Select(categories.get(i));
                        if (strCategory.equals("RND")) {
                            cate.selectByIndex((int) (Math.random() * cate
                                    .getOptions().size()));
                        } else {
                            cate.selectByVisibleText(strCategory[cateIndex]);
                            cateIndex++;
                        }
                        logger.info("Selected category option :"
                                + cate.getFirstSelectedOption().getText());
                    }
                } else {

                    for (int i = 0; i < categories.size(); i++) {
                        if (categories.get(i).getAttribute("onfocus") != null) {

                            Select cate = new Select(categories.get(i));
                            if (strCategory.equals("RND")) {
                                cate.selectByIndex((int) (Math.random() * cate
                                        .getOptions().size()));
                            } else {
                                cate.selectByVisibleText(strCategory[cateIndex]);
                                cateIndex++;
                            }
                            logger.info("Selected category option :"
                                    + cate.getFirstSelectedOption().getText());
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Exception occured while selecting categories"
                    + e.getMessage());
            throw new CustomException(
                    "Exception occured while selecting categories "
                            + e.getMessage());
        }
    }

    // Click on Member edit Button from Search Results
    private void clickEditButton(String strMemberUserName){

    	int noOfRowsInSearchResults=browser.getCurrentWebDriver().findElements(By.xpath(memberResultsRowCount)).size();
    	//logger.info("Number of Rows ="+noOfRowsInSearchResults);
    	logger.info("Member Name ="+strMemberUserName);
    	if(noOfRowsInSearchResults>0){
    		for(int i=1;i<=noOfRowsInSearchResults;i++){

    			String actualRowText=browser.getCurrentWebDriver().findElement(By.xpath(memberResultsRowCount+"["+i+"]")).getText();
    			if(actualRowText.trim().contains(strMemberUserName)){
    				//logger.info("Entered If Loop");
    				//logger.info(memberResultsRowCount+"["+i+"]"+"/td//a");
    				performAction.click(By.xpath(memberResultsRowCount+"["+i+"]"+"/td//a"), "Click on Member Edit Icon");
    				break;
    			}

    		}
    	}
    	if(noOfRowsInSearchResults==0){
    		logger.info("No Search results Found");
    	}
    }
    // Click on Update Member Identifier
    private void clickMemberIdentifier(){
    	performAction.click(updateMemberIdentifier, "Click on Update Member Identifier");
    }
    // Enter Identifier Information
    private void enterIdentifierInformation(String CarrierName,String Identifier,String value){
        //logger.info("Enter Carrier Information Keyword Called");
    	int noOfCarrierRows=browser.getCurrentWebDriver().findElements(By.xpath(carrierRowCount)).size();
        logger.info("Carriers Row Count="+noOfCarrierRows);
        if(noOfCarrierRows>0){

            for(int i=1;i<=noOfCarrierRows;i++){

    			String actualRowText=browser.getCurrentWebDriver().findElement(By.xpath(carrierRowCount+"["+i+"]")).getText();
    			logger.info("Actual Text ==> "+actualRowText);
    			if(actualRowText.trim().contains(CarrierName)){
    				//i++;
    				if(Identifier.equalsIgnoreCase("Member Identifier")){
    					//logger.info(carrierRowCount+"["+i+"]"+"//tr[1]//input");
    					performAction.clearEnter(By.xpath(carrierRowCount+"["+i+"]"+"//tr[1]//input"),value, "Enter Member Identifier");
    				}else{
    					performAction.clearEnter(By.xpath(carrierRowCount+"["+i+"]"+"//tr[2]//input"),value, "Enter Alternate Identifier");
    				}
    				break;
    			}
    		}



        }
        if(noOfCarrierRows==0){
    		logger.info("No Carrier Rows Found");
    	}

    }


    /**
     * <pre>
     * Author  : Ch Phani Srikar
     *
     * Description : addMemberInVista keyword or method is used to perform adding an Employee operation in vista Role.
     *
     * PreCondition : Add Member page in vista Role
     *
     * PostCondition : New member is saved successfully.
     *
     * <b>Parameters & Example </b>
     *
     * | classification | outLastName |
     * | hradminmandatory - is used to get the data set from the Json | lastname - is a variable name and it will be used to store the LastName of the Employee |
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({"classification", "outLastName"})
    public void addMemberInVista(String classification, String outLastName) {

        try {

            if (classification.startsWith("td:"))
                classification = classification.substring(3);

            Object object = null;
            JSONObject fields = ReadJsonTestData.getTestData(classification
                    .toLowerCase());
            System.out.println("fields = " + fields.toJSONString());

            object = fields.get("ssn");
            if (object != null) {
                String value = utils.generateRandomNumber(object.toString());
                this.setSSNField(value);
                logger.info("value : " + value);
                browser.hMap.put("strssn", value);
            }

            object = fields.get("firstname");
            if (object != null) {
                this.setFirstNameField(object.toString().toString());
            }

            object = fields.get("lastname");
            if (object != null) {
                String value = utils.generateRandomNumber(object.toString());
                this.setLastNameField(value);
                logger.info("value : " + value);
                browser.hMap.put(outLastName, value);
            }

            object = fields.get("birthdate");
            if (object != null) {
                this.setBirthDateField(utils.getDate(fields.get("birthdate")
                        .toString()));
            }

            object = fields.get("race");
            if (object != null) {
                this.selectRace(object.toString());
            }

            object = fields.get("eeoc");
            if (object != null) {
                this.selectEEOCValue(object.toString());
            }
            object = fields.get("gender");
            if (object != null) {
                this.selectGenderField(object.toString());
            }

            object = fields.get("maritalstatus");
            if (object != null) {
                this.selectMaritalStatusField(object.toString());
            }

            object = fields.get("address1");
            if (object != null) {
                this.setAddress1Field(object.toString());
            }

            object = fields.get("address1");
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
                this.selectCountryField(object.toString());
            }
            object = fields.get("homePhone");
            if (object != null) {
                this.setHomePhone(object.toString());
            }
            object = fields.get("homeEmail");
            if (object != null) {
                this.setPersonalEmail(object.toString());
            }

            object = fields.get("createlogin");
            if (object != null) {
                this.selectCreateLoginField("Create a login account for this person");

                object = fields.get("memberid");
                // System.out.println("object.toString() : "+object.toString());
                if (object != null) {
                    String memberid = utils.generateRandomNumber(object
                            .toString());
                    browser.hMap.put("memberloginid", memberid);
                    this.setMemberIDField(memberid);
                }

                object = fields.get("password");
                if (object != null) {
                    this.setPasswordField(object.toString());
                }

                object = fields.get("confirmpassword");
                if (object != null) {
                    this.setConfirmPasswordField(object.toString());
                }
            }

            object = fields.get("hiredate");
            if (object != null) {
                this.setHireDateField(utils.getDate(object.toString()));
            }

            object = fields.get("calendarset");
            if (object != null) {
                this.selectCalendarSet(fields.get("calendarset").toString());
            }

            object = fields.get("earnings");
            if (object != null) {
                this.setEarnings(object.toString());
            }

            object = fields.get("earningsfrequency");
            if (object != null) {
                this.selectEarningsFrequency(object.toString());
            }

            object = fields.get("occupation");
            if (object != null) {
                this.setOccupation(object.toString());
            }

            object = fields.get("calenderset");
            if (object != null) {
                this.selectCalendarSet(object.toString());
            }

            object = fields.get("benefitsalary");
            if (object != null) {
                this.setBenefitSalary(object.toString());
            }

            object = fields.get("benefitsalaryfrequency");
            if (object != null) {
                this.selectBenefitSalaryFrequency(object.toString());
            }

            object = fields.get("hoursPerWeek");
            if (object != null) {
                this.enterHoursPerWeek(object.toString());
            }

            object = fields.get("employerid");
            if (object != null) {
                String empID = utils.generateRandomNumber(object.toString());
                this.enterEmployerId(empID);
                logger.info("value : " + empID);
                //browser.hMap.put(outLastName, empID);
            }

            object = fields.get("payfrequency");
            if (object != null) {
                this.selectPayFrequency(object.toString());
            }

            object = fields.get("categories");
            if (object != null) {
                this.selectCategories(fields.get("categories").toString()
                        .split("#"));
            }

            // submit the add new member form
            this.clickSavebutton();

            if (performAction.isElementPresent(errorList)) {
                System.out
                        .println("Missing mandatory fields values or duplicate values.");
                /*throw new RuntimeException(
                        "Missing mandatory field values or duplicate values.");*/
                object = fields.get("ssn");
                if (object != null) {
                    String value = utils.generateRandomNumber(object.toString());
                    this.setSSNField(value);
                    logger.info("value : " + value);
                    browser.hMap.put("strssn", value);
                }

                object = fields.get("createlogin");
                if (object != null) {
                    this.selectCreateLoginField("Create a login account for this person");

                    object = fields.get("memberid");
                    // System.out.println("object.toString() : "+object.toString());
                    if (object != null) {
                        String memberid = utils.generateRandomNumber(object
                                .toString());
                        browser.hMap.put("memberloginid", memberid);
                        this.setMemberIDField(memberid);
                    }

                    object = fields.get("password");
                    if (object != null) {
                        this.setPasswordField(object.toString());
                    }

                    object = fields.get("confirmpassword");
                    if (object != null) {
                        this.setConfirmPasswordField(object.toString());
                    }
                }
                this.clickSavebutton();
            }
            Thread.sleep(1000);
            if (performAction.isElementPresent(continueAddressbutton)) {
                performAction.jsclick(continueAddressbutton,
                        "Continue address button");
            }
            scr.capturePageScreenshot();

        } catch (Exception e) {
            System.out.println("Exception occured " + e.getMessage());
            scr.capturePageScreenshot();
            throw new CustomException("Exception in adding new member "
                    + e.getMessage());
        }
    }
    /**
	 * <pre>
	 * Author  : Dilip K
	 *
	 * Role : Vista Role
	 *
	 * Description   : "Update Member Identifiers" Keyword is used to Update Member Identifier or Alternate Identifier
	 *
	 * PreCondition  : Member Search Results page should be displayed.
	 *
	 * PostCondition : Able to update Member/Alternate Identifier
	 * <pre>
	 * <b>Parameters :</b>
	 * | JSONKey,lastname of member, Identifier variable |
	 * | td:identifierinformation,HMVlastname,HMVidentifiervalue |
	 *
	 * <b> Java File Path : com.benefitfocus.robot.vista.groups.users >> AddMemberPage.java </b>
	 *
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({"strIdentifierInformation","strlastname","stridentifierid"})
	public void updateMemberIdentifiers(String strIdentifierInformation,String strlastname,String stridentifierid) {
		try {
			String identifier="";
			String value="";
			String carriername="";
			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(strIdentifierInformation.toLowerCase());

			object = fields.get("memberidentifier");
			if(object!=null){
				value = utils.getValue(object.toString());
				identifier="member identifier";
			}

			object = fields.get("alternateidentifier");
			if(object!=null){
				value = utils.getValue(object.toString());
				identifier="alternate identifier";
			}

			this.clickEditButton(utils.getValue(strlastname));
			/*
			 * object = fields.get("strlastname");
			if(object!=null){
				this.clickEditButton(utils.getValue(object.toString()));
			}*/
			/*object = fields.get("identifiervalue");
			if(object!=null){
				value=object.toString();
			}*/
			browser.hMap.put(stridentifierid, value);

			this.clickMemberIdentifier();

			object = fields.get("carriername");
			if(object!=null){
				carriername = object.toString();
			}

			this.enterIdentifierInformation(carriername, identifier, value);
			this.clickSavebutton();




			scr.capturePageScreenshot();
		} catch (Exception e) {
			logger.info("Exception in updating member identifiers");
			scr.capturePageScreenshot();
			throw new CustomException("Exception in updating member identifiers"
					+ e.getMessage());
		}
	}
}
