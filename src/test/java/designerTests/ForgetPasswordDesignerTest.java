package designerTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.RegistrationClientPage;
import clientPages.ConfirmPasswordClientPage;
import clientPages.DefaultPage;
import clientPages.ForgetPasswordClientPage;
import clientPages.HomeClientPage;
import clientPages.LoginPage;
import clientTests.TestBase;
import data.ExcelReader;

public class ForgetPasswordDesignerTest extends TestBase {

	Faker fakeData = new Faker();
	HomeClientPage homePage;
	ForgetPasswordClientPage forgetPasswordPage;
	DefaultPage defaultPage;
	LoginPage loginPage;
	ConfirmPasswordClientPage confirmPasswordPage;
	RegistrationClientPage registrationPage;
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
		forgetPasswordPage = new ForgetPasswordClientPage(driver);
		loginPage.openForgetPassPageFun();
		Assert.assertTrue(forgetPasswordPage.resetPassBtnCli.isDisplayed());
	}

	@Test(priority = 3, dependsOnMethods = { "openForgetPassPageTest" })
	public void invalidDataForgetPasswordFormTest() throws AWTException {
		forgetPasswordPage = new ForgetPasswordClientPage(driver);
		forgetPasswordPage.forgetPassFun(invalidEmail);
		System.out.println(forgetPasswordPage.invalidForgetPassEmailMsgCli.getText());
		Assert.assertTrue(forgetPasswordPage.invalidForgetPassEmailMsgCli.getText()
				.contains("البريد الإلكتروني المدخل غير صحيح. الرجاء إعادة المحاولة"));
		forgetPasswordPage.emailTxtBoxCli.clear();
	}

	@Test(priority = 4, dependsOnMethods = { "invalidDataForgetPasswordFormTest" })
	public void forgetPasswordFormTest() throws IOException {
		forgetPasswordPage = new ForgetPasswordClientPage(driver);
		confirmPasswordPage = new ConfirmPasswordClientPage(driver);
		ExcelReader ER = new ExcelReader();
		forgetPasswordPage.forgetPassFun(ER.getExcelData(5, 2)[1][1]);
		Assert.assertTrue(confirmPasswordPage.confirmPassMsg.getText()
				.contains("الرجاء اتباع التعليمات الموضحة في بريدك الالكتروني"));
	}

	@Test(priority = 5, dependsOnMethods = { "forgetPasswordFormTest" })
	public void openLoginFormTest() {
		loginPage = new LoginPage(driver);
		confirmPasswordPage = new ConfirmPasswordClientPage(driver);
		confirmPasswordPage.openLoginFormFun();
		Assert.assertTrue(loginPage.forgetPassLink.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "openLoginFormTest" })
	public void openRegisterFormTest() {
		loginPage = new LoginPage(driver);
		registrationPage = new RegistrationClientPage(driver);
		loginPage.openRegisterFormFun();
		Assert.assertTrue(registrationPage.registerFormHeaderTxtCli.isDisplayed());
	}
}