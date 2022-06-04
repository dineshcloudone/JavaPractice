package com.benefitfocus.robot.hradmin.dataandreporting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

@RobotKeywords
public class StandardReports {
	@Autowired
	protected ActionKeywords performAction;

	@Autowired
	protected Logging logger;

	@Autowired
	protected ManageBrowser browser;

	@Autowired
	protected Utilities utils;

	@Autowired
	protected Screenshot screen;

	By fileType=By.id("reportOutputType");
	By sortType=By.id("sort");
	By startDate=By.id("dateRange-startDate");
	By endDate=By.id("dateRange-endDate");
	By transactionHistory=By.linkText("Transaction History");
	By personalTransactionHistory=By.linkText("Personal Transaction History Report");
	By createReport=By.linkText("Create Report");
	By standardReports=By.linkText("Standard reports");
	By plusIcon=By.xpath("//form[@name='reportAction']/table/tbody/tr[3]/td[2]/div/div[2]/table/tbody/tr[2]/td[6]");
	By dependents=By.id("showDependents-nativeHtmlElement");
	By benefitType=By.id("benefitType");
	By showCategories=By.id("showCategories-nativeHtmlElement");
	By payrollEffectiveDate= By.id("endDate");
	By payrollchangestartdate=By.id("startDate");
	By payrollAttributeChanges=By.linkText("Payroll attribute changes");
	By payroll=By.linkText("Payroll");
	By payrollChanges=By.linkText("Payroll changes report");
	By payrollCensus=By.linkText("Payroll census report");
	By continueToDownload = By.partialLinkText("Continue To Download"); // this is used on if condition base
	By downloadLink = By.linkText("Download");
	By employeeSummaryReport=By.linkText("Employee Summary Report");
	
	By dependentRelationShip = By.xpath("//button[@title='Spouse, Child']");
	By census = By.linkText("Census");
	By dependentCensus = By.linkText("Dependent census");
	By selectAllCheckBox = By.xpath("//input[@value='multiselect-all']");
	By spouseCheckBox = By.xpath("//input[@value='SPOUSE']");
	By childCheckBox = By.xpath("//input[@value='CHILD']");
	By clickReport = By.xpath("//div[@class='minimalRegion']//td[@class='positive-complete qualifiedFunctionalityCommand positive']//td[@class='cc']//a");

	private void clickOnLink(By link, String element){
		try{

			performAction.click(link,element);
		}catch(Exception e){
			screen.capturePageScreenshot();
			throw new CustomException("Exception in clicking link:"+link+" "+ e.getMessage());
		}
	}
	
	private void clickStandardReports() {
		performAction.click(standardReports, "Click Standard Reports");
	}
	
	private void clickCensusTab() {
		performAction.click(census, "Click Census");
	}
	
	private void dependentCensusLink() {
		performAction.click(dependentCensus, "Click Dependent Census");
	}
	
	private void selectSpouseDependent() {
		performAction.click(dependentRelationShip, "Change Dependent Status");
		performAction.click(childCheckBox, "Click Child Check Box");
	}
	
	private void clickCreateReport() {
		performAction.jsclick(clickReport, "Click Create Report");
	}

	private String getFileName(){
		String fileName=null;
		String homeFolder=System.getProperty("user.home");
		String dir=homeFolder+System.getProperty("file.separator")+"Downloads"+System.getProperty("file.separator");

		try{

			File files = new File(dir);

			File[] listOfFiles = files.listFiles();

			if (listOfFiles == null || listOfFiles.length == 0) {
				throw new FileNotFoundException();
			}

			File lastModifiedFile = listOfFiles[0];
			for (int i = 1; i < listOfFiles.length; i++) {
				if (lastModifiedFile.lastModified() < listOfFiles[i].lastModified()) {
					lastModifiedFile = listOfFiles[i];
				}
			}


			if (lastModifiedFile.isFile()) {
				fileName=lastModifiedFile.getName();
				fileName=dir+fileName;
			}			
		}catch(Exception e){
			screen.capturePageScreenshot();
			throw new CustomException("Exception in getting the latest downloaded File"
					+ e.getMessage());
		}

		return fileName;

	}

