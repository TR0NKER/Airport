/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author edangulo
 */
public class Plane {
    
    private final String id;
    private String brand;
    private String model;
    private final int maxCapacity;
    private String airline;
    private ArrayList<Flight> flights;

    public Plane(String id, String brand, String model, int maxCapacity, String airline) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.maxCapacity = maxCapacity;
        this.airline = airline;
        this.flights = new ArrayList<>();
    }
    
    public static Plane fromJson(JSONObject obj) {

        String id = obj.getString("id");
        String brand = obj.getString("brand");
        String model = obj.getString("model");
        int maxCapacity = obj.getInt("maxCapacity");
        String airline = obj.getString("airline");

        return new Plane(id, brand, model, maxCapacity, airline);
    }
    
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();

        obj.put("id", id);
        obj.put("brand", brand);
        obj.put("model", model);
        obj.put("maxCapacity", maxCapacity);
        obj.put("airline", airline);

        return obj;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }
    
    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getAirline() {
        return airline;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }
    
    public int getNumFlights() {
        return flights.size();
    }
}
