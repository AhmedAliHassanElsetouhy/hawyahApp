package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmPasswordPage extends PageBase {

	public ConfirmPasswordPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[text()='تم ارسال الرسالة بنجاح. الرجاء اتباع التعليمات الموضحة في بريدك الالكتروني.']")
	public WebElement confirmPassMsg;

	@FindBy(linkText = "تسجيل الدخول")
	WebElement loginLink;

	public void openLoginFormFun() {
		clickButton(loginLink);
	}
}
