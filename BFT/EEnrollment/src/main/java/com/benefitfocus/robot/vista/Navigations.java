package com.benefitfocus.robot.vista;

import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.Screenshot;

@RobotKeywords
public class Navigations {

	@Autowired
	protected ActionKeywords performAction;

	@Autowired
	protected Logging logger;

	@Autowired
	protected Screenshot scr;

	public enum MainMenus {
		GROUPS, INDIVIDUALS, CARRIERS, AGENCIES, GLOBAL, SYSTEM, USERS, BENEFITS, BASICS, DATESANDRULES,SURVEYSANDHEALTHSTATEMENTS, PLUGINS, CARRIERINFORMATION, LANGUAGEANDAGREEMENTS
	};

	By groups = By.id("innerLinkgroups");
	By individuals = By.id("innerLinkindividuals");
	By carriers = By.id("innerLinkcarrierSetup");
	By agencies = By.id("innerLinkagencySetup");
	By global = By.id("innerLinkglobalSetup");
	By system = By.id("innerLinksystemSetup");
	By users = By.id("innerLinkGroupNavMenuusers");
	By surveysAndHealthStatements = By.id("innerLinkcarrierSetupSurveyHealthStatementsTab");
	By openInHRRole = By.cssSelector("a[href*='openHradmin']");
	By employeeOverviewpopup = By
			.xpath("//button[contains(text(),'Not right now')]");
	By benefits = By.id("innerLinkGroupNavMenubenefit");
	By basics = By.id("innerLinkGroupNavMenubasics");
	By carrierBasics = By.id("innerLinkcarrierSetupBasicsTab");
	By plugins = By.cssSelector("a[href*='managePlugins?method']");

	By menuBarSearch=By.id("contextualMenuAnchorsearchBy");
	By searchInputField=By.id("searchInputField");
	By searchButton=By.linkText("Search");
	By carrierInformation=By.id("innerLinkGroupNavMenucarrier");
	By datesandrules=By.id("innerLinkGroupNavMenudatesRules");	
	By languageandagreements = By.id("innerLinkcarrierSetupLanguageAndAgreementsTab");

	By memberOptions = By.cssSelector("a[href*='onClickNavMemberMenu']");
	
	//clickMemberOptions
		private void clickMemberOptions()
		{
			performAction.click(memberOptions, "Click on member Mainmenu in vista role");
		}

