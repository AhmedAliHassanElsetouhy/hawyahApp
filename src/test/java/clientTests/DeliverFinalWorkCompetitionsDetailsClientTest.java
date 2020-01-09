package clientTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.CompetitionsDetailsClientPage;
import clientPages.CompetitionsClientPage;
import clientPages.DefaultPage;
import clientPages.HomeClientPage;
import clientPages.LoginPage;
import clientTests.TestBase;
import data.ExcelReader;

public class DeliverFinalWorkCompetitionsDetailsClientTest extends TestBase {

	DefaultPage defaultClientPage;
	LoginPage loginClientPage;
	HomeClientPage homeClientPage;
	CompetitionsClientPage competitionsClientPage;
	int compititionItem = 1;
	CompetitionsDetailsClientPage competitionsDetailsClientPage;
	Faker fakeData = new Faker();
	String nameCli = fakeData.name().fullName();
	String addressCli = fakeData.address().fullAddress();
	String mobileNumCli = fakeData.phoneNumber().cellPhone();
	String commentsCli = fakeData.name().title();

	@Test(priority = 1)
	public void openHomePageTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultClientPage = new DefaultPage(driver);
		loginClientPage = new LoginPage(driver);
		defaultClientPage.openLoginForm();
		Assert.assertTrue(loginClientPage.forgetPassLink.isDisplayed());
	}

	@Test(priority = 2, dependsOnMethods = { "openHomePageTest" })
	public void loginFun() throws IOException {
		loginClientPage = new LoginPage(driver);
		defaultClientPage = new DefaultPage(driver);
		homeClientPage = new HomeClientPage(driver);
		ExcelReader ER = new ExcelReader();
		loginClientPage.loginFun(ER.getExcelData(0, 2)[1][1], ER.getExcelData(0, 2)[2][1]);
		Assert.assertTrue(homeClientPage.loginConfirmMsgCli.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openCompetitionsTest() {
		homeClientPage = new HomeClientPage(driver);
		competitionsClientPage = new CompetitionsClientPage(driver);
		homeClientPage.openCompetitionFun();
		Assert.assertTrue(competitionsClientPage.competitionsCli.isDisplayed());
	}

	@Test(priority = 4, dependsOnMethods = { "openCompetitionsTest" })
	public void deliverFinalWorkCompetitionTest() {
		competitionsClientPage = new CompetitionsClientPage(driver);
		competitionsDetailsClientPage = new CompetitionsDetailsClientPage(driver);
		competitionsClientPage.openDeliverFinalWorkCompetitionFun(compititionItem);
		Assert.assertTrue(competitionsDetailsClientPage.detailsLinkCli.isDisplayed());
		Assert.assertTrue(competitionsDetailsClientPage.designsLinkCli.isDisplayed());
		Assert.assertTrue(competitionsDetailsClientPage.filesLinkCli.isDisplayed());
		Assert.assertTrue(competitionsDetailsClientPage.contactUsLinkCLi.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "deliverFinalWorkCompetitionTest" })
	public void clientMakeAgreement() throws InterruptedException, AWTException, IOException {
		competitionsDetailsClientPage = new CompetitionsDetailsClientPage(driver);
		competitionsDetailsClientPage.openFiles();
		Assert.assertTrue(competitionsDetailsClientPage.clientagreementPageHeader.isDisplayed());
		competitionsDetailsClientPage.signAgreement();
		competitionsDetailsClientPage.clientAgreement(nameCli, addressCli, mobileNumCli, commentsCli);
	}

	@Test(priority = 6, dependsOnMethods = { "clientMakeAgreement" })
	public void makeLogoutTest() throws AWTException {
		homeClientPage = new HomeClientPage(driver);
		defaultClientPage = new DefaultPage(driver);
		homeClientPage.openMainMenuFun();
		homeClientPage.logoutFun();
		Assert.assertTrue(defaultClientPage.loginLink.isDisplayed());
	}
}
