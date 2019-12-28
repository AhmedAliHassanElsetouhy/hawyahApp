package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AboutMeUserPage extends PageBase {

	public AboutMeUserPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "designer_first_name")
	WebElement fNameTxtField;

	@FindBy(id = "designer_last_name")
	WebElement lNameTxtField;

	@FindBy(id = "designer_screen_name")
	WebElement designerScreenNameField;

	@FindBy(id = "designer_bio")
	WebElement designerBioField;

	@FindBy(id = "designer_city")
	WebElement cityTxtField;

	@FindBy(id = "designer_phone_number")
	WebElement phoneTxtField;

	// @FindBy(xpath = "//a[@class='chosen-single chosen-single-with-deselect']")
	@FindBy(id = "designer_country_id_chosen")
	WebElement cityLst;

	@FindBy(xpath = "//input[@class='chosen-search-input']")
	WebElement cityOptionTextBox;

	@FindBy(xpath = "//input[@value='حفظ']")
	WebElement saveBtn;

	public void personalDesignerDataForm(String fName, String lName, String screenName, String designerBio, String city,
			String phone, String cityOption) {
		fNameTxtField.clear();
		setTextElementText(fNameTxtField, fName);
		lNameTxtField.clear();
		setTextElementText(lNameTxtField, lName);
		designerScreenNameField.clear();
		setTextElementText(designerScreenNameField, screenName);
		designerBioField.clear();
		setTextElementText(designerBioField, designerBio);

		cityTxtField.clear();
		setTextElementText(cityTxtField, city);
		phoneTxtField.clear();
		setTextElementText(phoneTxtField, phone);

		clickButton(cityLst);
		cityOptionTextBox.clear();
		setTextElementText(cityOptionTextBox, cityOption);
		cityOptionTextBox.sendKeys(Keys.ENTER);
	}

	public void saveDataFun() {
		saveBtn.submit();
	}

	@FindBy(partialLinkText = "البيانات الشخصية")
	public WebElement personalDataLink;

	@FindBy(partialLinkText = "بيانات الدخول")
	WebElement loginDataLink;

	@FindBy(partialLinkText = "الحسابات البنكية")
	WebElement bankAccountLink;

	public void openPersonalDataFun() {
		clickButton(personalDataLink);
	}

	public void openBankAccountFun() {
		clickButton(bankAccountLink);
	}

	@FindBy(id = "designer_current_password")
	WebElement currentPassTxtBox;

	@FindBy(id = "designer_password")
	WebElement userPassTxtBox;

	@FindBy(id = "designer_password_confirmation")
	WebElement confirmPassTxtBox;

	public void updateLoginDataForm(String currentPass, String newPass, String confirmNewPass) {
		currentPassTxtBox.clear();
		setTextElementText(currentPassTxtBox, currentPass);
		currentPassTxtBox.clear();
		setTextElementText(userPassTxtBox, newPass);
		confirmPassTxtBox.clear();
		setTextElementText(confirmPassTxtBox, confirmNewPass);
	}

	@FindBy(id = "designer_account_iban_number")
	WebElement ibanNumbertxtBox;

	@FindBy(id = "designer_account_account_owner_name")
	WebElement accountOwnerNameTxtBox;

	@FindBy(id = "designer_account_bank_name")
	WebElement bankAccountTxtBox;

	public void updateBankAccountDataForm(String ibanNumber, String accountOwnerName, String bankAccount) {
		ibanNumbertxtBox.clear();
		setTextElementText(ibanNumbertxtBox, ibanNumber);
		accountOwnerNameTxtBox.clear();
		setTextElementText(accountOwnerNameTxtBox, accountOwnerName);
		bankAccountTxtBox.clear();
		setTextElementText(bankAccountTxtBox, bankAccount);
	}
	
	@FindBy(xpath="//div[@class='green lighten-5']")
	public WebElement bankAccConfirmMsg;

}