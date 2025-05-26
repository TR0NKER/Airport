/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.persistences.PlanePersistence;

import core.models.Plane;
import core.models.factories.PlaneFactory;
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
public class PlaneJSONPersistence implements PlanePersistence{
    private final String filePath;

    public PlaneJSONPersistence(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public ArrayList<Plane> load() throws IOException, JSONException {
        ArrayList<Plane> planes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonText.append(line);
            }
            JSONArray array = new JSONArray(jsonText.toString());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                planes.add(PlaneFactory.fromJson(obj));
            }
        }
        return planes;
    }

    @Override
    public void save(ArrayList<Plane> planes) throws IOException, JSONException {
        JSONArray array = new JSONArray();
        for (Plane location : planes) {
            array.put(PlaneFactory.toJson(location));
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(array.toString());
        }
    }
}
