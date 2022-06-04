package com.benefitfocus.robot.system;

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

@RobotKeywords
public class System5xDashboard {
	@Autowired
	protected ManageBrowser browser;
	@Autowired
	protected Utilities utils;
	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected Logging logger;
	@Autowired
	protected Screenshot scr;

	public enum searchtypemenu {
		JOBNAME, PROCESSID, EDIDESTINATION, SSN, SPONSOROID
	};

	By systemTab = By.id("innerLinksystemSetup");
	By dashboardTab = By.linkText("Dashboard");
	By jobSearchTextField = By.id("dashboardSearchInputField");
	By jobSearchButton = By.id("innerLinknavigationSearchButton");
	By jobSearchTyperopdownButton = By
			.id("contextualMenuAnchordashboardsearchBy");
	By jobSearchTypeName = By.id("dashboardJobName");
	By jobSearchTypeSSN = By.id("SSN_OID");
	By jobSearchEDI = By.id("EDIDestination");
	By jobsearchTypeProcessId = By.id("dashboardProcessId");
	By jobSearchSponsorOid = By.id("sponsorOID");
	String jobSearchResult = ".//*[@id='dataExchangeServiceResultDisplay']";

	By clickOnJob = By.cssSelector("div[id^='jobName']");
	By viewJobDetails = By
			.cssSelector("img[id^='contextual'][alt ='Member Options']");
	By viewJobSchedule = By.linkText("View Job Schedule");
	By runJobNow = By.linkText("Run Job Now");
	// By executeJob = By.linkText("Execute Job");

	By executeJob = By.linkText("Execute Job");
	By editJobParameters = By.linkText("Edit Job Parameters & Schedule");
	By ssnRadioButton = By
			.id("jobParameter_subscriberOptionForJobSchedule[subbyssn]");
	By subscriberOidTextBox = By.id("jobParameter_subscriber_oid");
	By subscriberSsnTextBox = By.id("jobParameter_ssns");
	By oidRadioButton = By
			.id("jobParameter_subscriberOptionForJobSchedule[subbysoid]");

	By extractionPeriodCombobox = By.id("jobParameter_payroll_extraction_period");
	By jobscopeNextButton = By.linkText("Next");

	By extractionModeCombobox = By.id("jobParameter_mode");
	By finishButton = By.linkText("Finish");
	By groupSponsorOID = By.id("jobParameter_sponsoroids");

	/* Click on Search field text box */
	private void clickAndEnterJobNameInSearchBox(String jobName) {
		performAction.clearEnter(jobSearchTextField, jobName,
				"Job Search Field Text Box");
	}

	/* Click on Search Field Button */
	private void clickJobSearchButton() {
		performAction.click(jobSearchButton, "Job Search Button");
	}

	/* Select Search type */
	private void selectSearchType(String searchtype) {
		performAction.mouseOver(jobSearchTyperopdownButton, "Job Search Type ");

		searchtypemenu sdf = searchtypemenu.valueOf(searchtype.toUpperCase());

		switch (sdf) {
		case JOBNAME:
			performAction.click(jobSearchTypeName, "Job Name");
			break;
		case PROCESSID:
			performAction.click(jobsearchTypeProcessId, "Process ID");
			break;
		case EDIDESTINATION:
			performAction.click(jobSearchEDI, "EDI Destination");
			break;
		case SSN:
			performAction.click(jobSearchTypeSSN, "SSN/OID");
			break;
		case SPONSOROID:
			performAction.click(jobSearchSponsorOid, "Sponsor OID");
			break;
		}
	}

	/* Click on Selected Job */
	private void clickSelectedJob() {
		performAction.click(clickOnJob, "Click on Selected Job");
	}

	/* Click on View Job Schedule Tab */
	private void clickJobSchedule() {
		performAction.mouseOver(viewJobDetails, "Click on Selected Job");
		utils.sleep(1000);
		performAction.click(viewJobSchedule, "View Job Schedule");
	}

	/* Click on Run Job Now */
	private void clickRunJobNow() {
		performAction.mouseOver(viewJobDetails, "Click on Selected Job");
		utils.sleep(1000);
		performAction.click(runJobNow, "Run Jon Now");

	}

	/* Click on Execute Job */
	private void clickExecuteJob() {
		performAction.click(executeJob, "Execute Job");
	}

	private void clickNextButtonInJobScope() {
		performAction
				.click(jobscopeNextButton, "Next button in Job Scope page");
	}

	private void clickFinishButtonOnJobConfigurationPage() {
		performAction.click(finishButton,
				"Finish Button in Prcessing Instructions");
		performAction.acceptAlert();
	}

	/* Click on Run Job Now */
	private void clickEditJobParameters() {
		performAction.mouseOver(viewJobDetails, "Click on Selected Job");
		utils.sleep(1000);
		performAction.click(editJobParameters, "click on edit job parameters");

	}

	/* Click on SSN tab and enter subscriber SSN/OID value */
	private void enterSubscriberValue(String subscriberValue) {
		if (subscriberValue.toUpperCase().startsWith("SSN")) {
			performAction.click(ssnRadioButton, "SSN Radio Button");
			subscriberValue = subscriberValue.substring(4);
			performAction.clearEnter(subscriberSsnTextBox, subscriberValue,
					"SubscriberOid TextBox");
		} else if (subscriberValue.toUpperCase().startsWith("OID")) {
			performAction.click(oidRadioButton, "Subscriber OID Radio button");

			subscriberValue = subscriberValue.substring(4);
			performAction.clearEnter(subscriberOidTextBox, subscriberValue,
					"SubscriberOid TextBox");
		}
	}

