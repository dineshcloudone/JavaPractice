package com.benefitfocus.robot.hradmin;

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
import com.benefitfocus.robot.utils.Utilities;
import com.benefitfocus.robot.utils.Screenshot;

@RobotKeywords
public class AddEmployeePage {

	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected ManageBrowser browser;
	@Autowired
	protected Logging logger;
	@Autowired
	protected Screenshot scr;
	@Autowired
	protected Utilities utils;

	// Locators on the page
	By Race=By.id("race");
	By ssn = By.id("SSN");
	By firstName = By.id("firstName");
	By lastName = By.id("lastName");
	By birthDate = By.id("birthDate");
	By gender = By.id("gender");
	By address1 = By.id("address1");
	By city = By.id("city");
	By state = By.id("state");
	By zip = By.id("zip");
	By country = By.id("country");
	By email = By.id("homeEmail");
	By doNotCreateLoginAccount = By.id("doNotCreateLoginAccount");
	By memberID = By.id("memberID");
	By password = By.id("password");
	By confirmPassword = By.id("confirmPassword");
	By hireDate = By.id("hireDate");
	By hoursWorkedperWeek = By.xpath(".//*[@id='weeklyHours']");
	By earnings = By.id("earnings");
	By earningsFrequency = By.id("payFrequency");
	By payFrequency = By.id("payPeriod");
	By calendarSetUrl = By.id("calendarSetUrl");
	By categorySelections = By.id("categorySelections");
	By saveAndDepedentButton = By.linkText("Save and Add Dependent");
	By continueButton = By.xpath("//a[@data-target='Continue']");
	By addNewEmployee = By.linkText("Add a new employee");
	By saveButton = By.linkText("Save");
	By useenteredaddress = By.linkText("Use entered address");
	By useEnteredAddress = By.partialLinkText("Use entered address");
	By personalEmail = By.xpath("//input[@id='homeEmail']");
	By duplicateSsn = By.xpath("//li[contains(text(),'The SSN entered')]");
	By duplicateUser = By.xpath("//li[contains(text(),'The Username you entered')]");
	By errorList = By.id("errorListContainerForEntirePage");
	By homePhone=By.id("homePhone");
	By cellPhone=By.id("cellPhone");
	By retired = By.id("retired-nativeHtmlElement");
	
	//PCP Locators
		String providerCodeTextBox = "/following::input[@id='providerCode']";
		String locationCodeTextBox = "/following::input[@id='locationCode']";
		String affiliateCodeTextBox = "/following::input[@id='affiliateCode']";
		By nextButton = By.xpath("//strong[text()='Next']");
		By memberphysicianSelectBox = By.xpath("//select[contains(@id,'lastVis')]");
		By spousePhysicianSelectBox = By.xpath("//div[@class='innerInnerInnerInnerSpotlight']/descendant::table[@cellspacing='0']/tbody/descendant::div/following::select[contains(@name,'lastVisitWasRecent14')]");
		By physicianRadioButton = By.xpath("//label[text()='No']");
		By medicareRadionButton = By.xpath("//label[contains(text(),'No, neith')]");
		String pcpMemberRows="//div[@class='innerInnerInnerInnerSpotlight']//tr";

	// click on Retired Checkbox
		private void checkRetiredEmployees() {
			performAction.click(retired, "Retired");
		}

	// click on Add New Employee button
	private void clickAddNewEmployee() {
		performAction.click(addNewEmployee, "Add New Employee Link");
	}
	
	//select Race drop down value
	private void selectRace(String racevalue) {
		performAction.select(Race, racevalue, "Race DropDown Value");
	}
	

