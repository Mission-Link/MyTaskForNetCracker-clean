package old.depricated;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/*
This class encapsulates WebDriver obj and provides behaviour that needs
to be tested
 */
public class DerpyTester {

    private WebDriver browser;

    //constructors

    public DerpyTester() {
        WebDriverManager.chromedriver().setup();
        browser = new ChromeDriver();
//        browser.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    /**
     * Constructor with String param
     *
     * @param browserName specified WebDriver name
     *                    that has to be created
     */
    public DerpyTester(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                browser = new ChromeDriver();
                break;
            case "opera":
                WebDriverManager.operadriver().setup();
                browser = new OperaDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                browser = new ChromeDriver();
                break;
        }
        browser.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }//end of constructor with String param

    //methods

    /**
     * Maximizes Window
     */
    void maximizeWindow() {
        browser.manage().window().maximize();
    }

    /**
     * Finishes work
     */
    void exitBrowser() {
        browser.close();
        browser.quit();
    }

    WebDriver getBrowser() {
        return browser;
    }

    /**
     * Opens a web page
     *
     * @param url (String) web page URL
     */
    void openURL(String url) {
        browser.get(url);
    }

    /**
     * Clicks the element
     *
     * @param xpath (String) Xpath to element that needs to be clicked
     */
    void click(String xpath) {
        WebElement webElement = null;
        {
            try {
                WebDriverWait wait = new WebDriverWait(browser, 3);
                webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                webElement.click();
//                webElement.sendKeys(Keys.RETURN); //press Enter key
            } catch (Exception e) {
                System.out.println("Exception!\n Impossible to click element by path: " + xpath);
                System.out.println(e.getMessage());
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
    }//end of method click()

    /**
     * Sets text to web element
     *
     * @param xpath (String) xpath to web elem
     * @param text  (String) text that needs to be set
     */
    void setValue(String xpath, String text) {
        browser.findElement(By.xpath(xpath)).sendKeys(text);
    }

    /**
     * Checks whether web element visible
     *
     * @param xpath path to element
     * @return true if visible and false if not
     */
    boolean checkElementVisible(String xpath) {
        return browser.findElement(By.xpath(xpath)).isDisplayed();
    }

    /**
     * Takes a screenshot
     */
    void takeScreenshot() {
        String uniqueScreenshotName = generateUniqueDT();

        File screenDir = new File("screenshots");
        if (!screenDir.exists()) {
            screenDir.mkdir();
        }
        File scrFile = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(screenDir.getPath() +
                    File.separator + "screenshot_" + uniqueScreenshotName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Helper method used to generate unique screenshot name
     */
    private static String generateUniqueDT() {
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HHmmss");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("ddMMYY");
        String f1 = formatter1.format(localTime);
        String f2 = formatter2.format(localDate);
        return f1 + "_" + f2;
    }

}//end of class DerpyTester
