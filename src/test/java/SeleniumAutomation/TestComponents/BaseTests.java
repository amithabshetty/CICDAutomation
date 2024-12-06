package SeleniumAutomation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumAutomationFramework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTests{
	public WebDriver driver;
	public LandingPage landingPage; //to make it accessible to other classes
	
	public WebDriver initializeDriver() throws IOException{
		
		//properties class
		Properties prop = new Properties();
		//FileInputStream fis=new FileInputStream("C:/Users/amith/OneDrive/Documents/AmithaEclipseNew/SeleniumFrameworkDesign/src/main/java/SeleniumAutomationFramework/resources/GlobalData.properties"); //converts file into inputstream object
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/SeleniumAutomationFramework/resources/GlobalData.properties");
		prop.load(fis); //will parse properties file and extract all key value pairs from it, needs file as input stream
		String browserName=prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		}
		
		if(browserName.equalsIgnoreCase("firefox"))
		{
		//firefox setup
		}
		
		
		if(browserName.equalsIgnoreCase("edge"))
		{
		System.setProperty("webdriver.edge.driver", "<<exe path>>");
		driver=new EdgeDriver();
				}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;
		
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		 //Read Json file through FileUtility package
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//Convert String to Hashmap - Dependency Jackson Databind to convert String to Hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
	//data will be a Hashmap now with 2 maps , (map,map}
		return data;
		
		
	}

	
	//Utility to take screenshot
	
	public String getScreenshot(String testcaseName,WebDriver driver) throws IOException {
		//First cast driver to screenshot mode - driver has control of browser
		TakesScreenshot ts= (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);//output in file format, it can also be saved BYTES< BASE65>
		//save source in local 
		//Create file object to send as parameter to copyFile method to copy the file in local directory
		File file = new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
		}
	
	
	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;	
	}
	
	@AfterMethod
		public void TearDown() {
		System.out.println("closing driver");
		driver.close();
		
		
	}
}
