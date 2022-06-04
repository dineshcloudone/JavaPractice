package com.benefitfocus.robot.hradmin;

import java.util.Set;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.python.modules.thread.thread;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.ReadJsonTestData;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;
import org.openqa.selenium.JavascriptExecutor;

@RobotKeywords
public class Messages {
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

	// Locators in Messages Page
	public enum EmployeeDetails {
		SSN, HIREDATE 
	};
	By messages = By.linkText("Messages");
	By secureMessages = By.linkText("Secure messages");
	By sentMessages = By.linkText("Sent Messages");
	By deletedMessages = By.linkText("Deleted Messages");
	By composeMessage = By.linkText("Compose Message");
	By subject = By.id("subject");
	By message = By.id("message");
	By send = By.linkText("Send");
	By sentmessageButton = By.xpath("//a[text()='Sent Messages']");
	By selectmessageCheckBox = By.xpath("//table[@id='messageDisplay']/tbody/tr[4]/td/preceding::td/input[@type='checkbox']");
	By deleteselectedmessagebutton = By.xpath("//a[text()='Delete Selected Messages']");
	By scheduledMessage_CreateNewButton = By.xpath("//a[contains(text(),'Create New')]");
	By  createNewButton   = By.xpath("//a[contains(text(),'Create')]");
	By customScheduledMessageRadioButton = By.xpath("//label[contains(text(),'Custom Scheduled Message')]");
	By nextButton   =By.xpath("//strong[contains(text(),'Next')]");
	By sendMessageSelectBox = By.id("messageCenterFilterType");
	By ssnTextBox = By.id("employeeSsns");
	By folliwingEmailSelectBox = By.id("emailSendToEmailCode");
	//By fromEmailAddressSelectBox = By.xpath("//div[contains(@id,'r20')]/table/tbody/tr[5]/td[2]/div/button/preceding::select[@id='emailFromAddress']");
	By subjectTextBox = By.id("emailSubject-ENGLISH");
	By delevirydatetextbox = By.id("scheduledDeliveryDate");
	By savebutton   = By.xpath("//strong[text()='Save']");
	By manageEmployeeButton = By.xpath("//button[contains(text(),'Manage employee')]");
	By sendMessage = By.linkText("Send Message");
	By deliverySchedule = By.id("sendNow");
	By deliveryDate = By.id("scheduledDeliveryDate");
	By sendEmailButton = By.xpath("//a[@class='btn btn-success']");
	By toEmailTextBox = By.id("emailSendToEmailCode");
	By fromEmailDropDown = By.xpath("//button[@title='--Please Select--']");
	By fromEmailRadioButton = By.xpath("//input[@type='radio']");
	By emailSubjectTextBox = By.id("emailSubject-ENGLISH");
	
	By scheduledMessages = By.xpath("//a//span[contains(text(),'Scheduled messages')]");
		// Locator for Open Message Center
	By messagesIcon = By
	.cssSelector("img[title='Open Message Center (Alt+M)']");
	By checkForNewMessages = By.linkText("Check for New Messages");
	By ssnValue = By.xpath("//dt[contains(text(),'SSN:')]/following-sibling::dd[1]");
	By hiredateValue = By.xpath("//dt[contains(text(),'Hire Date:')]/following-sibling::dd[1]");
    //savebutton
	public void clickSavebutton(){
		performAction.jsclick(savebutton, "savebutton");
	}
	
	// Click on Messages Link
	private void clickMesagesLink() {
		performAction.click(messages, "Click Messages Link");
	}

	// Click on Secure Messages Link
	private void clickSecureMesagesLink() {
		performAction.click(secureMessages, "Click Secure Messages Link");
	}

	// Click on Compose Message Link
	private void clickComposeMesageLink() {
		performAction.click(composeMessage, "Click Compose Messages Link");
	}

	// Enter value in Subject text box of Compose Message page
	private void enterSubject(String strEnterSubject) {
		performAction.enter(subject, strEnterSubject, "Enter Subject");
	}

