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
	@FindBy(xpath = "//div[@class='contest-image-frame package_option']")
	WebElement silverTypeCli;

	@FindBy(xpath = "//img[@class='icon']")
	WebElement correctIconCli;

	// @FindBy(xpath = "//*[text()='إختر طريقة الدفع']")
	// @FindBy(xpath = "//a[@class='chosen-single']")
	// @FindBy(id = "contest_payment_type_chosen")
	// @FindBy(xpath = "//*[@id='contest_payment_type_chosen']")
	// @FindBy(xpath = "//div[@class='col m8 s12']")
	@FindBy(xpath = "//div[@class='chosen-container chosen-container-single chosen-rtl chosen-container-single-nosearch chosen-with-drop chosen-container-active']")
	public WebElement paymentLstCli;

	@FindBy(xpath = "//*[@class='contest-image-frame package_option']")
	public WebElement silverElmCli;

	@FindBy(id = "contest_payment_type")
	WebElement paymentOptionCli;

	@FindBy(xpath = "//*[@class='chosen-drop']//*[@class='chosen-results']")
	public WebElement ulCli;

	public void silverPrize() {
		clickButton(silverTypeCli);
		// clickButton(correctIcon);
	}

	// @FindBy(xpath = "//ul[@class='chosen-results']")
	// @FindBy(xpath = "//li[@class='active-result']")
	// @FindBy(className = "active-result")
	@FindBy(id = "contest_payment_type")
	WebElement a;

	@FindBy(xpath = "//div[@class='chosen-drop']//ul[@class=chosen-results]")
	public WebElement openPaymentListCli;

	@FindBy(className = "active-result")
	List<WebElement> la;

	@FindBy(xpath = "/html[1]/body[1]/div[2]/form[1]/div[2]/div[6]/div[1]/div[1]/div[1]/ul[1]/li[2]")
	WebElement test;

	public void bankDepositFun() throws InterruptedException {
		// System.out.println(paymentLst.getLocation());
		// clickButton(paymentLst);
		// payment000.sendKeys(Keys.ARROW_DOWN);
		// paymentLst.sendKeys(Keys.ENTER);
		// Assert.assertTrue(paymentLst.isDisplayed());
		// Assert.assertTrue(ul.isDisplayed());
		Thread.sleep(10000);
		// explicit wait - to wait for the compose button to be click-able

		// WebDriverWait wait = new WebDriverWait(driver, 30);
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='chosen-drop']//ul[@class=chosen-results]")));
		//
		// click on the compose button as soon as the "compose" button is visible
		// openPaymentList.click();

		System.out.println(openPaymentListCli);
		List<WebElement> a = openPaymentListCli.findElements(By.className("active-result"));
		a.get(1).click();

		// electItemByValue(paymentOption, "DEPOSIT");
		// selectItemByValue(menuItem, selectedElement);
		// clickButton(paymentOptions);

		// clickButton(test);

		// selectSpecificIcon(paymentOptions, index);

	}
}