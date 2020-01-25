package adminTests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import adminPages.HomePageAdminPage;
import adminPages.MoneyAdminPage;
import adminPages.PaymentsAdminPage;
import clientPages.DefaultPage;
import clientPages.LoginPage;
import clientTests.TestBase;
import data.ExcelReader;

public class MoneyAdminTest extends TestBase {

	HomePageAdminPage homePageAdminPage;
	LoginPage loginAdminPage;
	DefaultPage defaultAdminPage;
	String password = "11111111";
	PaymentsAdminPage paymentsAdminPage;
	String paymentAmount = "1999";
	String transactionAmount = "157";
	MoneyAdminPage moneyAdminPage;

	@Test(priority = 1, alwaysRun = true)
	public void openMoneyPageTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		homePageAdminPage = new HomePageAdminPage(driver);
		loginAdminPage = new LoginPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		paymentsAdminPage = new PaymentsAdminPage(driver);
		moneyAdminPage = new MoneyAdminPage(driver);
		defaultAdminPage.openLoginForm();
		Assert.assertTrue(loginAdminPage.forgetPassLink.isDisplayed());
		loginAdminPage.loginFun(ER.getExcelData(10, 2)[1][1], password);
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(0).isDisplayed());
		homePageAdminPage.adminSideMenuListItems.get(6).click();
		System.out.println(moneyAdminPage.moneyPageTitleHeader.get(1).getText());
		Assert.assertTrue(moneyAdminPage.moneyPageTitleHeader.get(1).getText().contains("TRANSACTIONS"));
	}

	@Test(priority = 2)
	public void transferTransactionsFun() {
		moneyAdminPage = new MoneyAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		homePageAdminPage.adminSideMenuListItems.get(6).click();
		moneyAdminPage.amountEqTxtBox.clear();
		moneyAdminPage.trasactionTypeFilterFun(2);
		moneyAdminPage.searchFiltermoneyReportFun();
		System.out.println(moneyAdminPage.paymentsLabel.getText());
		Assert.assertTrue(moneyAdminPage.paymentsLabel.getText().contains("Payments Total: 0 Saudi Riyal"));
	}

	@Test(priority = 3)
	public void paymentTransactionsFun() {
		moneyAdminPage = new MoneyAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		homePageAdminPage.adminSideMenuListItems.get(6).click();
		moneyAdminPage.amountEqTxtBox.clear();
		moneyAdminPage.trasactionTypeFilterFun(1);
		moneyAdminPage.searchFiltermoneyReportFun();
		System.out.println(moneyAdminPage.moneyReportResultRecords.get(0).getText());
		Assert.assertTrue(moneyAdminPage.moneyReportResultRecords.get(0).getText().contains("Payment"));
	}

	@Test(priority = 4)
	public void downloadPaymentReportFun() {
		moneyAdminPage = new MoneyAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		homePageAdminPage.adminSideMenuListItems.get(6).click();
		moneyAdminPage.buildAndExportMoneyReportExcelSheetFun();
	}

	String moneyAmount = "1999";

	@Test(priority = 5)
	public void amountFilterTest() {
		moneyAdminPage = new MoneyAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		homePageAdminPage.adminSideMenuListItems.get(6).click();
		moneyAdminPage.trasactionTypeFilterFun(0);
		moneyAdminPage.amountSearchFilterFun(moneyAmount);
		moneyAdminPage.searchFiltermoneyReportFun();
		Assert.assertTrue(moneyAdminPage.moneyReportResultRecords.get(1).getText().contains(moneyAmount));
		homePageAdminPage.adminSideMenuListItems.get(6).click();
	}

	@Test(priority = 6)
	public void logoutTest() throws InterruptedException, AWTException {
		homePageAdminPage = new HomePageAdminPage(driver);
		Actions hoverAction = new Actions(driver);
		hoverAction.moveToElement(homePageAdminPage.adminMenu.get(1)).perform();
		homePageAdminPage.logoutAdminFun();
	}
}