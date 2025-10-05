package testCases;

import base.BaseTest;
import org.testng.annotations.Test;
import sharedSteps.Shared;

import static org.testng.Assert.*;

public class Sumerge extends Shared{
    @Test
    public void testDates(){
        Shared.stepsToShare();
        assertTrue(detailsPg.isCheckInDateCorrect(checkInDate));
        assertTrue(detailsPg.isCheckOutDateCorrect(checkOutDate));
    }

    @Test
    public void testHotelName(){
        byte amount = 1;
        Shared.stepsToShare();
        var confirmPg = detailsPg.selectAmountAndReserve(amount);
        String hotelNameAct = confirmPg.getHotelName();
        assertEquals(hotelNameAct, hotelNameExp, "The expected hotel name is not correctly shown in the confirmation page!");
    }
}
