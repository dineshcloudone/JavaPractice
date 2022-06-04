package com.benefitfocus.robot.vista.carriers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
public class CarrierRepresentatives {
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
	
	// Locators in Carrier Representatives page
	By searchBy=By.id("searchType");
	By lastName=By.id("lastName");
	By role=By.name("role");
	By carrier=By.name("carrier");
	By includeTerminatedMembersCheckBox=By.name("includeTerminatedUsers-nativeHtmlElement");
	By searchButton=By.xpath("//a[contains(@href,'submitSearch')]");
	By searchResults=By.xpath("//table[@class='table table-striped table-bordered standardDataTable dataTableTheme-benefitfocusCorporate']");
	By SearchResultsCount=By.xpath("//table[@class='table table-striped table-bordered standardDataTable dataTableTheme-benefitfocusCorporate']//tr[contains(@class,'Passive')]");
	
	// Locators in Carrier Representatives sort
	By sortBy=By.name("sort");
	By login=By.name("login");
	By nextButton=By.linkText("Next");
	By carRepLastName=By.name("lastName");
	
	// Locators for creating Carrier Representative Account
	By createCarrierRepresentative=By.linkText("Create New Carrier Representative Account");
	By carrRepFirstName=By.name("firstName");
	By carrRepLastName=By.name("lastName");
	By emailAddress=By.id("emailAddress");
	By employeeId=By.id("employeeId");
	By managerFirstName=By.id("managerFirstName");
	By managerLastName=By.id("managerLastName");
	By managerEmailAddress=By.id("managerEmailAddress");
	By carrierRepLoginId=By.id("userId");
	By carrierRepPassword=By.id("password");
	By carrierRepConfirmPassword=By.id("passwordConfirmation");
	By carrierName=By.id("selectedCarrierKeys");
	By carrierRole=By.id("role");
	
	
	// Select Search Option
	private void selectSearchBy(String strSearchType){
		performAction.select(searchBy, strSearchType, "Select Search Type");
	}
	// Enter value in Last Name Text Box
	private void enterLastName(String strLastName){
	    performAction.enter(lastName, strLastName, "Enter Last Name");	
	}
	// Select Role
	private void selectRole(String strRole){
		performAction.select(role, strRole, "Select Role");
	}
	// Select Carrier Name
	private void selectCarrier(String strCarrierName){
		performAction.select(carrier, strCarrierName, "Select Carrier Name");
	}
	// Click on Search Button
	private void clickSearchButton(){
		performAction.click(searchButton, "Click on Search Button");
	}
	//Select Sort By option
	private void selectSortBy(String strSortBy){
		performAction.select(sortBy, strSortBy, "Select Sort By option");
	}
	// Select Login option
	private void selectLogin(String strSelectLogin){
		performAction.select(login, strSelectLogin, "Select Login option");
	}
	// Verify Results Table
	private void verifyResultsTable(String strLoginID){
		int count=browser.getCurrentWebDriver().findElements(SearchResultsCount).size();
		System.out.println("No of Rows in Table"+count);
		String Actual="";
		if(count!=0){
			for(int i=1;i<=count;i++){
				Actual=browser.getCurrentWebDriver().findElement(By.xpath("//table[@class='table table-striped table-bordered standardDataTable dataTableTheme-benefitfocusCorporate']//tr[contains(@class,'Passive')]["+i+"]/td[3]")).getText();
				if(Actual.trim().equalsIgnoreCase(strLoginID)){
					System.out.println("Login ID Found "+Actual);
					break;
				}
			}				
		}else{
			System.out.println("No Results Found");
		}
	}
	// Sort By option
	private void sortByOptions(String strSortBy,String strLogin) throws InterruptedException{
		this.selectSortBy(strSortBy);
		this.selectLogin(strLogin);
		this.clickSearchButton();
		Thread.sleep(3000);
	}
	// Convert WebElement Array List to String ArrayList
	private ArrayList convertWebElementstoString(ArrayList<WebElement> listArray){
		System.out.println("convertWebElementstoString Function called");
		ArrayList<String> stringArray=new ArrayList<String>();
		int i=0;
		for(WebElement sortedValues:listArray){
			System.out.println("Entered For Loop");
			System.out.println("Priting String List== > "+sortedValues.getText());
			stringArray.add(i,sortedValues.getText().trim());
			System.out.println(stringArray.get(i));
			i++;
		}
		System.out.println("Exited from For Loop");
		return stringArray;
	}
	// Sort Carrier Representative Search Results using Login ID
		private void sortCarrierRepResultsUsingLoginID(ArrayList<WebElement> expected){
			ArrayList<String> strExpected=new ArrayList<String>();
			ArrayList<String> strActual=new ArrayList<String>();
			ArrayList<WebElement> actual=expected;
			
			System.out.println("sortCarrierRepResultsUsingLoginID Function Called");
			strExpected=this.convertWebElementstoString(expected);
			Collections.sort(strExpected);
			System.out.println("ArrayList of Web Elements Sorted");
			//ArrayList<WebElement> actual=(ArrayList<WebElement>) browser.getCurrentWebDriver().findElements(By.xpath("//tr[contains(@class,'Passive')]/td[3]"));			
			strActual=this.convertWebElementstoString(actual);
			System.out.println(strActual.get(0));
			System.out.println(strActual.get(5));
			if(strExpected.equals(strActual)){
				 System.out.println("Carrier Representatives Results were sorted using Login ID");
			}
		}
	// Sort Carrier Representative Search Results using Last Name
		private void sortCarrierRepResultsUsingLastName(ArrayList<WebElement> expected){
			//Collections.sort(expected);
			ArrayList actual=(ArrayList) browser.getCurrentWebDriver().findElements(By.xpath("//tr[contains(@class,'Passive')]/td[2]"));
			if(actual.equals(expected)){
				System.out.println("Carrier Representatives Results were sorted using Last Name");
			}
		}
	// Sort Carrier Representative Search Results using Login ID
		
