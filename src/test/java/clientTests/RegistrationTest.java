package clientTests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Locale;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.ClientRegistrationPage;
import clientPages.DefaultPage;
import clientPages.HomePage;
import clientPages.RegistrationConfirmationPage;
import data.ExcelReader;

public class RegistrationTest extends TestBase {

	ClientRegistrationPage registrationPage;
	DefaultPage defaultPage;
	HomePage homePage;
	RegistrationConfirmationPage registerConfirmPage;

	Faker fakeData = new Faker(new Locale("ar"));
	
	Faker fake = new Faker();
	String userName = fake.name().firstName() + fake.number().numberBetween(1, 99);
	String email = fake.internet().emailAddress();
	String password = fake.number().digits(8);

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
	public void clientRegisterFormPageTest() {
		defaultPage = new DefaultPage(driver);
		registrationPage = new ClientRegistrationPage(driver);
		registerConfirmPage = new RegistrationConfirmationPage(driver);
		System.out.println(email);
		registrationPage.clientRegister();
		registrationPage.submitRegisterfun();
		Assert.assertTrue(registerConfirmPage.registerClientConfirmMsg.getText().contains("شكرا لتسجيلك في موقع هوية"));
	}
}
