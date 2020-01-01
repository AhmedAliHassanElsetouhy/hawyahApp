package clientTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import clientPages.DefaultPage;
import clientPages.HomePage;
import data.ExcelReader;

public class AskDesignTest extends TestBase {

	DefaultPage defaultPage;
	HomePage homePage;

	@Test(priority = 1)
	public void askDesignTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultPage(driver);
		homePage = new HomePage(driver);
		defaultPage.openAskDesignPage();
		Assert.assertTrue(homePage.wantedDesignHeaderMsg.isDisplayed());
	}
}