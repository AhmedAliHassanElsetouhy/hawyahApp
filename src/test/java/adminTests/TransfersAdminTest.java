package adminTests;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import adminPages.HomePageAdminPage;
import adminPages.TransferDetailsPage;
import adminPages.TransfersAdminPage;
import clientPages.DefaultPage;
import clientPages.LoginPage;
import clientTests.TestBase;
import data.ExcelReader;

public class TransfersAdminTest extends TestBase {
	// test of creating contest until payment done to search for it and filter with
	// date to ensure integration done correctly

	HomePageAdminPage homePageAdminPage;
	LoginPage loginAdminPage;
	DefaultPage defaultAdminPage;
	TransfersAdminPage transfersAdminPage;
	String password = "11111111";
	String transferAmount = "1399";
	TransferDetailsPage transferDetailsPage;
	Faker fakeData = new Faker();
	int feesAmount = 1000;
	int transferIndex = 0;
	String transferNotes = fakeData.name().title();

	@Test(priority = 1, alwaysRun = true)
	public void openPaymentsPageTest() throws IOException {
		ExcelReader ER = new ExcelReader();
		driver.navigate().to(ER.getExcelData(0, 2)[0][1]);
		homePageAdminPage = new HomePageAdminPage(driver);
		loginAdminPage = new LoginPage(driver);
		defaultAdminPage = new DefaultPage(driver);
		transfersAdminPage = new TransfersAdminPage(driver);
		defaultAdminPage.openLoginForm();
		Assert.assertTrue(loginAdminPage.forgetPassLink.isDisplayed());
		loginAdminPage.loginFun(ER.getExcelData(10, 2)[1][1], password);
		Assert.assertTrue(homePageAdminPage.adminSideMenuListItems.get(0).isDisplayed());
		homePageAdminPage.adminSideMenuListItems.get(5).click();
		Assert.assertTrue(transfersAdminPage.notTransferredAmount.getText().contains("Not Transferred Total: "));
		Assert.assertTrue(transfersAdminPage.transferredAmount.getText().contains("Transferred Total: "));
	}

	@Test(priority = 2)
	public void searchAmountTransfersTest() {
		transfersAdminPage = new TransfersAdminPage(driver);
		transfersAdminPage.searchTransfersFilterAmountFun(transferAmount);
		transfersAdminPage.searchFilterTransfersFun();
		Assert.assertTrue(transfersAdminPage.notTransfersRecords.get(0).getText().contains("1399"));
		transfersAdminPage.clearSearchTransfersFun();
	}

	@Test(priority = 3)
	public void searchCompletedTransfersTest() {
		transfersAdminPage = new TransfersAdminPage(driver);
		transfersAdminPage.completedTransfersFun();
		transfersAdminPage.searchFilterTransfersFun();
		Assert.assertTrue(transfersAdminPage.notTransferredAmount.getText().contains("Not Transferred Total: "));
		Assert.assertTrue(transfersAdminPage.transferredAmount.getText().contains("Transferred Total: "));
		transfersAdminPage.clearSearchTransfersFun();
	}

	@Test(priority = 4)
	public void openTransfersDetailsTest() {
		transfersAdminPage = new TransfersAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		transferDetailsPage = new TransferDetailsPage(driver);
		homePageAdminPage.adminSideMenuListItems.get(5).click();
		transfersAdminPage.notTransfersRecords.get(1).click();
		Assert.assertTrue(transferDetailsPage.designerNameDisableBox.isDisplayed());
		Assert.assertTrue(transferDetailsPage.designerAmountDisableBox.isDisplayed());
		Assert.assertTrue(transferDetailsPage.designerAccountDisableBox.isDisplayed());
		Assert.assertTrue(transferDetailsPage.designerRequestCountDisableBox.isDisplayed());
		Assert.assertTrue(transferDetailsPage.designerLastRequestDateTransferDisableBox.isDisplayed());
		Assert.assertTrue(transferDetailsPage.designerTransferNotesBox.isEnabled());
	}

	@Test(priority = 5)
	public void sendTransfersDetailsTest() {
		transfersAdminPage = new TransfersAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		transferDetailsPage = new TransferDetailsPage(driver);
		homePageAdminPage.adminSideMenuListItems.get(5).click();
		transfersAdminPage.notTransfersRecords.get(1).click();
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		System.out.println(today.format(date));
		String todayDate = today.format(date);
		transferDetailsPage.adminSendTransferRequestFun(feesAmount, 1, todayDate, transferNotes);
		// transferDetailsPage.submitTransferDetailsDataFun();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				transferDetailsPage.submitTransferDetailsBtn);
		Assert.assertTrue(transferDetailsPage.confirmDetailsTransferMsg.isDisplayed());
	}

	@Test(priority = 6)
	public void makeTransfersTest() {
		transfersAdminPage = new TransfersAdminPage(driver);
		homePageAdminPage = new HomePageAdminPage(driver);
		transferDetailsPage = new TransferDetailsPage(driver);
		homePageAdminPage.adminSideMenuListItems.get(5).click();
		transfersAdminPage.notTransfersRecords.get(0).click();
		transferDetailsPage.changeStatusDetailsToTransferresFun(transferIndex);
		// transferDetailsPage.submitTransferDetailsDataFun();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				transferDetailsPage.submitTransferDetailsBtn);
		Assert.assertTrue(transferDetailsPage.confirmDetailsTransferMsg.isDisplayed());
		homePageAdminPage.adminSideMenuListItems.get(5).click();
		transfersAdminPage.completedTransfersFun();
		transfersAdminPage.searchFilterTransfersFun();
		// System.out.println(transfersAdminPage.notTransfersRecords.get(0).getText());
		Assert.assertTrue(transfersAdminPage.transferedRecords.size() != 0);
	}
}