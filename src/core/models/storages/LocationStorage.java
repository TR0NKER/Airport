/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storages;

import core.models.Location;
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
public class LocationStorage {
    
    private static LocationStorage instance;
    
    private ArrayList<Location> locations;
    
    private static final String FILE_PATH = "json/locations.json";
    
    private LocationStorage() {
        this.locations = new ArrayList<>();
        load();
    }
    
    public static LocationStorage getInstance() {
        if (instance == null) {
            instance = new LocationStorage();
        }
        return instance;
    }
    
    public boolean addLocation(Location location) {
        for (Location l : this.locations) {
            if (l.getAirportId().equals(location.getAirportId())) {
                return false;
            }
        }
        this.locations.add(location);
        save();
        return true;
    }
    
    public Location getLocation(String id) {
        for (Location location : this.locations) {
            if (location.getAirportId().equals(id)) {
                return location;
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
                locations.add(Location.fromJson(obj)); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        JSONArray array = new JSONArray();
        for (Location location : locations) {
            array.put(location.toJson()); 
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(array.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Location> getLocations() {
        locations.sort(Comparator.comparing(Location::getAirportId));
        return locations;
    }
    
}
