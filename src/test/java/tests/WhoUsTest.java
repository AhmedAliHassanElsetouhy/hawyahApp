package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DefaultPage;

public class WhoUsTest extends TestBase {

	DefaultPage defaultPage;

	@Test(priority = 1)
	public void whoUsTest() {
		defaultPage = new DefaultPage(driver);
		defaultPage.openWhoUsPage();
		Assert.assertTrue(defaultPage.whoUsLink.isDisplayed());
	}
}