		//clickSubMenuOptionInMemberOptions
		private void clickSubMenuOptionInMemberOptions(String strSubMenuOption)
		{
			By memberSubMenu = By.linkText(strSubMenuOption);
			performAction.click(memberSubMenu, "Click on "+ strSubMenuOption +" in member submenu options");

			if (strSubMenuOption.equalsIgnoreCase("Open in Member Role")){
				performAction.selectLatestWindow("Login Information");
				performAction.verifyMessage("Learning Center");
			}else if (strSubMenuOption.equalsIgnoreCase("Open in HR Role")){
				performAction.selectLatestWindow("Group Settings");
				performAction.verifyMessage("Manage employee");
			}

			if (performAction.isElementPresent(employeeOverviewpopup)) {
				performAction.click(employeeOverviewpopup, "Overview popup");
			}
		}
	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : NavigateTo keyword to perform navigation in eEnrollment application vista role
	 * 
	 * PreCondition : Vista Home page
	 * 
	 * PostCondition : Respective menu item page
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strMainMenu | strSubMenu | 
	 * | Groups | Add New Group | 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "strMainMenu", "strSubMenu" })
	public void navigateTo(String strMainMenu, String strSubMenu) {

		MainMenus sdf = MainMenus.valueOf(strMainMenu.toUpperCase());

		switch (sdf) {

		case GROUPS:
			performAction.mouseOver(groups, "Groups Menu item");
			break;
		case INDIVIDUALS:

		case CARRIERS:
            performAction.mouseOver(carriers, "Carrier Menu");
            break;
		case AGENCIES:
			performAction.mouseOver(agencies, "Carrier Menu");
			break;
		case GLOBAL:
			performAction.mouseOver(global, "Global Menu Item");
			break;
			
		case BENEFITS:
			performAction.mouseOver(benefits, "Benefits Menu");
			break;
		case SYSTEM:

		case USERS:
			performAction.mouseOver(users, "Users Menu item");
			break;
		case BASICS:
			if(performAction.isElementPresent(basics)){
			performAction.mouseOver(basics, "Basics Menu Item");
			}
			else{
				performAction.mouseOver(carrierBasics, "Carrier Basics Menu");
			}
			break;
		case DATESANDRULES:
			if(performAction.isElementPresent(datesandrules)){
				performAction.mouseOver(datesandrules, "Dates And Rules");
			}
			break;	
		case SURVEYSANDHEALTHSTATEMENTS:
			performAction.mouseOver(surveysAndHealthStatements, "Surveys And Health Statements Menu Item");
			break;
		case CARRIERINFORMATION:
			performAction.mouseOver(carrierInformation, "Carrier Information");
			break;
		case PLUGINS:
			if(performAction.isElementPresent(basics)){
				performAction.mouseOver(basics, "Basics Menu Item");
				performAction.click(plugins, "Plugins");
				}
        case LANGUAGEANDAGREEMENTS:
            performAction.mouseOver(languageandagreements, "Language And Agreements Menu Item");
            break;
		}
		subMenuNavigation(strSubMenu);
	}

	/**
	 * 'subMenuNavigation' Keyword to click on the menu option after mouse over on main menu 
	 */
	private void subMenuNavigation(String strSubMenu) {
		By locator = By.linkText(strSubMenu);
		performAction.jsclick(locator, strSubMenu);
	}
	
	/**
	 * <pre> 
	 * Author  : Ch Phani Srikar
	 *  
	 * Description : Keyword to open the group in HR role from group details page
	 * 
	 * PreCondition : Group details page 
	 * 
	 * PostCondition : Opens the group in hr role
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strMainMenu | strSubMenu | 
	 * | Groups | Add New Group | 
	 * </pre> 
	 **/
	@RobotKeyword
	public void openGroupInHRRole() {

		performAction.click(openInHRRole, "Open in HR role");

		performAction.selectLatestWindow("Group Settings");
		try {

			if (performAction.isElementPresent(employeeOverviewpopup)) {
				performAction.click(employeeOverviewpopup, "Overview popup");
			}
			Thread.sleep(1000);

		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			throw new CustomException("Exception occured: \n"
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Teja p
	 *
	 * Description : This keyword helps to serach the Group/Member from Vista
	 *
	 * PreCondition : Vista Home page
	 *
	 * PostCondition : Respective Group/Member page should be displayed
	 *
	 * <b>Parameters & Example </b>
	 *
	 * |strMenuBarSearch |
	 * |strMenuBarSearchValue |
	 *
	 * <b>strMenuBarSearch Example :</b>
	 * |Group/Prospect Name ,Sponsor / Group ID,Prospect Alternate ID,Tax ID,Sponsor OID,Member Name / SSN,Member Login,Employer ID,
	 * Member ID / Issuer Household ID,Alternate ID / Issuer Member ID,Member OID|
	 *
	 * <b>strMenuBarSearchValue Example :</b>
	 * |5429473465,Bf QA Automation,etc |
	 *
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strMenuBarSearch", "strMenuBarSearchValue" })
	public void performAdvancedGroupSearch(String strMenuBarSearch, String strMenuBarSearchValue) {

		performAction.mouseOver(menuBarSearch, "Clicking Menu Bar Search");
		By clickValue=By.linkText(strMenuBarSearch);
		performAction.click(clickValue, "Clicking the type of search in Menu Bar");
		performAction.enter(searchInputField, strMenuBarSearchValue, "Value to enter in the Member Search field");
		performAction.click(searchButton, "Clicking search button in Menu Bar");
	}
	
	/**
	 * <pre>
	 * Author  : Srikanth G
	 *
	 * Description : Keyword to select Member SubMenu option from Member options in member search results page
	 *
	 * Role: Vista Role
	 *
	 * PreCondition : specific Member search results page
	 *
	 * PostCondition : selects member option as per given option
	 *
	 * Java File Name: Vista/Navigations.java
	 *
	 * <b>Parameters & Example </b>
	 *
	 * |  strSubMenuOption |
	 * | Any option in member submenu ex:Open in Member Role or Open in HR Role |
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strSubMenuOption" })
	public void openMemberOptionFromVistaRole(String strSubMenuOption) {
		try {

			this.clickMemberOptions();
			this.clickSubMenuOptionInMemberOptions(strSubMenuOption);
			scr.capturePageScreenshot();

		} catch (Exception e) {
			logger.info("Exception occured while selecting member options"
					+ e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException("Exception occured while selecting member options: \n" + e.getMessage());
		}
	}
}
