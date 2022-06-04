package com.benefitfocus.robot.hradmin.contentmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
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
public class HrNoteManager {
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
	@Autowired
	protected Screenshot captureScreen;

	// Locators on the Page
	By hrNoteManager = By.linkText("HR Note Manager");

	// Locators in HR Note Manager Page
	By createNewHrNote = By.linkText("Create New HR Note");
	By textMessage = By.xpath("//td[@class='prhc regionHeader']/h1");
	String carrierNoteTable = "(//table[contains(@class,'standardDataTable')])[2]/tbody/tr";
	String notesActions = "(//a[contains(@id,'innerLinkdynamicCommandsForCarrierNote')])";
	String editCarrierNote = "(//div[contains(@id,'CommandsForCarrierNote')]//a[text()='Edit'])";
	By nextButton = By.xpath("//strong[text()='Next']");
	By template = By.id("templateType");
	By cancel = By.xpath("//strong[text()='Cancel']");
	By visibleEnabled = By.xpath("(//input[@name='isEnabled'])[1]");
	By visibleDisabled = By.xpath("(//input[@name='isEnabled'])[2]");
	By sortTextBox = By.id("sortOrder");
	By save = By.xpath("//strong[text()='Save']");

	String msg="";
	private void clickHrNoteManager() {
		performAction.click(hrNoteManager, "Hr Note Manager");
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Behara
	 *  
	 * Description : 'createNewHRNote' keyword used to Create New HR Note hr admin role
	 * 
	 * Role : HR Admin Role
	 * 
	 * PreCondition : user should be in Content Manager Page
	 *  
	 * PostCondition : user  will be redirected to Create a new HR Note page.
	 * 
	 * <b>Parameters & Example </b>
	 * 
     * Java file Name :  HrNoteManager.java
	 * </pre>
	 **/

	@RobotKeyword
	public void createNewHRNote() {
		try {
			this.clickHrNoteManager();
			performAction.click(createNewHrNote, "Create New HR Note");
			msg = browser.getCurrentWebDriver().findElement(textMessage)
					.getText().trim();
			logger.info("Message:::::" + msg);
			try {
				if (msg.equalsIgnoreCase("Create a new HR Note")) {
					logger.info(msg + " is verified in the page");
					scr.capturePageScreenshot();
				}
			} catch (Exception e) {
				logger.info(msg + "  is not find in the page");
				scr.capturePageScreenshot();
				throw new CustomException(
						"Exception in performing action in HR Note Manager page "
								+ e.getMessage());
			}
		} catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in performing action in HR Note Manager page "
							+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : 'verifyCarrierMemberNoteInHrRole' keyword used to verify the carrier member note hr admin role
	 * 
	 * Role: HR role
	 * 
	 * PreCondition : user should be in Content Manager Page
	 *  
	 * PostCondition : user  will be redirected to Create a new HR Note page.
	 * 
	 * <b>Parameters & Example </b> 
	 * 
	 * | strCarrierNote | 
	 * | getinsured |audax | 
	 * 
	 * JavaFileName  : HrNoteManager.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strCarrierNote" })
	public void verifyCarrierMemberNoteInHrRole(String strCarrierNote) {
		try {
			this.clickHrNoteManager();
			int notesCount = browser.getCurrentWebDriver()
					.findElements(By.xpath(carrierNoteTable)).size();
			logger.info("Notes Count is..." + notesCount);
			for (int i = 2; i <= notesCount; i++) {
				By actionButton = By.xpath(carrierNoteTable + "[" + i
						+ "]//td[6]//a[contains(text(),'Actions')]");
				performAction.mouseOver(actionButton,
						"Mouse over to Action button");
				Thread.sleep(1000);
				By editLink = By.xpath(editCarrierNote + "[" + (i - 1) + "]");
				performAction.jsclick(editLink, "Edit the Note ");
				if (performAction.isElementPresent(nextButton)) {
					performAction.click(nextButton, "Next Button");
				}
				Select sel = new Select(browser.getCurrentWebDriver()
						.findElement(template));
				scr.capturePageScreenshot();
				String selectedTemplate = sel.getFirstSelectedOption()
						.getText();
				logger.info(" selected template for current plugin is.."
						+ selectedTemplate);
				if (strCarrierNote.toLowerCase().equalsIgnoreCase(
						selectedTemplate.toLowerCase())) {
					logger.info(selectedTemplate
							+ "Carrier Member Note is visible in HR Role");
					break;
				}
				performAction.click(cancel, "Cancel Button");
				sel = null;
			}

		} catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception in verifying Carrier Member note in Hr Role"
							+ e.getMessage());
		}

	}


	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : 'changeCarrierMemberNoteOrderInHrRole' keyword used to change the sorting order of carrier member note hr admin role
	 * 
	 * Role: HR role
	 * 
	 * PreCondition : user should be in HR Note Manager Page
	 *  
	 * PostCondition : user  will be redirected to HR Notes page.
	 * 
	 *  *  * <b>Parameters & Example </b> 
	 * 
	 * | strCarrierNote | sorting order  |
	 * | getinsured |audax | 1 |
	 * 
	 * JavaFileName  : HrNoteManager.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strCarrierNote", "sortingOrder" })
	public void changeCarrierMemberNoteOrderInHrRole(String strCarrierNote,
			String sortingOrder) {
		try {
			if(performAction.isElementPresent(hrNoteManager)){
			this.clickHrNoteManager();
			}
			int notesCount = browser.getCurrentWebDriver()
					.findElements(By.xpath(carrierNoteTable)).size();
			logger.info("Notes Count is..." + notesCount);
			for (int i = 2; i <= notesCount; i++) {
				By carrierSetting = By.xpath(carrierNoteTable + "[" + i
						+ "]//td[4]//div");
				String carrierStatus = browser.getCurrentWebDriver()
						.findElement(carrierSetting).getText();
				logger.info("Carrier status is" + carrierStatus);
				By groupSetting = By.xpath(carrierNoteTable + "[" + i
						+ "]//td[5]//div");

				String groupStatus = browser.getCurrentWebDriver()
						.findElement(groupSetting).getText();
				logger.info("Group status is" + groupStatus);
				if ((carrierStatus.trim().equalsIgnoreCase("Enabled"))
						&& (groupStatus.trim().equalsIgnoreCase("Enabled"))) {

					By actionButton = By.xpath(carrierNoteTable + "[" + i
							+ "]//td[6]//a[contains(text(),'Actions')]");
					performAction.mouseOver(actionButton,
							"Mouse over to Action button");
					Thread.sleep(1000);
					By editLink = By.xpath(editCarrierNote + "[" + (i - 1)
							+ "]");
					performAction.jsclick(editLink, "Edit the Note ");
					if (performAction.isElementPresent(nextButton)) {
						performAction.click(nextButton, "Next Button");
					}
					Select sel = new Select(browser.getCurrentWebDriver()
							.findElement(template));
					scr.capturePageScreenshot();
					String selectedTemplate = sel.getFirstSelectedOption()
							.getText();
					logger.info(" selected template for current plugin is.."
									+ selectedTemplate);
					boolean visibleEnableStatus = browser.getCurrentWebDriver()
							.findElement(visibleEnabled).isSelected();
					if (strCarrierNote.toLowerCase().equalsIgnoreCase(
							selectedTemplate.toLowerCase())
							&& (visibleEnableStatus == true)) {
						logger.info(selectedTemplate
								+ "Carrier Member Note is visible in HR Role");
						String sortingStatus = browser.getCurrentWebDriver()
								.findElement(sortTextBox).getAttribute("value");
						logger.info("Changing Sorting order from..."
								+ sortingStatus + "  to  " + sortingOrder);
						performAction.clearEnter(sortTextBox, sortingOrder,
								"sorting order text box");
						performAction.click(save, "Save button");
						break;
					}
					performAction.click(cancel, "Cancel Button");
					sel = null;
				}
			}

		} catch (Exception e) {
			logger.info("Exception occured " + e.getMessage());
			scr.capturePageScreenshot();
			throw new CustomException(
					"Exception Occured while changing the sorting order of Carrier Member note in Hr Role"
							+ e.getMessage());
		}
	}
}
