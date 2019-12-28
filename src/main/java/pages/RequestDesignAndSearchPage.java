package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RequestDesignAndSearchPage extends PageBase {

	public RequestDesignAndSearchPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(linkText = "بحث متقدم")
	public WebElement advancedSearchLink;

	@FindBy(xpath = "//*[text()='بحث']")
	WebElement searchLink;

	@FindBy(id = "design_requests_search_keyword")
	public WebElement searchTxtBox;

	// @FindBy(xpath = "//*[text()='لا توجد نتائج للبحث']")
	@FindBy(xpath = "//div[@class='contest-listing']")
	public WebElement noResultTxtBox;

	@FindBy(xpath = "//*[text()='لايوجد تصاميم']")
	WebElement newDesign;

	@FindBy(xpath = "//*[text()='الفضية']")
	WebElement silverCheckBox;

	@FindBy(xpath = "//*[text()='الذهبية']")
	WebElement goldenCheckBox;

	@FindBy(id = "design_requests_price_from")
	WebElement fromTxtBox;

	@FindBy(id = "design_requests_price_to")
	WebElement toTxtBox;

	@FindBy(xpath = "//label[@for='days_left_3']")
	WebElement moreTwoDaysOption;

	@FindBy(xpath = "//*[@class='select-wrapper md-dropdown form-control']")
	// @FindBy(xpath = "//*[text()='الحالة']")
	WebElement statusLst;

	@FindBy(xpath = "//*[text()='منتهية']")
	WebElement endStatusLst;

	@FindBy(xpath = "//*[text()='مستمرة']")
	WebElement activeStatusLst;

	public void selectStatus(String status) {
		// selectItemWithVisible(statusLst, status);
		clickButton(statusLst);
		clickButton(endStatusLst);
	}

	public void selectActiveStatus(String status) {
		// selectItemWithVisible(statusLst, status);
		clickButton(statusLst);
		clickButton(activeStatusLst);
	}

	public void sendFromToPrice(int fromPrice, int toPrice) {
		clickButton(moreTwoDaysOption);
		setTextElementText(fromTxtBox, fromPrice);
		setTextElementText(toTxtBox, toPrice);
	}

	public void searchBtnFun() {
		clickButton(searchLink);
	}

	@FindBy(xpath = "//*[text()='الفئة الفضية']")
	// @FindBy(xpath = "//*[text()='Silver Pack']")
	public WebElement silverFlag;

	@FindBy(xpath = "//*[text()='الفئة الذهبية']")
	// @FindBy(xpath = "//*[text()='Golden Pack']")
	public WebElement goldenFlag;

	public void searchFun() {
		clickButton(searchLink);
	}

	public void advancedSearchFun() {
		clickButton(advancedSearchLink);
	}

	public void sendSearchTxt(String searchText) {
		setTextElementText(searchTxtBox, searchText);
		// searchTxtBox.sendKeys(Keys.ENTER);
		pressEnter(searchTxtBox);
	}

	//------ for search three list not made yet
	
	@FindBy(xpath = "//*[@class='chosen-search-input']")
	WebElement designTypeTxtBox;

	// @FindBy(xpath = "//*[text()='اختر نوع التصميم']")
	@FindBy(id = "design_type_chosen")
	WebElement selectDesignLst;

	// @FindBy(xpath = "//*[text()='تصميم هوية أساسية']")
	@FindBy(id = "design_type")
	WebElement designLstOption;

	// @FindBy(xpath = "//*[text()='إختر النشاط']")
	@FindBy(id = "contests_organization_category_chosen")
	WebElement selectActivityLst;

	@FindBy(xpath = "//*[text()='المحاسبة والمالية']")
	WebElement activityLstOption;

	// @FindBy(xpath = "//*[text()='ترتيب حسب']")
	@FindBy(id = "contests_order_chosen")
	WebElement orderLst;

	@FindBy(xpath = "//*[text()='الأحدث']")
	WebElement orderLstOption;

	public void searchList(int designIndex, int activityIndex, int orderIndex) {
		// clickButton(selectDesignLst);
		// selectItemWithIndex(designLstOption, index);
		// clickButton(designLstOption);

		selectItemWithIndex(selectActivityLst, activityIndex);

		selectItemWithIndex(orderLst, orderIndex);
	}

	//-----------
	
	public void silverfilter() {
		clickButton(silverCheckBox);
	}

	public void goldenOption() {
		clickButton(goldenCheckBox);
	}
}
