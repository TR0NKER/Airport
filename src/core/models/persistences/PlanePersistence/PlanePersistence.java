/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.persistences.PlanePersistence;

import core.models.Plane;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public interface PlanePersistence {
    ArrayList<Plane> load() throws Exception;
    void save(ArrayList<Plane> planes) throws Exception;
}
