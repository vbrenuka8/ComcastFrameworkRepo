package practice_testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import Com.ComCast.CRM.Generic.FileUtility.ExcelUtility;
import Com.ComCast.CRM.Generic.FileUtility.FileUtility;
import Com.ComCast.CRM.Generic.WebDriverUtility.JavaUtility;
import Com.ComCast.CRM.Generic.WebDriverUtility.WebDriverUtility;
import Com.ComCast.CRM.ObjectRepositoryUtility.CreateContactPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.CreateNewOrganizationPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.HomePage;
import Com.ComCast.CRM.ObjectRepositoryUtility.LoginPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.OrganizationsPage;

public class CreateContactWithOrgTest_IntegrationT_TC_06 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// read common data from properties file
		FileUtility plib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();

		String Browser = plib.getDataFromPropertiesFile("browser");
		String URL = plib.getDataFromPropertiesFile("url");
		String USN = plib.getDataFromPropertiesFile("username");
		String PSW = plib.getDataFromPropertiesFile("password");

		// read test script data from Excel file
		String orgName = elib.getDatafromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String lastname = elib.getDatafromExcel("contact", 7, 3) + jlib.getRandomNumber();

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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		// login to app
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USN, PSW);

		// navigate to Homepage
		HomePage hp = new HomePage(driver);
		hp.getOrganisationLink().click();

		// organization page
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrganisationBtn().click();

		// Navigate to createneworganization page
		CreateNewOrganizationPage cop = new CreateNewOrganizationPage(driver);
		cop.getOrgNameEdt().sendKeys(orgName);
		cop.getSaveBtn().click();

		// verify header information
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + " is created==PASS");
		} else {
			System.out.println(orgName + " is not created==FAIL");
		}
		
		//navigate to contact module
		hp.getContactLink().click();
		
		//create new contact
		CreateContactPage cp=new CreateContactPage(driver);
		cp.getCreateNewContactBtn().click();
		cp.getLastNameTextField().sendKeys(lastname);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		//Switch to child window
		wlib.switchToWindowByURL(driver, "?module=Accounts&action");
		Thread.sleep(2000);
		
		cop.getOrganizationSearchTextField().sendKeys(orgName);
		WebElement ele2=cop.getOrganizationDropDown();
		wlib.selectText(ele2, "Organization Name");
		cop.getSearchNowButton().click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		/*perform the operations in child window
		
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@type='button']")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		

		//Switch Control to the parent
		wlib.switchToWindowByURL(driver, "module=Contacts&action");
		
		 
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		Thread.sleep(2000);
		
		
		
		

		/* step 5:navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// switch to child window
		wlib.switchToWindowByTitle(driver, "?module=Accounts&action");

		/*
		 * Set<String> set= driver.getWindowHandles(); Iterator<String>
		 * it=set.iterator();
		 * 
		 * while(it.hasNext()) { String windowID=it.next();
		 * driver.switchTo().window(windowID); String Acturl=driver.getCurrentUrl();
		 * if(Acturl.contains("?module=Accounts&action")) { break; } }
		 */

		/*driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		wlib.switchToWindowByTitle(driver, "?module=Contacts&action");*/

		// switch to parent window
		/*
		 * Set<String> set1= driver.getWindowHandles(); Iterator<String>
		 * it1=set.iterator();
		 * 
		 * while(it1.hasNext()) { String windowID=it1.next();
		 * driver.switchTo().window(windowID); String Acturl=driver.getCurrentUrl();
		 * if(Acturl.contains("?module=Contacts&action")) { break; } }
		 */

		/*click on save button
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		// verify header information
		headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(lastname)) {
			System.out.println(lastname + " is created==PASS");
		} else {
			System.out.println(lastname + " is not created==FAIL");
		}
		// Verify the Organisation name is there
		String actOrgName = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']")).getText();
		if (actOrgName.trim().equals(orgName)) {
			System.out.println(orgName + " is created==PASS");
		} else {
			System.out.println(orgName + " is not created==FAIL");
		}

		Thread.sleep(2000);
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		act.moveToElement(ele).perform();
		driver.findElement(By.xpath("//a[@href='index.php?module=Users&action=Logout']")).click();

		// step*/
		//driver.quit();

	}

}
