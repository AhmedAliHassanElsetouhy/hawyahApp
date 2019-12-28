package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MessagesPage extends PageBase {

	public MessagesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//*[text()='الرسائل']")
	@FindBy(xpath = "//li[@class='collection-item highlighted-li']")
	public WebElement messagesLink;

	public void openMessages() {
		clickButton(messagesLink);
	}

	@FindBy(xpath = "//*[text()='send']")
	// @FindBy(xpath = "//li[@class='collection-item']")
	public WebElement sentMessageLink;

	public void openSentMessages() {
		clickButton(sentMessageLink);
	}

	@FindBy(xpath = "//*[text()='delete']")
	public WebElement deletedMessageLink;

	public void openDeletedMessageFun() {
		clickButton(deletedMessageLink);
	}

	@FindBy(xpath = "//*[text()='add']")
	WebElement addIcon;

	public void openAddMessageFun() {
		clickButton(addIcon);
	}
}
