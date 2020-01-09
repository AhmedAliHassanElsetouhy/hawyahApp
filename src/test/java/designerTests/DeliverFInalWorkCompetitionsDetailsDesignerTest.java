package designerTests;

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

public class DeliverFinalWorkCompetitionsDetailsDesignerTest extends TestBase {

	DefaultClientPage defaultPage;
	LoginClientPage loginPage;
	HomeClientPage homePage;
	CompetitionsClientPage competitionsPage;
	int compititionItem = 1;
	CompetitionDetailsDesignerPage competitionDetailsDesignerPage;

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
		defaultPage = new DefaultClientPage(driver);
		homePage = new HomeClientPage(driver);
		ExcelReader ER = new ExcelReader();
		loginPage.loginFun(ER.getExcelData(5, 2)[1][1], ER.getExcelData(5, 2)[2][1]);
		Assert.assertTrue(homePage.loginConfirmMsgCli.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openCompetitionsTest() {
		homePage = new HomeClientPage(driver);
		competitionsPage = new CompetitionsClientPage(driver);
		homePage.openCompetitionFun();
	}

	@Test(priority = 4, dependsOnMethods = { "openCompetitionsTest" })
	public void deliverFinalWorkCompetitionTest() {
		competitionsPage = new CompetitionsClientPage(driver);
		competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
		competitionsPage.openDeliverFinalWorkCompetitionFun(compititionItem);
		Assert.assertTrue(competitionDetailsDesignerPage.detailsLink.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.designsLink.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.filesLink.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.contactUsLink.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "deliverFinalWorkCompetitionTest" })
	public void uploadFinalDesign() throws InterruptedException, AWTException, IOException {
		competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
		ExcelReader ER = new ExcelReader();
		competitionDetailsDesignerPage.openFiles();
		competitionDetailsDesignerPage.uploadFinalDesign(ER.getExcelData(9, 2)[0][1]);
		Assert.assertTrue(competitionDetailsDesignerPage.detailsLink.isDisplayed());
	}

	 @Test(priority = 6, dependsOnMethods = { "uploadFinalDesign" })
	 public void makeLogoutTest() throws AWTException {
	 homePage = new HomeClientPage(driver);
	 defaultPage = new DefaultClientPage(driver);
	 homePage.openMainMenuFun();
	 homePage.logoutFun();
	 Assert.assertTrue(defaultPage.loginLinkCli.isDisplayed());
	 }
}
