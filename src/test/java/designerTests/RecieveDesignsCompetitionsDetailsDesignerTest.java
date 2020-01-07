package designerTests;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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

public class RecieveDesignsCompetitionsDetailsDesignerTest extends TestBase {

	DefaultPage defaultPage;
	LoginPage loginPage;
	HomePage homePage;
	CompetitionsPage competitionsPage;
	int compititionItem = 1;
	CompetitionDetailsDesignerPage competitionDetailsDesignerPage;
	Faker fakeData = new Faker();
	String message = fakeData.name().title();
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
		// loginPage.submitLoginFun();
		System.out.println(homePage.loginConfirmMsg.getText());
		Assert.assertTrue(homePage.loginConfirmMsg.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openCompetitionsTest() {
		homePage = new HomePage(driver);
		competitionsPage = new CompetitionsPage(driver);
		homePage.openCompetitionFun();
		// System.out.println(competitionsPage.competitions.getText());
	}

	@Test(priority = 4, dependsOnMethods = { "openCompetitionsTest" })
	public void recieveDesignsCompetitionsDetailsTest() {
		competitionsPage = new CompetitionsPage(driver);
		competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
		// competitionsPage.openCompetitionFun(compititionItem);
		competitionsPage.openRecievedDesignsCompetitionsFun(compititionItem);
		Assert.assertTrue(competitionDetailsDesignerPage.detailsLink.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.designsLink.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.uploadDesignLink.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.contactUsLink.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "recieveDesignsCompetitionsDetailsTest" })
	public void detailsTest() {
		competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
		competitionDetailsDesignerPage.openDetailsFun();
	}

	int index = 0;
	String comment = fakeData.name().title();

	@Test(priority = 6, dependsOnMethods = { "detailsTest" })
	public void designs_Comments_Test() {
		competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
		competitionDetailsDesignerPage.openDesignsFun();
		competitionDetailsDesignerPage.openCardFun(index);
		Assert.assertTrue(competitionDetailsDesignerPage.addCommentHeader.isDisplayed());
		competitionDetailsDesignerPage.addCommentFun(comment);

		// WebElement x =
		// competitionDetailsDesignerPage.comments.get(competitionDetailsDesignerPage.comments.size());
		// System.out.println(x);
		// Create object of SimpleDateFormat class and decide the format
//		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("dd, yyyy HH-2 :mm");
		// get current date time with Date()
		Date date = new Date();
		// Now format the date
		String date1 = dateFormat.format(date);
		System.out.println(date1);
		System.out.println(competitionDetailsDesignerPage.commentList.getText());
		Assert.assertTrue(competitionDetailsDesignerPage.commentList.getText().contains(date1));

		// Assert.assertTrue(x.getText().contains(date1));
		// if (commentsNum1 > commentsNum) {
		// jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].click();",
		// competitionDetailsDesignerPage.closeCommentView);
		// } else {
		// System.out.println("Not closed");
		// System.out.println("num1 = " + commentsNum);
		// System.out.println("num2 = " + commentsNum1);
		// }
	}

	// @Test(priority = 7, dependsOnMethods = { "designs_Comments_Test" })
	// public void uploadDesignTest() throws InterruptedException, AWTException,
	// IOException {
	// competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
	// ExcelReader ER = new ExcelReader();
	// competitionDetailsDesignerPage.openUploadDesignFun();
	// // competitionDetailsDesignerPage.uploadDesignFileFun(ER.getExcelData(9,
	// // 2)[0][1]);
	// competitionDetailsDesignerPage.selectFileBtn
	// .sendKeys(System.getProperty("user.dir") + "\\Uploads\\" + ER.getExcelData(9,
	// 2)[0][1]);
	// }
	//
	// @Test(priority = 8, dependsOnMethods = { "uploadDesignTest" })
	// public void contactUsTest() {
	// competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
	// jse = (JavascriptExecutor) driver;
	// jse.executeScript("arguments[0].click();",
	// competitionDetailsDesignerPage.contactUsLink);
	// competitionDetailsDesignerPage.contactUsFun(message);
	// }
	//
	// @Test(priority = 9, dependsOnMethods = { "contactUsTest" })
	// public void makeLogoutTest() throws AWTException {
	// homePage = new HomePage(driver);
	// defaultPage = new DefaultPage(driver);
	// homePage.openMainMenuFun();
	// homePage.logoutFun();
	// Assert.assertTrue(defaultPage.loginLink.isDisplayed());
	// }
}