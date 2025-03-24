package Com.ComCast.CRM.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	WebDriver driver;
	public OrganizationsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement CreateOrganisationBtn;
	@FindBy(xpath = "//img[@title='Search in Organizations...']")
	private WebElement SearchOrganisationBtn;
	@FindBy(xpath = "//img[@title='Open Calendar']")
	private WebElement CalenderBtn;
	@FindBy(xpath = "//img[@title='Show World Clock...']")
	private WebElement WorldClockBtn;
	@FindBy(xpath = "//img[@title='Open Calculator...']")
	private WebElement CalculatorBtn;
	@FindBy(xpath = "//img[@title='Chat...']")
	private WebElement ChatBtn;
	@FindBy(name = "search_text")
	private WebElement SearchTextField;
	@FindBy(name ="search_field" )
	private WebElement SerachDropDown;
	@FindBy(xpath="//input[@name='submit']")
	private WebElement SerchNowBtn;
	public WebDriver getDriver() {
		return driver;
	}
	public WebElement getCreateOrganisationBtn() {
		return CreateOrganisationBtn;
	}
	public WebElement getSearchOrganisationBtn() {
		return SearchOrganisationBtn;
	}
	public WebElement getCalenderBtn() {
		return CalenderBtn;
	}
	public WebElement getWorldClockBtn() {
		return WorldClockBtn;
	}
	public WebElement getCalculatorBtn() {
		return CalculatorBtn;
	}
	public WebElement getChatBtn() {
		return ChatBtn;
	}
	public WebElement getSearchTextField() {
		return SearchTextField;
	}
	public WebElement getSerachDropDown() {
		return SerachDropDown;
	}
	public WebElement getSerchNowBtn() {
		return SerchNowBtn;
	}
}

