package adminTests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import adminPages.ContestsAdminPage;
import adminPages.HomePageAdminPage;
import adminPages.UsersAdminPage;
import clientPages.DefaultPage;
import clientPages.HomeClientPage;
import clientPages.LoginPage;
import clientPages.RegistrationClientPage;
import clientPages.RegistrationConfirmationClientPage;
import clientTests.TestBase;
import data.ExcelReader;
import designerPages.HomeDesignerPage;

public class UsersAdminTest extends TestBase {

	HomePageAdminPage homePageAdminPage;
	DefaultPage defaultAdminPage;
	LoginPage loginAdminPage;
	String password = "11111111";
	ContestsAdminPage contestsAdminPage;
	UsersAdminPage usersAdminPage;
	RegistrationClientPage registrationPage;
	DefaultPage defaultPage;
	HomeClientPage homeClientPage;
	RegistrationConfirmationClientPage registerConfirmPage;
	HomeDesignerPage homeDesignerPage;

	Faker fake = new Faker();
	String userName = fake.name().firstName() + fake.number().numberBetween(1, 99);
	String email = fake.internet().emailAddress();
	// String password = fake.number().digits(8);
	String usPassword = "P@55word";

	@Test(priority = 1, alwaysRun = true)
	public void openHomePageTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		homePageAdminPage = new HomePageAdminPage(driver);
		loginAdminPage = new LoginPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		defaultAdminPage.openLoginForm();
		Assert.assertTrue(loginAdminPage.forgetPassLink.isDisplayed());
		loginAdminPage.loginFun(ER.getExcelData(10, 2)[1][1], password);
		System.out.println(homePageAdminPage.adminSideMenuListItems.get(0).getText());
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(0).isDisplayed());
		homePageAdminPage.adminSideMenuListItems.get(2).click();
	}

	@Test(priority = 2)
	public void Activate_Deactivate_Test() throws InterruptedException, AWTException {
		usersAdminPage = new UsersAdminPage(driver);
		int activated = usersAdminPage.activatedAccount.size();
		System.out.println(" Number of Activated accounts are: " + activated);
		int notActivated = usersAdminPage.deactivatedAccount.size();
		System.out.println(" Number of notActivated accounts are: " + notActivated);
		if (notActivated > 0) {
			usersAdminPage.activateAccountFun(0);
		} else {
			usersAdminPage.deActivateAccountFun(0);
		}
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F5);
		robot.keyRelease(KeyEvent.VK_F5);

		int activated1 = usersAdminPage.activatedAccount.size();
		System.out.println(" Number of notActivated accounts are: " + activated1);
		if (activated1 > 0) {
			usersAdminPage.deActivateAccountFun(0);
		} else {
			usersAdminPage.activateAccountFun(0);
		}
		int notActivated1 = usersAdminPage.deactivatedAccount.size();
		System.out.println(" Number of notActivated accounts are: " + notActivated1);
		Assert.assertEquals(usersAdminPage.pendingStatus.size(), usersAdminPage.activatedAccount.size());
		Assert.assertEquals(usersAdminPage.activeStatus.size(), usersAdminPage.deactivatedAccount.size());
	}

	@Test(priority = 3)
	public void searchTest() throws InterruptedException {
		usersAdminPage = new UsersAdminPage(driver);
		usersAdminPage.searchFun();
		Assert.assertEquals(usersAdminPage.pendingStatus.size(), 0);
	}

	@Test(priority = 4)
	public void searchActivatedTest() throws InterruptedException {
		usersAdminPage = new UsersAdminPage(driver);
		usersAdminPage.accountStatusFun(1);
		usersAdminPage.searchFun();
		Assert.assertEquals(usersAdminPage.pendingStatus.size(), 0);
		usersAdminPage.clearFun();
	}

	@Test(priority = 5)
	public void searchPendingTest() throws InterruptedException {
		usersAdminPage = new UsersAdminPage(driver);
		usersAdminPage.accountStatusFun(2);
		usersAdminPage.swapConfirmCheckBoxFun();
		usersAdminPage.searchFun();
		Assert.assertEquals(usersAdminPage.activeStatus.size(), 0);
		usersAdminPage.clearFun();
		Thread.sleep(1000);
	}

	@Test(priority = 6)
	public void searchDatesTest() throws InterruptedException, AWTException {
		usersAdminPage = new UsersAdminPage(driver);
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		System.out.println(today.format(date));
		String todayDate = today.format(date);
		usersAdminPage.fromToDateFun(todayDate);
		usersAdminPage.swapConfirmCheckBoxFun();
		usersAdminPage.searchFun();
		Assert.assertTrue(usersAdminPage.pendingStatus.size() != 0);
		usersAdminPage.clearFun();
		Actions hoverAction = new Actions(driver);
		hoverAction.moveToElement(homePageAdminPage.adminMenu.get(1)).perform();
		homePageAdminPage.logoutAdminFun();
	}

	@Test(priority = 7)
	public void EnsuringClientRegisterSubmittedtAdminTest() throws IOException, AWTException, InterruptedException {
		ExcelReader ER = new ExcelReader();
		Thread.sleep(1000);
		// driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultPage(driver);
		registrationPage = new RegistrationClientPage(driver);
		homeClientPage = new HomeClientPage(driver);
		registerConfirmPage = new RegistrationConfirmationClientPage(driver);
		defaultPage.openRegisterFormFun();
		Assert.assertTrue(registrationPage.registerFormHeaderTxtCli.getText().contains("إنشاء حساب في هوية"));
		registrationPage.registerFun(email, usPassword, userName);
		registrationPage.checkTermsAndCondition();
		registrationPage.clientRegister();
		registrationPage.submitRegisterfun();
		System.out.println(registerConfirmPage.registerClientConfirmMsgCli.getText());
		Assert.assertTrue(registerConfirmPage.registerClientConfirmMsgCli.getText().contains("شكرا"));

		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		homePageAdminPage = new HomePageAdminPage(driver);
		loginAdminPage = new LoginPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		usersAdminPage = new UsersAdminPage(driver);
		defaultAdminPage.openLoginForm();
		Assert.assertTrue(loginAdminPage.forgetPassLink.isDisplayed());
		loginAdminPage.loginFun(ER.getExcelData(10, 2)[1][1], password);
		System.out.println(homePageAdminPage.adminSideMenuListItems.get(0).getText());
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(0).isDisplayed());
		homePageAdminPage.adminSideMenuListItems.get(2).click();
		// String todayDate = "20200116";
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		System.out.println(today.format(date));
		String todayDate = today.format(date);
		usersAdminPage.fromToDateFun(todayDate);
		usersAdminPage.swapConfirmCheckBoxFun();
		usersAdminPage.searchFun();
		Assert.assertTrue(usersAdminPage.pendingStatus.size() != 0);
		Actions hoverAction = new Actions(driver);
		hoverAction.moveToElement(homePageAdminPage.adminMenu.get(1)).perform();
		homePageAdminPage.logoutAdminFun();
	}
}