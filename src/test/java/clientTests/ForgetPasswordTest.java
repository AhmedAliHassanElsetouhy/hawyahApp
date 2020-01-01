package clientTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.ClientRegistrationPage;
import clientPages.ConfirmPasswordPage;
import clientPages.DefaultPage;
import clientPages.ForgetPasswordPage;
import clientPages.HomePage;
import clientPages.LoginPage;
import data.ExcelReader;

public class ForgetPasswordTest extends TestBase {

	Faker fakeData = new Faker();
	HomePage homePage;
	ForgetPasswordPage forgetPasswordPage;
	DefaultPage defaultPage;
	LoginPage loginPage;
	ConfirmPasswordPage confirmPasswordPage;
	ClientRegistrationPage registrationPage;
//	String email = "ahmed.ali.rooya@gmail.com";
//	String password = "11111111";
	String invalidEmail = fakeData.internet().emailAddress();

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
	public void openForgetPassPageTest() {
		defaultPage = new DefaultPage(driver);
		loginPage = new LoginPage(driver);
		forgetPasswordPage = new ForgetPasswordPage(driver);
		loginPage.openForgetPassPageFun();
		Assert.assertTrue(forgetPasswordPage.resetPassBtn.isDisplayed());
	}

	@Test(priority = 3, dependsOnMethods = { "openForgetPassPageTest" })
	public void invalidDataForgetPasswordFormTest() throws AWTException {
		forgetPasswordPage = new ForgetPasswordPage(driver);
		forgetPasswordPage.forgetPassFun(invalidEmail);
		// forgetPasswordPage.sendForgottenEmailBtnFun();
		System.out.println(forgetPasswordPage.invalidForgetPassEmailMsg.getText());
		Assert.assertTrue(forgetPasswordPage.invalidForgetPassEmailMsg.getText()
				.contains("البريد الإلكتروني المدخل غير صحيح. الرجاء إعادة المحاولة"));
		// forgetPasswordPage.refreshPage();
		forgetPasswordPage.emailTxtBox.clear();
	}

	@Test(priority = 4, dependsOnMethods = { "invalidDataForgetPasswordFormTest" })
	public void forgetPasswordFormTest() throws IOException {
		forgetPasswordPage = new ForgetPasswordPage(driver);
		confirmPasswordPage = new ConfirmPasswordPage(driver);
		ExcelReader ER = new ExcelReader();
		forgetPasswordPage.forgetPassFun(ER.getExcelData(0, 2)[1][1]);		
		// forgetPasswordPage.sendForgottenEmailBtnFun();
		Assert.assertTrue(confirmPasswordPage.confirmPassMsg.getText()
				.contains("الرجاء اتباع التعليمات الموضحة في بريدك الالكتروني"));
	}

	@Test(priority = 5, dependsOnMethods = { "forgetPasswordFormTest" })
	public void openLoginFormTest() {
		loginPage = new LoginPage(driver);
		confirmPasswordPage = new ConfirmPasswordPage(driver);
		confirmPasswordPage.openLoginFormFun();
		// forgetPasswordPage.sendForgottenEmailBtnFun();
		Assert.assertTrue(loginPage.forgetPassLink.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "openLoginFormTest" })
	public void openRegisterFormTest() {
		loginPage = new LoginPage(driver);
		registrationPage = new ClientRegistrationPage(driver);
		loginPage.openRegisterFormFun();
		// forgetPasswordPage.sendForgottenEmailBtnFun();
		Assert.assertTrue(registrationPage.registerFormHeaderTxt.isDisplayed());
	}

}