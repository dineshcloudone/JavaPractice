package com.benefitfocus.robot.vista.carriers;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
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
import com.benefitfocus.robot.utils.ReadJsonTestData;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;
import junit.framework.Assert;

@RobotKeywords
public class Plugins {
	@Autowired
	protected ActionKeywords performAction;

	@Autowired
	protected Utilities utils;

	@Autowired
	protected ManageBrowser browser;
	
	@Autowired
	protected Screenshot scr;
	
	@Autowired
	protected Logging logger;

	// Locators
	String pluginTable = "//table[contains(@class,'table-striped table-bordered')]/tbody/tr";
	By pluginType = By.id("pluginType");
	By carrierEnable = By.id("enabledInd");
	By getInsuredPageTitle = By.id("GET_INSURED_config_title");
	By getInsuredPageMessage = By.id("GET_INSURED_config_note");
	By getInsuredImageUrl = By.id("GET_INSURED_config_imageUrl");
	By getInsuredBulletMessage1 = By.id("GET_INSURED_config_bullet1Content");
	By getInsuredBulletMessage2 = By.id("GET_INSURED_config_bullet2Content");
	By getInsuredBulletMessage3 = By.id("GET_INSURED_config_bullet3Content");
	By save = By.xpath("//strong[text()='Save']");
	By createNew = By.cssSelector("a[href*='method=addEditPlugin']");
	By audaxPageTitle = By.id("AUDAX_RALLY_config_title");
	By audaxPageMessage	= By.id("AUDAX_RALLY_config_note");
	By audaxImageUrl = By.id("AUDAX_RALLY_config_imageUrl");
	By audaxBulletTitle	=	By.id("AUDAX_RALLY_config_bulletListTitle");
	By audaxBullet1 = By.id("AUDAX_RALLY_config_bullet1Title");
	By audaxBulletMessage1 = By.id("AUDAX_RALLY_config_bullet1Content");
	By audaxBullet2 = By.id("AUDAX_RALLY_config_bullet2Title");
	By audaxBulletMessage2 = By.id("AUDAX_RALLY_config_bullet2Content");
	By audaxBullet3 = By.id("AUDAX_RALLY_config_bullet3Title");
	By audaxBulletMessage3 = By.id("AUDAX_RALLY_config_bullet3Content");
	By tableData = By.xpath("//table[contains(@class,'table-striped table-bordered')]/tbody/tr[4]/td[2]");
	By enablePlugin	= By.id("enabledInd");
	By getInsuredTitlelabel = By.xpath("//label[@for='GET_INSURED_config_title']");
	//By memberOptions = By.xpath("//table[@id='memberResults']/tbody/tr[4]/td[1]/div/table/tbody/tr[2]/td[2]/a,'Member Options')]");
	By codeBabyPlayer=By.id("codebaby_player_480");
	By codeBabyPlayerLink = By.xpath("//input[@class='codebaby-park-image']");
	By loginInfo = By.linkText("Login Information");
	By uiStyle = By.linkText("Switch UI Style");
	
	
	//Private Methods
	private void createNewPlugin() {
		performAction.click(createNew, "Create New Plugin");
	}
	
