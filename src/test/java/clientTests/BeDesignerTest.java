package clientTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import clientPages.DefaultPage;
import data.ExcelReader;

public class BeDesignerTest extends TestBase {

	DefaultPage defaultPage;

	@Test(priority = 1)
	public void beDesignerTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultPage(driver);
		defaultPage.openBeDesignerPage();
		Assert.assertTrue(defaultPage.beDesignerLink.isDisplayed());
	}
}
