package designerTests;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.CompetitionsClientPage;
import clientPages.DefaultPage;
import clientPages.HomeClientPage;
import clientPages.LoginPage;
import clientTests.TestBase;
import data.ExcelReader;
import designerPages.CompetitionDetailsDesignerPage;

public class AddCommentsDesignerTest extends TestBase {

	DefaultPage defaultPage;
	LoginPage loginPage;
	HomeClientPage homePage;
	CompetitionsClientPage competitionsPage;
	int compititionItem = 1;
	CompetitionDetailsDesignerPage competitionDetailsDesignerPage;
	Faker fakeData = new Faker();
	String messageDes = fakeData.name().title();
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
		Assert.assertTrue(competitionDetailsDesignerPage.detailsLinkDes.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.designsLinkDes.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.uploadDesignLinkDes.isDisplayed());
		Assert.assertTrue(competitionDetailsDesignerPage.contactUsLinkDes.isDisplayed());
	}

	int index = 0;
	String commentDes = fakeData.name().title();

	@Test(priority = 5, dependsOnMethods = { "recieveDesignsCompetitionsDetailsTest" })
	public void commentsTest() throws AWTException {
		competitionDetailsDesignerPage = new CompetitionDetailsDesignerPage(driver);
		competitionDetailsDesignerPage.openDesignsFun();
		competitionDetailsDesignerPage.openCardFun(index);
		Assert.assertTrue(competitionDetailsDesignerPage.addCommentHeaderDes.isDisplayed());
		competitionDetailsDesignerPage.addCommentFun(commentDes);
		// WebElement x =
		// competitionDetailsDesignerPage.comments.get(competitionDetailsDesignerPage.comments.size());
		// System.out.println(x);
		// Create object of SimpleDateFormat class and decide the format
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		// get current date time with Date()
		Date date = new Date();
		// Now format the date
		String date1Des = dateFormat.format(date);
		System.out.println(date1Des);
		System.out.println(competitionDetailsDesignerPage.commentListDes.getText()
				+ "Waiting untill updating the issue of time region");
		Assert.assertTrue(competitionDetailsDesignerPage.commentListDes.getText().contains(date1Des));

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

	@Test(priority = 6, dependsOnMethods = { "commentsTest" })
	public void makeLogoutTest() throws AWTException {
		homePage = new HomeClientPage(driver);
		defaultPage = new DefaultPage(driver);
		homePage.openMainMenuFun();
		homePage.logoutFun();
		Assert.assertTrue(defaultPage.loginLink.isDisplayed());
	}
}
