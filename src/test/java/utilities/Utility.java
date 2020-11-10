package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.collect.ArrayListMultimap;

/***This class is used for writing any utility classes***/
public class Utility{ 
	
	
	static List<String> fakeWebSites=new ArrayList<>();
	static List<String> genuineWebSites=new ArrayList<>();
	static String[] samplefakewebsites = {"ABCnews.com.co","bients.com","Breaking-CNN.com","CBSnews.com.co"};
	static List<String> samplefakewebsitesList = Arrays.asList( samplefakewebsites );
    	
    	public static List<String> getUrls(WebDriver driver) {
            
            List<String> urls = new ArrayList<>();
            
            List<WebElement> elementList = driver.findElements(By.tagName("a"));
            
            elementList.addAll(driver.findElements(By.tagName("img")));

            for (WebElement element : elementList) {
                
                String link = element.getTagName().equalsIgnoreCase("a") ? element.getAttribute("href")
                        : element.getAttribute("src");
                
                if (link != null && (link.startsWith("http://") || link.startsWith("https://"))) {
                    urls.add(link);
                }
            }
            return urls;
    	}


//    	public static List<String> getFakeURLsList(List<String> samples){
//    		 List<String> fakesampleurllist = new ArrayList<>();    	
//    		String request_uri="";
//    	
//    		for(int i=0;i<samples.size();i++){
//    			if (samples.get(i).toString().startsWith("http://")){
//    		    request_uri = samples.get(i).substring(7).split("/")[0];
//    			} 
//    			else if(samples.get(i).toString().startsWith("https://")){
//    			request_uri = samples.get(i).substring(8).split("/")[0];
//    			}
//
//    		    System.out.println ("Request_URI:"+request_uri);
//    		    for(String s: samplefakewebsitesList){
//    			    if(request_uri.contentEquals(s)){
//    				fakeWebSites.add(samples.get(i).toString());
//    			     }   			
//    		
//    		    }   		
//    		
//    		}
//			return fakesampleurllist;
//    	}   	
//    	
}
    	
    	
    	

    


