package SeleniumAutomationFramework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() { //static methods can be accessed without declaring object of the class, directly as class name.method name
		
		//String path = System.getProperty("User.dir")+"//reports//index.html";
		String path = System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Test Automation Tests");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Amitha Shetty");
		//create entry in reports for test
		extent.createTest(path);//instead of writing this in every test it can be handled through TestNG Listener
		return extent;
	}

}
