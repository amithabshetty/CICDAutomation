package SeleniumAutomationFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumAutomationFramework.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{
	
	WebDriver driver;
	//Constructor is the first method to execute in the class. Best place to write initiation code
	public ConfirmationPage(WebDriver driver) {
		
		super(driver);//sends variable value to parent class
		//Initialization
		this.driver=driver; //refers to local class instance variable which is the driver declared outside this construtor
		PageFactory.initElements(driver, this);//initiating driver details for pagefactory object declarations
		}
	
	@FindBy(css=".hero-primary")
	WebElement message;
	
	public String getMessage() {
		String confirmMessage = message.getText();
		return confirmMessage;
		
	}
	

}
