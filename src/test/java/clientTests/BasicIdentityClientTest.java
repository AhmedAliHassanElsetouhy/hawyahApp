package clientTests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.DefaultClientPage;
import clientPages.DesignsClientPage;
import clientPages.HomeClientPage;
import clientPages.LoginClientPage;
import clientPages.MoreDetailsClientPage;
import clientPages.PaymentClientPage;
import clientPages.RequestDesignAndSearchClientPage;
import clientPages.SelectColorsClientPage;
import clientPages.SelectDesignsClientPage;
import data.ExcelReader;

public class BasicIdentityClientTest extends TestBase {

	Faker fakeData = new Faker();
	DefaultClientPage defaultClientPage;
	RequestDesignAndSearchClientPage requestDesignClientPage;
	String searchTextCli = fakeData.name().firstName();
	LoginClientPage loginClientPage;
	HomeClientPage homeClientPage;
	DesignsClientPage designsClientPage;
	SelectDesignsClientPage selectDesignsClientPage;
	SelectColorsClientPage selectColorsClientPage;
	MoreDetailsClientPage moreDetailsClientPage;
	String activityNameCli = fakeData.name().firstName();
	String activityDescCli = fakeData.name().fullName();
	String contestCli = fakeData.name().lastName();
	String additionalInfoCli = fakeData.name().lastName();
	String titleCli = fakeData.name().nameWithMiddle();
	PaymentClientPage paymentClientPage;
	JavascriptExecutor jse;

	@Test(priority = 1)
	public void openHomePageTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultClientPage = new DefaultClientPage(driver);
		loginClientPage = new LoginClientPage(driver);
		defaultClientPage.openLoginForm();
		Assert.assertTrue(loginClientPage.forgetPassLinkCli.isDisplayed());
	}

	@Test(priority = 2, dependsOnMethods = { "openHomePageTest" })
	public void loginFun() throws IOException {
		loginClientPage = new LoginClientPage(driver);
		defaultClientPage = new DefaultClientPage(driver);
		homeClientPage = new HomeClientPage(driver);
		ExcelReader ER = new ExcelReader();
		loginClientPage.loginFun(ER.getExcelData(0, 2)[1][1], ER.getExcelData(0, 2)[2][1]);
		// loginPage.submitLoginFun();
		System.out.println(homeClientPage.loginConfirmMsgCli.getText());
		Assert.assertTrue(homeClientPage.loginConfirmMsgCli.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openRequestDesignsTest() {
		defaultClientPage = new DefaultClientPage(driver);
		requestDesignClientPage = new RequestDesignAndSearchClientPage(driver);
		homeClientPage = new HomeClientPage(driver);
		designsClientPage = new DesignsClientPage(driver);
		homeClientPage.openServicesFun();
		Assert.assertTrue(designsClientPage.basicIdentityLinkCli.isDisplayed());
	}

	@Test(priority = 4, dependsOnMethods = { "openRequestDesignsTest" })
	public void openDesignTest() {
		designsClientPage = new DesignsClientPage(driver);
		selectDesignsClientPage = new SelectDesignsClientPage(driver);
		designsClientPage.openBasicIdentityFun();
		Assert.assertTrue(selectDesignsClientPage.designPageHeaderTxtCli.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "openDesignTest" })
	public void selectDesignsTest() {
		designsClientPage = new DesignsClientPage(driver);
		selectDesignsClientPage = new SelectDesignsClientPage(driver);
		selectColorsClientPage = new SelectColorsClientPage(driver);
		// selectDesignsPage.selectIconDesign();
		selectDesignsClientPage.nextFun();
		Assert.assertTrue(selectColorsClientPage.colorPageHeaderTxtCli.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "selectDesignsTest" })
	public void selectColorsTest() {
		designsClientPage = new DesignsClientPage(driver);
		selectDesignsClientPage = new SelectDesignsClientPage(driver);
		selectColorsClientPage = new SelectColorsClientPage(driver);
		moreDetailsClientPage = new MoreDetailsClientPage(driver);
		selectColorsClientPage.selectColorFun();
		selectDesignsClientPage.nextFun();
		Assert.assertTrue(moreDetailsClientPage.moreDetailsPageHeaderTextCli.isDisplayed());
	}

	@Test(priority = 7, dependsOnMethods = { "selectColorsTest" })
	public void emptyMoreDetailsTest() {
		selectDesignsClientPage = new SelectDesignsClientPage(driver);
		moreDetailsClientPage = new MoreDetailsClientPage(driver);
		selectDesignsClientPage.nextFun();
		Assert.assertTrue(moreDetailsClientPage.activityClassificationValidationMsgCli.isDisplayed());
	}

	@Test(priority = 8, dependsOnMethods = { "emptyMoreDetailsTest" })
	public void ValidMoreDetailsTest() throws InterruptedException, AWTException, IOException {
		selectDesignsClientPage = new SelectDesignsClientPage(driver);
		moreDetailsClientPage = new MoreDetailsClientPage(driver);
		paymentClientPage = new PaymentClientPage(driver);
		ExcelReader ER = new ExcelReader();
		moreDetailsClientPage.moreDetailsFun(activityNameCli, activityDescCli, ER.getExcelData(2, 2)[1][1], contestCli,
				additionalInfoCli, ER.getExcelData(2, 2)[0][1], titleCli);
		selectDesignsClientPage.nextFun();
		Assert.assertTrue(paymentClientPage.paymentPageHeaderCli.isDisplayed());
	}

	@Test(priority = 9, dependsOnMethods = { "ValidMoreDetailsTest" })
	public void ValidPaymentTest() throws InterruptedException, AWTException {
		selectDesignsClientPage = new SelectDesignsClientPage(driver);
		paymentClientPage = new PaymentClientPage(driver);
		// paymentPage.silverPrize();
		// Thread.sleep(3000);
		// Actions action = new Actions(driver);
		// action.moveToElement(paymentPage.paymentLst, 594,
		// 1232).click().build().perform();

		// WebDriverWait wait = new WebDriverWait(driver, 30);
		// wait.until(ExpectedConditions.visibilityOfElementLocated((By)
		// paymentPage.openPaymentList));
		paymentClientPage.bankDepositFun();
		// System.out.println(paymentPage.bankDeposit.getText());
		// selectDesignsPage.nextFun();
		// Assert.assertTrue(paymentPage.paymentPageHeader.isDisplayed());
	}

	// @Test(priority = 10, dependsOnMethods = { "ValidPaymentTest" })
	// public void makeLogoutTest() throws AWTException {
	// homePage = new HomePage(driver);
	// defaultPage = new DefaultPage(driver);
	// homePage.openMainMenuFun();
	// homePage.logoutFun();
	// Assert.assertTrue(defaultPage.loginLink.isDisplayed());
	// }
}
