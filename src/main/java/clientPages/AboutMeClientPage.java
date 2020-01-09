package clientPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AboutMeClientPage extends PageBase {

	public AboutMeClientPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(partialLinkText = "البيانات الشخصية")
	public WebElement personalDataLinkCli;

	@FindBy(partialLinkText = "بيانات الدخول")
	WebElement loginDataLinkCli;

	public void openPersonalDataFun() {
		clickButton(personalDataLinkCli);
	}

	public void loginDataFun() {
		clickButton(loginDataLinkCli);
	}

	@FindBy(id = "user_first_name")
	WebElement fNameTxtFieldCli;

	@FindBy(id = "user_last_name")
	WebElement lNameTxtFieldCli;

	@FindBy(id = "user_city")
	WebElement cityTxtFieldCli;

	@FindBy(id = "user_phone_number")
	WebElement phoneTxtFieldCli;

	@FindBy(xpath = "//a[@class='chosen-single chosen-single-with-deselect']")
	WebElement cityLstCli;

	@FindBy(xpath = "//input[@class='chosen-search-input']")
	WebElement cityOptionTextBoxCli;

	@FindBy(id = "user_current_password")
	WebElement currentPassTxtBoxCli;

	@FindBy(id = "user_password")
	WebElement userPassTxtBoxCli;

	@FindBy(id = "user_password_confirmation")
	WebElement confirmPassTxtBoxCli;

	@FindBy(xpath = "//input[@value='حفظ']")
	WebElement saveBtnCli;

	public void personalDataForm(String fNameCli, String lNameCli, String cityCli, String phoneCli,
			String cityOptionCli) {
		fNameTxtFieldCli.clear();
		setTextElementText(fNameTxtFieldCli, fNameCli);
		lNameTxtFieldCli.clear();
		setTextElementText(lNameTxtFieldCli, lNameCli);
		cityTxtFieldCli.clear();
		setTextElementText(cityTxtFieldCli, cityCli);
		phoneTxtFieldCli.clear();
		setTextElementText(phoneTxtFieldCli, phoneCli);

		clickButton(cityLstCli);
		cityOptionTextBoxCli.clear();
		setTextElementText(cityOptionTextBoxCli, cityOptionCli);
		cityOptionTextBoxCli.sendKeys(Keys.ENTER);
	}

	public void updateLoginDataForm(String currentPassCli, String newPassCli, String confirmNewPassCli) {
		currentPassTxtBoxCli.clear();
		setTextElementText(currentPassTxtBoxCli, currentPassCli);
		currentPassTxtBoxCli.clear();
		setTextElementText(userPassTxtBoxCli, newPassCli);
		confirmPassTxtBoxCli.clear();
		setTextElementText(confirmPassTxtBoxCli, confirmNewPassCli);
	}

	public void saveDataFun() {
		saveBtnCli.submit();
	}
}