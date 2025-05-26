/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storages;

import core.models.Location;
import core.models.persistences.LocationPersistence.LocationJSONPersistence;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
/**
 *
 * @author DANIEL
 */
public class LocationStorage {
    
    private static LocationStorage instance;
    
    private ArrayList<Location> locations;
    
    private LocationJSONPersistence persistence;
    
    private static final String FILE_PATH = "json/locations.json";
    
    private LocationStorage() throws IOException {
        persistence = new LocationJSONPersistence(FILE_PATH);
        this.locations = persistence.load();   
    }
    
    public static LocationStorage getInstance() throws IOException {
        if (instance == null) {
            instance = new LocationStorage();
        }
        return instance;
    }
    
    public boolean addLocation(Location location) throws IOException {
        for (Location l : this.locations) {
            if (l.getAirportId().equals(location.getAirportId())) {
                return false;
            }
        }
        this.locations.add(location);
        persistence.save(locations);
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

    public ArrayList<Location> getLocations() {
        locations.sort(Comparator.comparing(Location::getAirportId));
        return locations;
    }
    
}
