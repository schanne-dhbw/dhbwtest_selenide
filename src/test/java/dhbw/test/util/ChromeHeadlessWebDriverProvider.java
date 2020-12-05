package dhbw.test.util;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Driver configuration to use headless Chrome on Windows for UI tests.
 */
public class ChromeHeadlessWebDriverProvider implements WebDriverProvider 
{
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) 
    {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1200");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.setCapability("download.default_directory", System.getProperty("java.io.tmpdir"));
        chromeOptions.merge(capabilities);
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "C:\\tools\\selenium\\chromedriver.exe");
        return new ChromeDriver(chromeOptions); 
    }
}
