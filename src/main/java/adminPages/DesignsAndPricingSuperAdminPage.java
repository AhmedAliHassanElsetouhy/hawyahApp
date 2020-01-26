package adminPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import clientPages.PageBase;

public class DesignsAndPricingSuperAdminPage extends PageBase {

	public DesignsAndPricingSuperAdminPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// @FindBy(xpath = "//*[@class='table table-hover table-light']")
	@FindBy(xpath = "//tbody")
	// @FindBy(xpath = "//*[@class='portlet-body']")
	public List<WebElement> designsAndPricingPage1;

	@FindBy(xpath = "//*[@href='/admin/design_types?locale=en&page=2']")
	WebElement designsAndPricingPage2;

	@FindBy(xpath = "//*[text()='next']")
	WebElement nextDesignsAndPricingPage;

	public void openPricingAndDesignsPage2Fun() {
		// clickButton(designsAndPricingPage2);
		System.out.println(designsAndPricingPage1.size());

		for (WebElement webElement : designsAndPricingPage1) {
			String name = webElement.getText();
			System.out.println(name);
			// clickButton(nextDesignsAndPricingPage);
		}
	}
}