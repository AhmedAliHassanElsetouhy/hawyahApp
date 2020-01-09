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
import designerPages.MyPageDesignerPage;
import designerPages.TransferRequestsDesignerPage;

public class TransfersRequestsDesignerTest extends TestBase {
	DefaultPage defaultPage;
	LoginPage loginPage;
	HomeClientPage homePage;
	DesignsClientPage designsPage;
	MyPageClientPage myPagePage;
	MyPageDesignerPage myPageUserPage;
	BankAccountsDesignerPage bankAccountsUserPage;
	AboutMeDesignerPage aboutMeUserPage;
	TransferRequestsDesignerPage transferRequestsUserPage;
	JavascriptExecutor jse;

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
		Assert.assertTrue(myPageUserPage.myStatsLinkDes.isDisplayed());
	}

	@Test(priority = 4, dependsOnMethods = { "openMyPageTest" })
	public void openTransfersRequestsTest() {
		myPageUserPage = new MyPageDesignerPage(driver);
		bankAccountsUserPage = new BankAccountsDesignerPage(driver);
		transferRequestsUserPage = new TransferRequestsDesignerPage(driver);
		driver.navigate().to("https://hawyah-dev.herokuapp.com/designer_profile/financial_transfers?locale=ar");
		Assert.assertTrue(transferRequestsUserPage.balanceDes.isDisplayed());
		// Assert.assertTrue(transferRequestsUserPage.sendTransferBtn.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "openTransfersRequestsTest" })
	public void sendTransfersTest() {
		myPageUserPage = new MyPageDesignerPage(driver);
		bankAccountsUserPage = new BankAccountsDesignerPage(driver);
		transferRequestsUserPage = new TransferRequestsDesignerPage(driver);
		if (!transferRequestsUserPage.totalTransferredDes.getText().contains("0")) {
			jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", transferRequestsUserPage.sendTransferBtnDes);
			System.out.println(transferRequestsUserPage.modelViewDes.getText());
			transferRequestsUserPage.cancelFun();
		} else {
			Assert.assertTrue(transferRequestsUserPage.transferDateDes.isDisplayed());
		}
	}

	@Test(priority = 6, dependsOnMethods = { "sendTransfersTest" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomeClientPage(driver);
		defaultPage = new DefaultPage(driver);
		homePage.openMainMenuFun();
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginLink.isDisplayed());
	}
}