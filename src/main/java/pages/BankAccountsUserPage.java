package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BankAccountsUserPage extends PageBase {

	public BankAccountsUserPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//*[@class='blue-text']")
	@FindBy(xpath = "//*[text()='ملاحظات بخصوص الحسابات البنكية']")
	public WebElement bankAccNotesHeader;

	@FindBy(xpath = "//*[@title='إلغاء تفعيل الحساب']")
	WebElement deactivateIcon;

	public void deactivateAccountFun() {
		clickButton(deactivateIcon);
	}

	@FindBy(xpath = "//*[@title='تفعيل الحساب']")
	WebElement activateIcon;

	public void activateAccountFun() {
		clickButton(activateIcon);
	}

	@FindBy(xpath = "(//a[@title='حذف الحساب'])")
	WebElement deleteIcon;

	public void deleteIcon() {
//		((WebDriver) driver.findElement(By.tagName("a"))).get;
		clickButton(deleteIcon);
	}

}
