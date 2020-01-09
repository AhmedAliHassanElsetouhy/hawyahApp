package footerPagesTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import clientPages.DefaultClientPage;
import clientTests.TestBase;
import data.ExcelReader;

public class BeDesignerTest extends TestBase {

	DefaultClientPage defaultPage;

	@Test(priority = 1)
	public void beDesignerTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultClientPage(driver);
		defaultPage.openBeDesignerPage();
		Assert.assertTrue(defaultPage.beDesignerLinkCli.isDisplayed());
	}
}
