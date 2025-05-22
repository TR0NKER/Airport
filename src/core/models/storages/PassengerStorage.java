/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storages;

import core.models.Passenger;
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
public class PassengerStorage {
    
    private static PassengerStorage instance;
    
    private ArrayList<Passenger> passengers;
    
    private static final String FILE_PATH = "json/passengers.json";
    
    private PassengerStorage() {
        this.passengers = new ArrayList<>();
        load();
    }
    
    public static PassengerStorage getInstance() {
        if (instance == null) {
            instance = new PassengerStorage();
        }
        return instance;
    }
    
    public boolean addPassenger(Passenger passenger) {
        for (Passenger p : this.passengers) {
            if (p.getId() == passenger.getId()) {
                return false;
            }
        }
        this.passengers.add(passenger);
        save();
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
                passengers.add(Passenger.fromJson(obj)); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        JSONArray array = new JSONArray();
        for (Passenger passenger : passengers) {
            array.put(passenger.toJson()); 
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(array.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Passenger> getPassengers() {
        passengers.sort(Comparator.comparing(Passenger::getId));
        return passengers;
    }
    
}
