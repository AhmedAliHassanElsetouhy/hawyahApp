package designerTests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.CompetitionsClientPage;
import clientPages.DefaultClientPage;
import clientPages.HomeClientPage;
import clientPages.LoginClientPage;
import clientTests.TestBase;
import data.ExcelReader;
import designerPages.CompetitionDetailsDesignerPage;

public class UploadDesignForRecieveDesignsCompetitionsDesignerTest extends TestBase {

	DefaultClientPage defaultPage;
	LoginClientPage loginPage;
	HomeClientPage homePage;
	CompetitionsClientPage competitionsPage;
	int compititionItem = 1;
	CompetitionDetailsDesignerPage competitionDetailsDesignerPage;
	Faker fakeData = new Faker();
	String message = fakeData.name().title();
	JavascriptExecutor jse;

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
		System.out.println(homePage.loginConfirmMsgCli.getText());
		Assert.assertTrue(homePage.loginConfirmMsgCli.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openCompetitionsTest() {
		homePage = new HomeClientPage(driver);
		competitionsPage = new CompetitionsClientPage(driver);
		homePage.openCompetitionFun();
	}

	@Test(priority = 4, dependsOnMethods = { "openCompetitionsTest" })
	public void recieveDesignsCompetitionsDetailsTest() {
		competitionsPage = new CompetitionsClientPage(driver);
		competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
		competitionsPage.openRecievedDesignsCompetitionsFun(compititionItem);
		Assert.assertTrue(competitionDetailsDesignerPage.detailsLink.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.designsLink.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.uploadDesignLink.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.contactUsLink.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "recieveDesignsCompetitionsDetailsTest" })
	public void uploadDesignTest() throws InterruptedException, AWTException, IOException {
		competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
		ExcelReader ER = new ExcelReader();
		competitionDetailsDesignerPage.openUploadDesignFun();
		// competitionDetailsDesignerPage.uploadDesignFileFun(ER.getExcelData(9,
		// 2)[0][1]);
		competitionDetailsDesignerPage.selectFileBtn
				.sendKeys(System.getProperty("user.dir") + "\\Uploads\\" + ER.getExcelData(9, 2)[0][1]);
		Assert.assertTrue(competitionDetailsDesignerPage.fileSize.getText().contains("KB"));
	}

	@Test(priority = 6, dependsOnMethods = { "uploadDesignTest" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomeClientPage(driver);
		defaultPage = new DefaultClientPage(driver);
		homePage.openMainMenuFun();
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginLinkCli.isDisplayed());
	}
}
