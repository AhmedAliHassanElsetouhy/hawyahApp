package adminTests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import adminPages.ContestsAdminPage;
import adminPages.DesignersAdminPage;
import adminPages.HomePageAdminPage;
import clientPages.DefaultPage;
import clientPages.HomeClientPage;
import clientPages.LoginPage;
import clientPages.RegistrationClientPage;
import clientPages.RegistrationConfirmationClientPage;
import clientTests.TestBase;
import data.ExcelReader;
import designerPages.HomeDesignerPage;

public class DesignersAdminTest extends TestBase {

	HomePageAdminPage homePageAdminPage;
	DefaultPage defaultAdminPage;
	LoginPage loginAdminPage;
	String password = "11111111";
	ContestsAdminPage contestsAdminPage;
	DesignersAdminPage designersAdminPage;
	RegistrationClientPage registrationPage;
	HomeClientPage homeClientPage;
	RegistrationConfirmationClientPage registerConfirmPage;
	HomeDesignerPage homeDesignerPage;

	Faker fake = new Faker();
	String userName = fake.name().firstName() + fake.number().numberBetween(1, 99);
	String email = fake.internet().emailAddress();
	String usPassword = "P@55word";

	@Test(priority = 1, alwaysRun = true)
	public void EnsuringClientRegisterSubmittedtAdminTest() throws IOException, AWTException, InterruptedException {
		ExcelReader ER = new ExcelReader();
		Thread.sleep(1000);
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultAdminPage = new DefaultPage(driver);
		registrationPage = new RegistrationClientPage(driver);
		homeClientPage = new HomeClientPage(driver);
		registerConfirmPage = new RegistrationConfirmationClientPage(driver);
		defaultAdminPage.openRegisterFormFun();
		Assert.assertTrue(registrationPage.registerFormHeaderTxtCli.getText().contains("إنشاء حساب في هوية"));
		registrationPage.registerFun(email, usPassword, userName);
		registrationPage.checkTermsAndCondition();
		registrationPage.designerRegister();
		registrationPage.submitRegisterfun();
		System.out.println(registerConfirmPage.registerDesignerConfirmMsgCli.getText());
		Assert.assertTrue(registerConfirmPage.registerDesignerConfirmMsgCli.getText().contains("كمصمم"));

		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		homePageAdminPage = new HomePageAdminPage(driver);
		loginAdminPage = new LoginPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		designersAdminPage = new DesignersAdminPage(driver);
		defaultAdminPage.openLoginForm();
		Assert.assertTrue(loginAdminPage.forgetPassLink.isDisplayed());
		loginAdminPage.loginFun(ER.getExcelData(10, 2)[1][1], password);
		System.out.println(homePageAdminPage.adminSideMenuListItems.get(0).getText());
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(0).isDisplayed());
		homePageAdminPage.adminSideMenuListItems.get(2).click();
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		System.out.println(today.format(date));
		String todayDate = today.format(date);
		designersAdminPage.fromToDateFunDes(todayDate);
		designersAdminPage.swapConfirmCheckBoxFunDes();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", designersAdminPage.searchBtnDes);
		Assert.assertTrue(designersAdminPage.pendingStatusDes.size() != 0);
		Actions hoverAction = new Actions(driver);
		hoverAction.moveToElement(homePageAdminPage.adminMenu.get(1)).perform();
		Thread.sleep(1000);
		homePageAdminPage.logoutAdminFun();
	}

	@Test(priority = 2)
	public void openHomePageTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		// driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		homePageAdminPage = new HomePageAdminPage(driver);
		loginAdminPage = new LoginPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		defaultAdminPage.openLoginForm();
		Assert.assertTrue(loginAdminPage.forgetPassLink.isDisplayed());
		loginAdminPage.loginFun(ER.getExcelData(10, 2)[1][1], password);
		System.out.println(homePageAdminPage.adminSideMenuListItems.get(0).getText());
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(0).isDisplayed());
		homePageAdminPage.adminSideMenuListItems.get(3).click();
	}

	@Test(priority = 3)
	public void Activate_Deactivate_Test() throws InterruptedException, AWTException {
		designersAdminPage = new DesignersAdminPage(driver);
		int numOfActiveAcc = designersAdminPage.activateAccountBtnDes.size();
		System.out.println(" Number of Activated accounts are: " + numOfActiveAcc);
		int numOfDeactivatedAcc = designersAdminPage.deactivatedAccountBtnDes.size();
		System.out.println(" Number of notActivated accounts are: " + numOfDeactivatedAcc);
		if (numOfActiveAcc > 0) {
			designersAdminPage.activateAccountFunDes(0);
		} else {
			designersAdminPage.deActivateAccountFunDes(0);
		}
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F5);
		robot.keyRelease(KeyEvent.VK_F5);

		int numOfDeactiveAcc1 = designersAdminPage.activateAccountBtnDes.size();
		System.out.println(" Number of notActivated accounts are: " + numOfDeactiveAcc1);
		if (numOfDeactiveAcc1 > 0) {
			designersAdminPage.deActivateAccountFunDes(0);
		} else {
			designersAdminPage.activateAccountFunDes(0);
		}
		int numOfActivatedAcc1 = designersAdminPage.deactivatedAccountBtnDes.size();
		System.out.println(" Number of notActivated accounts are: " + numOfActivatedAcc1);
		Assert.assertEquals(designersAdminPage.activeStatusDes.size(),
				designersAdminPage.deactivatedAccountBtnDes.size());
	}

	@Test(priority = 4)
	public void searchTest() throws InterruptedException {
		designersAdminPage = new DesignersAdminPage(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", designersAdminPage.searchBtnDes);
		Assert.assertTrue(
				designersAdminPage.pendingStatusDes.size() != 0 || designersAdminPage.activeStatusDes.size() != 0);
	}

	@Test(priority = 5)
	public void searchActivatedTest() throws InterruptedException {
		designersAdminPage = new DesignersAdminPage(driver);
		designersAdminPage.accountStatusFunDes(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", designersAdminPage.searchBtnDes);
		Assert.assertEquals(designersAdminPage.pendingStatusDes.size(), 0);
		designersAdminPage.clearFunDes();
	}

	@Test(priority = 6)
	public void searchPendingTest() throws InterruptedException {
		designersAdminPage = new DesignersAdminPage(driver);
		designersAdminPage.accountStatusFunDes(2);
		designersAdminPage.swapConfirmCheckBoxFunDes();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", designersAdminPage.searchBtnDes);
		Assert.assertEquals(designersAdminPage.activeStatusDes.size(), 0);
		designersAdminPage.clearFunDes();
	}

	@Test(priority = 7)
	public void searchDatesTest() throws InterruptedException, AWTException {
		designersAdminPage = new DesignersAdminPage(driver);
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		System.out.println(today.format(date));
		String todayDate = today.format(date);
		designersAdminPage.fromToDateFunDes(todayDate);
		designersAdminPage.swapConfirmCheckBoxFunDes();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", designersAdminPage.searchBtnDes);
		Assert.assertTrue(designersAdminPage.pendingStatusDes.size() != 0);
		designersAdminPage.clearFunDes();
		Actions hoverAction = new Actions(driver);
		hoverAction.moveToElement(homePageAdminPage.adminMenu.get(1)).perform();
		homePageAdminPage.logoutAdminFun();
	}
}
