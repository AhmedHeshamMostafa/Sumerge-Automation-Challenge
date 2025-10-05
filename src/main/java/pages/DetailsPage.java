package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import utils.Generic;

import java.time.Duration;
import java.util.List;


public class DetailsPage {
    private final WebDriver driver;

    private final By amountDrpDn = By.cssSelector(".hprt-nos-select.js-hprt-nos-select");
    private final By iWillReservBtn = By.cssSelector(".bui-button__text.js-reservation-button__text");
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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(amountDrpDn));
        Select amountSelect = new Select(dropdown);
        amountSelect.selectByIndex(amount);
        Generic.clickElement(driver, iWillReservBtn);
        return new ConfirmPage(driver);
    }

    /************************************************************************************************/
    /************************************************************************************************/

    public boolean isCheckInDateCorrect(String checkInDate){
        return Generic.isDateCorrect(datesList.get(0), checkInDate);
    }

    public boolean isCheckOutDateCorrect(String checkOutDate){
        return Generic.isDateCorrect(datesList.get(1), checkOutDate);
    }
}
