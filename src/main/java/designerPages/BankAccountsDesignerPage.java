package designerPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class BankAccountsDesignerPage extends PageBase {

	public BankAccountsDesignerPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//*[@class='blue-text']")
	@FindBy(xpath = "//*[text()='ملاحظات بخصوص الحسابات البنكية']")
	public WebElement bankAccNotesHeader;

	@FindBy(xpath = "//*[@title='إلغاء تفعيل الحساب']")
	List<WebElement> deactivateIcon;

	public void deactivateAccountFun(int index) {
		// clickButton(deactivateIcon);
		selectSpecificIcon(deactivateIcon, index);
	}

	@FindBy(xpath = "//a[@title='تفعيل الحساب']")
	public List<WebElement> activateIcon;

	public void activateAccountFun(int index) {
		// clickButton(activateIcon);
		selectSpecificIcon(activateIcon, index);
	}

	@FindBy(xpath = "(//a[@title='حذف الحساب'])")
	public List<WebElement> deleteIcons;

	public void deleteIcon(int index) {
		// List<WebElement> allLinks = deleteIcons;
		// allLinks.get(index).click();
		selectSpecificIcon(deleteIcons, index);
	}

}
