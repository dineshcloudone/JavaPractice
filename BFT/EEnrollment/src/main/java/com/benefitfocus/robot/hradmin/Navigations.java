package com.benefitfocus.robot.hradmin;

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

@RobotKeywords
public class Navigations {
	

	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected ManageBrowser browser;
	@Autowired
	protected Logging logger;	
	@Autowired
	protected Screenshot scr;

	public enum MainMenus {
		HOME, EMPLOYEES, GROUPSETUP,GROUPAPPROVALPREFERENCES,GROUPINFORMATIONREPORT, DATAANDREPORTING, MESSAGES, RESOURCES,CATEGORIES, GROUPSETTINGS, DEPENDENTS, DIRECTDEPOSIT, BENEFITDETAILS, BENEFITELIGIBILITY, DOCUMENTS, EMPLOYEEHISTORY, CONTENTMANAGER, OVERVIEW,MANAGEADMINISTRATORACCOUNTS,CALENDARS, RATEINFORMATION,EMPLOYEEBENEFITSUMMARYREPORT, EMPLOYEEDETAILREPORT, BENEFICIARYREPORT, WEBSTATS, DEFINEDCONTRIBUTIONS,FINDMEDICAREELIGIBLE,ADDADMINISTRATORACCOUNT,AGGREGATEGROUP,CUSTOMFIELDS,GROUPCUSTOMIZATION,IDENTIFICATION,MEMBERROLEHELPTEXT,OPENENROLLMENTSETTINGS,PERSONALCUSTOMIZATION,TOOLSANDREPORTSSETUP,UNIQUEPERSONVALIDATION,WORKCUSTOMIZATION,
		AGENTS,ADMINISTRATORS,PLANMATCHINGCPPW,RATEFACTORSURVEY,PLANS,CONTRIBUTIONAMOUNTS,PLANRATES,VIEWRATEINFORMATION,OFFERS,ENROLLMENTDATESANDTIMEZONE,ELIGIBILITYDATERULES,CATEGORYRETRORULES,REHIRERULES,CANCELLATIONTERMINATIONRULES,RETROACTIVITYANDEDIRULES,RELATIONSHIPTYPEDEPENDENCYRULES,COBRARULES,CARRIERIDENTIFIERS,PROSPECTDOCUMENTS,MASSCATEGORYCHANGE,VIEWSCHEDULEDPROCESSES,LOGINIDGENERATION
	};

	By home = By.linkText("Home");
	By employees = By.linkText("Employees");
	By dataAndReport = By.linkText("Data & Reporting");
	By messages = By.linkText("Messages");
	By resources = By.linkText("Resources");
	By groupSettings = By.linkText("Group Settings");

	By addNewEmployee = By.linkText("Add a new employee");
	By dependents = By.linkText("Dependents");
	By directDeposit = By.linkText("Direct deposit");
	By benefitDetails = By.linkText("Benefit details");
	By benefitEligibility = By.linkText("Benefit eligibility");
	By documents = By.linkText("Documents");
	By employeeHistory = By.linkText("Employee History");
	By overview = By.linkText("Overview");
	By startButton = By.linkText("Start");
	By contentManager=By.linkText("Content Manager");

	By groupApprovalPreferences=By.linkText("Group approval preferences");
	By groupApprovalMsg=By.xpath("//h2[text()='Select items from the list for which Task List Approval or Review items should be generated.']");
	By groupInformationReport=By.linkText("Group information report");
	By groupSetup=By.linkText("Group setup");
	By groupSettingsmsg=By.xpath("//h1[text()='Group Settings']");
	By benefitsElegibilityHeading=By.xpath("//div[@id='be-heading-content']//h2");
	By groupNavMenubasics=By.xpath("//table[@id='GroupNavMenubasics']");
	By groupNavMenuusers=By.xpath("//table[@id='GroupNavMenuusers']");
	By groupNavMenubenefit=By.xpath("//table[@id='GroupNavMenubenefit']");
	By groupNavMenudatesRules=By.xpath("//table[@id='GroupNavMenudatesRules']");
	By groupNavMenucarrier=By.xpath("//table[@id='GroupNavMenucarrier']");
	By groupNavMenutoolsReports=By.xpath("//table[@id='GroupNavMenutoolsReports']");
	By rateInformation = By.linkText("Rate information");
	By calendars = By.linkText("Calendars");
	By employeeBenefitSumamryReport=By.partialLinkText("Employee Benefit Summary Report");
	By employeeDetailReport=By.linkText("Employee Detail Report");
	
