package tests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.ExcelReader;
import pages.AboutMePage;
import pages.CompetitionsListPage;
import pages.DefaultPage;
import pages.DesignsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyPagePage;
import pages.PaymentsListPage;

public class CompetitionsListTest extends TestBase {

	DefaultPage defaultPage;
	LoginPage loginPage;
	HomePage homePage;
	DesignsPage designsPage;
	// String email = "ahmed.ali.rooya@gmail.com";
	// String password = "11111111";
	MyPagePage myPagePage;
	AboutMePage aboutMePage;
	CompetitionsListPage competitionsListPage;
	PaymentsListPage paymentsListPage;

	@Test(priority = 1)
	public void openHomePageTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultPage(driver);
		loginPage = new LoginPage(driver);
		defaultPage.openLoginForm();
		Assert.assertTrue(loginPage.forgetPassLink.isDisplayed());
	}

	@Test(priority = 2, dependsOnMethods = { "openHomePageTest" })
	public void loginFun() throws IOException {
		loginPage = new LoginPage(driver);
		defaultPage = new DefaultPage(driver);
		homePage = new HomePage(driver);
		ExcelReader ER = new ExcelReader();
		loginPage.loginFun(ER.getExcelData(0, 2)[1][1], ER.getExcelData(0, 2)[2][1]);
		System.out.println(homePage.loginConfirmMsg.getText());
		Assert.assertTrue(homePage.loginConfirmMsg.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openMyPageTest() {
		homePage = new HomePage(driver);
		myPagePage = new MyPagePage(driver);
		homePage.openMainMenuFun();
		myPagePage.openMyPageFun();
		Assert.assertTrue(myPagePage.aboutMeLink.isDisplayed());
		Assert.assertTrue(myPagePage.myCompetitionsLink.isDisplayed());
		Assert.assertTrue(myPagePage.paymentsLink.isDisplayed());
	}

	@Test(priority = 4, dependsOnMethods = { "openMyPageTest" })
	public void openMyCompetitionsListTest() throws InterruptedException {
		myPagePage = new MyPagePage(driver);
		competitionsListPage = new CompetitionsListPage(driver);
		myPagePage.openMyCompetitions();
		// Thread.sleep(5000);
		// Assert.assertTrue(competitionsListPage.requestTitleCol.isDisplayed());
		// Assert.assertTrue(competitionsListPage.designTypeCol.isDisplayed());
		// Assert.assertTrue(competitionsListPage.competitionCatCol.isDisplayed());
		// Assert.assertTrue(competitionsListPage.totalAmountCol.isDisplayed());
		// Assert.assertTrue(competitionsListPage.startDateCol.isDisplayed());
		// Assert.assertTrue(competitionsListPage.statusCol.isDisplayed());
		// Assert.assertTrue(competitionsListPage.requestTitleCol.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "openMyCompetitionsListTest" })
	public void openMyPaymentsListTest() throws InterruptedException {
		myPagePage = new MyPagePage(driver);
		paymentsListPage = new PaymentsListPage(driver);
		myPagePage.openMyPayments();
		// Assert.assertTrue(paymentsListPage.competitionCol.isDisplayed());
		// Assert.assertTrue(paymentsListPage.paymentMethodCol.isDisplayed());
		// Assert.assertTrue(paymentsListPage.amountCol.isDisplayed());
		// Assert.assertTrue(paymentsListPage.paymentDateCol.isDisplayed());
		// Assert.assertTrue(paymentsListPage.operationNumberCol.isDisplayed());
		// Assert.assertTrue(paymentsListPage.bankNameCol.isDisplayed());
		// Assert.assertTrue(paymentsListPage.sadadPayfortCol.isDisplayed());
		// Assert.assertTrue(paymentsListPage.competitionCol.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "openMyPaymentsListTest" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomePage(driver);
		defaultPage = new DefaultPage(driver);
		homePage.openMainMenuFun();
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginLink.isDisplayed());
	}
}
