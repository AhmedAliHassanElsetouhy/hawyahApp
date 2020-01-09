package designerTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.DefaultClientPage;
import clientPages.DesignsClientPage;
import clientPages.HomeClientPage;
import clientPages.LoginClientPage;
import clientPages.MyPageClientPage;
import clientTests.TestBase;
import data.ExcelReader;
import designerPages.AboutMeDesignerPage;
import designerPages.BankAccountsDesignerPage;
import designerPages.MyPageDesignerPage;

public class BankAccountsDesignerTest extends TestBase {

	DefaultClientPage defaultPage;
	LoginClientPage loginPage;
	HomeClientPage homePage;
	DesignsClientPage designsPage;
	MyPageClientPage myPagePage;
	MyPageDesignerPage myPageUserPage;
	BankAccountsDesignerPage bankAccountsUserPage;
	AboutMeDesignerPage aboutMeUserPage;

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
		defaultPage = new DefaultClientPage(driver);
		loginPage = new LoginClientPage(driver);
		defaultPage.openLoginForm();
		Assert.assertTrue(loginPage.forgetPassLinkCli.isDisplayed());
	}

	@Test(priority = 2, dependsOnMethods = { "openHomePageTest" })
	public void loginFun() throws IOException {
		loginPage = new LoginClientPage(driver);
		defaultPage = new DefaultClientPage(driver);
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
		myPageUserPage = new MyPageDesignerPage(driver);
		homePage.openMainMenuFun();
		myPagePage.openMyPageFun();
		Assert.assertTrue(myPageUserPage.myStatsLink.isDisplayed());
	}

	String iban = fakeData.number().digits(16);
	String accOwner = fakeData.funnyName().name();
	String bankName = fakeData.name().name();

	@Test(priority = 4, dependsOnMethods = { "openMyPageTest" })
	public void addBankAccountDataTest() throws InterruptedException {
		myPagePage = new MyPageClientPage(driver);
		myPageUserPage = new MyPageDesignerPage(driver);
		aboutMeUserPage = new AboutMeDesignerPage(driver);
		myPageUserPage.openMyBankAccount();
		myPagePage.openUpdateMyAccountPageFun();
		myPageUserPage.openMyBankAccount();
		aboutMeUserPage.updateBankAccountDataForm(iban, accOwner, bankName);
		Assert.assertTrue(aboutMeUserPage.bankAccConfirmMsg.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "addBankAccountDataTest" })
	public void ActivateTest() {
		myPageUserPage = new MyPageDesignerPage(driver);
		bankAccountsUserPage = new BankAccountsDesignerPage(driver);
		myPageUserPage.openMyBankAccount();
		bankAccountsUserPage.activateAccountFun(index);
		driver.switchTo().alert().accept();
		myPageUserPage.openMyBankAccount();
		Assert.assertTrue(bankAccountsUserPage.bankAccNotesHeader.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "ActivateTest" })
	public void DeActivateTest() {
		myPageUserPage = new MyPageDesignerPage(driver);
		bankAccountsUserPage = new BankAccountsDesignerPage(driver);
		// myPageUserPage.openMyBankAccount();
		bankAccountsUserPage.deactivateAccountFun(1);
		driver.switchTo().alert().accept();
		myPageUserPage.openMyBankAccount();
		Assert.assertTrue(bankAccountsUserPage.bankAccNotesHeader.isDisplayed());
	}

	@Test(priority = 7, dependsOnMethods = { "DeActivateTest" })
	public void DeleteTest() {
		myPageUserPage = new MyPageDesignerPage(driver);
		bankAccountsUserPage = new BankAccountsDesignerPage(driver);
		bankAccountsUserPage.deleteIcon(index);
		driver.switchTo().alert().accept();
		myPageUserPage.openMyBankAccount();
		Assert.assertTrue(bankAccountsUserPage.bankAccNotesHeader.isDisplayed());
	}

	@Test(priority = 8, dependsOnMethods = { "DeleteTest" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomeClientPage(driver);
		defaultPage = new DefaultClientPage(driver);
		homePage.openMainMenuFun();
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginLinkCli.isDisplayed());
	}
}