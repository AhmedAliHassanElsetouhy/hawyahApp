package footerPagesTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import clientPages.DefaultClientPage;
import clientTests.TestBase;
import data.ExcelReader;

public class PrivacyTest extends TestBase{

	DefaultClientPage defaultPage;

	@Test(priority = 1)
	public void privacyTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultClientPage(driver);
		defaultPage.openPrivacyPage();
		Assert.assertTrue(defaultPage.privacyLinkCli.isDisplayed());
	}
}
