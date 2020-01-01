package designerTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.DefaultPage;
import clientPages.HomePage;
import clientPages.LoginPage;
import clientPages.RequestDesignAndSearchPage;
import clientTests.TestBase;
import data.ExcelReader;
import designerPages.HomeUserPage;

public class RequestDesignAndSearchDesignerTest extends TestBase {

	Faker fakeData = new Faker();
	DefaultPage defaultPage;
	HomePage homePage;
	RequestDesignAndSearchPage requestDesignPage;
	String searchText = fakeData.name().firstName();
	// String selectDesignOption = "تصميم شعار";
	// String selectActivityOption = "الزراعة";
	// String orderOption = "الأحدث";
	String status = "منتهية";

	LoginPage loginPage;
	HomeUserPage homeUserPage;
	int designIndex = 2;
	int activityIndex = 2;
	int orderIndex = 2;

	@Test(priority = 1, alwaysRun = true)
	public void openHomePageTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultPage(driver);
		loginPage = new LoginPage(driver);
		defaultPage.openLoginForm();
		Assert.assertTrue(loginPage.forgetPassLink.isDisplayed());
	}

	@Test(priority = 2, dependsOnMethods = { "openHomePageTest" })
	public void loginFun() throws IOException {
		loginPage = new LoginPage(driver);
		defaultPage = new DefaultPage(driver);
		homePage = new HomePage(driver);
		homeUserPage = new HomeUserPage(driver);
		ExcelReader ER = new ExcelReader();
		loginPage.loginFun(ER.getExcelData(5, 2)[1][1], ER.getExcelData(5, 2)[2][1]);
		System.out.println(homePage.loginConfirmMsg.getText());
		Assert.assertTrue(homePage.loginConfirmMsg.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openRequestDesignTest() throws IOException {
		// ExcelReader ER = new ExcelReader();
		// driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultPage(driver);
		requestDesignPage = new RequestDesignAndSearchPage(driver);
		defaultPage.openRequestDesingFun();
		Assert.assertTrue(requestDesignPage.advancedSearchLink.isDisplayed());
	}

	@Test(priority = 4, dependsOnMethods = { "openRequestDesignTest" })
	public void textSearchTest() {
		defaultPage = new DefaultPage(driver);
		requestDesignPage = new RequestDesignAndSearchPage(driver);
		requestDesignPage.advancedSearchFun();
		requestDesignPage.sendSearchTxt(searchText);
		Assert.assertTrue(requestDesignPage.noResultTxtBox.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "textSearchTest" })
	public void searchListTest() throws IOException {
		defaultPage = new DefaultPage(driver);
		requestDesignPage = new RequestDesignAndSearchPage(driver);
		ExcelReader ER = new ExcelReader(); 
		requestDesignPage.searchList(ER.getExcelData(8, 2)[0][1], activityIndex, orderIndex);
		Assert.assertTrue(requestDesignPage.noResultTxtBox.isDisplayed());
	}

	// @Test(priority = 5, dependsOnMethods = { "textSearchTest" })
	// public void prizeOptionSearchTest() {
	// defaultPage = new DefaultPage(driver);
	// requestDesignPage = new RequestDesignAndSearchPage(driver);
	// requestDesignPage.advancedSearchFun();
	// requestDesignPage.searchTxtBox.clear();
	// requestDesignPage.goldenOption();
	// requestDesignPage.silverfilter();
	// requestDesignPage.searchBtnFun();
	// Assert.assertTrue(requestDesignPage.silverFlag.isDisplayed());
	// }
	//
	// @Test(priority = 6, dependsOnMethods = { "prizeOptionSearchTest" })
	// public void prizePriceSearchTest() throws IOException {
	// defaultPage = new DefaultPage(driver);
	// requestDesignPage = new RequestDesignAndSearchPage(driver);
	// ExcelReader ER = new ExcelReader();
	// requestDesignPage.advancedSearchFun();
	// requestDesignPage.searchTxtBox.clear();
	// requestDesignPage.sendFromToPrice(ER.getExcelData(4, 2)[0][1],
	// ER.getExcelData(4, 2)[1][1]);
	// requestDesignPage.searchBtnFun();
	// Assert.assertTrue(requestDesignPage.noResultTxtBox.isDisplayed());
	// }
	//
	// @Test(priority = 7, dependsOnMethods = { "prizePriceSearchTest" })
	// public void statusSearchTest() {
	// defaultPage = new DefaultPage(driver);
	// requestDesignPage = new RequestDesignAndSearchPage(driver);
	// requestDesignPage.advancedSearchFun();
	// requestDesignPage.searchTxtBox.clear();
	// requestDesignPage.selectStatus(status);
	// requestDesignPage.searchBtnFun();
	// Assert.assertTrue(requestDesignPage.noResultTxtBox.isDisplayed());
	// }
	//
	// @Test(priority = 8, dependsOnMethods = { "statusSearchTest" })
	// public void activeStatusSearchTest() {
	// defaultPage = new DefaultPage(driver);
	// requestDesignPage = new RequestDesignAndSearchPage(driver);
	// requestDesignPage.advancedSearchFun();
	// requestDesignPage.searchTxtBox.clear();
	// requestDesignPage.selectActiveStatus(status);
	// requestDesignPage.searchBtnFun();
	// Assert.assertTrue(requestDesignPage.noResultTxtBox.isDisplayed());
	// }

}