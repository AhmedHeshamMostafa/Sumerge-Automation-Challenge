package testCases;

import base.BaseTest;
import org.testng.annotations.Test;
import utils.DatePicker;

public class Initial extends BaseTest {
    /* Provide both the check-in date and the check-out date in the form of string.
    The string should be in the format "YYYY-MM-DD" */
    /* Here I provided check-in date as 1 October 2026, not 1 October 2024 nor 1 October 2025
    because the date picker in the website does NOT allow dates in the past */
    private String checkInDate = "2026-10-1";
    private String checkOutDate = "2026-10-14";
    private String destination = "Alexandria";
    private String hotelName = "Tolip Hotel Alexandria";
    @Test
    public void initialWork(){
        home.enterDestination(destination);
        home.enterDates(checkInDate, checkOutDate);
        var searchResPg = home.clickSearchBtn();
        searchResPg.seeAvailability(hotelName);
    }
}
