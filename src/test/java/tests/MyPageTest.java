package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.AboutMePage;
import pages.DefaultPage;
import pages.DesignsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyPagePage;

public class MyPageTest extends TestBase {

	DefaultPage defaultPage;
	LoginPage loginPage;
	HomePage homePage;
	DesignsPage designsPage;
	String email = "ahmed.ali.rooya@gmail.com";
	String password = "11111111";
	MyPagePage myPagePage;
	AboutMePage aboutMePage;

	@Test(priority = 1)
	public void openHomePageTest() {
		defaultPage = new DefaultPage(driver);
		loginPage = new LoginPage(driver);
		defaultPage.openLoginForm();
		Assert.assertTrue(loginPage.forgetPassLink.isDisplayed());
	}

	@Test(priority = 2, dependsOnMethods = { "openHomePageTest" })
	public void loginFun() {
		loginPage = new LoginPage(driver);
		defaultPage = new DefaultPage(driver);
		homePage = new HomePage(driver);
		loginPage.loginFun(email, password);
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
	public void openEditMyAccountDataTest() {
		homePage = new HomePage(driver);
		myPagePage = new MyPagePage(driver);
		aboutMePage = new AboutMePage(driver);
		myPagePage.openUpdateMyAccountPageFun();
		Assert.assertTrue(aboutMePage.personalDataLink.isDisplayed());
	}
}
