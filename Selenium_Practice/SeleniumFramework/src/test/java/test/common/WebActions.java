package test.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import org.junit.//Assert;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.utils.*;

/*import org.python.jline.internal.Log;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.Autowired;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywordOverload;
import org.robotframework.javalib.annotation.RobotKeywords;
import com.benefitfocus.robot.utils.CustomException;
import com.benefitfocus.robot.utils.Screenshot;
import com.benefitfocus.robot.utils.Utilities;*/

public class WebActions extends Utils{
    
  protected Logging logger;
    
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
	
    /**
     * Author: Phani Srikar Ch
     * <p/>
     * Enter keyword or method used to perform enter operation if the
     * object/element is present
     * <p/>
     * <pre>
     * <b>Parameters :</b>
     * | locator - By class | value to enter | fieldName / description of the object for reporting |
     * </pre>
     */    
    public void enter(By locator, String value, String fieldName) {
        try {
            // ////logger.info(" Enter the field " + fieldName);
            ////value = utils.getValue(value);
            webdriver.findElement(locator).sendKeys(value);
        } catch (Exception e) {
            //logger.LogEvent("fail", e.getMessage());
            
            // ////logger.warn("Unable to enter the value in the field " +
            // fieldName);
            throw new CustomException("Unable to enter the value in the field "
                    + fieldName, locator);
        }
    }
    
    public void enter(String locator, String value, String fieldName) {
        this.enter(locatorToByObj(webdriver, locator),
                value, fieldName);
    }

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * clearEnter keyword or method used to perform clear the field and enter
     * operation if the object/element is present
     * <p/>
     * <pre>
     * <b>Parameters :</b>
     * | locator - By class | value to enter | fieldName / description of the object for reporting |
     * </pre>
     */
    public void clearEnter(By locator, String value, String fieldName) {
        try {
            //value = utils.getValue(value);
            ////logger.info(" Enter the field " + fieldName);
            webdriver.findElement(locator).clear();
            webdriver.findElement(locator).sendKeys(value);

        } catch (Exception e) {
            //scr.capturePageScreenshot();
            //////logger.warn("Unable to enter the value in the field " + fieldName);
            throw new CustomException("Unable to enter the value in the field "
                    + fieldName, locator);
        }
    }

    ////@RobotKeywordOverload
    ////@ArgumentNames({"locator", "value", "fieldName"})
    public void clearEnter(String locator, String value, String fieldName) {
        this.clearEnter(locatorToByObj(webdriver, locator),
                value, fieldName);
    }

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * Click keyword or method used to perform click operation if the
     * object/element is present
     * <p/>
     * <pre>
     * <b>Parameters :</b>
     * | locator - By class | fieldName / description of the object for reporting |
     * </pre>
     */
   /* //@RobotKeyword("Overloaded")
    //@ArgumentNames({"locator", "fieldName"})*/
    public void click(By locator, String fieldName) {
        try {
            waitUntilElementPresent(locator);
            webdriver.findElement(By.xpath("/html/body")).sendKeys(Keys.HOME);
            webdriver.findElement(By.xpath("/html/body")).sendKeys(Keys.PAGE_UP);
            waitUntilElementPresent(locator);
            /*if (isElementPresent(By.id("page-spinner"))) {
                do {
                    Thread.sleep(5000);
                    JavascriptExecutor js = (JavascriptExecutor) webdriver;
                    WebElement element = webdriver.findElement(By.id("page-spinner"));
                    js.executeScript("arguments[0].setAttribute('style', 'display:none')", element);
                } while (isElementPresent(By.id("page-spinner")));
            }*/
            /*WebDriverWait wait = new WebDriverWait(webdriver,120);
            wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));*/
            WebDriverWait wait = new WebDriverWait(webdriver, 60);
            wait.until(ExpectedConditions.elementToBeClickable(locator));

			/*JavascriptExecutor js = (JavascriptExecutor) webdriver;
            WebElement element = webdriver.findElement(locator);
            js.executeScript("arguments[0].scrollIntoView(true);", element);*/

            /*JavascriptExecutor js = (JavascriptExecutor) webdriver;
            WebElement element = webdriver.findElement(By.className("environment-banner"));
            js.executeScript("arguments[0].setAttribute('style', 'display:none')",element);
			element = webdriver.findElement(By.className("container-fluid"));
			js.executeScript("arguments[0].setAttribute('style', 'display:none')",element);*/

            //webdriver.findElement(By.xpath("/html/body")).sendKeys(Keys.HOME);

            webdriver.findElement(locator).click();
            Thread.sleep(1000);
        } catch (Exception e) {
            /*if (e.getMessage().contains("Element is not clickable at point")) {
                System.out.println("Element is not clickable at point exception");
                String lc = utils.parseException(e.getMessage());
                JavascriptExecutor js = (JavascriptExecutor) webdriver;
                WebElement element = webdriver.findElement(By.xpath(lc));
                js.executeScript("arguments[0].setAttribute('style', 'display:none')", element);
                //scr.capturePageScreenshot();
                click(locator,fieldName);
            } else {*/
                throw new CustomException("Unable to perform click on " + fieldName
                        + " \n " + e.getMessage(), locator);
            //}
        }
    }

