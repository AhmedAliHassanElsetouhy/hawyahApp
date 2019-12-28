package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase {

	public HomePage(WebDriver driver) {
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

	@FindBy(xpath = "//*[text()='ما هو التصميم الذي تريده؟']")
	public WebElement wantedDesignHeaderMsg;

	@FindBy(xpath = "//*[text()='شعار وهوية']")
	public WebElement logoAndIdentity;

	@FindBy(xpath = "//*[text()='خط عربي']")
	public WebElement arabicFont;

	@FindBy(xpath = "//*[text()='تصميم واجهة موقع']")
	public WebElement websiteInterfaceDesign;

	@FindBy(xpath = "//*[text()='الملابس والتغليف']")
	public WebElement clothesAndPackaging;

	@FindBy(xpath = "//*[text()='أخرى']")
	public WebElement other;

	@FindBy(xpath = "//*[text()='كيف نعمل']")
	public WebElement howWeWorkHeaderMsg;

	@FindBy(xpath = "//*[text()='إشرح']")
	public WebElement explain;

	@FindBy(xpath = "//*[text()='يتنافس']")
	public WebElement competing;

	@FindBy(xpath = "//*[text()='اختر']")
	public WebElement choose;

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

	@FindBy(xpath = "//*[text()='خروج']")
	WebElement logoutBtn;

	public void logoutFun() {
		clickButton(logoutBtn);
	}
}