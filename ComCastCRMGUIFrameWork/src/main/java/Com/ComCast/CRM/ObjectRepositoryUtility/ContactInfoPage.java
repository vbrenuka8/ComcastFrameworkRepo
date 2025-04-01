package Com.ComCast.CRM.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
  WebDriver driver;
  public ContactInfoPage(WebDriver driver) {
	  this.driver=driver;
	  PageFactory.initElements(driver, this);
  }
  @FindBy(xpath="//span[@class='dvHeaderText']")
  private WebElement ContactHeaderInfo;
  @FindBy(xpath="//span[@id='dtlview_Last Name']")
  private WebElement LastNameTextAfterSave;
   public WebDriver getDriver() {
	return driver;
}
public WebElement getContactHeaderInfo() {
	return ContactHeaderInfo;
}
public WebElement getLastNameTextAfterSave() {
	return LastNameTextAfterSave;
}
}
