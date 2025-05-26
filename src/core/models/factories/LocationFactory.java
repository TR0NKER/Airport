/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.factories;

import core.models.Location;
import org.json.JSONObject;

/**
 *
 * @author HP
 */
public class LocationFactory {
    
    public static Location fromJson(JSONObject obj) {
        return new Location(
            obj.getString("airportId"),
            obj.getString("airportName"),
            obj.getString("airportCity"),
            obj.getString("airportCountry"),
            obj.getDouble("airportLatitude"),
            obj.getDouble("airportLongitude")
        );
    }

    public static JSONObject toJson(Location location) {
        JSONObject obj = new JSONObject();
        obj.put("airportId", location.getAirportId());
        obj.put("airportName", location.getAirportName());
        obj.put("airportCity", location.getAirportCity());
        obj.put("airportCountry", location.getAirportCountry());
        obj.put("airportLatitude", location.getAirportLatitude());
        obj.put("airportLongitude", location.getAirportLongitude());
        return obj;
    }
}
