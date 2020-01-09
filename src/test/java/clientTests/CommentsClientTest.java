package clientTests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.CompetitionsClientPage;
import clientPages.CompetitionsDetailsClientPage;
import clientPages.DefaultClientPage;
import clientPages.HomeClientPage;
import clientPages.LoginClientPage;
import clientTests.TestBase;
import data.ExcelReader;

public class CommentsClientTest extends TestBase {

	DefaultClientPage defaultClientPage;
	LoginClientPage loginClientPage;
	HomeClientPage homeClientPage;
	CompetitionsClientPage competitionsClientPage;
	int compititionItem = 1;
	CompetitionsDetailsClientPage competitionsDetailsClientPage;
	Faker fakeData = new Faker();
	String messageCli = fakeData.name().title();
	JavascriptExecutor jse;
	int index = 0;

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
		loginClientPage.loginFun(ER.getExcelData(5, 2)[1][1], ER.getExcelData(5, 2)[2][1]);
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
	public void recieveDesignsCompetitionsDetailsTest() {
		competitionsClientPage = new CompetitionsClientPage(driver);
		competitionsDetailsClientPage = new CompetitionsDetailsClientPage(driver);
		competitionsClientPage.openFinishedFun(compititionItem);
		Assert.assertTrue(competitionsDetailsClientPage.detailsLinkCli.isDisplayed());
		Assert.assertTrue(competitionsDetailsClientPage.designsLinkCli.isDisplayed());
		Assert.assertTrue(competitionsDetailsClientPage.filesLinkCli.isDisplayed());
		Assert.assertTrue(competitionsDetailsClientPage.contactUsLinkCLi.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "recieveDesignsCompetitionsDetailsTest" })
	public void commentsTest() throws AWTException {
		competitionsDetailsClientPage = new CompetitionsDetailsClientPage(driver);
		competitionsDetailsClientPage.openDesignsFun();
		competitionsDetailsClientPage.openCardFun(index);
		Assert.assertTrue(competitionsDetailsClientPage.rateCli.isDisplayed());
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				competitionsDetailsClientPage.closeCommentViewCli);
	}

	@Test(priority = 6, dependsOnMethods = { "commentsTest" })
	public void makeLogoutTest() throws AWTException {
		homeClientPage = new HomeClientPage(driver);
		defaultClientPage = new DefaultClientPage(driver);
		homeClientPage.openMainMenuFun();
		homeClientPage.logoutFun();
		Assert.assertTrue(defaultClientPage.loginLinkCli.isDisplayed());
	}
}
