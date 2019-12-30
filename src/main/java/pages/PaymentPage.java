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

	// @FindBy(xpath = "//*[text()='الفئة الفضية']")
	@FindBy(xpath = "//div[@class='contest-image-frame package_option']")
	WebElement silverType;

	@FindBy(xpath = "//img[@class='icon']")
	WebElement correctIcon;

	// @FindBy(id = "contest_payment_type_chosen")
	@FindBy(xpath = "//*[text()='إختر طريقة الدفع']")
	public WebElement paymentLst;

	@FindBy(xpath = "//*[@class='contest-image-frame package_option']")
	public WebElement silverElm;

	@FindBy(id = "contest_payment_type")
	WebElement paymentOption;

	public void silverPrize() {
		clickButton(silverType);
		// clickButton(correctIcon);
	}

	public void bankDepositFun() {
		clickButton(paymentLst);
		// selectItemByValue(paymentOption, "DEPOSIT");
	}
}