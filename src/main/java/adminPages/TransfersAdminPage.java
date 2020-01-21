package adminPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class TransfersAdminPage extends PageBase {

	public TransfersAdminPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "q_contest_title_cont")
	WebElement transferFilterContestTitleTxtBox;

	@FindBy(id = "q_designer_screen_name_cont")
	WebElement transferFilterDisplayNameTxtBox;

	@FindBy(id = "q_amount_eq")
	WebElement transferFilterAmountTxtBox;

	@FindBy(id = "q_last_request_date_eq")
	WebElement transferFilterRequestDateTxtBox;

	@FindBy(id = "q_transferred_true")
	WebElement transferFilterSwapTransferingCheckBox;

	@FindBy(xpath = "//*[@value='search']")
	WebElement transferFilterSearchBtn;

	@FindBy(xpath = "//*[@href='https://hawyah-dev.herokuapp.com/admin/transfers?locale=en']")
	WebElement transferFilterClearBtn;

	@FindBy(xpath = "//*[@class='alert-danger text-center well']")
	public WebElement notTransferredAmount;

	@FindBy(xpath = "//*[@class='alert-success text-center well']")
	public WebElement transferredAmount;
	
	@FindBy(xpath="//*[@class='danger']")
	public List<WebElement> notTransfersRecords;
	
	@FindBy(xpath="//*[@class='success']")
	public List<WebElement> transferedRecords;
	
	public void clearSearchTransfersFun() {
		clickButton(transferFilterClearBtn);
	}

	public void completedTransfersFun() {
		clickButton(transferFilterSwapTransferingCheckBox);
	}
	
	public void searchFilterTransfersFun() {
		clickButton(transferFilterSearchBtn);
	}
	
	public void searchTransfersFilterAmountFun(String transferAmount) {
		setTextElementText(transferFilterAmountTxtBox, transferAmount);
	}
}
