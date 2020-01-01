package clientPages;

import java.util.List;

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

	@FindBy(xpath = "//a[@title='رسالة جديدة']")
	public WebElement newMsgIcon;

	@FindBy(xpath = "//*[@title='الرسائل']")
	WebElement messagesIcon;

	public void openMessages() {
		clickButton(messagesIcon);
	}

	@FindBy(xpath = "//a[@title='حذف المحادثة']")
	List<WebElement> deleteIcons;

	public void deleteMsgFun(int index) {
		selectSpecificIcon(deleteIcons, index);
	}

	@FindBy(xpath = "//div[@class='green lighten-5']")
	public WebElement confirmDeleteMsg;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/ul/li[3]/a/i")
	WebElement sentMegsIcon;

	public void openSentMessages() {
		clickButton(sentMegsIcon);
	}

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/ul/li[4]/a/i")
	WebElement deletedMsgsIcon;

	public void openDeletedMsgsFun() {
		clickButton(deletedMsgsIcon);
	}

	@FindBy(xpath = "//a[@title='استرجاع']")
	List<WebElement> restoreIcons;

	public void restoreDeleteMsgFun(int index) {
		selectSpecificIcon(restoreIcons, index);
	}

	@FindBy(xpath = "//div[@class='green lighten-5']")
	public WebElement confirmRestoreMsg;

	@FindBy(partialLinkText = "حذف جميع الرسائل")
	WebElement deleteAllMsgLink;

	public void deleteAllMsgsFun() {
		clickButton(deleteAllMsgLink);
	}
}
