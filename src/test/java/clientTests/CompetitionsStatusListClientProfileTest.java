package clientTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import clientPages.AboutMeClientPage;
import clientPages.CompetitionsListClientPage;
import clientPages.DefaultClientPage;
import clientPages.DesignsClientPage;
import clientPages.HomeClientPage;
import clientPages.LoginClientPage;
import clientPages.MyPageClientPage;
import clientPages.PaymentsListClientPage;
import data.ExcelReader;

public class CompetitionsStatusListClientProfileTest extends TestBase {

	DefaultClientPage defaultClientPage;
	LoginClientPage loginClientPage;
	HomeClientPage homeClientPage;
	DesignsClientPage designsClientPage;
	MyPageClientPage myPageClientPage;
	AboutMeClientPage aboutMeClientPage;
	CompetitionsListClientPage competitionsListClientPage;
	PaymentsListClientPage paymentsListClientPage;

	@Test(priority = 1)
	public void openHomePageTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultClientPage = new DefaultClientPage(driver);
		loginClientPage = new LoginClientPage(driver);
		defaultClientPage.openLoginForm();
		Assert.assertTrue(loginClientPage.forgetPassLinkCli.isDisplayed());
	}

	@Test(priority = 2, dependsOnMethods = { "openHomePageTest" })
	public void loginFun() throws IOException {
		loginClientPage = new LoginClientPage(driver);
		defaultClientPage = new DefaultClientPage(driver);
		homeClientPage = new HomeClientPage(driver);
		ExcelReader ER = new ExcelReader();
		loginClientPage.loginFun(ER.getExcelData(0, 2)[1][1], ER.getExcelData(0, 2)[2][1]);
		System.out.println(homeClientPage.loginConfirmMsgCli.getText());
		Assert.assertTrue(homeClientPage.loginConfirmMsgCli.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openMyPageTest() {
		homeClientPage = new HomeClientPage(driver);
		myPageClientPage = new MyPageClientPage(driver);
		homeClientPage.openMainMenuFun();
		myPageClientPage.openMyPageFun();
		Assert.assertTrue(myPageClientPage.aboutMeLinkCli.isDisplayed());
		Assert.assertTrue(myPageClientPage.myCompetitionsLinkCli.isDisplayed());
		Assert.assertTrue(myPageClientPage.paymentsLinkCli.isDisplayed());
	}

	@Test(priority = 4, dependsOnMethods = { "openMyPageTest" })
	public void openMyCompetitionsListTest() throws InterruptedException {
		myPageClientPage = new MyPageClientPage(driver);
		competitionsListClientPage = new CompetitionsListClientPage(driver);
		myPageClientPage.openMyCompetitions();
		Assert.assertTrue(competitionsListClientPage.my_contests.get(1).isDisplayed());
		Assert.assertTrue(competitionsListClientPage.my_contests.get(2).isDisplayed());
		Assert.assertTrue(competitionsListClientPage.my_contests.get(3).isDisplayed());
		Assert.assertTrue(competitionsListClientPage.my_contests.get(4).isDisplayed());
		Assert.assertTrue(competitionsListClientPage.my_contests.get(5).isDisplayed());
		Assert.assertTrue(competitionsListClientPage.my_contests.get(6).isDisplayed());
		System.out.println(competitionsListClientPage.my_contests.get(1).getText() + " - "
				+ competitionsListClientPage.my_contests.get(2).getText() + " - "
				+ competitionsListClientPage.my_contests.get(3).getText() + " - "
				+ competitionsListClientPage.my_contests.get(4).getText() + " - "
				+ competitionsListClientPage.my_contests.get(5).getText() + " - "
				+ competitionsListClientPage.my_contests.get(6).getText());
	}

	@Test(priority = 5, dependsOnMethods = { "openMyCompetitionsListTest" })
	public void makeLogoutTest() throws AWTException {
		homeClientPage = new HomeClientPage(driver);
		defaultClientPage = new DefaultClientPage(driver);
		homeClientPage.openMainMenuFun();
		homeClientPage.logoutFun();
		Assert.assertTrue(defaultClientPage.loginLinkCli.isDisplayed());
	}

	// This take more time
	// @Test(priority = 1)
	// public void openHomePageTest() throws IOException {
	// ExcelReader ER = new ExcelReader();
	// driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
	// defaultPage = new DefaultPage(driver);
	// loginPage = new LoginPage(driver);
	// homePage = new HomePage(driver);
	// myPagePage = new MyPagePage(driver);
	// competitionsListPage = new CompetitionsListPage(driver);
	// paymentsListPage = new PaymentsListPage(driver);
	// defaultPage.openLoginForm();
	// Assert.assertTrue(loginPage.forgetPassLink.isDisplayed());
	// loginPage.loginFun(ER.getExcelData(0, 2)[1][1], ER.getExcelData(0, 2)[2][1]);
	// System.out.println(homePage.loginConfirmMsg.getText());
	// Assert.assertTrue(homePage.loginConfirmMsg.getText().contains("تم تسجيل
	// الدخول بنجاح"));
	// homePage.openMainMenuFun();
	// myPagePage.openMyPageFun();
	// Assert.assertTrue(myPagePage.aboutMeLink.isDisplayed());
	// Assert.assertTrue(myPagePage.myCompetitionsLink.isDisplayed());
	// Assert.assertTrue(myPagePage.paymentsLink.isDisplayed());
	// myPagePage.openMyCompetitions();
	// myPagePage.openMyPayments();
	// homePage.openMainMenuFun();
	// homePage.logoutFun();
	// Assert.assertTrue(defaultPage.loginLink.isDisplayed());
	//
	// }
}
