package clientPages;

import java.util.List;

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

	@FindBy(xpath = "//div[@class='col s12 m4 l3']")
	List<WebElement> competition;

	public void openCompetitionFun(int index) {
		selectSpecificIcon(competition, index);
	}

	@FindBy(xpath = "//*[text()='أنتهت']")
	List<WebElement> finishedCompetition;

	public void openFinishedFun(int index) {
		selectSpecificIcon(finishedCompetition, index);
	}
	
	@FindBy(xpath="//*[text()='مرحلة تسليم العمل النهائي']")
	List<WebElement> deliverFinalWorkCompetition;
	
	public void openDeliverFinalWorkCompetitionFun(int index) {
		selectSpecificIcon(deliverFinalWorkCompetition, index);
	}

}
