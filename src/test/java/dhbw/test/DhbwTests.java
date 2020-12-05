package dhbw.test;

import static com.codeborne.selenide.Condition.visible;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.addListener;
import dhbw.test.util.Highlighter;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

/**
 * Dummy DHBW test suite.
 * - Selenide configuration.
 * - Selenide example tests.
 */
public class DhbwTests 
{
    /** Default configuration to test DHBW website. */ 
    protected String DHBW_URL = "https://www.karlsruhe.dhbw.de/";
    
    /** Default configuration for headless chromium on raspi. */
    protected String WEB_DRIVER_PROVIDER =  "dhbw.test.util.ChromiumHeadlessWebDriverProvider";
    
    /** Overwrite configuration parameters from command line arguments. */
    public DhbwTests()
    {
        DHBW_URL = System.getProperty("DHBW_URL", DHBW_URL);
        WEB_DRIVER_PROVIDER = System.getProperty("WEB_DRIVER_PROVIDER", WEB_DRIVER_PROVIDER);
        if (!WEB_DRIVER_PROVIDER.contains("."))
        {
            WEB_DRIVER_PROVIDER = "dhbw.test.util." + WEB_DRIVER_PROVIDER + "WebDriverProvider";
        }
        System.out.println("Config: " + DHBW_URL + " " + WEB_DRIVER_PROVIDER);
    }
    
    /** Selenide configuration to start a test. */
    protected void configureBrowserWithSelenIde(String baseUrl, String proxyHost, String proxyPort) 
    {
        Configuration.timeout = 10000;
        Configuration.baseUrl = baseUrl;
        Configuration.startMaximized = false;
        Configuration.browser = WEB_DRIVER_PROVIDER;
        Configuration.browserPosition = "200x20";
        Configuration.browserSize = "1440x1024";
        Configuration.reportsFolder = "target/reports/";
        Configuration.reportsUrl = "file:///root/dhbwtest_selenide/target/reports/";
        Configuration.proxyEnabled = (proxyHost != null && proxyPort != null);
        if (proxyHost != null && proxyPort != null)
        {
            Configuration.proxyHost = proxyHost;
            Configuration.proxyPort = Integer.parseInt(proxyPort);
            Configuration.fileDownload = FileDownloadMode.PROXY;
        }
        else
        {
            Configuration.fileDownload = FileDownloadMode.FOLDER;
        }
        addListener(new Highlighter());
    }

    @Test
    public void testOffline() 
    {
        String act = "test";
        String exp = "test";
        assertEquals(exp, act, "String compare");
    }

    @Test
    public void testWirtschaftsinformatik() 
    {
        configureBrowserWithSelenIde(DHBW_URL, null, null);
        open("/");
        $(byText("Akzeptieren")).shouldBe(visible).click();
        assertWirtschaftsinformatik();
    }

    private void assertWirtschaftsinformatik()
    {
        $(byText("Menü")).shouldBe(visible).click();
        $(byText("Bachelorstudiengänge")).shouldBe(visible).click();
        $(byText("Wirtschaftsinformatik")).shouldBe(visible).click();
        $(By.xpath("/html/body/main/div/div[3]/div/div[1]/ul/li[6]/a")).shouldBe(visible).click();
        $(byText("Ratz, Prof. Dr. Dietmar")).shouldBe(visible).click();
        assertThat("Telefonnummer", $(byText("+49.721.9735-954")).is(visible));
    }    
}
