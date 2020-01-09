package designerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class MyStatsDesignerPage extends PageBase {

	public MyStatsDesignerPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//*[text()='المشاركات']")
	@FindBy(xpath = "//div[@class='black-text']")
	public WebElement shares;

	// @FindBy(xpath = "//*[text()='مرات الفوز']")
	@FindBy(xpath = "//div[@class='green-text']")
	public WebElement numOfWins;

	// @FindBy(xpath = "//*[text()='المبلغ المحصل']")
	@FindBy(xpath = "//div[@class='blue-text']")
	public WebElement totalCollectedNum;
}
