/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.persistences.LocationPersistence;

import core.models.Location;
import core.models.factories.LocationFactory;
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
public class LocationJSONPersistence implements LocationPersistence{
    private final String filePath;

    public LocationJSONPersistence(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public ArrayList<Location> load() throws IOException, JSONException {
        ArrayList<Location> locations = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonText.append(line);
            }
            JSONArray array = new JSONArray(jsonText.toString());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                locations.add(LocationFactory.fromJson(obj));
            }
        }
        return locations;
    }

    @Override
    public void save(ArrayList<Location> locations) throws IOException, JSONException {
        JSONArray array = new JSONArray();
        for (Location location : locations) {
            array.put(LocationFactory.toJson(location));
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(array.toString());
        }
    }
}
