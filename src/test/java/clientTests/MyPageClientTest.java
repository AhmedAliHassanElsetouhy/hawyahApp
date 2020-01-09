package clientTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import clientPages.AboutMeClientPage;
import clientPages.DefaultClientPage;
import clientPages.DesignsClientPage;
import clientPages.HomeClientPage;
import clientPages.LoginClientPage;
import clientPages.MyPageClientPage;
import data.ExcelReader;

public class MyPageClientTest extends TestBase {

	DefaultClientPage defaultClientPage;
	LoginClientPage loginClientPage;
	HomeClientPage homeClientPage;
	DesignsClientPage designsClientPage;
	MyPageClientPage myPageClientPage;
	AboutMeClientPage aboutMeClientPage;

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
	public void openEditMyAccountDataTest() {
		homeClientPage = new HomeClientPage(driver);
		myPageClientPage = new MyPageClientPage(driver);
		aboutMeClientPage = new AboutMeClientPage(driver);
		myPageClientPage.openUpdateMyAccountPageFun();
		Assert.assertTrue(aboutMeClientPage.personalDataLinkCli.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "openEditMyAccountDataTest" })
	public void makeLogoutTest() throws AWTException {
		homeClientPage = new HomeClientPage(driver);
		defaultClientPage = new DefaultClientPage(driver);
		homeClientPage.openMainMenuFun();
		homeClientPage.logoutFun();
		Assert.assertTrue(defaultClientPage.loginLinkCli.isDisplayed());
	}
}
