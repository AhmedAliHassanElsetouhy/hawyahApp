package adminPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

import clientPages.PageBase;

public class PaymentsAdminPage extends PageBase {

	public PaymentsAdminPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "q_contest_title_cont")
	WebElement paymentsFilterContestTitleTxtBox;

	@FindBy(id = "q_created_at_eq")
	WebElement paymentsFilterCreatedDateTxtBox;

	@FindBy(id = "q_payment_type_cont")
	WebElement paymentsFilterPaymentTypeList;

	@FindBy(id = "q_amount_eq")
	WebElement paymentsFilterAmountTxtBox;

	@FindBy(id = "q_transaction_number_cont")
	WebElement paymentsFilterTransactionTxtBox;

	@FindBy(id = "q_bank_name_cont")
	WebElement paymentsFilterBankNameTxtBox;

	@FindBy(xpath = "//*[@value='search']")
	WebElement paymentsFilterSearchBtn;

	@FindBy(xpath = "//*[@href='/admin/payments?locale=en']")
	WebElement paymentsFilterClearBtn;

	@FindBy(xpath = "//tr")
	public List<WebElement> paymentsFilterPaymentRecordsList;

	@FindBy(xpath = "//*[@class='text-center well']")
	public WebElement paymentsFilterTotalPaymentsAmount;

	public void searchFilterPaymentsFun() {
		clickButton(paymentsFilterSearchBtn);
	}

	public void searchPaymentsFilterAmountFun(String paymentAmount) {
		setTextElementText(paymentsFilterAmountTxtBox, paymentAmount);
	}

	public void searchTransactionPaymentsFilterAmountFun(String transactionAmount) {
		setTextElementText(paymentsFilterTransactionTxtBox, transactionAmount);
	}

	public void searchPaymentTypePaymentsFilterFun(int index) {
		selectItemWithIndex(paymentsFilterPaymentTypeList, index);
	}

	public void clearSearchPaymentFun() {
		clickButton(paymentsFilterClearBtn);
	}
}
