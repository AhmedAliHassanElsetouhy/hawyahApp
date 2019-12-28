package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.ExcelReader;
import pages.DefaultPage;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends TestBase {

	LoginPage loginPage;
	DefaultPage defaultPage;
	HomePage homePage;
	// String email = "ahmed.ali.rooya@gmail.com";
	// String password = "11111111";

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
		ExcelReader ER = new ExcelReader();
		loginPage.loginFun(ER.getExcelData(0, 2)[1][1], ER.getExcelData(0, 2)[2][1]);
		// loginPage.submitLoginFun();
		System.out.println(homePage.loginConfirmMsg.getText());
		Assert.assertTrue(homePage.loginConfirmMsg.getText().contains("تم تسجيل الدخول بنجاح"));
		Assert.assertTrue(homePage.wantedDesignHeaderMsg.isDisplayed());
		Assert.assertTrue(homePage.logoAndIdentity.isDisplayed());
		Assert.assertTrue(homePage.arabicFont.isDisplayed());
		Assert.assertTrue(homePage.websiteInterfaceDesign.isDisplayed());
		Assert.assertTrue(homePage.clothesAndPackaging.isDisplayed());
		Assert.assertTrue(homePage.other.isDisplayed());

		Assert.assertTrue(homePage.howWeWorkHeaderMsg.isDisplayed());
		Assert.assertTrue(homePage.explain.isDisplayed());
		Assert.assertTrue(homePage.competing.isDisplayed());
		Assert.assertTrue(homePage.choose.isDisplayed());
	}
}
