package com.benefitfocus.robot.vista.agencies;

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
public class AgencySearch {
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
	
	// Locators in Agency Search
	By agencySearch=By.id("simpleSearchCriteria");
	By searchButton=By.linkText("Search");
	By agencyNameCount=By.xpath("//tr[contains(@class,'Passive')]/td[1]");
	
	// Locators for Creating New Agency
	By agencyName=By.id("agencyName");
	By state=By.id("state");
	By country=By.id("country");
	By zip=By.id("zip");
	By carrier=By.name("selectedCarrierKeys");
	By privateLabel=By.name("selectedPrivateLabelHandle");
	By saveButton=By.linkText("Save");
	
	// Locators in Agent Search
	By agentName=By.xpath("//input[contains(@name,'lastName')]");
	By role=By.name("role");
	By sortBy=By.name("sortBy");
	By login=By.name("loginStatus");
	By searchAccounts=By.linkText("Search Accounts");
	By searchResults=By.xpath("//table[@class='table table-striped table-bordered standardDataTable dataTableTheme-benefitfocusCorporate']");
	By SearchResultsCount=By.xpath("//table[@class='table table-striped table-bordered standardDataTable dataTableTheme-benefitfocusCorporate']//tr[contains(@class,'Passive')]");
		
    By advancedSearch = By.linkText("Advanced Search");
    By carrierFilter = By.name("selectedCarrierKey");
    By privateLabelFilter = By.name("selectedPrivateLabelKey");
    By esalesFilter = By.name("selectedeSalesStatus");

    By editButton = By.id("contextualMenuAnchorNavAgentMenu0");
    By editAgent = By.linkText("Edit Agent");
    By password = By.id("password");
    By confirmPassword = By.id("confirmPassword");
    By loginAllowed = By.id("loginAllowed");

    //Create New Agent
    By clickAgencyName = By.xpath("//tr[@class='dtr dtrEvenPassive']//a");
    By agentsTab = By.xpath("//a[@id='innerLinkagencySetupBasicsTab'][contains(text(),'Agents')]");
    By createNewAgent = By.linkText("Create New Agent");
    By firstName = By.id("firstName");
    By lastName = By.id("lastName");
    By workNumber = By.id("phone");
    By loginID = By.id("loginID");
    By agentRole = By.id("agentRole");
    By saveAgent = By.xpath("//a[@class='buttonInnerLink']//strong[contains(text(),'Save')]");

