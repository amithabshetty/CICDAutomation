package SeleniumAutomationFramework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumAutomationFramework.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	//Constructor is the first method to execute in the class. Best place to write initiation code
	public LandingPage(WebDriver driver) {
		
		super(driver);//sends variable value to parent class
		//Initialization
		this.driver=driver; //refers to local class instance variable which is the driver declared outside this construtor
		PageFactory.initElements(driver, this);//initiating driver details for pagefactory object declarations
		
		
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//WebElement Password = driver.findElement(By.id("userPassword"));
	//WebElement loginButton = driver.findElement(By.name("login"));	
	
	//Write the above lines in pagefactory model
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement Password;
	
	
	@FindBy(name="login")
	WebElement loginButton; 
	
	//login error toaster message
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	public ProductCatologue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		Password.sendKeys(password);
		loginButton.click();
		//Creating object for new page here itself
		ProductCatologue productCatologue = new ProductCatologue(driver);
		return productCatologue;
				
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	

}
