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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import Com.ComCast.CRM.Generic.FileUtility.ExcelUtility;
import Com.ComCast.CRM.Generic.FileUtility.FileUtility;
import Com.ComCast.CRM.Generic.WebDriverUtility.JavaUtility;
import Com.ComCast.CRM.Generic.WebDriverUtility.WebDriverUtility;
import Com.ComCast.CRM.ObjectRepositoryUtility.CreateNewOrganizationPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.HomePage;
import Com.ComCast.CRM.ObjectRepositoryUtility.LoginPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.OrganizationInfoPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.OrganizationsPage;

public class CreateOrgTest_Testcase_01 {

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
				  /*Random random=new Random();
			       int rdmInt=random.nextInt(1000);*/
			       
			    //read testscript data from Excel file
			       String orgName=elib.getDatafromExcel("org3", 10, 2) + jlib.getRandomNumber();
				
		
		
		
		
		/* FileInputStream fis=new FileInputStream("./ConfigAppData/commondata.properties");
	       Properties poj=new Properties();
	       poj.load(fis);
	       String Browser=poj.getProperty("browser");
	       String URL=poj.getProperty("url");
	       String USN=poj.getProperty("username");
	       String PSW=poj.getProperty("password");
	       
	       //generate the random number
	       Random random=new Random();
	       int rdmInt=random.nextInt(1000);
	       
	       //read test script data from Excel file
	       //step1:get the excel path location and java object of the physical excelfile
			FileInputStream fis1=new FileInputStream("./Test_Data/Multiple_Row_Data.xlsx");
			//step2:open WorkBook in read Mode
			Workbook wb=WorkbookFactory.create(fis1);
			//step3:get the control of the "Sheet1" sheet
			Sheet sh=wb.getSheet("org3");
			//step4:get the control of the "1st" Row
			Row row=sh.getRow(1);
			//step5:get the control of the 2nd cell and read the String data
			String orgName=row.getCell(2).toString() + rdmInt;
			//step6:Close the Workbook
			wb.close();*/
			
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
	       //login to app
	       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	       driver.get(URL);
	       
	       LoginPage lp=new LoginPage(driver);
	       lp.loginToApp(USN, PSW);
	       
	       //navigate to organization module
	       HomePage hp=new HomePage(driver);
	       hp.getOrganisationLink().click();
	       
	       //click on create organization button
	       OrganizationsPage op=new OrganizationsPage(driver);
	       op.getCreateOrganisationBtn().click();
	       
	       //enter all details and create a new organization
	       CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
	       cnop.getOrgNameEdt().sendKeys(orgName);
	       cnop.getSaveBtn().click();
	       
	       //Verify Header Message Expected Result
	       OrganizationInfoPage oip=new OrganizationInfoPage(driver);
	      String actOrgName =oip.getOrgHeaderInfo().getText();
	      if(actOrgName.contains(orgName)) {
	    	  System.out.println(orgName + " name is verified==Pass");
	      }
	      else {
	    	  System.out.println(orgName + " name is nit verified==Fail");
	      }
	       
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	       /*driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USN);
	       driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PSW);
	       driver.findElement(By.xpath("//input[@type='submit']")).click();
	      //navigate to organization module 
	       driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
	       //click on create organization button
	       driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
	       //enter all the details and create new organization
	       driver.findElement(By.name("accountname")).sendKeys(orgName);
	       //click on save button
	       driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();*/
	       
	       //verify Header message expected result
	      /* String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	       if(headerInfo.contains(orgName)) {
	    	   System.out.println(orgName + "is created==PASS");
	       }else {
	    	   System.out.println(orgName + "is not created==FAIL");   
	       }
	       String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
	       if(actOrgName.equals(orgName)) {
	    	  System.out.println(orgName + "is created==PASS");  
	       }
	       else {
	    	   System.out.println(orgName + "is not created==FAIL");
	       }
	       //Thread.sleep(2000);
	       //logout from app
	       //Actions act=new Actions(driver);
	       //act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
	      // driver.findElement(By.linkText("Sign Out")).click();
	       driver.quit();
	       */
	}

	private static boolean actOrgName(String orgName) {
		// TODO Auto-generated method stub
		return false;
	}

}
