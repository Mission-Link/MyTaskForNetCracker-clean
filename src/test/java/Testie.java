import actions.helpers.ActionDeque;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import logger.SimpleReporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import parser.Parser;
import strikepackage.Browser;

public class Testie {
    private Browser browser;
    private ActionDeque actionDeque;

    private ExtentReports reports;
    private ExtentTest test;

    @BeforeTest
    private void setUp() {
        SimpleReporter simpleReporter = new SimpleReporter();
        reports = simpleReporter.getReports();
        test = simpleReporter.getTest();

        browser = new Browser(test);
        String path = ".\\src\\main\\testfile\\test_scenario.xml";
        Parser parser = new Parser(path, browser);
        try {
            actionDeque = parser.parseXML();
        } catch (Exception e) {
            System.out.println("Impossible to parse XML file");
            System.out.println(e.getMessage());
        }
        test.info("Test initiated");
    }//end of setUp method

    @Test
    private void performTest() {
        actionDeque.execute();
    }

    @AfterTest
    private void stopTest() {
        browser.closeAndQuit();
        test.info("Test completed");
        reports.flush();
    }

}//end of class
