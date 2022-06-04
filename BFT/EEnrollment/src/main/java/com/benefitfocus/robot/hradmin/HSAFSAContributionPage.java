package com.benefitfocus.robot.hradmin;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import org.openqa.selenium.support.ui.Select;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;


import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.*;
import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.utils.CustomException;


@RobotKeywords
public class HSAFSAContributionPage {

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
	
	String radioButtonHeaders="//div[@class='regionHeader']";	
	String allRadioButtonsRows="//*[@class='fieldListRow']";
	String allRadioButtonsNames=".//*[@class='fieldListLabelRight']";
	String allRadioButtons=".//*[@class='fieldListFieldLeft']//input";	
	//By nextButton=By.xpath("(//*[@class='btn btn-success'])[1]");
	
	By nextButton = By.xpath("//strong[text()='Next']");
	By effectiveDate = By.name("overallEffectiveDate");
	
	
	By hsaDentalVisionRadioButton = By
			.id("hsaUsedOutsideSystem[DENTAL_VISION]");
	By hsaNotApplicableRadioButton = By
			.id("hsaUsedOutsideSystem[NOT_APPLICABLE]");
	
	
	By contributionTypeHSA = By.id("contributionType");
	By contributionAmount = By.id("scheduledAmount-EE_SCHEDULED");
	By startPeriod = By.id("startDate-EE_SCHEDULED");
	By addButton = By.id("innerLinkcontributionCompositeAddButton");
	By editDueToChangeReasonHSAORFSA = By
			.xpath("(//div[@class='clumpRegion'])[3]//strong");
	
	
	//Selecting the Radio button as per its Name
	private void selectRadioButton(String radioButtonName)
	{
		try
		{
			
		boolean Status=false;	
		List<WebElement> radioButtonRows=browser.getCurrentWebDriver().findElements(By.xpath(allRadioButtonsRows));
		
		//logger.info("============================RadioButton Rows:"+radioButtonRows);
		
		for(WebElement element:radioButtonRows)
		{
			String radioButtonName1=element.findElement(By.xpath(allRadioButtonsNames)).getText();
			//logger.info("============================RadioButton Name1:"+radioButtonName1);
			//logger.info("============================RadioButton Name:"+radioButtonName);
			if(radioButtonName.trim().toUpperCase().contains(radioButtonName1.toUpperCase()))
			{
				Thread.sleep(2000);				
				element.findElement(By.xpath(allRadioButtons)).click();
				Status=true;
				break;
			}
		}
		if(!Status)
		{
			throw new RuntimeException("Failed to select the Radio Button"+radioButtonName);
		}
		
		}
		catch(Exception e)
		{
			System.out.print("Failed to select the Radio Button as per the name passed");
			e.printStackTrace();
		}
		
	}
	
	//Selecting the Radio button as per its Header Name
	private void selectRadioButtonBasedOnHeader(String strQuestion,String radioButtonName)
		{
			try
			{

			// ((//div[@class='regionHeader'])[2]/following-sibling::div//*[@class='fieldListLabelRight'])[1]/preceding-sibling::td/input
			boolean Status = false;
			List<WebElement> radioButtonHeaderList = browser
					.getCurrentWebDriver().findElements(
							By.xpath(radioButtonHeaders));

			for (WebElement headerElement : radioButtonHeaderList) {
				String headerText = headerElement.findElement(By.xpath("./h3"))
						.getText();
				if (headerText.trim().toLowerCase()
						.contains(strQuestion.trim().toLowerCase())) {
					List<WebElement> radioButtonNameElements = headerElement
							.findElements(By
									.xpath("./following-sibling::div//*[@class='fieldListLabelRight']"));

					for (WebElement NameElement : radioButtonNameElements) {
						String name = NameElement.getText();
						if (name.trim().toLowerCase()
								.contains(radioButtonName.trim().toLowerCase())) {
							Thread.sleep(3000);
							NameElement
									.findElement(
											By.xpath("./preceding-sibling::td/input"))
									.click();
							Status = true;
							break;
						}
					}
				}
			}

			//logger.info("============================RadioButton Rows:"+radioButtonRows);
			
			
			if(!Status)
			{
				throw new RuntimeException("Failed to select the Radio Button :"+radioButtonName);
			}
			
			}
			catch(Exception e)
			{
				System.out.print("Failed to select the Radio Button :"+radioButtonName);
				e.printStackTrace();
			}
			
		}
	
	
	
	// Select HSA Participation
			/*private void selectHSAParticipationForMedical(String strHSAParticipation) {
				performAction.waitUntilElementPresent(hsaDentalVisionRadioButton);
				if (strHSAParticipation.equalsIgnoreCase("Yes")) {
					performAction.click(hsaDentalVisionRadioButton,
							"Yes for HSA participation");
				} else {
					performAction.click(hsaNotApplicableRadioButton,
							"No for HSA Participation");
				}
			}*/
	
			
			private void enterHSAContributionAmountDetails(
					String contributionAmountType, String contributionAmountValue,
					String startPeriodValue) {
				performAction
						.select(contributionTypeHSA, contributionAmountType,
								"Contribution type combobox for selecting the Contribution type");
				performAction.enter(contributionAmount, contributionAmountValue,
						"Contribution Amount Type");

				performAction.select(startPeriod, startPeriodValue,
						"Period which is countable for Contribution");

				performAction.click(nextButton,
						"Next button in Contribution details page");

			}
	
	/**
	 * <pre>
	 * Author  : Prasad Pasupuleti
	 * source : HSAFSAContributionPage
	 *  
	 * Description   : 'Select HSA Participation' keyword or method is used to select the Yes/No option for HSA in HR-Admin
	 * 
	 * PreCondition  :  HSA Participation page should be opened
	 * 
	 * PostCondition : It will navigate to next page as per the group configuration
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strTitle","strRadioButtonName" })
	public void selectHSAParticipation(String strTitle,String strRadioButtonName) {
		try {
			this.selectRadioButtonBasedOnHeader(strTitle, strRadioButtonName);
			performAction.click(nextButton, "HSA Participation Next Button");
		} catch (Exception e) {
			scr.capturePageScreenshot();
			System.out
					.println("Exception occured while selecting HSA participation"
							+ e.getMessage());
			throw new CustomException(
					"Exception occured while selecting HSA participation"
							+ e.getMessage());
		}

	}
	
	/**
	 * <pre>
	 * Author  : Teja Puchala
	 * source : HSAFSAContributionPage
	 *  
	 * Description   : This keyword helps to change the HSA account details from HR-Role
	 * 
	 * PreCondition  : Member should be enrolled in Medical Benefit along with HSA details
	 * 
	 * PostCondition : Page will be displayed as per the configurations
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strHSADetail", "contributionAmountType",
			"contributionAmountValue", "startPeriodValue" })
	public void addFlexContribution(String strHSADetail,
			String contributionAmountType, String contributionAmountValue,
			String startPeriodValue) {
		try {
			// this.editHSAAccountDetails(strHSADetail);
			this.enterHSAContributionAmountDetails(contributionAmountType,
					contributionAmountValue, startPeriodValue);

		} catch (Exception e) {
			throw new CustomException(
					"Exception occured while editing the HSA account detail "
							+ strHSADetail + "+:+" + e.getMessage());
		}
	}
	

}
