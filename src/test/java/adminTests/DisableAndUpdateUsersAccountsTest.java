package adminTests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import adminPages.ContestsAdminPage;
import adminPages.HomePageAdminPage;
import adminPages.UserProfileAdminPage;
import adminPages.UsersAdminPage;
import clientPages.DefaultPage;
import clientPages.HomeClientPage;
import clientPages.LoginPage;
import clientPages.RegistrationClientPage;
import clientPages.RegistrationConfirmationClientPage;
import clientTests.TestBase;
import data.ExcelReader;
import designerPages.HomeDesignerPage;

public class DisableAndUpdateUsersAccountsTest extends TestBase {

	HomePageAdminPage homePageAdminPage;
	DefaultPage defaultAdminPage;
	LoginPage loginAdminPage;
	String password = "11111111";
	ContestsAdminPage contestsAdminPage;
	UsersAdminPage usersAdminPage;
	RegistrationClientPage registrationPage;
	HomeClientPage homeClientPage;
	RegistrationConfirmationClientPage registerConfirmPage;
	HomeDesignerPage homeDesignerPage;
	UserProfileAdminPage userProfileAdminPage;

	Faker fake = new Faker();
	String userName = fake.name().firstName() + fake.number().numberBetween(1, 99);
	String email = fake.internet().emailAddress();
	// String password = fake.number().digits(8);
	String usPassword = "P@55word";

	String cusfName = fake.name().firstName();
	String cuslName = fake.name().lastName();
	String cusBio = fake.name().title();
	int cusCountryIndex = 3;
	String cusRegion = fake.address().streetName();
	String cusCity = fake.address().cityName();
	String cusAdd = fake.address().fullAddress();
	int customerStatusIndex = 2;

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
	public void DisableTest() throws InterruptedException, AWTException {
		usersAdminPage = new UsersAdminPage(driver);
		userProfileAdminPage = new UserProfileAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		usersAdminPage.activateAccountFun(0);
		int activated = usersAdminPage.activeStatus.size();
		System.out.println(" Number of Activated accounts are: " + activated);
		Thread.sleep(2000);
		usersAdminPage.openActiveStatusFun(0);
		userProfileAdminPage.customerProfileStatus();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", userProfileAdminPage.submitBtn);
		homePageAdminPage.adminSideMenuListItems.get(2).click();
		int activated2 = usersAdminPage.activeStatus.size();
		System.out.println(" Number of Activated accounts are: " + activated2);
		Assert.assertTrue(activated != activated2);
	}

	@Test(priority = 3)
	public void UpdateProfileDataTest() throws InterruptedException, AWTException {
		usersAdminPage = new UsersAdminPage(driver);
		userProfileAdminPage = new UserProfileAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		usersAdminPage.activateAccountFun(0);
		int activated = usersAdminPage.activeStatus.size();
		System.out.println(" Number of Activated accounts are: " + activated);
		Thread.sleep(2000);
		usersAdminPage.openActiveStatusFun(0);
		userProfileAdminPage.updateProfileFun(cusfName, cuslName, cusBio, cusCountryIndex, cusRegion, cusCity, cusAdd,
				customerStatusIndex);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", userProfileAdminPage.submitBtn);
		Assert.assertTrue(userProfileAdminPage.updateAlertSuccessMsg.isDisplayed());
		Actions hoverAction;
		homePageAdminPage = new HomePageAdminPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		hoverAction = new Actions(driver);
		hoverAction.moveToElement(homePageAdminPage.userAdminMenu).perform();
		homePageAdminPage.logoutAdminFun();
		Assert.assertTrue(defaultAdminPage.loginLink.isDisplayed());
	}
}