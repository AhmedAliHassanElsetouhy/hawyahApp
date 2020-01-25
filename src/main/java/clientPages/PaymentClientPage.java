package clientPages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentClientPage extends PageBase {

	public PaymentClientPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[text()='اختر فئة المسابقة']")
	public WebElement paymentPageHeaderCli;

	// @FindBy(xpath = "//*[text()='الفئة الفضية']")
	// @FindBy(xpath = "//div[@class='contest-image-frame package_option']")
	@FindBy(xpath = "//div[@class='col m4 s12 wow fadeIn']")
	WebElement silverPrizeTypeCli;

	@FindBy(xpath = "//div[@class='col m4 s12 wow fadeIn full-width']")
	WebElement goldenPrizeTypeCli;

	// @FindBy(xpath = "//img[@class='icon']")
	@FindBy(xpath = "//*[@src='/assets/uncheckmark-ebf98565f8229ee815d774943a8d15a208f9a6af6a55a591539072f32a6a4786.svg']")
	List<WebElement> correctIconsCli;

	// @FindBy(xpath =
	// "//*[@src='/assets/checkmark-3b519427250e38ccb755782c11a98a529aac2129f11e327c4706b5a2b81c3b80.svg']")
	// @FindBy(xpath =
	// "//*[@src='/assets/uncheckmark-ebf98565f8229ee815d774943a8d15a208f9a6af6a55a591539072f32a6a4786.svg']")
	@FindBy(xpath = "//div[@class='icon-background']")
	List<WebElement> displayCorrectIconsCli;

	// @FindBy(xpath = "//*[text()='إختر طريقة الدفع']")
	// @FindBy(xpath = "//a[@class='chosen-single']")
	// @FindBy(id = "contest_payment_type_chosen")
	// @FindBy(xpath = "//*[@id='contest_payment_type_chosen']")
	// @FindBy(xpath = "//div[@class='col m8 s12']")
	@FindBy(xpath = "//div[@class='chosen-container chosen-container-single chosen-rtl chosen-container-single-nosearch chosen-with-drop chosen-container-active']")
	public WebElement paymentLstCli;

	@FindBy(id = "contest_payment_type")
	WebElement paymentOptionCli;

	@FindBy(xpath = "//*[@class='chosen-drop']//*[@class='chosen-results']")
	public WebElement ulCli;

	public void silverPrizeFun() {
		clickButton(silverPrizeTypeCli);
		selectSpecificIcon(correctIconsCli, 0);
	}

	public void goldenPrizeFun() {
		clickButton(goldenPrizeTypeCli);
		selectSpecificIcon(correctIconsCli, 1);
	}

	public void ContestDisplayOptionsFun() {
		selectSpecificIcon(displayCorrectIconsCli, 0);
		// selectSpecificIcon(displayCorrectIconsCli, 3);
		// selectSpecificIcon(displayCorrectIconsCli, 4);
	}

	@FindBy(xpath = "//div[@class='chosen-drop']//ul[@class=chosen-results]")
	public WebElement openPaymentListCli;

	@FindBy(id = "contest_payment_type_chosen")
	List<WebElement> paymentListOptions;

	public void paymentOptionFun(int paymentIndex) {
		selectSpecificIcon(paymentListOptions, paymentIndex);
	}

	public void bankDepositFun() throws InterruptedException {
		System.out.println(openPaymentListCli);
		List<WebElement> a = openPaymentListCli.findElements(By.className("active-result"));
		a.get(1).click();

		// electItemByValue(paymentOption, "DEPOSIT");
		// selectItemByValue(menuItem, selectedElement);
		// clickButton(paymentOptions);
		// selectSpecificIcon(paymentOptions, index);
	}
}