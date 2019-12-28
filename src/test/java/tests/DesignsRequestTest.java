package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DefaultPage;

public class DesignsRequestTest extends TestBase {

	DefaultPage defaultPage;

	@Test(priority = 1)
	public void designsRequestsTest() {
		defaultPage = new DefaultPage(driver);
		defaultPage.openDesignRequestsPage();
		Assert.assertTrue(defaultPage.designsReqLink.isDisplayed());
	}
}
