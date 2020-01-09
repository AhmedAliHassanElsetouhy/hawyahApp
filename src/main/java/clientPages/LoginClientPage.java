package clientPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginClientPage extends PageBase {

	public LoginClientPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//*[text()='تسجيل الدخول']")
	// WebElement loginBtn;

	@FindBy(partialLinkText = "تسجيل الدخول")
	WebElement loginBtnCli;

	@FindBy(id = "user_email")
	public WebElement emailTxtBoxCli;

	@FindBy(id = "user_password")
	public WebElement passwordTxtBoxCli;

	@FindBy(linkText = "هل نسيت كلمة المرور؟")
	public WebElement forgetPassLinkCli;

	@FindBy(linkText = "إنضم إلينا")
	WebElement joinUsLinkCli;

	public void submitLoginFun() {
		clickButton(loginBtnCli);
	}

	public void loginFun(String email, String password) {
		setTextElementText(emailTxtBoxCli, email);
		setTextElementText(passwordTxtBoxCli, password);
		passwordTxtBoxCli.sendKeys(Keys.ENTER);
	}

	public void openForgetPassPageFun() {
		clickButton(forgetPassLinkCli);
	}

	public void openRegisterFormFun() {
		clickButton(joinUsLinkCli);
	}
}