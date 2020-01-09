package designerPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class AboutMeDesignerPage extends PageBase {

	public AboutMeDesignerPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "designer_first_name")
	WebElement fNameTxtFieldDes;

	@FindBy(id = "designer_last_name")
	WebElement lNameTxtFieldDes;

	@FindBy(id = "designer_screen_name")
	WebElement designerScreenNameFieldDes;

	@FindBy(id = "designer_bio")
	WebElement designerBioFieldDes;

	@FindBy(id = "designer_city")
	WebElement cityTxtFieldDes;

	@FindBy(id = "designer_phone_number")
	WebElement phoneTxtFieldDes;

	// @FindBy(xpath = "//a[@class='chosen-single chosen-single-with-deselect']")
	@FindBy(id = "designer_country_id_chosen")
	WebElement cityLstDes;

	@FindBy(xpath = "//input[@class='chosen-search-input']")
	WebElement cityOptionTextBoxDes;

	@FindBy(xpath = "//input[@value='حفظ']")
	public WebElement saveBtnDes;

	@FindBy(id = "designer_address")
	WebElement addressTxtBoxDes;

	public void personalDesignerDataForm(String fNameDes, String lNameDes, String screenNameDes, String designerBioDes,
			String cityDes, String phoneDes, String cityOptionDes, String addressDes) {
		fNameTxtFieldDes.clear();
		setTextElementText(fNameTxtFieldDes, fNameDes);
		lNameTxtFieldDes.clear();
		setTextElementText(lNameTxtFieldDes, lNameDes);
		designerScreenNameFieldDes.clear();
		setTextElementText(designerScreenNameFieldDes, screenNameDes);
		designerBioFieldDes.clear();
		setTextElementText(designerBioFieldDes, designerBioDes);

		cityTxtFieldDes.clear();
		setTextElementText(cityTxtFieldDes, cityDes);
		phoneTxtFieldDes.clear();
		setTextElementText(phoneTxtFieldDes, phoneDes);

		addressTxtBoxDes.clear();
		setTextElementText(addressTxtBoxDes, addressDes);

		clickButton(cityLstDes);
		cityOptionTextBoxDes.clear();
		setTextElementText(cityOptionTextBoxDes, cityOptionDes);
		cityOptionTextBoxDes.sendKeys(Keys.ENTER);
	}

	public void saveDataFun() {
		saveBtnDes.submit();
	}

	@FindBy(partialLinkText = "البيانات الشخصية")
	public WebElement personalDataLinkDes;

	@FindBy(partialLinkText = "بيانات الدخول")
	WebElement loginDataLinkDes;

	@FindBy(partialLinkText = "الحسابات البنكية")
	WebElement bankAccountLinkDes;

	public void openPersonalDataFun() {
		clickButton(personalDataLinkDes);
	}

	public void openBankAccountFun() {
		clickButton(bankAccountLinkDes);
	}

	@FindBy(id = "designer_current_password")
	WebElement currentPassTxtBoxDes;

	@FindBy(id = "designer_password")
	WebElement userPassTxtBoxDes;

	@FindBy(id = "designer_password_confirmation")
	WebElement confirmPassTxtBoxDes;

	public void updateLoginDataForm(String currentPassDes, String newPassDes, String confirmNewPassDes) {
		currentPassTxtBoxDes.clear();
		setTextElementText(currentPassTxtBoxDes, currentPassDes);
		currentPassTxtBoxDes.clear();
		setTextElementText(userPassTxtBoxDes, newPassDes);
		confirmPassTxtBoxDes.clear();
		setTextElementText(confirmPassTxtBoxDes, confirmNewPassDes);
	}

	@FindBy(id = "designer_account_iban_number")
	WebElement ibanNumbertxtBoxDes;

	@FindBy(id = "designer_account_account_owner_name")
	WebElement accountOwnerNameTxtBoxDes;

	@FindBy(id = "designer_account_bank_name")
	WebElement bankAccountTxtBoxDes;

	@FindBy(xpath = "//*[text()='لم يتم تحديث بياناتك']")
	WebElement bankAccountValidationMsgDes;

	public void updateBankAccountDataForm(String ibanNumber, String accountOwnerName, String bankAccount) {
		ibanNumbertxtBoxDes.clear();
		setTextElementText(ibanNumbertxtBoxDes, ibanNumber);
		accountOwnerNameTxtBoxDes.clear();
		setTextElementText(accountOwnerNameTxtBoxDes, accountOwnerName);
		bankAccountTxtBoxDes.clear();
		setTextElementText(bankAccountTxtBoxDes, bankAccount);
		bankAccountTxtBoxDes.sendKeys(Keys.ENTER);
		// if (!bankAccountValidationMsg.isDisplayed()) {
		// } else {
		// openBankAccountFun();
		// ibanNumbertxtBox.clear();
		// setTextElementText(ibanNumbertxtBox, ibanNumber);
		// accountOwnerNameTxtBox.clear();
		// setTextElementText(accountOwnerNameTxtBox, accountOwnerName);
		// bankAccountTxtBox.clear();
		// setTextElementText(bankAccountTxtBox, bankAccount);
		// bankAccountTxtBox.sendKeys(Keys.ENTER);
		// }
	}

	@FindBy(xpath = "//div[@class='green lighten-5']")
	public WebElement bankAccConfirmMsgDes;

}