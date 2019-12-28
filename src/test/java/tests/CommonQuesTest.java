package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DefaultPage;

public class CommonQuesTest extends TestBase {
	DefaultPage defaultPage;

	@Test(priority = 1)
	public void commonQuesTest() {
		defaultPage = new DefaultPage(driver);
		defaultPage.openCommonQuesPage();
		Assert.assertTrue(defaultPage.commonQuesLink.isDisplayed());
	}
}
