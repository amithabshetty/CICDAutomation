package SeleniumAutomaiton.StepDefinitionFiles;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumAutomation.TestComponents.BaseTests;
import SeleniumAutomationFramework.pageobjects.CartPage;
import SeleniumAutomationFramework.pageobjects.CheckOutPage;
import SeleniumAutomationFramework.pageobjects.ConfirmationPage;
import SeleniumAutomationFramework.pageobjects.LandingPage;
import SeleniumAutomationFramework.pageobjects.ProductCatologue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefinition extends BaseTests {
	
	public LandingPage landingPage;
	public ProductCatologue productCatologue;
	public ConfirmationPage confirmPage;
	
	@Given("I landed on eCommerce Page")
	public void I_landed_on_eCommerce_Page() throws IOException
	{
		
		landingPage = launchApplication();
	}

	
	 @Given("^Logged in with username(.+) and password(.+)$") //reg exp for input data
	 	
	 public void Logged_in_with_username_and_password(String username, String password) 
	 {
		System.out.println(username+","+""+password);
		 productCatologue = landingPage.loginApplication(username,password);
	 }
	 
	@When("^I add product (.+) to cart$")
	 public void I_add_product_to_cart(String productName) 
	 {
		List<WebElement> products = productCatologue.getProductList();
		productCatologue.addProductToCart(productName);
		 
	 }
	 
	@When("^checkout product (.+) and submit the order$")
	public void checkout_product_and_submit_the_order(String productName){
		CartPage cartPage = productCatologue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartPage.checkout();
		checkoutpage.SelectCountry("India");
		confirmPage = checkoutpage.submitOrder();
		
	}
    @Then("{string} message should be displayed on Confirmation Page")
	public void message_should_be_displayed_on_Confirmation_Page(String string) {
    	
    	String confirmMessage = confirmPage.getMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
    }
	 }
