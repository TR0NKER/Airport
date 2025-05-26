/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.persistences.FlightPersistence;

import core.models.Flight;
import core.models.factories.FlightFactory;
import core.models.storages.LocationStorage;
import core.models.storages.PlaneStorage;
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
public class FlightJSONPersistence implements FlightPersistence{

    private final String filePath;

    public FlightJSONPersistence (String filePath) {
        this.filePath = filePath;
    }

    @Override
    public ArrayList<Flight> load() throws IOException, JSONException {
        ArrayList<Flight> flights = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonText.append(line);
            }
            JSONArray array = new JSONArray(jsonText.toString());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                flights.add(FlightFactory.fromJson(obj, PlaneStorage.getInstance(), LocationStorage.getInstance()));
            }
        }
        return flights;
    }

    @Override
    public void save(ArrayList<Flight> flights) throws IOException, JSONException {
        JSONArray array = new JSONArray();
        for (Flight location : flights) {
            array.put(FlightFactory.toJson(location));
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(array.toString());
        }
    }
}
