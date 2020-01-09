package clientPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyPageClientPage extends PageBase {

	public MyPageClientPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(linkText = "صفحتي")
	WebElement myPageLinkCli;

	@FindBy(linkText = "عني")
	public WebElement aboutMeLinkCli;

	@FindBy(partialLinkText = "مسابقاتي ")
	public WebElement myCompetitionsLinkCli;

	@FindBy(partialLinkText = "المدفوعات ")
	public WebElement paymentsLinkCli;

	@FindBy(partialLinkText = "بيانات الدخول")
	WebElement loginDataPageLinkCli;

	public void openMyPageFun() {
		clickButton(myPageLinkCli);
	}

	@FindBy(partialLinkText = "تعديل")
	public WebElement updateBtnCli;

	public void openUpdateMyAccountPageFun() {
		clickButton(updateBtnCli);
	}

	public void openLoginDataFormPage() {
		clickButton(loginDataPageLinkCli);
	}

	public void openMyCompetitions() {
		clickButton(myCompetitionsLinkCli);
	}

	public void openMyPayments() {
		clickButton(paymentsLinkCli);
	}
}