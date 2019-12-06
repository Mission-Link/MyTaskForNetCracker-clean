package actions;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import strikepackage.Browser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotAct extends Action implements IAction {
    public ScreenshotAct(Browser browser) {
        super("screenshot", browser);
    }

    @Override
    public String toString() {
        return "ScreenshotAct{}";
    }

    @Override
    public void run() {
        try {
            String uniqueScreenshotName = generateUniqueDT();
            File screenDir = new File("screenshots");
            if (!screenDir.exists()) {
                screenDir.mkdir();
            }
            File scrFile = ((TakesScreenshot) browser.getWebDriver()).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scrFile, new File(screenDir.getPath() +
                        File.separator + "screenshot_" + uniqueScreenshotName + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            browser.getTest().pass("Screenshot created successfully");
        } catch (Exception e) {
            browser.getTest().fail("Impossible to take a screenshot");
        }
    }//end of run() method

    private static String generateUniqueDT() {
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HHmmss");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("ddMMYY");
        String f1 = formatter1.format(localTime);
        String f2 = formatter2.format(localDate);
        return f1 + "_" + f2;
    }
}
