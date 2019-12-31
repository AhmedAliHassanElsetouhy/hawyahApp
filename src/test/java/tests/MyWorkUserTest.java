package tests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import data.ExcelReader;
import pages.AboutMeUserPage;
import pages.BankAccountsUserPage;
import pages.DefaultPage;
import pages.DesignsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyPagePage;
import pages.MyPageUserPage;
import pages.MyWorkUserPage;
import pages.TransferRequestsUserPage;

public class MyWorkUserTest extends TestBase {

	DefaultPage defaultPage;
	LoginPage loginPage;
	HomePage homePage;
	DesignsPage designsPage;
	MyPagePage myPagePage;
	MyPageUserPage myPageUserPage;
	BankAccountsUserPage bankAccountsUserPage;
	AboutMeUserPage aboutMeUserPage;
	TransferRequestsUserPage transferRequestsUserPage;
	MyWorkUserPage myWorkUserPage;

	Faker fakeData = new Faker();
	String fName = fakeData.name().firstName();
	String lName = fakeData.name().lastName();
	String city = fakeData.nation().capitalCity();
	String phone = fakeData.phoneNumber().cellPhone();
	String screenName = fakeData.name().firstName() + fakeData.number().numberBetween(1, 99);
	String designerBio = fakeData.name().title();
	String title = fakeData.name().title();
	String folderName = fakeData.file().extension();

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
		myPageUserPage = new MyPageUserPage(driver);
		homePage.openMainMenuFun();
		myPagePage.openMyPageFun();
		Assert.assertTrue(myPageUserPage.myStatsLink.isDisplayed());
	}

	@Test(priority = 4, dependsOnMethods = { "openMyPageTest" })
	public void openMyWorkTest() {
		myPageUserPage = new MyPageUserPage(driver);
		myWorkUserPage = new MyWorkUserPage(driver);
		myPageUserPage.openMyWorks();
		Assert.assertTrue(myWorkUserPage.myWorkHeaderMsg.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "openMyWorkTest" })
	public void addDesignTest() throws InterruptedException, AWTException, IOException {
		myWorkUserPage = new MyWorkUserPage(driver);
		ExcelReader ER = new ExcelReader();
		myWorkUserPage.addDesignFun(title, ER.getExcelData(0, 7)[0][1]);

		// Assert.assertTrue(transferRequestsUserPage.transferModel.isDisplayed());
	}
}
