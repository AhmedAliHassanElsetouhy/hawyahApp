package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.ExcelReader;
import pages.DefaultPage;

public class JobsTest extends TestBase {

	DefaultPage defaultPage;

	@Test(priority = 1)
	public void jobsTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultPage(driver);
		defaultPage.openJobsPage();
		Assert.assertTrue(defaultPage.jobsLink.isDisplayed());
	}
}
