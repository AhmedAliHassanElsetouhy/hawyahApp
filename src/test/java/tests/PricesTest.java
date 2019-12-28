package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DefaultPage;

public class PricesTest extends TestBase {

	DefaultPage defaultPage;

	@Test(priority = 1)
	public void pricesTest() {
		defaultPage = new DefaultPage(driver);
		defaultPage.openPricesPage();
		Assert.assertTrue(defaultPage.pricesLink.isDisplayed());
	}
}
