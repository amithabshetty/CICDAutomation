
package SeleniumAutomation.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumAutomation.TestComponents.BaseTests;
import SeleniumAutomation.TestComponents.Retry;
import SeleniumAutomationFramework.pageobjects.CartPage;
import SeleniumAutomationFramework.pageobjects.LandingPage;
import SeleniumAutomationFramework.pageobjects.ProductCatologue;

public class ErrorValidationsTest extends BaseTests {
	
	
	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	
	public void LoginErrorValidation() throws IOException{
		
			
		landingPage.loginApplication("ami.selenium@abc.com","Selenium1223");
		Assert.assertEquals("Incorrect email orr password.",landingPage.getErrorMessage());
		
		
	}
	
	@Test

	public void ProductErrorValidation() throws IOException{
		//Webdriver manager brings in chromedriver download automatically based on chrome browser version, no additional step or download required
		String productName =  "ZARA COAT 3";
		ProductCatologue productCatologue = landingPage.loginApplication("ami.selenium@abc.com","Selenium123");
		List<WebElement> products = productCatologue.getProductList();
		productCatologue.addProductToCart(productName);
		CartPage cartPage = productCatologue.goToCartPage();
		
		
		//Cart Page
		//Get the list of items added to cart and  verify if added item is available in the cart
		Boolean match = cartPage.VerifyProductDisplay("Zara Coat333");
		Assert.assertFalse(match);
			//System.out.print
		
			
	
}
}