	/**
	 *  Author  : Manikanta Yallabandi
	 *  
	 * Description   :verifyExcelReport keyword will verify whether there 
	 * 				is any data present in downloaded Excel file or not

	 * PreCondition : Excel File downloading should be completed in HR Admin Role
	 * 
	 * PostCondition : Excel report verification done successfully.
	 *  
	 * <pre>
	 * <b>Parameters :</b>
	 * | No Parameters|
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({})
	public void verifyExcelReport(){

		Boolean dataPresent=false;

		try {
			String filename=this.getFileName();

			FileInputStream file = new FileInputStream(new File(filename));

			HSSFWorkbook workbook = new HSSFWorkbook(file);

			HSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			while(rowIterator.hasNext()) {

				Row row = rowIterator.next();

				Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()) {        
					Cell cell=(Cell) cellIterator.next();
					if(cell!=null){
						dataPresent=true;
						break;
					}

				}

			}
			if(!dataPresent){
				throw new Exception("No Data present in the Excel file");
			}
		}catch(Exception e){
			screen.capturePageScreenshot();
			throw new CustomException("Exception occured in verifying Excel Report "
					+ e.getMessage());
		}
	}
	
	/**
	 *  Author  : Manikanta Yallabandi
	 *  
	 * Description   :exportReportTOPDFAndVerify keyword will verify whether the 
	 * 				required data present in the specified PDF file or not.
	 * 
	 * PreCondition : PDF File downloading should be completed in HR Admin Role
	 * 
	 * PostCondition : PDF report verification done successfully.
	 * 
		 * <pre>
	 * <b>Parameters :</b>
	 * | reportName|text|
	 * </pre>
	 */

	@RobotKeyword
    @ArgumentNames({"reportName" , "text"})
    public void exportReportTOPDFAndVerify(String reportName, String text){
        try{
            
            String filename= this.getFileName();
            Thread.sleep(5000);
            PdfReader reader = new PdfReader(filename);
            logger.info(filename);
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
                throw new CustomException(
                    "Exception occured verifing PDF Report "
                            + e.getMessage());
        }  
    }
    

	/**
	 *  Author  : Manikanta Yallabandi
	 *  
	 * Description   :verifyPDFReport keyword will verify whether there 
	 * 				is any data present in downloaded PDF file or not
	 * 
	 * PreCondition : PDF File downloading should be completed in HR Admin Role
	 * 
	 * PostCondition : PDF report verification done successfully.
	 * 
	 * <pre>
	 * <b>Parameters :</b>
	 * | No Parameters|
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({})
	public void verifyPDFReport(){
		try {
			String filename= this.getFileName();
			PdfReader reader = new PdfReader(filename);

			String page = PdfTextExtractor.getTextFromPage(reader, 1);
			if(page==null){
				throw new Exception("No data present in the report");
			}
		}catch(Exception e){
			screen.capturePageScreenshot();
			throw new CustomException("Exception in Verifying PDF Report"
					+ e.getMessage());
		}
	}

	/**
	 *  Author  : Manikanta Yallabandi
	 *  
	 * Description   : Transaction History Report keyword will fill the
	 * 				formatting options required to generate the Transaction History report, by taking
	 * 				data from JSON files and generate Transaction History Report
	 *
	 * PreCondition : Data and Reporting page in HR Admin Role
	 * 
	 * PostCondition : Transaction History report generated successfully.
	 * 
	 *  eg : Report Format, start date, end date...
	 * 
	 * <pre>
	 * <b>Parameters :</b>
	 * | Json Tag name|
	 * </pre>
	 */

