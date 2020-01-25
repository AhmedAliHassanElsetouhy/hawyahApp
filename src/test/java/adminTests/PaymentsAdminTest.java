package adminTests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import adminPages.HomePageAdminPage;
import adminPages.PaymentsAdminPage;
import clientPages.DefaultPage;
import clientPages.LoginPage;
import clientTests.TestBase;
import data.ExcelReader;

public class PaymentsAdminTest extends TestBase {
	// test of creating contest until payment done to search for it and filter with
	// date to ensure integration done correctly
	HomePageAdminPage homePageAdminPage;
	LoginPage loginAdminPage;
	DefaultPage defaultAdminPage;
	String password = "11111111";
	PaymentsAdminPage paymentsAdminPage;
	String paymentAmount = "1999";
	String transactionAmount = "157";

	@Test(priority = 1, alwaysRun = true)
	public void openPaymentsPageTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		homePageAdminPage = new HomePageAdminPage(driver);
		loginAdminPage = new LoginPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		paymentsAdminPage = new PaymentsAdminPage(driver);
		defaultAdminPage.openLoginForm();
		Assert.assertTrue(loginAdminPage.forgetPassLink.isDisplayed());
		loginAdminPage.loginFun(ER.getExcelData(10, 2)[1][1], password);
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(0).isDisplayed());
		homePageAdminPage.adminSideMenuListItems.get(4).click();
		Assert.assertTrue(paymentsAdminPage.paymentsFilterTotalPaymentsAmount.getText().contains("Payments Total:"));
	}

	@Test(priority = 2)
	public void filterDepositPaymentTypeTest() throws InterruptedException {
		paymentsAdminPage = new PaymentsAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		homePageAdminPage.adminSideMenuListItems.get(4).click();
		paymentsAdminPage.searchPaymentTypePaymentsFilterFun(1);
		paymentsAdminPage.searchFilterPaymentsFun();
		Assert.assertTrue(paymentsAdminPage.paymentsFilterPaymentRecordsList.get(1).getText().contains("Deposit"));
	}

	@Test(priority = 3)
	public void filterCreditCardPaymentTypeTest() throws InterruptedException {
		paymentsAdminPage = new PaymentsAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		homePageAdminPage.adminSideMenuListItems.get(4).click();
		paymentsAdminPage.searchPaymentTypePaymentsFilterFun(2);
		paymentsAdminPage.searchFilterPaymentsFun();
		Assert.assertTrue(paymentsAdminPage.paymentsFilterPaymentRecordsList.get(2).getText().contains("Credit card"));
	}

	@Test(priority = 4)
	public void filterSadadPaymentTypeTest() throws InterruptedException {
		paymentsAdminPage = new PaymentsAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		homePageAdminPage.adminSideMenuListItems.get(4).click();
		paymentsAdminPage.searchPaymentTypePaymentsFilterFun(3);
		paymentsAdminPage.searchFilterPaymentsFun();
		Assert.assertTrue(paymentsAdminPage.paymentsFilterTotalPaymentsAmount.getText().contains("0 Saudi Riyal"));
	}

	@Test(priority = 5)
	public void searchAmountPaymentTypeTest() throws InterruptedException {
		paymentsAdminPage = new PaymentsAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		homePageAdminPage.adminSideMenuListItems.get(4).click();
		paymentsAdminPage.searchPaymentTypePaymentsFilterFun(0);
		paymentsAdminPage.searchPaymentsFilterAmountFun(paymentAmount);
		paymentsAdminPage.searchFilterPaymentsFun();
		Assert.assertTrue(paymentsAdminPage.paymentsFilterPaymentRecordsList.get(1).getText().contains("1999"));
	}

	@Test(priority = 6)
	public void searchTransactionNumberTypeTest() throws InterruptedException {
		paymentsAdminPage = new PaymentsAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		homePageAdminPage.adminSideMenuListItems.get(4).click();
		paymentsAdminPage.searchPaymentTypePaymentsFilterFun(0);
		paymentsAdminPage.searchTransactionPaymentsFilterAmountFun(transactionAmount);
		paymentsAdminPage.searchFilterPaymentsFun();
		Assert.assertTrue(paymentsAdminPage.paymentsFilterPaymentRecordsList.get(1).getText().contains("157"));
	}

	@Test(priority = 7)
	public void logoutTest() throws InterruptedException, AWTException {
		homePageAdminPage = new HomePageAdminPage(driver);
		Actions hoverAction = new Actions(driver);
		hoverAction.moveToElement(homePageAdminPage.adminMenu.get(1)).perform();
		homePageAdminPage.logoutAdminFun();
	}
}