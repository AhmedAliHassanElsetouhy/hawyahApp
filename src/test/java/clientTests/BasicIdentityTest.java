package clientTests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.DefaultPage;
import clientPages.DesignsPage;
import clientPages.HomePage;
import clientPages.LoginPage;
import clientPages.MoreDetailsPage;
import clientPages.PaymentPage;
import clientPages.RequestDesignAndSearchPage;
import clientPages.SelectColorsPage;
import clientPages.SelectDesignsPage;
import data.ExcelReader;

public class BasicIdentityTest extends TestBase {

	Faker fakeData = new Faker();
	DefaultPage defaultPage;
	RequestDesignAndSearchPage requestDesignPage;
	String searchText = fakeData.name().firstName();
	LoginPage loginPage;
	HomePage homePage;
	DesignsPage designsPage;
	SelectDesignsPage selectDesignsPage;
	SelectColorsPage selectColorsPage;
	MoreDetailsPage moreDetailsPage;
	String activityName = fakeData.name().firstName();
	String activityDesc = fakeData.name().fullName();
	String contest = fakeData.name().lastName();
	String additionalInfo = fakeData.name().lastName();
	String title = fakeData.name().nameWithMiddle();
	PaymentPage paymentPage;
	JavascriptExecutor jse;

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
		loginPage.loginFun(ER.getExcelData(0, 2)[1][1], ER.getExcelData(0, 2)[2][1]);
		// loginPage.submitLoginFun();
		System.out.println(homePage.loginConfirmMsg.getText());
		Assert.assertTrue(homePage.loginConfirmMsg.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openRequestDesignsTest() {
		defaultPage = new DefaultPage(driver);
		requestDesignPage = new RequestDesignAndSearchPage(driver);
		homePage = new HomePage(driver);
		designsPage = new DesignsPage(driver);
		homePage.openServicesFun();
		Assert.assertTrue(designsPage.basicIdentityLink.isDisplayed());
	}

	@Test(priority = 4, dependsOnMethods = { "openRequestDesignsTest" })
	public void openDesignTest() {
		designsPage = new DesignsPage(driver);
		selectDesignsPage = new SelectDesignsPage(driver);
		designsPage.openBasicIdentityFun();
		Assert.assertTrue(selectDesignsPage.designPageHeaderTxt.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "openDesignTest" })
	public void selectDesignsTest() {
		designsPage = new DesignsPage(driver);
		selectDesignsPage = new SelectDesignsPage(driver);
		selectColorsPage = new SelectColorsPage(driver);
		// selectDesignsPage.selectIconDesign();
		selectDesignsPage.nextFun();
		Assert.assertTrue(selectColorsPage.colorPageHeaderTxt.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "selectDesignsTest" })
	public void selectColorsTest() {
		designsPage = new DesignsPage(driver);
		selectDesignsPage = new SelectDesignsPage(driver);
		selectColorsPage = new SelectColorsPage(driver);
		moreDetailsPage = new MoreDetailsPage(driver);
		selectColorsPage.selectColorFun();
		selectDesignsPage.nextFun();
		Assert.assertTrue(moreDetailsPage.moreDetailsPageHeaderText.isDisplayed());
	}

	@Test(priority = 7, dependsOnMethods = { "selectColorsTest" })
	public void emptyMoreDetailsTest() {
		selectDesignsPage = new SelectDesignsPage(driver);
		moreDetailsPage = new MoreDetailsPage(driver);
		selectDesignsPage.nextFun();
		Assert.assertTrue(moreDetailsPage.activityClassificationValidationMsg.isDisplayed());
	}

	@Test(priority = 8, dependsOnMethods = { "emptyMoreDetailsTest" })
	public void ValidMoreDetailsTest() throws InterruptedException, AWTException, IOException {
		selectDesignsPage = new SelectDesignsPage(driver);
		moreDetailsPage = new MoreDetailsPage(driver);
		paymentPage = new PaymentPage(driver);
		ExcelReader ER = new ExcelReader();
		moreDetailsPage.moreDetailsFun(activityName, activityDesc, ER.getExcelData(2, 2)[1][1], contest, additionalInfo,
				ER.getExcelData(2, 2)[0][1], title);
		selectDesignsPage.nextFun();
		Assert.assertTrue(paymentPage.paymentPageHeader.isDisplayed());
	}

	@Test(priority = 9, dependsOnMethods = { "ValidMoreDetailsTest" })
	public void ValidPaymentTest() throws InterruptedException, AWTException {
		selectDesignsPage = new SelectDesignsPage(driver);
		paymentPage = new PaymentPage(driver);
		// paymentPage.silverPrize();
		// Thread.sleep(3000);
		// Actions action = new Actions(driver);
		// action.moveToElement(paymentPage.paymentLst, 594,
		// 1232).click().build().perform();

		// WebDriverWait wait = new WebDriverWait(driver, 30);
		// wait.until(ExpectedConditions.visibilityOfElementLocated((By)
		// paymentPage.openPaymentList));
		paymentPage.bankDepositFun();
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
