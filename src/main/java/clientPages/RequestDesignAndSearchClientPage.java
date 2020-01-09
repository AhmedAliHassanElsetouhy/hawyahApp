package clientPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RequestDesignAndSearchClientPage extends PageBase {

	public RequestDesignAndSearchClientPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(linkText = "بحث متقدم")
	public WebElement advancedSearchLinkCli;

	@FindBy(xpath = "//*[text()='بحث']")
	WebElement searchLinkCli;

	@FindBy(id = "design_requests_search_keyword")
	public WebElement searchTxtBox;

	// @FindBy(xpath = "//*[text()='لا توجد نتائج للبحث']")
	@FindBy(xpath = "//div[@class='contest-listing']")
	public WebElement noResultTxtBoxCli;

	@FindBy(xpath = "//*[text()='لايوجد تصاميم']")
	WebElement newDesignCli;

	@FindBy(xpath = "//*[text()='الفضية']")
	WebElement silverCheckBoxCli;

	@FindBy(xpath = "//*[text()='الذهبية']")
	WebElement goldenCheckBoxCli;

	@FindBy(id = "design_requests_price_from")
	WebElement fromTxtBoxCli;

	@FindBy(id = "design_requests_price_to")
	WebElement toTxtBoxCli;

	@FindBy(xpath = "//label[@for='days_left_3']")
	WebElement moreTwoDaysOptionCli;

	@FindBy(xpath = "//*[@class='select-wrapper md-dropdown form-control']")
	// @FindBy(xpath = "//*[text()='الحالة']")
	WebElement statusLstCli;

	@FindBy(xpath = "//*[text()='منتهية']")
	WebElement endStatusLstCli;

	@FindBy(xpath = "//*[text()='مستمرة']")
	WebElement activeStatusLstCli;

	public void selectStatus(String status) {
		// selectItemWithVisible(statusLst, status);
		clickButton(statusLstCli);
		clickButton(endStatusLstCli);
	}

	public void selectActiveStatus(String status) {
		// selectItemWithVisible(statusLst, status);
		clickButton(statusLstCli);
		clickButton(activeStatusLstCli);
	}

	public void sendFromToPrice(String string, String string2) {
		clickButton(moreTwoDaysOptionCli);
		setTextElementText(fromTxtBoxCli, string);
		setTextElementText(toTxtBoxCli, string2);
	}

	public void searchBtnFun() {
		clickButton(searchLinkCli);
	}

	@FindBy(xpath = "//*[text()='الفئة الفضية']")
	// @FindBy(xpath = "//*[text()='Silver Pack']")
	public WebElement silverFlagCli;

	@FindBy(xpath = "//*[text()='الفئة الذهبية']")
	// @FindBy(xpath = "//*[text()='Golden Pack']")
	public WebElement goldenFlagCli;

	public void searchFun() {
		clickButton(searchLinkCli);
	}

	public void advancedSearchFun() {
		clickButton(advancedSearchLinkCli);
	}

	public void sendSearchTxt(String searchText) {
		setTextElementText(searchTxtBox, searchText);
		// searchTxtBox.sendKeys(Keys.ENTER);
		pressEnter(searchTxtBox);
	}

	// ------ for search three list not made yet

	@FindBy(xpath = "//*[@class='chosen-search-input']")
	WebElement designTypeTxtBoxCli;

	// @FindBy(xpath = "//*[text()='اختر نوع التصميم']")
	@FindBy(id = "design_type_chosen")
	WebElement selectDesignLstCli;

	// @FindBy(xpath = "//*[text()='تصميم هوية أساسية']")
	@FindBy(id = "design_type")
	WebElement designLstOptionCli;

	// @FindBy(xpath = "//*[@id=\"contests_organization_category_chosen\"]/div")
	// List<WebElement> selectActivityLst;

	@FindBy(id = "contests_organization_category_chosen")
	WebElement selectActivityLstCli;

	@FindBy(xpath = "//*[text()='المحاسبة والمالية']")
	WebElement activityLstOptionCli;

	// @FindBy(xpath = "//*[@id=\"contests_order_chosen\"]/div")
	// List<WebElement> orderLst;

	@FindBy(id = "contests_order_chosen")
	WebElement orderLstCli;

	@FindBy(xpath = "//*[text()='الأحدث']")
	WebElement orderLstOptionCli;

	@FindBy(xpath = "//a[@class='chosen-single']")
	WebElement activityTypeLstCli;

	@FindBy(xpath = "//input[@class='chosen-search-input']")
	WebElement activityOptionTxtFieldCli;

	public void searchList(String designIndex, int activityIndex, int orderIndex) {
		clickButton(activityTypeLstCli);
		setTextElementText(activityOptionTxtFieldCli, designIndex);
		activityOptionTxtFieldCli.sendKeys(Keys.ENTER);

		clickButton(selectActivityLstCli);
		clickButton(orderLstCli);
		// clickButton(activityLstOption);
		// selectItemWithIndex(selectActivityLst, activityIndex);
		// selectItemWithIndex(orderLst, orderIndex);
	}

	// -----------

	public void silverfilter() {
		clickButton(silverCheckBoxCli);
	}

	public void goldenOption() {
		clickButton(goldenCheckBoxCli);
	}
}
