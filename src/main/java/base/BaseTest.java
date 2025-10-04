package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

public class BaseTest {
    private WebDriver driver;
    protected HomePage home;
    private final By disPopUpBtn = By.cssSelector("[aria-label=\"Dismiss sign-in info.\"]");

    @BeforeClass
    public void testClassSetUp(){
        //FirefoxOptions options = new FirefoxOptions();
        //options.addArguments("--headless");
        //options.addArguments("--disable-gpu");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        home = testMethodSetUp();
    }

    @BeforeMethod
    public HomePage testMethodSetUp(){
        driver.get("https://www.booking.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(disPopUpBtn));
        return new HomePage(driver);
    }

    //@AfterSuite
//    public void tearDown(){
//        driver.quit();
//    }


}

