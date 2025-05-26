/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storages;

import core.models.Passenger;
import core.models.persistences.PassengerPersistence.PassengerJSONPersistence;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author DANIEL
 */
public class PassengerStorage {
    
    private static PassengerStorage instance;
    
    private ArrayList<Passenger> passengers;
    
    private PassengerJSONPersistence persistence;
    
    private static final String FILE_PATH = "json/passengers.json";
    
    private PassengerStorage() throws IOException {
        persistence = new PassengerJSONPersistence(FILE_PATH);
        this.passengers = persistence.load();
    }
    
    public static PassengerStorage getInstance() throws IOException {
        if (instance == null) {
            instance = new PassengerStorage();
        }
        return instance;
    }
    
    public boolean addPassenger(Passenger passenger) throws IOException {
        for (Passenger p : this.passengers) {
            if (p.getId() == passenger.getId()) {
                return false;
            }
        }
        this.passengers.add(passenger);
        persistence.save(passengers);
        return true;
    }
    
    public Passenger getPassenger(long id) {
        for (Passenger passenger : this.passengers) {
            if (passenger.getId() == id) {
                return passenger;
            }
        }
        return null;
    }

    public ArrayList<Passenger> getPassengers() {
        passengers.sort(Comparator.comparing(Passenger::getId));
        return passengers;
    }
    
}
