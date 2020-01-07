package clientPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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
	
	@FindBy(xpath = "//*[text()='مرحلة إستقبال التصاميم']")
	List<WebElement> recievedDesignsCompetition;

	public void openRecievedDesignsCompetitionsFun(int index) {
		selectSpecificIcon(recievedDesignsCompetition, index);
	}
	
	@FindBy(partialLinkText = "الملفات")
	public WebElement filesLink;

	@FindBy(xpath = "//legend[@class='blue-text']")
	public WebElement filesHeader;

	@FindBy(partialLinkText = "عرض الاتفاقية")
	WebElement showAgreementLink;

	// @FindBy(id = "handoverAgreementViewModal")
	@FindBy(xpath = "//div[@class='modal open']")
	public WebElement agreementPopup;

	@FindBy(xpath = "//*[text()='إغلاق']")
	WebElement closeBtn;

	@FindBy(xpath = "//legend[@class='purple-text']")
	WebElement designerSig;

	@FindBy(xpath = "//legend[@class='blue-text']")
	WebElement cliendSig;
	
	public void openFilesAndOpenAgreementDesignerOnlyFun() {
		clickButton(filesLink);
		Assert.assertTrue(filesHeader.isDisplayed());
		Assert.assertTrue(showAgreementLink.isDisplayed());
		clickButton(showAgreementLink);
		Assert.assertTrue(agreementPopup.isDisplayed());
		Assert.assertTrue(designerSig.isDisplayed());
		clickButton(closeBtn);
	}
	
	public void openFiles() {
		clickButton(filesLink);
	}
	
}
