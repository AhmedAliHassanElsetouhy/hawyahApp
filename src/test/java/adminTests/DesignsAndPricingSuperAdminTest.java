package adminTests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import adminPages.DesignsAndPricingSuperAdminPage;
import adminPages.HomePageAdminPage;
import adminPages.PaymentsAdminPage;
import clientPages.DefaultPage;
import clientPages.LoginPage;
import clientTests.TestBase;
import data.ExcelReader;

public class DesignsAndPricingSuperAdminTest extends TestBase {

	HomePageAdminPage homePageAdminPage;
	LoginPage loginAdminPage;
	DefaultPage defaultAdminPage;
	PaymentsAdminPage paymentsAdminPage;
	String password = "11111111";
	DesignsAndPricingSuperAdminPage designsAndPricingSuperAdminPage;

	@Test(priority = 1, alwaysRun = true)
	public void openDesignsAndPricingPageTest() throws IOException, AWTException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		homePageAdminPage = new HomePageAdminPage(driver);
		loginAdminPage = new LoginPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		paymentsAdminPage = new PaymentsAdminPage(driver);
		designsAndPricingSuperAdminPage = new DesignsAndPricingSuperAdminPage(driver);
		defaultAdminPage.openLoginForm();
		Assert.assertTrue(loginAdminPage.forgetPassLink.isDisplayed());
		loginAdminPage.loginFun(ER.getExcelData(10, 2)[1][1], password);
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(0).isDisplayed());
		Actions generateReportHoverAction = new Actions(driver);
		generateReportHoverAction.moveToElement(homePageAdminPage.adminSideMenuListItems.get(7)).perform();
		homePageAdminPage.openDesignsAndPricingFun();

		// System.out.println(designsAndPricingSuperAdminPage.designsAndPricingPage1.get(0).getText());
		designsAndPricingSuperAdminPage.openPricingAndDesignsPage2Fun();
		Actions hoverAction = new Actions(driver);
		hoverAction.moveToElement(homePageAdminPage.adminMenu.get(1)).perform();
		homePageAdminPage.logoutAdminFun();
	}
}