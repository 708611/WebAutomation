package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	public ExcelUtils(String excelPath, String sheetName)
	{
		try {
			workbook=new XSSFWorkbook(excelPath);
			sheet=workbook.getSheet(sheetName);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static String getCell(int rowNum, int colNum)
	{
		System.out.println("****Read Values to Excel*****");
		DataFormatter formatter=new DataFormatter();
		Object value=formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
		System.out.println(value);
		String str=value.toString();
		return str;
	}
	public static void getRowCount()
	{
		System.out.println("****Read Row Count to Excel*****");
		int rowCount=sheet.getPhysicalNumberOfRows();
		System.out.println("Rows count is "+rowCount);
	}
	
	

}
