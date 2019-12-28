package tests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.ExcelReader;
import pages.DefaultPage;
import pages.HomePage;
import pages.HomeUserPage;
import pages.LoginPage;

public class LoginUserTest extends TestBase {
	LoginPage loginPage;
	DefaultPage defaultPage;
	HomePage homePage;
	HomeUserPage homeUserPage;

	@Test(priority = 1, alwaysRun = true)
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
		homeUserPage = new HomeUserPage(driver);
		ExcelReader ER = new ExcelReader();
		loginPage.loginFun(ER.getExcelData(5, 2)[1][1], ER.getExcelData(5, 2)[2][1]);
		System.out.println(homePage.loginConfirmMsg.getText());
		Assert.assertTrue(homePage.loginConfirmMsg.getText().contains("تم تسجيل الدخول بنجاح"));
		Assert.assertTrue(homeUserPage.aboutMeLink.isDisplayed());
		Assert.assertTrue(homeUserPage.myStatsLink.isDisplayed());
		Assert.assertTrue(homeUserPage.myBankAccountLink.isDisplayed());
		Assert.assertTrue(homeUserPage.myTransfersLink.isDisplayed());
		Assert.assertTrue(homeUserPage.myWorksLink.isDisplayed());
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomePage(driver);
		defaultPage = new DefaultPage(driver);
		homePage.openMainMenuFun();
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginLink.isDisplayed());
	}

}
