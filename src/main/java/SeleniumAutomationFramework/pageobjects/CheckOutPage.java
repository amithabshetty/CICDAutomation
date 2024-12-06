package SeleniumAutomationFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumAutomationFramework.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{
	
	WebDriver driver;
	//Constructor is the first method to execute in the class. Best place to write initiation code
	public CheckOutPage(WebDriver driver) {
		
		super(driver);//sends variable value to parent class
		//Initialization
		this.driver=driver; //refers to local class instance variable which is the driver declared outside this construtor
		PageFactory.initElements(driver, this);//initiating driver details for pagefactory object declarations
		}
	
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	
	
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
			
	
	By results = By.cssSelector(".ta-results");
	
	
	public void enterCountry() {
		country.sendKeys("India");
	} 
	
	public void SelectCountry(String countryName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();	
		
	}
 	
	public ConfirmationPage submitOrder() {
		submit.click();
		ConfirmationPage confirmPage = new ConfirmationPage(driver);
		return confirmPage;
			
	}
}
