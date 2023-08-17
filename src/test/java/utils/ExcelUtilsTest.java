package utils;

public class ExcelUtilsTest {
	public static void main(String[] args) {
		String excelPath="./DataFiles.xlsx";
		String sheetName="sheet1";
		System.out.println("****Read Values to Excel*****");
		ExcelUtils excel=new ExcelUtils(excelPath,sheetName);
		excel.getRowCount();
		excel.getCell(1, 0);
		//write values to excel
		WriteExcel wexcel=new WriteExcel(excelPath,sheetName);
		wexcel.writeCellValue(1,2,"Indrajeet",excelPath);
	}

}
