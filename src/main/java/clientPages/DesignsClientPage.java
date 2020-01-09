package clientPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DesignsClientPage extends PageBase {

	public DesignsClientPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(partialLinkText = "تصميم هوية أساسية")
	public WebElement basicIdentityLinkCli;

	@FindBy(partialLinkText = "تصميم أوراق رسمية")
	public WebElement designOfficialPaperLinkCli;

	@FindBy(partialLinkText = "تعديل هوية أساسية")
	public WebElement editBasicIdentityLinkCli;

	@FindBy(partialLinkText = "تعديل شعار")
	public WebElement editLogoLinkCli;

	@FindBy(partialLinkText = "تصميم أظرف")
	public WebElement envelopDesignLinkCli;

	@FindBy(partialLinkText = "تصميم كرت أعمال")
	public WebElement businessCardDesignLinkCli;

	public void openBasicIdentityFun() {
		clickButton(basicIdentityLinkCli);
	}

	@FindBy(partialLinkText = "تصميم شعار")
	WebElement designLogoLinkCli;
}