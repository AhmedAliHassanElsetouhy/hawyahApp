package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentsListPage extends PageBase{

	public PaymentsListPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//*[text()='المسابقة']")
	public WebElement competitionCol;
	
	@FindBy(xpath="//*[text()='طريقة الدفع']")
	public WebElement paymentMethodCol;
	
	@FindBy(xpath="//*[text()='المبلغ']")
	public WebElement amountCol;
	
	@FindBy(xpath="//*[text()='تاريخ الدفع']")
	public WebElement paymentDateCol;
	
	@FindBy(xpath="//*[text()='رقم العملية']")
	public WebElement operationNumberCol;
	
	@FindBy(xpath="//*[text()='اسم البنك']")
	public WebElement bankNameCol;
	
	@FindBy(xpath="//*[text()='سداد  Payfort']")
	public WebElement sadadPayfortCol;
}
