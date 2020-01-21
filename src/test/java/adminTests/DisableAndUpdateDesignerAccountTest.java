package adminTests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import adminPages.ContestsAdminPage;
import adminPages.DesignerProfileAdminPage;
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

public class DisableAndUpdateDesignerAccountTest extends TestBase {

	HomePageAdminPage homePageAdminPage;
	DefaultPage defaultAdminPage;
	LoginPage loginAdminPage;
	String password = "11111111";
	ContestsAdminPage contestsAdminPage;
	RegistrationClientPage registrationPage;
	HomeClientPage homeClientPage;
	RegistrationConfirmationClientPage registerConfirmPage;
	HomeDesignerPage homeDesignerPage;
	DesignerProfileAdminPage designerProfileAdminPage;
	DesignersAdminPage designersAdminPage;

	Faker fake = new Faker();
	String userName = fake.name().firstName() + fake.number().numberBetween(1, 99);
	String email = fake.internet().emailAddress();
	// String password = fake.number().digits(8);
	String usPassword = "P@55word";

	String fNameDes = fake.name().firstName();
	String lNameDes = fake.name().lastName();
	String bioDes = fake.name().title();
	int countryIndexDes = 3;
	String regionDes = fake.address().streetName();
	String cityDes = fake.address().cityName();
	String addDes = fake.address().fullAddress();
	int statusIndexDes = 1;

	@Test(priority = 1, alwaysRun = true)
	public void openHomePageTest() throws IOException, InterruptedException {
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
		homePageAdminPage.adminSideMenuListItems.get(3).click();
		// Thread.sleep(500);
	}

	@Test(priority = 2)
	public void DisableTest() throws InterruptedException, AWTException {
		designersAdminPage = new DesignersAdminPage(driver);
		designerProfileAdminPage = new DesignerProfileAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		int numOfPendingAcc = designersAdminPage.activateAccountBtnDes.size();
		System.out.println(" Number of Activated accounts are: " + numOfPendingAcc);
		int numOfActivateAcc = designersAdminPage.deactivatedAccountBtnDes.size();
		System.out.println(" Number of notActivated accounts are: " + numOfActivateAcc);
		if (numOfPendingAcc > 1) {
			designersAdminPage.activateAccountFunDes(0);
		} else {
			designersAdminPage.deActivateAccountFunDes(0);
		}
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F5);
		robot.keyRelease(KeyEvent.VK_F5);

		int numOfPendingAcc1 = designersAdminPage.activateAccountBtnDes.size();
		System.out.println(" Number of notActivated accounts are: " + numOfPendingAcc1);
		if (numOfPendingAcc1 > 0) {
			designersAdminPage.deActivateAccountFunDes(0);
		} else {
			designersAdminPage.activateAccountFunDes(0);
		}
		int numOfActivateAcc1 = designersAdminPage.deactivatedAccountBtnDes.size();
		System.out.println(" Number of Activated accounts are: " + numOfActivateAcc1);
		Assert.assertEquals(designersAdminPage.activeStatusDes.size(),
				designersAdminPage.deactivatedAccountBtnDes.size());
		designersAdminPage.openActiveStatusFunDes(0);
		// Thread.sleep(500);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("designer_account_status")));
		// WebDriverWait wait = new WebDriverWait(driver, 15);
		// wait.until(ExpectedConditions.presenceOfElementLocated((By)
		// designerProfileAdminPage.statusListDes));
		designerProfileAdminPage.designerProfileStatus();
	}

	@Test(priority = 3, dependsOnMethods = { "DisableTest" })
	public void UpdateProfileDataTest() throws InterruptedException, AWTException {
		designersAdminPage = new DesignersAdminPage(driver);
		designerProfileAdminPage = new DesignerProfileAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		homePageAdminPage.adminSideMenuListItems.get(3).click();
		designersAdminPage.searchStatus(1);
		designersAdminPage.searchFunDes();
		if (designersAdminPage.deactivatedAccountBtnDes.get(0).isDisplayed()) {
			designersAdminPage.deActivateAccountFunDes(0);
			int activated = designersAdminPage.activeStatusDes.size();
			System.out.println(" Number of Activated accounts are: " + activated);
			Thread.sleep(1000);
			designersAdminPage.openActiveStatusFunDes(0);
			designerProfileAdminPage.updateProfileFunDes(fNameDes, lNameDes, bioDes, countryIndexDes, regionDes,
					cityDes, addDes, statusIndexDes);
			designerProfileAdminPage.openAccountInfoTabFun();
			designerProfileAdminPage.openContestsInfoTabFun();
			designerProfileAdminPage.openTransfersInfoTabFun();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", designerProfileAdminPage.submitBtnDes);
			Assert.assertTrue(designerProfileAdminPage.updateAlertSuccessMsgDes.isDisplayed());
		} else {
			designersAdminPage.activateAccountFunDes(0);
			int activated = designersAdminPage.activeStatusDes.size();
			System.out.println(" Number of Activated accounts are: " + activated);
			Thread.sleep(1000);
			designersAdminPage.openActiveStatusFunDes(0);
			designerProfileAdminPage.updateProfileFunDes(fNameDes, lNameDes, bioDes, countryIndexDes, regionDes,
					cityDes, addDes, statusIndexDes);
			designerProfileAdminPage.openAccountInfoTabFun();
			designerProfileAdminPage.openContestsInfoTabFun();
			designerProfileAdminPage.openTransfersInfoTabFun();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", designerProfileAdminPage.submitBtnDes);
			Assert.assertTrue(designerProfileAdminPage.updateAlertSuccessMsgDes.isDisplayed());
		}
		Thread.sleep(1000);
		Actions hoverAction;
		defaultAdminPage = new DefaultPage(driver);
		hoverAction = new Actions(driver);
		hoverAction.moveToElement(homePageAdminPage.userAdminMenu).perform();
		homePageAdminPage.logoutAdminFun();
		Assert.assertTrue(defaultAdminPage.loginLink.isDisplayed());
	}
}