package designerPages;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class MyWorkDesignerPage extends PageBase {

	public MyWorkDesignerPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[text()='اضف تصميم جديد']")
	public WebElement myWorkHeaderMsgDes;

	@FindBy(id = "designer_previous_work_design_title")
	// @FindBy(name = "designer_previous_work[design_title]")
	WebElement designTitleTxtBoxDes;

	// @FindBy(id = "upload_design_input")
	@FindBy(xpath = "//input[@type='file']")
	public WebElement uploadIconDes;

	@FindBy(xpath = "//*[text()='اختر نوع التصميم']")
	WebElement selectDesignTypeListDes;

	@FindBy(xpath = "تصميم هوية أساسية")
	WebElement designBasicIdentityDes;

	public void addDesignFun(String titleDes) throws InterruptedException, AWTException {
		setTextElementText(designTitleTxtBoxDes, titleDes);
		clickButton(selectDesignTypeListDes);
		clickButton(designBasicIdentityDes);
		clickButton(saveBtnDes);
	}

	@FindBy(xpath = "//*[text()='حفظ']")
	WebElement saveBtnDes;
}