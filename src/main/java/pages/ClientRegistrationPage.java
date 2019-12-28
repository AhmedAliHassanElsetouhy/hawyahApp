package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClientRegistrationPage extends PageBase {

	public ClientRegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[text()='إنشاء حساب في هوية']")
	public WebElement registerFormHeaderTxt;

	@FindBy(xpath = "//*[text()='تسجيل']")
	WebElement registerBtn;

	@FindBy(id = "user_email")
	public WebElement emailTxtBox;

	@FindBy(id = "user_password")
	public WebElement passwordTxtBox;

	@FindBy(id = "user_password_confirmation")
	public WebElement confirmPasswordTxtBox;

	@FindBy(id = "user_screen_name")
	public WebElement userNameTxtBox;

	@FindBy(xpath = "//label[@for='user_type_1']")
	WebElement clientRadioBtn;

	@FindBy(xpath = "//label[@for='user_type_2']")
	WebElement designerRadioBtn;

	@FindBy(xpath = "//label[@for='user_terms_and_conditions']")
	WebElement termsAndConditionsCheckBox;

	@FindBy(xpath = "//*[text()='يجب تحديد احد الخيارات ┊ احتاج الى تصميم ┊ انا مصمم/مصممة']")
	public WebElement userTypeValidationMsg;

	public void submitRegisterfun() {
		clickButton(registerBtn);
	}

	public void registerFun(String email, String password, String userName) {
		setTextElementText(emailTxtBox, email);
		setTextElementText(passwordTxtBox, password);
		setTextElementText(confirmPasswordTxtBox, password);
		setTextElementText(userNameTxtBox, userName);
	}

	public void clientRegister() {
		clickButton(clientRadioBtn);
	}

	public void designerRegister() {
		clickButton(designerRadioBtn);
	}

	public void checkTermsAndCondition() {
		clickButton(termsAndConditionsCheckBox);
	}

}
