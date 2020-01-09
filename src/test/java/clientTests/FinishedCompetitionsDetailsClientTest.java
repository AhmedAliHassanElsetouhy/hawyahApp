package clientTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import clientPages.CompetitionsClientPage;
import clientPages.DefaultClientPage;
import clientPages.HomeClientPage;
import clientPages.LoginClientPage;
import clientTests.TestBase;
import data.ExcelReader;
import designerPages.CompetitionDetailsDesignerPage;

public class FinishedCompetitionsDetailsClientTest extends TestBase {

	DefaultClientPage defaultClientPage;
	LoginClientPage loginClientPage;
	HomeClientPage homeClientPage;
	CompetitionsClientPage competitionsClientPage;
	int compititionItem = 1;
	CompetitionDetailsDesignerPage competitionDetailsDesignerClientPage;

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
	public void finishedCompetitionsDetailsTest() {
		competitionsClientPage = new CompetitionsClientPage(driver);
		competitionDetailsDesignerClientPage = new CompetitionDetailsDesignerPage(driver);
		competitionsClientPage.openFinishedFun(compititionItem);
		Assert.assertTrue(competitionDetailsDesignerClientPage.detailsLink.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerClientPage.designsLink.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerClientPage.filesLink.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerClientPage.contactUsLink.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "finishedCompetitionsDetailsTest" })
	public void showClientAgreementViewTest() {
		competitionDetailsDesignerClientPage = new CompetitionDetailsDesignerPage(driver);
		competitionDetailsDesignerClientPage.openFiles();
		Assert.assertTrue(competitionDetailsDesignerClientPage.filesHeader.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerClientPage.showAgreementLink.isDisplayed());
		competitionDetailsDesignerClientPage.showAgreementFun();
		Assert.assertTrue(competitionDetailsDesignerClientPage.agreementPopup.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerClientPage.designerSig.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerClientPage.cliendSig.isDisplayed());
		competitionDetailsDesignerClientPage.closeFun();
		Assert.assertTrue(competitionDetailsDesignerClientPage.detailsLink.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "showClientAgreementViewTest" })
	public void makeLogoutTest() throws AWTException {
		homeClientPage = new HomeClientPage(driver);
		defaultClientPage = new DefaultClientPage(driver);
		homeClientPage.openMainMenuFun();
		homeClientPage.logoutFun();
		Assert.assertTrue(defaultClientPage.loginLinkCli.isDisplayed());
	}
}
