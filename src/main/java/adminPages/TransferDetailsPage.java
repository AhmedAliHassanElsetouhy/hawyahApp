package adminPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class TransferDetailsPage extends PageBase{

	public TransferDetailsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id="designer_transfer_designer_id")
	public WebElement designerNameDisableBox;
	
	@FindBy(id="designer_transfer_amount")
	public WebElement designerAmountDisableBox;
	
	@FindBy(id="designer_transfer_designer_account_id")
	public WebElement designerAccountDisableBox;
	
	@FindBy(id="designer_transfer_request_count")
	public WebElement designerRequestCountDisableBox;
	
	@FindBy(id="designer_transfer_last_request_date")
	public WebElement designerLastRequestDateTransferDisableBox;
	
	@FindBy(id="designer_transfer_notes")
	public WebElement designerTransferNotesBox;
	
	
	
}
