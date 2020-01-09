package clientPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectDesignsClientPage extends PageBase {

	public SelectDesignsClientPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//*[text()='اختر نوع التصميم']")
	@FindBy(xpath = "//li[@data-step='1']")
	public WebElement designPageHeaderTxtCli;

	@FindBy(xpath = "//img[@class='icon']")
	WebElement iconDesignCli;

	@FindBy(xpath = "//*[text()='السابق']")
	WebElement prevBtnCli;

	@FindBy(xpath = "//button[@value='متابعة']")
	WebElement nextBtnCli;

	public void selectIconDesign() {
		clickButton(iconDesignCli);
	}

	public void nextFun() {
		clickButton(nextBtnCli);
	}

	public void prevFun() {
		clickButton(prevBtnCli);
	}
}