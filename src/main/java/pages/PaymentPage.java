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

	@FindBy(xpath = "//*[text()='الفئة الفضية']")
	WebElement silverType;

	@FindBy(xpath = "//img[@class='icon']")
	WebElement correctIcon;
	// *[@id="select-options-430a6986-b622-a71a-8a9c-216ae216a375"]/li[1]/span

	 @FindBy(xpath = "//ul[@class='dropdown-content']")
	// @FindBy(xpath = "//input[@class='select-dropdown']")
	// @FindBy(xpath = "//input[@value='إختر طريقة الدفع']")
//	@FindBy(xpath = "//[@class='dropdown-content select-dropdown']")
	WebElement paymentLst;

	// @FindBy(xpath = "//option[text()='ايداع بنكي']")
	@FindBy(xpath = "//option[@value='DEPOSIT']")
	public WebElement bankDeposit;

	@FindBy(id = "contest_payment_type")
	// @FindBy(xpath = "//*['@class='select-dropdown']")
	WebElement paymentList;

	public void silverPrize() {
		clickButton(silverType);
		clickButton(correctIcon);
	}

	public void bankDepositFun() {
		clickButton(paymentLst);
		// selectItemWithVisible(paymentList, "إختر طريقة الدفع");
		selectItemWithVisible(paymentList, "إختر طريقة الدفع");
		// clickButton(paymentLst);
		// selectItemWithVisible(paymentList, "إختر طريقة الدفع");
		selectItemWithVisible(bankDeposit, "ايداع بنكي");
		// clickButton(bankDeposit);
	}
}