package clientPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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
	// @FindBy(xpath = "//*[text()='إختر طريقة الدفع']")
	// @FindBy(xpath = "//*[@class='chosen-single']")
	// @FindBy(id = "contest_payment_type_chosen")
	@FindBy(xpath = "//*[@id=\"contest_payment_type_chosen\"]")
	public WebElement paymentLst;

	@FindBy(xpath = "//*[@class='contest-image-frame package_option']")
	public WebElement silverElm;

	@FindBy(id = "contest_payment_type")
	WebElement paymentOption;

	@FindBy(xpath = "//*[@class='chosen-drop']//*[@class='chosen-results']")
	public WebElement ul;

	public void silverPrize() {
		clickButton(silverType);
		// clickButton(correctIcon);
	}

	public void bankDepositFun() {
		clickButton(paymentLst);
		// Assert.assertTrue(paymentLst.isDisplayed());
		// Assert.assertTrue(ul.isDisplayed());
		// selectItemByValue(paymentOption, "DEPOSIT");
	}
}