package pages;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyWorkUserPage extends PageBase {

	public MyWorkUserPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[text()='اضف تصميم جديد']")
	public WebElement myWorkHeaderMsg;

	@FindBy(id = "designer_previous_work_design_title")
	// @FindBy(name = "designer_previous_work[design_title]")
	WebElement designTitleTxtBox;

	@FindBy(id = "upload_design_input")
	WebElement uploadIcon;

	public void addDesignFun(String title, String folderName) throws InterruptedException, AWTException {
		setTextElementText(designTitleTxtBox, title);
		clickButton(uploadIcon);
		FileUploadWithRobot(folderName);
	}
}
