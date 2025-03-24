package practice_testcases;

import java.time.Duration;

import org.apache.poi.ddf.EscherColorRef.SysIndexSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class CreateOrganizationWith_Industries_Type_Testcase_02 {

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
		
		 //read testscript data from Excel file
	       String orgName=elib.getDatafromExcel("org3", 4, 2) + jlib.getRandomNumber();
	       String industry =elib.getDatafromExcel("org3", 4, 3);
	       String type=elib.getDatafromExcel("org3", 4, 4);
	       
		 /*FileInputStream fis=new FileInputStream("./ConfigAppData/commondata.properties");
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
			Row row=sh.getRow(4);
			//step5:get the control of the 2nd cell and read the String data
			String orgName=row.getCell(2).toString() + rdmInt;
			String industry=row.getCell(3).toString();
			String type=row.getCell(4).toString();
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
	       driver.get(URL);
	       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	       //login to app
	       LoginPage lp=new LoginPage(driver);
	       lp.loginToApp(USN, PSW);
	       
	       //navigate to organization module 
	       HomePage hp=new HomePage(driver);
	       hp.getOrganisationLink().click();
	       
	     //click on create organization button
	       OrganizationsPage op=new OrganizationsPage(driver);
	       op.getCreateOrganisationBtn().click();
	     
	       //click on create organization button
	       CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
	       cnop.getOrgNameEdt().sendKeys(orgName);
	       
	       WebElement ele1=cnop.getIndustryDropdown();
	       wlib.selectText(ele1, industry);
	       WebElement ele2=cnop.getTypeDropdown();
	       wlib.selectText(ele2, type);
	       cnop.getSaveBtn().click();
	       
	         OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		      String actIndustryName =oip.getIndustryInfo().getText();
		      if(actIndustryName.contains(industry)) {
		    	  System.out.println(industry + " name is verified==PASS");
		      }
		      else {
		    	  System.out.println(industry + " name is not verified==FAIL");
		      }
	          String actTypeName=oip.getTypeInfo().getText();
	          if(actTypeName.contains(type)) {
	        	  System.out.println(type + " name is verified==PASS");
	          }
	          else {
	        	  System.out.println(type + " name is not verified==FAIL");
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
	       WebElement wbsele = driver.findElement(By.name("industry"));
	       Select sel1=new Select(wbsele);
	       sel1.selectByVisibleText(industry);
	       
	       WebElement wbsele1 = driver.findElement(By.name("accounttype"));
	       Select sel2=new Select(wbsele1);
	       sel2.selectByVisibleText(type);
	        //click on save button
	       driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
	       
	       //verify the industries and type info 
	       String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
	       if(actIndustries.contains(industry)) {
	    	   System.out.println(industry + " information is verified==PASS");
	       }else {
	    	   System.out.println(industry + " information is not verified==FAIL");   
	       }
	       
	       String actType = driver.findElement(By.id("dtlview_Type")).getText();
	       if(actType.equals(type)) {
	    	  System.out.println(type + " information is verified==PASS");  
	       }
	       else {
	    	   System.out.println(type + " information is not verified==FAIL");
	       }
	       //Thread.sleep(2000);
	       //logout from app
	       //Actions act=new Actions(driver);
	       //act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
	      // driver.findElement(By.linkText("Sign Out")).click();
	       driver.quit();*/
	}

}
