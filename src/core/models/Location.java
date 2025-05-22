/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import org.json.JSONObject;

/**
 *
 * @author edangulo
 */
public class Location {

    private final String airportId;
    private String airportName;
    private String airportCity;
    private String airportCountry;
    private double airportLatitude;
    private double airportLongitude;

    public Location(String airportId, String airportName, String airportCity, String airportCountry, double airportLatitude, double airportLongitude) {
        this.airportId = airportId;
        this.airportName = airportName;
        this.airportCity = airportCity;
        this.airportCountry = airportCountry;
        this.airportLatitude = airportLatitude;
        this.airportLongitude = airportLongitude;
    }

    public static Location fromJson(JSONObject obj) {

        String airportId = obj.getString("airportId");
        String airportName = obj.getString("airportName");
        String airportCity = obj.getString("airportCity");
        String airportCountry = obj.getString("airportCountry");
        double airportLatitude = obj.getDouble("airportLatitude");
        double airportLongitude = obj.getDouble("airportLongitude");

        return new Location(airportId, airportName, airportCity, airportCountry, airportLatitude, airportLongitude);
    }
    
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();

        obj.put("airportId", airportId);
        obj.put("airportName", airportName);
        obj.put("airportCity", airportCity);
        obj.put("airportCountry", airportCountry);
        obj.put("airportLatitude", airportLatitude);
        obj.put("airportLongitude", airportLongitude);

        return obj;
    }

    public String getAirportId() {
        return airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getAirportCity() {
        return airportCity;
    }

    public String getAirportCountry() {
        return airportCountry;
    }

    public double getAirportLatitude() {
        return airportLatitude;
    }

    public double getAirportLongitude() {
        return airportLongitude;
    }
    
}
