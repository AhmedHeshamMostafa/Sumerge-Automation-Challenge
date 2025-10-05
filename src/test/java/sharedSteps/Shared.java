package sharedSteps;

import base.BaseTest;
import pages.DetailsPage;
import pages.SearchResultPage;

public class Shared extends BaseTest {
    /* Provide both the check-in date and the check-out date in the form of string.
    The string should be in the format "YYYY-MM-DD" */
    /* Here I provided check-in date as 1 October 2026, not 1 October 2024 nor 1 October 2025
    because the date picker in the website does NOT allow dates in the past */

    static protected SearchResultPage searchResPg;
    static protected DetailsPage detailsPg;

    static public void stepsToShare(String checkInDate, String checkOutDate, String destination, String hotelNameExp){
        home.enterDestination(destination);
        home.enterDates(checkInDate, checkOutDate);
        searchResPg = home.clickSearchBtn();
        detailsPg = searchResPg.seeAvailability(hotelNameExp);
    }
}
