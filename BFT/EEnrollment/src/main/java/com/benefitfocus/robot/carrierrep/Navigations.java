package com.benefitfocus.robot.carrierrep;

import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.ActionKeywords;

@RobotKeywords
public class Navigations {


	@Autowired
	protected ActionKeywords performAction;

	public enum MainMenus {
		GROUPS, INDIVIDUALS, REPORTS, ACCOUNTSMANAGMENT, SYSTEMSETUP, CARRIERMEMBERNOTES ,
	};

	By groups = By.id("innerLinkgroups");
	By individuals = By.id("innerLinkindividuals");
	By reports = By.id("innerLinkreports");
	By accountmanagment = By.id("innerLinkaccountManagement");
	By systemsetup = By.id("innerLinksystemSetup");
	By carriermembernotes = By.id("innerLinkcarrierMemberNotes");
		By global = By.id("innerLinkglobalSetup");
		
	/**
	 * <pre> 
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : NavigateTo keyword to perform navigation in eEnrollment application vista role
	 * 
	 * Role : Carrier Representative Role
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
	public void navigateToCarrierRepMenu(String strMainMenu, String strSubMenu) {

		MainMenus sdf = MainMenus.valueOf(strMainMenu.toUpperCase());

		switch (sdf) {

		case GROUPS:
			performAction.mouseOver(groups, "Groups Menu item");
			break;
		case INDIVIDUALS:

		case REPORTS:
            performAction.mouseOver(reports, "Carrier Menu");
            break;
		case ACCOUNTSMANAGMENT:
			performAction.mouseOver(accountmanagment, "Carrier Menu");
			break;
		case CARRIERMEMBERNOTES:
			performAction.mouseOver(carriermembernotes, "Carrier Member Notes");
			break;
			
		
		}
		subMenuNavigation(strSubMenu);
	}

	/**
	 * 'subMenuNavigation' Keyword to click on the menu option after mouse over on main menu 
	 */
	private void subMenuNavigation(String strSubMenu) {
		By locator = By.linkText(strSubMenu);
		performAction.click(locator, strSubMenu);
	}

}
