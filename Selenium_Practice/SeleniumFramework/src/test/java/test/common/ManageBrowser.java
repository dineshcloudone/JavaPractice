package test.common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.asserts.LoggingAssert;

import test.common.*;
import test.utils.CustomException;

public class ManageBrowser {
	
	public static WebDriver webdriver;
	public String configurations[];
		
	public void launchBrowserAndAppication(String... configs) {
		
	        // Configuration variables
	        configurations = configs;

	        String browserType = configurations[0];
	        //System.out.println("configurations.length : " + configurations.length);
	        //String application = configurations[0];
	        String url = configurations[1];
	        //browserType = configurations[configurations.length - 1];

	        //logger.info("application : " + application);
	        //logger.info("url : " + url);
	        //logger.info("browserType : " + browserType);

	        try {
	            if (browserType.equalsIgnoreCase("IE")) {
	                System.setProperty("webdriver.ie.driver",
	                        System.getProperty("user.dir") + System.getProperty("file.separator") + "IEDriverServer.exe");
	                webdriver = new InternetExplorerDriver();
	            } else if (browserType.equalsIgnoreCase("firefox")) {
	            	System.setProperty("webdriver.gecko.driver",
	            		System.getProperty("user.dir") + System.getProperty("file.separator") + "geckodriver.exe");
	                webdriver = new FirefoxDriver();
	            } else if (browserType.equalsIgnoreCase("chrome")) {
	                if (System.getProperty("os.name").startsWith("Windows")) {
	                    System.setProperty("webdriver.chrome.driver",
	                            System.getProperty("user.dir") + System.getProperty("file.separator") + "chromedriver.exe");
	                    webdriver = new ChromeDriver();
	                } else {
	                    System.setProperty("webdriver.chrome.driver",
	                            System.getProperty("user.dir") + System.getProperty("file.separator") + "chromedriver.exe");
	                    webdriver = new ChromeDriver();
	                }
	            } else {
	                System.out.println(" Can't find the desired browser \n Using firefox");
	                System.setProperty("webdriver.gecko.driver",
	                		System.getProperty("user.dir") + System.getProperty("file.separator") + "geckodriver.exe");
	                webdriver = new FirefoxDriver();
	            }

	            // Set the timeout
	            webdriver.manage().window().maximize();
	            webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	            // Open the application
	            webdriver.navigate().to(url);//.get(url);
	        } catch (Exception e) {
	            System.out.println("Can't Create Driver Object.");
	            //throw new CustomException("Can't Create Driver Object." + browserType + e.getMessage());
	            // System.exit(0);
	        }
		
	}

	public boolean isBrowserAvailable() {
	        try {
	            webdriver.manage().window().maximize();
	            return true;
	        } catch (Exception e) {
	            /*
				 * TestLauncher.logger
				 * .info("!! Browser Exception !! Relaunching the application");
				 * this.launchBrowser(configurations);
				 */
	            return false;
	        }
	    }
	
	public void reloadApplication() {
        try {
            // Reload the application
            System.out.println("Reloading the application.. "
                    + configurations[1]);
            webdriver.get(configurations[1]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public WebDriver getCurrentWebDriver() {
        return webdriver;
    }
	
	public void closeBrowser() {
        webdriver.quit();
    }
	
	
}
