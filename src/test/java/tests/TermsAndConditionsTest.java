package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DefaultPage;

public class TermsAndConditionsTest extends TestBase{

	DefaultPage defaultPage;

	@Test(priority = 1)
	public void termsAndConditionsTest() {
		defaultPage = new DefaultPage(driver);
		defaultPage.openRegisterFormFun();
		Assert.assertTrue(defaultPage.termsAndConditionsLink.isDisplayed());
	}
}
