package clientTests;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.Helper;

public class TestBase {
	public static WebDriver driver;

	public static String downloadPath = System.getProperty("user.dir") + "\\Downloads";

	public static FirefoxOptions firefoxOption() {
		FirefoxOptions option = new FirefoxOptions();
		option.addPreference("browser.download.folderList", 2);
		option.addPreference("browser.download.dir", downloadPath);
		option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
		option.addPreference("browser.download.manager.showWhenStarting", false);
		option.addPreference("pdfjs.disabled", true);
		return option;
	}

	public static ChromeOptions chromeOption() {
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default.content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		options.setExperimentalOption("prefs", chromePrefs);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.addArguments("--disable-notifications");
		return options;
	}

	public static DesiredCapabilities capabilitiesInternetExplorer() {

		// it is used to define IE capability
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

		capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		return capabilities;
	}

	// @BeforeSuite
	@BeforeClass
	@Parameters({ "browser" })
	public void startDriver(@Optional("chrome") String browser) {
		// public void startDriver(@Optional String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			driver = new ChromeDriver(chromeOption());
		}

		else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
			driver = new FirefoxDriver(firefoxOption());
		}

		else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		else if (browser.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}

		// Check if parameter passed as 'Edge'
		else if (browser.equalsIgnoreCase("Edge")) {
			// set path to Edge.exe
			System.setProperty("webdriver.edge.driver", ".\\MicrosoftWebDriver.exe");
			// create Edge instance
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.navigate().to("https://hawyah-dev.herokuapp.com/?locale=ar");
		// LoginPage loginPage;
		// String userNameText = "yasser@emisk.org";
		// String PasswordText = "Yasso?2468";
		// loginPage = new LoginPage(driver);
		// loginPage.loginFun(userNameText, PasswordText);
	}

	@AfterSuite
	public void stopDriver() {
		// driver.quit();
	}

	@AfterMethod
	public void sleepMinute() throws InterruptedException {
//		Thread.sleep(1000);
	}

	@AfterClass
	public void quitBrowserAfterClass() {
		// driver.quit();
	}

	// take screenshot when test case fail and add it in the Screenshot folder
	@AfterMethod
	public void screenshotOnFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("Failed!");
			System.out.println("Taking Screenshot....");
			Helper.captureScreenshot(driver, result.getName());
		}
	}
}