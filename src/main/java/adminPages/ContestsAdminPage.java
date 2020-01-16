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

	@FindBy(xpath = "//span[@class='label label-sm label-default']//span[@class='translation_missing']")
	// @FindBy(xpath = "//*[text()='Phase 1']")
	public List<WebElement> phase1Status;

	// @FindBy(xpath = "//span[@class='label label-sm label-warning']")
	@FindBy(xpath = "//*[text()=' Waiting for deposit payment ']")
	public List<WebElement> waitingDepositStatus;

	@FindBy(xpath = "//*[text()=' مرحلة تسليم العمل النهائي ']")
	// @FindBy(xpath = "//span[@class='label label-sm label-default']")
	public List<WebElement> deliverFinalWorkStatus;

	@FindBy(xpath = "//span[@class='label label-sm label-Info']")
	public List<WebElement> completedStatus;

	// @FindBy(xpath="//span[@class='label label-sm label-default']")
	@FindBy(xpath = "//*[text()=' Cancelled by admin ']")
	public List<WebElement> CancelledByAdminStatus;

	@FindBy(xpath = "//div[@class='flash_alert']")
	public WebElement openContestFormSuccessMsg;

	@FindBy(xpath = "//a[@href='#contest_information']")
	public WebElement detailsTab;

	@FindBy(xpath = "//a[@href='#payment_info']")
	// @FindBy(xpath = "//*[text()='Payments']")
	public WebElement paymentsTab;

	@FindBy(xpath = "//a[@href='#designs']")
	public WebElement designsTab;

	@FindBy(id = "contest_status")
	public WebElement contestStatusList;

	// @FindBy(xpath = "//div[@class='center-align']")
	@FindBy(xpath = "//div[@class='col-xs-12']")
	public WebElement designsSignofMsg;

	@FindBy(id = "contest_design_type_id")
	public WebElement contestDesignTypeList;

	@FindBy(id = "contest_title")
	WebElement detailsContestTitleTxtBox;

	@FindBy(id = "contest_organization_name")
	WebElement detailsContestOrganizationNameTxtBox;

	@FindBy(id = "contest_organization_description")
	WebElement detailsOrgDescTxtBox;

	@FindBy(id = "contest_description")
	WebElement detailsContestDescTxtBox;

	@FindBy(id = "contest_additional_info")
	WebElement detailsContestAddInfoTxtBox;

	@FindBy(xpath = "//div[@class='tab-content']")
	public WebElement paymentStatus;

	// @FindBy(xpath = "//div[@class='form-actions']")
	@FindBy(xpath = "//div[@class='col-xs-12 col-sm-6']")
	// @FindBy(id = "contest_payment_status")
	public List<WebElement> paymentFormView;

	@FindBy(xpath = "//div[@class='tab-content']")
	public WebElement paymentDataView;

	@FindBy(xpath = "//*[@value='Submit']")
	public WebElement submitBtn;

	@FindBy(xpath = "//*[@href='/admin/contests?locale=en']")
	public WebElement cancelBtn;

	// @FindBy(xpath = "//a[@title='Cancel']")
	@FindBy(xpath = "//img[@alt='Cancel icon']")
	public WebElement cancelContestIcon;

	// @FindBy(xpath = "//*[@title='Cancel']")
	// public WebElement cancelContestIcon1;

	@FindBy(id = "contest_cancel_reason")
	WebElement cancelReasonTxtBox;

	public void openCancelContestFun() {
		if (cancelContestIcon.isDisplayed()) {
			clickButton(cancelContestIcon);
		} else {
			System.out.println("Cancel reason icon not appear");
		}
	}

	public void cancelContestFun(String cancelationReason) {
		if (cancelReasonTxtBox.isDisplayed()) {
			setTextElementText(cancelReasonTxtBox, cancelationReason);
		} else {
			System.out.println("Reason textBox not appear");
		}
	}

	public void submitFun() {
		clickButton(submitBtn);
	}

	public void cancelFun() {
		clickButton(cancelBtn);
	}

	public void openDetailsTabFun() {
		clickButton(detailsTab);
	}

	public void openPaymentsTabFun() {
		clickButton(paymentsTab);
	}

	public void openDesignsTabFun() {
		clickButton(designsTab);
	}

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

	public void contestDetailsFormFun(String contestTitle, String orgName, String orgDesc, String conDesc,
			String addInfo) {
		detailsContestTitleTxtBox.clear();
		setTextElementText(detailsContestTitleTxtBox, contestTitle);
		detailsContestOrganizationNameTxtBox.clear();
		setTextElementText(detailsContestOrganizationNameTxtBox, orgName);
		detailsOrgDescTxtBox.clear();
		setTextElementText(detailsOrgDescTxtBox, orgDesc);
		detailsContestDescTxtBox.clear();
		setTextElementText(detailsContestDescTxtBox, conDesc);
		detailsContestAddInfoTxtBox.clear();
		setTextElementText(detailsContestAddInfoTxtBox, addInfo);
	}

	public void phase1StatusPaymentsFun() {

	}
}