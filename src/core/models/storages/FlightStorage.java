/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storages;

import core.models.Flight;
import core.models.persistences.FlightPersistence.FlightJSONPersistence;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author DANIEL
 */
public class FlightStorage {
    
    private static FlightStorage instance;
    
    private ArrayList<Flight> flights;
    
    private FlightJSONPersistence persistence;
    
    private static final String FILE_PATH = "json/flights.json";
    
    private FlightStorage() throws IOException {
        persistence = new FlightJSONPersistence(FILE_PATH);
        this.flights = persistence.load();
    }
    
    public static FlightStorage getInstance() throws IOException {
        if (instance == null) {
            instance = new FlightStorage();
        }
        return instance;
    }
    
    public boolean addFlight(Flight flight) throws IOException {
        for (Flight p : this.flights) {
            if (p.getId().equals(flight.getId())) {
                return false;
            }
        }
        this.flights.add(flight);
        persistence.save(flights);
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

    public ArrayList<Flight> getFlights() {
        flights.sort(Comparator.comparing(Flight::getId));
        return flights;
    } 
}
