package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DefaultPage;

public class ContactUsTest extends TestBase {

	DefaultPage defaultPage;

	@Test(priority = 1)
	public void contactUsTest() {
		defaultPage = new DefaultPage(driver);
		defaultPage.openContactUsPage();
		Assert.assertTrue(defaultPage.contactUsLink.isDisplayed());
	}
}
