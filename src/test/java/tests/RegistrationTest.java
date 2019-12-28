package tests;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pages.DefaultPage;
import pages.ClientRegistrationPage;

public class RegistrationTest extends TestBase {

	ClientRegistrationPage registrationPage;
	DefaultPage homePage;

	Faker fake = new Faker();
	String userName = fake.name().firstName() + fake.number().numberBetween(1, 99);
	String email = fake.internet().emailAddress();
	String password = "11111111";

	@Test(priority = 1)
	public void openRegisterFormPageTest() {
		homePage = new DefaultPage(driver);
		registrationPage = new ClientRegistrationPage(driver);
		homePage.openRegisterFormFun();
		Assert.assertTrue(registrationPage.registerFormHeaderTxt.getText().contains("إنشاء حساب في هوية"));
	}

	@Test(priority = 2, dependsOnMethods = { "openRegisterFormPageTest" })
	public void emptyRegisterFormPageTest() {
		homePage = new DefaultPage(driver);
		registrationPage = new ClientRegistrationPage(driver);
		registrationPage.submitRegisterfun();
		Assert.assertTrue(registrationPage.registerFormHeaderTxt.getText().contains("إنشاء حساب في هوية"));
	}

	@Test(priority = 3, dependsOnMethods = { "emptyRegisterFormPageTest" })
	public void invalidRegisterFormPageTest() throws AWTException {
		homePage = new DefaultPage(driver);
		registrationPage = new ClientRegistrationPage(driver);
		registrationPage.registerFun(email, password, userName);
		registrationPage.checkTermsAndCondition();
		registrationPage.submitRegisterfun();
		Assert.assertTrue(registrationPage.userTypeValidationMsg.getText()
				.contains("يجب تحديد احد الخيارات ┊ احتاج الى تصميم ┊ انا مصمم/مصممة"));

	}

	@Test(priority = 4, dependsOnMethods = { "invalidRegisterFormPageTest" })
	public void clientRegisterFormPageTest() {
		homePage = new DefaultPage(driver);
		registrationPage = new ClientRegistrationPage(driver);
		System.out.println(email);
		registrationPage.clientRegister();

		registrationPage.submitRegisterfun();
		Assert.assertTrue(registrationPage.userTypeValidationMsg.getText()
				.contains("يجب تحديد احد الخيارات ┊ احتاج الى تصميم ┊ انا مصمم/مصممة"));
	}
}
