package com.benefitfocus.robot.hradmin;

import org.json.simple.JSONObject;

import org.openqa.selenium.By;
import java.io.File;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.common.ActionKeywords;
import com.benefitfocus.robot.common.ManageBrowser;
import com.benefitfocus.robot.common.Logging;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

@RobotKeywords
public class Dependent {

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
	
	By viewDetails = By.linkText("View Details");
	By getStarted = By.linkText("Get started");
	By editBenefitDetails = By.linkText("Edit");
	By editMedicalBenefit = By.linkText("Edit");
	
	private void getStarted() {
		performAction.click(getStarted, "Get Started");
	}
	
	private void viewDetails() {
		performAction.click(viewDetails, "View Details");
	}
	
	private void editBenefits() {
		performAction.click(editBenefitDetails, "Edit Button");
	}
	
	private void editMedical() {
		performAction.click(editMedicalBenefit, "Edit Medical Benefit");
	}
	
	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Delete Files in Download Folder
	 * 
	 * Role : HR Admin
	 * 
	 * PreCondition : Download Folder Available
	 * 
	 * PostCondition : Deleted All Files in Download Folder
	 * 
	 * Java File: Dependent.java
	 *
	 * </pre>
	 **/
	@RobotKeyword
	public void deleteFilesInDownloadFolder() {
		String homeFolder=System.getProperty("user.home");
		File dir = new File(homeFolder+File.separator+"Downloads");
		if (dir.isDirectory()) 
		{
		    String[] children = dir.list();
		    for (int i = 0; i < children.length; i++)
		    {
		       new File(dir, children[i]).delete();
		    }
		}
	}
	
	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Keyword is used to Edit Benefits to Enroll Dependent to Benefits After Adding in HR Role
	 * 
	 * Role : HR Admin
	 * 
	 * PreCondition : Dependent Overview page in HR Admin Role
	 * 
	 * PostCondition : Coverage Level Opened successfully.
	 * 
	 * Java File: Dependent.java
	 *
	 * </pre>
	 **/
	@RobotKeyword
	public void editBenefitsToEnrollDependentAfterAdding() {
		try {
			this.viewDetails();
			this.getStarted();
			this.editBenefits();
			this.editMedical();
		}catch (Exception e) {
			scr.capturePageScreenshot();
			throw new CustomException("No Edit Button Available. \n"
					+ e.getMessage());
		}
	}

	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Verify Text in Exported PDF Report
	 * 
	 * Role : HR Admin
	 * 
	 * PreCondition : Employee Summary Report Page Link Available on Page
	 * 
	 * PostCondition : Verified Text in Report
	 * 
	 * Java File: Dependent.java
	 * 
	 * | report Name | Text |
	 * |ex: EE_BENEFIT_SUMMARY | ex: Employee Details |
	 * </pre>
	 **/
	@RobotKeyword
    @ArgumentNames({"reportName" , "text"})
    public void verifyTextInExportedPdfReport(String reportName, String text){
        try{
        	String fileName=null;
            String uname=System.getProperty("user.home");
            String dir=uname+File.separator+"Downloads"+File.separator;
            fileName=dir+reportName+".pdf";
            logger.info(fileName);
            
            Thread.sleep(5000); //Wait for File
            PdfReader reader = new PdfReader(fileName);
            logger.info("This PDF has "+reader.getNumberOfPages()+" pages.");
            String page = PdfTextExtractor.getTextFromPage(reader, 1);
            if(page.contains(text)){
                logger.info("Data is present in PDF report ");
            }else{
                throw new CustomException(
                    "Required data is not present in PDF Report ");
            }
           
        }catch(Exception e){
            e.printStackTrace();
            scr.capturePageScreenshot();
                throw new CustomException(
                    "Exception occured verifing PDF Report "
                            + e.getMessage());
        }  
    }
}
