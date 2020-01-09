package designerTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import clientPages.DefaultClientPage;
import clientPages.HomeClientPage;
import clientPages.LoginClientPage;
import clientTests.TestBase;
import data.ExcelReader;
import designerPages.HomeDesignerPage;

public class LoginDesignerTest extends TestBase {
	LoginClientPage loginPage;
	DefaultClientPage defaultPage;
	HomeClientPage homePage;
	HomeDesignerPage homeUserPage;

	@Test(priority = 1, alwaysRun = true)
	public void openHomePageTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultClientPage(driver);
		loginPage = new LoginClientPage(driver);
		defaultPage.openLoginForm();
		Assert.assertTrue(loginPage.forgetPassLinkCli.isDisplayed());
	}

	@Test(priority = 2, dependsOnMethods = { "openHomePageTest" })
	public void loginFun() throws IOException {
		loginPage = new LoginClientPage(driver);
		defaultPage = new DefaultClientPage(driver);
		homePage = new HomeClientPage(driver);
		homeUserPage = new HomeDesignerPage(driver);
		ExcelReader ER = new ExcelReader();
		loginPage.loginFun(ER.getExcelData(5, 2)[1][1], ER.getExcelData(5, 2)[2][1]);
		System.out.println(homePage.loginConfirmMsgCli.getText());
		Assert.assertTrue(homePage.loginConfirmMsgCli.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomeClientPage(driver);
		defaultPage = new DefaultClientPage(driver);
		homePage.openMainMenuFun();
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginLinkCli.isDisplayed());
	}

}
