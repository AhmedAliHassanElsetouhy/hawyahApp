package clientPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeClientPage extends PageBase {

	public HomeClientPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//*[text()='تم تسجيل الدخول بنجاح']")
	@FindBy(xpath = "//div[@class='green lighten-5']")
	public WebElement loginConfirmMsgCli;

	@FindBy(xpath = "//*[text()='إبدأ الآن']")
	WebElement startNowBtnCli;

	public void openServicesFun() {
		clickButton(startNowBtnCli);
	}

	@FindBy(xpath = "//*[text()='ما هو التصميم الذي تريده؟']")
	public WebElement wantedDesignHeaderMsgCli;

	@FindBy(xpath = "//*[text()='شعار وهوية']")
	public WebElement logoAndIdentityCli;

	@FindBy(xpath = "//*[text()='خط عربي']")
	public WebElement arabicFontCli;

	@FindBy(xpath = "//*[text()='تصميم واجهة موقع']")
	public WebElement websiteInterfaceDesignCli;

	@FindBy(xpath = "//*[text()='الملابس والتغليف']")
	public WebElement clothesAndPackagingCli;

	@FindBy(xpath = "//*[text()='أخرى']")
	public WebElement otherCli;

	@FindBy(xpath = "//*[text()='كيف نعمل']")
	public WebElement howWeWorkHeaderMsgCli;

	@FindBy(xpath = "//*[text()='إشرح']")
	public WebElement explainCli;

	@FindBy(xpath = "//*[text()='يتنافس']")
	public WebElement competingCli;

	@FindBy(xpath = "//*[text()='اختر']")
	public WebElement chooseCli;

	@FindBy(xpath = "//a[@class='dropdown-button grey-text text-darken-1']")
	WebElement basicMenuCli;

	@FindBy(linkText = "مسابقاتي")
	// @FindBy(xpath = "//*[text()='مسابقاتي']")
	WebElement competitionLinkCli;

	@FindBy(xpath = "//*[text()='حسابي']")
	WebElement myAccountLinkCli;

	@FindBy(xpath = "//*[text()='الرسائل']")
	WebElement messagesLinkCli;

	public void openMyAccount() {
		clickButton(myAccountLinkCli);
	}

	public void openMessagesFun() {
		clickButton(messagesLinkCli);
	}

	public void openMainMenuFun() {
		clickButton(basicMenuCli);
	}

	public void openCompetitionFun() {
		clickButton(competitionLinkCli);
	}

	// @FindBy(xpath = "//*[text()='خروج']")
	// WebElement logoutBtn;

	@FindBy(xpath = "//*[@rel='nofollow']")
	WebElement logoutBtnCli;

	public void logoutFun() {
		clickButton(logoutBtnCli);
	}
}
