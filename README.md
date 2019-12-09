# RestfulMission
# About: 
A sample Java code to call & test the **REST APIs**.

# JUnit Tests: 
**Test 1**: **createBooking**: <br/>
Create booking using POST request & validate the response to confirm that booking is done.

**Test 2**: **getBookingDetails**: <br/>
Get the booking details using GET request for previously created booking id & validate the response from server.

**Test 3**: **deleteBooking**: <br/>
Delete booking using DELETE request & confirm that booking is deleted by requesting details again for deleted booking id

**Test 4**: **updateBooking**: <br/>
Update booking using PUT request & confirm that booking is updated by requesting details again for updated booking id


# Execute Tests: 
Go to root directory of source code from command prompt & exeute following command: <br/>
**gradlew clean test**


# Test Report: 
Open **build/reports/tests/test/index.html** file in browser to see the report.
