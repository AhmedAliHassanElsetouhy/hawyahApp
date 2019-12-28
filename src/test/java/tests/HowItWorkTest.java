package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DefaultPage;

public class HowItWorkTest extends TestBase {

	DefaultPage defaultPage;

	@Test(priority = 1)
	public void howItWorkTest() {
		defaultPage = new DefaultPage(driver);
		defaultPage.openHowItWorkPage();
		Assert.assertTrue(defaultPage.howItWorkLink.isDisplayed());
	}
}