	//private method to enter data into the fields
	private void enterData(By locator, String message) {
		performAction.clearEnter(locator, message, "Enterong data into the fields");
	}
	//Private method to save plugin
	private void savePlugin() {
		performAction.click(save, "Save Button");
	}
	//private method to create select plugin
	private void selectPlugin(String pluginName) {
		performAction.select(pluginType, pluginName, "Plugin Name");
	}
	//private method to select enable plugin
	private void selectenablePlugin(){
		performAction.select(enablePlugin, "Enabled", "Enable select Box");
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : deletePluginInCarrier keyword or method is used to delete a plugin in carrier role
	 * 
	 * Role : Vista Role
	 * 
	 * PreCondition : plugins page in carrier level
	 * 
	 * PostCondition : plugin should be deleted successfully.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strPluginName |  
	 * |  Get Insured - plugin name to delete which appears in table | 
	 * 
	 * JavaFileName : Plugins.java
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({"strPluginName"})
	public void deletePluginInCarrier(String strPluginName) {
		try {
			
		
		int tableRows = browser.getCurrentWebDriver().findElements(By.xpath(pluginTable)).size();
		
		if(tableRows > 3 && performAction.isElementPresent(tableData)){
			for (int i=4 ; i <=tableRows; i++){
				String pluginVerify = browser.getCurrentWebDriver().findElement(By.xpath(pluginTable+"["+i+"]/td[2]//div")).getText().trim();
				if(pluginVerify.equalsIgnoreCase(strPluginName.trim())){
				By del = By.xpath(pluginTable+"["+i+"]//a[text()='Delete']");
				performAction.click(del, "Delete Button");
				}
			}
		}
		scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occured " + e.getMessage());
			throw new CustomException("Exception in performing Delete plugin  "
					+ e.getMessage());
		}
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : createGetInsuredPlugin keyword or method is used to create new Get Insured plugin in carrier role. if already exists in carrier role 
	 * it will delete that plugin and creates new one.
	 * 
	 * Role: Vista Role
	 * 
	 * PreCondition : plugins page in carrier level
	 * 
	 * PostCondition : plugin should be deleted successfully.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strPluginTagName |  
	 * |  getinsured - json tag name to get plugin data | 
	 * 
	 * JavaFileName : Plugins.java
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({"strPluginTagName"})
	public void createGetInsuredPlugin(String strPluginTagName) {
		try{
		int tableRows = browser.getCurrentWebDriver().findElements(By.xpath(pluginTable)).size();
		
		if(tableRows > 3 && performAction.isElementPresent(tableData)){
			
			for (int i=4 ; i <=tableRows; i++){
				logger.info("No.of plugins are..."+tableRows);
				String pluginVerify = browser.getCurrentWebDriver().findElement(By.xpath(pluginTable+"["+i+"]/td[2]//div")).getText().trim();
				logger.info("pluggin name is..."+pluginVerify);
				if(pluginVerify.equalsIgnoreCase("Get Insured")){
					System.out.println("Plugin is already created");
				By del = By.xpath(pluginTable+"["+i+"]//a[text()='Delete']");
				performAction.click(del, "Delete Button");
					}
				}
			}
			this.createNewPlugin();
			this.selectPlugin("Get Insured");
			Object object = null;
			JSONObject pluginData = ReadJsonTestData.getTestData(strPluginTagName
					.toLowerCase());
			object = pluginData.get("pagetitle");
			logger.info("first data is..."+object.toString());
			if (object != null) {
				this.enterData(getInsuredPageTitle,(object.toString()));
			}
			object = pluginData.get("pagemessage");
			if (object != null) {
				this.enterData(getInsuredPageMessage,(object.toString()));
			}
			object = pluginData.get("imageurl");
			if (object != null) {
				this.enterData(getInsuredImageUrl,(object.toString()));
			}
			object = pluginData.get("message1");
			if (object != null) {
				this.enterData(getInsuredBulletMessage1,(object.toString()));
			}
			object = pluginData.get("message2");
			if (object != null) {
				this.enterData(getInsuredBulletMessage2,(object.toString()));
			}
			object = pluginData.get("message3");
			if (object != null) {
				this.enterData(getInsuredBulletMessage3,(object.toString()));
			}
			this.savePlugin();
			scr.capturePageScreenshot();
				
		}
		catch(Exception e){
			scr.capturePageScreenshot();
			logger.warn("Exception occured " + e.getMessage());
			throw new CustomException("Exception in while creating Get Insured plugin  "
					+ e.getMessage());
		}
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : createAudaxRallyPlugin keyword or method is used to create new Audax Rally plugin in carrier role. if already exists in carrier role 
	 * it will delete that plugin and creates new one.
	 * 
	 * Role: Vista Role
	 * 
	 * PreCondition : plugins page in carrier level
	 * 
	 * PostCondition : plugin should be deleted successfully.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strPluginTagName |  
	 * |  audaxrally - json tag name to get plugin data | 
	 * 
	 * JavaFileName : Plugins.java
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({"strPluginTagName"})
	public void createAudaxRallyPlugin(String strPluginTagName ) {

		try{
		int tableRows = browser.getCurrentWebDriver().findElements(By.xpath(pluginTable)).size();
		
		if(tableRows > 3&& performAction.isElementPresent(tableData)){
			for (int i=4 ; i <=tableRows; i++){
				String pluginVerify = browser.getCurrentWebDriver().findElement(By.xpath(pluginTable+"["+i+"]/td[2]//div")).getText().trim();
				if(pluginVerify.equalsIgnoreCase("Audax Rally Plugin")){
					logger.info("Plugin is already created");
				By del = By.xpath(pluginTable+"["+i+"]//a[text()='Delete']");
				performAction.click(del, "Delete Button");
					}
				}
			}
			this.createNewPlugin();
			this.selectPlugin("Audax Rally Plugin");
			Object object = null;
			JSONObject pluginData = ReadJsonTestData.getTestData(strPluginTagName
					.toLowerCase());
			object = pluginData.get("pagetitle");
			if (object != null) {
				this.enterData(audaxPageTitle,(object.toString()));
			}
			object = pluginData.get("pagemessage");
			if (object != null) {
				this.enterData(audaxPageMessage,(object.toString()));
			}
			object = pluginData.get("imageurl");
			if (object != null) {
				this.enterData(audaxImageUrl,(object.toString()));
			}
			
			object = pluginData.get("listtitle");
			if (object != null) {
				this.enterData(audaxBulletTitle,(object.toString()));
			}
			
			object = pluginData.get("bullet1");
			if (object != null) {
				this.enterData(audaxBullet1,(object.toString()));
			}
			
			object = pluginData.get("message1");
			if (object != null) {
				this.enterData(audaxBulletMessage1,(object.toString()));
			}
			
			object = pluginData.get("bullet2");
			if (object != null) {
				this.enterData(audaxBullet2,(object.toString()));
			}
			
			object = pluginData.get("message2");
			if (object != null) {
				this.enterData(audaxBulletMessage2,(object.toString()));
			}
			
			object = pluginData.get("bullet3");
			if (object != null) {
				this.enterData(audaxBullet3,(object.toString()));
			}
			object = pluginData.get("message3");
			if (object != null) {
				this.enterData(audaxBulletMessage3,(object.toString()));
			}
			this.savePlugin();
			scr.capturePageScreenshot();
		}
		catch(Exception e){
			scr.capturePageScreenshot();
			logger.warn("Exception occured " + e.getMessage());
			throw new CustomException("Exception in while creating Get Insured plugin  "
					+ e.getMessage());
		}
	
	}
	/**
	 * <pre> 
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : verifyAndEnablePluginInGroup keyword or method is used to verify the plugin in the group level 
	 * 
	 * Role: Vista Role
	 * 
	 * PreCondition : plugins page in carrier level
	 * 
	 * PostCondition : plugin should be deleted successfully.
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strPluginName |  
	 * |  Get Insured - Plugin name which appears in the table  | 
	 * 
	 * JavaFileName : Plugins.java
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({"strPluginName"})
	public void verifyAndEnablePluginInVista(String strPluginName) {
		try {
			
		
		int tableRows = browser.getCurrentWebDriver().findElements(By.xpath(pluginTable)).size();
		
		if(tableRows > 3 && performAction.isElementPresent(tableData)){
			for (int i=4 ; i <=tableRows; i++){
				String pluginVerify = browser.getCurrentWebDriver().findElement(By.xpath(pluginTable+"["+i+"]/td[2]//div")).getText().trim();
				if(pluginVerify.equalsIgnoreCase(strPluginName.trim())){
				By edit = By.xpath(pluginTable+"["+i+"]//a[contains(@href,'method=addEditPlugin&')]");
				performAction.click(edit, "Edit Button");
				this.selectenablePlugin();
				this.savePlugin();
				break;
				}
			}
		}
		scr.capturePageScreenshot();
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occured " + e.getMessage());
			throw new CustomException("Exception in performing Delete plugin  "
					+ e.getMessage());
		}
	}
	
	@RobotKeyword
	@ArgumentNames({"strPluginName"})
	public void verifyPluginInCarrier(String strPluginName) {
		try {
			
		
		int tableRows = browser.getCurrentWebDriver().findElements(By.xpath(pluginTable)).size();
		
		if(tableRows > 3 && performAction.isElementPresent(tableData)){
			for (int i=4 ; i <=tableRows; i++){
				String pluginVerify = browser.getCurrentWebDriver().findElement(By.xpath(pluginTable+"["+i+"]/td[2]//div")).getText().trim();
				if(pluginVerify.equalsIgnoreCase(strPluginName.trim())){
				By edit = By.xpath(pluginTable+"["+i+"]//a[contains(@href,'method=addEditPlugin&')]");
				performAction.click(edit, "Edit Button");
				}
			}
			scr.capturePageScreenshot();
		}
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occured " + e.getMessage());
			throw new CustomException("Exception in performing Delete plugin  "
					+ e.getMessage());
		}
	}
	
	/**
	 * <pre> 
	 * Author  : Srilatha A
	 * 
	 * Role    : HR Role
	 *  
	 * Description : SelectAndEnableCodeBabyPlugin keyword enables the Code baby hannah plugin
	 * 
	 * PreCondition : Login to Vista, Navigate to  -> Basics -> plugin
	 * 
	 * PostCondition : Code baby plugin should be enabled
	 * 
	 * Java file Name : Plugins.java
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * | strPluginName |  
	 * | strPluginName - Plugin name to get Enabled | 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({"strPluginName"})
	public void selectAndEnableCodeBabyPlugin(String strPluginName) {
		try {
			int tableRows = browser.getCurrentWebDriver().findElements(By.xpath(pluginTable)).size();
			
			if(tableRows > 3 && performAction.isElementPresent(tableData)){
				for (int i=4 ; i <tableRows; i++){
					
					String pluginVerify = browser.getCurrentWebDriver().findElement(By.xpath(pluginTable+"["+i+"]/td[2]//div")).getText().trim();
					if(pluginVerify.equalsIgnoreCase(strPluginName.trim())){
					By deleteButton = By.xpath(pluginTable+"["+i+"]//a[text()='Delete']");
					performAction.click(deleteButton, "Delete Button");
					break;
					}
				}
			}
			this.createNewPlugin();
			this.selectPlugin(strPluginName);
			this.selectenablePlugin();
			scr.capturePageScreenshot();
			this.savePlugin();
			
		} catch (Exception e) {
				scr.capturePageScreenshot();
				logger.warn("Exception occured in Enabling the Code Baby plugin " + e.getMessage());
				throw new CustomException("Exception in Enabling the Code Baby plugin  "
						+ e.getMessage());
			}
	}
	
	/**
	 * <pre> 
	 * Author  : Srilatha A
	 * 
	 * Role    : HR Role
	 *  
	 * Description : CheckCodeBabyImage keyword checks whether codebaby pluin is running properly or not
	 * 
	 * PreCondition : Code baby hannah plugin should be enabled from Vista -> basics -> plugin
	 * 
	 * PostCondition : Checks whether code baby displays.
	 * 
	 * Java file Name : Plugins.java
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * |  |  
	 * |  | 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void checkCodeBabyImage() {
		
		try {
			scr.capturePageScreenshot();
			Assert.assertTrue("Code Baby has not Dispalyed", performAction.isElementPresent(codeBabyPlayer));
						
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occured in code baby Image Verification  " + e.getMessage());
			throw new CustomException("Exception in code baby Image Verification  "
					+ e.getMessage());
		}
	}
	
	
	/**
	 * <pre> 
	 * Author  : Srilatha A
	 *  
	 * Role    : HR Role
	 *  
	 * Description : SelectLoginAndSwitchUIStyle keyword performs Select Login and click on Switch UI Style
	 * 
	 * PreCondition : Add Member and View in member Role
	 * 
	 * PostCondition : Should click on Switch UI style and change successfully.
	 *  
	 * Java file Name : Plugins.java
	 *  
	 * <b>Parameters & Example </b> 
	 * 
	 * |  |  
	 * |  | 
	 * </pre> 
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void selectLoginAndSwitchUIStyle() {

		try {
			
			performAction.waitUntilElementPresent(loginInfo);
			performAction.click(loginInfo, "Clicked On Login Information");
			
			performAction.waitUntilElementPresent(uiStyle);
			performAction.click(uiStyle, "Clicked On Switch UI Style");
			browser.getCurrentWebDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception in Select Login And Switch UI Style : " + e.getMessage());
			throw new CustomException("Exception occured in Select Login And Switch UI Style: \n"
					+ e.getMessage());
		}
	}	

	
}
