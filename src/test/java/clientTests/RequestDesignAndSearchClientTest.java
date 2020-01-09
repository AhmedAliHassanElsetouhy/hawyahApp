package clientTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.DefaultPage;
import clientPages.HomeClientPage;
import clientPages.RequestDesignAndSearchClientPage;
import data.ExcelReader;

public class RequestDesignAndSearchClientTest extends TestBase {

	Faker fakeData = new Faker();
	DefaultPage defaultClientPage;
	HomeClientPage homeClientPage;
	RequestDesignAndSearchClientPage requestDesignClientPage;
	String searchTextCli = fakeData.name().firstName();
	String statusCli = "منتهية";

	int designIndexCli = 2;
	int activityIndexCli = 2;
	int orderIndexCli = 2;

	@Test(priority = 1)
	public void openRequestDesignTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultClientPage = new DefaultPage(driver);
		requestDesignClientPage = new RequestDesignAndSearchClientPage(driver);
		defaultClientPage.openRequestDesingFun();
		Assert.assertTrue(requestDesignClientPage.advancedSearchLinkCli.isDisplayed());
	}

	@Test(priority = 2, dependsOnMethods = { "openRequestDesignTest" })
	public void textSearchTest() {
		defaultClientPage = new DefaultPage(driver);
		requestDesignClientPage = new RequestDesignAndSearchClientPage(driver);
		requestDesignClientPage.advancedSearchFun();
		requestDesignClientPage.sendSearchTxt(searchTextCli);
		Assert.assertTrue(requestDesignClientPage.noResultTxtBoxCli.isDisplayed());
	}

	// @Test(priority = 3)
	// public void searchListTest() {
	// defaultPage = new DefaultPage(driver);
	// requestDesignPage = new RequestDesignAndSearchPage(driver);
	// requestDesignPage.searchList(designIndex, activityIndex, orderIndex);
	// Assert.assertTrue(requestDesignPage.noResultTxtBox.isDisplayed());
	// }

	@Test(priority = 4, dependsOnMethods = { "textSearchTest" })
	public void prizeOptionSearchTest() throws InterruptedException {
		requestDesignClientPage = new RequestDesignAndSearchClientPage(driver);
		Thread.sleep(2000);
		requestDesignClientPage.advancedSearchFun();
		requestDesignClientPage.searchTxtBox.clear();
		requestDesignClientPage.goldenOption();
		requestDesignClientPage.silverfilter();
		requestDesignClientPage.searchBtnFun();
		Assert.assertTrue(requestDesignClientPage.silverFlagCli.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "prizeOptionSearchTest" })
	public void prizePriceSearchTest() throws IOException {
		defaultClientPage = new DefaultPage(driver);
		requestDesignClientPage = new RequestDesignAndSearchClientPage(driver);
		ExcelReader ER = new ExcelReader();
		requestDesignClientPage.advancedSearchFun();
		requestDesignClientPage.searchTxtBox.clear();
		requestDesignClientPage.sendFromToPrice(ER.getExcelData(4, 2)[0][1], ER.getExcelData(4, 2)[1][1]);
		requestDesignClientPage.searchBtnFun();
		Assert.assertTrue(requestDesignClientPage.noResultTxtBoxCli.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "prizePriceSearchTest" })
	public void statusSearchTest() {
		defaultClientPage = new DefaultPage(driver);
		requestDesignClientPage = new RequestDesignAndSearchClientPage(driver);
		requestDesignClientPage.advancedSearchFun();
		requestDesignClientPage.searchTxtBox.clear();
		requestDesignClientPage.selectStatus(statusCli);
		requestDesignClientPage.searchBtnFun();
		Assert.assertTrue(requestDesignClientPage.noResultTxtBoxCli.isDisplayed());
	}

	@Test(priority = 7, dependsOnMethods = { "statusSearchTest" })
	public void activeStatusSearchTest() {
		defaultClientPage = new DefaultPage(driver);
		requestDesignClientPage = new RequestDesignAndSearchClientPage(driver);
		requestDesignClientPage.advancedSearchFun();
		requestDesignClientPage.searchTxtBox.clear();
		requestDesignClientPage.selectActiveStatus(statusCli);
		requestDesignClientPage.searchBtnFun();
		Assert.assertTrue(requestDesignClientPage.noResultTxtBoxCli.isDisplayed());
	}

}