package pl.luxoft.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstSeleniumTest {

    private WebDriver driver;
    private final By PRIVACY_ACCEPT_BUTTON = By.xpath("/html/body/div[2]/div[1]/div[2]/div[3]/div[2]/button");

    @BeforeClass
    public void setUp() {
         driver = new ChromeDriver();
         driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


    private void acceptPrivacyPolicy() {
        //System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe")
        final By PRIVACY_MODAL_WINDOW_WRAPPER = By.className("alert");
        //final By PRIVACY_ACCEPT_BUTTON = By.cssSelector("button.width-full");


        driver.findElement(PRIVACY_MODAL_WINDOW_WRAPPER)
                .findElement(PRIVACY_ACCEPT_BUTTON)
                .click();
    }

    @Test
    public void openEmptyBasketTest() {
        driver.get("http://etsy.com");

        WebDriverWait wait = new WebDriverWait(driver, 5 );
        wait.until(x -> x.findElement(PRIVACY_ACCEPT_BUTTON));
        acceptPrivacyPolicy();

        //card click test
        driver.findElement(By.className("etsy-icon-card")).click();
        Assert.assertTrue(driver.getTitle().contains("Cart"));
    }

    @Test
    public void openSearchFieldTest() {
        final String searchQuery = "leather bag";
        //final By SEARCH_FIELD = By.id("global-enhancements-search-query");
        final By SEARCH_FIELD = By.name("search_query");
        final By SEARCH_SUGGESTIONS_LIST_FIRST = By.cssSelector("#global-enhancements-search-suggestions ul div.as-first");

        driver.get("http://etsy.com");
        acceptPrivacyPolicy();


        WebElement search_field = driver.findElement(SEARCH_FIELD);
        search_field.clear();
        search_field.sendKeys(searchQuery);
        search_field.sendKeys(Keys.ENTER);
        WebDriverWait waitForSuggestionsList = new WebDriverWait(driver, 5 );
        WebElement firstElement = waitForSuggestionsList.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_SUGGESTIONS_LIST_FIRST));
        firstElement.click();
//        Alert simpleAlert = driver.switchTo().alert();
//        simpleAlert.sendKeys(" alert: " + driver.getTitle());


        Assert.assertTrue(driver.getTitle().contains("Leather bag"));

    }
}
