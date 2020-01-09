package clientTests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import clientPages.CompetitionsDetailsClientPage;
import clientPages.CompetitionsClientPage;
import clientPages.DefaultClientPage;
import clientPages.HomeClientPage;
import clientPages.LoginClientPage;
import clientTests.TestBase;
import data.ExcelReader;

public class MoveToRecieveDesignsCompetitionsClientTest extends TestBase {

	DefaultClientPage defaultClientPage;
	LoginClientPage loginClientPage;
	HomeClientPage homeClientPage;
	CompetitionsClientPage competitionsClientPage;
	int compititionItem = 1;
	JavascriptExecutor jse;
	CompetitionsDetailsClientPage competitionsDetailsClientPage;

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
	public void recieveDesignsCompetitionsDetailsTest() {
		competitionsClientPage = new CompetitionsClientPage(driver);
		competitionsDetailsClientPage = new CompetitionsDetailsClientPage(driver);
		competitionsClientPage.openRecievedDesignsCompetitionsFun(compititionItem);
		Assert.assertTrue(competitionsDetailsClientPage.detailsLinkCli.isDisplayed());
		Assert.assertTrue(competitionsDetailsClientPage.designsLinkCli.isDisplayed());
		Assert.assertTrue(competitionsDetailsClientPage.settingsLinkCli.isDisplayed());
		Assert.assertTrue(competitionsDetailsClientPage.contactUsLinkCLi.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "recieveDesignsCompetitionsDetailsTest" })
	public void uploadDesignTest() throws InterruptedException, AWTException, IOException {
		competitionsDetailsClientPage = new CompetitionsDetailsClientPage(driver);
		competitionsDetailsClientPage.openSettingsFun();
		Assert.assertTrue(competitionsDetailsClientPage.settingsLinkCli.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "uploadDesignTest" })
	public void makeLogoutTest() throws AWTException {
		homeClientPage = new HomeClientPage(driver);
		defaultClientPage = new DefaultClientPage(driver);
		homeClientPage.openMainMenuFun();
		homeClientPage.logoutFun();
		Assert.assertTrue(defaultClientPage.loginLinkCli.isDisplayed());
	}
}
