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
	int statusIndexDes = 2;

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
		homePageAdminPage.adminSideMenuListItems.get(3).click();
	}

	@Test(priority = 2)
	public void DisableTest() throws InterruptedException, AWTException {
		designersAdminPage = new DesignersAdminPage(driver);
		designerProfileAdminPage = new DesignerProfileAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		designersAdminPage.deActivateAccountFunDes(0);
		int activated = designersAdminPage.activeStatusDes.size();
		System.out.println(" Number of Activated accounts are: " + activated);
		Thread.sleep(2000);
		designersAdminPage.openActiveStatusFunDes(0);
		designerProfileAdminPage.designerProfileStatus();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", designerProfileAdminPage.submitBtnDes);
		homePageAdminPage.adminSideMenuListItems.get(3).click();
		int activated2 = designersAdminPage.activeStatusDes.size();
		System.out.println(" Number of Activated accounts are: " + activated2);
		Assert.assertTrue(activated != activated2);
	}

	@Test(priority = 3)
	public void UpdateProfileDataTest() throws InterruptedException, AWTException {
		designersAdminPage = new DesignersAdminPage(driver);
		designerProfileAdminPage = new DesignerProfileAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		designersAdminPage.searchStatus(1);
		designersAdminPage.searchFunDes();
		designersAdminPage.deActivateAccountFunDes(0);
		int activated = designersAdminPage.activeStatusDes.size();
		System.out.println(" Number of Activated accounts are: " + activated);
		Thread.sleep(2000);
		designersAdminPage.openActiveStatusFunDes(0);
		designerProfileAdminPage.updateProfileFunDes(fNameDes, lNameDes, bioDes, countryIndexDes, regionDes, cityDes,
				addDes, statusIndexDes);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", designerProfileAdminPage.submitBtnDes);
		Assert.assertTrue(designerProfileAdminPage.updateAlertSuccessMsgDes.isDisplayed());

		Actions hoverAction;
		defaultAdminPage = new DefaultPage(driver);
		hoverAction = new Actions(driver);
		hoverAction.moveToElement(homePageAdminPage.userAdminMenu).perform();
		homePageAdminPage.logoutAdminFun();
		Assert.assertTrue(defaultAdminPage.loginLink.isDisplayed());
	}
}