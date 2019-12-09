package org.example;

import java.util.HashMap;
import java.util.Map;

public class MissionConfig {
    static final String BOOKING_URL = "https://restful-booker.herokuapp.com/booking";
//    static final String AUTH_URL = "https://restful-booker.herokuapp.com/auth";
    static int BOOKING_ID;

    static final Map<String, String> authParams = new HashMap<String, String>()
    {{
        put("username", "admin");
        put("password", "password123");
    }};

    static final Map<String, String> datesParams = new HashMap<String, String>()
    {{
        put("checkin", "2018-01-01");
        put("checkout", "2019-01-01");
    }};

    static final Map<String, Object> bookingParams = new HashMap<String, Object>()
    {{
        put("firstname", "TestFirstName");
        put("lastname", "TestLastName");
        put("totalprice", 111);
        put("depositpaid", true);
        put("bookingdates", datesParams);
        put("additionalneeds", "Breakfast");
    }};

    static final Map<String, Object> amendmentBookingParams = new HashMap<String, Object>()
    {{
        put("firstname", "TestFirstName");
        put("lastname", "TestLastName");
        put("totalprice", 111);
        put("depositpaid", false);
        put("bookingdates", datesParams);
        put("additionalneeds", "Breakfast");
    }};
}
