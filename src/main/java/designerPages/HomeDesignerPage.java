package designerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class HomeDesignerPage extends PageBase {

	public HomeDesignerPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//*[text()='تم تسجيل الدخول بنجاح']")
	@FindBy(xpath = "//div[@class='green lighten-5']")
	public WebElement loginConfirmMsgDes;

	@FindBy(xpath = "//*[text()='إبدأ الآن']")
	WebElement startNowBtnDes;

	public void openServicesFun() {
		clickButton(startNowBtnDes);
	}

	@FindBy(xpath = "//*[text()='إشرح']")
	public WebElement explainDes;

	@FindBy(xpath = "//*[text()='يتنافس']")
	public WebElement competingDes;

	@FindBy(xpath = "//*[text()='اختر']")
	public WebElement chooseDes;

	@FindBy(xpath = "//a[@class='dropdown-button grey-text text-darken-1']")
	WebElement basicMenuDes;

	@FindBy(linkText = "مسابقاتي")
	// @FindBy(xpath = "//*[text()='مسابقاتي']")
	WebElement competitionLinkDes;

	@FindBy(xpath = "//*[text()='حسابي']")
	WebElement myAccountLinkDes;

	@FindBy(xpath = "//*[text()='الرسائل']")
	WebElement messagesLinkDes;

	public void openMyAccount() {
		clickButton(myAccountLinkDes);
	}

	public void openMessagesFun() {
		clickButton(messagesLinkDes);
	}

	public void openMainMenuFun() {
		clickButton(basicMenuDes);
	}

	public void openCompetitionFun() {
		clickButton(competitionLinkDes);
	}

	// @FindBy(xpath = "//*[text()='خروج']")
	// WebElement logoutBtn;

	@FindBy(xpath = "//*[@rel='nofollow']")
	WebElement logoutBtnDes;

	public void logoutFun() {
		clickButton(logoutBtnDes);
	}
}