	@RobotKeyword
	@ArgumentNames({"classification"})
	public void transactionHistoryReport(String classification){
		try{
			this.clickOnLink(standardReports,"Navigate to Standard History sub menu");
			this.clickOnLink(transactionHistory,"Navigate to Transaction History Tab");
			this.clickOnLink(personalTransactionHistory,"Navigate to Personal Transaction History Tab");
			if (classification.startsWith("td:"))
				classification = classification.substring(3);

			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(classification.toLowerCase());

			object = fields.get("reportType");
			if (object != null) {
				performAction.select(fileType,object.toString(), "Report Format");
			}

			object = fields.get("sortBy");
			if (object != null) {
				performAction.select(sortType,object.toString(), "SortBy");
			}

			object = fields.get("startDate");
			if (object != null) {
				String strDate=utils.getDate(object.toString());
				performAction.enter(startDate,strDate , "startDate");
			}


			object = fields.get("endDate");
			if (object != null) {
				String enDate=utils.getDate(object.toString());
				performAction.enter(endDate, enDate, "endDate");
			}
			clickOnLink(createReport,"Click on Create Report button");
			Thread.sleep(40000); // The report takes approximately 30 secs to process and to show up the download link and no way to use explicit wait
			performAction.mouseOver(plusIcon, "move mouse on plus icon");
			performAction.click(downloadLink, "Click on Download link");

			boolean res=browser.getCurrentWebDriver().getPageSource().contains("PHI NOTIFICATION");
			if(res){
				performAction.click(continueToDownload,"Click Continue to download link");
			}


		}catch(Exception e){
			screen.capturePageScreenshot();
			throw new CustomException("Exception in Transaction History report "
					+ e.getMessage());
		}

	}

	/**
	 *  Author  : Manikanta Yallabandi
	 *  
	 * Description   :PayRoll Attribute Change Report keyword will fill the
	 * 				formatting options required to generate the Pay roll Attribute change
	 * 				report, by taking data from JSON files, and generate Payroll change reports
	 *
	 * PreCondition : Data and Reporting page in HR Admin Role
	 * 
	 * PostCondition : Payroll attribute change report generated successfully.
	 * 
	 *  eg : Report Format, start date, end date...
	 * 
	 * <pre>
	 * <b>Parameters :</b>
	 * | Json Tag name|
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({"classification"})
	public void payrollAttributeChangeReport(String classification){
		try{
			this.clickOnLink(standardReports,"Navigate to Standard History sub menu");
			this.clickOnLink(payroll,"Navigate to Payroll Tab");
			this.clickOnLink(payrollAttributeChanges,"Navigate to Payroll Attribute changes Tab");

			if (classification.startsWith("td:"))
				classification = classification.substring(3);

			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(classification.toLowerCase());

			object = fields.get("reportType");
			if (object != null) {
				performAction.select(fileType,object.toString(), "Report Format");
			}

			object = fields.get("sortBy");
			if (object != null) {
				performAction.select(sortType,object.toString(), "SortBy");
			}

			object = fields.get("startDate");
			if (object != null) {
				String strDate=utils.getDate(object.toString());
				performAction.enter(startDate,strDate , "startDate");
			}


			object = fields.get("endDate");
			if (object != null) {
				String enDate=utils.getDate(object.toString());
				performAction.enter(endDate, enDate, "endDate");
			}

			object = fields.get("showDependends");
			if (object != null) {
				if(object.toString().equalsIgnoreCase("yes")){
					performAction.click(dependents, "Select Dependents");
				}

			}

			object = fields.get("benefitType");
			if (object != null) {
				performAction.select(benefitType, object.toString(), "BenefirType");

			}

			clickOnLink(createReport,"Click on Create Report button");
			Thread.sleep(40000);  // The report takes approximately 30 secs to process and to show up the download link and no way to use explicit wait
			performAction.mouseOver(plusIcon, "move mouse on plus icon");
			performAction.click(downloadLink, "Click on Download link");

			boolean res=browser.getCurrentWebDriver().getPageSource().contains("PHI NOTIFICATION");
			if(res){
				performAction.click(continueToDownload,"Click Continue to download link");
			}

		}catch(Exception e){
			screen.capturePageScreenshot();
			throw new CustomException("Exception in generating payroll Attribute change report "
					+ e.getMessage());

		}
	}

	/**
	 *  Author  : Manikanta Yallabandi
	 *  
	 * Description   : payRollChangeReport keyword will fill the
	 * 				formatting options required to generate the Pay roll change
	 *  			report, by taking data from JSON files and generate Payroll change report
	 *
	 * PreCondition : Data and Reporting page in HR Admin Role
	 * 
	 * PostCondition : Payroll change report generated successfully.
	 * 
	 *  eg : Report Format, start date, end date...
	 * 
	 * <pre>
	 * <b>Parameters :</b>
	 * | Json Tag name|
	 * </pre>
	 */

