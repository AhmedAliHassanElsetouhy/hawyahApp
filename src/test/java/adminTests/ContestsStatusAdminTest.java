package adminTests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
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

public class ContestsStatusAdminTest extends TestBase {

	HomePageAdminPage homePageAdminPage;
	DefaultPage defaultAdminPage;
	LoginPage loginAdminPage;
	String password = "11111111";
	Actions hoverAction;
	ContestsAdminPage contestsAdminPage;

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
	String contestTitle = fakeData.name().title();
	String orgName = fakeData.company().name();
	String orgDesc = fakeData.company().industry();
	String conDesc = fakeData.name().title();
	String addInfo = fakeData.name().name();
	String cancellationReason = fakeData.nation().capitalCity();

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
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(0).isDisplayed());
	}

	@Test(priority = 3)
	public void cancelContestTest() throws InterruptedException {
		contestsAdminPage = new ContestsAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		homePageAdminPage.adminSideMenuListItems.get(1).click();
		contestsAdminPage.statusFun(11);
		Thread.sleep(1000);
		contestsAdminPage.CancelledByAdminStatus.get(0).click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", contestsAdminPage.designsTab);
		Assert.assertTrue(contestsAdminPage.paymentStatus.isDisplayed());
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", contestsAdminPage.cancelBtn);
		Thread.sleep(1000);
	}

	@Test(priority = 4)
	public void waitingForDepositTest() throws InterruptedException {
		contestsAdminPage = new ContestsAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		homePageAdminPage.adminSideMenuListItems.get(1).click();
		contestsAdminPage.statusFun(3);
		Thread.sleep(1000);
		contestsAdminPage.waitingDepositStatus.get(0).click();
		contestsAdminPage.contestDetailsFormFun(contestTitle, orgName, orgDesc, conDesc, addInfo);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", contestsAdminPage.designsTab);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", contestsAdminPage.cancelBtn);
		Thread.sleep(1000);
	}

	@Test(priority = 5)
	public void deliverFinalWorkTest() throws InterruptedException {
		contestsAdminPage = new ContestsAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		homePageAdminPage.adminSideMenuListItems.get(1).click();
		contestsAdminPage.statusFun(8);
		Thread.sleep(1000);
		contestsAdminPage.deliverFinalWorkStatus.get(0).click();
		contestsAdminPage.contestDetailsFormFun(contestTitle, orgName, orgDesc, conDesc, addInfo);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", contestsAdminPage.designsTab);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", contestsAdminPage.cancelBtn);
		Thread.sleep(1000);
	}

	@Test(priority = 6)
	public void completedStatusTest() throws InterruptedException {
		contestsAdminPage = new ContestsAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		homePageAdminPage.adminSideMenuListItems.get(1).click();
		contestsAdminPage.statusFun(9);
		Thread.sleep(1000);
		contestsAdminPage.completedStatus.get(8).click();
		contestsAdminPage.contestDetailsFormFun(contestTitle, orgName, orgDesc, conDesc, addInfo);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", contestsAdminPage.paymentsTab);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", contestsAdminPage.designsTab);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", contestsAdminPage.cancelBtn);
		Thread.sleep(1000);
	}

	@Test(priority = 7)
	public void Phase1StatusTest() throws InterruptedException {
		contestsAdminPage = new ContestsAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		homePageAdminPage.adminSideMenuListItems.get(1).click();
		contestsAdminPage.statusFun(5);
		Thread.sleep(1000);
		contestsAdminPage.phase1Status.get(0).click();
		System.out.println(contestTitle + " " + orgName + " " + orgDesc + " " + conDesc + " " + addInfo);
		contestsAdminPage.contestDetailsFormFun(contestTitle, orgName, orgDesc, conDesc, addInfo);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", contestsAdminPage.submitBtn);
		Assert.assertTrue(contestsAdminPage.openContestFormSuccessMsg.isDisplayed());
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", contestsAdminPage.paymentsTab);
		Assert.assertTrue(contestsAdminPage.paymentStatus.isDisplayed());
		contestsAdminPage.openCancelContestFun();
		contestsAdminPage.cancelContestFun(cancellationReason);
		contestsAdminPage.cancelFun();
		Thread.sleep(1000);
	}

	@Test(priority = 8, dependsOnMethods = { "Phase1StatusTest", "cancelContestTest", "waitingForDepositTest",
			"deliverFinalWorkTest", "completedStatusTest" })
	public void makeLogoutTest() throws AWTException {
		homePageAdminPage = new HomePageAdminPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		hoverAction = new Actions(driver);
		hoverAction.moveToElement(homePageAdminPage.userAdminMenu).perform();
		homePageAdminPage.logoutAdminFun();
		Assert.assertTrue(defaultAdminPage.loginLink.isDisplayed());
	}
}