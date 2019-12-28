package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationConfirmationPage extends PageBase {

	public RegistrationConfirmationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//p[text()='سيتم مراجعة بياناتك من قبل فريق هوية وستصلك رسالة التفعيل لحسابك كمصمم.']")
	public WebElement registerDesignerConfirmMsg;

	@FindBy(xpath = "//p[text()='شكرا لتسجيلك في موقع هوية، تم ارسالة رسالة تأكيد بالبريد الإلكتروني مع التعليمات اللازمة لإتمام عملية التسجيل']")
	public WebElement registerClientConfirmMsg;
}
