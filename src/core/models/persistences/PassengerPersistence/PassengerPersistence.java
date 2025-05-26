/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.persistences.PassengerPersistence;

import core.models.Passenger;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public interface PassengerPersistence {
    ArrayList<Passenger> load() throws Exception;
    void save(ArrayList<Passenger> passengers) throws Exception;
}
