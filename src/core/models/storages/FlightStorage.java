/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storages;

import core.models.Flight;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author DANIEL
 */
public class FlightStorage {
    
    private static FlightStorage instance;
    
    private ArrayList<Flight> flights;
    
    private static final String FILE_PATH = "json/flights.json";
    
    private FlightStorage() {
        this.flights = new ArrayList<>();
        load();
    }
    
    public static FlightStorage getInstance() {
        if (instance == null) {
            instance = new FlightStorage();
        }
        return instance;
    }
    
    public boolean addFlight(Flight flight) {
        for (Flight p : this.flights) {
            if (p.getId().equals(flight.getId())) {
                return false;
            }
        }
        this.flights.add(flight);
        save();
        return true;
    }
    
    public Flight getFlight(String id) {
        for (Flight flight : this.flights) {
            if (flight.getId().equals(id)) {
                return flight;
            }
        }
        return null;
    } 
    
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuilder jsonText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonText.append(line);
            }

            JSONArray array = new JSONArray(jsonText.toString());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                flights.add(Flight.fromJson(obj, PlaneStorage.getInstance(), LocationStorage.getInstance(), PassengerStorage.getInstance())); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        JSONArray array = new JSONArray();
        for (Flight flight : flights) {
            array.put(flight.toJson()); 
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(array.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Flight> getFlights() {
        flights.sort(Comparator.comparing(Flight::getId));
        return flights;
    }
    
    
}
