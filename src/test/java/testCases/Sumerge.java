package testCases;

import org.testng.annotations.Test;
import sharedSteps.Shared;
import dataproviders.DataProviders;

import java.util.Map;

import static org.testng.Assert.*;

public class Sumerge extends Shared{
    @Test(dataProvider = "SuiteData", dataProviderClass = DataProviders.class)
    public void testDates(Map<String, String> data){
        Shared.stepsToShare(data.get("checkInDate"), data.get("checkOutDate"), data.get("destination"), data.get("hotelNameExp"));
        assertTrue(detailsPg.isCheckInDateCorrect(data.get("checkInDate")));
        assertTrue(detailsPg.isCheckOutDateCorrect(data.get("checkOutDate")));
    }

    @Test(dataProvider = "SuiteData", dataProviderClass = DataProviders.class)
    public void testHotelName(Map<String, String> data){
        Shared.stepsToShare(data.get("checkInDate"), data.get("checkOutDate"), data.get("destination"), data.get("hotelNameExp"));
        var confirmPg = detailsPg.selectAmountAndReserve(Byte.parseByte(data.get("amount")));
        String hotelNameAct = confirmPg.getHotelName();
        assertEquals(hotelNameAct, data.get("hotelNameExp"), "The expected hotel name is not correctly shown in the confirmation page!");
    }
}
