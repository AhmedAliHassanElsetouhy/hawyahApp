package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pages.DefaultPage;
import pages.RequestDesignAndSearchPage;

public class RequestDesignAndSearchTest extends TestBase {

	Faker fakeData = new Faker();
	DefaultPage defaultPage;
	RequestDesignAndSearchPage requestDesignPage;
	String searchText = fakeData.name().firstName();
	// String selectDesignOption = "تصميم شعار";
	// String selectActivityOption = "الزراعة";
	// String orderOption = "الأحدث";
	int fromPrice = 10000;
	int toPrice = 20000;
	String status = "منتهية";

	int designIndex = 2;
	int activityIndex = 2;
	int orderIndex = 2;

	@Test(priority = 1)
	public void openRequestDesignTest() {
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
	public void prizeOptionSearchTest() {
		defaultPage = new DefaultPage(driver);
		requestDesignPage = new RequestDesignAndSearchPage(driver);
		requestDesignPage.advancedSearchFun();
		requestDesignPage.searchTxtBox.clear();
		requestDesignPage.goldenOption();
		requestDesignPage.silverfilter();
		requestDesignPage.searchBtnFun();
		Assert.assertTrue(requestDesignPage.silverFlag.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "prizeOptionSearchTest" })
	public void prizePriceSearchTest() {
		defaultPage = new DefaultPage(driver);
		requestDesignPage = new RequestDesignAndSearchPage(driver);
		requestDesignPage.advancedSearchFun();
		requestDesignPage.searchTxtBox.clear();
		requestDesignPage.sendFromToPrice(fromPrice, toPrice);
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

	@Test(priority = 7)
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