package SeleniumAutomationFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumAutomationFramework.AbstractComponents.AbstractComponents;

public class ProductCatologue extends AbstractComponents{
	
	WebDriver driver;
	//Constructor is the first method to execute in the class. Best place to write initiation code
	public ProductCatologue(WebDriver driver) {
		
		//All child classes should define super 
		super(driver);
		//Initialization
		this.driver=driver; //refers to local class instance variable which is the driver declared outside this construtor
		PageFactory.initElements(driver, this);//initiating driver details for pagefactory object declarations
		
		
	}
	

	//Write the above lines in pagefactory model
	//List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css= ".ng_animating")
	WebElement msgSpinner;
	
	By productsBy=By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	
		public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
				
			}

	public WebElement getProductByName(String productName) {		
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		//WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		return prod;
  
		}
		
		public void addProductToCart(String productName){
			WebElement prod=getProductByName(productName); //pageFactory cannot be applied to webElement, it can be applied at driver level
			prod.findElement(addToCart).click(); 
			waitForElementToAppear(toastMessage);
			waitForElementToDisappear(msgSpinner);
			
		}

	
	
	
	


}