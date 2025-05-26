/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.factories;

import core.models.Plane;
import org.json.JSONObject;

/**
 *
 * @author HP
 */
public class PlaneFactory {
    public static Plane fromJson(JSONObject obj) {

        String id = obj.getString("id");
        String brand = obj.getString("brand");
        String model = obj.getString("model");
        int maxCapacity = obj.getInt("maxCapacity");
        String airline = obj.getString("airline");

        return new Plane(id, brand, model, maxCapacity, airline);
    }
    
    public static JSONObject toJson(Plane plane) {
        JSONObject obj = new JSONObject();

        obj.put("id", plane.getId());
        obj.put("brand", plane.getBrand());
        obj.put("model", plane.getModel());
        obj.put("maxCapacity", plane.getMaxCapacity());
        obj.put("airline", plane.getAirline());

        return obj;
    }
}
