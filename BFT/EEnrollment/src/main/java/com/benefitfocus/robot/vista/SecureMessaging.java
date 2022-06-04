package com.benefitfocus.robot.vista;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
public class SecureMessaging {

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
	
	// Locators in Secure Messaging Page
		By messagesIcon = By
				.cssSelector("img[title='Open Message Center (Alt+M)']");
		
		By getNoOfMessages = By.xpath("//tr/td[1]//input[@type='checkbox']");
		By deleteMessagesLink = By.linkText("Delete Selected Messages");
		By composeMessageLinkInMessageCenter = By.xpath("//a[contains(text(),'Compose Message')]");
		By emailSubjectTextBox = By.id("subject");
		By emailBodyTextBox = By.id("message");
		By sendMessageButton = By.xpath("//strong[contains(text(),'Send')]");
		
    // Variables in Secure Messaging Page
		String MailSubject = "";
		String ActualSubject="";
	
		// Get Count of Number of Messages
		private int getCountOfMessages() {
			int count = browser.getCurrentWebDriver().findElements(getNoOfMessages)
					.size();
			return count;
		}

		// Click Delete Messages Link
		private void clickDeleteMessagesButton() {
			performAction.click(deleteMessagesLink, "Click Delete Messages Link");
		}
		
		//Private method to click on Compose message
		private void clickComposeMessageLink(){
	        performAction.selectLatestWindow("Compose Message");
	        WebDriverWait wait = new WebDriverWait(browser.getCurrentWebDriver(), 50);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(composeMessageLinkInMessageCenter));
			performAction.click(composeMessageLinkInMessageCenter,"Click on Compose Message Link In Message Center");
		}
	    //Private method to compose and Send secure message
		private void composeAndSendSecureMessage(String strEmailSubject,String strEmailBody){
			performAction.clearEnter(emailSubjectTextBox, strEmailSubject, "Email Subject");
			performAction.clearEnter(emailBodyTextBox,strEmailBody,"Email Body");
			performAction.click(sendMessageButton,"Send Message Button ");
		}

		// Private method to verify that message is sent successfully
		private void verifyEmailSentSuccessfully(){
			performAction.verifyMessage("Your message has been sent");
	        performAction.verifyMessage("View Sent Messages");
			performAction.verifyMessage("Compose Another Message");
	        performAction.selectLatestWindow("Logout");
		}
		
     /**	
     * <pre> 
     * Author  : Dilip K
     * 
     * Role : Vista Role
     *  
     * Description   : 'Delete Secure Messages From Message Center In Vista Role' keyword used to delete Selected Messages after clicking on Open Message Center Icon in Vista Role.
     * 
     * PreCondition  : Switch to Secure Messaging Window and Messages should be available.
     * 
     * PostCondition : Able to Select and Delete Messages based on Subject.
     *  
     * <b>Parameters & Example </b> 
     * 
     * | Subject    |
	 * | HMVSubject | 
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.vista >> SecureMessaging.java </b>
	 * 
     * </pre> 
     **/	
		@RobotKeyword
		@ArgumentNames({ "strDeleteMessagesBySubject" })
		public void deleteSecureMessagesFromMessageCenterInVistaRole(
				String strDeleteMessagesBySubject) {
			try{
				ActualSubject=utils.getValue(strDeleteMessagesBySubject
						.toString());
				
				int count = this.getCountOfMessages();
				if (count != 0) {
					for (int rowNumber = 4; rowNumber <= count; rowNumber = rowNumber + 2) {
						MailSubject = browser.getCurrentWebDriver()
								.findElement(By.xpath("//tr[" + rowNumber + "]/td[3]"))
								.getText();
						if (MailSubject.equalsIgnoreCase(ActualSubject)) {
							logger.info("Mail Found with Subject : "+MailSubject);
							browser.getCurrentWebDriver()
							.findElement(
									By.xpath("//tr["
											+ rowNumber
											+ "]/td[3]/..//input[@type='checkbox']"))
											.click();
							this.clickDeleteMessagesButton();
							break;
						}
					}
					scr.capturePageScreenshot();
				} else {
					logger.info("No Messages to Select");
					scr.capturePageScreenshot();
				}
			}catch(Exception e){
				scr.capturePageScreenshot();
				logger.info("Exception occured deleting secure messages from Message Center"
						+ e.getMessage());
				throw new CustomException(
						"Exception occured deleting secure messages from Message Center"
								+ e.getMessage());
			}
		}
		
		/**
		 * <pre>
		 * Author  : Bhavan Mettu
		 *
		 * Role : Vista Role
		 *
		 * Description   : This keyword used to Compose and Send Secure Messages after clicking on Open Message Center Icon in Vista Role.
		 *
		 * PreCondition  : Switch to Secure Messaging Window.
		 *
		 * PostCondition : Able to compose and send messages.
		 *
		 * <b>Parameters & Example </b>
		 *
		 * | Subject    | Email body |
		 * | Test Email Subject | Test Email body |
		 *
		 * <b> Java File Name : com.benefitfocus.robot.vista >> SecureMessaging.java </b>
		 *
		 * </pre>
		 **/
		@RobotKeyword
		@ArgumentNames({ "strEmailSubject","strEmailBody"})
		public void composeAndSendSecureMessageInVista(
				String strEmailSubject,String strEmailBody) {
			try{
					this.clickComposeMessageLink();
					this.composeAndSendSecureMessage(strEmailSubject,strEmailBody);
					this.verifyEmailSentSuccessfully();
					scr.capturePageScreenshot();

			}catch(Exception e){
				scr.capturePageScreenshot();
				logger.info("Exception occured while composing and sending secure messages from Message Center in Vista role"
						+ e.getMessage());
				throw new CustomException(
						"Exception occured while composing and sending secure messages from Message Center in Vista role"
								+ e.getMessage());
			}
		}		
}
