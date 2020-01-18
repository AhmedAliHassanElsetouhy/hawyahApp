package adminPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class DesignerProfileAdminPage extends PageBase {

	public DesignerProfileAdminPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[@value='Submit']")
	public WebElement submitBtnDes;

	@FindBy(id = "designer_first_name")
	WebElement fNameTxtBoxDes;

	@FindBy(id = "designer_last_name")
	WebElement lNameTxtBoxDes;

	@FindBy(id = "designer_bio")
	WebElement bioTxtBoxDes;

	@FindBy(id = "designer_country_id")
	WebElement countryListDes;

	@FindBy(id = "designer_region")
	WebElement regionTxtBoxDes;

	@FindBy(id = "designer_city")
	WebElement cityTxtBoxDes;

	@FindBy(id = "designer_address")
	WebElement addTxtBoxDes;

	@FindBy(id = "designer_account_status")
	WebElement statusListDes;

	@FindBy(xpath = "//div[@class='flash_alert']")
	public WebElement updateAlertSuccessMsgDes;

	public void submitFunDes() {
		clickButton(submitBtnDes);
	}

	public void designerProfileStatus() {
		selectItemWithVisible(statusListDes, "Disabled");
	}

	public void updateProfileFunDes(String fNameDes, String lNameDes, String bioDes, int countryIndexDes,
			String regionDes, String cityDes, String addDes, int statusIndexDes) {
		setTextElementText(fNameTxtBoxDes, fNameDes);
		setTextElementText(lNameTxtBoxDes, lNameDes);
		setTextElementText(bioTxtBoxDes, bioDes);
		selectItemWithIndex(countryListDes, countryIndexDes);
		setTextElementText(regionTxtBoxDes, regionDes);
		setTextElementText(cityTxtBoxDes, cityDes);
		setTextElementText(addTxtBoxDes, addDes);
		selectItemWithIndex(statusListDes, statusIndexDes);
	}
}