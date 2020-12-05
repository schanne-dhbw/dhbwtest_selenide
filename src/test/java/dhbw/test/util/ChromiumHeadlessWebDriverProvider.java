package dhbw.test.util;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Driver configuration to use headless Chromium on Linux for UI tests.
 */
public class ChromiumHeadlessWebDriverProvider implements WebDriverProvider 
{
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) 
    {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1200");
        chromeOptions.addArguments("--disable-qpu");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.setCapability("download.default_directory", System.getProperty("java.io.tmpdir"));
        chromeOptions.merge(capabilities);
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "/usr/bin/chromedriver");
        return new ChromeDriver(chromeOptions); 
    }
}
