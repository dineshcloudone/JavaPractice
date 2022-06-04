package testngTutorials;

/* Agenda
 * ------
 * 1.What is Apache POI
 * 2.why use excel in Selenium
 * 3.Download
 * 4.Add Jars to Project
 * 5.Create excel file and read the data
 * 6.create library to reuse the code
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.POIXMLDocument;


import org.apache.*;

public class ReadExcel {
	
	public static void main(String[] args) throws IOException
	{
		//String file="C:\\Dinesh\\Java\\Eclipse_Projects\\Selenium_Practice\\TestNGProject_MukeshOtwani\\TestData.xlsx";
		
		String file="C:\\Users\\dg185171\\OneDrive - NCR Corporation\\Desktop\\14.14.xlsx";
		FileInputStream fis=new FileInputStream(new File(file));
		
		
		XSSFWorkbook book=new XSSFWorkbook(fis);
		
		
		XSSFSheet sheet=book.getSheet("sheet1");
		
		
		Iterator<Row> iterator=sheet.iterator();
		System.out.println("Last Row value : "+sheet.getLastRowNum());
		while(iterator.hasNext())
		{
			Row row=iterator.next();
			Iterator<Cell> cell=row.iterator();
			while(cell.hasNext())
			{
				Cell c=cell.next();
				switch(c.getCellType())
				{
				case Cell.CELL_TYPE_STRING:
					System.out.print(c.getStringCellValue()+ "(String)\t");
					break;
				case Cell.CELL_TYPE_NUMERIC:
					System.out.println(c.getNumericCellValue()+"(Integer)\t");
					break;
				}
				/*System.out.print(c.getCellType()+ " ");
				System.out.print(c.getStringCellValue()+ " "); */
			}
			System.out.println(" after while loop of cell iterator");
		}
		
	}

}
