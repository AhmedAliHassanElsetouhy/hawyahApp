package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.ExcelReader;
import pages.DefaultPage;

public class ContactUsTest extends TestBase {

	DefaultPage defaultPage;

	@Test(priority = 1)
	public void contactUsTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultPage(driver);
		defaultPage.openContactUsPage();
		Assert.assertTrue(defaultPage.contactUsLink.isDisplayed());
	}
}
