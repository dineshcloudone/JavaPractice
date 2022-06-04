package com.benefitfocus.robot.member;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.json.simple.JSONObject;
import org.junit.Assert;
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
public class PrimaryCareProviderPage {
	
    @Autowired
    protected ManageBrowser browser;
    @Autowired
    protected Utilities utils;
    @Autowired
    protected ActionKeywords performAction;
    @Autowired
    protected Logging logger;
    @Autowired
    protected Screenshot scr;

    //Locators
    By pcpName = By.xpath("//input[@name='providerInformationEntries[0].providerName']");
    By pcpCode = By.xpath("//input[@name='providerInformationEntries[0].providerCode']");
    By sameProviderCheckBox = By.xpath("//input[@id='sameProvider-nativeHtmlElement']");
    By next = By.xpath("//*[contains(text(),'Next')]");
    By previousButton = By.xpath("//*[contains(text(),'Previous')]");
       
    //Private methods
    
    // Enter PrimaryCareProviderName into the text box
    private void setPCPName(String strPCPName) {
        performAction.clearEnter(pcpName, strPCPName,
                "Primary Care Provder Name");
    }

    // Enter PrimaryCareProviderCode into text box
    private void setPCPCode(String strPCPCode) {
        performAction.clearEnter(pcpCode, strPCPCode,
                "Primary care Provder Code");
    }
    // Enter primary care provider details for dependent
    private void setDependentDetails(String pcpdetails, String spouselastname) {
        Object object = null;
        JSONObject fields = ReadJsonTestData.getTestData(pcpdetails
                .toLowerCase());
        object = fields.get("deppcpcode");
        if (object != null) {
        	By PCPCodeValue = By.xpath("//span[contains(text(),'"+spouselastname+"')]/parent::td/following-sibling::td[@data-label='*PCP Code']//input");
            performAction.clearEnter(PCPCodeValue, object.toString(),
                    "Primary care Provder Code");
            browser.hMap.put("deppcpCode", object.toString());
        }
        object = fields.get("deppcpname");
        if (object != null) { 
            By PCPNameValue = By.xpath("//span[contains(text(),'"+spouselastname+"')]/parent::td/following-sibling::td[@data-label='PCP Name']//input");
            String value = utils.generateRandomNumber(object.toString().toString());
            performAction.clearEnter(PCPNameValue, value,
                    "Primary Care Provder Name");
            browser.hMap.put("deppcpname", value);
        }
    }
   
    // Select  Physician Visited Details
    private void selectPhysicianVisitedDetails(String physicianVisited, String spouselastname) {
    	By memberPhysicianVisitedSelectBox = By.xpath("//span[contains(text(),'"+spouselastname+"')]/ancestor::tr/following-sibling::tr[1]/td[2]//select");
    	performAction.select(memberPhysicianVisitedSelectBox, physicianVisited, "Select visited details");
        By dependentDateField = By.xpath("//span[contains(text(),'"+spouselastname+"')]/ancestor::tr/following-sibling::tr[1]/td[2]//select/following::div/input");
        Assert.assertTrue(performAction.isElementPresent(dependentDateField));
    }
	
	// Set date 
	private void setDate(String spouselastname) {
    	By dependentDateField = By.xpath("//span[contains(text(),'"+spouselastname+"')]/ancestor::tr/following-sibling::tr[1]/td[2]//select/following::div/input");
        DateFormat df=new SimpleDateFormat("MM/dd/yyyy");
    	Date date1=new Date();
    	String strDate=df.format(date1);
		performAction.enter(dependentDateField, strDate, " Last Visit date");
		browser.hMap.put("strdate", strDate);
	}
	// Check Use same provider for dependent
	private void selectUseSameProviderForDependent() {
        if(performAction.isElementPresent(sameProviderCheckBox, "Click same provider checkbox")) {
     	  performAction.click(sameProviderCheckBox, "Click same provider checkbox");
        }
	}
	// Verifying PCP Details
    private void verifyPCPDetails(String lastname, String subscriber) {
    	
        By PCPCodeValue = By.xpath("//span[contains(text(),'"+lastname+"')]/ancestor::tr/td[@data-label='PCP Code']/span");
        By PCPNameValue = By.xpath("//span[contains(text(),'"+lastname+"')]/ancestor::tr/td[@data-label='PCP Name']/span");
        if (subscriber.equalsIgnoreCase("Member") &&  browser.hMap.get("pcpcode") != null) {
        	scr.capturePageScreenshot();
            Assert.assertEquals(browser.hMap.get("pcpcode"), browser.getCurrentWebDriver().findElement(PCPCodeValue).getText());                    
        }
        else if (subscriber.equalsIgnoreCase("Dependent") &&  browser.hMap.get("deppcpcode") != null) {
        	scr.capturePageScreenshot();
            Assert.assertEquals(browser.hMap.get("deppcpcode"), browser.getCurrentWebDriver().findElement(PCPCodeValue).getText());
        }
        
        if (subscriber.equalsIgnoreCase("Member") && browser.hMap.get("pcpname") != null) {
        	scr.capturePageScreenshot();
            Assert.assertEquals(browser.hMap.get("pcpname"), browser.getCurrentWebDriver().findElement(PCPNameValue).getText());
        }
        else if (subscriber.equalsIgnoreCase("Dependent") && browser.hMap.get("deppcpname") != null) {
            logger.info(browser.hMap.get("deppcpname"));
            logger.info(browser.getCurrentWebDriver().findElement(PCPNameValue).getText());
            scr.capturePageScreenshot();
            Assert.assertEquals(browser.hMap.get("deppcpname"), browser.getCurrentWebDriver().findElement(PCPNameValue).getText());
        }
    }
   
