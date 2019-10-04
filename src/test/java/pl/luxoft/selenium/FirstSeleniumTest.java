package pl.luxoft.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FirstSeleniumTest {

    @Test
    public void openChromeWebdriverTest() {
        //System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe")
        WebDriver driver = new ChromeDriver();
        driver.get("http://google.com");
        driver.quit();
    }
}
