package adminTests;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import adminPages.HomePageAdminPage;
import clientPages.DefaultPage;
import clientPages.LoginPage;
import clientTests.TestBase;
import data.ExcelReader;
import designerPages.HomeDesignerPage;

public class SearchAdminTest extends TestBase {

	HomePageAdminPage homePageAdminPage;
	DefaultPage defaultAdminPage;
	LoginPage loginAdminPage;
	String password = "11111111";
	HomeDesignerPage homeDesignerPage;
	Faker fake = new Faker();
	String userName = fake.name().firstName() + fake.number().numberBetween(1, 99);
	String email = fake.internet().emailAddress();
	String usPassword = "P@55word";
	String searchText = fake.name().fullName();

	@Test(priority = 1, alwaysRun = true)
	public void openHomePageTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		homePageAdminPage = new HomePageAdminPage(driver);
		loginAdminPage = new LoginPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		defaultAdminPage.openLoginForm();
		Assert.assertTrue(loginAdminPage.forgetPassLink.isDisplayed());
		loginAdminPage.loginFun(ER.getExcelData(10, 2)[1][1], password);
		System.out.println(homePageAdminPage.adminSideMenuListItems.get(0).getText());
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(0).isDisplayed());
	}

	@Test(priority = 2)
	public void searchIconTest() throws IOException {
		homePageAdminPage = new HomePageAdminPage(driver);
		homePageAdminPage.searchTxtBoxFun(searchText);
		homePageAdminPage.searchClick();
		Assert.assertTrue(homePageAdminPage.searchResultCntainer.getText().contains(searchText));
	}

	@Test(priority = 3)
	public void searchTest() throws IOException {
		homePageAdminPage = new HomePageAdminPage(driver);
		homePageAdminPage.searchTxtBox.clear();
		homePageAdminPage.searchTxtBoxFun(searchText);
		homePageAdminPage.searchTxtBox.sendKeys(Keys.ENTER);
		Assert.assertTrue(homePageAdminPage.searchResultCntainer.getText().contains(searchText));
	}

}
