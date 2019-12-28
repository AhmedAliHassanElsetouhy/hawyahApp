package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DefaultPage;
import pages.HomePage;

public class AskDesignTest extends TestBase {

	DefaultPage defaultPage;
	HomePage homePage;

	@Test(priority = 1)
	public void askDesignTest() {
		driver.navigate().to("https://hawyah-dev.herokuapp.com/?locale=ar");
		defaultPage = new DefaultPage(driver);
		homePage = new HomePage(driver);
		defaultPage.openAskDesignPage();
		Assert.assertTrue(homePage.wantedDesignHeaderMsg.isDisplayed());
	}
}