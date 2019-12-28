package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DesignsPage extends PageBase {

	public DesignsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(partialLinkText = "تصميم هوية أساسية")
	public WebElement basicIdentityLink;

	@FindBy(partialLinkText = "تصميم أوراق رسمية")
	public WebElement designOfficialPaperLink;

	@FindBy(partialLinkText = "تعديل هوية أساسية")
	public WebElement editBasicIdentityLink;

	@FindBy(partialLinkText = "تعديل شعار")
	public WebElement editLogoLink;

	@FindBy(partialLinkText = "تصميم أظرف")
	public WebElement envelopDesignLink;

	@FindBy(partialLinkText = "تصميم كرت أعمال")
	public WebElement businessCardDesignLink;

	public void openBasicIdentityFun() {
		clickButton(basicIdentityLink);
	}

	@FindBy(partialLinkText = "تصميم شعار")
	WebElement designLogoLink;

}
