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
import org.testng.annotations.*;
import pages.HomePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

public class BaseTest {
    private WebDriver driver;
    static protected HomePage home;
    private final By disPopUpBtn = By.cssSelector("[aria-label=\"Dismiss sign-in info.\"]");

    @BeforeClass
    public void testClassSetUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }


    @BeforeMethod
    public void testMethodSetUp(){
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> tabs = new ArrayList<>(windowHandles);
        driver.switchTo().window(tabs.get(0));
        driver.get("https://www.booking.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(disPopUpBtn));
        home = new HomePage(driver);
    }

    @AfterMethod
    public void tearDownMethod(){
        driver.close();
    }

    @AfterClass
    public void tearDownClass(){
        driver.quit();
    }
}

