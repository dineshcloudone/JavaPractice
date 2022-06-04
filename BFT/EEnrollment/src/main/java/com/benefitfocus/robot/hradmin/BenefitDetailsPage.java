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
public class BenefitDetailsPage {

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

	String benefits = "//div[@class='clumpRegion' or @id='furlPanels']//div[@class='clumpRegion']";
	String currentBenefitDetails = "(//div[@class='clumpRegion' or @id='furlPanels']//tbody/tr/td[1])[1]/div";
	By nextButton = By.xpath("//strong[text()='Next']");

	// To edit the benefit detail from Hr-Admin role
	private void editBenefitDetail(String benefitName, String benefitDetail) {

		try {
			int benefitCount = browser.getCurrentWebDriver()
					.findElements(By.xpath(benefits)).size();
			System.out.println("=====================Benefits Count  :"
					+ benefitCount);

			if (benefitCount == 0) {
				int countDetails = browser.getCurrentWebDriver()
						.findElements(By.xpath(currentBenefitDetails)).size();
				System.out.println("Current Benefit Details Count :"
						+ countDetails);

				for (int i = 2; i <= countDetails; i++) {
					String detailName = browser
							.getCurrentWebDriver()
							.findElement(
									By.xpath("(" + currentBenefitDetails + ")"
											+ "[" + i + "]" + "/div[1]//span"))
							.getText();
					if (detailName.trim().toLowerCase()
							.contains(benefitDetail.trim().toLowerCase())) {

						System.out.print("==========Detail name from APP: "
								+ detailName
								+ "========Detail Name we Passed: "
								+ benefitDetail);

						By editButton = By
								.xpath("("
										+ currentBenefitDetails
										+ ")["
										+ i
										+ "]//a[text()='Edit' or text()='Cancel Benefits for All']");
						performAction.jsclick(editButton,
								"Benefit detail Edit button for :"
										+ benefitDetail);

						if (performAction.isAlertPresent()) {
							Thread.sleep(3000);
							performAction.acceptAlert();

						}

						break;
					}
				}

			} else {
				for (int b = 1; b <= benefitCount; b++) {

					boolean editBenefit = false;

					String benefName = browser
							.getCurrentWebDriver()
							.findElement(
									By.xpath("(" + benefits + ")[" + b
											+ "]//h2")).getText();

					System.out.println("=====================Benefit Name :"
							+ benefName);

					if (benefName.trim().toLowerCase()
							.equals(benefitName.trim().toLowerCase())) {
						System.out.println("Benefit Name Found : " + benefName);

						int count = browser
								.getCurrentWebDriver()
								.findElements(
										By.xpath("(" + benefits + ")[" + b
												+ "]" + "//tbody/tr/td/div"))
								.size();

						System.out.println("--------Number of Benefit Details:"
								+ count);

						for (int i = 2; i <= count; i++) {
							String detailName = browser
									.getCurrentWebDriver()
									.findElement(
											By.xpath("((" + benefits + ")[" + b
													+ "]"
													+ "//tbody/tr/td/div)"
													+ "[" + i + "]"
													+ "/div[1]//span"))
									.getText();

							System.out.println("============Details Name : "
									+ detailName);

							if (detailName.trim().toLowerCase()
									.equals(benefitDetail.trim().toLowerCase())) {

								System.out
										.print("==========Detail name from APP: "
												+ detailName
												+ "========Detail Name we Passed: "
												+ benefitDetail);

								By editButton = By
										.xpath("(("
												+ benefits
												+ ")["
												+ b
												+ "]"
												+ "//tbody/tr/td/div)["
												+ i
												+ "]//a[text()='Edit' or text()='Cancel Benefits for All']");
								performAction.jsclick(editButton,
										"Benefit detail Edit button for :"
												+ benefitDetail);

								editBenefit = true;

								if (performAction.isAlertPresent()) {
									Thread.sleep(3000);
									performAction.acceptAlert();

								}

								break;
							}
						}
						if (editBenefit) {
							break;
						}

					}

				}
			}
		} catch (Exception e) {
			scr.capturePageScreenshot();
			System.out.println("Exception occured while Editing Benefit"
					+ benefitName + "details" + e.getMessage());
			throw new RuntimeException(
					"Exception occured while Benefit account details "
							+ e.getMessage());
		}

	}

	/**
	 * <pre>
	 * Author  : Teja Puchala
	 * source: hradmin/PayrollBenefitDetails
	 *  
	 * Description   : This keyword is used to edit the Benfit details in HR Admin Role
	 * 
	 * PreCondition  : Benefit Details page should be displayed 
	 * 
	 * PostCondition : Corresponding page should be displayed as per configurations.
	 * 
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({ "strbenefitName", "strbenefitDetail" })
	public void editBenefitDetails(String benefitName, String benefitDetail) {
		this.editBenefitDetail(benefitName, benefitDetail);
	}

}
