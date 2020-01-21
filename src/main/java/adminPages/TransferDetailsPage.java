package adminPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class TransferDetailsPage extends PageBase {

	public TransferDetailsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "designer_transfer_designer_id")
	public WebElement designerNameDisableBox;

	@FindBy(id = "designer_transfer_amount")
	public WebElement designerAmountDisableBox;

	@FindBy(id = "designer_transfer_designer_account_id")
	public WebElement designerAccountDisableBox;

	@FindBy(id = "designer_transfer_request_count")
	public WebElement designerRequestCountDisableBox;

	@FindBy(id = "designer_transfer_last_request_date")
	public WebElement designerLastRequestDateTransferDisableBox;

	@FindBy(id = "designer_transfer_notes")
	public WebElement designerTransferNotesBox;

	@FindBy(id = "designer_transfer_process_fees")
	WebElement designerTransferFeesTxtBox;

	@FindBy(id = "designer_transfer_transferred")
	WebElement designerTransferList;

	@FindBy(id = "designer_transfer_transferred_date")
	WebElement designerTransferDateTxtBox;

	@FindBy(id = "designer_transfer_notes_by_admin")
	WebElement designerTransferNotesTxtBox;

	@FindBy(xpath = "//*[@value='Submit']")
	public WebElement submitTransferDetailsBtn;

	@FindBy(xpath = "//*[@href='/admin/transfers?locale=en']")
	WebElement cancelTransferDetailsBtn;

	@FindBy(xpath = "//*[@text()='Delete']")
	WebElement deleteTransferDetailsBtn;

	@FindBy(xpath = "//*[@class='flash_alert']")
	public WebElement confirmDetailsTransferMsg;

	public void changeStatusDetailsToTransferresFun(int transferIndex) {
		selectItemWithIndex(designerTransferList, transferIndex);
	}

	public void adminSendTransferRequestFun(int feesAmount, int transferIndex, String transferDate,
			String transferNotes) {
		setTextElementText(designerTransferFeesTxtBox, feesAmount);
		selectItemWithIndex(designerTransferList, transferIndex);
		setTextElementText(designerTransferDateTxtBox, transferDate);
		designerTransferNotesTxtBox.clear();
		setTextElementText(designerTransferNotesTxtBox, transferNotes);
	}

	public void submitTransferDetailsDataFun() {
		clickButton(submitTransferDetailsBtn);
	}

	public void cancelTransferDetailsDataFun() {
		clickButton(cancelTransferDetailsBtn);
	}

	public void deleteTransferDetailsDataFun() {
		clickButton(deleteTransferDetailsBtn);
	}

}
