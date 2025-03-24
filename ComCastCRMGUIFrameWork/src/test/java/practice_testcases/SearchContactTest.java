package practice_testcases;

import org.testng.annotations.Test;

import Com.ComCast.CRM.Generic.BaseUtility.BaseClass;
import Com.ComCast.CRM.ObjectRepositoryUtility.LoginPage;

/**
 * test class for contact module
 * 
 * @author Renuka
 * 
 */
public class SearchContactTest extends BaseClass {
	/**
	 * scenario: login()===>navigateContact==>createcontact()===>verify
	 */

	@Test
	public void searchcontactTest() {
		/*step 1: login to app*/
       LoginPage lp= new LoginPage(driver);
       lp.loginToApp("username", "password");
	}
}
