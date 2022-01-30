package zavrichko.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import zavrichko.helpers.Attach;

import java.net.MalformedURLException;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeAll
    static void setUp() throws MalformedURLException {
      //  Configuration.remote = "http://149.154.70.38:4444/wd/hub";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "95.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());


    }

   @AfterEach
   void addAttachments() {
       Attach.screenshotAs("Last screenshot");
       Attach.pageSource();
       Attach.browserConsoleLogs();
//       Attach.addVideo();
       closeWebDriver();
    }
}
