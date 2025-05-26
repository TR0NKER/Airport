/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storages;

import core.models.Plane;
import core.models.persistences.PlanePersistence.PlaneJSONPersistence;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author DANIEL
 */
public class PlaneStorage {
    
    private static PlaneStorage instance;

    private ArrayList<Plane> planes;
    
    private PlaneJSONPersistence persistence;
    
    private static final String FILE_PATH = "json/planes.json";
    
    private PlaneStorage() throws IOException {
        persistence = new PlaneJSONPersistence(FILE_PATH);
        this.planes = persistence.load();;
    }
    
    public static PlaneStorage getInstance() throws IOException {
        if (instance == null) {
            instance = new PlaneStorage();
        }
        return instance;
    }
    
    public boolean addPlane(Plane plane) throws IOException {
        for (Plane p : this.planes) {
            if (p.getId().equals(plane.getId())) {
                return false;
            }
        }
        this.planes.add(plane);
        persistence.save(planes);
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


    public ArrayList<Plane> getPlanes() {
        planes.sort(Comparator.comparing(Plane::getId));
        return planes;
    }
    
}
