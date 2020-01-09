package clientPages;

import java.awt.AWTException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MoreDetailsClientPage extends PageBase {

	public MoreDetailsClientPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath="//*[text()='زودنا بالتفاصيل']")
	@FindBy(xpath = "//li[@data-step='3']")
	public WebElement moreDetailsPageHeaderTextCli;

	@FindBy(xpath = "//*[text()='لا يمكن أن يكون المحتوى فارغا']")
	public WebElement activityClassificationValidationMsgCli;

	@FindBy(id = "contest_name_on_logo")
	WebElement activityNameTxtFieldCli;

	@FindBy(id = "contest_organization_description")
	WebElement descriptionTxtFieldCli;

	// @FindBy(id = "ما هو تصنيف نشاطك؟")
	@FindBy(xpath = "//a[@class='chosen-single']")
	WebElement activityTypeLstCli;

	// @FindBy(xpath = "//*[text()='الزراعة']")
	@FindBy(xpath = "//input[@class='chosen-search-input']")
	WebElement activityOptionTxtFieldCli;

	@FindBy(id = "contest_slogan_on_logo")
	WebElement contestTxtFieldCli;

	@FindBy(id = "contest_additional_info")
	WebElement additionalInfoTxtFieldCli;

	// @FindBy(xpath = "//*[text()='اختر ملف من جهازك']")
	// WebElement uploadBtnCli;

	@FindBy(id = "contest_title")
	WebElement titleTxtFieldCli;

	public void moreDetailsFun(String activityName, String activityDesc, String activityOption, String contest,
			String additionalInfo, String folderName, String title) throws InterruptedException, AWTException {
		setTextElementText(activityNameTxtFieldCli, activityName);
		setTextElementText(descriptionTxtFieldCli, activityDesc);
		clickButton(activityTypeLstCli);
		// clickButton(activityOption);
		setTextElementText(activityOptionTxtFieldCli, activityOption);
		activityOptionTxtFieldCli.sendKeys(Keys.ENTER);
		setTextElementText(contestTxtFieldCli, contest);
		setTextElementText(additionalInfoTxtFieldCli, additionalInfo);
		// clickButton(uploadBtn);
		// FileUploadWithRobot(folderName);
		setTextElementText(titleTxtFieldCli, title);
	}
}