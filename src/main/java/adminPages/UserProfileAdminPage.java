package adminPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class UserProfileAdminPage extends PageBase {

	public UserProfileAdminPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[@value='Submit']")
	public WebElement submitBtn;

	@FindBy(id = "customer_first_name")
	WebElement cusfNameTxtBox;

	@FindBy(id = "customer_last_name")
	WebElement cuslNameTxtBox;

	@FindBy(id = "customer_bio")
	WebElement cusBioTxtBox;

	@FindBy(id = "customer_country_id")
	WebElement cusCountryList;

	@FindBy(id = "customer_region")
	WebElement cusRegionTxtBox;

	@FindBy(id = "customer_city")
	WebElement cusCityTxtBox;

	@FindBy(id = "customer_address")
	WebElement cusAddTxtBox;

	@FindBy(id = "customer_account_status")
	WebElement customerStatusList;

	@FindBy(xpath="//div[@class='flash_alert']")
	public WebElement updateAlertSuccessMsg; 
	
	public void submitFun() {
		clickButton(submitBtn);
	}

	public void customerProfileStatus() {
		selectItemWithVisible(customerStatusList, "Disabled");
	}

	public void updateProfileFun(String cusfName, String cuslName, String cusBio, int cusCountryIndex, String cusRegion,
			String cusCity, String cusAdd, int customerStatusIndex) {
		setTextElementText(cusfNameTxtBox, cusfName);
		setTextElementText(cuslNameTxtBox, cuslName);
		setTextElementText(cusBioTxtBox, cusBio);
		selectItemWithIndex(cusCountryList, cusCountryIndex);
		setTextElementText(cusRegionTxtBox, cusRegion);
		setTextElementText(cusCityTxtBox, cusCity);
		setTextElementText(cusAddTxtBox, cusAdd);
		selectItemWithIndex(customerStatusList, customerStatusIndex);

	}
}