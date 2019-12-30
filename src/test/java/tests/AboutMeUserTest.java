package tests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import data.ExcelReader;
import pages.AboutMeUserPage;
import pages.DefaultPage;
import pages.DesignsPage;
import pages.HomePage;
import pages.HomeUserPage;
import pages.LoginPage;
import pages.MyPagePage;
import pages.MyPageUserPage;

public class AboutMeUserTest extends TestBase {

	DefaultPage defaultPage;
	LoginPage loginPage;
	HomePage homePage;
	DesignsPage designsPage;
	MyPagePage myPagePage;
	AboutMeUserPage aboutMeUserPage;
	HomeUserPage homeUserPage;
	MyPageUserPage myPageUserPage;

	Faker fakeData = new Faker();
	String fName = fakeData.name().firstName();
	String lName = fakeData.name().lastName();
	String city = fakeData.nation().capitalCity();
	String phone = fakeData.phoneNumber().cellPhone();
	String screenName = fakeData.name().firstName() + fakeData.number().numberBetween(1, 99);
	String designerBio = fakeData.name().title();

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
		System.out.println(ER.getExcelData(5, 2)[1][1]);
		System.out.println(ER.getExcelData(5, 2)[2][1]);
		System.out.println(homePage.loginConfirmMsg.getText());
		Assert.assertTrue(homePage.loginConfirmMsg.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openMyPageTest() {
		homePage = new HomePage(driver);
		myPagePage = new MyPagePage(driver);
		homePage.openMainMenuFun();
		myPagePage.openMyPageFun();
		Assert.assertTrue(myPagePage.aboutMeLink.isDisplayed());
	}

	@Test(priority = 4, dependsOnMethods = { "openMyPageTest" })
	public void openEditMyAccountDataTest() {
		myPagePage = new MyPagePage(driver);
		aboutMeUserPage = new AboutMeUserPage(driver);
		myPagePage.openUpdateMyAccountPageFun();
		Assert.assertTrue(aboutMeUserPage.personalDataLink.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "openEditMyAccountDataTest" })
	public void updatePersonalDataTest() throws IOException {
		homePage = new HomePage(driver);
		myPagePage = new MyPagePage(driver);
		aboutMeUserPage = new AboutMeUserPage(driver);
		homeUserPage = new HomeUserPage(driver);
		myPageUserPage = new MyPageUserPage(driver);
		ExcelReader ER = new ExcelReader();
		aboutMeUserPage.personalDesignerDataForm(fName, lName, screenName, designerBio, city, phone,
				ER.getExcelData(6, 4)[0][1]);
		aboutMeUserPage.saveDataFun();
		Assert.assertTrue(myPageUserPage.aboutMeLink.isDisplayed());
		Assert.assertTrue(myPageUserPage.myStatsLink.isDisplayed());
		Assert.assertTrue(myPageUserPage.myBankAccountLink.isDisplayed());
		Assert.assertTrue(myPageUserPage.myTransfersLink.isDisplayed());
		Assert.assertTrue(myPageUserPage.myWorksLink.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "updatePersonalDataTest" })
	public void updateLoginDataTest() throws IOException {
		homePage = new HomePage(driver);
		myPagePage = new MyPagePage(driver);
		aboutMeUserPage = new AboutMeUserPage(driver);
		ExcelReader ER = new ExcelReader();
		myPagePage.openUpdateMyAccountPageFun();
		myPagePage.openLoginDataFormPage();
		aboutMeUserPage.updateLoginDataForm(ER.getExcelData(6, 2)[1][1], ER.getExcelData(6, 2)[2][1],
				ER.getExcelData(6, 2)[3][1]);
		System.out.println(ER.getExcelData(1, 2)[1][1]);
		System.out.println(ER.getExcelData(1, 2)[2][1]);
		System.out.println(ER.getExcelData(1, 2)[3][1]);
		aboutMeUserPage.saveDataFun();
		Assert.assertTrue(myPageUserPage.aboutMeLink.isDisplayed());
		Assert.assertTrue(myPageUserPage.myStatsLink.isDisplayed());
		Assert.assertTrue(myPageUserPage.myBankAccountLink.isDisplayed());
		Assert.assertTrue(myPageUserPage.myTransfersLink.isDisplayed());
		Assert.assertTrue(myPageUserPage.myWorksLink.isDisplayed());
	}

	@Test(priority = 7, dependsOnMethods = { "updateLoginDataTest" })
	public void updateBankAccountDataTest() throws IOException {
		homePage = new HomePage(driver);
		myPagePage = new MyPagePage(driver);
		aboutMeUserPage = new AboutMeUserPage(driver);
		ExcelReader ER = new ExcelReader();
		myPagePage.openUpdateMyAccountPageFun();
		aboutMeUserPage.openBankAccountFun();
		aboutMeUserPage.updateBankAccountDataForm(ER.getExcelData(6, 2)[4][1], ER.getExcelData(6, 2)[5][1],
				ER.getExcelData(6, 2)[6][1]);
		// aboutMeUserPage.saveDataFun();
		// aboutMeUserPage.saveBtn.sendKeys(Keys.ENTER);
		Assert.assertTrue(aboutMeUserPage.bankAccConfirmMsg.isDisplayed());
		Assert.assertTrue(myPageUserPage.aboutMeLink.isDisplayed());
		Assert.assertTrue(myPageUserPage.myStatsLink.isDisplayed());
		Assert.assertTrue(myPageUserPage.myBankAccountLink.isDisplayed());
		Assert.assertTrue(myPageUserPage.myTransfersLink.isDisplayed());
		Assert.assertTrue(myPageUserPage.myWorksLink.isDisplayed());
	}

	@Test(priority = 8, dependsOnMethods = { "updateBankAccountDataTest" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomePage(driver);
		defaultPage = new DefaultPage(driver);
		homePage.openMainMenuFun();
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginLink.isDisplayed());
	}

}
