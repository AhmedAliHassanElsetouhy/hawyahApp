package footerPagesTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import clientPages.DefaultPage;
import clientTests.TestBase;
import data.ExcelReader;

public class TermsAndConditionsTest extends TestBase {

	DefaultPage defaultPage;

	@Test(priority = 1)
	public void termsAndConditionsTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultPage(driver);
		defaultPage.openTermsAndConditionsPage();
		Assert.assertTrue(defaultPage.termsAndConditionsLink.isDisplayed());
	}
}
