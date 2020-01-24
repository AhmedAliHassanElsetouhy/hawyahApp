package adminTests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import adminPages.ContestsAdminPage;
import adminPages.HomePageAdminPage;
import clientPages.DefaultPage;
import clientPages.LoginPage;
import clientTests.TestBase;
import data.ExcelReader;

public class SearchFiltersContestsAdminTest extends TestBase {

	HomePageAdminPage homePageAdminPage;
	DefaultPage defaultAdminPage;
	LoginPage loginAdminPage;
	String password = "11111111";
	Actions hoverAction;
	ContestsAdminPage contestsAdminPage;
	String contestTitle = "Test";
	int designTypeItem;
	int packageTypeItem;
	int price;
	int customerItem;
	int statusItem;
	String launchedDate = "2020-01-07";
	String endedDate = "2020-01-07";
	String fromCreatedDate = "2020-01-07";
	String toCreatedDate = "2020-01-07";
	Faker fakeData = new Faker();
	String orgName = fakeData.company().name();
	String orgDesc = fakeData.company().name();
	String conDesc = fakeData.name().title();
	String addInfo = fakeData.name().name();
	String cancelationReason = fakeData.name().title();

	@Test(priority = 1, alwaysRun = true)
	public void openHomePageTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		homePageAdminPage = new HomePageAdminPage(driver);
		loginAdminPage = new LoginPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		defaultAdminPage.openLoginForm();
		Assert.assertTrue(loginAdminPage.forgetPassLink.isDisplayed());
		loginAdminPage.loginFun(ER.getExcelData(10, 2)[1][1], password);
		System.out.println(homePageAdminPage.adminSideMenuListItems.get(0).getText());
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(0).isDisplayed());
		homePageAdminPage.adminSideMenuListItems.get(1).click();
	}

	@Test(priority = 2)
	public void DesignTypeSearchTest() {
		contestsAdminPage = new ContestsAdminPage(driver);
		contestsAdminPage.designTypeFun(1);
		Assert.assertTrue(contestsAdminPage.logoAndBrand.isDisplayed());
		contestsAdminPage.clearFun();
	}

	@Test(priority = 3)
	public void PackageTypeSearchTest() {
		contestsAdminPage = new ContestsAdminPage(driver);
		contestsAdminPage.packageTypeFun(1);
		Assert.assertTrue(contestsAdminPage.packageStatus.isDisplayed());
		contestsAdminPage.clearFun();
	}

	@Test(priority = 4)
	public void StatusTypeSearchTest() {
		contestsAdminPage = new ContestsAdminPage(driver);
		contestsAdminPage.statusFun(5);
		Assert.assertTrue(contestsAdminPage.contestStatus.isDisplayed());
		contestsAdminPage.clearFun();
	}

	@Test(priority = 5)
	public void launchedDateSearchTest() {
		contestsAdminPage = new ContestsAdminPage(driver);
		contestsAdminPage.launchedDateFun(launchedDate);
		Assert.assertTrue(contestsAdminPage.recordsBody.getText().isEmpty());
		contestsAdminPage.clearFun();
	}

	@Test(priority = 6)
	public void endedDateSearchTest() {
		contestsAdminPage = new ContestsAdminPage(driver);
		contestsAdminPage.endedDateFun(endedDate);
		Assert.assertTrue(contestsAdminPage.recordsBody.getText().isEmpty());
		contestsAdminPage.clearFun();
	}

	@Test(priority = 7)
	public void fromCreatedDateSearchTest() {
		contestsAdminPage = new ContestsAdminPage(driver);
		contestsAdminPage.fromCreatedDateFun(fromCreatedDate);
		Assert.assertTrue(contestsAdminPage.recordsBody.getText().isEmpty());
		contestsAdminPage.clearFun();
	}

	@Test(priority = 8)
	public void toCreatedDateSearchTest() throws InterruptedException {
		contestsAdminPage = new ContestsAdminPage(driver);
		contestsAdminPage.toCreatedDateFun(toCreatedDate);
		Assert.assertTrue(contestsAdminPage.recordsBody.getText().isEmpty());
		Thread.sleep(2000);
		contestsAdminPage.clearFun();
	}

	@Test(priority = 9)
	public void SearchTest() throws InterruptedException {
		contestsAdminPage = new ContestsAdminPage(driver);
		contestsAdminPage.searchFun(contestTitle, designTypeItem, packageTypeItem, price, customerItem, statusItem,
				launchedDate, endedDate, fromCreatedDate, toCreatedDate);
		Assert.assertTrue(contestsAdminPage.recordsBody.getText().isEmpty());
		contestsAdminPage.clearFun();
	}

	@Test(priority = 10)
	public void makeLogoutTest() throws AWTException {
		homePageAdminPage = new HomePageAdminPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		hoverAction = new Actions(driver);
		hoverAction.moveToElement(homePageAdminPage.userAdminMenu).perform();
		homePageAdminPage.logoutAdminFun();
		Assert.assertTrue(defaultAdminPage.loginLink.isDisplayed());
	}
}