	By employeeBenefitSummaryReport = By.linkText("Employee Benefit Summary Report");
	By beneficiaryReport = By.linkText("Beneficiary Report");
	By webStats = By.linkText("Web stats");
	By definedContributions = By.partialLinkText("Defined Contributions");
	By findMedicareEligible=By.linkText("Find Medicare Eligible");
	
	//Locators for Menu Items in Group Setup page 	
	By basics=By.id("innerLinkGroupNavMenubasics");
	By users=By.id("innerLinkGroupNavMenuusers");
	By benefits=By.id("innerLinkGroupNavMenubenefit");
	By datesRules=By.id("innerLinkGroupNavMenudatesRules");
	By carrierInformation=By.id("innerLinkGroupNavMenucarrier");
	By toolsAndReports=By.id("innerLinkGroupNavMenutoolsReports");
	
	//Locators for Sub Menu Items Under Basics Tab 	
	By aggregateGroup=By.xpath("//a[contains(text(),'Aggregate Group')]");
	By customFields=By.xpath("//a[contains(text(),'Custom Fields')]");
	By groupCustomization=By.xpath("//a[contains(text(),'Group Customization')]");
	By identification=By.xpath("//a[contains(text(),'Identification')]");
	By memberRoleHelpText=By.xpath("//a[contains(text(),'Member Role Help Text')]");
	By openEnrollmentSettings=By.xpath("//a[contains(text(),'Open Enrollment Settings')]");
	By personalCustomization=By.xpath("//a[contains(text(),'Personal Customization')]");
	By toolsandReportsSetup=By.xpath("//a[contains(text(),'Tools and Reports Setup')]");
	By uniquePersonValidation=By.xpath("//a[contains(text(),'Unique Person Validation')]");
	By workCustomization=By.xpath("//a[contains(text(),'Work Customization')]");
	
	//Locators for Sub Menu Items Under Users Tab
	By agents=By.xpath("//a[contains(text(),'Agents')]");
	By administrators=By.xpath("//a[text()='Administrators']");
	By addAdministratorAccount=By.xpath("//a[contains(text(),'Add Administrator Account')]");
	
	//Locators for Sub Menu Items Under Benefits Tab
	By planMatchingCPPW=By.xpath("//a[contains(text(),'PlanMatchingCPPW')]");
	By rateFactorSurvey=By.xpath("//a[contains(text(),'Rate Factor Survey')]");
	By plans=By.xpath("//a[contains(text(),'Plans')]");
	By contributionAmounts=By.xpath("//a[contains(text(),'Contribution Amounts')]");
	By planRates=By.xpath("//a[contains(text(),'Plan Rates')]");
	By viewRateInformation=By.xpath("//a[contains(text(),'View Rate Information')]");
	By offers=By.xpath("//a[contains(text(),'Offers')]");
	
	//Locators for Sub Menu Items Under Dates & Rules Tab
	By enrollmentDatesandTimeZone=By.xpath("//a[contains(text(),'Enrollment Dates and Time Zone')]");
	By eligibilityDateRulesORCategoryRetroRules=By.xpath("//a[contains(text(),'Eligibility Date Rules/Category Retro Rules')]");
	By rehireRules=By.xpath("//a[contains(text(),'Rehire Rules')]");
	By cancellationTerminationRules=By.xpath("//a[contains(text(),'Cancellation/Termination Rules')]");
	By retroactivityandEDIRules=By.xpath("//a[contains(text(),'Retroactivity and EDI Rules')]");
	By relationshipTypeDependencyRules=By.xpath("//a[contains(text(),'RRelationship Type Dependency Rules')]");
	By COBRARules=By.xpath("//a[contains(text(),'COBRA Rules')]");
	
