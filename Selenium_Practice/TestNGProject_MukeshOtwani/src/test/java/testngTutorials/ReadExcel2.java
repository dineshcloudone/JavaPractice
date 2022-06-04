package testngTutorials;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel2 {
	
	//int empty 

	public static String getCellValueAsString(Cell c) {
		String cell=null;
		switch(c.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			cell=c.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			cell=String.valueOf(c.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_BLANK:
			cell=null;
			break;
			
		}
		return cell;
	}
	
	public static int getCellValueAsInt(Cell c) {
		int cell=0;
		switch(c.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			cell=Integer.parseInt(c.getStringCellValue());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			cell=(int)c.getNumericCellValue();
			break;
		}
		return cell;
	}
	
	public static void readXLSFileWithBlankCells() {
		try {
			
			String file="C:\\Users\\dg185171\\Desktop\\Executions_R13P4.xlsx";			
			InputStream ExcelFileToRead = new FileInputStream(new File(file));
			XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

			XSSFSheet sheet = wb.getSheet("Sheet2");
			XSSFRow row;
			XSSFCell cell;

			Iterator rows = sheet.rowIterator();

			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();
				
				for(int i=0; i<row.getLastCellNum(); i++) {
					//cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					cell = row.getCell(i);
					System.out.print(cell.toString()+" ");
				}
				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		//readXLSFileWithBlankCells();
		
		String file="C:\\Users\\dg185171\\Desktop\\Executions_R13P4.xlsx";
		FileInputStream fis=new FileInputStream(new File(file));
		
		
		XSSFWorkbook book=new XSSFWorkbook(fis);
		
		
		XSSFSheet sheet=book.getSheet("sheet2");
		
		int rows=sheet.getLastRowNum();
		
		System.out.println("Total no of rows in excel :"+rows);
		
		for(int i=0;i<=rows;i++) {
			
			
			XSSFRow row=sheet.getRow(i);
			
			
			System.out.print(" "+row.getCell(0,Row.CREATE_NULL_AS_BLANK));
			System.out.println(" "+row.getCell(1,Row.CREATE_NULL_AS_BLANK));
			
			
			/*Cell ic=sheet.getRow(i).getCell(0);
			
			int inc=getCellValueAsInt(ic);
			System.out.println("cell value as integer : "+inc);
			Cell sc=sheet.getRow(i).getCell(1);
			String stc=getCellValueAsString(sc);			
			System.out.println("string cell value is : "+stc);*/
			
			
		}
		
	}

	}
