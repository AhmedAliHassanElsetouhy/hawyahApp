package designerTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.DefaultPage;
import clientPages.DesignsPage;
import clientPages.HomePage;
import clientPages.LoginPage;
import clientPages.MyPagePage;
import clientTests.TestBase;
import data.ExcelReader;
import designerPages.AboutMeUserPage;
import designerPages.BankAccountsUserPage;
import designerPages.MyPageUserPage;

public class BankAccountsUserTest extends TestBase {

	DefaultPage defaultPage;
	LoginPage loginPage;
	HomePage homePage;
	DesignsPage designsPage;
	MyPagePage myPagePage;
	MyPageUserPage myPageUserPage;
	BankAccountsUserPage bankAccountsUserPage;
	AboutMeUserPage aboutMeUserPage;

	Faker fakeData = new Faker();
	String fName = fakeData.name().firstName();
	String lName = fakeData.name().lastName();
	String city = fakeData.nation().capitalCity();
	String phone = fakeData.phoneNumber().cellPhone();
	String screenName = fakeData.name().firstName() + fakeData.number().numberBetween(1, 99);
	String designerBio = fakeData.name().title();
	int index = 1;

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
		myPageUserPage = new MyPageUserPage(driver);
		homePage.openMainMenuFun();
		myPagePage.openMyPageFun();
		Assert.assertTrue(myPageUserPage.myStatsLink.isDisplayed());
	}

	String iban = fakeData.number().digits(16);
	String accOwner = fakeData.funnyName().name();
	String bankName = fakeData.name().name();

	@Test(priority = 4, dependsOnMethods = { "openMyPageTest" })
	public void addBankAccountDataTest() throws InterruptedException {
		myPagePage = new MyPagePage(driver);
		myPageUserPage = new MyPageUserPage(driver);
		aboutMeUserPage = new AboutMeUserPage(driver);
		myPageUserPage.openMyBankAccount();
		myPagePage.openUpdateMyAccountPageFun();
		myPageUserPage.openMyBankAccount();
		aboutMeUserPage.updateBankAccountDataForm(iban, accOwner, bankName);
		Assert.assertTrue(aboutMeUserPage.bankAccConfirmMsg.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "addBankAccountDataTest" })
	public void ActivateTest() {
		myPageUserPage = new MyPageUserPage(driver);
		bankAccountsUserPage = new BankAccountsUserPage(driver);
		myPageUserPage.openMyBankAccount();
		bankAccountsUserPage.activateAccountFun(index);
		driver.switchTo().alert().accept();
		myPageUserPage.openMyBankAccount();
		Assert.assertTrue(bankAccountsUserPage.bankAccNotesHeader.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "ActivateTest" })
	public void DeActivateTest() {
		myPageUserPage = new MyPageUserPage(driver);
		bankAccountsUserPage = new BankAccountsUserPage(driver);
		// myPageUserPage.openMyBankAccount();
		bankAccountsUserPage.deactivateAccountFun(1);
		driver.switchTo().alert().accept();
		myPageUserPage.openMyBankAccount();
		Assert.assertTrue(bankAccountsUserPage.bankAccNotesHeader.isDisplayed());
	}

	@Test(priority = 7, dependsOnMethods = { "DeActivateTest" })
	public void DeleteTest() {
		myPageUserPage = new MyPageUserPage(driver);
		bankAccountsUserPage = new BankAccountsUserPage(driver);
		bankAccountsUserPage.deleteIcon(index);
		driver.switchTo().alert().accept();
		myPageUserPage.openMyBankAccount();
		Assert.assertTrue(bankAccountsUserPage.bankAccNotesHeader.isDisplayed());
	}

	@Test(priority = 8, dependsOnMethods = { "DeleteTest" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomePage(driver);
		defaultPage = new DefaultPage(driver);
		homePage.openMainMenuFun();
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginLink.isDisplayed());
	}
}