package Com.ComCast.CRM.Generic.BaseUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Com.ComCast.CRM.Generic.DataBaseUtility.DataBaseUtility;
import Com.ComCast.CRM.Generic.FileUtility.ExcelUtility;
import Com.ComCast.CRM.Generic.FileUtility.FileUtility;
import Com.ComCast.CRM.Generic.WebDriverUtility.JavaUtility;
import Com.ComCast.CRM.Generic.WebDriverUtility.UtilityClassObject;
import Com.ComCast.CRM.Generic.WebDriverUtility.WebDriverUtility;
import Com.ComCast.CRM.ObjectRepositoryUtility.HomePage;
import Com.ComCast.CRM.ObjectRepositoryUtility.LoginPage;

public class BaseClass {

	public DataBaseUtility dblib = new DataBaseUtility();
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib = new JavaUtility();
	public WebDriver driver = null;
	public WebDriverUtility wlib = new WebDriverUtility();
	public static WebDriver sdriver = null;

	@BeforeSuite(groups = { "smokeTest", "regressionTest" })
	public void configBS() throws Exception {
		System.out.println("==Connect to DB, Report Config==");
		dblib.getdbConnection();

	}

	// @Parameters("BROWSER")
	@BeforeClass(groups = { "smokeTest", "regressionTest" })
	// public void configBC(String browser) throws Exception { //cross browser
	// parallel execution
	public void configBC() throws Exception {
		System.out.println("==Launch the Browser==");
		String URL = flib.getDataFromPropertiesFile("url");
		// String Browser=browser;
		String Browser = flib.getDataFromPropertiesFile("browser");

		if (Browser.equals("Chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else if (Browser.equals("Edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver = driver;
		driver.get(URL);
		UtilityClassObject.setdriver(driver);

	}

	@BeforeMethod(groups = { "smokeTest", "regressionTest" })
	public void configBM() throws Exception {
		System.out.println("==Login==");
		String USN = flib.getDataFromPropertiesFile("username");
		String PSW = flib.getDataFromPropertiesFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USN, PSW);
	}

	@AfterMethod(groups = { "smokeTest", "regressionTest" })
	public void configAM() {
		System.out.println("==Logout==");
		HomePage hp = new HomePage(driver);
		hp.LogOut();

	}

	@AfterClass(groups = { "smokeTest", "regressionTest" })
	public void configAC() {
		System.out.println("==Close the Browser==");
		driver.quit();
	}

	@AfterSuite(groups = { "smokeTest", "regressionTest" })
	public void configAS() throws Exception {
		System.out.println("==Close the DB, Report BackUp==");
		dblib.closedbConnection();
		

	}
}
