/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.persistences.FlightPersistence;

import core.models.Flight;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public interface FlightPersistence {
    ArrayList<Flight> load() throws Exception;
    void save(ArrayList<Flight> flights) throws Exception;
}
