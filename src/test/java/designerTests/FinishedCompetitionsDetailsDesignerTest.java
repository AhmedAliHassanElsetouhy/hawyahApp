package designerTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.CompetitionsPage;
import clientPages.DefaultPage;
import clientPages.HomePage;
import clientPages.LoginPage;
import clientTests.TestBase;
import data.ExcelReader;
import designerPages.CompetitionDetailsDesignerPage;

public class FinishedCompetitionsDetailsDesignerTest extends TestBase {

	DefaultPage defaultPage;
	LoginPage loginPage;
	HomePage homePage;
	CompetitionsPage competitionsPage;
	int compititionItem = 1;
	CompetitionDetailsDesignerPage competitionDetailsDesignerPage;
	Faker fakeData = new Faker();
	String message = fakeData.name().title();

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
		loginPage.loginFun(ER.getExcelData(5, 2)[1][1], ER.getExcelData(5, 2)[2][1]);
		// loginPage.submitLoginFun();
		System.out.println(homePage.loginConfirmMsg.getText());
		Assert.assertTrue(homePage.loginConfirmMsg.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openCompetitionsTest() {
		homePage = new HomePage(driver);
		competitionsPage = new CompetitionsPage(driver);
		homePage.openCompetitionFun();
		System.out.println(competitionsPage.competitions.getText());
	}

	@Test(priority = 4, dependsOnMethods = { "openCompetitionsTest" })
	public void finishedCompetitionsDetailsTest() {
		competitionsPage = new CompetitionsPage(driver);
		competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
		// competitionsPage.openCompetitionFun(compititionItem);
		competitionsPage.openFinishedFun(compititionItem);
		Assert.assertTrue(competitionDetailsDesignerPage.detailsLink.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.designsLink.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.filesLink.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.contactUsLink.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "openCompetitionsTest" })
	public void detailsTest() {
		competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
		competitionDetailsDesignerPage.openDetailsFun();
	}

	@Test(priority = 6, dependsOnMethods = { "openCompetitionsTest" })
	public void designsTest() {
		competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
		competitionDetailsDesignerPage.openDesignsFun();
	}

	@Test(priority = 7, dependsOnMethods = { "openCompetitionsTest" })
	public void filesTest() {
		competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
		competitionDetailsDesignerPage.openFilesAndOpenAgreementFun();
	}

	@Test(priority = 8, dependsOnMethods = { "openCompetitionsTest" })
	public void contactUsTest() {
		competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
		competitionDetailsDesignerPage.contactUsFun(message);
	}

	@Test(priority = 9, dependsOnMethods = { "openCompetitionsTest" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomePage(driver);
		defaultPage = new DefaultPage(driver);
		homePage.openMainMenuFun();
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginLink.isDisplayed());
	}
}
