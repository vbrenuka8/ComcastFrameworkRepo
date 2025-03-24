package Com.ComCast.CRM.ObjectRepositoryUtility;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AutoHeal_Pom_FindAll {

	@FindBy(name="user_name")
	WebElement ele1;
	@FindBy(name="user_password")
	WebElement ele2;
	 @FindAll({@FindBy(id="submitButton"), @FindBy(xpath="//input[@value='Login']")})
	  private WebElement ele3;
	
	@Test
	public void AutoHeal() {
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888");
		
		SimpleTestWithPom s=PageFactory.initElements(driver,SimpleTestWithPom.class);
		s.ele1.sendKeys("admin");
		s.ele2.sendKeys("admin");
		
		driver.navigate().refresh();
		
		//here elements take the latest address and perform the action
		s.ele1.sendKeys("admin");
		s.ele2.sendKeys("admin");
		s.ele3.click();
		
		
	}
}
