package clientTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.DefaultPage;
import clientPages.HomePage;
import clientPages.RequestDesignAndSearchPage;
import data.ExcelReader;

public class RequestDesignAndSearchTest extends TestBase {

	Faker fakeData = new Faker();
	DefaultPage defaultPage;
	HomePage homePage;
	RequestDesignAndSearchPage requestDesignPage;
	String searchText = fakeData.name().firstName();
	// String selectDesignOption = "تصميم شعار";
	// String selectActivityOption = "الزراعة";
	// String orderOption = "الأحدث";
	String status = "منتهية";

	int designIndex = 2;
	int activityIndex = 2;
	int orderIndex = 2;

	@Test(priority = 1)
	public void openRequestDesignTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultPage(driver);
		requestDesignPage = new RequestDesignAndSearchPage(driver);
		defaultPage.openRequestDesingFun();
		Assert.assertTrue(requestDesignPage.advancedSearchLink.isDisplayed());
	}

	@Test(priority = 2, dependsOnMethods = { "openRequestDesignTest" })
	public void textSearchTest() {
		defaultPage = new DefaultPage(driver);
		requestDesignPage = new RequestDesignAndSearchPage(driver);
		requestDesignPage.advancedSearchFun();
		requestDesignPage.sendSearchTxt(searchText);
		Assert.assertTrue(requestDesignPage.noResultTxtBox.isDisplayed());
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
		requestDesignPage = new RequestDesignAndSearchPage(driver);
		Thread.sleep(2000);
		requestDesignPage.advancedSearchFun();
		requestDesignPage.searchTxtBox.clear();
		requestDesignPage.goldenOption();
		requestDesignPage.silverfilter();
		requestDesignPage.searchBtnFun();
		Assert.assertTrue(requestDesignPage.silverFlag.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "prizeOptionSearchTest" })
	public void prizePriceSearchTest() throws IOException {
		defaultPage = new DefaultPage(driver);
		requestDesignPage = new RequestDesignAndSearchPage(driver);
		ExcelReader ER = new ExcelReader();
		requestDesignPage.advancedSearchFun();
		requestDesignPage.searchTxtBox.clear();
		requestDesignPage.sendFromToPrice(ER.getExcelData(4, 2)[0][1], ER.getExcelData(4, 2)[1][1]);
		requestDesignPage.searchBtnFun();
		Assert.assertTrue(requestDesignPage.noResultTxtBox.isDisplayed());
	}

	@Test(priority = 6, dependsOnMethods = { "prizePriceSearchTest" })
	public void statusSearchTest() {
		defaultPage = new DefaultPage(driver);
		requestDesignPage = new RequestDesignAndSearchPage(driver);
		requestDesignPage.advancedSearchFun();
		requestDesignPage.searchTxtBox.clear();
		requestDesignPage.selectStatus(status);
		requestDesignPage.searchBtnFun();
		Assert.assertTrue(requestDesignPage.noResultTxtBox.isDisplayed());
	}

	@Test(priority = 7, dependsOnMethods = { "statusSearchTest" })
	public void activeStatusSearchTest() {
		defaultPage = new DefaultPage(driver);
		requestDesignPage = new RequestDesignAndSearchPage(driver);
		requestDesignPage.advancedSearchFun();
		requestDesignPage.searchTxtBox.clear();
		requestDesignPage.selectActiveStatus(status);
		requestDesignPage.searchBtnFun();
		Assert.assertTrue(requestDesignPage.noResultTxtBox.isDisplayed());
	}

}