	//Locators for Sub Menu Items Under Carrier Information
	By carrierIdentifiers=By.xpath("//a[contains(text(),'Carrier Identifiers')]");
	By prospectDocuments=By.xpath("//a[contains(text(),'Prospect Documents')]");	

	//Locators for Sub Menu Items Under Tools And Reports Setup
	By massCategoryChange=By.xpath("//a[contains(text(),'Mass Category Change')]");
	By viewScheduledProcesses=By.xpath("//a[contains(text(),'View Scheduled Processes')]");
	By loginIDGeneration=By.xpath("//a[contains(text(),'Login ID Generation')]");
	
	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : NavigateTo keyword to perform Side menu navigation in eEnrollment application HR admin role
	 * 
	 * PreCondition : HR admin logged in
	 * 
	 * PostCondition : Respective menu item page
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strSideMenu - type argument takes Menu option as input value | 
	 * | HOME, Employees, DataAndReporting, Messages, Calendars, Rate Information, Group Settings, Web Stats |
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "strSideMenu" })
	public void navigateToHRMenu(String strSideMenu) {

		try {

			MainMenus sdf = MainMenus.valueOf(strSideMenu.toUpperCase());

			switch (sdf) {

			case HOME:
				performAction.click(home, "HOME link");
				if (performAction.isAlertPresent()) {
					performAction.acceptAlert();
				}
				break;
			case EMPLOYEES:
				performAction.jsclick(employees, "Employees link");
				Thread.sleep(2000);
				if (performAction.isAlertPresent()) {
					performAction.acceptAlert();		
				}
				break;
			case DATAANDREPORTING:
				performAction.click(dataAndReport, "DATAANDREPORTING link");
				break;
			case MESSAGES:
				performAction.click(messages, "MESSAGES link");
				break;
			case RESOURCES:
				performAction.jsclick(resources, "RESOURCES link");
				break;
			case GROUPSETTINGS:
				performAction.click(groupSettings, "GROUPSETTINGS link");				
				break;

			case DEPENDENTS:
				Thread.sleep(2000);
				performAction.jsclick(dependents, "Dependents link");
				Thread.sleep(2000);
				if (performAction.isAlertPresent()) {
					performAction.acceptAlert();
				}
				break;
			case DIRECTDEPOSIT:
				performAction.waitForPageLoad();
				performAction.click(directDeposit, "Direct Deposit link");
				performAction.waitForPageLoad();
				if (performAction.isAlertPresent()) {
					performAction.closeAlertAndGetItsText();
				}
				break;
			case BENEFITDETAILS:
				Thread.sleep(2000);
				performAction.click(overview, "Overview link");
				Thread.sleep(2000);
				if (performAction.isAlertPresent()) {
				performAction.acceptAlert();
				}
				performAction.click(benefitDetails, "Benefit details link");
				Thread.sleep(2000);
				if (performAction.isAlertPresent()) {
					performAction.acceptAlert();
				}
				break;
			case BENEFITELIGIBILITY:
				if (performAction.isAlertPresent()) {
					performAction.acceptAlert();		
				}
				performAction.click(benefitEligibility,
				"Benefit Eligibility link");
				if (performAction.isAlertPresent()) {
					performAction.acceptAlert();		
				}				
				performAction.waitUntilElementPresent(benefitsElegibilityHeading);
				performAction.isElementPresent(benefitsElegibilityHeading);
				if (performAction.isAlertPresent()) {
					performAction.acceptAlert();		
				}
				Thread.sleep(2000);
				break;
			case DOCUMENTS:
				performAction.click(documents, "Documents link");
				break;
			case EMPLOYEEHISTORY:
				performAction.click(employeeHistory, "Employee History link");
				Thread.sleep(2000);
				if (performAction.isAlertPresent()) {
					performAction.acceptAlert();
				}	
				scr.capturePageScreenshot();
				break;
			case CONTENTMANAGER:
				performAction.click(contentManager, "Content Manager link");
				scr.capturePageScreenshot();
				break;
			case EMPLOYEEBENEFITSUMMARYREPORT:
				Thread.sleep(2000);
				performAction.click(employeeBenefitSummaryReport, "Employee Benefit Summary Report Overview link");
				Thread.sleep(2000);
				if (performAction.isAlertPresent()) {
					performAction.acceptAlert();
				}
				break;
			case EMPLOYEEDETAILREPORT:
				performAction.click(employeeDetailReport, "Employee Detail Report link");
				break;
			case GROUPSETUP:
				performAction.click(groupSettings, "Group Settings link");	
				String groupsettingsMsg=browser.getCurrentWebDriver().findElement(groupSettingsmsg).getText();
				performAction.verify(groupSettingsmsg, groupsettingsMsg, "Group Settings");
				performAction.click(groupSetup, "Group Setup");
				scr.capturePageScreenshot();
				break;
			case GROUPINFORMATIONREPORT:
				performAction.click(groupSettings, "Group Settings link");	
				groupsettingsMsg=browser.getCurrentWebDriver().findElement(groupSettingsmsg).getText();
				performAction.verify(groupSettingsmsg, groupsettingsMsg, "Group Settings");
				performAction.click(groupInformationReport, "GROUPINFORMATIONREPORT");				
				scr.capturePageScreenshot();
				break;
				
			case GROUPAPPROVALPREFERENCES:
				performAction.click(groupSettings, "Group Settings link");						
				performAction.click(groupApprovalPreferences, "Group Approval Preferences");
				String value="Select items from the list for which Task List Approval or Review items should be generated.";
				performAction.verify(groupApprovalMsg, value, "Verification Message");
				scr.capturePageScreenshot();
				break;

			case  CATEGORIES:
				performAction.click(By.xpath("//h2[contains(text(),'Categories')]/descendant::span"), "Categories Link");
				scr.capturePageScreenshot();
				break;

			case OVERVIEW:
				Thread.sleep(2000);
				performAction.click(overview, "Overview link");
				Thread.sleep(2000);
				if (performAction.isAlertPresent()) {
					performAction.acceptAlert();
				}	
				scr.capturePageScreenshot();
				break;
			case MANAGEADMINISTRATORACCOUNTS:
				performAction.click(By.xpath("//span[contains(text(),'Manage administrator accounts')]"), "Manage administrator Accounts Link");
				scr.capturePageScreenshot();
				break;
			case CALENDARS:
				//performAction.click(groupSettings, "Group Settings link");
				performAction.waitUntilElementPresent(calendars);
				performAction.click(calendars, "Calendars link");
				scr.capturePageScreenshot();
				break;
			case RATEINFORMATION:
				//performAction.click(groupSettings, "Group Settings link");
				performAction.waitUntilElementPresent(rateInformation);
				performAction.click(rateInformation, "Rate Information link");
				scr.capturePageScreenshot();
				break;
			case BENEFICIARYREPORT:
				Thread.sleep(2000);
				performAction.click(beneficiaryReport, "Beneficiary Report Overview link");
				Thread.sleep(2000);
				if (performAction.isAlertPresent()) {
					performAction.acceptAlert();
				}
				break;
			case WEBSTATS:
				performAction.waitUntilElementPresent(webStats);
				performAction.click(webStats, "Web Stats Link");
				scr.capturePageScreenshot();
				break;
			case DEFINEDCONTRIBUTIONS:
				performAction.waitUntilElementPresent(definedContributions);
				performAction.click(definedContributions, "Defined Contributions Link");
				break;
			case FINDMEDICAREELIGIBLE:
				performAction.waitUntilElementPresent(findMedicareEligible);
				performAction.click(findMedicareEligible, "Click on Find Medicare Eligible");
				scr.capturePageScreenshot();
				break;
			case AGENTS:
				performAction.mouseOver(users, "Users");
				performAction.click(agents, "AGENTS");
				scr.capturePageScreenshot();
				break;
			case ADMINISTRATORS:
				performAction.mouseOver(users, "Users");
				performAction.click(administrators, "ADMINISTRATORS");
				scr.capturePageScreenshot();
				break;
			case ADDADMINISTRATORACCOUNT:
				performAction.mouseOver(users, "Users");
				performAction.click(addAdministratorAccount, "Add AdministratorAccount");
				scr.capturePageScreenshot();
				break;
			
			case AGGREGATEGROUP:
				performAction.mouseOver(basics, "BASICS");	
				performAction.click(aggregateGroup, "Aggregate Group");
				scr.capturePageScreenshot();
				break;
			case CUSTOMFIELDS:
				performAction.mouseOver(basics, "BASICS");
				performAction.click(customFields, "Custom Fields");
				scr.capturePageScreenshot();
				break;
			case GROUPCUSTOMIZATION:
				performAction.mouseOver(basics, "BASICS");
				performAction.click(groupCustomization, "Group Customization");
				scr.capturePageScreenshot();
				break;
			case IDENTIFICATION:
				performAction.mouseOver(basics, "BASICS");
				performAction.click(identification, "identification");
				scr.capturePageScreenshot();
				break;
			case MEMBERROLEHELPTEXT:
				performAction.mouseOver(basics, "BASICS");
				performAction.click(memberRoleHelpText, "Member Role Help Text");
				scr.capturePageScreenshot();
				break;
			case OPENENROLLMENTSETTINGS:
				performAction.mouseOver(basics, "BASICS");
				performAction.click(openEnrollmentSettings, "OpenEnrollment Settings");
				scr.capturePageScreenshot();
				break;
			case PERSONALCUSTOMIZATION:
				performAction.mouseOver(basics, "BASICS");
				performAction.click(personalCustomization, "personalCustomization");
				scr.capturePageScreenshot();
				break;
			case TOOLSANDREPORTSSETUP:
				performAction.mouseOver(basics, "BASICS");
				performAction.click(toolsandReportsSetup, "Tools and Reports Setup");
				scr.capturePageScreenshot();
				break;
			case UNIQUEPERSONVALIDATION:
				performAction.mouseOver(basics, "BASICS");
				performAction.click(uniquePersonValidation, "uniquePersonValidation");
				scr.capturePageScreenshot();
				break;			
			case WORKCUSTOMIZATION:
				performAction.mouseOver(basics, "BASICS");
				performAction.click(workCustomization, "workCustomization");
				scr.capturePageScreenshot();
				break;
			case PLANMATCHINGCPPW:
				performAction.mouseOver(benefits, "BENEFITS");
				performAction.click(planMatchingCPPW, "PLAN MATCHING CPPW");
				scr.capturePageScreenshot();
				break;
			case RATEFACTORSURVEY:
				performAction.mouseOver(benefits, "BENEFITS");
				performAction.click(rateFactorSurvey, "RATEFACTOR SURVEY");
				scr.capturePageScreenshot();
				break;
			case PLANS:
				performAction.mouseOver(benefits, "BENEFITS");
				performAction.click(plans, "PLANS");
				scr.capturePageScreenshot();
				break;
			case CONTRIBUTIONAMOUNTS:
				performAction.mouseOver(benefits, "BENEFITS");
				performAction.click(contributionAmounts, "CONTRIBUTION AMOUNTS");
				scr.capturePageScreenshot();
				break;
			case PLANRATES:
				performAction.mouseOver(benefits, "BENEFITS");
				performAction.click(planRates,"PLAN RATES");
				scr.capturePageScreenshot();
				break;
			case VIEWRATEINFORMATION:
				performAction.mouseOver(benefits, "BENEFITS");
				performAction.click(viewRateInformation, "VIEW RATE INFORMATION");
				scr.capturePageScreenshot();
				break;
			case OFFERS:
				performAction.mouseOver(datesRules, "DATES AND RULES");
				performAction.click(offers, "OFFERS");
				scr.capturePageScreenshot();
				break;
				
			case ENROLLMENTDATESANDTIMEZONE:
				performAction.mouseOver(datesRules, "DATES AND RULES");
				performAction.click(enrollmentDatesandTimeZone, "ENROLLMENT DATES AND TIME ZONE");
				scr.capturePageScreenshot();
				break;
			case ELIGIBILITYDATERULES:
				performAction.mouseOver(datesRules, "DATES AND RULES");
				performAction.click(eligibilityDateRulesORCategoryRetroRules, "ELIGIBILITY DATE RULES");
				scr.capturePageScreenshot();
				break;
			case CATEGORYRETRORULES:
				performAction.mouseOver(datesRules, "DATES AND RULES");
				performAction.click(eligibilityDateRulesORCategoryRetroRules, "CATEGORY RETRO RULES");
				scr.capturePageScreenshot();
				break;
			case REHIRERULES:
				performAction.mouseOver(datesRules, "DATES AND RULES");
				performAction.click(rehireRules, "REHIRE RULES");
				scr.capturePageScreenshot();
				break;
			case CANCELLATIONTERMINATIONRULES:
				performAction.mouseOver(datesRules, "DATES AND RULES");
				performAction.click(cancellationTerminationRules, "CANCELLATION TERMINATION RULES");
				scr.capturePageScreenshot();
				break;
			case RETROACTIVITYANDEDIRULES:
				performAction.mouseOver(datesRules, "DATES AND RULES");
				performAction.click(retroactivityandEDIRules, "RETROACTIVITY AND EDI RULES");
				scr.capturePageScreenshot();
				break;
			case RELATIONSHIPTYPEDEPENDENCYRULES:
				performAction.mouseOver(datesRules, "DATES AND RULES");
				performAction.click(relationshipTypeDependencyRules, "RELATIONSHIPTYPE DEPENDENCY RULES");
				scr.capturePageScreenshot();
				break;
			case COBRARULES:
				performAction.mouseOver(benefits, "BENEFITS");
				performAction.click(COBRARules, "COBRA RULES");
				scr.capturePageScreenshot();
				break;
			case CARRIERIDENTIFIERS:
				performAction.mouseOver(carrierInformation, "CARRIER INFORMATION");
				performAction.click(carrierIdentifiers, "CARRIER IDENTIFIERS");
				scr.capturePageScreenshot();
				break;
			case PROSPECTDOCUMENTS:
				performAction.mouseOver(carrierInformation, "CARRIER INFORMATION");
				performAction.click(COBRARules, "PROSPECT DOCUMENTS");
				scr.capturePageScreenshot();
				break;
			case MASSCATEGORYCHANGE:
				performAction.mouseOver(toolsAndReports, "TOOLS AND REPORTS");
				performAction.click(massCategoryChange, "MASS CATEGORY CHANGE");
				scr.capturePageScreenshot();
				break;
			case VIEWSCHEDULEDPROCESSES:
				performAction.mouseOver(toolsAndReports, "TOOLS AND REPORTS");
				performAction.click(viewScheduledProcesses, "VIEWSCHEDULEDPROCESSES");
				scr.capturePageScreenshot();
				break;
			case LOGINIDGENERATION:
				performAction.mouseOver(toolsAndReports, "TOOLS AND REPORTS");
				performAction.click(loginIDGeneration, "LOGIN ID GENERATION");
				scr.capturePageScreenshot();
				break; 	
				
			}
			

		} catch (Exception e) {
			logger.info("Exception in performing HR admin menu navigations "
					+ e.getMessage());
			throw new CustomException(
					"Exception in performing HR admin menu navigations "
					+ e.getMessage());
		}
	}
}
