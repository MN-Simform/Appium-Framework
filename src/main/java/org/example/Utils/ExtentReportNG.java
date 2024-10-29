package org.example.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

    static ExtentReports extentReport;

    public static ExtentReports getExtentReportObject(){
        String path = System.getProperty("user.dir") + "/reports/index.html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("E-Com Report");
        reporter.config().setDocumentTitle("Test Results");

        extentReport = new ExtentReports();
        extentReport.attachReporter(reporter);
        extentReport.setSystemInfo("Tester", "Nimit");
        return extentReport;
    }
}
