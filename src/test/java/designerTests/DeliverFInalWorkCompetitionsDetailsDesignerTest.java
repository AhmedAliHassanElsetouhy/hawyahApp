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
import org.openqa.selenium.JavascriptExecutor;

public class DeliverFInalWorkCompetitionsDetailsDesignerTest extends TestBase {

	DefaultPage defaultPage;
	LoginPage loginPage;
	HomePage homePage;
	CompetitionsPage competitionsPage;
	int compititionItem = 1;
	CompetitionDetailsDesignerPage competitionDetailsDesignerPage;
	Faker fakeData = new Faker();
	String message = fakeData.name().title();
	String name = fakeData.name().fullName();
	String title = fakeData.name().title();
	String phone1 = fakeData.phoneNumber().cellPhone();
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
		loginPage.loginFun(ER.getExcelData(5, 2)[1][1], ER.getExcelData(5, 2)[2][1]);
		Assert.assertTrue(homePage.loginConfirmMsg.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openCompetitionsTest() {
		homePage = new HomePage(driver);
		competitionsPage = new CompetitionsPage(driver);
		homePage.openCompetitionFun();
	}

	@Test(priority = 4, dependsOnMethods = { "openCompetitionsTest" })
	public void deliverFinalWorkCompetitionTest() {
		competitionsPage = new CompetitionsPage(driver);
		competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
		competitionsPage.openDeliverFinalWorkCompetitionFun(compititionItem);
		Assert.assertTrue(competitionDetailsDesignerPage.detailsLink.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.designsLink.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.filesLink.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.contactUsLink.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "deliverFinalWorkCompetitionTest" })
	public void details_Designs_Test() throws InterruptedException, AWTException, IOException {
		competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
		competitionDetailsDesignerPage.openDetailsFun();
		competitionDetailsDesignerPage.openDesignsFun();
	}

	@Test(priority = 6, dependsOnMethods = { "details_Designs_Test" })
	public void uploadFinalDesign() throws InterruptedException, AWTException, IOException {
		competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
		ExcelReader ER = new ExcelReader();
		competitionDetailsDesignerPage.openFiles();
		competitionDetailsDesignerPage.uploadContract(ER.getExcelData(9, 2)[0][1]);
		competitionDetailsDesignerPage.openFilesAndOpenAgreementDesignerOnlyFun();
	}

	@Test(priority = 7, dependsOnMethods = { "uploadFinalDesign" })
	public void contactUsTest() {
		competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", competitionDetailsDesignerPage.contactUsLink);
		competitionDetailsDesignerPage.contactUsFun(message);
	}

	@Test(priority = 8, dependsOnMethods = { "contactUsTest" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomePage(driver);
		defaultPage = new DefaultPage(driver);
		homePage.openMainMenuFun();
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginLink.isDisplayed());
	}
}