	// Enter value in Message text box of Compose Message page
	private void enterMessage(String strEnterMessage) {
		performAction.enter(message, strEnterMessage, "Enter Message");
	}

	// Click on Send Messages Button
	private void clickSendMesageLink() {
		performAction.click(send, "Click Send Link");
	}

	// Verify Successfully sent message
	private void verifySucessMessage(String verifySuccessMessage) {
		browser.getCurrentWebDriver().getPageSource()
		.contains(verifySuccessMessage);

	}

	// Click on Messages Icon
	private void clickMessagesIcon() {
		performAction.click(messagesIcon, "Click on Messages Icon");
	}

	// Click on Check for New Messages
	private void clickCheckForNewMesages() {
		performAction.click(checkForNewMessages,
		"Clik on Check for New Messages Link");
	}

	// Switch to Latest Secure Messaging Window
	private void switchToLatestSecureMessagingWindow() {
		performAction.selectLatestWindow("Secure Messaging");
	}

	//deleteSelectedMessages
	private void deleteSelectedMessages(){
		//click on SentMessages
		performAction.click(sentmessageButton, "Click Sent Message Button");
		//select the sent message
		performAction.click(selectmessageCheckBox, "Click selectmessageCheckBox");
		//click on deleteselectedmessagebutton
		performAction.click(deleteselectedmessagebutton, "Click deleteselectedmessagebutton");
	}
	private void navigateToSendMessages(){
		browser.getCurrentWebDriver().findElement(manageEmployeeButton).click();
		browser.getCurrentWebDriver().findElement(sendMessage).click();
	}
	//Navigate to Custom Scheduled Messages
	private void navigateToMessageTypePage(String messageType){
		
		//Click on Scheduled Messages button
		performAction.click(scheduledMessages, "Click Scheduled Messages");
		//Click on Create new button
		performAction.click(scheduledMessage_CreateNewButton, "Click Create New Button");
		//select custom scheduled message radio button
		if(messageType.equalsIgnoreCase("Custom Scheduled Message")){
			performAction.click(customScheduledMessageRadioButton, "Click Custom Scheduled Message Radio Button");
		}
		
		//Click on Next button
		performAction.click(nextButton, "Click Next Button");
		
	}
	private void updateEmailDetails() {
		try{
			if(performAction.isElementPresent(sendMessageSelectBox)){
				
				performAction.select(sendMessageSelectBox, "Employees by SSN", "Select Employee by SSN");
			}else {
				browser.getCurrentWebDriver().findElement(manageEmployeeButton).click();
				browser.getCurrentWebDriver().findElement(sendMessage).click();
			}
				performAction.select(toEmailTextBox, "Personal email", "send to the following email address");
				//Select radio button (email of HR Admin) from From Address drop down
				browser.getCurrentWebDriver().findElement(fromEmailDropDown).click();
				browser.getCurrentWebDriver().findElement(fromEmailRadioButton).click();
				
				performAction.clearEnter(emailSubjectTextBox, "test", "Subject");
				
				//Enter text in email body 
				browser.getCurrentWebDriver().switchTo().defaultContent();
				//Switching to Email Frame
				browser.getCurrentWebDriver().switchTo().frame(2);
				WebElement element = browser.getCurrentWebDriver().findElement(By.tagName("body"));
				// Clicking inside frame text body to get the focus
				browser.getCurrentWebDriver().findElement(By.xpath("html/body")).click();
				((JavascriptExecutor)browser.getCurrentWebDriver()).executeScript("arguments[0].innerHTML = '<p>Sample Email body text</p>'", element);
				scr.capturePageScreenshot();
				browser.getCurrentWebDriver().switchTo().defaultContent();
			
		}catch(Exception e){
			scr.capturePageScreenshot();
			logger.info("Failed to Send Email Message "+ e.getMessage());
			
			
		}
	}
	// Set delivery date
		private void setDeliveryDate(String strDate) {
			
			performAction.enter(deliveryDate, strDate, "Delivery date");
		}


