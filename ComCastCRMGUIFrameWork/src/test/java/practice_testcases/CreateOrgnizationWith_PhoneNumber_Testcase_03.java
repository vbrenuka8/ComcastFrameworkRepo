package practice_testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Com.ComCast.CRM.Generic.FileUtility.ExcelUtility;
import Com.ComCast.CRM.Generic.FileUtility.FileUtility;
import Com.ComCast.CRM.Generic.WebDriverUtility.JavaUtility;
import Com.ComCast.CRM.ObjectRepositoryUtility.CreateNewOrganizationPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.HomePage;
import Com.ComCast.CRM.ObjectRepositoryUtility.LoginPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.OrganizationInfoPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.OrganizationsPage;

public class CreateOrgnizationWith_PhoneNumber_Testcase_03 {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		//read common data from properties file
		FileUtility plib=new FileUtility();
        ExcelUtility elib=new ExcelUtility();
        JavaUtility jlib=new JavaUtility();

       //read commondata fom property file
		String Browser=plib.getDataFromPropertiesFile("browser");
		String URL=plib.getDataFromPropertiesFile("url");
		String USN=plib.getDataFromPropertiesFile("username");
		String PSW=plib.getDataFromPropertiesFile("password");
		
	       
	       //read test script data from Excel file
		 String orgName=elib.getDatafromExcel("org3", 7,2) + jlib.getRandomNumber();
		 String phonenumber=elib.getDatafromExcel("org3", 7,3);
	       
			
	       WebDriver driver=null;
	       if(Browser.equals("Chrome"))
	       {
	    	   driver=new ChromeDriver();
	       }
	       else if(Browser.equals("Firefox"))
	       {
	    	 driver=new FirefoxDriver();  
	       }
	       else if(Browser.equals("Edge"))
	       {
	    	   driver=new EdgeDriver();
	       }
	       else
	       {
	    	   driver=new ChromeDriver();
	       }
	       
	       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	       driver.get(URL);
	       
	       //login to app
	       LoginPage lp=new LoginPage(driver);
	       lp.loginToApp(USN, PSW);
	       
	     //navigate to organization module 
	       HomePage hp=new HomePage(driver);
	       hp.getOrganisationLink().click();
	       
	     //click on create organization button
	       OrganizationsPage op=new OrganizationsPage(driver);
	       op.getCreateOrganisationBtn().click();
	       
	       //enter all details
	       CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
	       cnop.getOrgNameEdt().sendKeys(orgName);
	       cnop.getPhoneTxtField().sendKeys(phonenumber);
	       cnop.getSaveBtn().click();
	       
	      OrganizationInfoPage oip=new OrganizationInfoPage(driver);
	       String actPhoneNumber=oip.getOrgPhoneInfo().getText();
	       if(actPhoneNumber.contains(phonenumber)) {
	    	   System.out.println(phonenumber + " number is verified==PASS");
	       }
	       else {
	    	   System.out.println(phonenumber + " number is not verified==FAIL");
	       }
	       
	       hp.LogOut();
	       driver.quit();
	       
	      /* driver.manage().window().maximize();
	       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	       driver.get(URL);
	       driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USN);
	       driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PSW);
	       driver.findElement(By.xpath("//input[@type='submit']")).click();
	      //navigate to organization module 
	       driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
	       //click on create organization button
	       driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
	       //enter all the details and create new organization
	       driver.findElement(By.name("accountname")).sendKeys(orgName);
	       driver.findElement(By.id("phone")).sendKeys(phonenumber);
	       //click on save button
	       driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
	       
	       //verify phone number
	       String phoneNoInfo = driver.findElement(By.id("dtlview_Phone")).getText();
	       if(phoneNoInfo.equals(phonenumber)) {
	    	   System.out.println(phonenumber + " information is verified==PASS");
	       }else {
	    	   System.out.println(phonenumber + " information is not verified==FAIL");   
	       }
	       driver.quit();*/
	       
	}

}
