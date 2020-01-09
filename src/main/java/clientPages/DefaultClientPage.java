package clientPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DefaultClientPage extends PageBase {

	public DefaultClientPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(linkText = "تسجيل الدخول")
	public WebElement loginLinkCli;

	@FindBy(linkText = "إنضم إلينا")
	WebElement joinUsBtnCli;

	@FindBy(linkText = "طلبات التصميم")
	WebElement requestDesignCli;

	public void openLoginForm() {
		clickButton(loginLinkCli);
	}

	public void openRegisterFormFun() {
		clickButton(joinUsBtnCli);
	}

	public void openRequestDesingFun() {
		clickButton(requestDesignCli);
	}

	@FindBy(xpath = "//*[text()='من نحن']")
	public WebElement whoUsLink;

	public void openWhoUsPage() {
		clickButton(whoUsLink);
	}

	@FindBy(xpath = "//*[text()='تواصل معنا']")
	public WebElement contactUsLinkCli;

	public void openContactUsPage() {
		clickButton(contactUsLinkCli);
	}

	@FindBy(xpath = "//*[text()='الأسعار']")
	public WebElement pricesLinkCli;

	public void openPricesPage() {
		clickButton(pricesLinkCli);
	}

	@FindBy(xpath = "//*[text()='وظائف']")
	public WebElement jobsLinkCli;

	public void openJobsPage() {
		clickButton(jobsLinkCli);
	}

	@FindBy(xpath = "//*[text()='الشروط والأحكام']")
	public WebElement termsAndConditionsLinkCli;

	public void openTermsAndConditionsPage() {
		clickButton(termsAndConditionsLinkCli);
	}

	@FindBy(xpath = "//*[text()='سياسة الخصوصية']")
	public WebElement privacyLinkCli;

	public void openPrivacyPage() {
		clickButton(pricesLinkCli);
	}

	@FindBy(xpath = "//*[text()='الاسئلة الشائعة']")
	public WebElement commonQuesLinkCli;

	public void openCommonQuesPage() {
		clickButton(commonQuesLinkCli);
	}

	@FindBy(xpath = "//*[text()='اطلب تصميم']")
	public WebElement askDesignLinkCli;

	public void openAskDesignPage() {
		clickButton(askDesignLinkCli);
	}

	@FindBy(xpath = "//*[text()='إنضم الى قائمة مصممينا']")
	public WebElement beDesignerLinkCli;

	public void openBeDesignerPage() {
		clickButton(beDesignerLinkCli);
	}

	@FindBy(xpath = "//*[text()='طلبات التصميم']")
	public WebElement designsReqLinkCli;

	public void openDesignRequestsPage() {
		clickButton(designsReqLinkCli);
	}

	@FindBy(xpath = "//*[text()='كيف نعمل']")
	public WebElement howItWorkLinkCli;

	public void openHowItWorkPage() {
		clickButton(howItWorkLinkCli);
	}
}