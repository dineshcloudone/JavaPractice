package com.benefitfocus.robot.vista.global;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import org.openqa.selenium.Alert;
import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.ReadJsonTestData;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;

@RobotKeywords
public class Announcements {

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
	
	By delete = By.linkText("Delete");
	By okButton = By.linkText("OK");
	By createNewAnnouncements = By.linkText("Create New Announcement");
	By announcementType = By.id("tag");
	By iframe = By.xpath(".//*[@id='cke_1_contents']/iframe");
	By clickSave = By.linkText("Save");
	By clickNextMemberUi = By.xpath("//button[@class='btn btn-primary']");
	
	//Private Methods
	
	private void createNewAnnouncements() {
		performAction.click(createNewAnnouncements, "Click Create New Announcements");
		performAction.waitForPageLoad();
		performAction.select(announcementType, "Member", "Member");
		performAction.click(iframe, "Click in Form");
	}
	
	private void enterGlobalAnnouncement() {
		performAction.enter(iframe, "Global Announcement for Member", "Global Announcement for Member");
	}
	
	private void enterGroupAnnouncement() {
		performAction.enter(iframe, "Group Announcement for Member", "Group Announcement for Member");
	}
	
	private void saveConfiguration() {
		performAction.click(clickSave, "Click Save Button");
	}
	
	private void deleteAnnouncement() {
		performAction.click(delete, "Click Delete Button to Delete Announcement");
		Alert alerts = browser.getCurrentWebDriver().switchTo().alert();
        alerts.accept();
	}

	//Robot KeyWords
	
	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Create New Announcement for Member in Vista Role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : Respective Announcement Page Opened in Vista Role
	 * 
	 * PostCondition : Added New Announcement for Member in Vista Role
	 * 
	 * Java File Name : Announcements.java
	 * 
	 * | Announcement Type |
	 * | ex: Gloabl/Group |
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strAnnouncementType" })
	public void createNewAnnouncement(String strAnnouncementType) {
		try {
				if (strAnnouncementType.equalsIgnoreCase("Global")) {
				this.createNewAnnouncements();
					this.enterGlobalAnnouncement();
				this.saveConfiguration();
				scr.capturePageScreenshot();
				} else if (strAnnouncementType.equalsIgnoreCase("Group")) {
				this.createNewAnnouncements();
					this.enterGroupAnnouncement();
				this.saveConfiguration();
				scr.capturePageScreenshot();
			} else if (strAnnouncementType.equalsIgnoreCase("Delete")) {
                if (performAction.isElementPresent(delete)) {
                    this.deleteAnnouncement();
			}
			}
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Exception occured while Creating New Announcements"
					+ e.getMessage());
		}
	}
}
