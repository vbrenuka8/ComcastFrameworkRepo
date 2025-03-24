package Practice_DataProvider;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Com.ComCast.CRM.Generic.FileUtility.ExcelUtility;

public class GetProductInfo_Test {

	@Test(dataProvider="getData")
	public void getProductInfoTest(String brandName, String productName) {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']")).sendKeys(brandName,Keys.ENTER);
		String x="//div[text( )='"+productName+"']/../../"
				+ "div[@class='col col-5-12 BfVC2z']/div[1]/div[1]/div[@class='Nx9bqj _4b5DiR']";
	    String price=driver.findElement(By.xpath(x)).getText();
	    System.out.println(price);
	}
	    @DataProvider
	    public Object[][]getData() throws Exception{
	    	ExcelUtility elib=new ExcelUtility();
	    	int rowCount=elib.getRowCount("product");
	    	
	    	Object[][] objArr=new Object[rowCount][2];
	    	
	    	for(int i=0; i<rowCount; i++) {
	    	
	    	objArr[i][0]=elib.getDatafromExcel("product", i+1, 0);
	    	objArr[i][1]=elib.getDatafromExcel("product", i+1, 1);
	    	}
	    	
	    	return objArr;
	    	
	   	
	    
    }
}