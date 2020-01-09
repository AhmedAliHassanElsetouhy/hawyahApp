package clientPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DefaultPage extends PageBase {

	public DefaultPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(linkText = "تسجيل الدخول")
	public WebElement loginLink;

	@FindBy(linkText = "إنضم إلينا")
	WebElement joinUsBtn;

	@FindBy(linkText = "طلبات التصميم")
	WebElement requestDesign;

	public void openLoginForm() {
		clickButton(loginLink);
	}

	public void openRegisterFormFun() {
		clickButton(joinUsBtn);
	}

	public void openRequestDesingFun() {
		clickButton(requestDesign);
	}

	@FindBy(xpath = "//*[text()='من نحن']")
	public WebElement whoUsLink;

	public void openWhoUsPage() {
		clickButton(whoUsLink);
	}

	@FindBy(xpath = "//*[text()='تواصل معنا']")
	public WebElement contactUsLink;

	public void openContactUsPage() {
		clickButton(contactUsLink);
	}

	@FindBy(xpath = "//*[text()='الأسعار']")
	public WebElement pricesLink;

	public void openPricesPage() {
		clickButton(pricesLink);
	}

	@FindBy(xpath = "//*[text()='وظائف']")
	public WebElement jobsLink;

	public void openJobsPage() {
		clickButton(jobsLink);
	}

	@FindBy(xpath = "//*[text()='الشروط والأحكام']")
	public WebElement termsAndConditionsLink;

	public void openTermsAndConditionsPage() {
		clickButton(termsAndConditionsLink);
	}

	@FindBy(xpath = "//*[text()='سياسة الخصوصية']")
	public WebElement privacyLink;

	public void openPrivacyPage() {
		clickButton(pricesLink);
	}

	@FindBy(xpath = "//*[text()='الاسئلة الشائعة']")
	public WebElement commonQuesLink;

	public void openCommonQuesPage() {
		clickButton(commonQuesLink);
	}

	@FindBy(xpath = "//*[text()='اطلب تصميم']")
	public WebElement askDesignLink;

	public void openAskDesignPage() {
		clickButton(askDesignLink);
	}

	@FindBy(xpath = "//*[text()='إنضم الى قائمة مصممينا']")
	public WebElement beDesignerLink;

	public void openBeDesignerPage() {
		clickButton(beDesignerLink);
	}

	@FindBy(xpath = "//*[text()='طلبات التصميم']")
	public WebElement designsReqLink;

	public void openDesignRequestsPage() {
		clickButton(designsReqLink);
	}

	@FindBy(xpath = "//*[text()='كيف نعمل']")
	public WebElement howItWorkLink;

	public void openHowItWorkPage() {
		clickButton(howItWorkLink);
	}
}