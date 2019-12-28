package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CompetitionsPage extends PageBase {

	public CompetitionsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//*[text()='مسابقاتي']")
	// public WebElement competitionHeaderTxt;

	@FindBy(xpath = "//div[@class='row']")
	public WebElement competitions;
}
