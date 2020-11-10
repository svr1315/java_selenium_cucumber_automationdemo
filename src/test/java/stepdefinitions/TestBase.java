package stepdefinitions;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase(){
		try{
			prop=new Properties();
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/test/java/config/config.properties");
			prop.load(fis);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
    public static void beforeScenario(){
    	try{
		String browserName=prop.getProperty("browser");
        System.out.println("*****TEST SETUP********");  
        if(browserName.equals("chrome")){
        ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-popup-blocking");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe");
		driver=new ChromeDriver(options);
        }
        else if(browserName.equals("FF")){
        	System.setProperty("webdriver.gekco.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\gekcodriver.exe");
        	driver=new FirefoxDriver();
        }
        else if(browserName.equals("IE")){
        	System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\internetexplorerdriver.exe");
        	driver=new InternetExplorerDriver();
        }       
        
        
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("baseurl"));
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
    	}
    	catch(NullPointerException e){
    		System.out.println("******Please select valid BROWSER and BASE URL******");
    		return;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		
    	}
    }
    	
	
	
    public static void afterScenario(){
        System.out.println("*****TEAR DOWN**********");
        driver.close();
    }
}