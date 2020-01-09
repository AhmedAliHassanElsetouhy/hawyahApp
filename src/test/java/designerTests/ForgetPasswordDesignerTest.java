package designerTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.RegistrationClientPage;
import clientPages.ConfirmPasswordClientPage;
import clientPages.DefaultClientPage;
import clientPages.ForgetPasswordClientPage;
import clientPages.HomeClientPage;
import clientPages.LoginClientPage;
import clientTests.TestBase;
import data.ExcelReader;

public class ForgetPasswordDesignerTest extends TestBase {

	Faker fakeData = new Faker();
	HomeClientPage homePage;
	ForgetPasswordClientPage forgetPasswordPage;
	DefaultClientPage defaultPage;
	LoginClientPage loginPage;
	ConfirmPasswordClientPage confirmPasswordPage;
	RegistrationClientPage registrationPage;
	String invalidEmail = fakeData.internet().emailAddress();

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
	public void openForgetPassPageTest() {
		defaultPage = new DefaultClientPage(driver);
		loginPage = new LoginClientPage(driver);
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
		loginPage = new LoginClientPage(driver);
		confirmPasswordPage = new ConfirmPasswordClientPage(driver);
		confirmPasswordPage.openLoginFormFun();
		Assert.assertTrue(loginPage.forgetPassLinkCli.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "openLoginFormTest" })
	public void openRegisterFormTest() {
		loginPage = new LoginClientPage(driver);
		registrationPage = new RegistrationClientPage(driver);
		loginPage.openRegisterFormFun();
		Assert.assertTrue(registrationPage.registerFormHeaderTxtCli.isDisplayed());
	}
}