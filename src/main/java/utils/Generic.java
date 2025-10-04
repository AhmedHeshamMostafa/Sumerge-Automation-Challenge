package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Generic {
    static public String getElementText(WebDriver driver, By elementLocator){
        WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement element = waitTime.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        return element.getText();
    }

    static public void clickElement(WebDriver driver, By elementLocator){
        WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement element = waitTime.until(ExpectedConditions.elementToBeClickable(elementLocator));
        element.click();
    }

    static public void typeIntoTxtBx(WebDriver driver, By elementLocator, String txtToType){
        WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement element = waitTime.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        element.clear();
        element.sendKeys(txtToType);
    }
}
