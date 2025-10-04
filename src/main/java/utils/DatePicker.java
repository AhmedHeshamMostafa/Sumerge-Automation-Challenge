package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DatePicker {

    static protected boolean isDateValid(String earlierDate, String laterDate){
        boolean valid = true;

        String[] laterDateParts = laterDate.split("-");
        String[] earlierDateParts = earlierDate.split("-");

        if(Integer.parseInt(laterDateParts[0]) < Integer.parseInt(earlierDateParts[0])){
            valid = false;
        }
        else if(Integer.parseInt(laterDateParts[0]) == Integer.parseInt(earlierDateParts[0])){
            if(Integer.parseInt(laterDateParts[1]) < Integer.parseInt(earlierDateParts[1]))
            {
                valid = false;
            }
            else if(Integer.parseInt(laterDateParts[1]) == Integer.parseInt(earlierDateParts[1])){
                if(Integer.parseInt(laterDateParts[2]) < Integer.parseInt(earlierDateParts[2])){
                    valid = false;
                }
            }
        }

        return valid;
    }


    static protected byte getCalMonthNum(WebDriver driver, WebElement element){
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        byte calMonthNum = 0;
        String calMonthYearStr = element.getText();
        String[] calMonthYearParts = calMonthYearStr.split(" ");
        String calMonthStr = calMonthYearParts[0];
        for (byte i = 0; i < 12; i++){
            if (calMonthStr.equalsIgnoreCase(months[i])){
                calMonthNum = (byte) (i + 1);
            }
        }

        return calMonthNum;
    }


    static protected int getCalYearNum(WebDriver driver, WebElement element){
        String calMonthYearStr = element.getText();
        String[] calMonthYearParts = calMonthYearStr.split(" ");
        return Integer.parseInt(calMonthYearParts[1]);
    }

}
