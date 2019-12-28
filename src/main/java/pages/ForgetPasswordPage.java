package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgetPasswordPage extends PageBase {

	public ForgetPasswordPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[text()='استعادة كلمة المرور']")
	public WebElement resetPassBtn;

	@FindBy(id = "user_email")
	public WebElement emailTxtBox;

	// @FindBy(xpath = "//*[text()='البريد الإلكتروني المدخل غير صحيح. الرجاء إعادة
	// المحاولة']")
	@FindBy(xpath = "//div[@class='pink lighten-5']")
	public WebElement invalidForgetPassEmailMsg;

	public void forgetPassFun(String email) {
		setTextElementText(emailTxtBox, email);
		emailTxtBox.sendKeys(Keys.ENTER);
	}

	public void sendForgottenEmailBtnFun() {
		clickButton(resetPassBtn);
	}
}