	// set the ssn text box
	private void setSSNField(String strssn) {
		performAction.enter(ssn, strssn, "SSN Text Box");

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

	// set the gender combo box
	private void selectgenderField(String strValuetoSelect) {
		performAction.select(gender, strValuetoSelect, "Gender Combo Box");
	}

	// set the address1 textbox
	private void setAddress1Field(String straddress1) {
		performAction.enter(address1, straddress1, "Address Text Box");
	}

	// set the city text box
	private void setCityField(String strcity) {
		performAction.enter(city, strcity, "City Text Box");
	}

	// set the state combo box
	private void selectStateField(String strValuetoSelect) {
		performAction.select(state, strValuetoSelect, "State Combo Box");
	}

	// set the zip text box
	private void setZipField(String strzip) {
		performAction.enter(zip, strzip, "Zip Code Text Box");
	}

	// set the Country combo box
	private void selectcountryField(String strValuetoSelect) {
		performAction.select(country, strValuetoSelect, "Country Combo Box");
	}
	
	// set the email text box
		private void setEmailField(String strEmail) {
			performAction.enter(email, strEmail, "Personal Email Text Box");
		}

	// set the doNotCreateLoginAccount combo box
	private void selectCreateLoginField(String strValuetoSelect) {
		performAction.select(doNotCreateLoginAccount, strValuetoSelect,
				"Create Login Combo Box");
	}

	// set the memberID text box
	private void setMemberIDField(String strmemberID) {

		performAction.clearEnter(memberID, strmemberID, "Member ID Text Box");
	}

	// set the password text box
	private void setPasswordField(String strpassword) {
		performAction.enter(password, strpassword, "Password Text Box");
	}

	// set the confirmPassword text box
	private void setConfirmPasswordField(String strconfirmPassword) {
		performAction.enter(confirmPassword, strconfirmPassword,
				"Confirm Password Text Box");
	}

	// set the hireDate text box
	private void setHireDateField(String strhireDate) {
		performAction.enter(hireDate, strhireDate, "Hire Date Text Box");
	}
	
	// set the hoursWorkedPerWeek text box
	private void sethoursWorkedperWeekField(String strhoursWorkedperWeek) {
		performAction.enter(hoursWorkedperWeek, strhoursWorkedperWeek, "Hours Worked Per Week Text Box");
	}

	// set the hireDate text box
	private void setEarnings(String strearnings) {
		performAction.enter(earnings, strearnings, "earnings textbox");
	}

	// set the calendar text box
	private void selectCalendarSet(String strValuetoSelect) {
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
	private void selectPayFrequency(String strValuetoSelect) {
		performAction.select(payFrequency, strValuetoSelect,
				"payfrequency combobox");
	}
	// set the personal email text box
	private void setPersonalEmailField(String strPersonalEmail){
		performAction.clearEnter(personalEmail, strPersonalEmail, "Enter email into personal email filed");
		
	}
	//Retired employees checkbox selection
	private void setRetiredEmployees(){
		performAction.click(retired, "Retired");
	}

	// click on save button
	private void clickSaveAndAddDependentButton() {
		performAction.click(saveAndDepedentButton,
				"Save and Add Dependent Button");
	}
	private void setHomePhoneNumber(String strvalue)
	{
		performAction.enter(homePhone, strvalue, "Home phone textbox");
	}
	private void setCellPhoneNumber(String strvalue)
	{
		performAction.enter(cellPhone, strvalue, "Cell phone textbox");
	}
	// Click on Save Button
	private void saveButton() {
		performAction.click(saveButton, "Save Button");
	}

	/**
	 * SelectCategories keyword to get the list of categories available under
	 * 'work information' section on the Add member page. like Benfits /
	 * Classification eg : Plan / location - for BF QA test Automation Group
	 * 
	 * <pre>
	 * <b>Parameters :</b>
	 * | strCategory - strCategory |
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({ "*strCategory" })
	private void selectCategories(String strCategory[]) {

		try {

			List<WebElement> categories = browser.getCurrentWebDriver()
					.findElements(categorySelections);

			if (categories.size() > 0) {
				int cateIndex = 0;

				for (int i = 0; i < categories.size(); i++) {

					// for (WebElement category : categories)
					if (categories.get(i).getAttribute("onfocus") != null) {
						// logger.info("Category : "+strCategory[cateIndex]);
						// System.out.println("Onfocus : "+
						// categories.get(i).getAttribute("onfocus"));
						// logger.info(categories.get(i).getAttribute("onfocus"));
						Select cate = new Select(categories.get(i));
						if (strCategory.equals("RND")) {

							cate.selectByIndex((int) (Math.random() * cate
									.getOptions().size()));

						} else {
							cate.selectByVisibleText(strCategory[cateIndex]);
							cateIndex++;
						}
						Thread.sleep(20000);
						logger.info("Selected category option :"
								+ cate.getFirstSelectedOption().getText());
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
	//PCP Memeber
		private void setPCPD(String strPCPMember,String setpassPCPCode, String setPCPLocationCode, String setPCPAffiliateCode) throws Exception{
			try {
				String value ="";

				if(strPCPMember.startsWith("HMV")){
					value=utils.getValue(strPCPMember);
				}else{
					value =strPCPMember;
				}
				int rowCount= browser.getCurrentWebDriver().findElements(By.xpath(pcpMemberRows)).size();

				for (int i = 2; i <=rowCount; i++) {

					String  actualTableText =  browser.getCurrentWebDriver().findElement(By.xpath(pcpMemberRows+"["+i+"]")).getText();
					if (actualTableText.trim().contains(value.trim())){
						performAction.clearEnter(By.xpath("//div[@class='innerInnerInnerInnerSpotlight']/" +
								"descendant::table[@cellspacing='0']/tbody/tr["+i+"]/td[1]/div[contains(text(),'"+value+"')]"+providerCodeTextBox), setpassPCPCode, "PCP Code Text Box");
						performAction.clearEnter(By.xpath("//div[@class='innerInnerInnerInnerSpotlight']/" +
								"descendant::table[@cellspacing='0']/tbody/tr["+i+"]/td[1]/div[contains(text(),'"+value+"')]"+locationCodeTextBox), setPCPLocationCode, "Location Code Text Box");
						performAction.clearEnter(By.xpath("//div[@class='innerInnerInnerInnerSpotlight']/" +
								"descendant::table[@cellspacing='0']/tbody/tr["+i+"]/td[1]/div[contains(text(),'"+value+"')]"+affiliateCodeTextBox), setPCPAffiliateCode, "Affiliate Code Text Box");
						scr.capturePageScreenshot();
						performAction.click(nextButton,"Next Button");


						if(performAction.isElementPresent(memberphysicianSelectBox)){
							Thread.sleep(2000);
							performAction.select(memberphysicianSelectBox, "No", "Physician Select Box");
							if (performAction.isElementPresent(spousePhysicianSelectBox)) {
								performAction.select(memberphysicianSelectBox, "No", "Member Physician Select Box");
								performAction.select(spousePhysicianSelectBox, "No", "Spouse Physician Select Box");
							}
							performAction.click(nextButton,"Next Button");
							performAction.click(physicianRadioButton,"No -Physician Question ");
							performAction.click(nextButton,"Next Button");
							performAction.click(medicareRadionButton, "No MEdicare Question");
						}
						break;
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : verifyErrorMessageAddEmployeeWithEmptyData keyword or method is used to verify an Error message in HR Admin Role
	 * 
	 * PreCondition : Add New Employee page in HR Admin Role
	 * 
	 * PostCondition : New employee is saved successfully.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | classification | outLastName | 
	 * | hradminmandatory - is used to get the data set from the Json file "AddEmployeeFunctionality.json" |  
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "classification" })
	public void verifyErrorMessageAddEmployeeWithEmptyData(String classification) {
		if (classification.startsWith("td:"))
			classification = classification.substring(3);
		try {
			String value1= "ConfirmationPopuprel";
			String commands  = new String("Q:\\KB\\HrInTouch\\Data\\"+value1+".exe"); //location of the autoit executable	
			Thread.sleep(5000);	
			
			this.clickAddNewEmployee();
			Thread.sleep(4000);
			Runtime.getRuntime().exec(commands);
			this.saveButton();			
			System.out.println("Save clicked");
			performAction.selectLatestWindow(browser.getCurrentWebDriver().getTitle());					         
		} catch (Exception e) {
			logger.info("Exception in performing the Add employee "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in performing the add employee "
							+ e.getMessage());
		}
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : verifyErrorMessageWhenaddEmployeeInHRAdmin keyword or method is used to verify an Error message in HR Admin Role
	 * 
	 * PreCondition : Add New Employee page in HR Admin Role
	 * 
	 * PostCondition : New employee is saved successfully.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | classification | outLastName | 
	 * | hradminmandatory - is used to get the data set from the Json file "AddEmployeeFunctionality.json" | lastname - is a variable name and it will be used to store the LastName of the Employee | 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "classification", "outLastName" })
	public void verifyErrorMessageAddEmployee(String classification, String outLastName) {
		
		if (classification.startsWith("td:"))
			classification = classification.substring(3);		

		try {			
			
			this.clickAddNewEmployee();
			Thread.sleep(4000);
			Object object;
			JSONObject fields = ReadJsonTestData.getTestData(classification
					.toLowerCase());

			object = fields.get("ssn");
            if (object != null) {
                this.setSSNField(utils.generateRandomNumber(object.toString()));
            }

			object = fields.get("firstname");
			if (object != null) {
				this.setFirstNameField(object.toString());
			}

			object = fields.get("lastname");
			if (object != null) {
				String value = utils.generateRandomNumber(object.toString());
				this.setLastNameField(value);
				browser.hMap.put(outLastName, value);
			}

			//Race Select Box
			if (performAction.isElementPresent(Race)) {

				System.out.println("Race Drop Down Is Enabled");
				
				object = fields.get("race");
				if (object != null) {
					
					this.selectRace(object.toString());
					
				} else {
					System.out.println("Race Drop Down Is Disabled");
				}								
			}
			
			object = fields.get("birthdate");
			if (object != null) {
				this.setBirthDateField(utils.getDate(fields.get("birthdate")
						.toString()));
			}

			object = fields.get("gender");
			if (object != null) {
				this.selectgenderField(object.toString());
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
			
			object = fields.get("email");
			if (object != null) {
				this.setEmailField(object.toString());
			}
			object = fields.get("homephone");
			if (object != null) {
				this.setHomePhoneNumber(object.toString());
			}
			object = fields.get("cellphone");
			if (object != null) {
				this.setCellPhoneNumber(object.toString());
			}
			object = fields.get("createlogin");
			if (object != null) {
				this.selectCreateLoginField(object.toString());

				object = fields.get("memberid");
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
			
			object = fields.get("hoursworkedperweek");
			if (object != null) {
				this.sethoursWorkedperWeekField(object.toString());
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

			object = fields.get("payfrequency"); 
			if (object != null) {
				this.selectPayFrequency(object.toString());
			}
			object = fields.get("categories");
			if (object != null) {
				this.selectCategories(fields.get("categories").toString()
						.split(","));
			}			
			object = fields.get("personalemail");
			if (object != null) {
				this.setPersonalEmailField(object.toString());
			}		
						
			this.saveButton();		
			
			performAction.selectLatestWindow(browser.getCurrentWebDriver().getTitle());					         
		} catch (Exception e) {
			logger.info("Exception in performing the Add employee "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in performing the add employee "
							+ e.getMessage());
		}
	}
	
	
	
	
	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : AddEmployeeInHRAdmin keyword or method is used to perform adding an Employee operation in HR Admin Role.
	 * 
	 * PreCondition : Add New Employee page in HR Admin Role
	 * 
	 * PostCondition : New employee is saved successfully.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | classification | outLastName | 
	 * | hradminmandatory - is used to get the data set from the Json file "AddEmployeeFunctionality.json" | lastname - is a variable name and it will be used to store the LastName of the Employee | 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "classification", "outLastName" })
	public void addEmployeeInHRAdmin(String classification, String outLastName) {

		if (classification.startsWith("td:"))
			classification = classification.substring(3);

		try {
			this.clickAddNewEmployee();

			Object object;
			JSONObject fields = ReadJsonTestData.getTestData(classification
					.toLowerCase());

			object = fields.get("ssn");
            if (object != null) {
            	String value = utils.generateRandomNumber(object.toString());
                this.setSSNField(value);
                browser.hMap.put("ssn", value);
            }

			object = fields.get("firstname");
			if (object != null) {
				this.setFirstNameField(object.toString());
			}

			object = fields.get("lastname");
			if (object != null) {
				String value = utils.generateRandomNumber(object.toString());
				this.setLastNameField(value);
				browser.hMap.put(outLastName, value);
			}

			//Race Select Box
			if (performAction.isElementPresent(Race)) {

				System.out.println("Race Drop Down Is Enabled");
				
				object = fields.get("race");
				if (object != null) {
					
					this.selectRace(object.toString());
					
				} else {
					System.out.println("Race Drop Down Is Disabled");
				}								
			}
			
			object = fields.get("birthdate");
			if (object != null) {
				this.setBirthDateField(utils.getDate(fields.get("birthdate")
						.toString()));
			}

			object = fields.get("gender");
			if (object != null) {
				this.selectgenderField(object.toString());
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
			
			object = fields.get("email");
			if (object != null) {
				this.setEmailField(object.toString());
			}

			object = fields.get("createlogin");
			if (object != null) {
				this.selectCreateLoginField(object.toString());

				object = fields.get("memberid");
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
			
			object = fields.get("hoursworkedperweek");
			if (object != null) {
				this.sethoursWorkedperWeekField(object.toString());
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

			object = fields.get("payfrequency"); 
			if (object != null) {
				this.selectPayFrequency(object.toString());
			}
			object = fields.get("categories");
			if (object != null) {
				this.selectCategories(fields.get("categories").toString()
						.split(","));
			}
			
			object = fields.get("personalemail");
			if (object != null) {
				this.setPersonalEmailField(object.toString());
			}
			//newly added for retitred checkbox selection
			object = fields.get("retired");
			if (object != null) {
				this.setRetiredEmployees();
			}
			// click on save button
			this.saveButton();

			if (performAction.isElementPresent(useenteredaddress)){
				performAction.click(useenteredaddress, "use entered address button");
			}

            if (performAction.isElementPresent(errorList)) {

                do {
					object = fields.get("ssn");
                    this.setSSNField(utils.generateRandomNumber(object.toString()));
                    object = fields.get("createlogin");
                    this.selectCreateLoginField(object.toString());

                    object = fields.get("memberid");
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
                    this.saveButton();

                    if (performAction.isElementPresent(useEnteredAddress)){
                        performAction.click(useEnteredAddress, "use entered address button");
                    }                    
                } while (performAction.isElementPresent(errorList));                
                
            }

		} catch (Exception e) {
			logger.info("Exception in performing the Add employee "
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in performing the add employee "
							+ e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Sekhar Tirumala
	 *
	 * Role : HR Role
	 *
	 * Description   : 'Set Primary Code Provider' keyword used to select select the all the Primary Code Provider Fields.
	 *
	 * PreCondition  :  Cuyrrent Benenfits In HRRole .
	 *
	 * PostCondition : Able to Click Select all Primary code Fileds.
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | strPCPMember  | strpassPCPCode | strPCPLocationCode| strPCPAffiliateCode |
	 *
	 * | TestMandatory05238  | 78962 | Automation123 | Test4562 |
	 *
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> AddEmeployeePagejava </b>
	 * </pre>
	 **/

	@RobotKeyword
	@ArgumentNames({"strPCPMember", "strpassPCPCode","strPCPLocationCode","strPCPAffiliateCode" })
	//public void setPrimaryCodeProvider(String setpassPCPCode,String setPCPLocationCode,String setPCPAffiliateCode) {
	public void setPrimaryCodeProvider(String strPCPMember,String setpassPCPCode,String setPCPLocationCode,String setPCPAffiliateCode) {
		try {

			this.setPCPD(strPCPMember, setpassPCPCode, setPCPLocationCode, setPCPAffiliateCode);

		} catch (Exception e) {
			scr.capturePageScreenshot();
			System.out.println("Exception occured while Entering PCP Details"
					+ e.getMessage());
			throw new CustomException(
					"Exception occured while Entering PCP Details "
					+ e.getMessage());
		}
	}

}
