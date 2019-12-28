package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pages.AboutMePage;
import pages.DefaultPage;
import pages.DesignsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MessageFormPage;
import pages.MessagesPage;
import pages.MyPagePage;

public class MessagesTest extends TestBase {
	DefaultPage defaultPage;
	LoginPage loginPage;
	HomePage homePage;
	DesignsPage designsPage;
	String email = "ahmed.ali.rooya@gmail.com";
	String password = "11111111";
	MyPagePage myPagePage;
	AboutMePage aboutMePage;
	Faker fakeData = new Faker();
	String fName = fakeData.name().firstName();
	String lName = fakeData.name().lastName();
	String city = fakeData.nation().capitalCity();
	String phone = fakeData.phoneNumber().cellPhone();
	String cityOption = "إنغولا";
	String currentPass = "11111111";
	String newPass = "11111111";
	String confirmNewPass = "11111111";
	MessagesPage messagePage;
	MessageFormPage messageFormPage;
	String body = "Body Body Body";
	String title = "Title";

	@Test(priority = 1)
	public void openHomePageTest() {
		defaultPage = new DefaultPage(driver);
		loginPage = new LoginPage(driver);
		defaultPage.openLoginForm();
		Assert.assertTrue(loginPage.forgetPassLink.isDisplayed());
	}

	@Test(priority = 2, dependsOnMethods = { "openHomePageTest" })
	public void loginFun() {
		loginPage = new LoginPage(driver);
		defaultPage = new DefaultPage(driver);
		homePage = new HomePage(driver);
		loginPage.loginFun(email, password);
		System.out.println(homePage.loginConfirmMsg.getText());
		Assert.assertTrue(homePage.loginConfirmMsg.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openMyPageTest() {
		homePage = new HomePage(driver);
		myPagePage = new MyPagePage(driver);
		messagePage = new MessagesPage(driver);
		messageFormPage = new MessageFormPage(driver);
		homePage.openMainMenuFun();
		homePage.openMessagesFun();
		messagePage.openAddMessageFun();
		Assert.assertTrue(messageFormPage.peopleIcon.isDisplayed());
	}

	@Test(priority = 4, dependsOnMethods = { "openMyPageTest" })
	public void openEditMyAccountDataTest() {
		myPagePage = new MyPagePage(driver);
		aboutMePage = new AboutMePage(driver);
		messageFormPage.openAdminListFun();
		messageFormPage.selectAdmin1Fun();
		messageFormPage.sendMsgFun(body, title);
		Assert.assertTrue(messageFormPage.newMsgIcon.isDisplayed());
	}
}
