package SeleniumAutomation.Tests;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SeleniumAutomationFramework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	
	
	public static void main(String[] args) {
		//Webdriver manager brings in chromedriver download automatically based on chrome browser version, no additional step or download required
		String productName =  "Zara Coat 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
			
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	
		
		driver.findElement(By.id("userEmail")).sendKeys("ami.selenium@abc.com");
		driver.findElement(By.id("userPassword")).sendKeys("Selenium123");
		driver.findElement(By.name("login")).click();	
			
		//Explicit wait
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		System.out.println(products);
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		System.out.println("selected "+prod);
		//Add to Cart
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		//wait for toaster message "Product added to cart successfully"
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//Wait for screen loading icon to disappear//appears after adding prouct to cart
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng_animating")));
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng_animating"))));
		
		
		//Click on view cart using custom CSS -> attribute*=value (reg exp)
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//Get the list of items added to cart to verify if added item is available in the cart
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3")); //parent child traverse using tag name for CSS
		
		//if we need to retrieve the matching element use filter, if all we need is match or not use anyMatch method
		Boolean match= (cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName)));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("India");
		//wait for options to load and Select option 'India'
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		//Click on Place order
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();
		
		
		
		
		
		
		
		
		
		
		
	}

}
