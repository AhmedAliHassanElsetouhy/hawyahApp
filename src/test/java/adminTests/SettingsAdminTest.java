package adminTests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import adminPages.HomePageAdminPage;
import adminPages.SettingsAdminPage;
import clientPages.DefaultPage;
import clientPages.LoginPage;
import clientTests.TestBase;
import data.ExcelReader;

public class SettingsAdminTest extends TestBase {

	HomePageAdminPage homePageAdminPage;
	LoginPage loginAdminPage;
	DefaultPage defaultAdminPage;
	String password = "11111111";
	SettingsAdminPage settingsAdminPage;

	@Test(priority = 1, alwaysRun = true)
	public void openSettingsPageTest() throws IOException, AWTException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		homePageAdminPage = new HomePageAdminPage(driver);
		loginAdminPage = new LoginPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		settingsAdminPage = new SettingsAdminPage(driver);
		defaultAdminPage.openLoginForm();
		Assert.assertTrue(loginAdminPage.forgetPassLink.isDisplayed());
		loginAdminPage.loginFun(ER.getExcelData(10, 2)[1][1], password);
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(0).isDisplayed());
		System.out.println(homePageAdminPage.adminSideMenuListItems.size());
		homePageAdminPage.adminSideMenuListItems.get(9).click();
		System.out.println(settingsAdminPage.settingsRecords.size());
		System.out.println(settingsAdminPage.settingsRecords.get(1).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(1).getText().contains("Hawiyah version no 1.0"));
		System.out.println(settingsAdminPage.settingsRecords.get(2).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(2).getText().contains("Hawiyah Profit Rate 30"));
		System.out.println(settingsAdminPage.settingsRecords.get(4).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(4).getText().contains("Command PURCHASE"));
		System.out.println(settingsAdminPage.settingsRecords.get(5).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(5).getText().contains("Currency SAR"));
		System.out.println(settingsAdminPage.settingsRecords.get(6).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(6).getText().contains("language ar"));
		System.out.println(settingsAdminPage.settingsRecords.get(7).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(7).getText()
				.contains("Test Purchase Url https://sbcheckout.payfort.com/FortAPI/paymentPage"));
		System.out.println(settingsAdminPage.settingsRecords.get(8).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(8).getText()
				.contains("Test Return Url http://192.168.8.115:3000/checkpayfortpayment"));
		System.out.println(settingsAdminPage.settingsRecords.get(9).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(9).getText()
				.contains("Test Sadad Return Url http://192.168.8.115:3000/checkpayfortsadadpayment"));
		System.out.println(settingsAdminPage.settingsRecords.get(10).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(10).getText()
				.contains("Test Mp Return Url http://192.168.8.115:5000/checkpayfortpaymentmpo"));
		System.out.println(settingsAdminPage.settingsRecords.get(11).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(11).getText().contains("Test Access Code"));
		System.out.println(settingsAdminPage.settingsRecords.get(12).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(12).getText().contains("Test Sha Request Phrase"));
		System.out.println(settingsAdminPage.settingsRecords.get(13).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(13).getText().contains("Test Merchant Identifier"));
		System.out.println(settingsAdminPage.settingsRecords.get(14).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(14).getText()
				.contains("Staging Purchase Url https://sbcheckout.payfort.com/FortAPI/paymentPage"));
		System.out.println(settingsAdminPage.settingsRecords.get(15).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(15).getText()
				.contains("Staging Return Url https://hawyah-dev.herokuapp.com/checkpayfortpayment"));
		System.out.println(settingsAdminPage.settingsRecords.get(16).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(16).getText()
				.contains("Staging Sadad Return Url https://hawyah-dev.herokuapp.com/checkpayfortsadadpayment"));
		System.out.println(settingsAdminPage.settingsRecords.get(17).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(17).getText()
				.contains("Purchase Url https://checkout.payfort.com/FortAPI/paymentPage"));
		System.out.println(settingsAdminPage.settingsRecords.get(18).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(18).getText().contains("Return Url"));
		System.out.println(settingsAdminPage.settingsRecords.get(19).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(19).getText().contains("Sadad Return Url"));
		System.out.println(settingsAdminPage.settingsRecords.get(20).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(20).getText().contains("Mp Return Url"));
		System.out.println(settingsAdminPage.settingsRecords.get(21).getText());
		Assert.assertTrue(
				settingsAdminPage.settingsRecords.get(21).getText().contains("Access Code kYljnBj8wVBMRoz63YJp"));
		System.out.println(settingsAdminPage.settingsRecords.get(22).getText());
		Assert.assertTrue(
				settingsAdminPage.settingsRecords.get(22).getText().contains("Sha Request Phrase $2y$10$n0anjwPNk"));
		System.out.println(settingsAdminPage.settingsRecords.get(23).getText());
		Assert.assertTrue(settingsAdminPage.settingsRecords.get(23).getText().contains("Merchant Identifier 375ba82c"));
		System.out.println(settingsAdminPage.settingsRecords.get(24).getText());
		Assert.assertTrue(
				settingsAdminPage.settingsRecords.get(24).getText().contains("Payfort Production Enabled false"));

		Actions hoverAction = new Actions(driver);
		hoverAction.moveToElement(homePageAdminPage.adminMenu.get(1)).perform();
		homePageAdminPage.logoutAdminFun();
	}

	// OpenFormsandCloseItButloading take time and test fails
	// @Test(priority = 1, alwaysRun = true)
	// public void openHomePageTest1() throws IOException {
	// ExcelReader ER = new ExcelReader();
	// driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
	// homePageAdminPage = new HomePageAdminPage(driver);
	// loginAdminPage = new LoginPage(driver);
	// defaultAdminPage = new DefaultPage(driver);
	// settingsAdminPage = new SettingsAdminPage(driver);
	// defaultAdminPage.openLoginForm();
	// Assert.assertTrue(loginAdminPage.forgetPassLink.isDisplayed());
	// loginAdminPage.loginFun(ER.getExcelData(10, 2)[1][1], password);
	// Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(0).isDisplayed());
	// System.out.println(homePageAdminPage.adminSideMenuListItems.size());
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// System.out.println(settingsAdminPage.settingsRecords.size());
	// System.out.println(settingsAdminPage.settingsRecords.get(1).getText());
	// settingsAdminPage.openSettingsRecordFun(1);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(1).getText().contains("Hawiyah
	// version no 1.0"));
	// System.out.println(settingsAdminPage.settingsRecords.get(2).getText());
	// settingsAdminPage.openSettingsRecordFun(2);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(2).getText().contains("Hawiyah
	// Profit Rate 30"));
	// System.out.println(settingsAdminPage.settingsRecords.get(4).getText());
	// settingsAdminPage.openSettingsRecordFun(4);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(4).getText().contains("Command
	// PURCHASE"));
	// System.out.println(settingsAdminPage.settingsRecords.get(5).getText());
	// settingsAdminPage.openSettingsRecordFun(5);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(5).getText().contains("Currency
	// SAR"));
	// System.out.println(settingsAdminPage.settingsRecords.get(6).getText());
	// settingsAdminPage.openSettingsRecordFun(6);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(6).getText().contains("language
	// ar"));
	// System.out.println(settingsAdminPage.settingsRecords.get(7).getText());
	// settingsAdminPage.openSettingsRecordFun(7);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(7).getText()
	// .contains("Test Purchase Url
	// https://sbcheckout.payfort.com/FortAPI/paymentPage"));
	// System.out.println(settingsAdminPage.settingsRecords.get(8).getText());
	// settingsAdminPage.openSettingsRecordFun(8);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(8).getText()
	// .contains("Test Return Url http://192.168.8.115:3000/checkpayfortpayment"));
	// System.out.println(settingsAdminPage.settingsRecords.get(9).getText());
	// settingsAdminPage.openSettingsRecordFun(9);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(9).getText()
	// .contains("Test Sadad Return Url
	// http://192.168.8.115:3000/checkpayfortsadadpayment"));
	// System.out.println(settingsAdminPage.settingsRecords.get(10).getText());
	// settingsAdminPage.openSettingsRecordFun(10);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(10).getText()
	// .contains("Test Mp Return Url
	// http://192.168.8.115:5000/checkpayfortpaymentmpo"));
	// System.out.println(settingsAdminPage.settingsRecords.get(11).getText());
	// settingsAdminPage.openSettingsRecordFun(11);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(11).getText().contains("Test
	// Access Code"));
	// System.out.println(settingsAdminPage.settingsRecords.get(12).getText());
	// settingsAdminPage.openSettingsRecordFun(12);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(12).getText().contains("Test
	// Sha Request Phrase"));
	// System.out.println(settingsAdminPage.settingsRecords.get(13).getText());
	// settingsAdminPage.openSettingsRecordFun(13);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(13).getText().contains("Test
	// Merchant Identifier"));
	// System.out.println(settingsAdminPage.settingsRecords.get(14).getText());
	// settingsAdminPage.openSettingsRecordFun(14);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(14).getText()
	// .contains("Staging Purchase Url
	// https://sbcheckout.payfort.com/FortAPI/paymentPage"));
	// System.out.println(settingsAdminPage.settingsRecords.get(15).getText());
	// settingsAdminPage.openSettingsRecordFun(15);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(15).getText()
	// .contains("Staging Return Url
	// https://hawyah-dev.herokuapp.com/checkpayfortpayment"));
	// System.out.println(settingsAdminPage.settingsRecords.get(16).getText());
	// settingsAdminPage.openSettingsRecordFun(16);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(16).getText()
	// .contains("Staging Sadad Return Url
	// https://hawyah-dev.herokuapp.com/checkpayfortsadadpayment"));
	// System.out.println(settingsAdminPage.settingsRecords.get(17).getText());
	// settingsAdminPage.openSettingsRecordFun(17);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(17).getText()
	// .contains("Purchase Url https://checkout.payfort.com/FortAPI/paymentPage"));
	// System.out.println(settingsAdminPage.settingsRecords.get(18).getText());
	// settingsAdminPage.openSettingsRecordFun(18);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(18).getText().contains("Return
	// Url"));
	// System.out.println(settingsAdminPage.settingsRecords.get(19).getText());
	// settingsAdminPage.openSettingsRecordFun(19);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(19).getText().contains("Sadad
	// Return Url"));
	// System.out.println(settingsAdminPage.settingsRecords.get(20).getText());
	// settingsAdminPage.openSettingsRecordFun(20);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(20).getText().contains("Mp
	// Return Url"));
	// System.out.println(settingsAdminPage.settingsRecords.get(21).getText());
	// settingsAdminPage.openSettingsRecordFun(21);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(
	// settingsAdminPage.settingsRecords.get(21).getText().contains("Access Code
	// kYljnBj8wVBMRoz63YJp"));
	// System.out.println(settingsAdminPage.settingsRecords.get(22).getText());
	// settingsAdminPage.openSettingsRecordFun(22);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(
	// settingsAdminPage.settingsRecords.get(22).getText().contains("Sha Request
	// Phrase $2y$10$n0anjwPNk"));
	// System.out.println(settingsAdminPage.settingsRecords.get(23).getText());
	// settingsAdminPage.openSettingsRecordFun(23);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(settingsAdminPage.settingsRecords.get(23).getText().contains("Merchant
	// Identifier 375ba82c"));
	// System.out.println(settingsAdminPage.settingsRecords.get(24).getText());
	// settingsAdminPage.openSettingsRecordFun(24);
	// homePageAdminPage.adminSideMenuListItems.get(9).click();
	// Assert.assertTrue(
	// settingsAdminPage.settingsRecords.get(24).getText().contains("Payfort
	// Production Enabled false"));
	// }
}