package listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import businessComponents.SetUp;

public class Listeners extends SetUp implements ITestListener  {
	
	public ExtentReports extent;//specify the location of report
	public ExtentTest test;//what details should be populated in the report
	public ExtentSparkReporter spark;
	public String screenshotPath;
	
//	WebDriver driver;
	
	
	public void configureReport()
	{
		extent = new ExtentReports();
		spark=new ExtentSparkReporter("ExtentReportResults.html");
		System.out.println("ExtentReportResults.html");
		extent.attachReporter(spark);
		extent.setSystemInfo("Host Name", "Local Host");
		extent.setSystemInfo("Environment", "QA");
		spark.config().setDocumentTitle("Daily Smoke Report");//Name of the report
		spark.config().setReportName("Smoke Report");
		spark.config().setTheme(Theme.DARK);
	}
	
	public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
		}
	public void getReport(ITestResult result)
	{
		test=extent.createTest(result.getName());
		if(result.getStatus()==ITestResult.FAILURE)
		{
			//MarkupHelper is used to display the output in different colors
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"- Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable()+"- Test Case Failed", ExtentColor.RED));
			//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
			//We do pass the path captured by this method in to the extent reports using "logger.addScreenCapture" method.
			//String Scrnshot=TakeScreenshot.captuerScreenshot(driver,"TestCaseFailed");
			try {
				screenshotPath=getScreenShot(driver,result.getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			test.fail("Test Case Failed Snapshot is below "+test.addScreenCaptureFromPath(screenshotPath));
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			test.log(Status.SKIP,MarkupHelper.createLabel(result.getName()+" - Test Case Skipped", ExtentColor.ORANGE));
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.log(Status.PASS,MarkupHelper.createLabel(result.getName()+" - Test Case PASSED", ExtentColor.GREEN));
		}
	}
	public void generateReport()
	{
		extent.flush();
	}
	
	public void onStart(ITestContext tr)
	{
		configureReport();
		System.out.println("On Start method invoked");
	}
	public void onFinish(ITestContext tr)
	{
		System.out.println("On finish method invoked");
		generateReport();
	}
	
	public void onTestStart(ITestResult tr)
	{
		System.out.println("Test Started"+tr.getName());
	}
	public void onTestSuccess(ITestResult tr)
	{
		System.out.println("Test Passed"+tr.getName());
		getReport(tr);
//		test=extent.createTest(tr.getName());
//		test.log(Status.PASS, MarkupHelper.createLabel("Name of the failed test is: "+tr.getName(), ExtentColor.GREEN));
	}
	public void onTestFailure(ITestResult tr)
	{
		System.out.println("Test Failed"+tr.getName());
		getReport(tr);
//		test=extent.createTest("Test");
//		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test is: "+tr.getName(), ExtentColor.RED));
	}
	public void onTestSkipped(ITestResult tr)
	{
		System.out.println("Test Skipped"+tr.getName());
		getReport(tr);
//		test=extent.createTest(tr.getName());
//		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the failed test is: "+tr.getName(), ExtentColor.YELLOW));
	}
    	
}