   /* //@RobotKeywordOverload
    //@ArgumentNames({"locator", "fieldName"})*/
    public void click(String locator, String fieldName) {
        this.click(locatorToByObj(webdriver, locator),
                fieldName);
    }

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * jsclick keyword or method used to perform click operation using
     * javascript.
     * <p/>
     * <pre>
     * <b>Parameters : </b>
     * | locator - By class | fieldName - name / description of the object for reporting |
     * </pre>
     */
  /*  //@RobotKeyword("Overloaded")
    //@ArgumentNames({"locator", "fieldName"})*/
    public void jsclick(By locator, String fieldName) {
        try {

            WebElement element = webdriver.findElement(
                    locator);
            JavascriptExecutor executor = (JavascriptExecutor) webdriver;
            executor.executeScript("arguments[0].click();", element);

        } catch (Exception e) {
            //scr.capturePageScreenshot();
            // ////logger.warn("Unable to perform click on " + fieldName);
            throw new CustomException(
                    "Unable to perform click on " + fieldName, locator);
        }
    }

    //@RobotKeywordOverload
    //@ArgumentNames({"locator", "fieldName"})
    public void jsclick(String locator, String fieldName) {
        this.jsclick(locatorToByObj(webdriver, locator),
                fieldName);
    }

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * Select keyword or method used to perform click operation if the
     * object/element is present
     * <p/>
     * <pre>
     * <b>Parameters : </b>
     * | locator - By class | value to select | fieldName / description of the object for reporting |
     * </pre>
     */
    //@RobotKeyword("Overloaded")
    //@ArgumentNames({"locator", "value", "fieldName"})
    public void select(By locator, String value, String fieldName) {
        Select select = null;
        int index = -1;

        try {
            select = new Select(webdriver.findElement(
                    locator));
        } catch (Exception e) {
            /*//scr.capturePageScreenshot();
            ////logger.warn("Exception in creating '" + fieldName
                    + "' select object");
            System.out.println("Exception in creating '" + fieldName
                    + "' select object");
            System.out.println(e.getCause().getMessage());*/
            throw new CustomException("Exception in creating '" + fieldName
                    + "' select object" + e.getMessage(), locator);
        }

        if (value.toLowerCase().startsWith("js:")) {

            value = value.substring(3);
            try {

                // ////logger.info(" Selecting Dropdown value " + value
                // + " using Java Script ");
                String ln = "\n";
                String field = webdriver
                        .findElement(locator).getAttribute("id");
                System.out.println("Field ID : " + field);

                String javascript = " javascript:var s = document.getElementById('"
                        + field
                        + "');"
                        + ln
                        + "for (i = 0; i< s.options.length; i++){"
                        + ln
                        + " if (s.options[i].text.trim().toUpperCase() == '"
                        + value.toUpperCase()
                        + "'){"
                        + ln
                        + " s.options[i].selected = true;"
                        + ln
                        + " s.click();"
                        + ln
                        + " if (s.onchange) {"
                        + ln
                        + " s.onchange();"
                        + ln + " }" + ln + " break;" + ln + " }" + ln + "}";
                System.out.println("Java Script : " + javascript);
                ((JavascriptExecutor) webdriver)
                        .executeScript(javascript);
                Thread.sleep(2000);
                return;
            } catch (Exception e) {
                ////logger.warn("Exception while selecting dropdown value using javascript");
                System.out.println("Exception occured in select : "
                        + e.getMessage());

            }
        }
        try {
            if (value.equalsIgnoreCase("RND")) {
                // ////logger.info(" Selecting a RANDOM Value from dropdown ");
                int size = select.getOptions().size();
                while (index <= 1)
                    index = (int) (Math.random() * size);
            } else if (value.toLowerCase().startsWith("index=")) {
                index = Integer.parseInt(value.substring(6));
            }

            if (index != -1) {
                select.selectByIndex(index);
                // ////logger.info(" Selecting value with index " +
                // index + " from dropdown ");
            } else {
                if (value.startsWith("value=")) {
                    value = value.substring(6);
                    select.selectByValue(value);
                    // ////logger.info(" Selecting value " + value +
                    // " from dropdown ");
                } else {
                    select.selectByVisibleText(value);
                    // ////logger.info(" Selecting " + value +
                    // " from dropdown ");
                }
            }
        } catch (Exception e) {
            /*//scr.capturePageScreenshot();
            ////logger.warn("Unable to select " + value
                    + " from the drop drown in the field " + fieldName);*/
            throw new CustomException("Unable to select " + value
                    + " from the drop drown in the field " + fieldName
                    + e.getMessage(), locator);
        }
    }

