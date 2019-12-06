package old.testies_old;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class Main {
    private static String taskURL = "https://google.com";
    private static String taskClick = "//*[@id=\"sb_ifc0\"]";

    public static void main(String[] args) throws InterruptedException {

//        System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver_m.exe");

        //firefox
//        System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Firefox\\firefox.exe");
//        System.setProperty("webdriver.gecko.driver", ".\\Driver\\geckodriver.exe");
//        WebDriver browser = new FirefoxDriver();

        //opera
//        System.setProperty("webdriver.opera.driver", ".\\Driver\\operadriver.exe");

        WebDriverManager.chromedriver().setup();
        WebDriver browser = new ChromeDriver();


//        WebDriver browser = new OperaDriver();
        String googleURL = "https://www.google.com";
        String searchRequest = "Mice";
//        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.manage().window().maximize();
        browser.get(googleURL);
        Thread.sleep(3000);
        try {
            WebElement searchBar = browser.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input")); //gsr
            searchBar.click();
            if (searchBar.isDisplayed()) {
                searchBar.clear();
                searchBar.sendKeys(searchRequest);
                searchBar.submit();
            }
            Thread.sleep(4000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Page title is: " + browser.getTitle());
        WebElement unexistedSb = browser.findElement(By.id("//*[@id=\"tsf\"]/div[2]/div[1]/div[2]/div[2]/div[2]/center/input[1]21"));
        boolean b = isElementPresent(browser, By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[2]/div[2]/div[2]/center/input[1]21"));
        System.out.println("b: "+b);

//        try {
//            unexistedSb.isDisplayed();
//        } catch (NoSuchElementException e) {
//            System.out.println(e.getMessage());;
//        }
    }

    private static boolean isElementPresent(WebDriver driver, By by) {
        boolean isPresent = true;
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            isPresent = false;
        }
        return isPresent;
    }

//        System.out.println("Page title is: "+browser.getTitle());

//        browser.quit();
//        browser.close();
//        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        webDriver.manage().window().maximize();
//        webDriver.get("https://www.google.com");
//        Thread.sleep(3000);
//        WebElement element = webDriver.findElement(By.id("tsf"));
//        System.out.println("After elem creation: ");
//        elemDisplay(element);
//        System.out.println("=========================");
//        String srt = "ololo";
//        element.click();
//        element.sendKeys(srt);
//        element.click();
//        System.out.println("elem get text:" +element.getText());
//        System.out.println("After elem clicking: ");
//        elemDisplay(element);

//        webDriver.findElement(By.id("gsr")).click();


    private static void openURL(WebDriver browser, String url) {
        browser.get(url);
    }

    private static void click(WebDriver browser, String xpath) {
        WebElement webElement = browser.findElement(By.xpath(xpath));
        webElement.click();
    }

    private static void setValue(WebDriver browser, String xpath, String text) {
        browser.findElement(By.xpath(xpath)).sendKeys(text);
    }

    private static boolean checkElementVisible(WebDriver browser, String xpath) {
        return browser.findElement(By.xpath(xpath)).isDisplayed();
    }

    private static void takeScreenshot(WebDriver browser) {
        File screenDir = new File(".");
        if (!screenDir.exists()) {
            screenDir.mkdir();
        }
        File scrFile = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(screenDir.getPath() + "\\screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    private static void elemDisplay(WebElement element) {
        System.out.println("Elem is displayed: " + element.isDisplayed());
        System.out.println("Elem is enabled: " + element.isEnabled());
        System.out.println("Elem is selected: " + element.isSelected());
    }
}
