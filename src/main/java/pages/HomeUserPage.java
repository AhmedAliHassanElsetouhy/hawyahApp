package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeUserPage extends PageBase {

	public HomeUserPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//*[text()='تم تسجيل الدخول بنجاح']")
	@FindBy(xpath = "//div[@class='green lighten-5']")
	public WebElement loginConfirmMsg;

	@FindBy(xpath = "//*[text()='إبدأ الآن']")
	WebElement startNowBtn;

	public void openServicesFun() {
		clickButton(startNowBtn);
	}

	@FindBy(xpath = "//*[text()='إشرح']")
	public WebElement explain;

	@FindBy(xpath = "//*[text()='يتنافس']")
	public WebElement competing;

	@FindBy(xpath = "//*[text()='اختر']")
	public WebElement choose;
	
	@FindBy(partialLinkText="عني")
	public WebElement aboutMeLink;
	
	public void openAboutMe() {
		clickButton(aboutMeLink);
	}
	
	@FindBy(partialLinkText="احصائياتي")
	public WebElement myStatsLink;
	
	public void openMyStats() {
		clickButton(myStatsLink);
	}
	
	@FindBy(partialLinkText="الحسابات البنكية")
	public WebElement myBankAccountLink;
	
	public void openMyBankAccount() {
		clickButton(myBankAccountLink);
	}
	
	@FindBy(partialLinkText="طلبات التحويل")
	public WebElement myTransfersLink;
	
	public void openMyTransfers() {
		clickButton(myTransfersLink);
	}
	
	@FindBy(partialLinkText="أعمالي")
	public WebElement myWorksLink;
	
	public void openMyWorks() {
		clickButton(myWorksLink);
	}

	@FindBy(xpath = "//a[@class='dropdown-button grey-text text-darken-1']")
	WebElement basicMenu;

	@FindBy(linkText = "مسابقاتي")
	// @FindBy(xpath = "//*[text()='مسابقاتي']")
	WebElement competitionLink;

	@FindBy(xpath = "//*[text()='حسابي']")
	WebElement myAccountLink;

	@FindBy(xpath = "//*[text()='الرسائل']")
	WebElement messagesLink;

	public void openMyAccount() {
		clickButton(myAccountLink);
	}

	public void openMessagesFun() {
		clickButton(messagesLink);
	}

	public void openMainMenuFun() {
		clickButton(basicMenu);
	}

	public void openCompetitionFun() {
		clickButton(competitionLink);
	}

	// @FindBy(xpath = "//*[text()='خروج']")
	// WebElement logoutBtn;

	@FindBy(xpath = "//*[@rel='nofollow']")
	WebElement logoutBtn;

	public void logoutFun() {
		clickButton(logoutBtn);
	}

}
