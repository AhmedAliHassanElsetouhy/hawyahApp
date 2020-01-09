package designerTests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.RegistrationClientPage;
import clientPages.DefaultPage;
import clientPages.HomeClientPage;
import clientPages.RegistrationConfirmationClientPage;
import clientTests.TestBase;
import data.ExcelReader;

public class RegistrationDesignerTest extends TestBase {

	RegistrationClientPage registrationPage;
	DefaultPage defaultPage;
	HomeClientPage homePage;
	RegistrationConfirmationClientPage registerConfirmPage;

	Faker fake = new Faker();
	String userName = fake.name().firstName() + fake.number().numberBetween(1, 99);
	String email = fake.internet().emailAddress();
	String password = fake.number().digits(8);

	@Test(priority = 1)
	public void openRegisterFormPageTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultPage(driver);
		registrationPage = new RegistrationClientPage(driver);
		defaultPage.openRegisterFormFun();
		Assert.assertTrue(registrationPage.registerFormHeaderTxtCli.getText().contains("إنشاء حساب في هوية"));
	}

	@Test(priority = 2, dependsOnMethods = { "openRegisterFormPageTest" })
	public void emptyRegisterFormPageTest() {
		defaultPage = new DefaultPage(driver);
		registrationPage = new RegistrationClientPage(driver);
		registrationPage.submitRegisterfun();
		Assert.assertTrue(registrationPage.registerFormHeaderTxtCli.getText().contains("إنشاء حساب في هوية"));
	}

	@Test(priority = 3, dependsOnMethods = { "emptyRegisterFormPageTest" })
	public void invalidRegisterFormPageTest() throws AWTException {
		defaultPage = new DefaultPage(driver);
		registrationPage = new RegistrationClientPage(driver);
		registrationPage.registerFun(email, password, userName);
		registrationPage.checkTermsAndCondition();
		registrationPage.submitRegisterfun();
		Assert.assertTrue(registrationPage.userTypeValidationMsgCli.getText()
				.contains("يجب تحديد احد الخيارات ┊ احتاج الى تصميم ┊ انا مصمم/مصممة"));

	}

	@Test(priority = 4, dependsOnMethods = { "invalidRegisterFormPageTest" })
	public void designerRegisterFormPageTest() {
		defaultPage = new DefaultPage(driver);
		registrationPage = new RegistrationClientPage(driver);
		registerConfirmPage = new RegistrationConfirmationClientPage(driver);
		System.out.println(email);
		registrationPage.designerRegister();
		registrationPage.submitRegisterfun();
		System.out.println(registerConfirmPage.registerDesignerConfirmMsgCli.getText());
		Assert.assertTrue(registerConfirmPage.registerDesignerConfirmMsgCli.getText().contains("كمصمم"));
	}
}
