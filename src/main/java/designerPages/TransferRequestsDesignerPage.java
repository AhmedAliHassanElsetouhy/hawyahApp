package designerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class TransferRequestsDesignerPage extends PageBase {

	public TransferRequestsDesignerPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[text()='طلبات التحويل']")
	public WebElement balanceDes;

	@FindBy(xpath = "//*[text()='نموذج طلب تحويل الرصيد الحالي']")
	public WebElement transferModelDes;

	@FindBy(xpath = "//a[@href='#SendNewTransferRequest']")
	public WebElement sendTransferBtnDes;

	@FindBy(id = "SendNewTransferRequest")
	public WebElement modelViewDes;

	@FindBy(xpath = "//div[@class='total_transferred']")
	public WebElement totalTransferredDes;

	@FindBy(xpath = "//*[text()='تاريخ طلب التحويل']")
	public WebElement transferDateDes;

	// @FindBy(partialLinkText = "X")
	// WebElement closeIcon;

	public void sendTransferFun() {
		clickButton(sendTransferBtnDes);
	}

	// public void close() {
	// clickButton(closeIcon);
	// }

	@FindBy(xpath = "//*[text()='إلغاء']")
	WebElement cancelDes;

	public void cancelFun() {
		clickButton(cancelDes);
	}
}