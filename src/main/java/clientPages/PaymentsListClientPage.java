package clientPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentsListClientPage extends PageBase {

	public PaymentsListClientPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[@id=\"payments\"]/div/div/table/thead/tr/th")
	public List<WebElement> peymentsCli;

	@FindBy(xpath = "//*[text()='المسابقة']")
	public WebElement competitionColCli;

	@FindBy(xpath = "//*[text()='طريقة الدفع']")
	public WebElement paymentMethodColCli;

	@FindBy(xpath = "//*[text()='المبلغ']")
	public WebElement amountColCli;

	@FindBy(xpath = "//*[text()='تاريخ الدفع']")
	public WebElement paymentDateColCli;

	@FindBy(xpath = "//*[text()='رقم العملية']")
	public WebElement operationNumberColCli;

	@FindBy(xpath = "//*[text()='اسم البنك']")
	public WebElement bankNameColCli;

	@FindBy(xpath = "//*[text()='سداد  Payfort']")
	public WebElement sadadPayfortColCli;
}