	@RobotKeyword
	@ArgumentNames({"classification"})
	public void payrollChangeReport(String classification){
		try{
			this.clickOnLink(standardReports,"Navigate to Standard History sub menu");
			this.clickOnLink(payroll,"Navigate to Payroll Tab");
			this.clickOnLink(payrollChanges,"Navigate to Payroll Changes Tab");

			if (classification.startsWith("td:"))
				classification = classification.substring(3);

			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(classification.toLowerCase());

			object = fields.get("reportType");
			if (object != null) {
				performAction.select(fileType,object.toString(), "Report Format");
			}

			object = fields.get("sortBy");
			if (object != null) {
				performAction.select(sortType,object.toString(), "SortBy");
			}

			object = fields.get("showCategories");
			if (object != null) {
				if(object.toString().equalsIgnoreCase("yes")){
					performAction.click(showCategories, "Select showCategories");
				}

			}

			object = fields.get("startDate");
			if (object != null) {
				String strDate=utils.getDate(object.toString());
				performAction.enter(payrollchangestartdate,strDate , "startDate");
			}


			object = fields.get("endDate");
			if (object != null) {
				String enDate=utils.getDate(object.toString());
				performAction.enter(payrollEffectiveDate, enDate, "endDate");
			}



			object = fields.get("benefitType");
			if (object != null) {
				performAction.select(benefitType, object.toString(), "BenefirType");

			}

			clickOnLink(createReport,"Click on Create Report button");
			Thread.sleep(40000); // The report takes approximately 30 secs to process and to show up the download link and no way to use explicit wait
			performAction.mouseOver(plusIcon, "move mouse on plus icon");
			performAction.click(downloadLink, "Click on Download link");

			boolean res=browser.getCurrentWebDriver().getPageSource().contains("PHI NOTIFICATION");
			if(res){
				performAction.click(continueToDownload,"Click Continue to download link");
			}
		}catch(Exception e){
			screen.capturePageScreenshot();
			throw new CustomException("Exception in generating Payroll cahnges report"
					+ e.getMessage());
		}

	}

