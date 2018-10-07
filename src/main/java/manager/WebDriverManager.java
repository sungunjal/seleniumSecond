package manager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import enums.DriverType;

public class WebDriverManager {

	public WebDriver driver;
	private static DriverType driverType;
	public ConfigFileReader configFileReader = new ConfigFileReader();
	public static ExtentReports extentReport;
	public static ExtentTest extentTest;
	static File theDir;
	List failedList = new ArrayList();

	public WebDriverManager() {
		driverType = configFileReader.getBrowser();
	}

	// Driver Initilization
	public WebDriver getDriver() {
		if (driver == null) {
			driver = setDriver();
		}
		return driver;
	}

	public ExtentTest getExtent() {
		extentTest = extentReport.startTest(this.getClass().getSimpleName());
		return extentTest;
	}

	@BeforeClass
	private WebDriver setDriver() {
		try {
			this.getExtent();
		} catch (Exception e) {
		}
		switch (driverType) {
		case CHROME:
			driver = new ChromeDriver();
			break;
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case INTERNETEXPLORER:
			driver = new InternetExplorerDriver();
			break;
		default:
			break;
		}

		driver.get(configFileReader.getAppUrl());
		driver.manage().window().maximize();
		return driver;

	}

	// Extent Report Setup
	@BeforeSuite
	public ExtentReports extentReportSetup() {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		theDir = new File("Logs\\Test" + timeStamp);

		if (!theDir.exists()) {
			System.out.println("creating directory: " + theDir.getName());
			theDir.mkdirs();
		}
		extentReport = new ExtentReports(
				System.getProperty("user.dir") + "\\" + theDir + "/BrowserSuite/" + timeStamp + ".html", true,
				DisplayOrder.OLDEST_FIRST);
		extentReport.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
		return extentReport;
	}

	// Take Screenshot path
	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String destination = null;
		File finalDestination = null;
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		destination = System.getProperty("user.dir") + "\\" + theDir + "/BrowserSuite/FailedTestsScreenshots/"
				+ screenshotName + dateName + ".png";
		finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Test Case Failed :-" + result.getName());
			extentTest.log(LogStatus.FAIL, "Test Case Failed is :-" + result.getThrowable());
			String screenshotPath = this.getScreenhot(driver, result.getName());
			// To add it in the extent report
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));
			driver.quit();
			failedList.add(result.getTestClass());
			System.out.println(failedList);
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		// ending test
		extentReport.endTest(extentTest);
	}

	// Flush extent Report
	@AfterSuite
	public void flushExtent() {
		extentReport.flush();
	}

}
