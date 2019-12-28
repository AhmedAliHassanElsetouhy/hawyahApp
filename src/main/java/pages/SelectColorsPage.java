package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectColorsPage extends PageBase {

	public SelectColorsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//*[text()='اختر الألوان']")
	@FindBy(xpath = "//li[@data-step='2']")
	public WebElement colorPageHeaderTxt;

	@FindBy(xpath = "//img[@class='responsive-img contest-image-frame']")
	WebElement color;

	public void selectColorFun() {
		clickButton(color);
	}
}
