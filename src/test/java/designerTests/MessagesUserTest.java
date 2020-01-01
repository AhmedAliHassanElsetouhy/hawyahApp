package designerTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import clientPages.AboutMePage;
import clientPages.DefaultPage;
import clientPages.DesignsPage;
import clientPages.HomePage;
import clientPages.LoginPage;
import clientPages.MessageFormPage;
import clientPages.MessagesPage;
import clientPages.MyPagePage;
import clientTests.TestBase;
import data.ExcelReader;

public class MessagesUserTest extends TestBase {

	DefaultPage defaultPage;
	LoginPage loginPage;
	HomePage homePage;
	DesignsPage designsPage;
	MyPagePage myPagePage;
	AboutMePage aboutMePage;
	MessagesPage messagePage;
	MessageFormPage messageFormPage;
	int index = 0;

	@Test(priority = 1)
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
		loginPage.loginFun(ER.getExcelData(5, 2)[1][1], ER.getExcelData(5, 2)[2][1]);
		System.out.println(homePage.loginConfirmMsg.getText());
		Assert.assertTrue(homePage.loginConfirmMsg.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openMyPageTest() {
		homePage = new HomePage(driver);
		myPagePage = new MyPagePage(driver);
		messagePage = new MessagesPage(driver);
		messageFormPage = new MessageFormPage(driver);
		homePage.openMainMenuFun();
		homePage.openMessagesFun();
		messagePage.openAddMessageFun();
		Assert.assertTrue(messageFormPage.peopleIcon.isDisplayed());
	}

	@Test(priority = 4, dependsOnMethods = { "openMyPageTest" })
	public void addMessageTest() throws IOException {
		myPagePage = new MyPagePage(driver);
		aboutMePage = new AboutMePage(driver);
		ExcelReader ER = new ExcelReader();
		messageFormPage.openAdminListFun();
		messageFormPage.selectAdmin1Fun();
		messageFormPage.sendMsgFun(ER.getExcelData(3, 2)[0][1], ER.getExcelData(3, 2)[1][1]);
		Assert.assertTrue(messageFormPage.newMsgIcon.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "addMessageTest" })
	public void deleteMsgTest() {
		messageFormPage = new MessageFormPage(driver);
		messageFormPage.openMessages();
		messageFormPage.openSentMessages();
		messageFormPage.deleteMsgFun(index);
		driver.switchTo().alert().accept();
		Assert.assertTrue(messageFormPage.confirmDeleteMsg.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "deleteMsgTest" })
	public void openDeletedMsgsTest() {
		messageFormPage = new MessageFormPage(driver);
		driver.navigate().to("https://hawyah-dev.herokuapp.com/conversations?box=sent&locale=ar");
		messageFormPage.deleteMsgFun(index);
		driver.switchTo().alert().accept();
		driver.navigate().to("https://hawyah-dev.herokuapp.com/conversations?box=trash&locale=ar");
		messageFormPage.restoreDeleteMsgFun(index);
		Assert.assertTrue(messageFormPage.confirmRestoreMsg.isDisplayed());
	}

	@Test(priority = 7, dependsOnMethods = { "openDeletedMsgsTest" })
	public void deletedAllMsgsTest() {
		messageFormPage = new MessageFormPage(driver);
		driver.navigate().to("https://hawyah-dev.herokuapp.com/conversations?box=trash&locale=ar");
		messageFormPage.deleteAllMsgsFun();
		driver.switchTo().alert().accept();
		Assert.assertTrue(messageFormPage.confirmRestoreMsg.isDisplayed());
	}

	@Test(priority = 8, dependsOnMethods = { "deletedAllMsgsTest" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomePage(driver);
		defaultPage = new DefaultPage(driver);
		homePage.openMainMenuFun();
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginLink.isDisplayed());
	}
}
