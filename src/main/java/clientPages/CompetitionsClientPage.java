package clientPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CompetitionsClientPage extends PageBase {

	public CompetitionsClientPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//*[text()='مسابقاتي']")
	// public WebElement competitionHeaderTxt;

	@FindBy(xpath = "//div[@class='row']")
	public WebElement competitionsCli;

	@FindBy(xpath = "//div[@class='col s12 m4 l3']")
	List<WebElement> competitionCli;

	public void openCompetitionFun(int index) {
		selectSpecificIcon(competitionCli, index);
	}

	@FindBy(xpath = "//*[text()='أنتهت']")
	List<WebElement> finishedCompetitionCli;

	public void openFinishedFun(int index) {
		selectSpecificIcon(finishedCompetitionCli, index);
	}
	
	@FindBy(xpath="//*[text()='مرحلة تسليم العمل النهائي']")
	List<WebElement> deliverFinalWorkCompetitionCli;
	
	public void openDeliverFinalWorkCompetitionFun(int index) {
		selectSpecificIcon(deliverFinalWorkCompetitionCli, index);
	}
	
	@FindBy(xpath="//*[text()='في إنتظار التحويل']")
	List<WebElement> waitingTransferCompetitionsCli;
	
	public void openwaitingTransferCompetitionsFun(int index) {
		selectSpecificIcon(waitingTransferCompetitionsCli, index);
	}
	
	@FindBy(xpath="//*[text()='لم يتم إكمال بيانات المسابقة']")
	List<WebElement> notCompletedCompetitionsCli;
	
	public void openNotCompletedCompetitionsFun(int index) {
		selectSpecificIcon(notCompletedCompetitionsCli, index);
	}
	
	@FindBy(xpath = "//*[text()='مرحلة إستقبال التصاميم']")
	List<WebElement> recievedDesignsCompetitionCli;

	public void openRecievedDesignsCompetitionsFun(int index) {
		selectSpecificIcon(recievedDesignsCompetitionCli, index);
	}
	
	@FindBy(partialLinkText = "الملفات")
	public WebElement filesLinkCli;

	@FindBy(xpath = "//legend[@class='blue-text']")
	public WebElement filesHeaderCli;

	@FindBy(partialLinkText = "عرض الاتفاقية")
	WebElement showAgreementLinkCli;

	// @FindBy(id = "handoverAgreementViewModal")
	@FindBy(xpath = "//div[@class='modal open']")
	public WebElement agreementPopupCli;

	@FindBy(xpath = "//*[text()='إغلاق']")
	WebElement closeBtnCli;

	@FindBy(xpath = "//legend[@class='purple-text']")
	WebElement designerSig;

	@FindBy(xpath = "//legend[@class='blue-text']")
	WebElement cliendSig;
	
	public void openFilesAndOpenAgreementDesignerOnlyFun() {
		clickButton(filesLinkCli);
		Assert.assertTrue(filesHeaderCli.isDisplayed());
		Assert.assertTrue(showAgreementLinkCli.isDisplayed());
		clickButton(showAgreementLinkCli);
		Assert.assertTrue(agreementPopupCli.isDisplayed());
		Assert.assertTrue(designerSig.isDisplayed());
		clickButton(closeBtnCli);
	}
	
	public void openFiles() {
		clickButton(filesLinkCli);
	}	
}