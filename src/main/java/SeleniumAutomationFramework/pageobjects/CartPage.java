package SeleniumAutomationFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumAutomationFramework.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	
	WebDriver driver;
	//Constructor is the first method to execute in the class. Best place to write initiation code
public CartPage(WebDriver driver) {
		
		super(driver);//sends variable value to parent class
		//Initialization
		this.driver=driver; //refers to local class instance variable which is the driver declared outside this construtor
		PageFactory.initElements(driver, this);//initiating driver details for pagefactory object declarations
		}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css =".totalRow button")
	WebElement checkout;
	
			
	public Boolean VerifyProductDisplay(String productName) {
		Boolean match= (cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName)));
		return match;			
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	public CheckOutPage checkout() {
		
		checkout.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
		
		
	}
	

}
