package clientTests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.CompetitionsClientPage;
import clientPages.CompetitionsDetailsClientPage;
import clientPages.DefaultPage;
import clientPages.DesignsClientPage;
import clientPages.HomeClientPage;
import clientPages.LoginPage;
import clientPages.MoreDetailsClientPage;
import clientPages.PaymentClientPage;
import clientPages.RequestDesignAndSearchClientPage;
import clientPages.SelectColorsClientPage;
import clientPages.SelectDesignsClientPage;
import data.ExcelReader;

public class NotCompletedCompetitionsClientTest extends TestBase {

	Faker fakeData = new Faker();
	DefaultPage defaultClientPage;
	RequestDesignAndSearchClientPage requestDesignAndSearchClientPage;
	String searchTextCli = fakeData.name().firstName();
	LoginPage loginClientPage;
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
	CompetitionsClientPage competitionsClientPage;
	CompetitionsDetailsClientPage competitionsDetailsClientPage;
	int compititionItem = 0;

	@Test(priority = 1)
	public void openHomePageTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultClientPage = new DefaultPage(driver);
		loginClientPage = new LoginPage(driver);
		defaultClientPage.openLoginForm();
		Assert.assertTrue(loginClientPage.forgetPassLink.isDisplayed());
	}

	@Test(priority = 2)
	// @Test(priority = 2, dependsOnMethods = { "openHomePageTest" })
	public void loginFun() throws IOException {
		loginClientPage = new LoginPage(driver);
		defaultClientPage = new DefaultPage(driver);
		homeClientPage = new HomeClientPage(driver);
		ExcelReader ER = new ExcelReader();
		loginClientPage.loginFun(ER.getExcelData(0, 2)[1][1], ER.getExcelData(0, 2)[2][1]);
		System.out.println(homeClientPage.loginConfirmMsgCli.getText());
		Assert.assertTrue(homeClientPage.loginConfirmMsgCli.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3)
	// @Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openRequestDesignsTest() {
		defaultClientPage = new DefaultPage(driver);
		requestDesignAndSearchClientPage = new RequestDesignAndSearchClientPage(driver);
		homeClientPage = new HomeClientPage(driver);
		designsClientPage = new DesignsClientPage(driver);
		homeClientPage.openServicesFun();
		Assert.assertTrue(designsClientPage.basicIdentityLinkCli.isDisplayed());
	}

	@Test(priority = 4)
	// @Test(priority = 4, dependsOnMethods = { "openRequestDesignsTest" })
	public void openDesignTest() {
		designsClientPage = new DesignsClientPage(driver);
		selectDesignsClientPage = new SelectDesignsClientPage(driver);
		designsClientPage.openBasicIdentityFun();
		Assert.assertTrue(selectDesignsClientPage.designPageHeaderTxtCli.isDisplayed());
	}

	@Test(priority = 5)
	// @Test(priority = 5, dependsOnMethods = { "openDesignTest" })
	public void selectDesignsTest() {
		designsClientPage = new DesignsClientPage(driver);
		selectDesignsClientPage = new SelectDesignsClientPage(driver);
		selectColorsClientPage = new SelectColorsClientPage(driver);
		selectDesignsClientPage.nextFun();
		Assert.assertTrue(selectColorsClientPage.colorPageHeaderTxtCli.isDisplayed());
	}

	@Test(priority = 6)
	// @Test(priority = 6, dependsOnMethods = { "selectDesignsTest" })
	public void selectColorsTest() {
		designsClientPage = new DesignsClientPage(driver);
		selectDesignsClientPage = new SelectDesignsClientPage(driver);
		selectColorsClientPage = new SelectColorsClientPage(driver);
		moreDetailsClientPage = new MoreDetailsClientPage(driver);
		selectColorsClientPage.selectColorFun();
		selectDesignsClientPage.nextFun();
		Assert.assertTrue(moreDetailsClientPage.moreDetailsPageHeaderTextCli.isDisplayed());
	}

	@Test(priority = 7)
	// @Test(priority = 7, dependsOnMethods = { "selectColorsTest" })
	public void emptyMoreDetailsTest() {
		selectDesignsClientPage = new SelectDesignsClientPage(driver);
		moreDetailsClientPage = new MoreDetailsClientPage(driver);
		selectDesignsClientPage.nextFun();
		Assert.assertTrue(moreDetailsClientPage.activityClassificationValidationMsgCli.isDisplayed());
	}

	@Test(priority = 8)
	// @Test(priority = 8, dependsOnMethods = { "emptyMoreDetailsTest" })
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

	@Test(priority = 9)
	// @Test(priority = 9, dependsOnMethods = { "ValidMoreDetailsTest" })
	public void openCompetitionsTest() {
		homeClientPage = new HomeClientPage(driver);
		competitionsClientPage = new CompetitionsClientPage(driver);
		homeClientPage.openCompetitionFun();
		Assert.assertTrue(competitionsClientPage.competitionsCli.isDisplayed());
	}

	@Test(priority = 10)
	// @Test(priority = 10, dependsOnMethods = { "openCompetitionsTest" })
	public void deliverFinalWorkCompetitionTest() {
		competitionsClientPage = new CompetitionsClientPage(driver);
		competitionsDetailsClientPage = new CompetitionsDetailsClientPage(driver);
		competitionsClientPage.openNotCompletedCompetitionsFun(compititionItem);
		Assert.assertTrue(competitionsDetailsClientPage.contactUsLinkCLi.isDisplayed());
		Assert.assertTrue(competitionsDetailsClientPage.completeDataBtn.isDisplayed());
		competitionsDetailsClientPage.openCompleteDataFun();
	}

	@Test(priority = 11)
	// @Test(priority = 11, dependsOnMethods = { "deliverFinalWorkCompetitionTest"
	// })
	public void makeLogoutTest() throws AWTException {
		homeClientPage = new HomeClientPage(driver);
		defaultClientPage = new DefaultPage(driver);
		homeClientPage.openMainMenuFun();
		homeClientPage.logoutFun();
		Assert.assertTrue(defaultClientPage.loginLink.isDisplayed());
	}
}
