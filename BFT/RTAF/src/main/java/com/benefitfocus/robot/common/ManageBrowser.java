package com.benefitfocus.robot.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.benefitfocus.robot.utils.CustomException;

@RobotKeywords
public class ManageBrowser {

    public static WebDriver webdriver;
    public String configurations[];

    @Autowired
    protected Logging logger;

    // Hashmap for runtime variables
    public Map<String, String> hMap = new HashMap<String, String>();

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * Keyword 'launcherBrowser' used to open required browser based on robot
     * test input parameters
     * <p/>
     * [] String array input being passed from Robot-tests environment variables
     * <p/>
     * <pre>
     * <b>Parameters :</b>
     * | *configs |
     * |@{EESHREL / @{EESHDEV} / @{EESHPATCH} |
     * | Application name - eEnrollment | Application URL - http://eeshrel/go/bfi | browserType - FF / firefox / IE / chrome / safari |
     * </pre>
     */
    @RobotKeyword
    @ArgumentNames({"*configs"})
    public void launchBrowser(String... configs) {
        // Configuration variables
        configurations = configs;

        String browserType = "ff";
        //System.out.println("configurations.length : " + configurations.length);
        String application = configurations[0];
        String url = configurations[1];
        browserType = configurations[configurations.length - 1];

        logger.info("application : " + application);
        logger.info("url : " + url);
        logger.info("browserType : " + browserType);

        try {
            if (browserType.equalsIgnoreCase("IE8")
                    || browserType.equalsIgnoreCase("IE6")
                    || browserType.equalsIgnoreCase("IE")) {
                System.setProperty("webdriver.ie.driver",
                        System.getProperty("user.dir") + System.getProperty("file.separator") + "IEDriverServer.exe");
                webdriver = new InternetExplorerDriver();
            } else if (browserType.equalsIgnoreCase("firefox")
                    || browserType.equalsIgnoreCase("ff")) {
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.setEnableNativeEvents(true);
                webdriver = new FirefoxDriver(firefoxProfile);
            } else if (browserType.equalsIgnoreCase("chrome")) {
                if (System.getProperty("os.name").startsWith("Windows")) {
                    System.setProperty("webdriver.chrome.driver",
                            System.getProperty("user.dir") + System.getProperty("file.separator") + "chromedriver.exe");
                    webdriver = new ChromeDriver();
                } else {
                    System.setProperty("webdriver.chrome.driver",
                            System.getProperty("user.dir") + System.getProperty("file.separator") + "chromedriver");
                    webdriver = new ChromeDriver();
                }
            } else if (browserType.equalsIgnoreCase("safari")) {
                webdriver = new SafariDriver();
            } else if (browserType.equalsIgnoreCase("opera")) {
                // TODO:webdriver = new OperaDriver();
            } else if (browserType.equalsIgnoreCase("html")) {
                //TODO:webdriver = new WebDriver(true);
            } else {
                System.out
                        .println(" Can't find the desired browser \n Using firefox");
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.setEnableNativeEvents(true);
                webdriver = new FirefoxDriver(firefoxProfile);
            }

            // Set the timeout
            webdriver.manage().window().maximize();
            webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // Open the application
            webdriver.get(url);
        } catch (Exception e) {
            System.out.println("Can't Create Driver Object.");
            throw new CustomException("Can't Create Driver Object." + browserType + e.getMessage());
            // System.exit(0);
        }
    }

    /**
     * Keyword used to verify the browser is available or not
     */
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

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * Reload the browser with the application
     */
    @RobotKeyword
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

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * return the current webdriver object
     */
    @RobotKeyword
    public WebDriver getCurrentWebDriver() {
        return webdriver;
    }

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * Keyword used to close the browser window
     */
    @RobotKeyword
    public void closeBrowser() {
        webdriver.quit();
    }
}