		private void sortCarrierRepResultsUsingFirstName(ArrayList<WebElement> expected){
			ArrayList sortedExpectedList=new ArrayList<String>();
			String firstname[]=new String[expected.size()];
	        for(int i=0;i<expected.size();i++){
	            firstname[i]=expected.get(i).toString().substring(expected.get(i).toString().lastIndexOf(",")+2);
	            //System.out.println(firstname[i]);
	        }
	        Arrays.sort(firstname);
	     // Printing sorted Array list 
            for(int j=0;j<firstname.length;j++){
                for(int k=0;k<expected.size();k++){
                    if(expected.get(k).toString().substring(expected.get(k).toString().lastIndexOf(",")+2).equals(firstname[j])){
                        System.out.println("Sorted List with First Name ==>"+expected.get(k).toString());
                        sortedExpectedList.add(j,expected.get(k).toString());
                        expected.remove(k);
                        break;
                    }
                }
            }
	        ArrayList actual=(ArrayList) browser.getCurrentWebDriver().findElements(By.xpath("//tr[contains(@class,'Passive')]/td[2]"));
			if(actual.equals(sortedExpectedList)){
				 System.out.println("Carrier Representatives Results were sorted using First Name");
			}
		}
		// Verify Results Table
		private void clickEditCarrierRepAccount(String strLoginID){
			int count=browser.getCurrentWebDriver().findElements(SearchResultsCount).size();
			logger.info("No of Rows in Table"+count);
			String Actual="";
			if(count!=0){
				for(int i=1;i<=count;i++){
					Actual=browser.getCurrentWebDriver().findElement(By.xpath("//table[@class='table table-striped table-bordered standardDataTable dataTableTheme-benefitfocusCorporate']//tr[contains(@class,'Passive')]["+i+"]/td[3]")).getText();
					if(Actual.trim().equalsIgnoreCase(strLoginID)){
						logger.info("Login ID Found "+Actual);
						performAction.click(By.xpath("//table[@class='table table-striped table-bordered standardDataTable dataTableTheme-benefitfocusCorporate']//tr[contains(@class,'Passive')]["+i+"]/td[3]/..//a"),"Click on Edit Carrier Rep Account");
						break;
					}
				}				
			}else{
				logger.info("No Results Found for Carrier Representatives");
			}
		}
		// Edit Carrier Representative Account
		private void editCarrieRep(String strEdit){
			performAction.clearEnter(carRepLastName, strEdit, "Edit Carrier Representative");
		}
		// Click on Next Button
		private void clickNextButton(){
			performAction.click(nextButton, "Click on Next Button");
		}
		// Click on Create New Carrier Representative Button
		private void clickCreateCarrierRepresentative() {
            if (performAction.isElementPresent(createCarrierRepresentative)) {
			performAction.click(createCarrierRepresentative, "Click on Create New Representative Button");
		}
		}
		// Enter Carrier Representative First Name
		private void setCarrRepFirstName(String  strCarrRepFirstName) {
			performAction.enter(carrRepFirstName, strCarrRepFirstName, "Enter Carrier Representative First Name");
		}
		// Enter Carrier Representative Last Name
		private void setCarrRepLastName(String  strCarrRepLastName) {
			performAction.enter(carrRepLastName, strCarrRepLastName, "Enter Carrier Representative Last Name");
		}
		// Enter Email Address
		private void setEmailAddress(String strEmailAddress) {
			performAction.enter(emailAddress, strEmailAddress, "Enter Email Address");
		}
		// Enter Employee ID
		private void setEmployeeId(String strEmployeeId) {
		    performAction.enter(employeeId, strEmployeeId, "Enter Employee ID");
		}
		// Enter Manage First Name
		private void setManagerFirstName(String strManagerFirstName) {
			performAction.enter(managerFirstName, strManagerFirstName, "Enter Manage First Name");
		}
		// Enter Manager Last Name
		private void setManagerLastName(String strManagerLastName) {
			performAction.enter(managerLastName, strManagerLastName, "Enter Manager Last Name");
		}
		// Enter Manager Email Address
		private void setManagerEmailAddress(String strManagerEmailAddress) {
			performAction.enter(managerEmailAddress, strManagerEmailAddress, "Enter Manager Email Address");
		}
		// Enter Carrier Representative Login ID
		private void setCarrierRepLoginId(String strCarrierRepLoginId) {
			performAction.enter(carrierRepLoginId, strCarrierRepLoginId, "Enter Carrier Representative Login ID");
		}
		// Enter Carrier Representative Password
		private void setCarrierRepPassword(String strCarrierRepPassword) {
			performAction.enter(carrierRepPassword, strCarrierRepPassword, "Enter Carrier Representative Password");
		}
		// Enter carrier Representative Confirm Password
		private void setCarrierRepConfirmPassword(String strCarrierRepConfirmPassword) {
			performAction.enter(carrierRepConfirmPassword, strCarrierRepConfirmPassword, "Enter Carrier Representative Confirm Password");
		}
		// Select Carrier Name
		private void selecttCarrierName(String strCarrierName) {
			performAction.select(carrierName, strCarrierName, "Select Carrier Name");
		}
		// Select Carrier Role
		private void selectCarrierRole(String strCarrierRole) {
			performAction.select(carrierRole, strCarrierRole, "Select Carrier Role");
		}
		
		
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : Vista Role
	 *  
	 * Description   : "Perform Carrier Representative Search" keyword used to search for Carrier Representative.
	 * 
	 * PreCondition  : Navigate to 'Carriers >> Carrier Representatives' page  
	 * 
	 * PostCondition : Search Results table will be displayed.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | Search By:Value   | Role | Carrier Name | 
	 * | Last Name:representative| All,Membership,Reporting | BFI |
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.vista.carriers >> CarrierRepresentatives.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strSearchByValue","strRole","strCarrierName" })
    public void performCarrierRepresentativeSearch(String strSearchByValue,String strRole,String strCarrierName) {
		try{
			String searchByValue[]=strSearchByValue.split(":");
			String searchByDropDown=searchByValue[0];
			String value=searchByValue[1];
			performAction.verifyMessage("Carrier Representatives");
			performAction.isElementPresent(searchBy);
			this.selectSearchBy(searchByDropDown);
			this.enterLastName(value);
			this.selectRole(strRole);
			this.selectCarrier(strCarrierName);
			this.clickSearchButton();
			scr.capturePageScreenshot();
		}catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in performing carrier representatives search"
					+ e.getMessage());
			throw new CustomException(
					"Exception in performing carrier representatives search"
							+ e.getMessage());
		}		
	}	
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : Vista Role
	 *  
	 * Description   : "Perform Carrier Representative Sort" keyword used to sort search results of Carrier Representative.
	 * 
	 * PreCondition  : Navigate to 'Carriers >> Carrier Representatives' page  
	 * 
	 * PostCondition : Able to sort Search Results table.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strSortBy | strLogin | 
	 * | Last Name | All or Enabled or Disabled |
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.vista.carriers >> CarrierRepresentatives.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strSortBy","strLogin" })
    public void performCarrierRepresentativeSort(String strSortBy,String strLogin) {
		try{
			performAction.verifyMessage("Carrier Representatives");
			performAction.isElementPresent(sortBy);
			ArrayList<WebElement> expected;
			if(strSortBy.equalsIgnoreCase("Login ID")){
				System.out.println("Entered IF Statement of Login ID");
				By resultstable=By.xpath("//tr[contains(@class,'Passive')]/td[3]");
				performAction.isElementPresent(resultstable);
			    this.sortByOptions(strSortBy, strLogin);
			    /*Thread.sleep(3000);
			    expected=(ArrayList<WebElement>) browser.getCurrentWebDriver().findElements(By.xpath("//tr[contains(@class,'Passive')]/td[3]"));
			    this.sortCarrierRepResultsUsingLoginID(expected);*/
			}else if(strSortBy.equalsIgnoreCase("Last Name")){
				System.out.println("Entered IF Statement of Last Name");
				expected=(ArrayList<WebElement>) browser.getCurrentWebDriver().findElements(By.xpath("//tr[contains(@class,'Passive')]/td[2]"));
				this.sortByOptions(strSortBy, strLogin);
				Thread.sleep(3000);
				this.sortCarrierRepResultsUsingLastName(expected);
			}else{
				System.out.println("Entered IF Statement of First Name");
				expected=(ArrayList<WebElement>) browser.getCurrentWebDriver().findElements(By.xpath("//tr[contains(@class,'Passive')]/td[2]"));
				this.sortByOptions(strSortBy, strLogin);
				Thread.sleep(3000);
				this.sortCarrierRepResultsUsingFirstName(expected);				
			}			
		}catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in performing carrier representatives sort"
							+ e.getMessage());
		}		
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role: Vista Role
	 *  
	 * Description   : "Verify Carrier Representative Results" keyword used to verify search results of Carrier Representative.
	 * 
	 * PreCondition  : Navigate to Carrier Representatives Search Results page  
	 * 
	 * PostCondition : Verify Carrier Representatives Search Results table.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | Login ID | 
	 * | carrier_membership |
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.vista.carriers >> CarrierRepresentatives.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strLoginID" })
    public void verifyCarrierRepresentativeResults(String strLoginID) {
		try{
			performAction.verifyMessage("Carrier Representatives");
			performAction.isElementPresent(searchResults);
			this.verifyResultsTable(strLoginID);
			
		}catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in performing carrier representatives sort"
							+ e.getMessage());
		}		
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : Vista Role
	 *  
	 * Description   : "Edit Carrier Representative Account" keyword used to edit Carrier Representative Account.
	 * 
	 * PreCondition  : Navigate to Carrier Representatives Search Results page  
	 * 
	 * PostCondition : Edit Carrier Representative Account.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strCarrierRepLoginId,strEditCarrierAccount | 
	 * | carrierrep1,td:editcarrierrepresentative |
	 * 
	 * Java File Name : com.benefitfocus.robot.vista.carriers >> CarrierRepresentatives.java
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strCarrierRepAccount","strEditCarrierAccount" })
    public void editCarrierRepresentativeAccount(String strCarrierRepLoginId,String strEditCarrierAccount) {
		try{
			if(strEditCarrierAccount.startsWith("td:")){
				strEditCarrierAccount=strEditCarrierAccount.substring(3);
			}
			this.clickEditCarrierRepAccount(strCarrierRepLoginId);
			Object object = null;
            JSONObject fields = ReadJsonTestData.getTestData(strEditCarrierAccount.toLowerCase());

            object = fields.get("lastname");
            String strlastname=utils.getValue(object.toString());
            if (object != null) {
                this.editCarrieRep(strlastname);
            }
			this.clickNextButton();
			performAction.verifyMessage(strlastname);
			scr.capturePageScreenshot();			
		}catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in editing carrier representatives Account"
					+ e.getMessage());
			throw new CustomException(
					"Exception in editing carrier representatives Account"
							+ e.getMessage());
		}		
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : Vista Role
	 *  
	 * Description   : "Create New Carrier Representative Account In Vista Role" keyword used to create new Carrier Representative Account.
	 * 
	 * PreCondition  : Navigate to Carrier Representative page.  
	 * 
	 * PostCondition : Able to create new Carrier Representative Account.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strCarrierRepDetails | 
	 * | td:newcarrierrepresentative |
	 * 
	 * Java File Name : com.benefitfocus.robot.vista.carriers >> CarrierRepresentatives.java
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strCarrierRepDetails" })
    public void createNewCarrierRepresentativeAccountInVistaRole(String strCarrierRepDetails) {
		try{
            this.clickCreateCarrierRepresentative();
            performAction.waitForPageLoad();
		if(strCarrierRepDetails.startsWith("td:")){
			strCarrierRepDetails=strCarrierRepDetails.substring(3);
		}
			Object object = null;
            JSONObject fields = ReadJsonTestData.getTestData(strCarrierRepDetails.toLowerCase());

            object = fields.get("firstname");
            String strfirstname=utils.getValue(object.toString());
            if (object != null) {
                this.setCarrRepFirstName(strfirstname);
            }
            object = fields.get("lastname");
            String strlastname=utils.getValue(object.toString());
            if (object != null) {
				this.setCarrRepLastName(strlastname);
			}
			object = fields.get("emailaddress");
			String strEmailAddress=utils.getValue(object.toString());
			if (object != null) {
				this.setEmailAddress(strEmailAddress);
			}
			object = fields.get("employeeid");
			String strEmployeeId=utils.getValue(object.toString());
			if (object != null) {
				this.setEmployeeId(strEmployeeId);
			}
			object = fields.get("managerfirstname");
			String strManagerFirstName=utils.getValue(object.toString());
			if (object != null) {
				this.setManagerFirstName(strManagerFirstName);
			}
			object = fields.get("managerlastname");
			String strManagerLastName=utils.getValue(object.toString());
			if (object != null) {
				this.setManagerLastName(strManagerLastName);
			}
			object = fields.get("manageremailaddress");
			String strManagerEmailAddress=utils.getValue(object.toString());
			if (object != null) {
				this.setManagerEmailAddress(strManagerEmailAddress);
			}
			object = fields.get("carrierreploginid");
			String strCarrierRepLoginId=utils.getValue(object.toString());
			if (object != null) {
				this.setCarrierRepLoginId(strCarrierRepLoginId);
                browser.hMap.put("carrierreploginid", strCarrierRepLoginId);
			}
            object = fields.get("carriername");
            String strCarrierName=utils.getValue(object.toString());
            if (object != null) {
                this.selecttCarrierName(strCarrierName);
            }
			object = fields.get("carrierreppassword");
			String strCarrierRepPassword=utils.getValue(object.toString());
			if (object != null) {
				this.setCarrierRepPassword(strCarrierRepPassword);
			}
			object = fields.get("carrierrepconfirmpassword");
			String strCarrierRepConfirmPassword=utils.getValue(object.toString());
			if (object != null) {
				this.setCarrierRepConfirmPassword(strCarrierRepConfirmPassword);
			}
            object = fields.get("carrierrole");
            String strCarrierRole=utils.getValue(object.toString());
            if (object != null) {
                this.selectCarrierRole(strCarrierRole);
            }
			this.clickNextButton();
			performAction.waitForPageLoad();
			performAction.verifyMessage("Carrier Representative");
			scr.capturePageScreenshot();			
		}catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in Creating Carrier Representatives Account"
							+ e.getMessage());
		}		
	}
	
}
