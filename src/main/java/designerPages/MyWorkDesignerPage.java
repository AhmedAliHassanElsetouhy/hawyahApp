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
	public WebElement myWorkHeaderMsg;

	@FindBy(id = "designer_previous_work_design_title")
	// @FindBy(name = "designer_previous_work[design_title]")
	WebElement designTitleTxtBox;

	// @FindBy(id = "upload_design_input")
	@FindBy(xpath = "//input[@type='file']")
	public WebElement uploadIcon;

	@FindBy(xpath = "//*[text()='اختر نوع التصميم']")
	WebElement selectDesignTypeList;

	@FindBy(xpath = "تصميم هوية أساسية")
	WebElement designBasicIdentity;

	public void addDesignFun(String title) throws InterruptedException, AWTException {
		setTextElementText(designTitleTxtBox, title);
		clickButton(selectDesignTypeList);
		clickButton(designBasicIdentity);
		clickButton(saveBtn);
	}

	@FindBy(xpath = "//*[text()='حفظ']")
	WebElement saveBtn;
}
