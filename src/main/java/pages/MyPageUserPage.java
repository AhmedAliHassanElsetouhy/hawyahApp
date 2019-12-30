package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyPageUserPage extends PageBase {

	public MyPageUserPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(partialLinkText = "عني")
	public WebElement aboutMeLink;

	public void openAboutMe() {
		clickButton(aboutMeLink);
	}

	@FindBy(partialLinkText = "احصائياتي")
	public WebElement myStatsLink;

	public void openMyStats() {
		clickButton(myStatsLink);
	}

	@FindBy(partialLinkText = "الحسابات البنكية")
//	@FindBy(xpath = "//*[text()='الحسابات البنكية']")
	public WebElement myBankAccountLink;

	public void openMyBankAccount() {
		clickButton(myBankAccountLink);
	}

	@FindBy(partialLinkText = "طلبات التحويل")
	public WebElement myTransfersLink;

	public void openMyTransfers() {
		clickButton(myTransfersLink);
	}

	@FindBy(partialLinkText = "أعمالي")
	public WebElement myWorksLink;

	public void openMyWorks() {
		clickButton(myWorksLink);
	}
}