    //@RobotKeywordOverload
    //@ArgumentNames({"locator", "value", "fieldName"})
    public void select(String locator, String value, String fieldName) {
        this.select(locatorToByObj(webdriver, locator),
                value, fieldName);
    }

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * 'mouseOver' keyword or method used to perform mouseover action on the
     * element
     * <p/>
     * <pre>
     * <b>Parameters : </b>
     * | locator - By class | fieldName / description of the object for reporting |
     * </pre>
     */
    //@RobotKeyword("Overloaded")
    //@ArgumentNames({"locator", "fieldName"})
    public void mouseOver(By locator, String fieldName) {
        try {
            Thread.sleep(2000);
            WebElement element = webdriver.findElement(
                    locator);
            Actions builder = new Actions(webdriver);
            builder.moveToElement(element).build().perform();

        } catch (Exception e) {
            //scr.capturePageScreenshot();
            throw new CustomException("Unable to perform mouse over on '"
                    + fieldName, locator);
        }
    }

    //@RobotKeywordOverload
    //@ArgumentNames({"locator", "value", "fieldName"})
    public void mouseOver(String locator, String fieldName) {
        mouseOver(locatorToByObj(webdriver, locator),
                fieldName);
    }

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * 'verify' keyword or method used to verify a specific values is present in
     * a partcular locator
     * <p/>
     * <pre>
     * <b>Parameters : </b>
     * | locator - By class | value to verify | fieldName / description of the object for reporting |
     *
     * <b>Example : </b>
     * | //div[@id='test'] | 20,000 | fieldName / description of the object for reporting |
     * </pre>
     */
    //@RobotKeyword("Overloaded")
    //@ArgumentNames({"locator", "value", "fieldName"})
    public void verify(By locator, String value, String fieldName) {
        try {

            String temp;

            System.out.println("locator as by class");

            if (locator == null) {
                ////logger.warn(" Unable to find Element " + fieldName);
                return;
            }

            //value = utils.getValue(value);

            String actual = "";

            String tag = webdriver.findElement(locator)
                    .getTagName();

            if (tag.equalsIgnoreCase("input")) {
                if (webdriver.findElement(locator)
                        .getAttribute("type").equalsIgnoreCase("text"))
                    actual = webdriver.findElement(locator)
                            .getAttribute("value");
            } else
                actual = webdriver.findElement(locator)
                        .getText().trim();

            String expected = value.trim();

            System.out.println("text == " + actual);
            System.out.println("value == " + expected);

            boolean res = actual.contains(expected);
            // System.out.println("res == " + res);

            if (res) {
                temp = " Value matched. Expected '" + expected + "' . Found '"
                        + actual + "'";
                System.out.println(temp);
                //scr.capturePageScreenshot();
            } else {

                actual = webdriver.findElement(locator)
                        .getAttribute("value");
                res = actual.equals(expected);
                if (res)
                    temp = " Value matched . Expected '" + expected
                            + "' . Found '" + actual + "'";
                else {
                    temp = " Value Mismatch . Expected '" + expected
                            + "' . But Found '" + actual + "'";
                    // System.out.println(temp);
                    ////logger.warn(temp);
                    throw new CustomException("Value Mismatch . Expected '"
                            + expected + "' . But Found '" + actual
                            + "' for field : " + fieldName);
                }
                //scr.capturePageScreenshot();
            }
        } catch (Exception e) {
            //scr.capturePageScreenshot();
            ////logger.warn(" Exception in executing verify on " + fieldName);
            throw new CustomException("Exception in executing verify on '"
                    + fieldName);
        }
    }

