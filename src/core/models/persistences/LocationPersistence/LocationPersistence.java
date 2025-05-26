/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.persistences.LocationPersistence;

import core.models.Location;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public interface LocationPersistence {
    ArrayList<Location> load() throws Exception;
    void save(ArrayList<Location> locations) throws Exception;
}
