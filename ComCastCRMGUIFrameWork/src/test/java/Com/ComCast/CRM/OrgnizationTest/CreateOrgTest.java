package Com.ComCast.CRM.OrgnizationTest;

import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Com.ComCast.CRM.Generic.BaseUtility.BaseClass;
import Com.ComCast.CRM.Generic.WebDriverUtility.UtilityClassObject;
import Com.ComCast.CRM.ListenerUtility.ListenerImpClass;
import Com.ComCast.CRM.ObjectRepositoryUtility.CreateNewOrganizationPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.HomePage;

import Com.ComCast.CRM.ObjectRepositoryUtility.OrganizationInfoPage;
import Com.ComCast.CRM.ObjectRepositoryUtility.OrganizationsPage;

public class CreateOrgTest extends BaseClass {
	
	@Test (groups="smokeTest")
	public void createOrg() throws Exception {
         //UtilityClassObject.getTest().log(Status.INFO, "read data from Excel");
		// read testscript data from Excel file
		String orgName = elib.getDatafromExcel("org3", 10, 2) + jlib.getRandomNumber();

		// navigate to organization module
		 //UtilityClassObject.getTest().log(Status.INFO, "navigate to Org Page");
		HomePage hp = new HomePage(driver);
		hp.getOrganisationLink().click();

		// click on create organization button 
		//UtilityClassObject.getTest().log(Status.INFO, "navigate to create Org Page");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrganisationBtn().click();

		// enter all details and create a new organization
		//UtilityClassObject.getTest().log(Status.INFO, "Create a new Org");
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.getOrgNameEdt().sendKeys(orgName);
		//UtilityClassObject.getTest().log(Status.INFO, orgName + "====> Craete a new Org");
		cnop.getSaveBtn().click();

		// Verify Header Message Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getOrgHeaderInfo().getText();
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + " name is verified==Pass");
		} else {
			System.out.println(orgName + " name is nit verified==Fail");
		}

	}

	@Test(groups="regressionTest")
	public void createOrganizationWithIndustriesTest() throws Exception {

		// read testscript data from Excel file
		String orgName = elib.getDatafromExcel("org3", 4, 2) + jlib.getRandomNumber();
		String industry = elib.getDatafromExcel("org3", 4, 3);
		String type = elib.getDatafromExcel("org3", 4, 4);
		// navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrganisationLink().click();

		// click on create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrganisationBtn().click();

		// click on create organization button
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.getOrgNameEdt().sendKeys(orgName);

		WebElement ele1 = cnop.getIndustryDropdown();
		wlib.selectText(ele1, industry);
		WebElement ele2 = cnop.getTypeDropdown();
		wlib.selectText(ele2, type);
		cnop.getSaveBtn().click();

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actIndustryName = oip.getIndustryInfo().getText();
		if (actIndustryName.contains(industry)) {
			System.out.println(industry + " name is verified==PASS");
		} else {
			System.out.println(industry + " name is not verified==FAIL");
		}
		String actTypeName = oip.getTypeInfo().getText();
		if (actTypeName.contains(type)) {
			System.out.println(type + " name is verified==PASS");
		} else {
			System.out.println(type + " name is not verified==FAIL");
		}

	}

	@Test(groups="regressionTest")
	public void createOrgnizationWithPhoneNumberTest() throws Exception {

		// read test script data from Excel file
		String orgName = elib.getDatafromExcel("org3", 7, 2) + jlib.getRandomNumber();
		String phonenumber = elib.getDatafromExcel("org3", 7, 3);
		// navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrganisationLink().click();

		// click on create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrganisationBtn().click();

		// enter all details
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.getOrgNameEdt().sendKeys(orgName);
		cnop.getPhoneTxtField().sendKeys(phonenumber);
		cnop.getSaveBtn().click();

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actPhoneNumber = oip.getOrgPhoneInfo().getText();
		if (actPhoneNumber.contains(phonenumber)) {
			System.out.println(phonenumber + " number is verified==PASS");
		} else {
			System.out.println(phonenumber + " number is not verified==FAIL");
		}
	}
}