    //@RobotKeywordOverload
    public void verify(String locator, String value, String fieldName) {
        this.verify(locatorToByObj(webdriver, locator),
                value, fieldName);
    }

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * 'isElementPresent' keyword or method used to verify object/element is
     * present
     * <p/>
     * <pre>
     * <b>Parameters : </b>
     * | locator - By class | fieldName / description of the object for reporting |
     * </pre>
     */
    //@RobotKeyword("Overloaded")
    //@ArgumentNames({"locator", "fieldName"})
    public boolean isElementPresent(By locator, String fieldName) {
        try {
            // Browser.utils.sleep(2000);
            webdriver.findElement(locator);
            return true;
        } catch (Exception e) {
            // //scr.capturePageScreenshot();
            // throw new RuntimeException("Element '" + fieldName +
            // "' not found.");
            return false;
        }
    }

    //@RobotKeywordOverload
    public void isElementPresent(String locator, String fieldName) {
        isElementPresent(
                locatorToByObj(webdriver, locator),
                fieldName);
    }

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * 'isElementPresent' Overloaded keyword to verify object/element is present
     * <p/>
     * <pre>
     * <b>Parameters : </b>
     * | locator - By class |
     * </pre>
     */
    //@RobotKeyword("Overloaded")
    //@ArgumentNames({"locator"})
    public boolean isElementPresent(By locator) {
        try {
            webdriver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            ////scr.capturePageScreenshot();
            System.out.println("Element '" + locator.toString()
                    + "' not found.");
            return false;
        }
    }

    //@RobotKeywordOverload
    public void isElementPresent(String locator) {
        this.isElementPresent(locatorToByObj(webdriver,
                locator));
    }

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * 'isDisplayed' keyword or method used to verify object/element is
     * displayed or not
     * <p/>
     * <pre>
     * <b>Parameters : </b>
     * | locator - By class | fieldName / description of the object for reporting |
     * </pre>
     */
    //@RobotKeyword("Overloaded")
    //@ArgumentNames({"locator", "fieldName"})
    public boolean isDisplayed(By locator, String fieldName) {
        try {
            return webdriver.findElement(locator)
                    .isDisplayed();
        } catch (NoSuchElementException e) {
            //scr.capturePageScreenshot();
            throw new CustomException("Element '" + fieldName
                    + "' not displayed.", locator);
        }
    }

    //@RobotKeywordOverload
    public void isDisplayed(String locator, String fieldName) {
        this.isDisplayed(
                locatorToByObj(webdriver, locator),
                fieldName);
    }

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * 'isAlertPresent' keyword to verify alert is present
     */
    //@RobotKeyword
    public boolean isAlertPresent() {
        try {
            webdriver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            //scr.capturePageScreenshot();
            return false;
        }
    }

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * Close the alert window and get the text on the alert box *
     */
    //@RobotKeyword
    /*public String closeAlertAndGetItsText() {
        try {
            Alert alert = webdriver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }*/

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * 'selectLatestWindow' keyword used to select latest window opened
     * <p/>
     * <pre>
     * <b>Parameters : </b>
     * | value - any string which is uniquely available on the newly opened window |
     * </pre>
     */
    //@RobotKeyword
    //@ArgumentNames({"value"})
    public void selectLatestWindow(String value) {
        try {
            java.util.Set<String> windowHandles = webdriver
                    .getWindowHandles();
            Thread.sleep(1000);
            for (String window : windowHandles) {
                webdriver.switchTo().window(window);
                if (webdriver.getPageSource()
                        .contains(value)) {
                    webdriver.manage().window().maximize();
                    webdriver.getWindowHandle();
                    break;
                }
            }
        } catch (Throwable e) {
            //scr.capturePageScreenshot();
            System.out.println("Error :: " + e.getMessage());
        }
    }

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * 'closeLatestWindow' keyword used to close the latest window
     * <p/>
     * <pre>
     * <b>Parameters : </b>
     * | value - any string which is uniquely available on the newly opened window |
     * </pre>
     */
    //@RobotKeyword
    //@ArgumentNames({"value"})
    public void closeLatestWindow(String value) {
        try {
            java.util.Set<String> windowHandles = webdriver
                    .getWindowHandles();
            System.out.println("windowHandles" + windowHandles.size());
            Thread.sleep(1000);
            for (String window : windowHandles) {

                webdriver.switchTo().window(window);
                if (webdriver.getPageSource()
                        .contains(value)) {
                    webdriver.getWindowHandle();
                    webdriver.close();
                    break;
                }
            }
        } catch (Throwable e) {
            //scr.capturePageScreenshot();
            System.out.println("Error :: " + e.getMessage());
        }
    }

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * 'verifyMessage' keyword to verify text message is available on the page
     * <p/>
     * <pre>
     * <b>Parameters : </b>
     * | strMessage - any string which is uniquely available on the page |
     * </pre>
     */
    //@RobotKeyword
    //@ArgumentNames({"strMessage"})
    public void verifyMessage(String strMessage) {
        try {
            // Browser.utils.sleep(1000);
            //strMessage = utils.getValue(strMessage);
            boolean res = webdriver.getPageSource()
                    .toLowerCase().contains(strMessage.trim().toLowerCase());
            if (res) {
                // ////logger.info(" Verifying that Message " +
                // strMessage + " is present ");
                System.out.println("Verifying that Message " + strMessage
                        + " is present");
                //scr.capturePageScreenshot();
            } else {
                throw new RuntimeException("Message " + strMessage + " not found"
                );
                ////logger.error(" Message " + strMessage + " not found");
            }

        } catch (Exception e) {
            //scr.capturePageScreenshot();
            //logger.error(" Text :: + " + strMessage + "   :: not found");
            System.out.println("Text :: +" + strMessage + "   :: not found");
            throw new RuntimeException("Message " + strMessage + " not found"
                    + e.getMessage());
        }
    }


