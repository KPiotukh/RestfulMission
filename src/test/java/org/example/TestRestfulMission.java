package org.example;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.HttpURLConnection;

public class TestRestfulMission {
    static MissionRestful mr;

    @BeforeClass
    public static void beforeClass() throws Exception {
        mr = new MissionRestful();
    }

    @Test
    public void createBooking() {
        //Create Booking by sending booking parameters & validate the response
        Assert.assertTrue(mr.bookPlace(HttpURLConnection.HTTP_OK, MissionConfig.bookingParams));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @Test
    public void getBookingDetails() {
        //Get Booking details by providing booking id & validate received response
        Assert.assertTrue(mr.bookPlace(HttpURLConnection.HTTP_OK, MissionConfig.bookingParams));

        //Get Booking details by providing booking id & validate from received response
        Assert.assertTrue(mr.fetchAndValidateBooking(MissionConfig.BOOKING_ID,
                HttpURLConnection.HTTP_OK, MissionConfig.bookingParams));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @Test
    public void deleteBooking() {
        //Create Booking by sending booking parameters & validate the response
        Assert.assertTrue(mr.bookPlace(HttpURLConnection.HTTP_OK, MissionConfig.bookingParams));

        //Delete Booking
        Assert.assertTrue(mr.deleteBooking(MissionConfig.BOOKING_ID, HttpURLConnection.HTTP_CREATED));

        //Validate that Booking is deleted by requesting booking details again
        Assert.assertTrue(mr.fetchAndValidateBooking(MissionConfig.BOOKING_ID,
                HttpURLConnection.HTTP_NOT_FOUND, null));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @Test
    public void updateBooking() {
        //Get Booking details by providing booking id & validate received response
        Assert.assertTrue(mr.bookPlace(HttpURLConnection.HTTP_OK, MissionConfig.bookingParams));

        //Update Booking request & Validate that Booking is updated from received response
        Assert.assertTrue(mr.updateAndValidateBooking(MissionConfig.BOOKING_ID,
                HttpURLConnection.HTTP_OK, MissionConfig.amendmentBookingParams));

        //Validate that Booking is deleted by requesting booking details again
        Assert.assertTrue(mr.fetchAndValidateBooking(MissionConfig.BOOKING_ID,
                HttpURLConnection.HTTP_OK, MissionConfig.amendmentBookingParams));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @AfterClass
    public static void afterClass() throws Exception {
        mr = null;
    }
}
