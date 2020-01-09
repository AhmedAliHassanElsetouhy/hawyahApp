package designerTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import clientPages.AboutMeClientPage;
import clientPages.DefaultClientPage;
import clientPages.DesignsClientPage;
import clientPages.HomeClientPage;
import clientPages.LoginClientPage;
import clientPages.MessageFormClientPage;
import clientPages.MessagesClientPage;
import clientPages.MyPageClientPage;
import clientTests.TestBase;
import data.ExcelReader;

public class AddMessageDesignerTest extends TestBase {

	DefaultClientPage defaultPage;
	LoginClientPage loginPage;
	HomeClientPage homePage;
	DesignsClientPage designsPage;
	MyPageClientPage myPagePage;
	AboutMeClientPage aboutMePage;
	MessagesClientPage messagePage;
	MessageFormClientPage messageFormPage;
	int index = 0;

	@Test(priority = 1)
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
		ExcelReader ER = new ExcelReader();
		loginPage.loginFun(ER.getExcelData(5, 2)[1][1], ER.getExcelData(5, 2)[2][1]);
		System.out.println(homePage.loginConfirmMsgCli.getText());
		Assert.assertTrue(homePage.loginConfirmMsgCli.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openMyPageTest() {
		homePage = new HomeClientPage(driver);
		myPagePage = new MyPageClientPage(driver);
		messagePage = new MessagesClientPage(driver);
		messageFormPage = new MessageFormClientPage(driver);
		homePage.openMainMenuFun();
		homePage.openMessagesFun();
		messagePage.openAddMessageFun();
		Assert.assertTrue(messageFormPage.peopleIconCli.isDisplayed());
	}

	@Test(priority = 4, dependsOnMethods = { "openMyPageTest" })
	public void addMessageTest() throws IOException {
		myPagePage = new MyPageClientPage(driver);
		aboutMePage = new AboutMeClientPage(driver);
		ExcelReader ER = new ExcelReader();
		messageFormPage.openAdminListFun();
		messageFormPage.selectAdmin1Fun();
		messageFormPage.sendMsgFun(ER.getExcelData(3, 2)[0][1], ER.getExcelData(3, 2)[1][1]);
		Assert.assertTrue(messageFormPage.newMsgIconCli.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "addMessageTest" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomeClientPage(driver);
		defaultPage = new DefaultClientPage(driver);
		homePage.openMainMenuFun();
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginLinkCli.isDisplayed());
	}
}