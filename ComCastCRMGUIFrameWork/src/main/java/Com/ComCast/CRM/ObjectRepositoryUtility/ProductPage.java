package Com.ComCast.CRM.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	WebDriver driver;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@title='Create Product...']")
	private WebElement CreateNewProductBtn;
	@FindBy(name = "productname")
	private WebElement ProductNameTextField;
	@FindBy(name = "unit_price")
	private WebElement UnitPriceTextField;
	@FindBy(xpath = "//input[@name='qtyinstock']")
	private WebElement QuantityInStockTextField;
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement SaveButton;
	@FindBy(xpath = "//select[@name='usageunit']")
	private WebElement UsageUnitDropDown;

	public WebElement getUsageUnitDropDown() {
		return UsageUnitDropDown;
	}

	public WebElement getSaveButton() {
		return SaveButton;
	}

	public WebElement getQuantityInStockTextField() {
		return QuantityInStockTextField;
	}

	public WebElement getUnitPriceTextField() {
		return UnitPriceTextField;
	}

	public WebElement getProductNameTextField() {
		return ProductNameTextField;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getCreateNewProductBtn() {
		return CreateNewProductBtn;
	}

}