	/**
	 *  Author  : Manikanta Yallabandi
	 *  
	 * Description   : payRollcensusReport keyword will fill the
	 * 				formatting options required to generate the Pay roll census
	 *  			report, by taking data from JSON files and generate
	 *  			Payroll census report
	 *
	 * PreCondition : Data and Reporting page in HR Admin Role
	 * 
	 * PostCondition : Payroll census report generated successfully.
	 * 
	 *  eg : Report Format, start date, end date...
	 * 
	 * <pre>
	 * <b>Parameters :</b>
	 * | Json Tag name|
	 * </pre>
	 */
	@RobotKeyword
	@ArgumentNames({"classification"})
	public void payrollCensusReport(String classification){
		try{
			this.clickOnLink(standardReports,"Navigate to Standard History sub menu");
			this.clickOnLink(payroll,"Navigate to Payroll Tab");
			this.clickOnLink(payrollCensus,"Navigate to Payroll Census Tab");

			if (classification.startsWith("td:"))
				classification = classification.substring(3);

			Object object = null;
			JSONObject fields = ReadJsonTestData.getTestData(classification.toLowerCase());

			object = fields.get("reportType");
			if (object != null) {
				performAction.select(fileType,object.toString(), "Report Format");
			}

			object = fields.get("sortBy");
			if (object != null) {
				performAction.select(sortType,object.toString(), "SortBy");
			}


			object = fields.get("effectiveDate");
			if (object != null) {
				String enDate=utils.getDate(object.toString());
				performAction.enter(payrollEffectiveDate, enDate, "effectiveDate");
			}

			object = fields.get("showCategories");
			if (object != null) {
				if(object.toString().equalsIgnoreCase("yes")){
					performAction.click(showCategories, "Select showCategories");
				}

			}
			clickOnLink(createReport,"Click on Create Report button");
			Thread.sleep(40000);  // The report takes approximately 30 secs to process and to show up the download link and no way to use explicit wait
			performAction.mouseOver(plusIcon, "move mouse on plus icon");
			performAction.click(downloadLink, "Click on Download link");

			boolean res=browser.getCurrentWebDriver().getPageSource().contains("PHI NOTIFICATION");
			if(res){
				performAction.click(continueToDownload,"Click Continue to download link");
			}


		}catch(Exception e){
			screen.capturePageScreenshot();
			throw new CustomException("Exception in generating Payroll Census report "
					+ e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * 
	 *  Author  : Rajeswari Nimmala
	 *  
	 * Description	: verifyPDFContent keyword will verify weather there is given content present in downloaded PDF file or not
	 * 
	 * Role : HR Role
	 * 
	 * PreCondition  : Employee report should be downloaded
	 * 
	 * PostCondition : Content will be verified from downloaded report. 
	 *   
	 * 
	 * <b>Parameters :</b>
	 *  |"reqTextInPDF"--String which needs to be verified in the Report|
	 * 
	 * Java file Name :  StandardReports.java
	 * 
	 *  </pre>
	 */
	@RobotKeyword
	@ArgumentNames({"reqTextInPDF"})
	public void verifyPDFContent(String reqTextInPDF){
		
		String parsedText=null;
		try{
			String filename=this.getFileName();
			logger.info("PDF File name: " + filename);
			PDFParser parser=new PDFParser(new FileInputStream(filename));
			parser.parse();
			
			parsedText=new PDFTextStripper().getText(parser.getPDDocument());
			logger.info("Employee Report: " + parsedText);
			parser.getPDDocument().close();
			
		}catch(Exception e){
			logger.info(e.getMessage());
			screen.capturePageScreenshot();
			throw new CustomException("Exception in performing verification PDF content "
					+ e.getMessage());
		}
		Assert.assertTrue(reqTextInPDF, parsedText.contains(reqTextInPDF));
	}
	
	/**
	 * <pre>
	 * Author  : Varun Reddy Proddutoori
	 *  
	 * Description  : Click Data & Reporting HR Role
	 * 
	 * Role : HR Admin
	 * 
	 * PreCondition : Dependent Overview Page Opened in HR Admin Role
	 * 
	 * PostCondition : Verify Dependent Relationship Displayed in Reports
	 * 
	 * Java File: StandardReports.java
	 * 
	 * | |
	 * </pre>
	 **/
	@RobotKeyword
	@ArgumentNames({})
	public void verifyDependentRelationShipInReports() {
		try {
			this.clickStandardReports();
			this.clickCensusTab();
			this.dependentCensusLink();
			this.selectSpouseDependent();
			this.clickCreateReport();
		}catch (Exception e) {
			screen.capturePageScreenshot();
			throw new CustomException("No Create Report Link Available. \n"
					+ e.getMessage());
		}
	}
}
