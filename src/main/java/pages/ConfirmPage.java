package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmPage{
    private final WebDriver driver;

    private final By hotelNameTxt = By.cssSelector("h1[class=\"e7addce19e\"]");

    public ConfirmPage(WebDriver userDriver) {
        this.driver = userDriver;
    }

    public String getHotelName(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(hotelNameTxt));
        return driver.findElement(hotelNameTxt).getText().trim();
    }
}
