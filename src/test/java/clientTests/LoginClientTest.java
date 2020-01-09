package clientTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import clientPages.DefaultClientPage;
import clientPages.HomeClientPage;
import clientPages.LoginClientPage;
import data.ExcelReader;

public class LoginClientTest extends TestBase {

	LoginClientPage loginClientPage;
	DefaultClientPage defaultClientPage;
	HomeClientPage homeClientPage;

	@Test(priority = 1, alwaysRun = true)
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
		// loginPage.submitLoginFun();
		System.out.println(homeClientPage.loginConfirmMsgCli.getText());
		Assert.assertTrue(homeClientPage.loginConfirmMsgCli.getText().contains("تم تسجيل الدخول بنجاح"));
		Assert.assertTrue(homeClientPage.wantedDesignHeaderMsgCli.isDisplayed());
		Assert.assertTrue(homeClientPage.logoAndIdentityCli.isDisplayed());
		Assert.assertTrue(homeClientPage.arabicFontCli.isDisplayed());
		Assert.assertTrue(homeClientPage.websiteInterfaceDesignCli.isDisplayed());
		Assert.assertTrue(homeClientPage.clothesAndPackagingCli.isDisplayed());
		Assert.assertTrue(homeClientPage.otherCli.isDisplayed());

		Assert.assertTrue(homeClientPage.howWeWorkHeaderMsgCli.isDisplayed());
		Assert.assertTrue(homeClientPage.explainCli.isDisplayed());
		Assert.assertTrue(homeClientPage.competingCli.isDisplayed());
		Assert.assertTrue(homeClientPage.chooseCli.isDisplayed());
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void makeLogoutTest() throws AWTException {
		homeClientPage = new HomeClientPage(driver);
		defaultClientPage = new DefaultClientPage(driver);
		homeClientPage.openMainMenuFun();
		homeClientPage.logoutFun();
		Assert.assertTrue(defaultClientPage.loginLinkCli.isDisplayed());
	}
}
