package adminPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class MoneyAdminPage extends PageBase {

	public MoneyAdminPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[@class='caption-subject font-red sbold uppercase']")
	public List<WebElement> moneyPageTitleHeader;

	@FindBy(id = "q_transaction_id_eq")
	WebElement transactionIdTxtBox;

	@FindBy(id = "q_amount_eq")
	public WebElement amountEqTxtBox;

	@FindBy(id = "q_transaction_type_eq")
	WebElement transactionTypeList;

	@FindBy(id = "q_transaction_date_gteq")
	WebElement transactionFromDateTxtBox;

	@FindBy(id = "q_transaction_date_lteq")
	WebElement transactionToDateTxtBox;

	@FindBy(xpath = "//*[@href='/admin/money?locale=en&populate_now=true']")
	WebElement buildMoneyReportBtn;

	@FindBy(xpath = "//*[@href='https://hawyah-dev.herokuapp.com/admin/money.xlsx?locale=en&populate_now=true']")
	WebElement exportExcelSheetBtn;

	@FindBy(xpath = "//*[@value='search']")
	WebElement moneyReportSearchFilterBtn;

	// @FindBy(xpath =
	// "//*[@href='https://hawyah-dev.herokuapp.com/admin/money?locale=en']")
	@FindBy(xpath = "//*[text()='Clear']")
	WebElement moneyReportClearFilterBtn;

	@FindBy(xpath = "//tr[@class='default']")
	public List<WebElement> moneyReportResultRecords;

	@FindBy(xpath = "//*[@class='text-center well label-success']")
	public WebElement paymentsLabel;

	@FindBy(xpath = "//*[@class='text-center well label-danger']")
	WebElement transfersLabel;

	// @FindBy(xpath = "//*[@class='text-center well']")
	// public WebElement moneyLabel;

	@FindBy(xpath = "//strong")
	public List<WebElement> moneyLabel;

	public void trasactionTypeFilterFun(int transactionIndex) {
		selectItemWithIndex(transactionTypeList, transactionIndex);
	}

	public void amountSearchFilterFun(String moneyAmount) {
		setTextElementText(amountEqTxtBox, moneyAmount);
	}

	public void searchFiltermoneyReportFun() {
		clickButton(moneyReportSearchFilterBtn);
	}

	public void clearSearchFilterMoneyReportFun() {
		clickButton(moneyReportClearFilterBtn);
	}

	public void buildAndExportMoneyReportExcelSheetFun() {
		clickButton(buildMoneyReportBtn);
		clickButton(exportExcelSheetBtn);
	}
}