package test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.POIXMLDocument;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import test.common.*;

public class Utils extends Logging{
	
	HashMap<String,String> hm=new HashMap<String,String>();
	
public HashMap runDetails() {
		
		try {
			// Access to RunManager.xls file
			File f1 = new File("C:\\Dinesh\\Java\\Eclipse_Projects\\Selenium_Practice\\SeleniumFramework\\ExecutionDetails.xlsx");
			// Create File Input Steam Object
			FileInputStream fis= new FileInputStream(f1);
			// Create Workbook Object
			XSSFWorkbook wb1 = new XSSFWorkbook(fis);			
			
			// Create Worksheet Object
			XSSFSheet ws1 = wb1.getSheet("RunDetails");
			// get Row count
			int rowcount = ws1.getLastRowNum();
			
			// Iterate Through all rows and get the testcases which are set to true.
			for (int j = 1; j <= rowcount; j++) {
				Cell browserCell = ws1.getRow(j).getCell(1);
				hm.put("Browser", browserCell.getStringCellValue());
				
				Cell parallelExecutionCell = ws1.getRow(j).getCell(1);
				hm.put("Parallel execution", parallelExecutionCell.getStringCellValue());
				
				Cell urlCell = ws1.getRow(j).getCell(1);
				hm.put("URL", urlCell .getStringCellValue());
				
				Cell usernameCell = ws1.getRow(j).getCell(1);
				hm.put("username", usernameCell.getStringCellValue());
				
				Cell passwordCell = ws1.getRow(j).getCell(1);
				hm.put("password", passwordCell.getStringCellValue());				
				break;
			}
			
			// close workbook
			wb1.close();
			
			// close input stream
			fis.close();
			
		} catch (Exception e) {

		}
		
		return hm;		
	}
	
	public static int getCellValueAsInt(Cell c1) {
		int output = 0;
		switch (c1.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			output = Integer.parseInt(c1.getStringCellValue());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			output = (int) c1.getNumericCellValue();
			break;
		}
		return output;
	}

	public static String getCellValueAsString(Cell c1) {
		String output = "";
		switch (c1.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			output = c1.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			output = String.valueOf(c1.getNumericCellValue());
			break;
		}
		return output;
	}

}
