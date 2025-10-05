package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import utils.Generic;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class DetailsPage {
    private final WebDriver driver;

    private final By amountDrpDn = By.cssSelector(".hprt-nos-select.js-hprt-nos-select");
    private final By iWillReservBtn = By.cssSelector("[class=\"bui-button__text js-reservation-button__text\"]");
    private final By availabilityDtls = By.className("be2db1c937");

    List<String> datesList;

    public DetailsPage(WebDriver userDriver) {
        this.driver = userDriver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(availabilityDtls));
        List<WebElement> availDtlsList = driver.findElements(availabilityDtls);
        String DatesTxt = availDtlsList.get(0).getText();
        datesList = Generic.extractDates(DatesTxt);
    }

    public ConfirmPage selectAmountAndReserve(byte amount){
//        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofMillis(200)).ignoring(NoSuchElementException.class).ignoring(TimeoutException.class);
//        WebElement element = wait.until(driver-> driver.findElement(amountDrpDn));

//        WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(30));
//        waitTime.until(ExpectedConditions.presenceOfElementLocated(amountDrpDn));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(driver.findElement(amountDrpDn)).perform();
//        Actions actions = new Actions(driver);
//        actions.sendKeys(Keys.PAGE_DOWN).perform();
//        actions.sendKeys(Keys.PAGE_DOWN).perform();
//        actions.sendKeys(Keys.PAGE_DOWN).perform();
//        actions.sendKeys(Keys.PAGE_DOWN).perform();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0, 1000)");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
//        // diagnostics - run before the failing code
//        try {
//            // 🟨 Debug capture block
//            String pageSource = driver.getPageSource();
//            Files.write(Paths.get("debug_failure_page.html"), pageSource.getBytes(StandardCharsets.UTF_8));
//
//            File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            Files.copy(scr.toPath(), Paths.get("debug_failure_screenshot.png"), StandardCopyOption.REPLACE_EXISTING);
//
//            System.out.println("✅ Saved debug_failure_page.html and debug_failure_screenshot.png");
//        } catch (Exception e) {
//            System.out.println("⚠️ Failed to save debug artifacts: " + e.getMessage());
//        }

        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(amountDrpDn));
        Select amountSelect = new Select(dropdown);
        amountSelect.selectByIndex(amount);
        Generic.clickElement(driver, iWillReservBtn);
        return new ConfirmPage(driver);
    }

    public boolean isCheckInDateCorrect(String checkInDate){
        return Generic.isDateCorrect(datesList.get(0), checkInDate);
    }

    public boolean isCheckOutDateCorrect(String checkOutDate){
        return Generic.isDateCorrect(datesList.get(1), checkOutDate);
    }
}
