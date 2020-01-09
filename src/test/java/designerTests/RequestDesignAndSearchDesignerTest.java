package designerTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clientPages.DefaultPage;
import clientPages.HomeClientPage;
import clientPages.LoginPage;
import clientPages.RequestDesignAndSearchClientPage;
import clientTests.TestBase;
import data.ExcelReader;
import designerPages.HomeDesignerPage;

public class RequestDesignAndSearchDesignerTest extends TestBase {

	Faker fakeData = new Faker();
	DefaultPage defaultPage;
	HomeClientPage homePage;
	RequestDesignAndSearchClientPage requestDesignPage;
	String searchText = fakeData.name().firstName();
	// String selectDesignOption = "تصميم شعار";
	// String selectActivityOption = "الزراعة";
	// String orderOption = "الأحدث";
	String status = "منتهية";

	LoginPage loginPage;
	HomeDesignerPage homeUserPage;
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
		homePage = new HomeClientPage(driver);
		homeUserPage = new HomeDesignerPage(driver);
		ExcelReader ER = new ExcelReader();
		loginPage.loginFun(ER.getExcelData(5, 2)[1][1], ER.getExcelData(5, 2)[2][1]);
		System.out.println(homePage.loginConfirmMsgCli.getText());
		Assert.assertTrue(homePage.loginConfirmMsgCli.getText().contains("تم تسجيل الدخول بنجاح"));
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void openRequestDesignTest() throws IOException {
		// ExcelReader ER = new ExcelReader();
		// driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		defaultPage = new DefaultPage(driver);
		requestDesignPage = new RequestDesignAndSearchClientPage(driver);
		defaultPage.openRequestDesingFun();
		Assert.assertTrue(requestDesignPage.advancedSearchLinkCli.isDisplayed());
	}

	@Test(priority = 4, dependsOnMethods = { "openRequestDesignTest" })
	public void textSearchTest() {
		defaultPage = new DefaultPage(driver);
		requestDesignPage = new RequestDesignAndSearchClientPage(driver);
		requestDesignPage.advancedSearchFun();
		requestDesignPage.sendSearchTxt(searchText);
		Assert.assertTrue(requestDesignPage.noResultTxtBoxCli.isDisplayed());
	}

	@Test(priority = 5, dependsOnMethods = { "textSearchTest" })
	public void searchListTest() throws IOException {
		defaultPage = new DefaultPage(driver);
		requestDesignPage = new RequestDesignAndSearchClientPage(driver);
		ExcelReader ER = new ExcelReader(); 
		requestDesignPage.searchList(ER.getExcelData(8, 2)[0][1], activityIndex, orderIndex);
		Assert.assertTrue(requestDesignPage.noResultTxtBoxCli.isDisplayed());
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