    /**
     * Author: Phani Srikar Ch
     * <p/>
     * Method to select a frame by it's name / index / id
     * <p/>
     * <pre>
     * <b>Parameters : </b>
     * | value - is string parameter |
     * | value - index=1 |
     * | value - //frame[@id='test'] |
     * | value - css=frameset#fs1 |
     * </pre>
     */
    //@RobotKeyword
    //@ArgumentNames({"value", "fieldName"})
/*    public void selectFrame(String value, String fieldName) {

        int index = -1;
        //utils.sleep(3000);

        ////logger.info(" Selecting frame with value : " + value);

        if (value.equalsIgnoreCase("")) {
            //logger.error(" Frame id / name / index is not specified");
            return;
        }

        if (value.toLowerCase().startsWith("index=")) {
            try {
                System.out.println(" Index = " + value.substring(6));
                index = Integer.parseInt(value.substring(6));
            } catch (Exception e) {
                //logger.error(" Invalid index is specified" + e.getMessage());
                return;
            }
        }

        if (value.startsWith("//") || value.startsWith("css=")
                || value.startsWith("xpath=")) {
            WebElement ele = null;
            if (!value.startsWith("css="))
                ele = webdriver
                        .findElement(By.xpath(value));
            else
                ele = webdriver.findElement(
                        By.cssSelector(value));

            webdriver.switchTo().frame(ele);
            return;
        }

        try {
            if (index != -1) {
                ////logger.info(" Selecting frame with index " + index);
                webdriver.switchTo().frame(index);
                return;
            }
            ////logger.info(" Selecting frame with id / name " + value);
            webdriver.switchTo().frame(value);
            return;

        } catch (NoSuchFrameException e) {
            //logger.error(" No such frame found" + fieldName);
        } catch (Exception e) {
            //logger.error(" Exception in executing SELECTFRAME on " + fieldName
                    + e.getMessage());
        }
    }*/

