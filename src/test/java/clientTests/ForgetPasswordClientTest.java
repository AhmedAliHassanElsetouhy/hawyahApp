package clientTests;

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
import data.ExcelReader;

public class ForgetPasswordClientTest extends TestBase {

	Faker fakeData = new Faker();
	HomeClientPage homeClientPage;
	ForgetPasswordClientPage forgetPasswordClientPage;
	DefaultClientPage defaultClientPage;
	LoginClientPage loginClientPage;
	ConfirmPasswordClientPage confirmPasswordClientPage;
	RegistrationClientPage registrationClientPage;
	String invalidEmailCli = fakeData.internet().emailAddress();

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
	public void openForgetPassPageTest() {
		defaultClientPage = new DefaultClientPage(driver);
		loginClientPage = new LoginClientPage(driver);
		forgetPasswordClientPage = new ForgetPasswordClientPage(driver);
		loginClientPage.openForgetPassPageFun();
		Assert.assertTrue(forgetPasswordClientPage.resetPassBtnCli.isDisplayed());
	}

	@Test(priority = 3, dependsOnMethods = { "openForgetPassPageTest" })
	public void invalidDataForgetPasswordFormTest() throws AWTException {
		forgetPasswordClientPage = new ForgetPasswordClientPage(driver);
		forgetPasswordClientPage.forgetPassFun(invalidEmailCli);
		System.out.println(forgetPasswordClientPage.invalidForgetPassEmailMsgCli.getText());
		Assert.assertTrue(forgetPasswordClientPage.invalidForgetPassEmailMsgCli.getText()
				.contains("البريد الإلكتروني المدخل غير صحيح. الرجاء إعادة المحاولة"));
		forgetPasswordClientPage.emailTxtBoxCli.clear();
	}

	@Test(priority = 4, dependsOnMethods = { "invalidDataForgetPasswordFormTest" })
	public void forgetPasswordFormTest() throws IOException {
		forgetPasswordClientPage = new ForgetPasswordClientPage(driver);
		confirmPasswordClientPage = new ConfirmPasswordClientPage(driver);
		ExcelReader ER = new ExcelReader();
		forgetPasswordClientPage.forgetPassFun(ER.getExcelData(0, 2)[1][1]);
		Assert.assertTrue(confirmPasswordClientPage.confirmPassMsg.getText()
				.contains("الرجاء اتباع التعليمات الموضحة في بريدك الالكتروني"));
	}

	@Test(priority = 5, dependsOnMethods = { "forgetPasswordFormTest" })
	public void openLoginFormTest() {
		loginClientPage = new LoginClientPage(driver);
		confirmPasswordClientPage = new ConfirmPasswordClientPage(driver);
		confirmPasswordClientPage.openLoginFormFun();
		Assert.assertTrue(loginClientPage.forgetPassLinkCli.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "openLoginFormTest" })
	public void openRegisterFormTest() {
		loginClientPage = new LoginClientPage(driver);
		registrationClientPage = new RegistrationClientPage(driver);
		loginClientPage.openRegisterFormFun();
		Assert.assertTrue(registrationClientPage.registerFormHeaderTxtCli.isDisplayed());
	}

}