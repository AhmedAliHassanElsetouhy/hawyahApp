package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import data.ExcelReader;
import pages.AboutMePage;
import pages.DefaultPage;
import pages.DesignsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyPagePage;

public class AboutMeTest extends TestBase {

	DefaultPage defaultPage;
	LoginPage loginPage;
	HomePage homePage;
	DesignsPage designsPage;
	// String email = "ahmed.ali.rooya@gmail.com";
	// String password = "11111111";
	MyPagePage myPagePage;
	AboutMePage aboutMePage;
	Faker fakeData = new Faker();
	String fName = fakeData.name().firstName();
	String lName = fakeData.name().lastName();
	String city = fakeData.nation().capitalCity();
	String phone = fakeData.phoneNumber().cellPhone();
	// String cityOption = "إنغولا";
	// String currentPass = "P@55word";
	// String newPass = "P@55word";
	// String confirmNewPass = "P@55word";

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
		loginPage.loginFun(ER.getExcelData(0, 2)[1][1], ER.getExcelData(0, 2)[2][1]);
		System.out.println(ER.getExcelData(0, 2)[1][1]);
		System.out.println(ER.getExcelData(0, 2)[2][1]);
		System.out.println(homePage.loginConfirmMsg.getText());
		Assert.assertTrue(homePage.loginConfirmMsg.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openMyPageTest() {
		homePage = new HomePage(driver);
		myPagePage = new MyPagePage(driver);
		homePage.openMainMenuFun();
		myPagePage.openMyPageFun();
		Assert.assertTrue(myPagePage.aboutMeLink.isDisplayed());
	}

	@Test(priority = 4, dependsOnMethods = { "openMyPageTest" })
	public void openEditMyAccountDataTest() {
		myPagePage = new MyPagePage(driver);
		aboutMePage = new AboutMePage(driver);
		myPagePage.openUpdateMyAccountPageFun();
		Assert.assertTrue(aboutMePage.personalDataLink.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "openEditMyAccountDataTest" })
	public void updatePersonalDataTest() throws IOException {
		homePage = new HomePage(driver);
		myPagePage = new MyPagePage(driver);
		aboutMePage = new AboutMePage(driver);
		ExcelReader ER = new ExcelReader();
		aboutMePage.personalDataForm(fName, lName, city, phone, ER.getExcelData(1, 2)[0][1]);
		aboutMePage.saveDataFun();
		Assert.assertTrue(myPagePage.myCompetitionsLink.isDisplayed());
		Assert.assertTrue(myPagePage.paymentsLink.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "updatePersonalDataTest" })
	public void updateLoginDataTest() throws IOException {
		homePage = new HomePage(driver);
		myPagePage = new MyPagePage(driver);
		aboutMePage = new AboutMePage(driver);
		ExcelReader ER = new ExcelReader();
		myPagePage.openUpdateMyAccountPageFun();
		myPagePage.openLoginDataFormPage();
		aboutMePage.updateLoginDataForm(ER.getExcelData(1, 2)[1][1], ER.getExcelData(1, 2)[2][1],
				ER.getExcelData(1, 2)[3][1]);
		System.out.println(ER.getExcelData(1, 2)[1][1]);
		System.out.println(ER.getExcelData(1, 2)[2][1]);
		System.out.println(ER.getExcelData(1, 2)[3][1]);
		aboutMePage.saveDataFun();
		Assert.assertTrue(myPagePage.myCompetitionsLink.isDisplayed());
		Assert.assertTrue(myPagePage.paymentsLink.isDisplayed());
	}

}
