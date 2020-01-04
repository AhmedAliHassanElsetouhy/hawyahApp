package designerPages;

import java.awt.AWTException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import clientPages.PageBase;

public class CompetitionDetailsDesignerPage extends PageBase {

	public CompetitionDetailsDesignerPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(partialLinkText = "التفاصيل")
	public WebElement detailsLink;

	@FindBy(xpath = "//*[text()='ما هو الاسم الذي تريده في طلبك']")
	WebElement name;

	@FindBy(xpath = "//*[text()='ما هو تصنيف نشاطك؟']")
	WebElement cat;

	@FindBy(xpath = "//*[text()='اشرح نشاط أو صفات منتجك الذي تريد']")
	WebElement desc;

	@FindBy(xpath = "//*[text()='هل لديك عبارة ترغب بإضافتها؟']")
	WebElement details;

	@FindBy(xpath = "//*[text()='هل لديك فكرة أو إضافات معينة ترغب بإخبار المصمم عنها؟']")
	WebElement idea;

	@FindBy(xpath = "//*[text()='الفئة الفضية']")
	WebElement prize;

	@FindBy(xpath = "//*[text()='متطلبات تسليم العمل في مرحلة التسليم']")
	WebElement requirement;

	public void openDetailsFun() {
		clickButton(detailsLink);
		Assert.assertTrue(cat.isDisplayed());
		Assert.assertTrue(desc.isDisplayed());
		Assert.assertTrue(idea.isDisplayed());
	}

	@FindBy(partialLinkText = "التصاميم")
	public WebElement designsLink;

	@FindBy(xpath = "//div[@class='card']")
	public WebElement designCard;

	@FindBy(xpath = "//div[@class='rating m-t-10']")
	public WebElement rate;

	public void openDesignsFun() {
		clickButton(designsLink);
		Assert.assertTrue(designCard.isDisplayed());
		Assert.assertTrue(rate.isDisplayed());
	}

	@FindBy(partialLinkText = "الملفات")
	public WebElement filesLink;

	@FindBy(xpath = "//legend[@class='blue-text']")
	public WebElement filesHeader;

	@FindBy(partialLinkText = "عرض الاتفاقية")
	WebElement showAgreementLink;

	// @FindBy(id = "handoverAgreementViewModal")
	@FindBy(xpath = "//div[@class='modal open']")
	public WebElement agreementPopup;

	@FindBy(xpath = "//*[text()='إغلاق']")
	WebElement closeBtn;

	// @FindBy(id = "upload_design_input_for_signoff")
	@FindBy(xpath = "//*[@id=\"upload_design_input_for_signoff\"]")
	// @FindBy(xpath = "//input[@id='upload_design_input_for_signoff']")
	WebElement chooseFileBtn;

	public void openFilesAndOpenAgreementFun() {
		clickButton(filesLink);
		Assert.assertTrue(filesHeader.isDisplayed());
		Assert.assertTrue(showAgreementLink.isDisplayed());
		clickButton(showAgreementLink);
		Assert.assertTrue(agreementPopup.isDisplayed());
		clickButton(closeBtn);
	}

	public void openFiles() {
		clickButton(filesLink);
	}

	@FindBy(xpath = "//input[@value='حفظ']")
	// @FindBy(xpath = "//*[text()='حفظ']")
	WebElement saveBtn;

	public void uploadContract(String folderName) throws InterruptedException, AWTException {
		// saveBtn.submit();
		clickButton(saveBtn);
		// clickButton(chooseFileBtn);
		chooseFileBtn.submit();
		chooseFileBtn.sendKeys(Keys.ENTER);
//		 FileUploadWithRobot(folderName);
		testFileUpload(folderName, chooseFileBtn);
		// saveBtn.submit();
	}

	@FindBy(partialLinkText = "تواصل معنا")
	public WebElement contactUsLink;

	@FindBy(id = "message[body]")
	WebElement messageTxtBox;

	@FindBy(xpath = "//input[@value='ارسال']")
	WebElement sendBtn;

	@FindBy(xpath = "//div[@class='green lighten-5']")
	WebElement confirmAlertMsg;

	public void contactUsFun(String message) {
		clickButton(contactUsLink);
		setTextElementText(messageTxtBox, message);
		clickButton(sendBtn);
		Assert.assertTrue(confirmAlertMsg.isDisplayed());
	}

	@FindBy(xpath = "//span[@class='lever']")
	WebElement signAgreementIcon;

	public void openDesignerAgreementFormFun() {
		clickButton(filesLink);
		clickButton(signAgreementIcon);
	}

	@FindBy(id = "name")
	WebElement designerName;

	@FindBy(id = "address")
	WebElement designerTitle;

	@FindBy(id = "mobile_no")
	WebElement designerPhone;

	@FindBy(xpath = "//label[@for='terms_and_conditions']")
	WebElement conditionCheckBox;

	@FindBy(xpath = "//*[text()='توقيع الإتفاقية']")
	WebElement agreementBtn;

	public void designerAgreementFormData(String name, String title, String phone) {
		setTextElementText(designerName, name);
		setTextElementText(designerTitle, title);
		setTextElementText(designerPhone, phone);
		clickButton(conditionCheckBox);
		agreementBtn.submit();
	}
}
