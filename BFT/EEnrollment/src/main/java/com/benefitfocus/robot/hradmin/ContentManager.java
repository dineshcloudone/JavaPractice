package com.benefitfocus.robot.hradmin;

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
public class ContentManager {
	
	@Autowired
	protected ActionKeywords performAction;
	@Autowired
	protected Utilities utils;
	@Autowired
	protected Screenshot scr;
	@Autowired
	protected ManageBrowser browser; 
	@Autowired
	protected Logging logger;
	
	// Locators for Content Manager HR Note
	By hrNoteManager=By.linkText("HR Note Manager");
	By createNewHRNote=By.xpath("//a[contains(text(),'New HR Note')]");
	By BenefitName=By.xpath("//td[contains(text(),'Plan')]/.././/select");
	By hrNoteTitle=By.id("subject-ENGLISH"); // A note from your HR Administrator in Member Profile page
	By hrNoteMessage=By.id("content-ENGLISH"); // A message from HR Administrator in Member Profile page
	By nextButton=By.linkText("Next");
	By saveButton=By.linkText("Save");
	By deleteButton=By.linkText("Delete");
	By hrNotePanel=By.id("hr-note");
	By nextIconInHrNote=By.xpath("//a[contains(@href,'method=navigateNoteList')]");
	By hrNoteCount=By.xpath("//td[contains(@id,'cell')]");
	int rowSize=0;
	String hrNotesText="";
	String strTitle="";
	String hrnoteslink="";
	// Click on HR Note Manager Link
	private void clickHRNoteManager() {
		performAction.click(hrNoteManager, "Click on HR Note Manager");
	}
	// Click on Create New HR Note Button
	private void clickCreateNewHRNote() {
		performAction.click(createNewHRNote, "Click on Create New HR Note Button");
	}
	// Select Benefit or Category Name
	private void selectBenefitName(String strBenefitName) {
		performAction.select(BenefitName, strBenefitName, "Select Benefit or Category Name");
	}
	// Enter HR Note Title
	private void enterHrNoteTitle(String strHrNoteTitle) {
		performAction.clearEnter(hrNoteTitle, strHrNoteTitle, "Enter HR Note Title");
	}
	// Enter HR Note Message
	private void enterHrNoteMessage(String strHrNoteMessage) {
		performAction.clearEnter(hrNoteMessage, strHrNoteMessage, "Enter HR Note Message");
	}
	// Click on Next Button
	private void clickNextButton() {
		performAction.click(nextButton, "Click on Next Button");
	}
	// Click on Save Button
	private void clickSaveButton() {
		performAction.click(saveButton, "Click on Save Button");
	}
	// Mouse Over Actions Button
	private void mouseOverHrNote(String strHrNoteTitle){
		By hrNoteActions=By.xpath("//div[contains(text(),'"+strHrNoteTitle+"')]/../..//../../..//a");
		performAction.mouseOver(hrNoteActions, "Mouse Over Actions Button");
	}
	// Click on Delete Link
	private void clickDelete() {
		performAction.click(deleteButton, "Click on Delete Link");
	}
	// Select or Check Plan Name from Benefit Offerings Page
	private void selectPlan(String strPlanName){
		By planName=By.xpath("//td[contains(text(),'"+strPlanName+"')]/..//input");
		performAction.click(planName, "Select or Check Plan Name from Benefit Offerings Page");
	}
	
