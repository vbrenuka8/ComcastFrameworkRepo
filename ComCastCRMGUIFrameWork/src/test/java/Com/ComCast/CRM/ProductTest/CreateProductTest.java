package Com.ComCast.CRM.ProductTest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Com.ComCast.CRM.Generic.BaseUtility.BaseClass;
import Com.ComCast.CRM.ObjectRepositoryUtility.HomePage;
import Com.ComCast.CRM.ObjectRepositoryUtility.ProductInfoPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.ProductPage;

public class CreateProductTest extends BaseClass {

	@Test
	public void createProduct() throws Exception {
		// UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		/* read testscript data from Excel file */
		String product = elib.getDatafromExcel("products", 1, 1) + jlib.getRandomNumber();
		String price = elib.getDatafromExcel("products", 1, 2);
		String stock = elib.getDatafromExcel("products", 1, 3);
		String usage = elib.getDatafromExcel("products", 1, 4);

		/* navigate to product module */
		// UtilityClassObject.getTest().log(Status.INFO, "navigate to Product Page");
		HomePage hp = new HomePage(driver);
		hp.getProductsLink().click();

		/* navigate to create new product */
		ProductPage pg = new ProductPage(driver);
		pg.getCreateNewProductBtn().click();
		pg.getProductNameTextField().sendKeys(product);
		pg.getUnitPriceTextField().sendKeys(price);

		WebElement ele1 = pg.getUsageUnitDropDown();
		wlib.selectText(ele1, usage);
		// pg.getUsageUnitDropDown().getText();
		pg.getQuantityInStockTextField().sendKeys(stock);
		pg.getSaveButton().click();

		/* Verification of Header Information */
		//ProductInfoPage pinf = new ProductInfoPage(driver);
		//String pdctHeaderName = pinf.getProductHeaderInfo().getText();
		//boolean p = pdctHeaderName.contains(product);
		//Assert.assertEquals(p, true);

		/*
		 * VenderInfoPage vip = new VenderInfoPage(driver); String headName =
		 * vip.getHeaderInfo().getText(); boolean a = headName.contains(vendorName);
		 * Assert.assertEquals(a, true);
		 */

		
	}

}
