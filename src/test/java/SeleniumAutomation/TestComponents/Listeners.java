package SeleniumAutomation.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SeleniumAutomationFramework.resources.ExtentReporterNG;

public class Listeners extends BaseTests implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();//Thread Safe
	
	@Override
	public void onTestStart(ITestResult result) {
		
	test = extent.createTest(result.getMethod().getMethodName());
	extentTest.set(test);
	}
	
	
	@Override
	public void onTestSuccess(ITestResult result) {
	test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
	extentTest.get().fail(result.getThrowable());
	try {
		
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());//referrring to class here and not method because objects have reference to class and not method
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//Screenshot
	String filePath = null;
	try {
		filePath = getScreenshot(result.getMethod().getMethodName(),driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	}
	
	@Override
	public void onFinish(ITestContext context) {
	extent.flush();
	
	}
	
}