package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import strikepackage.Browser;

import java.util.Arrays;

public class ClickAct extends Action implements IAction{
    private String locatorXpath;

    public ClickAct(String locatorXpath, Browser browser) {
        super("click", browser);
        this.locatorXpath = locatorXpath;
    }

    public String getLocatorXpath() {
        return locatorXpath;
    }

    @Override
    public String toString() {
        return "ClickAct{" +
                "locatorXpath='" + locatorXpath + '\'' +
                '}';
    }

    @Override
    public void run() {
        try{
            WebElement webElement = null;
            {
                try {
                    WebDriverWait wait = new WebDriverWait(browser.getWebDriver(), 3);
                    webElement = wait.until(ExpectedConditions.visibilityOfElementLocated
                            (By.xpath(locatorXpath)));
                    webElement.click();
//                webElement.sendKeys(Keys.RETURN); //press Enter key
                } catch (Exception e) {
                    System.out.println("Exception!\n Impossible to click element by path: " + locatorXpath);
                    System.out.println(e.getMessage());
                    System.out.println(Arrays.toString(e.getStackTrace()));
                }
            }
            browser.getTest().pass("Clicked on element \""+locatorXpath+"\" successfully");
        }catch (Exception e){
            browser.getTest().fail("Impossible to click on element \""+locatorXpath+"\"");
        }

    }//end of method run()

}//end of class
