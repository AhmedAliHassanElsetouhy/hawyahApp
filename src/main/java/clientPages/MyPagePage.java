package clientPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyPagePage extends PageBase {

	public MyPagePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(linkText = "صفحتي")
	WebElement myPageLink;

	public void openMyPageFun() {
		clickButton(myPageLink);
	}

	@FindBy(partialLinkText = "تعديل")
	WebElement updateBtn;

	public void openUpdateMyAccountPageFun() {
		clickButton(updateBtn);
	}

	@FindBy(linkText = "عني")
	public WebElement aboutMeLink;

	@FindBy(partialLinkText = "مسابقاتي ")
	public WebElement myCompetitionsLink;

	@FindBy(partialLinkText = "المدفوعات ")
	public WebElement paymentsLink;

	@FindBy(partialLinkText = "بيانات الدخول")
	WebElement loginDataPageLink;

	public void openLoginDataFormPage() {
		clickButton(loginDataPageLink);
	}

	public void openMyCompetitions() {
		clickButton(myCompetitionsLink);
	}

	public void openMyPayments() {
		clickButton(paymentsLink);
	}
}