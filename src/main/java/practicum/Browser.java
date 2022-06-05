package practicum;

import io.qameta.allure.Step;
import org.openqa.selenium.chrome.ChromeDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;


public class Browser {

    ChromeDriver driver;

    public Browser(String browser) {
        if (browser.equals("yandex")) {
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\yandexdriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        }
        driver = new ChromeDriver();
        setWebDriver(driver);
    }

    @Step("exit from browser")
    public void tearDown() {
        driver.quit();
    }

}
