package practice_testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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
import Com.ComCast.CRM.Generic.WebDriverUtility.WebDriverUtility;
import Com.ComCast.CRM.ObjectRepositoryUtility.CreateContactPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.CreateNewOrganizationPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.HomePage;
import Com.ComCast.CRM.ObjectRepositoryUtility.LoginPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.OrganizationsPage;


public class CreateContactWith_supportStartDateAndEndDate_Testcase05 {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		//read common data from properties file
		FileUtility plib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
		
		//read commondata fom property file
		String Browser=plib.getDataFromPropertiesFile("browser");
		String URL=plib.getDataFromPropertiesFile("url");
		String USN=plib.getDataFromPropertiesFile("username");
		String PSW=plib.getDataFromPropertiesFile("password");
		
		//generate the random number
	      // Random random=new Random();
	       //int rdmInt=random.nextInt(1000);
	       
	     //read test script data from Excel file
	       String lastname=elib.getDatafromExcel("contact", 4, 2) + jlib.getRandomNumber();
	       //capture date using java utility
	       String startDate = jlib.getSystemDateYYYYDDMM();
	       String endDate = jlib.getRequiredDateYYYYDDMM(30);	       
	      
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
	       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	       driver.get(URL);
	       
	       //Login to app
	       LoginPage lp=new LoginPage(driver);
	       lp.loginToApp(USN, PSW);
	       
	       //navigate to HomePage
	       HomePage hp=new HomePage(driver);
	       hp.getContactLink().click();
	       
	       //Navigate to Organization Module
	       CreateContactPage cp=new CreateContactPage(driver);
	       cp.getCreateNewContactBtn().click();
	       cp.getLastNameTextField().sendKeys(lastname);
	       cp.getStartDate().clear();
	       cp.getStartDate().sendKeys(startDate);
	       cp.getEndDate().clear();
	       cp.getEndDate().sendKeys(endDate);
	       cp.getSaveButton().click();
	       
	       //Verify Header the Expected Result
			String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
			if(actLastName.contains(lastname)) {
				System.out.println(lastname+ " informatoin is Verified==PASS");
			}
			else {
				System.out.println(lastname+ " information is not verified==FAIL");
			}
			//Verify the Start date
			String StartDate = driver.findElement(By.xpath("//span[@id='dtlview_Support Start Date']")).getText();
			if(StartDate.equals(startDate)) {
				System.out.println(startDate+ " is Verified==PASS");
			}
			else {
				System.out.println(startDate+ " is not verified==FAIL");
			}
			//Verify the End date
			
			String EndDate = driver.findElement(By.xpath("//span[@id='dtlview_Support End Date']")).getText();
			if(EndDate.equals(endDate))
			{
				System.out.println(endDate+ " is Verified==PASS");
			}
			else
			{
				System.out.println(endDate+ " is not verified==FAIL");
			}

			
			driver.quit();
	       
	       //enter all details
	       
	       
	       /*login to app
	       driver.manage().window().maximize();
	       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	       driver.get(URL);
	       driver.findElement(By.xpath("//input[@name='user_name']")).clear();
		   driver.findElement(By.xpath("//input[@name='user_password']")).clear();
			
	       driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USN);
	       driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PSW);
	       driver.findElement(By.xpath("//input[@type='submit']")).click();
	      //navigate to organization module 
	       driver.findElement(By.xpath("//a[text( )='Contacts']")).click();
	       //click on create organization button
	       driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	       //enter all the details and create new organization
	       driver.findElement(By.name("lastname")).sendKeys(lastname);
	       
	       //capture date using java utility
	       String startDate = jlib.getSystemDateYYYYDDMM();
	       String endDate = jlib.getRequiredDateYYYYDDMM(30);*/
	       
	       
	       
	       /*CapToture the Current date
		    Date dobj=new Date();
			SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
			String startDate = simple.format(dobj);
			System.out.println("Currentdate "+startDate);
			
			//to Capture the date after 1 month
			Calendar cal = simple.getCalendar();
			cal.add(Calendar.DAY_OF_MONTH,30);
			String endDate = simple.format(cal.getTime());
			System.out.println("After Date "+endDate);*/
	       
	       /* driver.findElement(By.xpath("//input[@name='support_start_date']")).clear();
			driver.findElement(By.xpath("//input[@name='support_start_date']")).sendKeys(startDate);
			driver.findElement(By.xpath("//input[@name='support_end_date']")).clear();
			driver.findElement(By.xpath("//input[@name='support_end_date']")).sendKeys(endDate);
	       //click on save button
	       driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
	       Thread.sleep(2000);
	     //Verify Header the Expected Result
			String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
			if(actLastName.contains(lastname)) {
				System.out.println(lastname+ " informatoin is Verified==PASS");
			}
			else {
				System.out.println(lastname+ " information is not verified==FAIL");
			}
			//Verify the Start date
			String StartDate = driver.findElement(By.xpath("//span[@id='dtlview_Support Start Date']")).getText();
			if(StartDate.equals(startDate)) {
				System.out.println(startDate+ " is Verified==PASS");
			}
			else {
				System.out.println(startDate+ " is not verified==FAIL");
			}
			//Verify the End date
			
			String EndDate = driver.findElement(By.xpath("//span[@id='dtlview_Support End Date']")).getText();
			if(EndDate.equals(endDate))
			{
				System.out.println(endDate+ " is Verified==PASS");
			}
			else
			{
				System.out.println(endDate+ " is not verified==FAIL");
			}

			
			driver.quit();*/
	       
	       
	}

}
