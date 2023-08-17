package businessComponents;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.objects.HomePage;

public class BusinessLogic extends SetUp {
	
	String objective="./DataFiles.xlsx";
	
	static WebDriverWait wait;
	
	HomePage homePage=new HomePage();
	
	public static void clickLocation()
	{
		System.out.println("***********Click On Location*******");
		driver.findElement(By.xpath(HomePage.location)).click();
		
	}
	
	
	public static void clickDestination()
	{
		System.out.println("***********Click Destination*******");
		String xpath="//div[@class='filter-toggle-button-group']//button[contains(text(),'Destinations')]";
		explicitWait(xpath,20);
		driver.findElement(By.xpath(xpath)).click();;
	}
	public static void explicitWait(String xpath,int second)
	{
		System.out.println("***********Explicit Wait Call*******");
		wait=new WebDriverWait(driver,Duration.ofSeconds(second));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		
	}
	
	public static void takeScreenshot(int n)
	{
		try {
		System.out.println("***********Take Screenshot*******");
		
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyyhhmmss");
		String dateformat=sdf.format(date);
		System.out.println(dateformat);
		
		//Convert web driver object to TakeScreenshot
		TakesScreenshot screenshot=((TakesScreenshot)driver);
		
		//Call getScreenshotAs method to create image file
		File srcFile=screenshot.getScreenshotAs(OutputType.FILE);
	
		//Move image file to new destination
		File destFile=new File(".\\ObjectiveEvidences\\"+n+dateformat+".jpg");
		
	    FileUtils.copyFile(srcFile, destFile);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	public static void selectRegion()
	{
		try {
			System.out.println("***********Select Region*******");
		String ele="//select[@id='dest-region']";
		explicitWait(ele,20);
		Select select=new Select(driver.findElement(By.xpath(ele)));
		select.selectByVisibleText("Europe");
		List<WebElement> list=select.getOptions();
		System.out.println("Drop Down options are :-");
		for(WebElement options:list)
		{
			System.out.println(options.getText());
		}
		Thread.sleep(3000);
		}
		catch(Exception e)
		{
			System.out.println("Region not selected");
			e.printStackTrace();
		}
	}

}
