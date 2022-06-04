package com.benefitfocus.robot.vista.global;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import java.io.File;
import java.util.List;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.datatransfer.StringSelection;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.ReadJsonTestData;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;

@RobotKeywords
public class PrivateLabels {
	
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
	
	By mercerMarketPlace = By.linkText("Mercer Marketplace - Diamond Foods");
	By brandingConfigurations = By.xpath("(//img[@alt='Edit Contents'])[6]");
	By memberBrandingConfigurations = By.id("innerLinktoggleRegionmemberBrandingConfiguration");
	By enableEmployeeAuthorization = By.id("member-SHOW_TERMS_CONDITIONS");
	By configurationNote = By.id("member-termsConditionsDescription-ENGLISH");
	
    String privateLabel = "//div[@id='innerInnerInnerInnerPageCore']/div";
    By createPrivateLabel = By.linkText("Create New Private Label");
    By description = By.id("description");
    By type = By.id("type");
    By save = By.xpath("//a[@class='buttonInnerLink']//strong[contains(text(),'Save')]");

    //edit system information to add user guides
    By editSystemInfo = By.xpath("//td[@class='regionArrangementFirstColumn']//div[@class='clumpRegion'][1]//td[@class='cc']//img");
    By bookletLanguage = By.xpath("//tr[@class='fieldListRow'][1]//td[@class='fieldListField']//button");
    By englishLanguage = By.id("ui-multiselect-privateLabelLanguages-option-46");
    By next = By.xpath("//td[@class='cc']//strong[contains(text(),'Next')]");
    By addMemberGuide = By.linkText("Add Member User Guide");
    By addHRGuide = By.linkText("Add HR User Guide");
    By title = By.id("newGuideName");
    By radioButtonDocument = By.xpath("//input[@value='DOCUMENT']");
    By location = By.xpath("//div[@id='document_box']//input");
    By nextToSaveUserGuide = By.linkText("Next");
    By saveEditConfig = By.id("innerLinksavePrivateLabelSystemInformationButton");
    By savePrivateLabelConfig = By.xpath("//strong[text()='Save']/parent::a");

	// Private Methods
	
	private void clickMercerMarketPlace() {
		performAction.click(mercerMarketPlace, "Click Mercer MarketPlace Diamond Foods Link");
	}
	
	private void clickBrandingConfiguration() {
		performAction.waitUntilElementPresent(brandingConfigurations);
		performAction.click(brandingConfigurations, "Click Edit Branding Configurations Link");
		performAction.waitUntilElementPresent(memberBrandingConfigurations);
		performAction.click(memberBrandingConfigurations, "Click ShowAll Member Branding Configurations Link");
	}
	
	private void enableEmployeeAuthorization() {
		performAction.waitUntilElementPresent(enableEmployeeAuthorization);
		performAction.select(enableEmployeeAuthorization, "Enabled", "Enabled");
		performAction.clearEnter(configurationNote, "Configurations Note", "Configurations Note");
		this.savePrivateLabelConfiguration();
	}

	private void savePrivateLabelConfiguration()
	{
		performAction.click(savePrivateLabelConfig, "Save Private Label Configuration");
	}

    private void clickCreatePrivateLabel() {
        performAction.click(createPrivateLabel, "Click Create New Private Label Button");
    }

    private void enterPrivateLabelDetails(String strLabelName, String strLabelType) {
        performAction.enter(description, strLabelName, "Enter Private Label Description");
        performAction.enter(type, strLabelType, "Enter Private Label Type(Key)");
        performAction.click(save, "Save Private Label");

    }

