package clientTests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Locale;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.RegistrationClientPage;
import clientPages.DefaultPage;
import clientPages.HomeClientPage;
import clientPages.RegistrationConfirmationClientPage;
import data.ExcelReader;

public class RegistrationClientTest extends TestBase {

	RegistrationClientPage registrationClientPage;
	DefaultPage defaultClientPage;
	HomeClientPage homeClientPage;
	RegistrationConfirmationClientPage registerConfirmClientPage;

	Faker fakeData = new Faker(new Locale("ar"));
	Faker fake = new Faker();
	String userNameCli = fake.name().firstName() + fake.number().numberBetween(1, 99);
	String emailCli = fake.internet().emailAddress();
	String passwordCli = fake.number().digits(8);

	@Test(priority = 1)
	public void openRegisterFormPageTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultClientPage = new DefaultPage(driver);
		registrationClientPage = new RegistrationClientPage(driver);
		defaultClientPage.openRegisterFormFun();
		Assert.assertTrue(registrationClientPage.registerFormHeaderTxtCli.getText().contains("إنشاء حساب في هوية"));
	}

	@Test(priority = 2, dependsOnMethods = { "openRegisterFormPageTest" })
	public void emptyRegisterFormPageTest() {
		defaultClientPage = new DefaultPage(driver);
		registrationClientPage = new RegistrationClientPage(driver);
		registrationClientPage.submitRegisterfun();
		Assert.assertTrue(registrationClientPage.registerFormHeaderTxtCli.getText().contains("إنشاء حساب في هوية"));
	}

	@Test(priority = 3, dependsOnMethods = { "emptyRegisterFormPageTest" })
	public void invalidRegisterFormPageTest() throws AWTException {
		defaultClientPage = new DefaultPage(driver);
		registrationClientPage = new RegistrationClientPage(driver);
		registrationClientPage.registerFun(emailCli, passwordCli, userNameCli);
		registrationClientPage.checkTermsAndCondition();
		registrationClientPage.submitRegisterfun();
		Assert.assertTrue(registrationClientPage.userTypeValidationMsgCli.getText()
				.contains("يجب تحديد احد الخيارات ┊ احتاج الى تصميم ┊ انا مصمم/مصممة"));

	}

	@Test(priority = 4, dependsOnMethods = { "invalidRegisterFormPageTest" })
	public void clientRegisterFormPageTest() {
		defaultClientPage = new DefaultPage(driver);
		registrationClientPage = new RegistrationClientPage(driver);
		registerConfirmClientPage = new RegistrationConfirmationClientPage(driver);
		System.out.println(emailCli);
		registrationClientPage.clientRegister();
		registrationClientPage.submitRegisterfun();
		Assert.assertTrue(registerConfirmClientPage.registerClientConfirmMsgCli.getText().contains("شكرا لتسجيلك في موقع هوية"));
	}
}
