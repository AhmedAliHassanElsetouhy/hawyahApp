package adminTests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
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
		// homePageAdminPage.adminSideMenuListItems.get(3).click();
		// Thread.sleep(500);
	}

	@Test(priority = 2)
	public void DisableTest() throws InterruptedException, AWTException {
		designersAdminPage = new DesignersAdminPage(driver);
		designerProfileAdminPage = new DesignerProfileAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		int activateAccountBtnNum = designersAdminPage.activateAccountBtnDes.size();
		System.out.println(" Number of Activated accounts are: " + activateAccountBtnNum);
		int deactivatedAccountBtnNum = designersAdminPage.deactivatedAccountBtnDes.size();
		System.out.println(" Number of notActivated accounts are: " + deactivatedAccountBtnNum);
		if (designersAdminPage.activateAccountBtnDes.size() > 1) {
			designersAdminPage.activateAccountFunDes(0);
			System.out.println("activateAccountBtnDes" + designersAdminPage.activateAccountBtnDes.size());
			designersAdminPage.openActiveStatusFunDes(0);
			designerProfileAdminPage.designerProfileStatus();
			if (designersAdminPage.deactivatedAccountBtnDes.size() > 0) {
				designersAdminPage.deActivateAccountFunDes(0);
				System.out.println("deActivateAccountFunDes" + designersAdminPage.deactivatedAccountBtnDes.size());
				designersAdminPage.openPendingStatusFunDes(0);
				designerProfileAdminPage.designerProfileStatus();
			}
		}
		homePageAdminPage.adminSideMenuListItems.get(3).click();
		System.out.println("activateAccountBtnDes" + designersAdminPage.activateAccountBtnDes.size());
		System.out.println("deActivateAccountFunDes" + designersAdminPage.deactivatedAccountBtnDes.size());
	}

	@Test(priority = 3)
	public void UpdateActivatedProfileDataTest() throws InterruptedException, AWTException {
		designersAdminPage = new DesignersAdminPage(driver);
		designerProfileAdminPage = new DesignerProfileAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		// Search for pending accounts
		homePageAdminPage.adminSideMenuListItems.get(3).click();
		designersAdminPage.searchStatus(2);
		if (designersAdminPage.confirmFCheckBoxDes.isSelected()) {
			designersAdminPage.swapConfirmCheckBoxFunDes();
			// Thread.sleep(1000);
			designersAdminPage.searchFilterFunDes();
			int activated1 = designersAdminPage.activeStatusDes.size();
			System.out.println(" Number of Activated accounts are: " + activated1);
			// Thread.sleep(1000);
			designersAdminPage.activateAccountFunDes(0);
			int activated2 = designersAdminPage.activeStatusDes.size();
			System.out.println(" Number of Activated accounts are: " + activated2);

			designersAdminPage.clearFilterFunDes();
			designersAdminPage.searchStatus(1);
			// Thread.sleep(1000);
			designersAdminPage.searchFilterFunDes();

			designersAdminPage.openActiveStatusFunDes(0);
			designerProfileAdminPage.updateProfileFunDes(fNameDes, lNameDes, bioDes, countryIndexDes, regionDes,
					cityDes, addDes, 2);
			designerProfileAdminPage.openAccountInfoTabFun();
			designerProfileAdminPage.openContestsInfoTabFun();
			designerProfileAdminPage.openTransfersInfoTabFun();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", designerProfileAdminPage.submitBtnDes);
			Assert.assertTrue(designerProfileAdminPage.updateAlertSuccessMsgDes.isDisplayed());
		} else {
			// Thread.sleep(1000);
			designersAdminPage.searchFilterFunDes();
			int activated1 = designersAdminPage.activeStatusDes.size();
			System.out.println(" Number of Activated accounts are: " + activated1);
			// Thread.sleep(1000);
			designersAdminPage.activateAccountFunDes(0);
			int activated2 = designersAdminPage.activeStatusDes.size();
			System.out.println(" Number of Activated accounts are: " + activated2);

			designersAdminPage.clearFilterFunDes();
			designersAdminPage.searchStatus(1);
			// Thread.sleep(1000);
			designersAdminPage.searchFilterFunDes();

			designersAdminPage.openActiveStatusFunDes(0);
			designerProfileAdminPage.updateProfileFunDes(fNameDes, lNameDes, bioDes, countryIndexDes, regionDes,
					cityDes, addDes, 2);
			designerProfileAdminPage.openAccountInfoTabFun();
			designerProfileAdminPage.openContestsInfoTabFun();
			designerProfileAdminPage.openTransfersInfoTabFun();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", designerProfileAdminPage.submitBtnDes);
			Assert.assertTrue(designerProfileAdminPage.updateAlertSuccessMsgDes.isDisplayed());
		}

	}

	@Test(priority = 4)
	public void UpdateNotActivatedProfileDataTest() throws InterruptedException, AWTException {
		designersAdminPage = new DesignersAdminPage(driver);
		designerProfileAdminPage = new DesignerProfileAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		// Search for pending accounts
		homePageAdminPage.adminSideMenuListItems.get(3).click();
		// Search for active accounts
		designersAdminPage.searchStatus(1);

		if (designersAdminPage.confirmFCheckBoxDes.isSelected()) {
			// Thread.sleep(1000);
			designersAdminPage.searchFilterFunDes();
			int activated = designersAdminPage.activeStatusDes.size();
			System.out.println(" Number of Activated accounts are: " + activated);
			// Thread.sleep(1000);
			designersAdminPage.deActivateAccountFunDes(0);

			designersAdminPage.clearFilterFunDes();
			designersAdminPage.searchStatus(2);
			// Thread.sleep(1000);
			designersAdminPage.searchFilterFunDes();
			int activated1 = designersAdminPage.activeStatusDes.size();
			System.out.println(" Number of Activated accounts are: " + activated1);
			designersAdminPage.openPendingStatusFunDes(0);
			designerProfileAdminPage.updateProfileFunDes(fNameDes, lNameDes, bioDes, countryIndexDes, regionDes,
					cityDes, addDes, 1);
			designerProfileAdminPage.openAccountInfoTabFun();
			designerProfileAdminPage.openContestsInfoTabFun();
			designerProfileAdminPage.openTransfersInfoTabFun();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", designerProfileAdminPage.submitBtnDes);
			Assert.assertTrue(designerProfileAdminPage.updateAlertSuccessMsgDes.isDisplayed());
		} else {
			designersAdminPage.swapConfirmCheckBoxFunDes();
			// Thread.sleep(1000);
			designersAdminPage.searchFilterFunDes();
			int activated = designersAdminPage.activeStatusDes.size();
			System.out.println(" Number of Activated accounts are: " + activated);
			// Thread.sleep(1000);
			designersAdminPage.deActivateAccountFunDes(0);

			designersAdminPage.clearFilterFunDes();
			designersAdminPage.searchStatus(2);
			// Thread.sleep(1000);
			designersAdminPage.searchFilterFunDes();
			int activated1 = designersAdminPage.activeStatusDes.size();
			System.out.println(" Number of Activated accounts are: " + activated1);
			designersAdminPage.openPendingStatusFunDes(0);
			designerProfileAdminPage.updateProfileFunDes(fNameDes, lNameDes, bioDes, countryIndexDes, regionDes,
					cityDes, addDes, 1);
			designerProfileAdminPage.openAccountInfoTabFun();
			designerProfileAdminPage.openContestsInfoTabFun();
			designerProfileAdminPage.openTransfersInfoTabFun();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", designerProfileAdminPage.submitBtnDes);
			Assert.assertTrue(designerProfileAdminPage.updateAlertSuccessMsgDes.isDisplayed());
		}
	}

	@Test(priority = 5)
	public void logoutFun() throws AWTException {
		homePageAdminPage = new HomePageAdminPage(driver);
		Actions hoverAction;
		hoverAction = new Actions(driver);
		hoverAction.moveToElement(homePageAdminPage.userAdminMenu).perform();
		homePageAdminPage.logoutAdminFun();
		Assert.assertTrue(defaultAdminPage.loginLink.isDisplayed());
	}
}