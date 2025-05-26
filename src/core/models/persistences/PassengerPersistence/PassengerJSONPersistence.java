/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.persistences.PassengerPersistence;

import core.models.Passenger;
import core.models.factories.PassengerFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author HP
 */
public class PassengerJSONPersistence implements PassengerPersistence {

    private final String filePath;

    public PassengerJSONPersistence (String filePath) {
        this.filePath = filePath;
    }

    @Override
    public ArrayList<Passenger> load() throws IOException, JSONException {
        ArrayList<Passenger> passengers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonText.append(line);
            }
            JSONArray array = new JSONArray(jsonText.toString());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                passengers.add(PassengerFactory.fromJson(obj));
            }
        }
        return passengers;
    }

    @Override
    public void save(ArrayList<Passenger> passengers) throws IOException, JSONException {
        JSONArray array = new JSONArray();
        for (Passenger location : passengers) {
            array.put(PassengerFactory.toJson(location));
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(array.toString());
        }
    }
}