	/**
	 * <pre>
	 * Author  : Dilip K
	 *  
	 * Description   : 'Delete Hr Note For Employee' keyword or method is used to delete HR Note from Content Manager in HR Admin Home Page.
	 * 
	 * PreCondition  : Navigate to Content Manager page
	 * 
	 * PostCondition : Able to delete HR Note.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strTitle |
	 * | A note from Hr Note Member Page |
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> ContentManager.java </b> 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strTitle" })
	public void deleteHrNoteforEmployee(String strTitle) {
		try {
			if(strTitle.startsWith("HMV")){
				strTitle=browser.hMap.get(strTitle.substring(3));
			}
			performAction.verifyMessage("Content Manager");
			this.clickHRNoteManager();
			this.mouseOverHrNote(strTitle);
			this.clickDelete();			
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in Deleting HR Note" + e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * Author  : Dilip K
	 *  
	 * Description   : 'Delete All Hr Note For Employee' keyword or method is used to delete HR Note from Content Manager in HR Admin Home Page.
	 * 
	 * PreCondition  : Navigate to Content Manager page
	 * 
	 * PostCondition : Able to delete HR Note.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strTitle |
	 * | A note from Hr Note Member Page |
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> ContentManager.java </b> 
	 * </pre>
	 **/
	@RobotKeyword
	public void deleteAllHrNotesofEmployeeInHRRole() {
		try {
			performAction.verifyMessage("Content Manager");
			this.clickHRNoteManager();
			int hrNoteCount;
			//performAction.isElementPresent(nextIconInHrNote)
			
			if(browser.getCurrentWebDriver().findElements(By.xpath("//table[contains(@class,'table-striped')]//a[contains(@id,'innerLinkdynamicCommandsForNote')]")).size()>0){
				
					hrNoteCount=browser.getCurrentWebDriver().findElements(By.xpath("//table[contains(@class,'table-striped')]//a[contains(@id,'innerLinkdynamicCommandsForNote')]")).size();
				    logger.info("Initial Hr Note Count="+hrNoteCount);
					for(int i=0;i<hrNoteCount;){
					By hrNoteMouseHover=By.xpath("//table[contains(@class,'table-striped')]//a[contains(@id,'innerLinkdynamicCommandsForNote"+i+"')]");
					performAction.mouseOver(hrNoteMouseHover, "Mouse Hover on HR Note");
					performAction.waitUntilElementPresent(deleteButton);
					this.clickDelete();
					hrNoteCount=browser.getCurrentWebDriver().findElements(By.xpath("//table[contains(@class,'table-striped')]//a[contains(@id,'innerLinkdynamicCommandsForNote')]")).size();
					if(hrNoteCount==0){
						break;
					}
				    }
			}
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in Deleting HR Note" + e.getMessage());
		}
	}
	/**
	 * <pre>
	 * Author  : Dilip K
	 *  
	 * Description   : 'Add HR Note For Employee' keyword or method is used to add HR Note for Employee from Content Manager in HR Admin Home Page.HR Note Details will be given in JSON(say : Benefit Name,Plan Name,Title,Message ).
	 * 
	 * PreCondition  : Navigate to Content Manager page
	 * 
	 * PostCondition : Able to Add HR Note Benefits.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strBenefitName | strPlanName | strTitle | strMessage |
	 * | Dental | Dental Offer 2016 | A Note from HR Admin | A Message from HR Admin |
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> ContentManager.java </b> 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strHRNotes" })
	public void addHRNoteForEmployee(String strHRNotes) {
		try {
			if(strHRNotes.startsWith("td:")){
				strHRNotes=strHRNotes.substring(3);
			}
			 Object object = null;
	            JSONObject fields = ReadJsonTestData.getTestData(strHRNotes
	                    .toLowerCase());
	            
			performAction.verifyMessage("Content Manager");
			this.clickHRNoteManager();
			this.clickCreateNewHRNote();
			
			rowSize=browser.getCurrentWebDriver().findElements(hrNoteCount).size();
			if(rowSize>0){
				object = fields.get("hrnoteslink");
	            if (object != null) {
	            	hrnoteslink=object.toString();
	            }
				for(int i=0;i<=rowSize;i++){
					hrNotesText="//td[@id='cell"+i+"']";
					if(performAction.isElementPresent(By.xpath(hrNotesText))){
						if(browser.getCurrentWebDriver().findElement(By.xpath(hrNotesText)).getText().toLowerCase().equalsIgnoreCase(hrnoteslink)){
							hrNotesText=hrNotesText+"//following::a[text()='Select Page']";
							logger.info("Clicked on HR Note : "+hrnoteslink);
							performAction.click(By.xpath(hrNotesText), "Click on HR Notes Link");
							break;
						}
					}
				}
			}
			
			object = fields.get("benefit");
            if (object != null) {
            	this.selectBenefitName(object.toString());
            }
			this.clickNextButton();
			// Select One or More Plan from Benefit Offerings page
			object = fields.get("plan");
            if (object != null) {
            	this.selectPlan(object.toString());
            	this.clickNextButton();
            }
			// Select Benefit from Benefits Page
            object = fields.get("plan");
            if (object != null) {
            	this.selectPlan(object.toString());
            	this.clickNextButton();
            }
            
			object = fields.get("title");
			strTitle=object.toString();
            if (object != null) {
            	if(strTitle.startsWith("RND")){
        			strTitle=utils.getValue(strTitle);
        		}
            	browser.hMap.put("hrNoteTitle", strTitle);
    			this.enterHrNoteTitle(strTitle);
            }
			
            object = fields.get("message");
            if (object != null) {
            	this.enterHrNoteMessage(object.toString());
            }
			this.clickSaveButton();	
			
			performAction.waitUntilElementPresent(createNewHRNote);
			performAction.verifyMessage(strTitle);
			scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Exception in Adding HR Note" + e.getMessage());
			throw new CustomException(
					"Exception in Adding HR Note" + e.getMessage());
		}
	}
}
