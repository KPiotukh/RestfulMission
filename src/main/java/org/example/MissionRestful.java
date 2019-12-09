package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class MissionRestful {

    URL bookerURL;
    HttpURLConnection missionCon;
    int returnCode;

    public boolean bookPlace(int httpReturnCode, Map<String, Object> bookingParams) {
        System.out.println("Booking Place");
        try {
            String serverOutput="";

            bookerURL = new URL(MissionConfig.BOOKING_URL);
            missionCon = (HttpURLConnection) bookerURL.openConnection();

            missionCon.setDoOutput(true);
            missionCon.setRequestMethod("POST");
            missionCon.setRequestProperty("Content-Type", "application/json");
            missionCon.setRequestProperty("Accept", "application/json");

            JSONObject json = new JSONObject(bookingParams);
            String jsonString = json.toString();

            OutputStream os = missionCon.getOutputStream();
            os.write(jsonString.getBytes());
            os.flush();
            os.close();

            System.out.println("Response Code: ");
            returnCode = missionCon.getResponseCode();
            System.out.println(returnCode);

            if (returnCode == httpReturnCode) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (missionCon.getInputStream())));

                String output;
                System.out.println("Booking Response:  ");
                while ((output = br.readLine()) != null) {
                    serverOutput=serverOutput+output;
                }

                //Read Booking ID
                System.out.println(serverOutput);
                System.out.println("Read Booking ID:  ");
                JSONObject jObj = new JSONObject(serverOutput);
                MissionConfig.BOOKING_ID=jObj.getInt("bookingid");

                missionCon.disconnect();

                HashMap<String, Object> result =
                        new ObjectMapper().readValue(serverOutput, HashMap.class);

                System.out.println(result.get("booking"));
                if (bookingParams.equals(result.get("booking"))) {
                    System.out.println("Received Booking Response matches with Provided Booking Request");
                    missionCon.disconnect();
                    return true;
                }
                throw new RuntimeException("Received Booking Response doesn't match with Provided Booking Request");
            }
            else{
                throw new RuntimeException("Failed : Expected HTTP Return Code Not Received : "
                        + missionCon.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(missionCon!=null){
                missionCon.disconnect();
            }
            return false;
        }
    }

    public boolean fetchAndValidateBooking(int bookingId, int httpReturnCode, Map<String, Object> bookingParams) {
        System.out.println("Get Booking Details for booking id: "+bookingId);
        try {
            String serverOutput="";
            bookerURL = new URL(MissionConfig.BOOKING_URL+"/"+bookingId);
            missionCon = (HttpURLConnection) bookerURL.openConnection();

            missionCon.setRequestMethod("GET");
            missionCon.setRequestProperty("Accept", "application/json");

            System.out.println("Response Code: ");
            returnCode = missionCon.getResponseCode();
            System.out.println(returnCode);

            if (returnCode == httpReturnCode) {
                if(bookingParams!=null) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            (missionCon.getInputStream())));

                    String output;
                    System.out.println("Received Booking Details from Server: ");
                    while ((output = br.readLine()) != null) {
                        serverOutput = serverOutput + output;
                    }

                    System.out.println(serverOutput);
                    HashMap<String, Object> result =
                            new ObjectMapper().readValue(serverOutput, HashMap.class);

                    System.out.println(result.toString());
                    if (bookingParams.equals(result)) {
                        System.out.println("Received Booking details matches with Provided Booking details");
                        missionCon.disconnect();
                        return true;
                    }
                    throw new RuntimeException("Received Booking details do not match with Provided Booking details");
                }
                return true;
            }
            else{
                throw new RuntimeException("Failed : Expected HTTP Return Code Not Received : "
                        + missionCon.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(missionCon!=null){
                missionCon.disconnect();
            }
            return false;
        }
    }

    public boolean deleteBooking(int bookingId, int httpReturnCode) {
        System.out.println("Delete Booking for booking id: "+bookingId);
        try{
            bookerURL = new URL(MissionConfig.BOOKING_URL+"/"+bookingId);
            missionCon = (HttpURLConnection) bookerURL.openConnection();

            missionCon.setDoOutput(true);
            missionCon.setRequestMethod("DELETE");
            missionCon.setRequestProperty("Content-Type", "application/json");

            String credentials = "admin:password123";
            String encodedAuthorization = Base64.getEncoder().encodeToString( credentials.getBytes() );
            missionCon.setRequestProperty("Authorization", "Basic "+encodedAuthorization);

            System.out.println("Response Code: ");
            returnCode = missionCon.getResponseCode();
            System.out.println(returnCode);

            if (returnCode == httpReturnCode) {
                System.out.println("Successfully deleted booking for booking id: "+bookingId);
                return true;
            }
            else{
                throw new RuntimeException("Failed : Expected HTTP Return Code Not Received : "
                        + missionCon.getResponseCode());
            }
        }
        catch(Exception e){
            e.printStackTrace();
            if(missionCon!=null){
                missionCon.disconnect();
            }
            return false;
        }
    }

    public boolean updateAndValidateBooking(int bookingId, int httpReturnCode, Map<String, Object> bookingParams) {
        System.out.println("Update Booking for booking id: "+bookingId);
        try{
            String serverOutput="";

            bookerURL = new URL(MissionConfig.BOOKING_URL+"/"+bookingId);
            missionCon = (HttpURLConnection) bookerURL.openConnection();

            missionCon.setDoOutput(true);
            missionCon.setRequestMethod("PUT");
            missionCon.setRequestProperty("Content-Type", "application/json");
            missionCon.setRequestProperty("Accept", "application/json");

            String credentials = "admin:password123";
            String encodedAuthorization = Base64.getEncoder().encodeToString( credentials.getBytes() );
            missionCon.setRequestProperty("Authorization", "Basic "+encodedAuthorization);

            JSONObject json = new JSONObject(bookingParams);
            String jsonString = json.toString();

            OutputStream os = missionCon.getOutputStream();
            os.write(jsonString.getBytes());
            os.flush();
            os.close();

            System.out.println("Response Code: ");
            returnCode = missionCon.getResponseCode();
            System.out.println(returnCode);
            if (returnCode == httpReturnCode) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (missionCon.getInputStream())));

                String output;
                System.out.println("Booking Response:  ");
                while ((output = br.readLine()) != null) {
                    serverOutput=serverOutput+output;
                }
                System.out.println(serverOutput);

                missionCon.disconnect();
                System.out.println("Successfully updated booking details for booking id: "+bookingId);
                return true;
            }
            else{
                throw new RuntimeException("Failed : Expected HTTP Return Code Not Received : "
                        + missionCon.getResponseCode());
            }
        }
        catch(Exception e){
            e.printStackTrace();
            if(missionCon!=null){
                missionCon.disconnect();
            }
            return false;
        }
    }
}
