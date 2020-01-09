package clientPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MessagesClientPage extends PageBase {

	public MessagesClientPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//*[text()='الرسائل']")
	@FindBy(xpath = "//li[@class='collection-item highlighted-li']")
	public WebElement messagesLinkCli;

	@FindBy(xpath = "//*[text()='send']")
	// @FindBy(xpath = "//li[@class='collection-item']")
	public WebElement sentMessageLinkCli;

	@FindBy(xpath = "//*[text()='delete']")
	public WebElement deletedMessageLinkCli;

	@FindBy(xpath = "//*[text()='add']")
	WebElement addIconCli;

	public void openMessages() {
		clickButton(messagesLinkCli);
	}

	public void openSentMessages() {
		clickButton(sentMessageLinkCli);
	}

	public void openDeletedMessageFun() {
		clickButton(deletedMessageLinkCli);
	}

	public void openAddMessageFun() {
		clickButton(addIconCli);
	}
}