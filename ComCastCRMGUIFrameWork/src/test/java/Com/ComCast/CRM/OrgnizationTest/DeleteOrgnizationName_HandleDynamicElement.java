package Com.ComCast.CRM.OrgnizationTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Com.ComCast.CRM.Generic.FileUtility.ExcelUtility;
import Com.ComCast.CRM.Generic.FileUtility.FileUtility;
import Com.ComCast.CRM.Generic.WebDriverUtility.JavaUtility;
import Com.ComCast.CRM.Generic.WebDriverUtility.WebDriverUtility;
import Com.ComCast.CRM.ObjectRepositoryUtility.CreateNewOrganizationPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.HomePage;
import Com.ComCast.CRM.ObjectRepositoryUtility.LoginPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.OrganizationInfoPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.OrganizationsPage;

public class DeleteOrgnizationName_HandleDynamicElement {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// read common data from properties file
		FileUtility plib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();

		// read commondata fom property file
		String Browser = plib.getDataFromPropertiesFile("browser");
		String URL = plib.getDataFromPropertiesFile("url");
		String USN = plib.getDataFromPropertiesFile("username");
		String PSW = plib.getDataFromPropertiesFile("password");

		// read testscript data from Excel file
		String orgName = elib.getDatafromExcel("org3", 10, 2) + jlib.getRandomNumber();
		WebDriver driver = null;
		if (Browser.equals("Chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else if (Browser.equals("Edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		// login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);

		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USN, PSW);

		// navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrganisationLink().click();

		// click on create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrganisationBtn().click();

		// enter all details and create a new organization
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.getOrgNameEdt().sendKeys(orgName);
		cnop.getSaveBtn().click();

		// Verify Header Message Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getOrgHeaderInfo().getText();
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + " name is verified==Pass");
		} else {
			System.out.println(orgName + " name is nit verified==Fail");
		}

		// go back to Organizations page
		hp.getOrganisationLink().click();

		// search for Organization
		op.getSearchTextField().sendKeys(orgName);
		wlib.selectText(op.getSerachDropDown(), "Organization Name");
		op.getSerchNowBtn().click();
		Thread.sleep(2000);
		// in dynamic web table select and delete orgName
		driver.findElement(By.xpath("//a[text()='" + orgName + "'] /../../td[8]/a[text()='del']")).click();
		Thread.sleep(2000);

		wlib.AlertPopupAccept(driver);
		hp.LogOut();
		driver.quit();

	}

}