    // Verifying  Physician Visited Select box exist or not exist
    private void verifyPhysicianVisitedSelectBox(String physicianVisitedSelectBox, String lastname) {
    	
        By memberPhysicianVisitedSelectBox = By.xpath("//span[contains(text(),'"+lastname+"')]/ancestor::tr/following-sibling::tr[1]/td[2]//select");
        if(physicianVisitedSelectBox.equalsIgnoreCase("Exist")){
            performAction.verifyMessage("Have you previously visited this physician?");
            scr.capturePageScreenshot();
            Assert.assertTrue("Member Physician Visited SelectBox Exist", performAction.isElementPresent(memberPhysicianVisitedSelectBox));
        } else {
        	logger.info("Previously visited  physican details are not captured according to vista configurations ");
        	scr.capturePageScreenshot();
        	Assert.assertFalse("Member Physician Visited SelectBox Not Exist", performAction.isElementPresent(memberPhysicianVisitedSelectBox));
        }
    }
    //verifying date filed exist
    private void verifyDateFieldExist() {
    	String lastname=browser.hMap.get("lastname");
        By memberDateField = By.xpath("//span[contains(text(),'"+lastname+"')]/ancestor::tr/following-sibling::tr[1]/td[2]//select/following::div/input");
        scr.capturePageScreenshot();
        Assert.assertTrue("Date Field Exist", performAction.isElementPresent(memberDateField));
    }
    // Verify Date field not exist
    private void verifyDateFieldNotExist() {
    	String lastname=browser.hMap.get("lastname");
        By memberDateField = By.xpath("//span[contains(text(),'"+lastname+"')]/ancestor::tr/following-sibling::tr[1]/td[2]//select/following::div/input");
        scr.capturePageScreenshot();
        Assert.assertFalse("Date Field Not Exist", performAction.isDisplayed(memberDateField, "Date Field"));
    }
    // Click Next Button
    private void clickNext() {
        performAction.click(next, "Click nextbutton");
    }

    private void verifyPCPDetailsAreNotEntered(String LastName){
    	try {
			if (LastName.startsWith("HMV")){
				LastName = utils.getValue(LastName);
			}
    	 By PCPCodeValue = By.xpath("//span[contains(text(),'"+LastName+"')]/ancestor::tr/td[@data-label='*PCP Code']/span");
         By PCPNameValue = By.xpath("//span[contains(text(),'"+LastName+"')]/ancestor::tr/td[@data-label='PCP Name']/span");
         scr.capturePageScreenshot();
         Assert.assertEquals("", browser.getCurrentWebDriver().findElement(PCPCodeValue).getText());
         Assert.assertEquals("", browser.getCurrentWebDriver().findElement(PCPNameValue).getText());
         scr.capturePageScreenshot();
    }catch (Exception e) {
		logger.info("Exception occured " + e.getMessage());
		scr.capturePageScreenshot();
		throw new CustomException("Exception Occured while reading Employee or Dependent Lastname "
				+ e.getMessage());
		}
    }
    
    private void clickPrevious(){
    	performAction.click(previousButton, "Click Previous Button");
    }

