package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DefaultPage;

public class PrivacyTest extends TestBase{

	DefaultPage defaultPage;

	@Test(priority = 1)
	public void privacyTest() {
		defaultPage = new DefaultPage(driver);
		defaultPage.openPrivacyPage();
		Assert.assertTrue(defaultPage.privacyLink.isDisplayed());
	}
}
