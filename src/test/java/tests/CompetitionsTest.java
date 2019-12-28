package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CompetitionsPage;
import pages.DefaultPage;
import pages.DesignsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RequestDesignAndSearchPage;

public class CompetitionsTest extends TestBase {

	DefaultPage defaultPage;
	LoginPage loginPage;
	HomePage homePage;
	RequestDesignAndSearchPage requestDesignPage;
	DesignsPage designsPage;
	String email = "ahmed.ali.rooya@gmail.com";
	String password = "11111111";
	CompetitionsPage competitionsPage;

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
		// loginPage.submitLoginFun();
		System.out.println(homePage.loginConfirmMsg.getText());
		Assert.assertTrue(homePage.loginConfirmMsg.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openCompetitionsTest() {
		homePage = new HomePage(driver);
		competitionsPage = new CompetitionsPage(driver);
		homePage.openCompetitionFun();
		System.out.println(competitionsPage.competitions.getText());
	}
}
