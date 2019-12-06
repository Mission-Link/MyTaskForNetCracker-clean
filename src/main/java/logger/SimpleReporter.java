package logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SimpleReporter {
    private ExtentReports reports;
    private ExtentTest test;

    public SimpleReporter() {
        String pathToReports = System.getProperty("user.dir");
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(pathToReports +
                File.separator + "reports" + File.separator + "report_"+generateUniqueDT()+".html");
        //create ExtentReports and attach
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);
        //creates a toggle (probably like a 'hat' above the information part)
        test = reports.createTest("TestCase", "Let's test something :3 ");
    }

    private static String generateUniqueDT() {
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HHmmss");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("ddMMYY");
        String f1 = formatter1.format(localTime);
        String f2 = formatter2.format(localDate);
        return f2 + "_" + f1;
    }

    public ExtentReports getReports() {
        return reports;
    }

    public ExtentTest getTest() {
        return test;
    }
}
