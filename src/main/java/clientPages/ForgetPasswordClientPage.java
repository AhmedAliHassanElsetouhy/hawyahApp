package clientPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgetPasswordClientPage extends PageBase {

	public ForgetPasswordClientPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[text()='استعادة كلمة المرور']")
	public WebElement resetPassBtnCli;

	@FindBy(id = "user_email")
	public WebElement emailTxtBoxCli;

	// @FindBy(xpath = "//*[text()='البريد الإلكتروني المدخل غير صحيح. الرجاء إعادة
	// المحاولة']")
	@FindBy(xpath = "//div[@class='pink lighten-5']")
	public WebElement invalidForgetPassEmailMsgCli;

	public void forgetPassFun(String email) {
		setTextElementText(emailTxtBoxCli, email);
		emailTxtBoxCli.sendKeys(Keys.ENTER);
	}

	public void sendForgottenEmailBtnFun() {
		clickButton(resetPassBtnCli);
	}
}