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
	public void filterDepositPaymentTypeTest() {
		paymentsAdminPage = new PaymentsAdminPage(driver);
		paymentsAdminPage.searchPaymentTypePaymentsFilterFun(1);
		paymentsAdminPage.searchFilterPaymentsFun();
		Assert.assertTrue(paymentsAdminPage.paymentsFilterPaymentRecordsList.get(1).getText().contains("Deposit"));
	}

	@Test(priority = 3)
	public void filterCreditCardPaymentTypeTest() {
		paymentsAdminPage = new PaymentsAdminPage(driver);
		paymentsAdminPage.searchPaymentTypePaymentsFilterFun(2);
		paymentsAdminPage.searchFilterPaymentsFun();
		Assert.assertTrue(paymentsAdminPage.paymentsFilterPaymentRecordsList.get(2).getText().contains("Credit card"));
	}

	@Test(priority = 4)
	public void filterSadadPaymentTypeTest() {
		paymentsAdminPage = new PaymentsAdminPage(driver);
		paymentsAdminPage.searchPaymentTypePaymentsFilterFun(3);
		paymentsAdminPage.searchFilterPaymentsFun();
		Assert.assertTrue(paymentsAdminPage.paymentsFilterTotalPaymentsAmount.getText().contains("0 Saudi Riyal"));
		paymentsAdminPage.clearSearchPaymentFun();
	}

	@Test(priority = 5)
	public void searchAmountPaymentTypeTest() {
		paymentsAdminPage = new PaymentsAdminPage(driver);
		paymentsAdminPage.searchPaymentsFilterAmountFun(paymentAmount);
		Assert.assertTrue(paymentsAdminPage.paymentsFilterPaymentRecordsList.get(1).getText().contains("1999"));
		paymentsAdminPage.clearSearchPaymentFun();
	}

	@Test(priority = 6)
	public void searchTransactionNumberTypeTest() {
		paymentsAdminPage = new PaymentsAdminPage(driver);
		paymentsAdminPage.searchTransactionPaymentsFilterAmountFun(transactionAmount);
		Assert.assertTrue(paymentsAdminPage.paymentsFilterPaymentRecordsList.get(2).getText().contains("157"));
		paymentsAdminPage.clearSearchPaymentFun();
	}

	@Test(priority = 7)
	public void logoutTest() throws InterruptedException, AWTException {
		homePageAdminPage = new HomePageAdminPage(driver);
		Actions hoverAction = new Actions(driver);
		hoverAction.moveToElement(homePageAdminPage.adminMenu.get(1)).perform();
		homePageAdminPage.logoutAdminFun();
	}
}