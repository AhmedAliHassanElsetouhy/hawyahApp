package adminPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class ContestsAdminPage extends PageBase {

	public ContestsAdminPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "q_title_eq")
	WebElement contestTitleTxtBox;

	@FindBy(id = "q_design_type_id_eq")
	WebElement designTypesList;

	@FindBy(id = "q_contest_package_id_eq")
	WebElement contestPackageTypesList;

	@FindBy(id = "q_total_price_eq")
	WebElement priceTxtBox;

	@FindBy(id = "q_customer_id_eq")
	WebElement customersNameList;

	@FindBy(id = "q_status_eq")
	WebElement statusList;

	@FindBy(id = "q_launched_at_eq")
	WebElement launchedDateTxtBox;

	@FindBy(id = "q_ended_at_eq")
	WebElement endedDateTxtBox;

	@FindBy(id = "q_created_at_gteq")
	WebElement fromCreatedDateTxtBox;

	@FindBy(id = "q_created_at_lteq")
	WebElement toCreatedDateTxtBox;

	@FindBy(xpath = "//*[@href='/admin/contests?locale=en']")
	WebElement clearBtn;

	@FindBy(xpath = "//*[@type='submit']")
	WebElement searchBtn;

	@FindBy(xpath = "//*[text()='Logo & brand identity pack']")
	public WebElement logoAndBrand;

	@FindBy(xpath = "//*[text()='Phase 1']")
	public WebElement contestStatus;

	@FindBy(xpath = "//*[text()='Silver Pack']")
	public WebElement packageStatus;

	@FindBy(xpath = "//div[@class='text-center']")
	public WebElement recordsBody;

	// @FindBy(xpath = "//span[@class='translation_missing']")
	@FindBy(xpath = "//span[@class='label label-sm label-default']")
	public List<WebElement> phase1Status;

	@FindBy(xpath = "//span[@class='label label-sm label-warning']")
	public List<WebElement> waitingDepositStatus;

	@FindBy(xpath = "//span[@class='label label-sm label-default']")
	public List<WebElement> deliverFinalWorkStatus;

	@FindBy(xpath = "//span[@class='label label-sm label-Info']")
	public List<WebElement> completedStatus;

	public void submitSearchFun() {
		clickButton(searchBtn);
	}

	public void clearFun() {
		clickButton(clearBtn);
		// ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		// jsClickBtn);
	}

	public void contestFun(String contestTitle) {
		setTextElementText(contestTitleTxtBox, contestTitle);
		submitSearchFun();
	}

	public void designTypeFun(int designTypeItem) {
		selectItemWithIndex(designTypesList, designTypeItem);
		submitSearchFun();
	}

	public void packageTypeFun(int packageTypeItem) {
		selectItemWithIndex(contestPackageTypesList, packageTypeItem);
		submitSearchFun();
	}

	public void priceFun(int price) {
		setTextElementText(priceTxtBox, price);
		submitSearchFun();
	}

	public void customerFun(int customerItem) {
		selectItemWithIndex(customersNameList, customerItem);
		submitSearchFun();
	}

	public void statusFun(int statusItem) {
		selectItemWithIndex(statusList, statusItem);
		submitSearchFun();
	}

	public void launchedDateFun(String launchedDate) {
		setTextElementText(launchedDateTxtBox, launchedDate);
		submitSearchFun();
	}

	public void endedDateFun(String endedDate) {
		setTextElementText(endedDateTxtBox, endedDate);
		submitSearchFun();
	}

	public void fromCreatedDateFun(String fromCreatedDate) {
		setTextElementText(fromCreatedDateTxtBox, fromCreatedDate);
		submitSearchFun();
	}

	public void toCreatedDateFun(String toCreatedDate) {
		setTextElementText(toCreatedDateTxtBox, toCreatedDate);
		submitSearchFun();
	}

	public void searchFun(String contestTitle, int designTypeItem, int packageTypeItem, int price, int customerItem,
			int statusItem, String launchedDate, String endedDate, String fromCreatedDate, String toCreatedDate) {
		setTextElementText(contestTitleTxtBox, contestTitle);
		selectItemWithIndex(designTypesList, designTypeItem);
		selectItemWithIndex(contestPackageTypesList, packageTypeItem);
		setTextElementText(priceTxtBox, price);
		selectItemWithIndex(customersNameList, customerItem);
		selectItemWithIndex(statusList, statusItem);
		setTextElementText(launchedDateTxtBox, launchedDate);
		setTextElementText(endedDateTxtBox, endedDate);
		setTextElementText(fromCreatedDateTxtBox, fromCreatedDate);
		setTextElementText(toCreatedDateTxtBox, toCreatedDate);
		submitSearchFun();
	}
}