	/**
	 * <pre> 
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Open Message Centre' keyword used to Open Message Centre Window
	 * 
	 * PreCondition  :  Messages Icon Should be Enabled When Login in to Vista Role.
	 * 
	 * PostCondition : Able to Click on Open Message Centre and switch to Secure Messaging Window
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | Subject - Subject of an Email   | or | Message- Actual Message to be send  |
	 * | RNDAutomation Test Mail Subject | or | Automation Test Email |
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> Messages.java </b>
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void openMessageCentre() {
		try{
			this.clickMessagesIcon();
			this.switchToLatestSecureMessagingWindow();
		}catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception While Open Message Center"
                    + e.getMessage());
            throw new CustomException(
                    "Exception While Open Message Center"
                            + e.getMessage());
        }
	}

	/**
	 * <pre> 
	 * Author  : Dilip K
	 * 
	 * Role : HR Role
	 *  
	 * Description   : 'Compose Message and Send' keyword used to compose message and send.
	 * 
	 * PreCondition  :  Login as HR Admin, Navigate to Messages tab.
	 * 
	 * PostCondition : Able to Compose and Send Messages.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | Subject - Subject of an Email   | or | Message- Actual Message to be send  |
	 * | RNDAutomation Test Mail Subject | or | Automation Test Email |
	 * 
	 * <b> Java File Path : com.benefitfocus.robot.hradmin >> Messages.java </b>
	 * 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({ "strSubject", "strMessage" })
	public void composeMessageAndSend(String strSubject, String strMessage) {
		try{
			this.clickMesagesLink();
			this.clickSecureMesagesLink();
			this.clickComposeMesageLink();
			String subWithRandomNumber =utils.getValue(strSubject.toString());
			this.enterSubject(subWithRandomNumber);
			browser.hMap.put("Subject", subWithRandomNumber);
			this.enterMessage(strMessage);
			this.clickSendMesageLink();
			this.verifySucessMessage("Your message has been sent");
			scr.capturePageScreenshot();
		}catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception While Sending message"
                    + e.getMessage());
            throw new CustomException(
                    "Exception While Sending message"
                            + e.getMessage());
        }
	}

	/**
	 * <pre> 
	 * Author  : Sekhar Tirumala
	 *  
	 * Description   : 'Delete Selected Message' keyword used to Delete the sent message.
	 * 
	 * Role : HR Role
	 * 
	 * PreCondition  :  Login as HR Admin, Navigate to Messages tab.
	 * 
	 * PostCondition :  Able to Deleted the Sent Messages.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 *  Java FileName : Messages.java
	 *  
	 * </pre> 
	 **/
	@RobotKeyword
	public void deleteSelectedMessageInHrRole() {
		try {
			this.deleteSelectedMessages();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Exception occured while deleting selected message: \n"
					+ e.getMessage());
		}
	}
	/**
	 * <pre> 
	 * 
	 * Author  : Bhavan Mettu
	 *  
	 * Description   : 'sendInstantMessageFromEmployeeOverview' keyword used to send message from employee overview screen. 
	 * 
	 * PreCondition  :  Login as HR Admin, Navigate to employee overview page.
	 * 
	 * PostCondition : Verifies the "Your message was sent." text
	 *
	 * </pre> 	 
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void sendInstantMessageFromEmployeeOverview() {
		try {
			this.navigateToSendMessages();
			this.updateEmailDetails();
			performAction.click(sendEmailButton, "Send Email Button");
			this.verifySucessMessage("Your message was sent.");
		
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Failed to Send Instant Email Message " + e.getMessage());
			throw new CustomException("Exception occured: \n"
					+ e.getMessage());
		}
	}

	/**
	 * <pre> 
	 * Author  : Bhavan Mettu
	 *  
	 * Description   : 'sendScheduledMessageFromEmployeeOverview' keyword used to send scheduled message from employee overview screen.
	 *                  Email message will be sent to the configured personal email address of the member exactly 24 hours from now. 
	 * 
	 * PreCondition  :  Login as HR Admin, Navigate to employee overview page.
	 * PostCondition : Verifies the "Your message was sent." text	
	 * 
	 * <b>Parameters & Example </b> 
	 * 
	 * | classification | outLastName | 
	 * | StrDate- to schedule message to that date |  | 
	 *  </pre>	 
	 **/
	@RobotKeyword
	@ArgumentNames({ "strDate" })
	public void sendScheduledMessageFromEmployeeOverview(String date) {
		
		
		try {
			this.navigateToSendMessages();
			this.updateEmailDetails();
			
			performAction.select(deliverySchedule, "Send this message later", "Send this message later selected");
			String deliveryInputDate;
			deliveryInputDate=utils.getDate(date);
			performAction.clearEnter(deliveryDate, deliveryInputDate, "Date Entered");
			performAction.click(sendEmailButton, "Send Email Button");
			this.verifySucessMessage("Your message was sent.");
			scr.capturePageScreenshot();
		
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Schedule Email Message was not Sent "+e.getMessage());
			throw new CustomException("Exception occured: \n"
					+ e.getMessage());
			}
	}
	/**
	 * <pre> 
	 * Author  : Rajeswari Nimmala
	 *  
	 * Description   : 'getEmployeeDetails' keyword used to get specified details of an employeeFrom HR role
	 * 
	 * Role : HR Role
	 * 
	 * PreCondition  :  Login as HR Admin, Navigate to Employees tab, Search Employee with lastname.
	 * 
	 * PostCondition :  Able to get  value and store in variable..
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | empDetails - is used to get details of the Employee for example ssn,hiredate etc| | value - is a variable name and it will be used to get and store the details of the Employee |
	 * 
	 * Java file Name :  Messages.java
	 *
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({"empDetails","value" })
	public void getEmployeeDetails(String empDetails, String value) {
		try {
			EmployeeDetails sed = EmployeeDetails.valueOf(empDetails.toUpperCase());

			switch (sed) {

			case SSN:
				String strSSN=browser.getCurrentWebDriver().findElement(ssnValue).getText();
				logger.info(strSSN);
				browser.hMap.put(value, strSSN);
				break;
			case HIREDATE:
				String strHiredate=browser.getCurrentWebDriver().findElement(hiredateValue).getText();
				browser.hMap.put(value, strHiredate);
				break;
			
			}
			
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Exception occured in reading details of member: \n"
					+ e.getMessage());
		}
	}
	/**
	 * <pre> 
	 * Author  : Rajeswari Nimmala
	 *  
	 * Description   : 'sendScheduledMessage' keyword used to send scheduled custom message from Messages screen.
	 *                  Email message will be sent to the configured personal email address of the member exactly 24 hours from now. 
	 * Role : HR Role
	 * 
	 * PreCondition  :  Login as HR Admin, Navigate to Message page.
	 * 
	 * PostCondition : 	Email will be received.
	 * 
	 * | messageType - to scheduled which type of  message to that date   || delivaryDate- to scheduled custom  message to that date   | |ssn- to pass ssn of the Employee in to textbox |
	 * 
	 * Java file Name :  Messages.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"messageType", "deliveryDate", "ssn" })
	public void sendScheduledMessages(String messageType, String deliveryDate, String ssn) {
		try {
			
			this.navigateToMessageTypePage(messageType);
			this.updateEmailDetails();
			// Enter SSN in Text Box
			performAction.enter(ssnTextBox, ssn, "Enter ssn Value");
			//Set deleivery date
			if (deliveryDate.startsWith("d:")) {
				deliveryDate = deliveryDate.substring(2);
				this.setDeliveryDate(utils.getDate(deliveryDate));
			} else {
				this.setDeliveryDate(deliveryDate);
			}
			
			this.clickSavebutton();
			
			scr.capturePageScreenshot();
		
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.info("Scheduled Custom Message was not Sent "+e.getMessage());
			throw new CustomException("Exception occured in scheduled custom message: \n"
					+ e.getMessage());
			}
	}

		
}



