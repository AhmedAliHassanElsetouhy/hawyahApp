package designerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class MyPageDesignerPage extends PageBase {

	public MyPageDesignerPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(partialLinkText = "عني")
	public WebElement aboutMeLinkDes;

	public void openAboutMe() {
		clickButton(aboutMeLinkDes);
	}

	@FindBy(partialLinkText = "احصائياتي")
	public WebElement myStatsLinkDes;

	public void openMyStats() {
		clickButton(myStatsLinkDes);
	}

	@FindBy(partialLinkText = "الحسابات البنكية")
	// @FindBy(xpath = "//*[text()='الحسابات البنكية']")
	public WebElement myBankAccountLinkDes;

	public void openMyBankAccount() {
		clickButton(myBankAccountLinkDes);
	}

	@FindBy(partialLinkText = "طلبات التحويل")
	public WebElement myTransfersLinkDes;

	public void openMyTransfers() {
		clickButton(myTransfersLinkDes);
	}

	@FindBy(partialLinkText = "أعمالي")
	public WebElement myWorksLinkDes;

	public void openMyWorks() {
		clickButton(myWorksLinkDes);
	}

	@FindBy(xpath = "//div[@class='green lighten-5']")
	public WebElement confirmSaveDesignMsgDes;
}