    private void privateLabel(String strLabelName) {
    	strLabelName = utils.getValue(strLabelName);
    	logger.info(strLabelName);
    	//boolean privateLabelFound = false;
        int privateLabellistCnt = browser.getCurrentWebDriver()
                .findElements(By.xpath(privateLabel + "//table" + "//tbody" + "//tr")).size();
        if (privateLabellistCnt > 0) {
            for (int i = 4; i < privateLabellistCnt; i++) {

                By privateLabels = By.xpath(privateLabel + "/table" + "/tbody" + "/tr[" + i
                        + "]/td[1]/a");
				//logger.info(browser.getCurrentWebDriver()
                //        .findElement(privateLabels).getText());
                if (browser.getCurrentWebDriver().findElement(privateLabels)
                        .getText().trim()
                        .equalsIgnoreCase(strLabelName)) {
                    By clickLabel = By.xpath(privateLabel + "/table" + "/tbody" + "/tr[" + i
                            + "]//td[1]//a");
                    performAction.click(clickLabel, "Click Private Label");
                    //privateLabelFound = true;
                    performAction.waitForPageLoad();
                    performAction.verifyMessage("Private Label Basics");
                    scr.capturePageScreenshot();
                    break;
                }
            }
        }else {
            throw new RuntimeException("No Private Labels available");
        }
    }

    private void editSystemInformation() {
        performAction.click(editSystemInfo, "Click Edit Button of System Information");
    }

    private void selectLanguage() {
        performAction.click(bookletLanguage, "Click Booklet Language");
        performAction.click(englishLanguage, "Select English Language");
        performAction.click(next, "Click Next Button");
    }

    private void clickAddMemberGuide() {
        performAction.click(addMemberGuide, "Click Add Member User Guide");
    }

    private void clickAddHRGuide() {
        performAction.click(addHRGuide, "Click HR User Guide");
    }

    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    private void selectFile(String file) {
        String fileName=null;
        String dir = File.separator+File.separator+"bfifile1.benefitfocus.com"+File.separator+"Departments"+File.separator+"QCOE"+File.separator+"Automation"+File.separator+"SampleUserGuides"+File.separator;
        fileName=dir+file;
        logger.info(fileName);

        setClipboardData(fileName);
    }

    private void uploadFile(String userGuide) {
        if (userGuide.equalsIgnoreCase("Member")) {
            this.clickAddMemberGuide();
            performAction.enter(title, "Sample Member User Guide", "Enter Sample Member User Guide");
            performAction.click(radioButtonDocument, "Click Document Radio Button");
            performAction.click(location, "Click Location to Select File");
            this.selectFile("Member_User_Guide.doc");
        }else if(userGuide.equalsIgnoreCase("HR")) {
            this.clickAddHRGuide();
            performAction.enter(title, "Sample HR User Guide", "Enter Sample HR User Guide");
            performAction.click(radioButtonDocument, "Click Document Radio Button");
            performAction.click(location, "Click Location to Select File");
            this.selectFile("HR_User_Guide.doc");
        }
    }

    private void saveUserGuideDocument() {
        performAction.click(nextToSaveUserGuide, "Click Next to Save User Guide");
    }

    private void saveEditConfigurations() {
        performAction.click(saveEditConfig, "Click Save Edit Configurations");
    }

