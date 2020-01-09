package designerTests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.DefaultPage;
import clientPages.DesignsClientPage;
import clientPages.HomeClientPage;
import clientPages.LoginPage;
import clientPages.MyPageClientPage;
import clientTests.TestBase;
import data.ExcelReader;
import designerPages.AboutMeDesignerPage;
import designerPages.BankAccountsDesignerPage;
import designerPages.HomeDesignerPage;
import designerPages.MyPageDesignerPage;

public class UpdateLoginDataDesignerTest extends TestBase {

	DefaultPage defaultPage;
	LoginPage loginPage;
	HomeClientPage homePage;
	DesignsClientPage designsPage;
	MyPageClientPage myPagePage;
	AboutMeDesignerPage aboutMeUserPage;
	HomeDesignerPage homeUserPage;
	MyPageDesignerPage myPageUserPage;
	BankAccountsDesignerPage bankAccountsUserPage;

	Faker fakeData = new Faker();
	String fName = fakeData.name().firstName();
	String lName = fakeData.name().lastName();
	String city = fakeData.nation().capitalCity();
	String phone = fakeData.phoneNumber().cellPhone();
	String screenName = fakeData.name().firstName() + fakeData.number().numberBetween(1, 99);
	String designerBio = fakeData.name().title();
	public String address = fakeData.address().fullAddress();

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
		homePage = new HomeClientPage(driver);
		ExcelReader ER = new ExcelReader();
		loginPage.loginFun(ER.getExcelData(5, 2)[1][1], ER.getExcelData(5, 2)[2][1]);
		System.out.println(ER.getExcelData(5, 2)[1][1]);
		System.out.println(ER.getExcelData(5, 2)[2][1]);
		System.out.println(homePage.loginConfirmMsgCli.getText());
		Assert.assertTrue(homePage.loginConfirmMsgCli.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openMyPageTest() {
		homePage = new HomeClientPage(driver);
		myPagePage = new MyPageClientPage(driver);
		homePage.openMainMenuFun();
		myPagePage.openMyPageFun();
		Assert.assertTrue(myPagePage.aboutMeLinkCli.isDisplayed());
	}

	JavascriptExecutor jse;

	@Test(priority = 4, dependsOnMethods = { "openMyPageTest" })
	public void openEditMyAccountDataTest() {
		myPagePage = new MyPageClientPage(driver);
		aboutMeUserPage = new AboutMeDesignerPage(driver);
		myPagePage.openUpdateMyAccountPageFun();
		// jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();", myPagePage.updateBtn);
		Assert.assertTrue(aboutMeUserPage.personalDataLinkDes.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "openEditMyAccountDataTest" })
	public void updateLoginDataTest() throws IOException {
		homePage = new HomeClientPage(driver);
		myPagePage = new MyPageClientPage(driver);
		aboutMeUserPage = new AboutMeDesignerPage(driver);
		myPageUserPage = new MyPageDesignerPage(driver);
		ExcelReader ER = new ExcelReader();
		// myPagePage.openUpdateMyAccountPageFun();
		myPagePage.openLoginDataFormPage();
		aboutMeUserPage.updateLoginDataForm(ER.getExcelData(6, 2)[1][1], ER.getExcelData(6, 2)[2][1],
				ER.getExcelData(6, 2)[3][1]);
		System.out.println(ER.getExcelData(1, 2)[1][1]);
		System.out.println(ER.getExcelData(1, 2)[2][1]);
		System.out.println(ER.getExcelData(1, 2)[3][1]);
		aboutMeUserPage.saveDataFun();
		Assert.assertTrue(myPageUserPage.aboutMeLinkDes.isDisplayed());
		Assert.assertTrue(myPageUserPage.myStatsLinkDes.isDisplayed());
		Assert.assertTrue(myPageUserPage.myBankAccountLinkDes.isDisplayed());
		Assert.assertTrue(myPageUserPage.myTransfersLinkDes.isDisplayed());
		Assert.assertTrue(myPageUserPage.myWorksLinkDes.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "updateLoginDataTest" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomeClientPage(driver);
		defaultPage = new DefaultPage(driver);
		homePage.openMainMenuFun();
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginLink.isDisplayed());
	}
}
