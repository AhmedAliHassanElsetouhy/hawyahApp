package adminTests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import adminPages.HomePageAdminPage;
import clientPages.DefaultPage;
import clientPages.LoginPage;
import clientTests.TestBase;
import data.ExcelReader;

public class LoginAdminTest extends TestBase {

	HomePageAdminPage homePageAdminPage;
	DefaultPage defaultAdminPage;
	LoginPage loginAdminPage;
	String password = "11111111";
	Actions hoverAction;

	@Test(priority = 1, alwaysRun = true)
	public void openHomePageTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		homePageAdminPage = new HomePageAdminPage(driver);
		loginAdminPage = new LoginPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		defaultAdminPage.openLoginForm();
		Assert.assertTrue(loginAdminPage.forgetPassLink.isDisplayed());
	}

	@Test(priority = 2, dependsOnMethods = { "openHomePageTest" })
	public void loginFun() throws IOException {
		loginAdminPage = new LoginPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		ExcelReader ER = new ExcelReader();
		loginAdminPage.loginFun(ER.getExcelData(10, 2)[1][1], password);
		System.out.println(homePageAdminPage.adminSideMenuListItems.get(0).getText());
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(0).isDisplayed());
		System.out.println(homePageAdminPage.adminSideMenuListItems.get(1).getText());
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(1).isDisplayed());
		System.out.println(homePageAdminPage.adminSideMenuListItems.get(2).getText());
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(2).isDisplayed());
		System.out.println(homePageAdminPage.adminSideMenuListItems.get(3).getText());
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(3).isDisplayed());
		System.out.println(homePageAdminPage.adminSideMenuListItems.get(4).getText());
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(4).isDisplayed());
		System.out.println(homePageAdminPage.adminSideMenuListItems.get(5).getText());
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(5).isDisplayed());
		System.out.println(homePageAdminPage.adminSideMenuListItems.get(6).getText());
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(6).isDisplayed());
		System.out.println(homePageAdminPage.adminSideMenuListItems.get(7).getText());
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(7).isDisplayed());

		System.out.println(homePageAdminPage.packagesGraph.getText());
		System.out.println(homePageAdminPage.designsGraph.getText());
		System.out.println(homePageAdminPage.usersGraph.getText());
		System.out.println(homePageAdminPage.moneyGraph.getText());
		System.out.println(homePageAdminPage.statusGraph.getText());
	}

	@Test(priority = 3, dependsOnMethods = { "loginFun" })
	public void makeLogoutTest() throws AWTException {
		homePageAdminPage = new HomePageAdminPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		hoverAction = new Actions(driver);
		hoverAction.moveToElement(homePageAdminPage.userAdminMenu).perform();
		homePageAdminPage.logoutAdminFun();
		Assert.assertTrue(defaultAdminPage.loginLink.isDisplayed());
	}
}