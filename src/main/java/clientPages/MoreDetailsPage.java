package clientPages;

import java.awt.AWTException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MoreDetailsPage extends PageBase {

	public MoreDetailsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath="//*[text()='زودنا بالتفاصيل']")
	@FindBy(xpath = "//li[@data-step='3']")
	public WebElement moreDetailsPageHeaderText;

	@FindBy(xpath = "//*[text()='لا يمكن أن يكون المحتوى فارغا']")
	public WebElement activityClassificationValidationMsg;

	@FindBy(id = "contest_name_on_logo")
	WebElement activityNameTxtField;

	@FindBy(id = "contest_organization_description")
	WebElement descriptionTxtField;

	// @FindBy(id = "ما هو تصنيف نشاطك؟")
	@FindBy(xpath = "//a[@class='chosen-single']")
	WebElement activityTypeLst;

	// @FindBy(xpath = "//*[text()='الزراعة']")
	@FindBy(xpath = "//input[@class='chosen-search-input']")
	WebElement activityOptionTxtField;

	@FindBy(id = "contest_slogan_on_logo")
	WebElement contestTxtField;

	@FindBy(id = "contest_additional_info")
	WebElement additionalInfoTxtField;

	@FindBy(xpath = "//*[text()='اختر ملف من جهازك']")
	WebElement uploadBtn;

	@FindBy(id = "contest_title")
	WebElement titleTxtField;

	public void moreDetailsFun(String activityName, String activityDesc, String activityOption, String contest,
			String additionalInfo, String folderName, String title) throws InterruptedException, AWTException {
		setTextElementText(activityNameTxtField, activityName);
		setTextElementText(descriptionTxtField, activityDesc);
		clickButton(activityTypeLst);
		// clickButton(activityOption);
		setTextElementText(activityOptionTxtField, activityOption);
		activityOptionTxtField.sendKeys(Keys.ENTER);
		setTextElementText(contestTxtField, contest);
		setTextElementText(additionalInfoTxtField, additionalInfo);
		// clickButton(uploadBtn);
		// FileUploadWithRobot(folderName);
		setTextElementText(titleTxtField, title);
	}
}
