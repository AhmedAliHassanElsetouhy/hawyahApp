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
	public WebElement detailsLinkDes;

	@FindBy(xpath = "//*[text()='ما هو الاسم الذي تريده في طلبك']")
	WebElement nameDes;

	@FindBy(xpath = "//*[text()='ما هو تصنيف نشاطك؟']")
	public WebElement catDes;

	@FindBy(xpath = "//*[text()='اشرح نشاط أو صفات منتجك الذي تريد']")
	public WebElement descDes;

	@FindBy(xpath = "//*[text()='هل لديك عبارة ترغب بإضافتها؟']")
	WebElement detailsDes;

	@FindBy(xpath = "//*[text()='هل لديك فكرة أو إضافات معينة ترغب بإخبار المصمم عنها؟']")
	public WebElement ideaDes;

	@FindBy(xpath = "//*[text()='الفئة الفضية']")
	WebElement prizeDes;

	@FindBy(xpath = "//*[text()='متطلبات تسليم العمل في مرحلة التسليم']")
	WebElement requirementDes;

	public void openDetailsFun() {
		clickButton(detailsLinkDes);
	}

	// @FindBy(partialLinkText = "التصاميم")
	@FindBy(xpath = "//a[@href='#designs']")
	public WebElement designsLinkDes;

	@FindBy(xpath = "//div[@class='card']")
	public List<WebElement> designCardDes;

	@FindBy(xpath = "//div[@class='rating m-t-10']")
	public WebElement rateDes;

	public void openDesignsFun() {
		clickButton(designsLinkDes);
	}

	public void openCardFun(int index) {
		selectSpecificIcon(designCardDes, index);
	}

	@FindBy(xpath = "//*[text()='أضف تعليق']")
	public WebElement addCommentHeaderDes;

	@FindBy(id = "contest_design_comment_message")
	WebElement msgTextareaDes;

	@FindBy(id = "submitComment;")
	WebElement submitCommentBtnDes;

	@FindBy(xpath = "//div[@class='comment-item']")
	public List<WebElement> commentsDes;

	public void addCommentFun(String comment) throws AWTException {
		setTextElementText(msgTextareaDes, comment);
		clickButton(submitCommentBtnDes);
		refreshPage();
	}

	@FindBy(xpath = "//div[@class='comment-list']")
	public WebElement commentListDes;

	@FindBy(xpath = "//span[@class='close']")
	public WebElement closeCommentViewDes;

	public void closeCommentViewFun() {
		clickButton(closeCommentViewDes);
	}

	@FindBy(partialLinkText = "الملفات")
	public WebElement filesLinkDes;

	@FindBy(xpath = "//legend[@class='blue-text']")
	public WebElement filesHeaderDes;

	@FindBy(partialLinkText = "عرض الاتفاقية")
	public WebElement showAgreementLinkDes;

	// @FindBy(id = "handoverAgreementViewModal")
	@FindBy(xpath = "//div[@class='modal open']")
	public WebElement agreementPopupDes;

	@FindBy(xpath = "//*[text()='إغلاق']")
	public WebElement closeBtnDes;

	// @FindBy(xpath = "//*[@id=\"upload_design_input_for_signoff\"]")
	@FindBy(xpath = "//input[@type='file']")
	public WebElement chooseFileBtnDes;

	@FindBy(xpath = "//legend[@class='purple-text']")
	public WebElement designerSig;

	@FindBy(xpath = "//legend[@class='blue-text']")
	public WebElement cliendSig;

	public void showAgreementFun() {
		clickButton(showAgreementLinkDes);
	}

	public void closeFun() {
		clickButton(closeBtnDes);
	}

	public void openFiles() {
		clickButton(filesLinkDes);
	}

	@FindBy(xpath = "//input[@value='حفظ']")
	// @FindBy(xpath = "//*[text()='حفظ']")
	WebElement saveBtnDes;

	public void uploadFinalDesign(String folderName) throws InterruptedException, AWTException {
		chooseFileBtnDes.sendKeys(System.getProperty("user.dir") + "\\Uploads\\" + folderName);
		clickButton(saveBtnDes);
	}

	// @FindBy(partialLinkText = "تواصل معنا")
	@FindBy(xpath = "//a[text()='تواصل معنا']")
	public WebElement contactUsLinkDes;

	@FindBy(xpath = "//a[text()='تحميل تصميم']")
	public WebElement uploadDesignLinkDes;

	public void openUploadDesignFun() {
		clickButton(uploadDesignLinkDes);
	}

	@FindBy(xpath = "//span[@class='size']")
	public WebElement fileSizeDes;

	@FindBy(xpath = "//input[@type='file']")
	public WebElement selectFileBtnDes;

	public void uploadDesignFileFun(String folderName) throws InterruptedException, AWTException {
		selectFileBtnDes.sendKeys(System.getProperty("user.dir") + "\\Uploads\\" + folderName);
	}

	@FindBy(id = "message[body]")
	WebElement messageTxtBoxDes;

	@FindBy(xpath = "//input[@value='ارسال']")
	WebElement sendBtnDes;

	@FindBy(xpath = "//div[@class='green lighten-5']")
	WebElement confirmAlertMsgDes;

	public void contactUsFun(String messageDes) {
		// clickButton(contactUsLink);
		setTextElementText(messageTxtBoxDes, messageDes);
		clickButton(sendBtnDes);
		Assert.assertTrue(confirmAlertMsgDes.isDisplayed());
	}

	@FindBy(xpath = "//span[@class='lever']")
	WebElement signAgreementIconDes;

	public void openDesignerAgreementFormFun() {
		clickButton(filesLinkDes);
		clickButton(signAgreementIconDes);
	}

	@FindBy(id = "name")
	WebElement designerNameDes;

	@FindBy(id = "address")
	WebElement designerTitleDes;

	@FindBy(id = "mobile_no")
	WebElement designerPhoneDes;

	@FindBy(xpath = "//label[@for='terms_and_conditions']")
	WebElement conditionCheckBoxDes;

	@FindBy(xpath = "//*[text()='توقيع الإتفاقية']")
	WebElement agreementBtnDes;

	public void designerAgreementFormData(String nameDes, String titleDes, String phoneDes) {
		setTextElementText(designerNameDes, nameDes);
		setTextElementText(designerTitleDes, titleDes);
		setTextElementText(designerPhoneDes, phoneDes);
		clickButton(conditionCheckBoxDes);
		agreementBtnDes.submit();
	}
}