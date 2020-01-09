package clientPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationClientPage extends PageBase {

	public RegistrationClientPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[text()='إنشاء حساب في هوية']")
	public WebElement registerFormHeaderTxtCli;

	@FindBy(xpath = "//*[text()='تسجيل']")
	WebElement registerBtnCli;

	@FindBy(id = "user_email")
	public WebElement emailTxtBoxCli;

	@FindBy(id = "user_password")
	public WebElement passwordTxtBoxCli;

	@FindBy(id = "user_password_confirmation")
	public WebElement confirmPasswordTxtBoxCli;

	@FindBy(id = "user_screen_name")
	public WebElement userNameTxtBoxCli;

	@FindBy(xpath = "//label[@for='user_type_1']")
	WebElement clientRadioBtnCli;

	@FindBy(xpath = "//label[@for='user_type_2']")
	WebElement designerRadioBtnCli;

	@FindBy(xpath = "//label[@for='user_terms_and_conditions']")
	WebElement termsAndConditionsCheckBoxCli;

	@FindBy(xpath = "//*[text()='يجب تحديد احد الخيارات ┊ احتاج الى تصميم ┊ انا مصمم/مصممة']")
	public WebElement userTypeValidationMsgCli;

	public void submitRegisterfun() {
		clickButton(registerBtnCli);
	}

	public void registerFun(String email, String password, String userName) {
		setTextElementText(emailTxtBoxCli, email);
		setTextElementText(passwordTxtBoxCli, password);
		setTextElementText(confirmPasswordTxtBoxCli, password);
		setTextElementText(userNameTxtBoxCli, userName);
	}

	public void clientRegister() {
		clickButton(clientRadioBtnCli);
	}

	public void designerRegister() {
		clickButton(designerRadioBtnCli);
	}

	public void checkTermsAndCondition() {
		clickButton(termsAndConditionsCheckBoxCli);
	}
}