	// Enter Agency Name
	private void enterAgencyNameSearch(String strAgencyName){
		performAction.enter(agencySearch, strAgencyName, "Enter Agency Name");
	}
	// Click on Search Button
	private void clickSearchButton(){
		performAction.click(searchButton, "Click on Search Button");
	}
	// Number of rows returned by Agency Search
	private int noOfRows(){
		int count=browser.getCurrentWebDriver().findElements(agencyNameCount).size();
		if(count!=0){
			System.out.println("Number of Rows returned by Agency Search : "+count);			
		}else{
			System.out.println("No search Results displayed");
		}
		return count;
	}
	// Verify Agency Name
	private void verifyAgencyName(String strAgencyName){
		By expectedAgencyName=By.xpath("//a[text()='"+strAgencyName+"']");
		performAction.verify(expectedAgencyName, strAgencyName, "Verify Agency Search Results");
	}
	// Enter Agency Name
	private void enterAgencyName(String strAgencyName){
		performAction.enter(agencyName, strAgencyName, "Enter Agency Name");
	}
	// Select State
	private void selectState(String strState){
		performAction.select(state, strState, "Select State");
	}
	// Select Country
	private void selectCountry(String strCountry){
		performAction.select(country, strCountry, "Select Country");
	}
	// Select Zip code
	private void enterZip(String strZip){
		performAction.enter(zip, strZip, "Enter Zip code");
	}
	// Select Carrier
	private void selectCarrier(String strCarrier){
		performAction.select(carrier, strCarrier, "Select Carrier");
	}
	// Select Private Label
	private void selectPrivateLabel(String strPrivateLabel){
		performAction.select(privateLabel, strPrivateLabel, "Select Private Label");
	}
	// Click on Save Button
	private void clickSaveButton(){
		performAction.click(saveButton, "Click on Save Button");
	}
	// Enter agent Name
	private void enterAgentName(String strAgentName){
		performAction.enter(agentName, strAgentName, "Enter Agent Name");
	}
	// Select Role
	private void selectRole(String strSelectRole){
		performAction.select(role, strSelectRole, "Select Role");
	}
	// Select Sort By option
    private void sortBy(String strSortBy){
		performAction.select(sortBy, strSortBy, "Select Sort By option");
	}	
	// Select Login
    private void selectLogin(String strSelectLogin){
		performAction.select(login, strSelectLogin, "Select Login");
	}
    // Click on Search Accounts
    private void clickSearchAccounts(){
		performAction.click(searchAccounts, "Click on Search Accounts Button");
	}
    // Verify Agent Results Table
 	private void verifyAgentResultsTable(String strLoginID){
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
	
    private void clickAdvancedSearchButton() {
        performAction.click(advancedSearch, "Click Advanced Search Button");
        performAction.verifyMessage("Advanced Search");
    }

    private void selectCarrierFilter(String strCarrierFilter) {
        performAction.select(carrierFilter, strCarrierFilter, "Select Carrier Filter From DropDown");
    }

    private void selectPrivateLabelFilter(String strPrivateLabelFilter) {
        performAction.select(privateLabelFilter, strPrivateLabelFilter, "Select Private label Filter From DropDown");
    }

    private void selectESalesFilter(String strESalesFilter) {
        performAction.select(esalesFilter, strESalesFilter, "Select eSales Filter From DropDown");
    }

    private void editAgentDetails() {
        performAction.mouseOver(editButton, "Click Edit Button");
        performAction.click(editAgent, "Click Edit Agent Link");
    }

    private void changePassword(String strPassword) {
        performAction.enter(password, strPassword, "Enter Password");
        performAction.enter(confirmPassword, strPassword, "Enter Confirm Password");
        performAction.select(loginAllowed, "Yes", "Select Yes for Login Allowed");
    }
    //Create New Agent
    private void setAgentFirstName(String strFirstName) {
        performAction.enter(firstName, strFirstName, "Enter Agent First Name");
    }

    private void setAgentLastName(String strLastName) {
        performAction.enter(lastName, strLastName, "Enter Agent Last Name");
    }

    private void setAgentWorkNumber(String strWorkNumber) {
        performAction.enter(workNumber, strWorkNumber, "Enter Agent Work Number");
    }

    private void setAgentLoginID(String strLoginID) {
        performAction.enter(loginID, strLoginID, "Enter Agent Login ID");
    }

    private void setAgentPassword(String strPassword) {
        performAction.enter(password, strPassword, "Enter Agent Password");
    }

    private void setAgentConfirmPassword(String strConfirmPassword) {
        performAction.enter(confirmPassword, strConfirmPassword, "Enter Agent Confirm Password");
    }

    private void selectLoginAllowed(String strLoginAllowed) {
        performAction.select(loginAllowed, strLoginAllowed, "Select Login Allowed");
    }

    private void selectAgentRole(String strAgentRole) {
        performAction.select(agentRole, strAgentRole, "Select Agent Role");
    }

    private void saveAgent() { performAction.click(saveAgent, "Click Save to Save Agent"); }
    private void clickAgencyNameToExpand() { performAction.click(clickAgencyName, "Click Agency Name"); }
    private void clickAgentTab() { performAction.click(agentsTab, "Click Agent Tab"); }
    private void clickCreateNewAgentLink() { performAction.click(createNewAgent, "Click Create New Agent Link"); }


	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : Vista Role
	 *  
	 * Description   : "Perform Agency Search and Verify" keyword used to search for Agency and verif ythe search results and perform click on the agency link
	 * 
	 * PreCondition  : Navigate to 'Agencies >> Agency Search' page  
	 * 
	 * PostCondition : Search Results table will be displayed.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | Agency Name        | 
	 * | Automation agency1 |
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.vista.agencies >> AgencySearch.java </b>
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strAgencyName" })
    public void performAgencySearchAndVerify(String strAgencyName) {
		if(strAgencyName.startsWith("HMV")){
			strAgencyName=utils.getValue(strAgencyName);
		}
		try{
			//performAction.verifyMessage("Agency Search");
			performAction.isElementPresent(agencySearch);
			this.enterAgencyNameSearch(strAgencyName);
			this.clickSearchButton();
			performAction.verifyMessage("Agency Search");
			int rowCount=this.noOfRows();
			if(rowCount!=0){
				this.verifyAgencyName(strAgencyName);
			}
			scr.capturePageScreenshot();
		}catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in performing Agency search"
					+ e.getMessage());
			throw new CustomException(
					"Exception in performing Agency search"
							+ e.getMessage());
		}		
	}

	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : Vista Role
	 *  
	 * Description   : "Create New Agency" keyword used to create New Agency in Vista Role.
	 * 
	 * PreCondition  : Navigate to Create New Agency Page(Agencies >> Add New Agency)  
	 * 
	 * PostCondition : Able to create new Agency.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | JSON Tag Name        | 
	 * | td:newautomationagency |
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.vista.agencies >> AgencySearch.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strNewAgency" })
    public void createNewAgency(String strNewAgency) {
		try{
			if (strNewAgency.startsWith("td:"))
				strNewAgency = strNewAgency.substring(3);
			
			performAction.verifyMessage("Create New Agency");
			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(strNewAgency.toLowerCase());

			object = fields.get("newagencyname");
			String agencyName=object.toString();
			if (agencyName.startsWith("RND")) {
				agencyName=utils.getValue(agencyName);
				this.enterAgencyName(agencyName);
				browser.hMap.put("NewAgencyName",agencyName);
			}			
			object = fields.get("newagencystate");
			this.selectState(object.toString());
			
			object = fields.get("newagencycoutry");
			this.selectCountry(object.toString());
			
			object = fields.get("newagencyzip");
			this.enterZip(object.toString());
			
			object = fields.get("newagencycarrier");
			this.selectCarrier(object.toString());
			
			object = fields.get("newagencyprivatelabel");
			this.selectPrivateLabel(object.toString());
			
			this.clickSaveButton();
            performAction.waitUntilElementPresent(By.tagName("h1"));
			performAction.verifyMessage("Edit Agency");
			this.clickSaveButton();
			performAction.verifyMessage("Your information has been saved.");
			scr.capturePageScreenshot();			
		}catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in creating new agency"
					+ e.getMessage());
			throw new CustomException(
					"Exception in creating new agency"
							+ e.getMessage());
		}		
	}
	
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Roel : Vista Role
	 *  
	 * Description   : "Perform Agent Search" keyword used to search for Agency.
	 * 
	 * PreCondition  : Navigate to 'Agencies >> Agents Search' page  
	 * 
	 * PostCondition : Search Results table will be displayed.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | Agent Name | Agent Role | 
	 * | Automation | Agent Administrator | 
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.vista.agencies >> AgencySearch.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strAgentName","strAgentRole" })
    public void performAgentSearch(String strAgentName,String strAgentRole) {
		try{
			if(strAgentName.startsWith("HMV"))
				strAgentName=utils.getValue(strAgentName);
			
			performAction.verifyMessage("Agents");
			performAction.isElementPresent(agentName);
			this.enterAgentName(strAgentName);
			this.selectRole(strAgentRole);
			this.clickSearchAccounts();
			
			
			
			scr.capturePageScreenshot();
		}catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in performing Agent search"
					+ e.getMessage());
			throw new CustomException(
					"Exception in performing Agent search"
							+ e.getMessage());
		}		
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 * 
	 * Role : Vista Role
	 *  
	 * Description   : "Verify Agent Search Results" keyword used to verify search results of Agent.
	 * 
	 * PreCondition  : Navigate to Agent Search Results. 
	 * 
	 * PostCondition : Able to verify Agent Search Results.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | Login ID | 
	 * | AGENTADMINISTRATOR | 
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.vista.agencies >> AgencySearch.java </b>
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strLoginID"})
    public void verifyAgentSearchResults(String strLoginID) {
		
		try{
			performAction.verifyMessage("Agents");
			performAction.isElementPresent(searchResults);
			this.verifyAgentResultsTable(strLoginID);
			
		}catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in verify Agent search Results"
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *
	 * Role : Vista Role
	 *
	 * Description   : "Perform Advanced Search For Agency and Verify" keyword used to Advanced Search for Agency Using Filters as Carrier, Private Label, eSaled and verify the search results
	 *
	 * PreCondition  : Navigate to 'Agencies >> Agency Search' page
	 *
	 * PostCondition : Search Results table will be displayed.
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | Filter Values | Agency Name        |
	 * | Carrier Value(ACME), PrivateLabel Value(ABCBS), eSaled Value(ANY) | Automation agency1 |
	 *
	 * <b> Java File Path : com.benefitfocus.robot.vista.agencies >> AgencySearch.java </b>
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strFilterValues", "strAgencyName" })
	public void performAdvancedSearchForAgencyAndVerify(String strFilterValues, String strAgencyName) {
		if(strAgencyName.startsWith("HMV")){
			strAgencyName=utils.getValue(strAgencyName);
		}
        if (strFilterValues.startsWith("td:"))
            strFilterValues = strFilterValues.substring(3);
        Object object = null;
        JSONObject fields = ReadJsonTestData.getTestData(strFilterValues.toLowerCase());
        object = fields.get("carrierfiltervalue");
        String strCarrierFilter = utils.getValue(object.toString());
        object = fields.get("privatelabelfiltervalue");
        String strPrivateLabelFilter = utils.getValue(object.toString());
        object = fields.get("esalesfiltervalue");
        String strESalesFilter = utils.getValue(object.toString());

		try{
			performAction.isElementPresent(advancedSearch);
			this.clickAdvancedSearchButton();
            this.selectCarrierFilter(strCarrierFilter);
            this.selectPrivateLabelFilter(strPrivateLabelFilter);
            this.selectESalesFilter(strESalesFilter);
            this.clickSearchButton();
            performAction.waitForPageLoad();
			int rowCount=this.noOfRows();
			if(rowCount!=0){
				this.verifyAgencyName(strAgencyName);
			}
			scr.capturePageScreenshot();
		}catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in performing Agency Advanced Search"
					+ e.getMessage());
			throw new CustomException(
					"Exception in performing Agency Advanced Search"
							+ e.getMessage());
		}
	}

    /**
     * <pre>
     * Author  : Varun Reddy Proddutoori
     *
     * Role : Vista Role
     *
     * Description   : "Update Agent Details Password From Edit Button" keyword used to Edit Agent Details and Update password
     *
     * PreCondition  : Navigate to 'Agencies >> Agents Search' page
     *
     * PostCondition : Updated Agent Details Password Successfully.
     *
     * <b>Parameters & Example </b>
     *
     * | passwordvalues |
     * | td:passwordvalues |
     *
     * <b> Java File Path : com.benefitfocus.robot.vista.agencies >> AgencySearch.java </b>
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({ "strPasswordValues" })
    public void updateAgentDetailsPasswordFromEditButton(String strPasswordValues) {

        if (strPasswordValues.startsWith("td:"))
            strPasswordValues = strPasswordValues.substring(3);
        Object object = null;
        JSONObject fields = ReadJsonTestData.getTestData(strPasswordValues.toLowerCase());
        object = fields.get("password");
        String strPassword = utils.getValue(object.toString());
        browser.hMap.put("agentloginpassword", strPassword);

        try{
            this.editAgentDetails();
            this.changePassword(strPassword);
            this.clickSaveButton();
            scr.capturePageScreenshot();
        }catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception occured while Editing Agent Details Password"
                    + e.getMessage());
            throw new CustomException(
                    "Exception occured while Editing Agent Details Password"
                            + e.getMessage());
        }
    }

	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *
	 * Role : Vista Role
	 *
	 * Description   : Create New Agent Keyword is used to create new agent
	 *
	 * PreCondition  : Navigate to 'Agencies >> Agency Search >> Open Agency >> Agents Tab' page
	 *
	 * PostCondition : New Agent is created successfully.
	 *
	 * <b>Parameters & Example </b>
	 *
	 * | strAgentValues |
	 * | td:agentvalues |
	 *
	 * <b> Java File Path : com.benefitfocus.robot.vista.agencies >> AgencySearch.java </b>
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strAgentValues", "outAgentLastName"})
	public void createNewAgent(String strAgentValues, String outAgentLastName) {
        this.clickAgencyNameToExpand();
        this.clickAgentTab();
        this.clickCreateNewAgentLink();

        try{
            if (strAgentValues.startsWith("td:"))
                strAgentValues = strAgentValues.substring(3);

            Object object = null;
            JSONObject fields = ReadJsonTestData.getTestData(strAgentValues.toLowerCase());

            object = fields.get("firstname");
            if (object != null) {
                this.setAgentFirstName(object.toString());
            }
            object = fields.get("lastname");
            if (object != null) {
                //String agentLastName=utils.getValue(object.toString());
            	String agentLastName = utils
						.generateRandomNumber(object.toString());
                this.setAgentLastName(agentLastName);
                browser.hMap.put(outAgentLastName, agentLastName);
            }
            object = fields.get("worknumber");
            if (object != null) {
                this.setAgentWorkNumber(object.toString());
            }
            object = fields.get("agentid");
            if (object != null) {
                String agentid=utils.getValue(object.toString());
                this.setAgentLoginID(agentid);
                browser.hMap.put("agentloginid", agentid);
            }
            object = fields.get("password");
            if (object != null) {
                this.setAgentPassword(object.toString());
            }
            object = fields.get("confirmpassword");
            if (object != null) {
                String agentPassword=utils.getValue(object.toString());
                this.setAgentConfirmPassword(agentPassword);
                browser.hMap.put("agentPassword", agentPassword);
            }
            object = fields.get("loginallowed");
            if (object != null) {
                this.selectLoginAllowed(object.toString());
            }
            object = fields.get("agentrole");
            if (object != null) {
                this.selectAgentRole(object.toString());
            }

            this.saveAgent();

            if (performAction.isElementPresent(saveAgent)) {
                this.saveAgent();
            }
            scr.capturePageScreenshot();
            performAction.verifyMessage("Create New Agent");

        }catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception occured while creating new Agent"
                    + e.getMessage());
            throw new CustomException(
                    "Exception occured while creating new Agent"
                            + e.getMessage());
		}
	}
}
