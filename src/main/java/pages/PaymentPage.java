package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends PageBase {

	public PaymentPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[text()='اختر فئة المسابقة']")
	public WebElement paymentPageHeader;

	// @FindBy(xpath = "//div[@data-id='1']")
	@FindBy(xpath = "//div[@class='col m4 s12 wow fadeIn']")
	// @FindBy(xpath = "//div[@class='contest-image-frame package_option']")
	WebElement silverType;

	@FindBy(xpath = "//img[@class='icon']")
	WebElement correctIcon;

	@FindBy(xpath = "//*[text()='إختر طريقة الدفع']")
	// @FindBy(xpath = "//input[@class='select-dropdown']")
	// @FindBy(xpath = "//input[@value='إختر طريقة الدفع']")
	WebElement paymentLst;

	// @FindBy(xpath = "//*[text()='ايداع بنكي']")
	@FindBy(xpath = "//option[@value='DEPOSIT']")
	WebElement bankDeposit;

	@FindBy(id = "contest_payment_type")
	WebElement paymentList;

	public void silverPrize() {
		clickButton(silverType);
		clickButton(correctIcon);
	}

	public void bankDepositFun() {
		// clickButton(paymentLst);
		selectItemWithVisible(paymentList, "إختر طريقة الدفع");
		// clickButton(paymentLst);
		selectItemWithVisible(paymentList, "إختر طريقة الدفع");
		selectItemWithVisible(bankDeposit, "ايداع بنكي");
		// clickButton(bankDeposit);
	}
}
