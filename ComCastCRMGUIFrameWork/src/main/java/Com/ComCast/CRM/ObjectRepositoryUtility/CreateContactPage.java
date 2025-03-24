package Com.ComCast.CRM.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {
   WebDriver driver;
   public CreateContactPage(WebDriver driver) {
	   this.driver=driver;
	   PageFactory.initElements(driver, this);
   }


@FindBy(xpath="//img[@title='Create Contact...']")
private WebElement CreateNewContactBtn;
@FindBy(xpath="//input[@name='lastname']")
private WebElement LastNameTextField;
@FindBy(xpath="//input[@class='detailedViewTextBoxOn']")
private WebElement OfficePhoneTextField;
@FindBy(xpath="//input[@class='crmButton small save']")
private WebElement SaveButton;
@FindBy (xpath="//input[@name='support_start_date']")
private WebElement StartDate;
@FindBy(xpath="//input[@name='support_end_date']")
private WebElement EndDate;

public WebElement getEndDate() {
	return EndDate;
}
public WebElement getStartDate() {
	return StartDate;
}
public WebDriver getDriver() {
	return driver;
}
public WebElement getCreateNewContactBtn() {
	return CreateNewContactBtn;
}
public WebElement getLastNameTextField() {
	return LastNameTextField;
}
public WebElement getOfficePhoneTextField() {
	return OfficePhoneTextField;
}
public WebElement getSaveButton() {
	return SaveButton;
}


}