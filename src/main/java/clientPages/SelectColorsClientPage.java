package clientPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectColorsClientPage extends PageBase {

	public SelectColorsClientPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//*[text()='اختر الألوان']")
	@FindBy(xpath = "//li[@data-step='2']")
	public WebElement colorPageHeaderTxtCli;

	@FindBy(xpath = "//img[@class='responsive-img contest-image-frame']")
	WebElement colorCli;

	public void selectColorFun() {
		clickButton(colorCli);
	}
}
