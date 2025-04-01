package Com.ComCast.CRM.ContactTest;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import Com.ComCast.CRM.Generic.BaseUtility.BaseClass;
import Com.ComCast.CRM.Generic.WebDriverUtility.UtilityClassObject;
import Com.ComCast.CRM.ObjectRepositoryUtility.ContactInfoPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.CreateContactPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.CreateNewOrganizationPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.HomePage;
import Com.ComCast.CRM.ObjectRepositoryUtility.OrganizationInfoPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.OrganizationsPage;
/**
 * 
 * @author Renuka
 * 
 */
public class CreateContactTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createContact() throws Throwable {
		 //UtilityClassObject.getTest().log(Status.INFO, "read data from Excel");
		/* read testscript data from Excel file*/
		String lastname = elib.getDatafromExcel("contact", 1, 2) + jlib.getRandomNumber();

		// navigate to organization module
		//UtilityClassObject.getTest().log(Status.INFO, "navigate to Contact Page");
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// navigate to create contact page
		//UtilityClassObject.getTest().log(Status.INFO, "navigate to create new  contact");
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.getCreateNewContactBtn().click();
		ccp.getLastNameTextField().sendKeys(lastname);
		ccp.getSaveButton().click();

		// verification of header information
		ContactInfoPage cinf = new ContactInfoPage(driver);

		String actHeaderInfo = cinf.getContactHeaderInfo().getText();
		boolean status = actHeaderInfo.contains(lastname);
		Assert.assertEquals(status, true);

		/*
		 * if (actHeaderInfo.contains(lastname)) { System.out.println(lastname +
		 * " lastname is verified==PASS"); } else { System.out.println(lastname +
		 * " lastname is not verified==FAIL"); }
		 */

	}

	@Test(groups = "regressionTest")
	public void createContactWithSupportStartDateAndEndDateTest() throws Exception {
		// read test script data from Excel file
		String lastname = elib.getDatafromExcel("contact", 4, 2) + jlib.getRandomNumber();
		// capture date using java utility
		String startDate = jlib.getSystemDateYYYYDDMM();
		String endDate = jlib.getRequiredDateYYYYDDMM(30);

		// navigate to HomePage
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// Navigate to Organization Module
		CreateContactPage cp = new CreateContactPage(driver);
		cp.getCreateNewContactBtn().click();
		cp.getLastNameTextField().sendKeys(lastname);
		cp.getStartDate().clear();
		cp.getStartDate().sendKeys(startDate);
		cp.getEndDate().clear();
		cp.getEndDate().sendKeys(endDate);
		cp.getSaveButton().click();

		// Verify Header the Expected Result
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
        SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastName, lastname);

		/*
		 * if (actLastName.contains(lastname)) { System.out.println(lastname +
		 * " informatoin is Verified==PASS"); } else { System.out.println(lastname +
		 * " information is not verified==FAIL"); }
		 */
		// Verify the Start date
		String StartDate = driver.findElement(By.xpath("//span[@id='dtlview_Support Start Date']")).getText();
		boolean status1 = StartDate.contains(startDate);
		Assert.assertEquals(status1, true);

		/*
		 * if (StartDate.equals(startDate)) { System.out.println(startDate +
		 * " is Verified==PASS"); } else { System.out.println(startDate +
		 * " is not verified==FAIL"); }
		 */
		// Verify the End date

		String EndDate = driver.findElement(By.xpath("//span[@id='dtlview_Support End Date']")).getText();
		boolean status2 = EndDate.contains(endDate);
		Assert.assertEquals(status2, true);

		/*
		 * if (EndDate.equals(endDate)) { System.out.println(endDate +
		 * " is Verified==PASS"); } else { System.out.println(endDate +
		 * " is not verified==FAIL"); }
		 */

	}

	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws Exception {

		// read test script data from Excel file
		String orgName = elib.getDatafromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String lastname = elib.getDatafromExcel("contact", 7, 3) + jlib.getRandomNumber();

		// navigate to Homepage
		HomePage hp = new HomePage(driver);
		hp.getOrganisationLink().click();

		// organization page
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrganisationBtn().click();

		// Navigate to create new organization page
		CreateNewOrganizationPage cop = new CreateNewOrganizationPage(driver);
		cop.getOrgNameEdt().sendKeys(orgName);
		cop.getSaveBtn().click();

		// verify header information
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgHeaderInfo = oip.getOrgHeaderInfo().getText();
		boolean orgn = orgHeaderInfo.contains(orgName);
		Assert.assertEquals(orgn, true);

		/*
		 * String headerInfo =
		 * driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText(); if
		 * (headerInfo.contains(orgName)) { System.out.println(orgName +
		 * " is created==PASS"); } else { System.out.println(orgName +
		 * " is not created==FAIL"); }
		 */

		// navigate to contact module
		hp.getContactLink().click();

		// create new contact
		CreateContactPage cp = new CreateContactPage(driver);
		cp.getCreateNewContactBtn().click();
		cp.getLastNameTextField().sendKeys(lastname);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// Switch to child window
		wlib.switchToWindowByURL(driver, "?module=Accounts&action");
		Thread.sleep(2000);

		cop.getOrganizationSearchTextField().sendKeys(orgName);
		WebElement ele2 = cop.getOrganizationDropDown();
		wlib.selectText(ele2, "Organization Name");
		cop.getSearchNowButton().click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// switch to parent window
		wlib.switchToWindowByURL(driver, "?module=Contacts&action");
		cp.getSaveButton().click();

		// verify header information
		ContactInfoPage cip = new ContactInfoPage(driver);
		WebElement headerInfo2 = cip.getContactHeaderInfo();
		String headerInfo3 = headerInfo2.getText();

		String actHeaderInfor = cip.getContactHeaderInfo().getText();
		boolean lastn = actHeaderInfor.contains(lastname);
		Assert.assertEquals(lastn, true);

		/*
		 * if (headerInfo3.contains(lastname)) { System.out.println(lastname +
		 * " is created==PASS"); } else { System.out.println(lastname +
		 * " is not created==FAIL"); }
		 */

	}

}
