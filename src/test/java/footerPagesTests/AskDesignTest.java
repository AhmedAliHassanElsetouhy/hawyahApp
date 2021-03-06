package footerPagesTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import clientPages.DefaultPage;
import clientPages.HomeClientPage;
import clientTests.TestBase;
import data.ExcelReader;

public class AskDesignTest extends TestBase {

	DefaultPage defaultPage;
	HomeClientPage homePage;

	@Test(priority = 1)
	public void askDesignTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultPage(driver);
		homePage = new HomeClientPage(driver);
		defaultPage.openAskDesignPage();
		Assert.assertTrue(homePage.wantedDesignHeaderMsgCli.isDisplayed());
	}
}