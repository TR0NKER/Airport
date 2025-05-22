/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storages;

import core.models.Plane;
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
public class PlaneStorage {
    // Instancia Singleton
    private static PlaneStorage instance;
    
    // Atributos del Storage
    private ArrayList<Plane> planes;
    
    private static final String FILE_PATH = "json/planes.json";
    
    private PlaneStorage() {
        this.planes = new ArrayList<>();
        load();
    }
    
    public static PlaneStorage getInstance() {
        if (instance == null) {
            instance = new PlaneStorage();
        }
        return instance;
    }
    
    public boolean addPlane(Plane plane) {
        for (Plane p : this.planes) {
            if (p.getId().equals(plane.getId())) {
                return false;
            }
        }
        this.planes.add(plane);
        save();
        return true;
    }
    
    public Plane getPlane(String id) {
        for (Plane plane : this.planes) {
            if (plane.getId().equals(id)) {
                return plane;
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
                planes.add(Plane.fromJson(obj)); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        JSONArray array = new JSONArray();
        for (Plane plane : planes) {
            array.put(plane.toJson()); 
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(array.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Plane> getPlanes() {
        planes.sort(Comparator.comparing(Plane::getId));
        return planes;
    }
}


