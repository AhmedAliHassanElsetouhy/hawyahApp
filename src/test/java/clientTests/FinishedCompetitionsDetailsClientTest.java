package clientTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import clientPages.CompetitionsClientPage;
import clientPages.DefaultPage;
import clientPages.HomeClientPage;
import clientPages.LoginPage;
import clientTests.TestBase;
import data.ExcelReader;
import designerPages.CompetitionDetailsDesignerPage;

public class FinishedCompetitionsDetailsClientTest extends TestBase {

	DefaultPage defaultClientPage;
	LoginPage loginClientPage;
	HomeClientPage homeClientPage;
	CompetitionsClientPage competitionsClientPage;
	int compititionItem = 1;
	CompetitionDetailsDesignerPage competitionDetailsDesignerClientPage;

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
	public void finishedCompetitionsDetailsTest() {
		competitionsClientPage = new CompetitionsClientPage(driver);
		competitionDetailsDesignerClientPage = new CompetitionDetailsDesignerPage(driver);
		competitionsClientPage.openFinishedFun(compititionItem);
		Assert.assertTrue(competitionDetailsDesignerClientPage.detailsLinkDes.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerClientPage.designsLinkDes.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerClientPage.filesLinkDes.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerClientPage.contactUsLinkDes.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "finishedCompetitionsDetailsTest" })
	public void showClientAgreementViewTest() {
		competitionDetailsDesignerClientPage = new CompetitionDetailsDesignerPage(driver);
		competitionDetailsDesignerClientPage.openFiles();
		Assert.assertTrue(competitionDetailsDesignerClientPage.filesHeaderDes.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerClientPage.showAgreementLinkDes.isDisplayed());
		competitionDetailsDesignerClientPage.showAgreementFun();
		Assert.assertTrue(competitionDetailsDesignerClientPage.agreementPopupDes.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerClientPage.designerSig.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerClientPage.cliendSig.isDisplayed());
		competitionDetailsDesignerClientPage.closeFun();
		Assert.assertTrue(competitionDetailsDesignerClientPage.detailsLinkDes.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "showClientAgreementViewTest" })
	public void makeLogoutTest() throws AWTException {
		homeClientPage = new HomeClientPage(driver);
		defaultClientPage = new DefaultPage(driver);
		homeClientPage.openMainMenuFun();
		homeClientPage.logoutFun();
		Assert.assertTrue(defaultClientPage.loginLink.isDisplayed());
	}
}
