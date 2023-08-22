package main.app;

import static org.testng.Assert.assertEquals;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import businessComponents.*;
import utils.ExcelUtils;
import utils.WriteExcel;

public class MainApp extends SetUp {
	
	
	String excelPath="./DataFiles.xlsx";
	String sheetName="sheet1";
//	public ExtentTest test;
	
	
    BusinessLogic logic=new BusinessLogic();
    ExcelUtils readExcel=new ExcelUtils(excelPath,sheetName);
    WriteExcel writeExcel=new WriteExcel(excelPath,sheetName);
    
  
    @BeforeMethod
	public void LaunchApllication()
	{
		String url=readExcel.getCell(1, 3);
		System.out.println("LaunchApllication"+url);
		setBrowser(url);
	}
	
	@Test(priority=1)
	public void VerifyLogFunctionality()
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
		logic.clickLocation();
		logic.clickDestination();
		logic.takeScreenshot(2);
	}
	@Test(priority=3)
	public void region()
	{ logic.clickLocation();
	   logic.clickDestination();
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
	
	@AfterMethod
	public void browserClose()
	{
		driver.close();
	}
	
}
