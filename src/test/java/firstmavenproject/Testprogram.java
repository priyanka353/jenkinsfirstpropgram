package firstmavenproject;

import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Testprogram 
{
    WebDriver driver;
    @BeforeTest
    public void setUp() {
    	 System.setProperty("webdriver.chrome.driver", "E:\\priyanka\\Automation Setup file\\chromedriver_win32\\chromedriver.exe");
	    driver=new ChromeDriver();
		driver.get("https://www.dhlsmartrucking.com");
		driver.manage().window().maximize();
    }
 
    @Test
    public void testLinks() throws Exception 
    {
 
        //Fetch all hyper links those contains HREF attributes
        List<WebElement> links = driver.findElements(By.tagName("a"));
 
        System.out.println("Total number of hyper links on webpage : " + links.size());
 
        // Validate all URLS one by one using For loop
        for(int i = 0 ; i < links.size(); i++) 
        {
            try {
                System.out.println("URL: " + links.get(i).getAttribute("href") + " - Result : " + isLinkBroken(new URL(links.get(i).getAttribute("href"))));
 
                } 
            catch(Exception e) 
            {
 
                    System.out.println("Error at " + links.get(i).getAttribute("innerHTML") + " Exception occured : " + e.getMessage());
                }
            }
        }
 
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
 
      public List<WebElement> fetchAllLinks(WebDriver driver) 
      {
 
          List<WebElement> list = new ArrayList<WebElement>();
 
          //Find all elements with anchor tag and store it into a list
          list = driver.findElements(By.xpath("//ul[@class='l-grid l-grid--w-auto-s js--nav-list']"));
 
          List<WebElement> finalList = new ArrayList<WebElement>(); ;
 
          //Now scan all hyper links in order to filter the ones those are without href attribute and save elements into FinalList
          for (int i = 0 ; i<list.size(); i++) {
 
              if(list.get(i).getAttribute("href") != null) {
 
                  finalList.add(list.get(i));
              }
          }
 
          return finalList;
 
      }
 
        public String isLinkBroken(URL url) throws Exception {
 
            String response = " ";
 
            //Create an instance of HTTP URL connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            connection.setConnectTimeout(3000);
            //connect to the URL
            connection.connect();
 
            // get the response
            response = connection.getResponseMessage();
 
            // Disconnect the connection
             connection.disconnect();
 
             return response;
 
            }
 
    }