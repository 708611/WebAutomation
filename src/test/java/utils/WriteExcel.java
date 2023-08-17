package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {
	
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static Row row;
	static Cell cell;
	static FileReader fr = null;
	static BufferedReader br=null;
	static FileInputStream fis;
	static FileOutputStream fos;
	
	public WriteExcel(String excelPath, String sheetName)
	{
		try {
		  fis=new FileInputStream(excelPath);
		  workbook=new XSSFWorkbook(fis);
		  sheet=workbook.getSheet(sheetName);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public static void writeCellValue(int rowNum,int colNum,String value,String excelPath)
	{
		try {
		System.out.println("****Write Values to Excel*****");
		 fr=new FileReader(excelPath);
		 br=new BufferedReader(fr);
		row=sheet.getRow(rowNum);
		cell=row.createCell(colNum);
		cell.setCellValue(value);
	    fos=new FileOutputStream(excelPath);
		workbook.write(fos);
		fis.close();
		fos.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
			 if (br != null) br.close();
			 if (fr != null) fr.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	
		
	}
	

}
