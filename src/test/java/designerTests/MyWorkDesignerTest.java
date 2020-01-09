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
import designerPages.MyWorkDesignerPage;
import designerPages.TransferRequestsDesignerPage;

public class MyWorkDesignerTest extends TestBase {

	DefaultClientPage defaultPage;
	LoginClientPage loginPage;
	HomeClientPage homePage;
	DesignsClientPage designsPage;
	MyPageClientPage myPagePage;
	MyPageDesignerPage myPageUserPage;
	BankAccountsDesignerPage bankAccountsUserPage;
	AboutMeDesignerPage aboutMeUserPage;
	TransferRequestsDesignerPage transferRequestsUserPage;
	MyWorkDesignerPage myWorkUserPage;

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
		defaultPage = new DefaultClientPage(driver);
		loginPage = new LoginClientPage(driver);
		defaultPage.openLoginForm();
		Assert.assertTrue(loginPage.forgetPassLinkCli.isDisplayed());
	}

	@Test(priority = 2, dependsOnMethods = { "openHomePageTest" })
	public void loginFun() throws IOException {
		loginPage = new LoginClientPage(driver);
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

	@Test(priority = 4, dependsOnMethods = { "openMyPageTest" })
	public void openMyWorkTest() {
		myPageUserPage = new MyPageDesignerPage(driver);
		myWorkUserPage = new MyWorkDesignerPage(driver);
		myPageUserPage.openMyWorks();
		Assert.assertTrue(myWorkUserPage.myWorkHeaderMsg.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "openMyWorkTest" })
	public void addDesignTest() throws InterruptedException, AWTException, IOException {
		myWorkUserPage = new MyWorkDesignerPage(driver);
		myPageUserPage = new MyPageDesignerPage(driver);
		ExcelReader ER = new ExcelReader();

		myWorkUserPage.uploadIcon
				.sendKeys(System.getProperty("user.dir") + "\\Uploads\\" + ER.getExcelData(9, 2)[0][1]);
		myWorkUserPage.addDesignFun(title);
		// Assert.assertTrue(transferRequestsUserPage.transferModel.isDisplayed());
		Assert.assertTrue(myPageUserPage.confirmSaveDesignMsg.isDisplayed());
	}

	// @Test(priority = 6, dependsOnMethods = { "addDesignTest" })
	// public void makeLogoutTest() throws AWTException {
	// homePage = new HomePage(driver);
	// defaultPage = new DefaultPage(driver);
	// homePage.openMainMenuFun();
	// homePage.logoutFun();
	// Assert.assertTrue(defaultPage.loginLink.isDisplayed());
	// }
}
