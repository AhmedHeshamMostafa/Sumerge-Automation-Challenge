package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DatePicker;
import utils.Generic;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class HomePage extends DatePicker {
    private final WebDriver driver;

    private final By locationTxtBx = By.id(":rh:");
    private final By datePicker = By.cssSelector("[data-testid=\"searchbox-dates-container\"]");
    private final By curNxtMonthYearTxt = By.cssSelector(".e7addce19e.af236b7586");
    private final By nextMonthBtn = By.cssSelector("button[aria-label=\"Next month\"]");
    private final By curAndNxtMonthTables = By.className("b8fcb0c66a");
    private final By searchBtn = By.cssSelector("button[type=\"submit\"]");


    public HomePage(WebDriver userDriver) {
        this.driver = userDriver;
    }

    public void enterDates(String checkInDate, String checkOutDate) {
        byte curMonthNum = 0;
        LocalDate today = LocalDate.now();
        String todayStr = today.toString();
        if (!DatePicker.isDateValid(todayStr, checkInDate)) {
            throw new IllegalArgumentException("Check-in date must be today or a date in the future!");
        } else if (!DatePicker.isDateValid(todayStr, checkOutDate)) {
            throw new IllegalArgumentException("Check-out date must be today or a date in the future!");
        } else if (!DatePicker.isDateValid(checkInDate, checkOutDate)) {
            throw new IllegalArgumentException("Check-out date must equal or be later than the check-in date!");
        } else {
            String[] checkInDateParts = checkInDate.split("-");
            String[] todayParts = todayStr.split("-");
            int todayYear = Integer.parseInt(todayParts[0]);
            if(Integer.parseInt(checkInDateParts[0]) > todayYear+1){
                throw new IllegalArgumentException("Check-in year must be at most next year!");
            }

            Generic.clickElement(driver, datePicker);

            List<WebElement> curNxtMonthYearLst = driver.findElements(curNxtMonthYearTxt);
            String[] checkOutDateParts = checkOutDate.split("-");

            while ((Integer.parseInt(checkInDateParts[0]) > DatePicker.getCalYearNum(driver, curNxtMonthYearLst.get(0)))
            &&    (Integer.parseInt(checkInDateParts[0]) > DatePicker.getCalYearNum(driver, curNxtMonthYearLst.get(1)))) {
                Generic.clickElement(driver, nextMonthBtn);
                curNxtMonthYearLst = driver.findElements(curNxtMonthYearTxt);
            }
            while(Integer.parseInt(checkInDateParts[1]) > DatePicker.getCalMonthNum(driver, curNxtMonthYearLst.get(1)))
             {
                Generic.clickElement(driver, nextMonthBtn);
                curNxtMonthYearLst = driver.findElements(curNxtMonthYearTxt);
            }

            while ((Integer.parseInt(checkOutDateParts[0]) > DatePicker.getCalYearNum(driver, curNxtMonthYearLst.get(0)))
            &&    (Integer.parseInt(checkOutDateParts[0]) > DatePicker.getCalYearNum(driver, curNxtMonthYearLst.get(1)))) {
                Generic.clickElement(driver, nextMonthBtn);
                curNxtMonthYearLst = driver.findElements(curNxtMonthYearTxt);
            }
            while(Integer.parseInt(checkOutDateParts[1]) > DatePicker.getCalMonthNum(driver, curNxtMonthYearLst.get(1))){
                Generic.clickElement(driver, nextMonthBtn);
                curNxtMonthYearLst = driver.findElements(curNxtMonthYearTxt);
            }

            /* The current month table is tablesList.get(0)
               The next month table is tablesList.get(1) */
            List<WebElement> tablesList = driver.findElements(curAndNxtMonthTables);
            List<WebElement> curMonthDays = tablesList.get(0).findElements(By.tagName("td"));
            List<WebElement> nxtMonthDays = tablesList.get(1).findElements(By.tagName("td"));
            WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(2));

            if((Integer.parseInt(checkInDateParts[1])) == DatePicker.getCalMonthNum(driver, curNxtMonthYearLst.get(0))){
                for(WebElement day : curMonthDays){
                    if(!day.getText().isEmpty()){
                        if(Integer.parseInt(checkInDateParts[2]) == Integer.parseInt(day.getText().trim())){
                            WebElement dayElement = waitTime.until(ExpectedConditions.elementToBeClickable(day));
                            dayElement.click();
                        }
                    }
                }
            }
            else if((Integer.parseInt(checkInDateParts[1])) == DatePicker.getCalMonthNum(driver, curNxtMonthYearLst.get(1))){
                for(WebElement day : nxtMonthDays){
                    if(!day.getText().isEmpty()){
                        if(Integer.parseInt(checkInDateParts[2]) == Integer.parseInt(day.getText().trim())){
                            WebElement dayElement = waitTime.until(ExpectedConditions.elementToBeClickable(day));
                            dayElement.click();
                        }
                    }
                }
            }


            if((Integer.parseInt(checkOutDateParts[1])) == DatePicker.getCalMonthNum(driver, curNxtMonthYearLst.get(0))){
                for(WebElement day : curMonthDays){
                    if(!day.getText().isEmpty()){
                        if(Integer.parseInt(checkOutDateParts[2]) == Integer.parseInt(day.getText().trim())){
                            WebElement dayElement = waitTime.until(ExpectedConditions.elementToBeClickable(day));
                            dayElement.click();
                        }
                    }
                }
            }
            else if((Integer.parseInt(checkOutDateParts[1])) == DatePicker.getCalMonthNum(driver, curNxtMonthYearLst.get(1))){
                for(WebElement day : nxtMonthDays){
                    if(!day.getText().isEmpty()){
                        if(Integer.parseInt(checkOutDateParts[2]) == Integer.parseInt(day.getText().trim())){
                            WebElement dayElement = waitTime.until(ExpectedConditions.elementToBeClickable(day));
                            dayElement.click();
                        }
                    }
                }
            }
        }
    }

    /****************************************************************************************************/
    /****************************************************************************************************/

    public void enterDestination(String dest){
        Generic.typeIntoTxtBx(driver, locationTxtBx, dest);
    }

    /****************************************************************************************************/
    /****************************************************************************************************/

    public SearchResultPage clickSearchBtn(){
        Generic.clickElement(driver, searchBtn);
        return new SearchResultPage(driver);
    }

}



        //driver.findElement(datePicker).click();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(checkInDatePick));
//        element.sendKeys(checkInDate);


//    public void enterCheckOutDate(){
//        driver.findElement(checkOutDatePick).click();
////        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
////        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(checkInDatePick));
////        element.sendKeys(checkOutDate);
//    }

