
package SeleniumAutomation.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumAutomation.TestComponents.BaseTests;
import SeleniumAutomationFramework.pageobjects.CartPage;
import SeleniumAutomationFramework.pageobjects.CheckOutPage;
import SeleniumAutomationFramework.pageobjects.ConfirmationPage;
import SeleniumAutomationFramework.pageobjects.LandingPage;
import SeleniumAutomationFramework.pageobjects.OrderPage;
import SeleniumAutomationFramework.pageobjects.ProductCatologue;

public class SubmitOrderTest extends BaseTests {
	
	String productName =  "ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	
	// data provider with Object array -> public void submitOrder(String email, String password, String productName) throws IOException{
		
		public void submitOrder(HashMap<String,String>input) throws IOException{
		//Webdriver manager brings in chromedriver download automatically based on chrome browser version, no additional step or download required
		
		LandingPage landingPage = launchApplication();
		//ProductCatologue productCatologue = landingPage.loginApplication("ami.selenium@abc.com","Selenium123");
		ProductCatologue productCatologue = landingPage.loginApplication(input.get("email"),input.get("password"));
		System.out.println(input.get("email"));
		
		List<WebElement> products = productCatologue.getProductList();
		productCatologue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatologue.goToCartPage();
		//Cart Page
		//Get the list of items added to cart and  verify if added item is available in the cart
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		//go to checkout
		CheckOutPage checkoutpage = cartPage.checkout();
		checkoutpage.SelectCountry("India");
		ConfirmationPage confirmPage = checkoutpage.submitOrder();
		
		//Checkout Page -//enter Country;
		 String confirmMessage = confirmPage.getMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println(confirmMessage);
			}

	
	//Verify if submitted order is in Orders page
	
	@Test(dependsOnMethods= {"submitOrder"})//will run only if submitOrder test is run -  - Dependency Tests
	
	public void OrdersHistoryTest() {
		
		ProductCatologue productCatologue = landingPage.loginApplication("amisselenium@abc.com","Selenium123");
		OrderPage ordersPage = productCatologue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		
		} 


		/*
		 * @DataProvider //provides data to all tests with arrays, pass this as
		 * parameters to the calling method public Object[] getData() {
		 * 
		 * return new Object[][] {{"ami.selenium@abc.com","Selenium123","ZARA COAT 3"},
		 * {"samy.selenium@abc.com","Password123","ADIDAS ORIGINAL"}}; //you can also
		 * create String array, integer array , Object array can have any type of data
		 * 
		 * }
		 */


		@DataProvider //provides data to all tests with Hashmap(kay value pair)
		public Object[][] getData() 
		{
			
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("email","ami.selenium@abc.com");
			map.put("password","Selenium123");
			map.put("product", "ZARA COAT 3");
			
			
			HashMap<String,String> map1 = new HashMap<String,String>();
			map1.put("email","samy.selenium@abc.com");
			map1.put("password","Password");
			map1.put("product", "ADIDAS ORIGINAL");
			
			return new Object[][] {{map},{map1}};
					
		}

}
