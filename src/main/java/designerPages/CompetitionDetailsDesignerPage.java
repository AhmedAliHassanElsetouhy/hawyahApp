package designerPages;

import java.awt.AWTException;
import java.util.List;

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
		clickButton(detailsLink);
	}

	// @FindBy(partialLinkText = "التصاميم")
	@FindBy(xpath = "//a[@href='#designs']")
	public WebElement designsLink;

	@FindBy(xpath = "//div[@class='card']")
	public List<WebElement> designCard;

	@FindBy(xpath = "//div[@class='rating m-t-10']")
	public WebElement rate;

	public void openDesignsFun() {
		clickButton(designsLink);
	}

	public void openCardFun(int index) {
		selectSpecificIcon(designCard, index);
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
	
	@FindBy(xpath="//div[@class='comment-list']")
	public WebElement commentList;

	@FindBy(xpath = "//span[@class='close']")
	public WebElement closeCommentView;

	public void closeCommentViewFun() {
		clickButton(closeCommentView);
	}

	@FindBy(partialLinkText = "الملفات")
	public WebElement filesLink;

	@FindBy(xpath = "//legend[@class='blue-text']")
	public WebElement filesHeader;

	@FindBy(partialLinkText = "عرض الاتفاقية")
	public WebElement showAgreementLink;

	// @FindBy(id = "handoverAgreementViewModal")
	@FindBy(xpath = "//div[@class='modal open']")
	public WebElement agreementPopup;

	@FindBy(xpath = "//*[text()='إغلاق']")
	WebElement closeBtn;

	// @FindBy(xpath = "//*[@id=\"upload_design_input_for_signoff\"]")
	@FindBy(xpath = "//input[@type='file']")
	public WebElement chooseFileBtn;

	@FindBy(xpath = "//legend[@class='purple-text']")
	public WebElement designerSig;

	@FindBy(xpath = "//legend[@class='blue-text']")
	public WebElement cliendSig;

	public void openFilesAndOpenAgreementDesignerFun() {
		clickButton(filesLink);
		Assert.assertTrue(filesHeader.isDisplayed());
		Assert.assertTrue(showAgreementLink.isDisplayed());
		clickButton(showAgreementLink);
		Assert.assertTrue(agreementPopup.isDisplayed());
		Assert.assertTrue(designerSig.isDisplayed());
		Assert.assertTrue(cliendSig.isDisplayed());
		closeFun();
	}

	public void showAgreementFun() {
		clickButton(showAgreementLink);
	}
	
//	public void openFilesAndOpenAgreementDesignerOnlyFun() {
////		clickButton(filesLink);
//		
////		clickButton(showAgreementLink);
//		
//		closeFun();
//	}
	
	public void closeFun() {
		clickButton(closeBtn);
	}

	public void openFiles() {
		clickButton(filesLink);
	}

	@FindBy(xpath = "//input[@value='حفظ']")
	// @FindBy(xpath = "//*[text()='حفظ']")
	WebElement saveBtn;

	public void uploadFinalDesign(String folderName) throws InterruptedException, AWTException {
		chooseFileBtn.sendKeys(System.getProperty("user.dir") + "\\Uploads\\" + folderName);
		clickButton(saveBtn);
	}

	// @FindBy(partialLinkText = "تواصل معنا")
	@FindBy(xpath = "//a[text()='تواصل معنا']")
	public WebElement contactUsLink;

	@FindBy(xpath = "//a[text()='تحميل تصميم']")
	public WebElement uploadDesignLink;

	public void openUploadDesignFun() {
		clickButton(uploadDesignLink);
	}

	@FindBy(xpath="//span[@class='size']")
	public WebElement fileSize;
	
	@FindBy(xpath = "//input[@type='file']")
	public WebElement selectFileBtn;

	public void uploadDesignFileFun(String folderName) throws InterruptedException, AWTException {
		selectFileBtn.sendKeys(System.getProperty("user.dir") + "\\Uploads\\" + folderName);
	}

	@FindBy(id = "message[body]")
	WebElement messageTxtBox;

	@FindBy(xpath = "//input[@value='ارسال']")
	WebElement sendBtn;

	@FindBy(xpath = "//div[@class='green lighten-5']")
	WebElement confirmAlertMsg;

	public void contactUsFun(String message) {
		// clickButton(contactUsLink);
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
