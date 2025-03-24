package practice_testcases;

import java.io.FileInputStream;
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
import Com.ComCast.CRM.ObjectRepositoryUtility.ContactInfoPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.CreateContactPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.HomePage;
import Com.ComCast.CRM.ObjectRepositoryUtility.LoginPage;

public class CreateContactTest_Testcase_04 {

	

	public static void main(String [] args) throws Exception {
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
		
		//generate the random number
	       //Random random=new Random();
	       //int rdmInt=random.nextInt(1000);
	       
	    //read testscript data from Excel file
	       String lastname=elib.getDatafromExcel("contact", 1, 2) + jlib.getRandomNumber();
		   
	               WebDriver driver=null;
	    		   if(Browser.equals("Chrome")) {
	    			   driver=new ChromeDriver();
	    		   }
	    		   else if(Browser.equals("Firefox")){
	    			   driver=new FirefoxDriver();
	    		   }
	    		   else if(Browser.equals("Edge")) {
	    	       	   driver=new EdgeDriver();
	    	       }
	    	       else {
	    	       	  driver=new ChromeDriver();
	    	       }
	    	       
	    	       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    	       driver.get(URL);   
	      
	               //login to app
	    	       LoginPage lp=new LoginPage(driver);
	               lp.loginToApp(USN, PSW);
	               
	              //navigate to organization module  
		            HomePage hp=new HomePage(driver);
		            hp.getContactLink().click();
		            
		            //navigate to create contact page
		            CreateContactPage ccp=new CreateContactPage(driver);
		            ccp.getCreateNewContactBtn().click();
		            ccp.getLastNameTextField().sendKeys(lastname);
		            ccp.getSaveButton().click();
		            
		            //verification of header information
		            ContactInfoPage cinf=new ContactInfoPage(driver);
		            
		            String actHeaderInfo=cinf.getContactHeaderInfo().getText();
		            		if(actHeaderInfo.contains(lastname)) {
		         	    	   System.out.println(lastname + " lastname is verified==PASS");
		         	       }
		         	       else {
		         	    	   System.out.println(lastname + " lastname is not verified==FAIL");
		         	       }
		         	       
		         	       hp.LogOut();
		         	       driver.quit();
	       
	       
		/* FileInputStream fis=new FileInputStream("./ConfigAppData/commondata.properties");
	       Properties poj=new Properties();
	       poj.load(fis);
	       String Browser=poj.getProperty("browser");
	       String URL=poj.getProperty("url");
	       String USN=poj.getProperty("username");
	       String PSW=poj.getProperty("password");*/
	       
	       //generate the random number
	       /*Random random=new Random();
	       int rdmInt=random.nextInt(1000);*/
	       
	       //read test script data from Excel file
	       	/*FileInputStream fis1=new FileInputStream("./Test_Data/Multiple_Row_Data.xlsx");
			Workbook wb=WorkbookFactory.create(fis1);
			Sheet sh=wb.getSheet("contact");
			Row row=sh.getRow(1);
			//step5:get the control of the 2nd cell and read the String data
			String lastname=row.getCell(2).toString() + rdmInt;
			//step6:Close the Workbook
			wb.close();*/
			
	      /* WebDriver driver=null;
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
	       
	       
	       
	       //click on save button
	       driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
	       
	       //verify Header message expected result
	       String headerInfo  = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	       if(headerInfo.contains(lastname)) {
	    	   System.out.println(lastname + " information is Verified==PASS");
	       }else {
	    	   System.out.println(lastname + " information is not Verified==FAIL");   
	       }
	       
	       String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
	       if(actLastName.equals(lastname)) {
	    	  System.out.println(lastname + " information is verified==PASS");  
	       }
	       else {
	    	   System.out.println(lastname + " information is not verified==FAIL");
	       }
	       
	       driver.quit();*/
	       
	}

}
