package com.benefitfocus.robot.member;

import java.util.List;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

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
public class MyDocumentsPage {
	
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
	
	By addDocument = By.xpath("//a[@class='cp-userdoc-document-add ui-button-inline ui-button-inline-alt']//span[contains(text(),'Add Document')]");
	By chooseFile = By.id("filEditDocumentFileendUserDocCtrl_EndUserDocument_hostMainContent_LibraryMain_hostCenter_Main_form1");
	By docName = By.id("txtEditDocumentNameendUserDocCtrl_EndUserDocument_hostMainContent_LibraryMain_hostCenter_Main_form1");
	By docType = By.id("lstEditDocumentTypeendUserDocCtrl_EndUserDocument_hostMainContent_LibraryMain_hostCenter_Main_form1");
	By save = By.id("lnkEditDocumentSaveendUserDocCtrl_EndUserDocument_hostMainContent_LibraryMain_hostCenter_Main_form1");
	By delete = By.xpath("//a[@class='cp-userdoc-result-delete']");
	By yesButton = By.xpath("//div[@class='ui-dialog-buttonset']//span[contains(text(),'Yes')]");
	
	By documentCentreEnabled = By.id("hrInTouchDocumentCenterEnabled");
	By documentCentreAccessPage = By.id("documentCenterAccessPageEnabled");
	By dependentVerificationDocument = By.xpath("//option[@value='ALL']");
	By saveButton = By.linkText("Save");
	By uploadDocument = By.xpath("//div[@class='inner']//span[contains(text(),'Upload a Document')]");
    By nextButton = By.xpath("//button[contains(text(),'Next')]");

	//Private Methods
	
	private void clickAddDocumentLink() {
		performAction.click(addDocument, "Click Add Document Link");
	}
	
    private void clickUploadDocumentLink() {
        performAction.click(uploadDocument, "Click Upload Document Link");
    }

	private void clickChooseFileButton() {
		performAction.click(chooseFile, "Click Choose Button");
	}
	
	private void selectFile(String docName) {
		String fileName=null;
		String dir="F:"+File.separator+"QCOE"+File.separator+"Automation"+File.separator+"SampleUserGuides"+File.separator;
        fileName=dir+docName;
        logger.info(fileName);

		setClipboardData(fileName);
	}
	
	private void additionalDocumentInfo(String strDocName, String strDocType) {
		performAction.enter(docName, strDocName, "Enter Document Name");
		performAction.select(docType, strDocType, "Enter Document Type");
		performAction.click(save, "Click Save Button");
	}
	
	private void deleteDocument() {
		performAction.click(delete, "Click Delete Link");
		if (performAction.isElementPresent(yesButton)) {
			performAction.click(yesButton, "Click Yes Button");
		}
	}
	
	public static void setClipboardData(String string) {
	   StringSelection stringSelection = new StringSelection(string);
	   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}
	
    private void clickNextButton() {
        performAction.click(nextButton, "Click Next Button");
    }

	//Robot Keywords
		
	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Add Delete Document for Member in New Member Role
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition : My Documents Page Opened in New Member Role
	 * 
	 * PostCondition : Add/Delete Document for Member in New Member Role
	 * 
	 * Java File Name : MyDocumentsPage.java
	 * 
	 * | Action Type | Action | File Name | Document Name | Document Type |
	 * | ex: Upload/Delete | none/Dependent | Member_User_Guide.doc | Test | Other/Marriage/Birth |
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strActionType", "strAction", "strFileName", "strDocName", "strDocType" })
	public void document(String strActionType, String strAction, String strFileName, String strDocName, String strDocType) {
		try {						
			if (strAction.equalsIgnoreCase("none")) {
			if (strActionType.equalsIgnoreCase("Upload")) {
				this.clickAddDocumentLink();
				this.clickChooseFileButton();
                    this.selectFile(strFileName);
				Robot robot = new Robot();
		        robot.keyPress(KeyEvent.VK_CONTROL);
		        robot.keyPress(KeyEvent.VK_V);
		        robot.keyRelease(KeyEvent.VK_V);
		        robot.keyRelease(KeyEvent.VK_CONTROL);
		        Thread.sleep(5000); // Wait for File to be copied before pressing Enter
		        robot.keyPress(KeyEvent.VK_ENTER);
		        robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(5000); // Wait for File Upload after pressing Enter
				
                    this.additionalDocumentInfo(strDocName, strDocType);
				Thread.sleep(5000);// Wait for File to load
				scr.capturePageScreenshot();
                    performAction.verifyMessage(strDocName);
                    if (performAction.isElementPresent(nextButton)) {
                        this.clickNextButton();
                    }

			}else if (strActionType.equalsIgnoreCase("Delete")) {
				this.deleteDocument();
				Thread.sleep(5000);// Wait for File to delete
				scr.capturePageScreenshot();
                    performAction.verifyMessage("Add Document");
			}
            } else {
                this.clickUploadDocumentLink();
                this.clickChooseFileButton();
                this.selectFile(strFileName);
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                Thread.sleep(5000); // Wait for File to be copied before pressing Enter
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(5000); // Wait for File Upload after pressing Enter

                this.additionalDocumentInfo(strDocName, strDocType);
                Thread.sleep(5000);// Wait for File to load
                scr.capturePageScreenshot();
                performAction.verifyMessage(strDocName);
                if (performAction.isElementPresent(nextButton)) {
                    this.clickNextButton();
                }
            }
		} catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("Exception occured while adding or deleting document"
					+ e.getMessage());
		}
	}

}
