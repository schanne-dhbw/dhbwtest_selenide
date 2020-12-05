package dhbw.test.util;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Driver configuration to use Firefox on Windows for UI tests.
 */
public class FirefoxWebDriverProvider implements WebDriverProvider 
{
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) 
    {
        FirefoxProfile profile = new FirefoxProfile();

        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.dir", System.getProperty("java.io.tmpdir"));
        profile.setPreference("pdfjs.disabled", true);
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
        profile.setPreference("security.default_personal_cert", "Select Automatically");
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        profile.setPreference("trustAllSSLCertificates", true);

        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        capabilities.setAcceptInsecureCerts(true);

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.merge(capabilities);

        System.setProperty("webdriver.gecko.driver", "C:\\tools\\selenium\\geckodriver.exe");
        System.setProperty("webdriver.firefox.bin","C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        return new FirefoxDriver(firefoxOptions);
    }
}

