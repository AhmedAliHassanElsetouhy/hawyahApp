package adminPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class SettingsAdminPage extends PageBase {

	public SettingsAdminPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//tr")
	public List<WebElement> settingsRecords;

	// @FindBy(xpath = "//*[@class='btn btn-primary btn-raised btn-danger']")
	@FindBy(xpath = "//*[@href='/admin/settings?locale=en']")
	WebElement cancelSettignsRecordsFormBtn;

	public void cancelSettignsRecordsFormFun() {
		clickButton(cancelSettignsRecordsFormBtn);
	}

	public void openSettingsRecordFun(int settingIndex) {
		selectSpecificIcon(settingsRecords, settingIndex);
	}
}