    /**
     * Author: Phani Srikar Ch
     * <p/>
     * Method to perform a verify operation on particular cell in table
     * <p/>
     * <pre>
     * <b>Example : </b>
     * | Table/ section in form of rows | value to verify in the table | fieldName / description for reporting |
     *
     * </pre>
     */
    //@RobotKeyword
    //@ArgumentNames({"field", "value", "fieldName"})
   /* public void verifyTableData(String field, String value, String fieldName) {

        try {

            if (field.equals("")) {
                String warn = " Field is Empty ";
                System.out.println(warn);
                //logger.error(warn);
                return;
            }

            if (field.length() < 4) {
                String warn = " Field is Not specified in expected Format";
                System.out.println(warn);
                //logger.error(warn);
                return;
            }

            if (fieldName.equalsIgnoreCase(""))
                fieldName = field;

            boolean click = false;
            //value = utils.getValue(value);

            String[] datavalue = value.split(":");

            if (field.startsWith("//table")) {

                if (datavalue.length <= 3) {
                    String temp = " Invalid format of Data Value. \n It  should be row_start:col_no:text_to_match:check";
                    //logger.error(temp);
                    System.out.println(temp);
                    return;
                }
                String tblField = "//table[" + field + "]";
                if (field.startsWith("//")) {
                    tblField = field;
                }

                click = datavalue[3].equalsIgnoreCase("click");

                if (click && datavalue.length <= 3) {
                    String temp = " Invalid format of Data Value. \n It  should be row_start:col_no:text_to_match:click:column_to_be_clicked";
                    //logger.error(temp);
                    System.out.println(temp);
                    return;
                }

                datavalue[2] = utils.getValue(datavalue[2]);
                try {
                    WebDriverWait wait = new WebDriverWait(
                            webdriver, 5);
                    wait.until(ExpectedConditions
                            .presenceOfAllElementsLocatedBy(By.xpath(tblField
                                    + "//tr")));
                } catch (Exception e) {
                }

                int rowcount = webdriver
                        .findElements(By.xpath(tblField + "//tr")).size();
                System.out.println("No. of rows  = " + rowcount);

                int start_row = Integer.parseInt(datavalue[0]);

                if (start_row > rowcount) {
                    //logger.error(" Required no. of rows are not present. Required :: "
                            + start_row + " Present  ::" + rowcount);
                    return;
                }

                if (rowcount != 0) {

                    int ecnt = browser
                            .getCurrentWebDriver()
                            .findElements(
                                    By.xpath(tblField + "//tr[" + start_row
                                            + "]//td")).size();

                    for (int i = start_row; i <= rowcount; i++) {

                        int acnt = browser
                                .getCurrentWebDriver()
                                .findElements(
                                        By.xpath(tblField + "//tr[" + i
                                                + "]//td")).size();

                        if (ecnt > acnt) {
                            continue;
                        }

                        String loc = tblField + "//tr[" + i + "]//td ["
                                + datavalue[1] + "]";
                        String actual = webdriver
                                .findElement(By.xpath(loc)).getText();

                        boolean res = actual.equalsIgnoreCase(datavalue[2]);

                        if (res) {
                            ////logger.info("Expected value :: " + actual
                                    + "found at row " + i);
                            browser.hMap.put(datavalue[2], i + "");
                            if (click) {
                                int click_on = 4;
                                if (datavalue.length != 5) {
                                    click_on = 1;
                                }

                                loc = tblField + "//tr[" + i + "]//td["
                                        + datavalue[click_on] + "]";
                                try {
                                    webdriver
                                            .findElement(By.xpath(loc + "//*"))
                                            .click();
                                    System.out
                                            .println("Clicked on child element");
                                } catch (Exception e) {
                                    try {
                                        webdriver
                                                .findElement(By.xpath(loc))
                                                .click();
                                        System.out
                                                .println("Clicked on TD element");
                                    } catch (Exception e1) {
                                        throw new RuntimeException(
                                                " Unable to find element to be clicked ");
                                    }
                                }
                            }
                            return;
                        }
                    }
                    String temp = " No data found for table " + fieldName;
                    System.out.println(temp);
                    throw new RuntimeException(temp);
                } else {
                    String temp = " No rows found for table " + fieldName;
                    System.out.println(temp);
                    throw new RuntimeException(temp);

                }

            } else {

                if (datavalue.length <= 2) {
                    String temp = " Invalid format of Data Value. \n It  should be row_start:text_to_match:check";
                    System.out.println(temp);
                    return;
                }

                click = datavalue[2].equalsIgnoreCase("click");

                datavalue[1] = utils.getValue(datavalue[1]);

                String dataFields[] = field.split("::");

                if (click && dataFields.length <= 2) {
                    String temp = " Invalid format of Data Field. It should be XPHparent::child::child_to_be_clicked";
                    System.out.println(temp);
                    throw new RuntimeException(temp);
                }

                if (dataFields.length <= 1) {
                    String temp = " Invalid format of Data Field. It should be XPHparent::child";
                    System.out.println(temp);
                    throw new RuntimeException(temp);
                }

                int divcount = webdriver
                        .findElements(By.xpath(dataFields[0])).size();
                System.out.println("No. of rows  = " + divcount);

                int start_div = Integer.parseInt(datavalue[0]);

                if (start_div > divcount) {
                    String temp = " Required no. of rows are not present. Required :: "
                            + start_div + " Present  ::" + divcount;
                    // //logger.error(temp);
                    System.out.println(temp);
                    throw new RuntimeException(temp);
                }

                if (divcount != 0) {

                    for (int i = start_div; i <= divcount; i++) {

                        String loc = dataFields[0] + "[" + i + "]"
                                + dataFields[1];
                        String actual = webdriver
                                .findElement(By.xpath(loc)).getText();

                        boolean res = actual.equalsIgnoreCase(datavalue[1]);

                        if (res) {

                            ////logger.info("Expected value :: " + actual
                                    + "found at row " + i);

                            browser.hMap.put(datavalue[1], i + "");

                            if (click) {

                                loc = dataFields[0] + "[" + i + "]"
                                        + dataFields[2];
                                try {
                                    webdriver
                                            .findElement(By.xpath(loc)).click();
                                    ////logger.info(" Clicked on child element");
                                } catch (Exception e) {
                                    try {
                                        webdriver
                                                .findElement(By.xpath(loc))
                                                .click();
                                        ////logger.info(" Clicked on TD element");
                                    } catch (Exception e1) {
                                        //logger.error(" Unable to find element to be clicked ");
                                        throw new RuntimeException(
                                                "Unable to find element to be clicked");
                                    }
                                }
                            }
                            return;
                        }
                    }
                    String temp = " No data found for " + fieldName;
                    ////logger.info(temp);
                    throw new RuntimeException(temp);
                } else {
                    String temp = " No rows found for " + fieldName;
                    //logger.error(temp);
                    throw new RuntimeException(temp);
                }
            }

        } catch (Exception e) {
            ////logger.error(e.getMessage());
            throw new RuntimeException("Exception in executing VERIFYTABLEDATA"
                    + e.getMessage());
        }
    }
*/
    /**
     * Author: Phani Srikar Ch
     * <p/>
     * Method to verify that whether a table cell exists with given value / not
     * <p/>
     * <pre>
     * <b>Example : </b>	 *
     * | Table locator | any column Name | value to verify in the table | fieldName / description for reporting|
     * </pre>
     */
    //@RobotKeyword
    //@ArgumentNames({"field", "columnName", "value", "fieldName"})
    public boolean verifyTableRowExists(String field, String columnName,
                                        String value, String fieldName) {

        int columnNo = 0;
        int row_no = 1;
        int rowsize = 0;

        if (fieldName.equalsIgnoreCase(""))
            fieldName = field;

        //value = utils.getValue(value);

        try {
            String tableheaders = field + "//th";
            int headersize = webdriver
                    .findElements(By.xpath(tableheaders)).size();

            for (int i = 1; i <= headersize; i++) {
                String header = tableheaders + "[" + i + "]";
                if (webdriver.findElement(By.xpath(header))
                        .getText().trim().equalsIgnoreCase(columnName)) {
                    columnNo = i;
                    break;
                }
            }

            String tablerows = field + "/tbody/tr";
            rowsize = webdriver
                    .findElements(By.xpath(tablerows)).size();

            for (int i = row_no; i <= rowsize; i++) {
                if (isElementPresent(By.xpath(tablerows + "[" + i + "]//th"))) {
                    System.out.println("headers are in the row : " + i);
                    row_no++;
                    break;
                }
            }

            rowsize = webdriver
                    .findElements(By.xpath(tablerows)).size();
            boolean flag = false;
            for (int i = row_no; i <= rowsize; i++) {
                String row = tablerows + "[" + i + "]/td[" + columnNo + "]";
                if (webdriver.findElement(By.xpath(row))
                        .getText().trim().toLowerCase()
                        .contains(value.toLowerCase())) {
                    System.out.println(value + "Found in the row no : " + i);
                    flag = true;
                    break;
                }
            }

            //scr.capturePageScreenshot();
			if (flag==false) {
				////logger.info(value + " is not available under " + columnName
						//+ " column.");
				////Assert.//AssertTrue(flag);
			}
            return true;

        } catch (Exception e) {
            //scr.capturePageScreenshot();
            System.out.println(e.getMessage());
            /*//logger.debug(" Exception in executing VerifyTableRowExist"
                    + e.getMessage());*/
        }
        return false;
    }

