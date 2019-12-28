package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DefaultPage;

public class BeDesignerTest extends TestBase {

	DefaultPage defaultPage;

	@Test(priority = 1)
	public void beDesignerTest() {
		defaultPage = new DefaultPage(driver);
		defaultPage.openBeDesignerPage();
		Assert.assertTrue(defaultPage.beDesignerLink.isDisplayed());
	}
}