	// Robot Methods
	
	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Change Branding Configurations for Member in Vista Role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : Private Label Page Opened in Vista Role
	 * 
	 * PostCondition : Updated Branding Configurations Settings for Member in Vista Role
	 * 
	 * Java File Name : PrivateLabels.java
	 * 
	 * | |
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void changeBrandingConfigurationsInVistaRole() {
		try {
			this.clickMercerMarketPlace();
			this.clickBrandingConfiguration();
			this.enableEmployeeAuthorization();
            performAction.verifyMessage("Branding Configuration");
            scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
            throw new CustomException("Exception occured while changing Branding Configurations Authorization"
                    + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Varun Reddy Proddutoori
     *
     * Description : Keyword used to click on Private label and open the carrier information
     *
     * Role : Vista Role
     *
     * PreCondition : User should be in Private Labels page(Global > PrivateLabels)
     *
     * PostCondition : User  will be redirected to Carrier information Summary page
     *
     * Java File Name: PrivateLabels.Java
     *
     * <b>Parameters :</b>
     * | strLabelName - Value of Carrier name Private Label |
     * | Ex: BCBSFI, Mercer MarketPlace etc |
     * </pre>
     **/

    @RobotKeyword
    @ArgumentNames({"strLabelName"})
    public void accessPrivateLabel(String strLabelName) {
        try {
            if (strLabelName.startsWith("td:")) {
                strLabelName = strLabelName.substring(3);
                Object object = null;
                JSONObject fields = ReadJsonTestData.getTestData(strLabelName.toLowerCase());
                object = fields.get("labelsearch");
                String strLabelNames = utils.getValue(object.toString());
                this.privateLabel(strLabelNames);
            }else {
                this.privateLabel(strLabelName);
            }
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception occured while selecting Private Label"
                    + e.getMessage());
            throw new CustomException(
                    "Exception occured while selecting Private Label"
                            + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Varun Reddy Proddutoori
     *
     * Description  : Create New Private Label in Vista Role
     *
     * Role : Vista Role
     *
     * PreCondition : Private Label Page Opened in Vista Role(Global > PrivateLabels)
     *
     * PostCondition : Created New Private Labels Successfully
     *
     * Java File Name : PrivateLabels.java
     *
     * <b> Parameters </b>
     * | privatelabeldetails | outprivateLabelName
     * |ex: td:privatelabeldetails |  output private label name
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({ "privatelabeldetails","outprivateLabelName" })
    public void createNewPrivateLabels(String privatelabeldetails,String outprivateLabelName ) {
        try {
            if (privatelabeldetails.startsWith("td:"))
                privatelabeldetails = privatelabeldetails.substring(3);
            Object object = null;
            JSONObject fields = ReadJsonTestData.getTestData(privatelabeldetails.toLowerCase());
            object = fields.get("labelname");
            String strLabelName = utils.getValue(object.toString());
            object = fields.get("labeltype");
            String strLabelType = utils.getValue(object.toString());
            this.clickCreatePrivateLabel();
            this.enterPrivateLabelDetails(strLabelName, strLabelType);
            browser.hMap.put("outprivateLabelName", strLabelName);
            performAction.waitForPageLoad();
            performAction.verifyMessage(strLabelName);
            scr.capturePageScreenshot();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            throw new CustomException("Exception Occured while Creating Private label"
                    + e.getMessage());
        }
    }

    /**
     * <pre>
     * Author  : Varun Reddy Proddutoori
     *
     * Description  : Edit System Information To Add HR Member User Guides for Private Labels in Vista Role
     *
     * Role : Vista Role
     *
     * PreCondition : Carrier Information Page from Private Label Opened in Vista Role(Global > PrivateLabels > carrier Page)
     *
     * PostCondition : User Guides Added Successfully
     *
     * Java File Name : PrivateLabels.java
     *
     * <b> Parameters </b>
     * | |
     *
     * </pre>
     **/
    @RobotKeyword
    @ArgumentNames({})
    public void editSystemInfoToAddHRMemberUserGuide() {
        try {
            this.editSystemInformation();
            this.selectLanguage();
            this.uploadFile("Member");

            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(5000); // Wait for File to be copied before pressing Enter
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(5000); // Wait for File Upload after pressing Enter
            this.saveUserGuideDocument();
            performAction.verifyMessage("Sample Member User Guide");

            this.uploadFile("HR");
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(5000); // Wait for File to be copied before pressing Enter
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(5000); // Wait for File Upload after pressing Enter
            this.saveUserGuideDocument();
            performAction.verifyMessage("Sample HR User Guide");

            this.saveEditConfigurations();
            scr.capturePageScreenshot();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            throw new CustomException("Exception Occured while Uploading User Guides Documents from Private labels"
					+ e.getMessage());
		}
	}
}
