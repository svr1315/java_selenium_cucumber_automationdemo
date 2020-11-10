package stepdefinitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.HomePage;
import utilities.Utility;

public class Steps extends TestBase{

	public Steps(){
		super();
	}

	static int factCounter=0;
	static int fakeCounter=0;
	List<WebElement> myUrls=new ArrayList<WebElement>();

	static List<String> newsHeadLines=new ArrayList<String>();
	static List<WebElement> newsheadlines=new ArrayList<WebElement>();

	String[] fakewords = {"Bias","!!!","BestEver!","Amber","Disinformation","Option","Hoax"};
	String[] factwords= {"UnBias","Correct","fact","truth","confirmed"};
	List<String> fakeWordList = Arrays.asList( fakewords );
	List<String> factWordList = Arrays.asList( factwords );
	List<String> urls=new ArrayList<String>();




	@Given("User gets news from guardianchannel website")
	public void user_is_on_guardianchannel_web_page() throws InterruptedException{
		System.out.println("User is on Guardian News Channel WebPage");
		TestBase.beforeScenario();	
		System.out.println("Title:"+driver.getTitle());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
		HomePage homePage = new HomePage(driver);
		homePage.moveToIframeOnHomePage();		
		homePage.scrollIntoViewOfElement();		
		homePage.clickIAmHappyButton();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		for (int second = 0;; second++) {
			if(second >=60){
				break;
			}

			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)", ""); 

			Thread.sleep(300);
		}
		
		homePage.clickOnElement2();
		String mainWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();
		Iterator<String> iterator = allWindowHandles.iterator();        
		while (iterator.hasNext()) {
			String ChildWindow = iterator.next();
			if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				System.out.println("Page URL of child window " + driver.getCurrentUrl());				
				newsheadlines=driver.findElements(By.xpath("//*[@id='headlines']/div/div/div[2]/ul/li[3]/ul/li"));
				System.out.println("headlines"+newsheadlines.size());
				for(int i=0;i<newsheadlines.size();i++){
					String str=newsheadlines.get(i).getText();
					newsHeadLines.add(str);
				}                
			}
			System.out.println("Sample News Headlines:"+ newsHeadLines);
			driver.switchTo().window(mainWindowHandle);
			

		}	
	}


	@Given("^User enters the news ([^\"]*) for correctness ([^\"]*)$")
	public void verifyIfNewsIsFake(String samplenews, String website) throws NumberFormatException, InterruptedException{
		String latestNews=newsHeadLines.get(0).toString();
		System.out.println("One of the latest news:"+latestNews);
		String samplenewsupdated="\"" + samplenews.replaceAll(samplenews,latestNews) + "\"";		
		System.out.println("samplenewsupdated:"+samplenewsupdated);	
		driver.get(website+"/search?q="+samplenewsupdated);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		List<WebElement> links=driver.findElements(By.xpath("//*[@id='rso']/div"));	
		String url;	
		
		for(int i=0;i<links.size();i++){
		try
        {
            url = driver.findElement(By.xpath("//*[@id='rso']/div["+(i+1)+"]/div/div[1]/a")).getAttribute("href");
            System.out.println("URLS:"+ url);
        }
        catch (NoSuchElementException e)
        {
            url = null;
            continue;
            
        }
        if(url!=null){
        	urls.add(url);
        }
		}
	

		
			for(String st: urls){
			driver.get(st);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			WebElement body = driver.findElement(By.tagName("body"));
			String result = driver.findElement(By.tagName("body")).getText();
			System.out.println("result:"+result);

			for(String s: fakeWordList){
				if(result.contains(s)){
					fakeCounter++;
				}
			}

			for(String s:factWordList){
				if(result.contains(s)){
					factCounter++;
				}
			}

		System.out.println("Fake Counter:"+ fakeCounter);
		System.out.println("Fact Counter:"+ factCounter);

		if(factCounter>fakeCounter){
			System.out.println("News seems to be Genuine");
		}
		else if(factCounter<fakeCounter){
			System.out.println("News seems to be Fake");
		}
		else{
			System.out.println("Based on statistics.. News is neither FAKE nor Genuine.. Please check other websites for accuracy");
		}
	}
			
		}
	
	@Then("User verifies Genuinity of News")
	public void user_verifies_genuinity_of_news() {
		System.out.println("User fact checked news");
	}

	}
