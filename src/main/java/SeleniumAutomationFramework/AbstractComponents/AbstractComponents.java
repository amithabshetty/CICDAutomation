package SeleniumAutomationFramework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumAutomationFramework.pageobjects.CartPage;
import SeleniumAutomationFramework.pageobjects.OrderPage;

public class AbstractComponents {
	
WebDriver driver;
	//Constructor is the first method to execute in the class. Best place to write initiation code
	public AbstractComponents(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);//initiating driver details for pagefactory object declarations
		
	}
	
	
	public void waitForElementToAppear(By findBy) {
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	
	public void waitForWebElementToAppear(WebElement findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));

		}
		
	
	public void waitForElementToDisappear(WebElement element) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));

}

	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	@FindBy(css = "[routerlink*='cart']" )
	WebElement cartHeader;	
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersHeader;
	
	public CartPage goToCartPage() {
	cartHeader.click();
	CartPage cartPage = new CartPage(driver);
	System.out.println("Cart Page");
	return cartPage;
				}
		
	
	public OrderPage goToOrdersPage() {
		
		ordersHeader.click();
		OrderPage orderPage =  new OrderPage(driver);
		System.out.println("Orders Page");
		return orderPage;
				
	}
}