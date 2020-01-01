package designerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class TransferRequestsUserPage extends PageBase {

	public TransferRequestsUserPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//div[@class='total_transferred']")
	@FindBy(xpath = "//*[text()='طلبات التحويل']")
	public WebElement balance;

	@FindBy(xpath = "//*[text()='نموذج طلب تحويل الرصيد الحالي']")
	public WebElement transferModel;

	@FindBy(partialLinkText = "إضغط لإرسال طلب تحويل للرصيد الحالي")
	WebElement sendTransferBtn;

	// @FindBy(xpath = "//div[@class='modal-content']")
	@FindBy(id = "SendNewTransferRequest")
	public WebElement modelView;

	@FindBy(partialLinkText = "X")
	WebElement closeIcon;

	public void sendTransferFun() {
		clickButton(sendTransferBtn);
	}

	public void close() {
		clickButton(closeIcon);
	}
}
