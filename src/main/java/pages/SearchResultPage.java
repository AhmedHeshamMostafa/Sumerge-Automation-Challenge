package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SearchResultPage {
    private final WebDriver driver;

    private final By searchResultsTxts = By.cssSelector(".b87c397a13.a3e0b4ffd1");
    //private final By searchResultsTxts = By.xpath("//*[@class=\"b87c397a13 a3e0b4ffd1\"]");
    private final By loadMoreResultsBtn = By.cssSelector(".de576f5064.b46cd7aad7.d0a01e3d83.dda427e6b5.bbf83acb81.a0ddd706cc");
    private final By seeAvailabilityBtn = By.xpath("./ancestor::*[@class=\"f6e3a11b0d ae5dbab14d e95943ce9b d32e843a31\"]//*[@data-testid=\"availability-cta-btn\"]");


    public SearchResultPage(WebDriver userDriver) {
        this.driver = userDriver;
    }

    public DetailsPage seeAvailability(String hotelName){
        WebElement targetHotel = null;

        for(WebElement hotel : driver.findElements(searchResultsTxts)){
            if(hotel.getText().toLowerCase().contains(hotelName.trim().toLowerCase())){
                targetHotel = hotel;
                break;
            }
        }

        if(targetHotel == null){
            throw new IllegalArgumentException("Hotel you provided is not found!");
        }
        else{
            targetHotel.findElement(seeAvailabilityBtn).click();
//            /* Switching to the newly opened tab, just safer than relying on the browser doing that. */
//            Set<String> windowHandles = driver.getWindowHandles();
//            List<String> tabs = new ArrayList<>(windowHandles);
//            driver.switchTo().window(tabs.get(1));

            return new DetailsPage(driver);
        }
    }
}
