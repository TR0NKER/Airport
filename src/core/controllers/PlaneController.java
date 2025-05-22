/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Plane;
import core.models.storages.PlaneStorage;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class PlaneController {

    public static Response createPlane(String id, String brand, String model, String maxCapacity, String airline) {
        try {

            int maxCapacityInt;

            if (id.equals("")) {
                return new Response("Id must not be empty", Status.BAD_REQUEST);
            }
            if (!id.matches("^[A-Z]{2}\\d{5}$")) {
                return new Response("Plane id must follow the format XXYYYYY (2 uppercase letters followed by 5 digits)", Status.BAD_REQUEST);
            }

            if (brand.equals("")) {
                return new Response("Brand must not be empty", Status.BAD_REQUEST);
            }

            if (model.equals("")) {
                return new Response("Model must not be empty", Status.BAD_REQUEST);
            }

            try {
                if (maxCapacity.equals("")) {
                    return new Response("Max capacity must not be empty", Status.BAD_REQUEST);
                }
                maxCapacityInt = Integer.parseInt(maxCapacity);
            } catch (NumberFormatException ex) {
                return new Response("Max capacity must be numeric", Status.BAD_REQUEST);
            }

            if (airline.equals("")) {
                return new Response("Airline must not be empty", Status.BAD_REQUEST);
            }

            PlaneStorage storage = PlaneStorage.getInstance();
            if (!storage.addPlane(new Plane(id, brand, model, maxCapacityInt, airline))) {
                return new Response("A plane with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Plane created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response readPlane(String id) {
        try {

            if (id.equals("")) {
                return new Response("Id must not be empty", Status.BAD_REQUEST);
            }

            PlaneStorage storage = PlaneStorage.getInstance();
            Plane plane = storage.getPlane(id);
            
            if (plane == null) {
                return new Response("Plane not found", Status.NOT_FOUND);
            }
            return new Response("Plane found", Status.OK, plane);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static ArrayList<Plane> getPlanes(){
        PlaneStorage storage = PlaneStorage.getInstance();
        return storage.getPlanes();
    }
}
