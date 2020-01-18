package adminPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class DesignersAdminPage extends PageBase {

	public DesignersAdminPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "q_first_name_cont")
	WebElement firstNameTxtBoxDes;

	@FindBy(id = "q_last_name_cont")
	WebElement lastNameTxtBoxDes;

	@FindBy(id = "q_screen_name_cont")
	WebElement displayNameTxtBoxDes;

	@FindBy(id = "q_email_cont")
	WebElement emailTxtBoxDes;

	@FindBy(id = "q_account_status_eq")
	WebElement accountStatusListDes;

	@FindBy(id = "q_created_at_gteq")
	WebElement createdFromDateTxtBoxDes;

	@FindBy(id = "q_created_at_lteq")
	WebElement createdToDateTxtBoxDes;

	@FindBy(xpath = "//input[@value='search']")
	public WebElement searchBtnDes;

	@FindBy(xpath = "//a[@href='https://hawyah-dev.herokuapp.com/admin/designers?locale=en']")
	WebElement clearBtnDes;

	@FindBy(xpath = "//span[@class='label label-sm label-danger']")
	public List<WebElement> pendingStatusDes;

	public void openPendingStatusFunDes(int index) {
		selectSpecificIcon(pendingStatusDes, index);
	}

	@FindBy(xpath = "//span[@class='label label-sm label-success']")
	public List<WebElement> activeStatusDes;

	public void openActiveStatusFunDes(int index) {
		selectSpecificIcon(activeStatusDes, index);
	}

	@FindBy(xpath = "//a[@class='btn btn-success btn-xs']")
	public List<WebElement> activateAccountBtnDes;

	@FindBy(xpath = "//span[text()='disabled']")
	public List<WebElement> disabledStatusDes;

	@FindBy(xpath = "//a[@class='btn btn-danger btn-xs']")
	public List<WebElement> deactivatedAccountBtnDes;

	@FindBy(id = "q_confirmed_true")
	WebElement confirmFCheckBoxDes;

	public void swapConfirmCheckBoxFunDes() {
		clickButton(confirmFCheckBoxDes);
	}

	public void activateAccountFunDes(int activatedIndex) {
		selectSpecificIcon(activateAccountBtnDes, activatedIndex);
	}

	public void deActivateAccountFunDes(int deactivatedIndex) {
		selectSpecificIcon(deactivatedAccountBtnDes, deactivatedIndex);
	}

	public void searchFunDes() {
		clickButton(searchBtnDes);
	}

	public void clearFunDes() {
		clickButton(clearBtnDes);
	}

	public void searchUserFunDes(String fName, String lName, String displayName, String email, int statusIndex,
			String fromdDate, String toDate) {
		setTextElementText(firstNameTxtBoxDes, fName);
		setTextElementText(lastNameTxtBoxDes, lName);
		setTextElementText(displayNameTxtBoxDes, displayName);
		setTextElementText(emailTxtBoxDes, email);
		selectItemWithIndex(accountStatusListDes, statusIndex);
		setTextElementText(createdFromDateTxtBoxDes, fromdDate);
		setTextElementText(createdToDateTxtBoxDes, toDate);
	}

	public void searchStatus(int statusIndex) {
		selectItemWithIndex(accountStatusListDes, statusIndex);
	}

	public void fromToDateFunDes(String today) {
		setTextElementText(createdFromDateTxtBoxDes, today);
		setTextElementText(createdToDateTxtBoxDes, today);
	}

	public void accountStatusFunDes(int accountStatusIndex) {
		selectItemWithIndex(accountStatusListDes, accountStatusIndex);
	}
}