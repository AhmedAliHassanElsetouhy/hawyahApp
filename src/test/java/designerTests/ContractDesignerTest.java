package designerTests;

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

public class ContractDesignerTest extends TestBase {
	DefaultPage defaultPage;
	LoginPage loginPage;
	HomeClientPage homePage;
	CompetitionsClientPage competitionsPage;
	int compititionItem = 1;
	CompetitionDetailsDesignerPage competitionDetailsDesignerPage;

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
		Assert.assertTrue(competitionDetailsDesignerPage.detailsLinkDes.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.designsLinkDes.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.filesLinkDes.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.contactUsLinkDes.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "deliverFinalWorkCompetitionTest" })
	public void uploadFinalDesignFun() throws InterruptedException, AWTException, IOException {
		competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
		competitionDetailsDesignerPage.openFiles();
		Assert.assertTrue(competitionDetailsDesignerPage.filesHeaderDes.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.showAgreementLinkDes.isDisplayed());
		competitionDetailsDesignerPage.showAgreementFun();
		Assert.assertTrue(competitionDetailsDesignerPage.agreementPopupDes.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.designerSig.isDisplayed());
		// competitionDetailsDesignerPage.closeFun();

		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				competitionDetailsDesignerPage.closeBtnDes);

	}

	@Test(priority = 6, dependsOnMethods = { "uploadFinalDesignFun" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomeClientPage(driver);
		defaultPage = new DefaultPage(driver);
		homePage.openMainMenuFun();
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginLink.isDisplayed());
	}
}