	/* Select Extraction Period for Job run */
	private void extractionPeriod(String value) {
		performAction.select(extractionPeriodCombobox, value,
				"Extraction Period Combobox");
	}

	/* Select Extraction mode for the current running Job */
	private void extractionMode(String value) {
		performAction.select(extractionModeCombobox, value,
				"Extraction Mode Combobox");
	}

	/**
	 * Keyword to Perform Job Search
	 * 
	 * <pre>
	 * <precondition>
	 * <b> eEnrollment application home page should open </b>
	 * <b> Parameter1</b>
	 * <b> Supply Job full name as a input 
	 * <b> Parameter2 </b>
	 * <b> Supply search type as a input 
	 * <b> Search type : | PROCESSID / JOBNAME / SSN or OID / EDIDESTINATION / SPONSOROID |
	 * <postcondition>
	 * <b>Specified Job will searched and appears on the screen, if available </b>
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({ "jobName", "searchtype" })
	public void performJobSearch(String jobName, String searchType) {
		try {

			this.selectSearchType(searchType);
			this.clickAndEnterJobNameInSearchBox(jobName);
			utils.sleep(1000);
			this.clickJobSearchButton();

			if (browser.getCurrentWebDriver()
					.findElement(By.xpath(jobSearchResult)).isDisplayed())

			{
				By searchList = By.xpath(jobSearchResult + "//table");
				System.out.println("list" + searchList.toString());

				int jobsCount = browser.getCurrentWebDriver()
						.findElements(searchList).size();
				if (jobsCount == 0)
					throw new RuntimeException(
							"No Jobs available From the Search Result");

			}
		}

		catch (Exception e) {

			throw new CustomException(
					"Exception occured while performing Job Search" + " "
							+ e.getMessage());
		}
	}

	/*
	 * /** Keyword to Run Selected Job
	 * 
	 * <pre> <precondition> <b> Specified Job Should open </b> <b>
	 * Parameter1</b> <b> Supply Job full name as a input <postcondition>
	 * <b>Selected Job will be ran</b> </pre>
	 */
	@RobotKeyword
	public void runCurrentJob() {
		try {
			// this.clickSelectedJob();
			this.clickJobSchedule();
			this.clickRunJobNow();
			utils.sleep(2000);
			this.clickExecuteJob();
		}
		catch (Exception e) {
			throw new CustomException("Exception occured while running Job"
					+ " " + e.getMessage());
		}
	}

	/*
	 * /** Keyword to enter/update Group Sponsor OID value
	 * 
	 * <pre> <precondition> <b> Job configuration page should open </b> <b>
	 * Parameter1</b> <b> Supply Group sponsor OID value as a input
	 * <postcondition> <b>Selected Job will be ran</b> </pre>
	 */
	private void updateGroupSponsorOid(String OIDValue) {
		try {
			// entering group sponsor OID
			performAction.clearEnter(groupSponsorOID, OIDValue, "Group Sponsor OID");

		}
		catch (Exception e) {
			throw new CustomException("Exception occured while updating Sponsor OID"
					+ " " + e.getMessage());
		}
	}

	/**
	 * Author : Teja
	 * 
	 * Keyword to Perform Modify the job configuration
	 * 
	 * <pre>
	 * <precondition>
	 * <b> eEnrollment application home page should open and job displayed on the screen </b>
	 * 
	 * <b> Parameter1</b>
	 * <b> groupSubscriberValue : Group subscriber oid.
	 * <b> example </b>
	 * | groupSubscriberValue  - 1481289201 |
	 * 
	 * <b> Parameter1</b>
	 * 
	 * <b> subscriber: it takes SSN/OID value as the parameter
	 * <b> example </b>
	 * | SSN:value / OID:value |
	 * 
	 * <b> Parameter2 </b>
	 * <b> extraction period: it takes the extraction period of the job
	 * <b> example : | Single Pay Period / Current / Previous / Open |
	 * 
	 * <b> Parameter3 </b>
	 * <b> extraction mode: it accepts the extraction mode of the current job
	 * <b> example : | Full File / Changes Only / Full File With Changes |
	 * 
	 * <postcondition>
	 * <b> Job configuration modified and saved the modifications   </b>
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({ "groupSubscriberValue","subscriber", "extractionperiod", "extractionmode" })
	public void modifyJobConfiguration(String groupSubscriberValue, String subscriber,
			String extractionPeriod, String extractionMode) {
		try {

			this.clickJobSchedule();
			this.clickEditJobParameters();
			utils.sleep(1000);
			this.updateGroupSponsorOid(groupSubscriberValue);
			this.enterSubscriberValue(subscriber);
			// to select extraction period
			this.extractionPeriod(extractionPeriod);
			// to click on next button in Jobscope page
			this.clickNextButtonInJobScope();
			utils.sleep(5000);
			// To select extraction mode
			this.extractionMode(extractionMode);
			this.clickFinishButtonOnJobConfigurationPage();

		}

		catch (Exception e) {

			throw new CustomException(
					"Exception occured while modifying Job Configuration" + " "
							+ e.getMessage());
		}
	}
}