    /**
     * Method to OK / Accept in the Alert boxes in browsers
     */
    public boolean acceptAlert() {
        try {
            Thread.sleep(1000);
            webdriver.switchTo().alert().accept();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method to wait for the sepcify amount of time to load the Entirepage
     */


    public void waitForPageLoad() {
        try {

            webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        } catch (Exception e) {
            //scr.capturePageScreenshot();
            System.out.println(e.getMessage());
            /*//logger.debug(" Exception in Wait For Page Load"
                    + e.getMessage());*/
        }

    }


    /**
     * Highlight the element on the page
     */
    public boolean highlightElement(By locator) {
        try {
            Thread.sleep(1000);
            WebElement elem = webdriver.findElement(locator);
            // draw a border around the found element
            if (webdriver instanceof JavascriptExecutor) {
                ((JavascriptExecutor) webdriver).executeScript("arguments[0].style.border='3px solid red'", elem);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method to press cancel in the Alert boxes in browsers
     */
    public boolean dismissAlert() {
        try {
            Thread.sleep(1000);
            webdriver.switchTo().alert().dismiss();
            return true;
        } catch (Exception e) {
			/*
			 * TestLauncher.//logger
			 * .info("!! Browser Exception !! Relaunching the application");
			 * this.launchBrowser(configurations);
			 */
            return false;
        }
    }

    /*
     * This method if for find the element based its property
     */
    protected By locatorToByObj(WebDriver webdriver, String locator) {
        try {

            if (locator.startsWith("css=")) {
                locator = locator.substring(4, locator.length());
                return By.cssSelector(locator);
            }

            if (locator.startsWith("class=")) {
                locator = locator.substring(6, locator.length());
                return By.className(locator);
            }

            if (locator.startsWith("xpath=")) {
                locator = locator.substring(6, locator.length());
                return By.xpath(locator);
            }

            if (locator.startsWith("name=")) {
                locator = locator.substring(6, locator.length());
                return By.name(locator);
            }

            if (locator.startsWith("//")) {
                try {
                    webdriver.findElement(By.xpath(locator));
                    return By.xpath(locator);
                } catch (Throwable e) {
                }
            }

            if (locator.startsWith("id=")) {
                locator = locator.substring(3, locator.length());
                return By.id(locator);
            }

            try {
                webdriver.findElement(By.id(locator));
                return By.id(locator);
            } catch (Throwable e) {
            }

            try {
                webdriver.findElement(By.linkText(locator));
                return By.linkText(locator);
            } catch (Throwable e) {
            }

            try {
                webdriver.findElement(By.cssSelector(locator));
                return By.cssSelector(locator);
            } catch (Throwable e) {
            }

            try {
                webdriver.findElement(By.name(locator));
                return By.name(locator);
            } catch (Throwable e) {
            }

            try {
                webdriver.findElement(By.className(locator));
                return By.className(locator);
            } catch (Throwable e) {
            }

            try {
                webdriver.findElement(By.xpath("//a[contains(text(),'"
                        + locator + "')]")); // this is for objects without
                // linkText.
                return By.xpath("//a[contains(text(),'" + locator + "')]");
            } catch (Throwable e) {
            }

            try {
                webdriver.findElement(By.linkText(locator));
                return By.linkText(locator);
            } catch (Throwable e) {
            }

            try {
                webdriver.findElement(By.partialLinkText(locator));
                return By.partialLinkText(locator);
            } catch (Throwable e) {
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to identify the element." + e.getMessage());
        }
        return null;
    }

    /**
     * Payroll - Waits until required page element to be present on the page
     */
    public void waitUntilElementPresent(By strElement) {
        try {
            WebDriverWait wait = new WebDriverWait(
                    webdriver, 30);
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(strElement));

        } catch (Exception e) {
            //scr.capturePageScreenshot();
            throw new CustomException(
                    "Exception occured while explicitly waiting until required page element to be present"
                            + e.getMessage());
        }
    }

    public void waitUntilNotElementPresent(By strElement) {
        try {

            WebDriverWait wait = new WebDriverWait(
                    webdriver, 10);
            wait.until(ExpectedConditions
                    .invisibilityOfElementLocated(strElement));

        } catch (Exception e) {
            //scr.capturePageScreenshot();
            throw new CustomException(
                    "Exception occured while explicitly waiting until required page element to be present"
                            + e.getMessage());
        }
    }



}
