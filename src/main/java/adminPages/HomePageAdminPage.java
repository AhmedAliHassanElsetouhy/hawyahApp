package adminPages;

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
	@FindBy(xpath = "//a[@class=\"nav-link nav-toggle\"]")
	public List<WebElement> adminMainListItems;

	// @FindBy(xpath="//h3[@class='page-title']")
	@FindBy(xpath = "//*[text()='Dashboard']")
	public WebElement adminHomePageTitle;
	
	@FindBy(xpath="//span[@class=\"username username-hide-on-mobile\"]")
	public WebElement userAdminMenu;
	
	@FindBy(xpath="//a[@href=\"/web/logout?locale=en\"]")
	WebElement logoutbtn;
	
	@FindBy(id="graph4")
	public WebElement packagesGraph;
	
	@FindBy(id="graph3")
	public WebElement designsGraph;
	
	@FindBy(id="graph5")
	public WebElement usersGraph;
	
	@FindBy(id="graph")
	public WebElement moneyGraph;
	
	@FindBy(id="graph2")
	public WebElement statusGraph;
	
	
	public void logoutAdminFun() {
		clickButton(logoutbtn);
	}
	
	public void openUserAdminMenuFun() {
		clickButton(userAdminMenu);
	}

	public void openAdminMenuItem(int adminMainMenuOption) {
		selectSpecificIcon(adminMainListItems, adminMainMenuOption);
	}
}
