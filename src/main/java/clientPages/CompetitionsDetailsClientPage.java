package clientPages;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CompetitionsDetailsClientPage extends PageBase {

	public CompetitionsDetailsClientPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//legend[@class='blue-text']")
	public WebElement clientagreementPageHeader;

	@FindBy(xpath = "//span[@class='lever']")
	WebElement openAgreementIcon;

	@FindBy(id = "name")
	WebElement clientNameTxtBox;

	@FindBy(id = "address")
	WebElement clientAddressTxtBox;

	@FindBy(id = "mobile_no")
	WebElement mobileNumTxtBox;

	@FindBy(id = "comments")
	WebElement commentsTxtArea;

	@FindBy(xpath = "//label[@for='terms_and_conditions']")
	WebElement termsAndConditionsCheckBox;

	@FindBy(xpath = "//*[@type='submit']")
	WebElement submitBtn;

	public void clientAgreement(String name, String address, String mobileNum, String comments) {
		clickButton(openAgreementIcon);
		setTextElementText(clientNameTxtBox, name);
		setTextElementText(clientAddressTxtBox, address);
		setTextElementText(mobileNumTxtBox, mobileNum);
		setTextElementText(commentsTxtArea, comments);
		clickButton(termsAndConditionsCheckBox);
	}

	public void signAgreement() {
		submitBtn.submit();
	}

	@FindBy(xpath = "//a[text()='الإعدادات']")
	public WebElement settingsLinkCli;

	public void openSettingsFun() {
		clickButton(settingsLinkCli);
	}

	@FindBy(xpath = "//a[text()='الإنتقال لمرحلة إختيار التصاميم']")
	public WebElement moveToSelectDesignStatus;

	public void moveToSelectDesignStatusFun() {
		clickButton(moveToSelectDesignStatus);
	}

	@FindBy(xpath = "//*[@href='/web/contests/design_select?locale=ar']")
	public WebElement completeDataBtn;

	public void openCompleteDataFun() {
		clickButton(completeDataBtn);
	}

	// @FindBy(partialLinkText = "تواصل معنا")
	@FindBy(xpath = "//a[text()='تواصل معنا']")
	public WebElement contactUsLinkCLi;

	@FindBy(partialLinkText = "التفاصيل")
	public WebElement detailsLinkCli;

	// @FindBy(partialLinkText = "التصاميم")
	@FindBy(xpath = "//a[@href='#designs']")
	public WebElement designsLinkCli;

	@FindBy(xpath = "//div[@class='card']")
	public List<WebElement> designCardCli;

	@FindBy(xpath = "//*[text()='ما هو الاسم الذي تريده في طلبك']")
	WebElement name;

	@FindBy(xpath = "//*[text()='ما هو تصنيف نشاطك؟']")
	public WebElement cat;

	@FindBy(xpath = "//*[text()='اشرح نشاط أو صفات منتجك الذي تريد']")
	public WebElement desc;

	@FindBy(xpath = "//*[text()='هل لديك عبارة ترغب بإضافتها؟']")
	WebElement details;

	@FindBy(xpath = "//*[text()='هل لديك فكرة أو إضافات معينة ترغب بإخبار المصمم عنها؟']")
	public WebElement idea;

	@FindBy(xpath = "//*[text()='الفئة الفضية']")
	WebElement prize;

	@FindBy(xpath = "//*[text()='متطلبات تسليم العمل في مرحلة التسليم']")
	WebElement requirement;

	public void openDetailsFun() {
		clickButton(detailsLinkCli);
	}

	// @FindBy(xpath = "//div[@class='rating m-t-10']")
	@FindBy(xpath = "//div[@class='star']")
	public WebElement rateCli;

	public void openDesignsFun() {
		clickButton(designsLinkCli);
	}

	public void openCardFun(int index) {
		selectSpecificIcon(designCardCli, index);
	}

	@FindBy(xpath = "//*[text()='أضف تعليق']")
	public WebElement addCommentHeader;

	@FindBy(id = "contest_design_comment_message")
	WebElement msgTextarea;

	@FindBy(id = "submitComment;")
	WebElement submitCommentBtn;

	@FindBy(xpath = "//div[@class='comment-item']")
	public List<WebElement> comments;

	public void addCommentFun(String comment) throws AWTException {
		setTextElementText(msgTextarea, comment);
		clickButton(submitCommentBtn);
		refreshPage();
	}

	@FindBy(xpath = "//div[@class='comment-list']")
	public WebElement commentList;

	@FindBy(xpath = "//span[@class='close']")
	public WebElement closeCommentViewCli;

	public void closeCommentViewFun() {
		clickButton(closeCommentViewCli);
	}

	@FindBy(partialLinkText = "الملفات")
	public WebElement filesLinkCli;

	public void openFiles() {
		clickButton(filesLinkCli);
	}

	@FindBy(id = "message[body]")
	WebElement messageTxtBoxCli;

	@FindBy(xpath = "//input[@value='ارسال']")
	WebElement sendBtnCli;

	@FindBy(xpath = "//div[@class='green lighten-5']")
	WebElement confirmAlertMsgCli;

	public void contactUsFun(String messageCli) {
		// clickButton(contactUsLink);
		setTextElementText(messageTxtBoxCli, messageCli);
		clickButton(sendBtnCli);
		Assert.assertTrue(confirmAlertMsgCli.isDisplayed());
	}
}