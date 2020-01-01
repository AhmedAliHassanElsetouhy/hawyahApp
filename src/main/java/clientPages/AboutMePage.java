package clientPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AboutMePage extends PageBase {

	public AboutMePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(partialLinkText = "البيانات الشخصية")
	public WebElement personalDataLink;

	@FindBy(partialLinkText = "بيانات الدخول")
	WebElement loginDataLink;

	public void openPersonalDataFun() {
		clickButton(personalDataLink);
	}

	public void loginDataFun() {
		clickButton(loginDataLink);
	}

	@FindBy(id = "user_first_name")
	WebElement fNameTxtField;

	@FindBy(id = "user_last_name")
	WebElement lNameTxtField;

	@FindBy(id = "user_city")
	WebElement cityTxtField;

	@FindBy(id = "user_phone_number")
	WebElement phoneTxtField;

	@FindBy(xpath = "//a[@class='chosen-single chosen-single-with-deselect']")
	WebElement cityLst;

	@FindBy(xpath = "//input[@class='chosen-search-input']")
	WebElement cityOptionTextBox;

	@FindBy(id = "user_current_password")
	WebElement currentPassTxtBox;

	@FindBy(id = "user_password")
	WebElement userPassTxtBox;

	@FindBy(id = "user_password_confirmation")
	WebElement confirmPassTxtBox;

	@FindBy(xpath = "//input[@value='حفظ']")
	WebElement saveBtn;

	public void personalDataForm(String fName, String lName, String city, String phone, String cityOption) {
		fNameTxtField.clear();
		setTextElementText(fNameTxtField, fName);
		lNameTxtField.clear();
		setTextElementText(lNameTxtField, lName);
		cityTxtField.clear();
		setTextElementText(cityTxtField, city);
		phoneTxtField.clear();
		setTextElementText(phoneTxtField, phone);

		clickButton(cityLst);
		cityOptionTextBox.clear();
		setTextElementText(cityOptionTextBox, cityOption);
		cityOptionTextBox.sendKeys(Keys.ENTER);
	}

	public void updateLoginDataForm(String currentPass, String newPass, String confirmNewPass) {
		currentPassTxtBox.clear();
		setTextElementText(currentPassTxtBox, currentPass);
		currentPassTxtBox.clear();
		setTextElementText(userPassTxtBox, newPass);
		confirmPassTxtBox.clear();
		setTextElementText(confirmPassTxtBox, confirmNewPass);
	}

	public void saveDataFun() {
		saveBtn.submit();
	}

}
