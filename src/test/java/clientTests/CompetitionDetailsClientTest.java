package clientTests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import clientPages.CompetitionsClientPage;
import clientPages.DefaultPage;
import clientPages.HomeClientPage;
import clientPages.LoginPage;
import clientTests.TestBase;
import data.ExcelReader;
import designerPages.CompetitionDetailsDesignerPage;

public class CompetitionDetailsClientTest extends TestBase {

	DefaultPage defaultClientPage;
	LoginPage loginClientPage;
	HomeClientPage homeClientPage;
	CompetitionsClientPage competitionsClientPage;
	int compititionItem = 1;
	CompetitionDetailsDesignerPage competitionDetailsDesignerClientPage;
	JavascriptExecutor jse;

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
		// loginPage.submitLoginFun();
		System.out.println(homeClientPage.loginConfirmMsgCli.getText());
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
	public void openDeliverFinalWorkCompetitionTest() {
		competitionsClientPage = new CompetitionsClientPage(driver);
		competitionDetailsDesignerClientPage = new CompetitionDetailsDesignerPage(driver);
		competitionsClientPage.openDeliverFinalWorkCompetitionFun(compititionItem);
		Assert.assertTrue(competitionDetailsDesignerClientPage.detailsLinkDes.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerClientPage.designsLinkDes.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerClientPage.filesLinkDes.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerClientPage.contactUsLinkDes.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "openDeliverFinalWorkCompetitionTest" })
	public void detailsTest() throws InterruptedException, AWTException, IOException {
		competitionDetailsDesignerClientPage = new CompetitionDetailsDesignerPage(driver);
		competitionDetailsDesignerClientPage.openDetailsFun();
		Assert.assertTrue(competitionDetailsDesignerClientPage.catDes.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerClientPage.descDes.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerClientPage.ideaDes.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "detailsTest" })
	public void makeLogoutTest() throws AWTException {
		homeClientPage = new HomeClientPage(driver);
		defaultClientPage = new DefaultPage(driver);
		homeClientPage.openMainMenuFun();
		homeClientPage.logoutFun();
		Assert.assertTrue(defaultClientPage.loginLink.isDisplayed());
	}
}
