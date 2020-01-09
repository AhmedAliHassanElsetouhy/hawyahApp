package clientPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MessageFormClientPage extends PageBase {

	public MessageFormClientPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[text()='people']")
	public WebElement peopleIconCli;

	@FindBy(xpath = "//input[@value='قائمة المرسل اليهم']")
	WebElement adminListCli;

	public void openAdminListFun() {
		clickButton(adminListCli);
	}

	@FindBy(xpath = "//*[text()='admin1']")
	WebElement admin1Cli;

	public void selectAdmin1Fun() {
		clickButton(admin1Cli);
	}

	@FindBy(id = "message[body]")
	WebElement msgBodyTxtBoxCli;

	@FindBy(id = "message[subject]")
	WebElement msgTitleTxtBoxCli;

	public void sendMsgFun(String body, String title) {
		setTextElementText(msgBodyTxtBoxCli, body);
		setTextElementText(msgTitleTxtBoxCli, title);
		clickButton(sendBtnCli);
	}

	@FindBy(xpath = "//input[@value='ارسال']")
	WebElement sendBtnCli;

	@FindBy(xpath = "//a[@title='رسالة جديدة']")
	public WebElement newMsgIconCli;

	@FindBy(xpath = "//*[@title='الرسائل']")
	WebElement messagesIconCli;

	public void openMessages() {
		clickButton(messagesIconCli);
	}

	@FindBy(xpath = "//a[@href='/conversations?box=sent&locale=ar']")
	WebElement sentMsgsCli;

	public void openSentMsgs() {
		clickButton(sentMsgsCli);
	}

	@FindBy(xpath = "//a[@href='/conversations?box=trash&locale=ar']")
	WebElement deletedMsgsCli;

	public void openDeletedMsgs() {
		clickButton(deletedMsgsCli);
	}

	@FindBy(xpath = "//a[@title='حذف المحادثة']")
	List<WebElement> deleteIconsCli;

	public void deleteMsgFun(int index) {
		selectSpecificIcon(deleteIconsCli, index);
	}

	@FindBy(xpath = "//div[@class='green lighten-5']")
	public WebElement confirmDeleteMsgCli;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/ul/li[3]/a/i")
	WebElement sentMegsIconCli;

	public void openSentMessages() {
		clickButton(sentMegsIconCli);
	}

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/ul/li[4]/a/i")
	WebElement deletedMsgsIconCli;

	public void openDeletedMsgsFun() {
		clickButton(deletedMsgsIconCli);
	}

	@FindBy(xpath = "//a[@title='استرجاع']")
	List<WebElement> restoreIconsCli;

	public void restoreDeleteMsgFun(int index) {
		selectSpecificIcon(restoreIconsCli, index);
	}

	@FindBy(xpath = "//div[@class='green lighten-5']")
	public WebElement confirmRestoreMsgCli;

	@FindBy(partialLinkText = "حذف جميع الرسائل")
	WebElement deleteAllMsgLinkCli;

	public void deleteAllMsgsFun() {
		clickButton(deleteAllMsgLinkCli);
	}
}