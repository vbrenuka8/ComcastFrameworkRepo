package Com.ComCast.CRM.ProductTest;


import org.testng.annotations.Test;import com.aventstack.extentreports.Status;

import Com.ComCast.CRM.Generic.BaseUtility.BaseClass;
import Com.ComCast.CRM.Generic.WebDriverUtility.UtilityClassObject;

public class CreateProductTest extends BaseClass {
	
	@Test
	public void createProduct() throws Exception {
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		/* read testscript data from Excel file*/
		String prodName=elib.getDatafromExcel("product", 6, 2) + jlib.getRandomNumber();
		
		/* navigate to product module*/
				 UtilityClassObject.getTest().log(Status.INFO, "navigate to Org Page");
	}

}
