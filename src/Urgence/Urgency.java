package Urgence;

import Expedition.Model.Expedition;
import Expedition.Services.ExpeditionService;
import Urgence.Model.Urgence;
import Urgence.Services.UrgenceService;
import dataSource.DataSource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Urgency extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Urgence/View/urgences.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

        public static void main(String[] args) { launch(args); }

    public static String get_ip() throws Exception {
        String url = "https://ipapi.co/json/";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to Ip : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());
        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("result after Reading JSON Response");
        System.out.println("ip- "+myResponse.getString("ip"));

        return myResponse.getString("ip");
    }

    public static double get_lat() throws Exception {
        String url = "http://ip-api.com/json/"+get_ip();
        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to Lat : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());
        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("result after Reading JSON Response");
        System.out.println("lat- "+myResponse.getDouble("lat"));

        return myResponse.getDouble("lat");
    }

    public static double get_long() throws Exception {
        String url = "http://ip-api.com/json/"+get_ip();
        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to Long : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());
        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("result after Reading JSON Response");
        System.out.println("long- "+myResponse.getDouble("lon"));

        return myResponse.getDouble("lon");
    }

    public static String get_placeId() throws Exception {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+ get_lat() +","+ get_long() +"&key=AIzaSyD2Ws0KYSjxNXXgRh8jRBGZgrXqgNHzWbI";
        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to Long : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());
        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("result after Reading JSON Response");
        JSONArray results = myResponse.getJSONArray("results");
        System.out.println("placeId- "+results.getJSONObject(3).getString("place_id"));


        return results.getJSONObject(3).getString("place_id");
    }

    public static String get_address() throws Exception {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+ get_lat() +","+ get_long() +"&key=AIzaSyD2Ws0KYSjxNXXgRh8jRBGZgrXqgNHzWbI";
        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to Long : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());
        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("result after Reading JSON Response");
        JSONObject results = myResponse.getJSONObject("plus_code");
        System.out.println("address- "+results.getString("compound_code"));


        return results.getString("compound_code");
    }

}