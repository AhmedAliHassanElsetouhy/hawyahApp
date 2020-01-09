package clientTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import clientPages.AboutMeClientPage;
import clientPages.CompetitionsListClientPage;
import clientPages.DefaultPage;
import clientPages.DesignsClientPage;
import clientPages.HomeClientPage;
import clientPages.LoginPage;
import clientPages.MyPageClientPage;
import clientPages.PaymentsListClientPage;
import data.ExcelReader;

public class PaymentsStatusListProfileClientTest extends TestBase {

	DefaultPage defaultClientPage;
	LoginPage loginClientPage;
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
		defaultClientPage = new DefaultPage(driver);
		loginClientPage = new LoginPage(driver);
		defaultClientPage.openLoginForm();
		Assert.assertTrue(loginClientPage.forgetPassLink.isDisplayed());
	}

	@Test(priority = 2, dependsOnMethods = { "openHomePageTest" })
	public void loginFun() throws IOException {
		loginClientPage = new LoginPage(driver);
		defaultClientPage = new DefaultPage(driver);
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
	public void openMyPaymentsListTest() throws InterruptedException {
		myPageClientPage = new MyPageClientPage(driver);
		paymentsListClientPage = new PaymentsListClientPage(driver);
		myPageClientPage.openMyPayments();

		Assert.assertTrue(paymentsListClientPage.peymentsCli.get(1).isDisplayed());
		Assert.assertTrue(paymentsListClientPage.peymentsCli.get(2).isDisplayed());
		Assert.assertTrue(paymentsListClientPage.peymentsCli.get(3).isDisplayed());
		Assert.assertTrue(paymentsListClientPage.peymentsCli.get(4).isDisplayed());
		Assert.assertTrue(paymentsListClientPage.peymentsCli.get(5).isDisplayed());
		Assert.assertTrue(paymentsListClientPage.peymentsCli.get(6).isDisplayed());
		Assert.assertTrue(paymentsListClientPage.peymentsCli.get(7).isDisplayed());
		System.out.println(paymentsListClientPage.peymentsCli.get(1).getText() + " - "
				+ paymentsListClientPage.peymentsCli.get(2).getText() + " - " + paymentsListClientPage.peymentsCli.get(3).getText()
				+ " - " + paymentsListClientPage.peymentsCli.get(4).getText() + " - "
				+ paymentsListClientPage.peymentsCli.get(5).getText() + " - " + paymentsListClientPage.peymentsCli.get(6).getText()
				+ " - " + paymentsListClientPage.peymentsCli.get(7).getText());
	}

	@Test(priority = 5, dependsOnMethods = { "openMyPaymentsListTest" })
	public void makeLogoutTest() throws AWTException {
		homeClientPage = new HomeClientPage(driver);
		defaultClientPage = new DefaultPage(driver);
		homeClientPage.openMainMenuFun();
		homeClientPage.logoutFun();
		Assert.assertTrue(defaultClientPage.loginLink.isDisplayed());
	}
}
