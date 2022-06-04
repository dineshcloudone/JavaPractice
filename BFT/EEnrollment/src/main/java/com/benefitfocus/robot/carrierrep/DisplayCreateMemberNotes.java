package com.benefitfocus.robot.carrierrep;

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
public class DisplayCreateMemberNotes {
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

	// Locators
	By carrierName = By.id("selectedCarrier");
	String pluginList = "//table[contains(@class,'standardDataTable')]/tbody/tr";
	String edit = "(//a[text()='Edit'])";
	By template = By.id("templateType");
	By save = By.xpath("//strong[text()='Save']");
	By sortOrder = By.xpath("//input[@id='sortOrder']"); 
	By yesButton = By.id("displayAfterEnrollmentComplete-0");
	By noButton = By.id("displayAfterEnrollmentComplete-1");
	By  visibleEnabled = By.xpath("(//input[@name='isEnabled'])[1]");
	By  visibleDisabled = By.xpath("(//input[@name='isEnabled'])[2]");
	By selectedStates = By.id("selectedStatesDiv");
	By cancel = By.xpath("//strong[text()='Cancel']");
	// Private Methods
	private void selectCarrier(String strCarrierName) {
		performAction.select(carrierName, strCarrierName, "Carreir Select Box");
	}

	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : 'verifyCarrierMemberNoteInHrRole' keyword used to verify the member note  Carrier representative role
	 * 
	 * Role: Carrier Representative role
	 * 
	 * PreCondition : user should be in Display\Create member notes Page
	 *  
	 * PostCondition : user  will be redirected to Display\Create member notes Page
	 * 
	 *  * <b>Parameters & Example </b> 
	 * 
	 * | strCarrierNote | 
	 * | getinsured |audax | 
	 * 
	 * JavaFileName  : DispayCreateMemberNotes.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strPluginName" })
	public void verifyMemberNoteInCarrierRep(String strPluginName) {
		try {

			if (performAction.isElementPresent(carrierName)) {
				this.selectCarrier("Mercer");
			}
			int pluginCount = browser.getCurrentWebDriver()
					.findElements(By.xpath(pluginList)).size();
			System.out.println("Plugins is.." + pluginCount);

			for (int i = 2; i <= pluginCount; i++) {

				By actionButton = By.xpath(pluginList + "[" + i
						+ "]/td[5]//a[contains(text(),'Actions')]");
				scr.capturePageScreenshot();
				performAction.mouseOver(actionButton, "Actions button");
				By editButton = By.xpath(edit + "[" + (i - 1) + "]");
				performAction.waitUntilElementPresent(editButton);
				performAction.jsclick(editButton, "Edit Button");

				Select sel = new Select(browser.getCurrentWebDriver()
						.findElement(template));
				scr.capturePageScreenshot();
				String selectedTemplate = sel.getFirstSelectedOption()
						.getText();
				System.out.println(" selected template for current plugin is.."
						+ selectedTemplate);
				performAction.click(save, "Save Button");

				if (strPluginName.toLowerCase().equalsIgnoreCase(
						selectedTemplate.toLowerCase())) {

					By statusPath = By.xpath(pluginList + "[" + i + "]/td[4]");
					String pluginStatus = browser.getCurrentWebDriver()
							.findElement(statusPath).getText();
					System.out.println(selectedTemplate + " plugin note is .."
							+ pluginStatus);
					break;
				}
				sel = null;
			}

		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occured in carrier notes " + e.getMessage());
			throw new CustomException(
					"Exception occured in verifying member note "
							+ e.getMessage());
		}

	}
	/**
	 * <pre>
	 * Author  : Nagarjuna Thallam
	 *  
	 * Description : 'verifyCarrierMemberNoteInHrRole' keyword used to verify the member note configurations in  Carrier representative role
	 * 
	 * Role: Carrier Representative role
	 * 
	 * PreCondition : user should be in Display\Create member notes Page
	 *  
	 * PostCondition : user  will be redirected to Display\Create member notes Page
	 * 
	 *  * <b>Parameters & Example </b> 
	 * 
	 * | strCarrierNote | 
	 * | getinsured |audax | 
	 * 
	 * JavaFileName  : DispayCreateMemberNotes.java
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({"strPluginName"})
	public void verifyMemberNoteConfigurationsInCarreirRep(String strPluginName) {
		try {
			if (performAction.isElementPresent(carrierName)) {
				this.selectCarrier("Mercer");
			}
			int pluginCount = browser.getCurrentWebDriver()
					.findElements(By.xpath(pluginList)).size();
			System.out.println("Plugins is.." + pluginCount);

			for (int i = 2; i <= pluginCount; i++) {
				By actionButton = By.xpath(pluginList + "[" + i
						+ "]/td[5]//a[contains(text(),'Actions')]");

				performAction.mouseOver(actionButton, "Actions button");
				By editButton = By.xpath(edit + "[" + (i - 1) + "]");
				performAction.waitUntilElementPresent(editButton);
				performAction.jsclick(editButton, "Edit Button");

			Select sel = new Select(browser.getCurrentWebDriver()
					.findElement(template));
			
			String selectedTemplate = sel.getFirstSelectedOption()
					.getText();
			System.out.println(" selected template for current plugin is.."
					+ selectedTemplate);
			boolean enableStatus = browser.getCurrentWebDriver().findElement(visibleEnabled).isSelected();
			if (strPluginName.toLowerCase().equalsIgnoreCase(
					selectedTemplate.toLowerCase())&& enableStatus == true) {
				String title = browser.getCurrentWebDriver().findElement(By.xpath("(//table[@class='fieldList'])[1]//tr[1]/td[2]//textarea[1]")).getText();
				System.out.println("Titile text is... "+title);
				String Description = browser.getCurrentWebDriver().findElement(By.xpath("(//table[@class='fieldList'])[1]//tr[2]/td[2]//textarea[1]")).getText();
				System.out.println("Description is..."+Description);
				String message = browser.getCurrentWebDriver().findElement(By.xpath("(//table[@class='fieldList'])[1]//tr[3]/td[2]//textarea[1]")).getText();
				System.out.println("Message is... "+message);
				String sort = browser.getCurrentWebDriver().findElement(sortOrder).getAttribute("value");
				System.out.println("sorting order is ..."+sort);
				boolean yesStatus = browser.getCurrentWebDriver().findElement(yesButton).isSelected();
				boolean noStatus = browser.getCurrentWebDriver().findElement(yesButton).isSelected();
				if(yesStatus == true ){
					System.out.println("Display only after enrollments complete is..... Enabled");
				}
				else if(noStatus == true){
					System.out.println("Display only after enrollments complete is not enabled");
				}
				boolean visibleEnableStatus = browser.getCurrentWebDriver().findElement(visibleEnabled).isSelected();
				boolean visibleDisaStatus = browser.getCurrentWebDriver().findElement(visibleEnabled).isSelected();
					if(visibleEnableStatus == true ){
						System.out.println("visibility is enabled");		
				}
					else if(visibleDisaStatus == true ){
						System.out.println("visibility is Disabled");	
					}
					String selected = browser.getCurrentWebDriver().findElement(selectedStates).getText();
					System.out.println("Selected states are...."+selected);
					break;
			}
				performAction.click(cancel, "Cancel Button");
				scr.capturePageScreenshot();
			}
			
		} catch (Exception e) {
			scr.capturePageScreenshot();
			logger.warn("Exception occured while verifying carrier notes configuration " + e.getMessage());
			throw new CustomException(
					"Exception occured in verifying member note configuration"
							+ e.getMessage());
		}
	}

}
