package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectDesignsPage extends PageBase {

	public SelectDesignsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//*[text()='اختر نوع التصميم']")
	@FindBy(xpath = "//li[@data-step='1']")
	public WebElement designPageHeaderTxt;

	@FindBy(xpath = "//img[@class='icon']")
	WebElement iconDesign;

	@FindBy(xpath = "//*[text()='السابق']")
	WebElement prevBtn;

	@FindBy(xpath = "//button[@value='متابعة']")
	WebElement nextBtn;

	public void selectIconDesign() {
		clickButton(iconDesign);
	}

	public void nextFun() {
		clickButton(nextBtn);
	}

	public void prevFun() {
		clickButton(prevBtn);
	}
}
