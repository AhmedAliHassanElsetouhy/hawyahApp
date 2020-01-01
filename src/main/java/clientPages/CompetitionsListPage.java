package clientPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CompetitionsListPage extends PageBase{

	public CompetitionsListPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//*[text()='نوع التصميم']")
	public WebElement designTypeCol;
	
	@FindBy(xpath="//*[text()='فئة المسابقة']")
	public WebElement competitionCatCol;
	
	@FindBy(xpath="//*[text()='عنوان الطلب']")
	public WebElement requestTitleCol;
	
	@FindBy(xpath="//*[text()='إجمالي المبلغ']")
	public WebElement totalAmountCol;
	
	@FindBy(xpath="//*[text()='بدئت بتاريخ']")
	public WebElement startDateCol;
	
	@FindBy(xpath="//*[text()='الحالة']")
	public WebElement statusCol;

	
}
