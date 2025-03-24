package Com.ComCast.CRM.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Com.ComCast.CRM.Generic.WebDriverUtility.WebDriverUtility;
/**
 * 
 * @author Renuka
 * 
 * Contains Login page elements & business library like login()
 */
public class LoginPage extends WebDriverUtility {

	//Rule-1 create a separate java class
	//Rule-2 Object creation
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	 @FindBy(name="user_name")
	 private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	//Rule-3:Object Initialization
	
		//Rule-4 Object Encapsulation

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	/**
	 * login to application based on username, password arguments
	 * @param username
	 * @param password
	 */
	//Rule-5:Provide Action
	public void loginToApp(String username,String password) {
		waitForPageToLoad(driver);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
	
	
	
}
