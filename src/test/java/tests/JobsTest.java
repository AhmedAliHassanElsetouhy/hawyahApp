package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DefaultPage;

public class JobsTest extends TestBase {

	DefaultPage defaultPage;

	@Test(priority = 1)
	public void jobsTest() {
		defaultPage = new DefaultPage(driver);
		defaultPage.openJobsPage();
		Assert.assertTrue(defaultPage.jobsLink.isDisplayed());
	}
}