    /**
	 * <pre>
	 * Author  : Rajeswari Nimmala
	 * 
	 * Description : keyword is used to enter and verify PCP details 
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition :  Medical Primary care provider page should be open in Member role
	 * 
	 * PostCondition : PCP Details  will be displayed
	 * 
	 * <b>Parameters & Example </b>
	 * 
	 * |pcpdetails - JSON tag ||strUseSameProviderCheckBox -  Value passing through ride yes or no to check the check box ||physicianVisitedSelectBox - Value passing through ride Exist or Not Exist to Verify|
	 * 
	 * </pre>
	 * 
	 * Java file Name : PrimaryCareProviderPage.java
	 **/
    @RobotKeyword
    @ArgumentNames({"pcpdetails","strUseSameProviderCheckBox", "physicianVisitedSelectBox"})
    public void enterAndVerifyPCPDetails(String pcpdetails, String strUseSameProviderCheckBox, String physicianVisitedSelectBox) {
        try {
            String spouselastname = browser.hMap.get("spouselastname");
            String lastname = browser.hMap.get("lastname");
            if (pcpdetails.startsWith("td:"))
            	pcpdetails = pcpdetails.substring(3);

            Object object = null;
            JSONObject fields = ReadJsonTestData.getTestData(pcpdetails
                    .toLowerCase());
            logger.info("fields = " + fields.toJSONString());

            object = fields.get("pcpcode");
            if (object != null) {
                String value = utils.generateRandomNumber(object.toString().toString());
                this.setPCPCode(value);
                browser.hMap.put("pcpcode", value);
            }
            object = fields.get("pcpname");
            if (object != null) {
            	 String value = utils.generateRandomNumber(object.toString().toString());
            	 this.setPCPName(value);
            	 browser.hMap.put("pcpname", value);
            }
            if(strUseSameProviderCheckBox.equalsIgnoreCase("yes")){
            	this.selectUseSameProviderForDependent();
            } else if(spouselastname!="none"){
                this.setDependentDetails(pcpdetails, spouselastname);
            }
            this.clickNext();
            this.verifyPhysicianVisitedSelectBox(physicianVisitedSelectBox, lastname);
            this.verifyPCPDetails(lastname,"Member");
            if(spouselastname!="none"){
                this.verifyPCPDetails(spouselastname,"Dependent");
                this.verifyPhysicianVisitedSelectBox(physicianVisitedSelectBox, spouselastname);
            }
            scr.capturePageScreenshot();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception occured while enter and verify PCP information while enrolling benefits"
                            + e.getMessage());
            throw new CustomException(
                    "Exception occured while enter and verify PCP information while enrolling benefits"
                            + e.getMessage());
        }
    }
	
	/**
	 * <pre>
	 * Author  : Rajeswari Nimmala
	 * 
	 * Description : Keyword is used to enter member and dependent last physician visited details
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition : Medical Primary care provider page with physician visited details should be open in Member role
	 * 
	 * PostCondition : PCP Details  will be displayed
	 * 
	 * <b>Parameters & Example </b>
	 * 
	 * |physicianVisited - last physician visited details should be given ex: Yes, No |
	 * 
	 * </pre>
	 * 
	 * Java file Name : PrimaryCareProviderPage.java
	 **/
    @RobotKeyword
    @ArgumentNames({"physicianVisited"})
    public void enterPhysicianVisitedAndDateDetails(String physicianVisited) {
    	try{
    		String lastname=browser.hMap.get("lastname");
    		if(physicianVisited.equalsIgnoreCase("yes")){
        		this.selectPhysicianVisitedDetails(physicianVisited, lastname);
        		this.verifyDateFieldExist();
        		
        		this.setDate(lastname);
        		//this.setDateForMember();
        		String spouselastname = browser.hMap.get("spouselastname");
        		By dependentdetails = By.xpath("//span[contains(text(),'"+spouselastname+"')]");
        		
        		if(performAction.isElementPresent(dependentdetails)) {
        			logger.info("dependent exist");
        			this.selectPhysicianVisitedDetails(physicianVisited, spouselastname);
        			this.setDate(spouselastname);
         		}
        		this.clickNext();
        		performAction.verifyMessage("Provider Summary");
    		}else{
    			this.verifyDateFieldNotExist();
    			this.clickNext();
    			performAction.verifyMessage("Provider Summary");
    		}	
    		scr.capturePageScreenshot();
    	} catch (Exception e) {
    		e.printStackTrace();
            scr.capturePageScreenshot();
            throw new CustomException(
                    "Exception occured while Selecting Physician visited date on PCP information while enrolling benefits"
                            + e.getMessage());
    	}
    }
    
    
    /**
	 * <pre>
	 * Author  : Bhavan Mettu
	 * 
	 * Description : keyword is used to verify PCP details are blank 
	 * 
	 * Role : Member Role
	 * 
	 * PreCondition :  Medical Primary care provider page should be open in Member role
	 * 
	 * PostCondition : Verifies that PCP Details are empty
	 * 
	 * <b>Parameters & Example </b>
	 * 
	 * |strLastName - Hash Map Value of employee last name/dependent last name |.
	 * 
	 * |HMVlastname/HMVdeplastname
	 * 
	 * </pre>
	 * 
	 * Java file Name : com.benefitfocus.robot.member>>PrimaryCareProviderPage.java
	 **/
    @RobotKeyword
    @ArgumentNames({"strLastName"})
    public void verifyPCPDetailsAreBlank(String strLastName) {
        try {
        	this.verifyPCPDetailsAreNotEntered(strLastName);
        	this.clickPrevious();
            scr.capturePageScreenshot();
        } catch (Exception e) {
            scr.capturePageScreenshot();
            logger.info("Exception occured while verifying PCP blank information"
                            + e.getMessage());
            throw new CustomException(
                    "Exception occured while verifying PCP blank information"
                            + e.getMessage());
        }
    }

}