package adminPages;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class HomePageAdminPage extends PageBase {

	public HomePageAdminPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//li[@class='nav-item']")
	@FindBy(xpath = "//a[@class='nav-link nav-toggle']")
	public List<WebElement> adminSideMenuListItems;

	// @FindBy(xpath="//h3[@class='page-title']")
	@FindBy(xpath = "//*[text()='Dashboard']")
	public WebElement adminHomePageTitle;

	@FindBy(xpath = "//*[@href='/admin/design_types?locale=en']")
	WebElement designsAndPricingBtn;

	public void openDesignsAndPricingFun() {
		clickButton(designsAndPricingBtn);
	}

	@FindBy(xpath = "//*[@href='javascript:;']")
	WebElement generalSettingsBtn;

	public void openGeneralSettingsFun() {
		clickButton(generalSettingsBtn);
	}

	@FindBy(xpath = "//span[@class='username username-hide-on-mobile']")
	public WebElement userAdminMenu;

	@FindBy(xpath = "//a[@href='/web/logout?locale=en']")
	WebElement logoutbtn;

	@FindBy(id = "graph4")
	public WebElement packagesGraph;

	@FindBy(id = "graph3")
	public WebElement designsGraph;

	@FindBy(id = "graph5")
	public WebElement usersGraph;

	@FindBy(id = "graph")
	public WebElement moneyGraph;

	@FindBy(id = "graph2")
	public WebElement statusGraph;

	// @FindBy(xpath = "//*[@href='javascript:;']")
	@FindBy(xpath = "//a[@class='dropdown-toggle']")
	public List<WebElement> adminMenu;

	@FindBy(xpath = "//div[@class='portlet-body']")
	public WebElement searchResultCntainer;

	@FindBy(xpath = "//span[@class='input-group-btn']")
	WebElement searchIcon;

	public void searchClick() {
		clickButton(searchIcon);
	}

	@FindBy(xpath = "//input[@placeholder='Search...']")
	public WebElement searchTxtBox;

	public void searchTxtBoxFun(String searchText) {
		setTextElementText(searchTxtBox, searchText);
	}

	public void logoutAdminFun() throws AWTException {
		clickButton(logoutbtn);
		refreshPage();
	}

	public void openUserAdminMenuFun() {
		clickButton(userAdminMenu);
	}

	public void openAdminMenuItem(int adminMainMenuOption) {
		selectSpecificIcon(adminSideMenuListItems, adminMainMenuOption);
	}
}