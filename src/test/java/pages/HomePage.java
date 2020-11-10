package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import stepdefinitions.TestBase;

public class HomePage extends TestBase{
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
		 }
	
	@FindBy(how = How.XPATH, using = "//*[starts-with(@id,'sp_message_iframe_')]") 
	 private WebElement iframeMessageContainer;
	
	@FindBy(how = How.XPATH, using = "//div[@class='message-container gu-overlay']/div[3]") 
	 private WebElement element;
	
	@FindBy(how = How.XPATH, using = "//button[@title='Yes, I’m happy']") 
	 private WebElement IamHappyBtn;
	
	@FindBy(how = How.XPATH, using = "(//a[@class='pillar-link pillar-link--News'])[2]") 
	 private WebElement element2;
	
		
	

	public void moveToIframeOnHomePage() {
		driver.switchTo().frame(iframeMessageContainer);
		
	}
	
	public void clickIAmHappyButton(){
		IamHappyBtn.click();
	}
	
	
	public void scrollIntoViewOfElement() {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();",element );	
		
	}
	
	public void clickOnElement2() {
		JavascriptExecutor jse = (JavascriptExecutor) driver; 
		jse.executeScript("arguments[0].scrollIntoView();",element2 );
		element2.click();
		
	}
	
}
