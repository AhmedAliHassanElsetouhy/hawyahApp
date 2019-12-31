package tests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import data.ExcelReader;
import pages.ClientRegistrationPage;
import pages.DefaultPage;
import pages.HomePage;
import pages.RegistrationConfirmationPage;

public class RegistrationUserTest extends TestBase {

	ClientRegistrationPage registrationPage;
	DefaultPage defaultPage;
	HomePage homePage;
	RegistrationConfirmationPage registerConfirmPage;

	Faker fake = new Faker();
	String userName = fake.name().firstName() + fake.number().numberBetween(1, 99);
	String email = fake.internet().emailAddress();
	String password = fake.number().digits(8);

	// String email = "ahmedalihassanelsetouhy@gmail.com";
	// String password = "11111111";

	@Test(priority = 1)
	public void openRegisterFormPageTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultPage(driver);
		registrationPage = new ClientRegistrationPage(driver);
		defaultPage.openRegisterFormFun();
		Assert.assertTrue(registrationPage.registerFormHeaderTxt.getText().contains("إنشاء حساب في هوية"));
	}

	@Test(priority = 2, dependsOnMethods = { "openRegisterFormPageTest" })
	public void emptyRegisterFormPageTest() {
		defaultPage = new DefaultPage(driver);
		registrationPage = new ClientRegistrationPage(driver);
		registrationPage.submitRegisterfun();
		Assert.assertTrue(registrationPage.registerFormHeaderTxt.getText().contains("إنشاء حساب في هوية"));
	}

	@Test(priority = 3, dependsOnMethods = { "emptyRegisterFormPageTest" })
	public void invalidRegisterFormPageTest() throws AWTException {
		defaultPage = new DefaultPage(driver);
		registrationPage = new ClientRegistrationPage(driver);
		registrationPage.registerFun(email, password, userName);
		registrationPage.checkTermsAndCondition();
		registrationPage.submitRegisterfun();
		Assert.assertTrue(registrationPage.userTypeValidationMsg.getText()
				.contains("يجب تحديد احد الخيارات ┊ احتاج الى تصميم ┊ انا مصمم/مصممة"));

	}

	@Test(priority = 4, dependsOnMethods = { "invalidRegisterFormPageTest" })
	public void designerRegisterFormPageTest() {
		defaultPage = new DefaultPage(driver);
		registrationPage = new ClientRegistrationPage(driver);
		registerConfirmPage = new RegistrationConfirmationPage(driver);
		System.out.println(email);
		registrationPage.designerRegister();
		registrationPage.submitRegisterfun();
		System.out.println(registerConfirmPage.registerDesignerConfirmMsg.getText());
		Assert.assertTrue(registerConfirmPage.registerDesignerConfirmMsg.getText().contains("كمصمم"));
	}
}
