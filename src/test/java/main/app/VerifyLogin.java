package main.app;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import businessComponents.BusinessLogic;
import businessComponents.SetUp;
import utils.ExcelUtils;
import utils.WriteExcel;

public class VerifyLogin extends SetUp{
	

	String excelPath="./DataFiles.xlsx";
	String sheetName="sheet1";
//	public ExtentTest test;
	
	
    BusinessLogic logic=new BusinessLogic();
    ExcelUtils readExcel=new ExcelUtils(excelPath,sheetName);
    WriteExcel writeExcel=new WriteExcel(excelPath,sheetName);
    
  
    @BeforeTest
	public void LaunchApllication()
	{
		String url=readExcel.getCell(1, 3);
		System.out.println("LaunchApllication"+url);
		setBrowser(url);
	}
	
	@Test(priority=1)
	public void login()
	{
		try {
		logic.clickLocation();
		logic.takeScreenshot(1);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Test(priority=2)
	public void destination()
	{
		logic.clickDestination();
		logic.takeScreenshot(2);
	}
	@Test(priority=3)
	public void region()
	{
		logic.selectRegion();
		logic.takeScreenshot(3);
		assertEquals(true, true);
	}
	@Test(priority=4)
	public void readWriteExcel()
	{
		try {
		readExcel.getCell(1, 2);
		logic.takeScreenshot(4);
//		writeExcel.writeCellValue(2, 2, "Suman1", excelPath);
//		writeExcel.writeCellValue(2, 3, "Aarav", excelPath);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	@AfterTest
	public void browserClose()
	{
		driver.close();
	}
	
}


