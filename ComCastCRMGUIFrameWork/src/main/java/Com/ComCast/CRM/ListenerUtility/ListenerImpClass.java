package Com.ComCast.CRM.ListenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Com.ComCast.CRM.Generic.BaseUtility.BaseClass;
import Com.ComCast.CRM.Generic.WebDriverUtility.UtilityClassObject;

public class ListenerImpClass implements ITestListener, ISuiteListener {
	public ExtentSparkReporter spark;
	public static ExtentReports report;
	public static ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report configuration");
		String time=new Date().toString().replace(" ", "_").replace(":", " ");
		// spark report config
				ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
				spark.config().setDocumentTitle("CRM Test Suite Results");
				spark.config().setReportName("CRM Report");
				spark.config().setTheme(Theme.DARK);

				// Add Environment information and create test
				report = new ExtentReports();
				report.attachReporter(spark);
				report.setSystemInfo("OS", "Windows-10");
				report.setSystemInfo("Browser", "Chrome-100");
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report backUp");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("======>" + result.getMethod().getMethodName() + ">===START===");
		 test=report.createTest(result.getMethod().getMethodName());
		 UtilityClassObject.setTest(test);
		 test.log(Status.INFO, result.getMethod().getMethodName()+"====> STARTED <====");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("======>" + result.getMethod().getMethodName() + ">===END===");
		 test.log(Status.PASS, result.getMethod().getMethodName()+"====> COMPLETED <====");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String testName = result.getMethod().getMethodName();
		
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		String filePath=ts.getScreenshotAs(OutputType.BASE64);
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String time=new Date().toString().replace(" ", "_").replace(":", " ");
		test.addScreenCaptureFromBase64String(filePath, testName+"_"+time);
		test.log(Status.FAIL, result.getMethod().getMethodName()+"====> FAILED <====");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
