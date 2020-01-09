package clientTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import clientPages.AboutMeClientPage;
import clientPages.DefaultPage;
import clientPages.DesignsClientPage;
import clientPages.HomeClientPage;
import clientPages.LoginPage;
import clientPages.MessageFormClientPage;
import clientPages.MessagesClientPage;
import clientPages.MyPageClientPage;
import clientTests.TestBase;
import data.ExcelReader;

public class RestoreMsgsClientTest extends TestBase {

	DefaultPage defaultClientPage;
	LoginPage loginClientPage;
	HomeClientPage homeClientPage;
	DesignsClientPage designsClientPage;
	MyPageClientPage myPageClientPage;
	AboutMeClientPage aboutMeClientPage;
	MessagesClientPage messageClientPage;
	MessageFormClientPage messageFormClientPage;
	int index = 0;

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
		loginClientPage.loginFun(ER.getExcelData(5, 2)[1][1], ER.getExcelData(5, 2)[2][1]);
		System.out.println(homeClientPage.loginConfirmMsgCli.getText());
		Assert.assertTrue(homeClientPage.loginConfirmMsgCli.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openMyPageTest() {
		homeClientPage = new HomeClientPage(driver);
		myPageClientPage = new MyPageClientPage(driver);
		messageClientPage = new MessagesClientPage(driver);
		messageFormClientPage = new MessageFormClientPage(driver);
		homeClientPage.openMainMenuFun();
		homeClientPage.openMessagesFun();
		messageClientPage.openAddMessageFun();
		Assert.assertTrue(messageFormClientPage.peopleIconCli.isDisplayed());
	}

	@Test(priority = 4, dependsOnMethods = { "openMyPageTest" })
	public void addMessageTest() throws IOException {
		myPageClientPage = new MyPageClientPage(driver);
		aboutMeClientPage = new AboutMeClientPage(driver);
		ExcelReader ER = new ExcelReader();
		messageFormClientPage.openAdminListFun();
		messageFormClientPage.selectAdmin1Fun();
		messageFormClientPage.sendMsgFun(ER.getExcelData(3, 2)[0][1], ER.getExcelData(3, 2)[1][1]);
		Assert.assertTrue(messageFormClientPage.newMsgIconCli.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "addMessageTest" })
	public void deleteMsgTest() {
		messageFormClientPage = new MessageFormClientPage(driver);
		messageFormClientPage.openMessages();
		messageFormClientPage.openSentMessages();
		messageFormClientPage.deleteMsgFun(index);
		driver.switchTo().alert().accept();
		Assert.assertTrue(messageFormClientPage.confirmDeleteMsgCli.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "deleteMsgTest" })
	public void openDeletedMsgsTest() {
		messageFormClientPage = new MessageFormClientPage(driver);
		// messageFormPage.openSentMsgs();
		// messageFormPage.deleteMsgFun(index);
		// driver.switchTo().alert().accept();
		messageFormClientPage.openDeletedMsgs();
		messageFormClientPage.restoreDeleteMsgFun(index);
		Assert.assertTrue(messageFormClientPage.confirmRestoreMsgCli.isDisplayed());
	}

	@Test(priority = 7, dependsOnMethods = { "openDeletedMsgsTest" })
	public void makeLogoutTest() throws AWTException {
		homeClientPage = new HomeClientPage(driver);
		defaultClientPage = new DefaultPage(driver);
		homeClientPage.openMainMenuFun();
		homeClientPage.logoutFun();
		Assert.assertTrue(defaultClientPage.loginLink.isDisplayed());
	}
}
