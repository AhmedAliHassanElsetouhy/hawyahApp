package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MessageFormPage extends PageBase {

	public MessageFormPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[text()='people']")
	public WebElement peopleIcon;

	@FindBy(xpath = "//input[@value='قائمة المرسل اليهم']")
	WebElement adminList;

	public void openAdminListFun() {
		clickButton(adminList);
	}

	@FindBy(xpath = "//*[text()='admin1']")
	WebElement admin1;

	public void selectAdmin1Fun() {
		clickButton(admin1);
	}

	@FindBy(id = "message[body]")
	WebElement msgBodyTxtBox;

	@FindBy(id = "message[subject]")
	WebElement msgTitleTxtBox;

	public void sendMsgFun(String body, String title) {
		setTextElementText(msgBodyTxtBox, body);
		setTextElementText(msgTitleTxtBox, title);
		clickButton(sendBtn);
	}

	@FindBy(xpath = "//input[@value='ارسال']")
	WebElement sendBtn;
	
	@FindBy(xpath="//a[@title='رسالة جديدة']")
	public WebElement newMsgIcon;
}
