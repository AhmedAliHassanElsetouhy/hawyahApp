package adminPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class UsersAdminPage extends PageBase {

	public UsersAdminPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "q_first_name_cont")
	WebElement firstNameTxtBox;

	@FindBy(id = "q_last_name_cont")
	WebElement lastNameTxtBox;

	@FindBy(id = "q_screen_name_cont")
	WebElement displayNameTxtBox;

	@FindBy(id = "q_email_cont")
	WebElement emailTxtBox;

	@FindBy(id = "q_account_status_eq")
	WebElement accountStatusList;

	@FindBy(id = "q_created_at_gteq")
	WebElement createdFromDateTxtBox;

	@FindBy(id = "q_created_at_lteq")
	WebElement createdToDateTxtBox;

	@FindBy(xpath = "//input[@value='search']")
	public WebElement searchBtn;

	@FindBy(xpath = "//a[@href='https://hawyah-dev.herokuapp.com/admin/users?locale=en']")
	WebElement clearBtn;

	@FindBy(xpath = "//span[@class='label label-sm label-danger']")
	public List<WebElement> pendingStatus;

	public void openPendingStatusFun(int index) {
		selectSpecificIcon(pendingStatus, index);
	}

	@FindBy(xpath = "//span[@class='label label-sm label-success']")
	public List<WebElement> activeStatus;

	public void openActiveStatusFun(int index) {
		selectSpecificIcon(activeStatus, index);
	}

	@FindBy(xpath = "//a[@class='btn btn-success btn-xs']")
	public List<WebElement> activateAccountBtn;

	@FindBy(xpath = "//span[text()='disabled']")
	public List<WebElement> disabledStatus;

	@FindBy(xpath = "//a[@class='btn btn-danger btn-xs']")
	public List<WebElement> deactivatedAccountBtn;

	@FindBy(id = "q_confirmed_true")
	WebElement confirmFCheckBox;

	public void swapConfirmCheckBoxFun() {
		clickButton(confirmFCheckBox);
	}

	public void activateAccountFun(int activatedIndex) {
		selectSpecificIcon(activateAccountBtn, activatedIndex);
	}

	public void deActivateAccountFun(int deactivatedIndex) {
		selectSpecificIcon(deactivatedAccountBtn, deactivatedIndex);
	}

	public void searchFun() {
		clickButton(searchBtn);
	}

	public void clearFun() {
		clickButton(clearBtn);
	}

	public void searchUserFun(String fName, String lName, String displayName, String email, int statusIndex,
			String fromdDate, String toDate) {
		setTextElementText(firstNameTxtBox, fName);
		setTextElementText(lastNameTxtBox, lName);
		setTextElementText(displayNameTxtBox, displayName);
		setTextElementText(emailTxtBox, email);
		selectItemWithIndex(accountStatusList, statusIndex);
		setTextElementText(createdFromDateTxtBox, fromdDate);
		setTextElementText(createdToDateTxtBox, toDate);
	}

	public void fromToDateFun(String today) {
		setTextElementText(createdFromDateTxtBox, today);
		setTextElementText(createdToDateTxtBox, today);
	}

	public void accountStatusFun(int accountStatusIndex) {
		selectItemWithIndex(accountStatusList, accountStatusIndex);
	}
}