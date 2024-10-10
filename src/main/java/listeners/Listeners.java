package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import utilities.ExtentReporter;

public class Listeners extends Base implements ITestListener {

	WebDriver driver = null;
	ExtentReports extentReport = ExtentReporter.getExtentReport();
	ExtentTest extentTest;
	ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extentTest = extentReport.createTest(testName  +" execution started");
	}
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.PASS,testName + "Got Passed");
	}
	public void onTestFailure(ITestResult result) {
		//we have to pass 2 things
		//takeScreenshot(testname,driver);
		
		String testName = result.getName();
		
		extentTest.fail(result.getThrowable());
		
		try {
			//the purpose of this line is that we need a driver of a test which is filing
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			String screenshotFilePath = takeScreenshot(testName,driver);
			//extentTestThread.get().addScreenCaptureFromPath(screenshotFilePath,testName);
			extentTest.addScreenCaptureFromPath(screenshotFilePath, testName);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void onTestSkipped(ITestResult result) {
		
	}
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	public void onTestFailedWithTimeout(ITestResult result) {

	}
	public void onStart(ITestContext context) {
	
	}
	public void onFinish(ITestContext context) {
		extentReport.flush();//add this to see the report otherwise nothing will be displayed
		
	}
	
}
