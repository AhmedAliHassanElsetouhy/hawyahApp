package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//*[text()='تسجيل الدخول']")
	// WebElement loginBtn;

	@FindBy(partialLinkText = "تسجيل الدخول")
	WebElement loginBtn;

	@FindBy(id = "user_email")
	public WebElement emailTxtBox;

	@FindBy(id = "user_password")
	public WebElement passwordTxtBox;

	@FindBy(linkText = "هل نسيت كلمة المرور؟")
	public WebElement forgetPassLink;

	@FindBy(linkText = "إنضم إلينا")
	WebElement joinUsLink;

	public void submitLoginFun() {
		clickButton(loginBtn);
	}

	public void loginFun(String email, String password) {
		setTextElementText(emailTxtBox, email);
		setTextElementText(passwordTxtBox, password);
		passwordTxtBox.sendKeys(Keys.ENTER);
	}

	public void openForgetPassPageFun() {
		clickButton(forgetPassLink);
	}
	
	public void openRegisterFormFun() {
		clickButton(joinUsLink);
	}
}
