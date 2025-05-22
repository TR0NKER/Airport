/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Location;
import core.models.storages.LocationStorage;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class LocationController {

    public static Response createLocation(String id, String name, String city, String country, String latitude, String longitude) {
        try {

            double latitudeDouble, longitudeDouble;

            if (id.equals("")) {
                return new Response("Id must not be empty", Status.BAD_REQUEST);
            }
            if (!id.matches("^[A-Z]{3}$")) {
                return new Response("Location id must consist of exactly 3 uppercase letters", Status.BAD_REQUEST);
            }

            if (name.equals("")) {
                return new Response("Name must not be empty", Status.BAD_REQUEST);
            }

            if (city.equals("")) {
                return new Response("City must not be empty", Status.BAD_REQUEST);
            }

            if (country.equals("")) {
                return new Response("Country must not be empty", Status.BAD_REQUEST);
            }

            try {
                if (latitude.equals("")) {
                    return new Response("Latitude must not be empty", Status.BAD_REQUEST);
                }
                if (!latitude.matches("^-?\\d+(\\.\\d{1,4})?$")) {
                    return new Response("Latitude must have at most 4 decimal places", Status.BAD_REQUEST);
                }
                latitudeDouble = Double.parseDouble(latitude);
                if (latitudeDouble < -90 || latitudeDouble > 90) {
                    return new Response("Latitude must be in the range [-90, 90]", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Latitude must be numeric", Status.BAD_REQUEST);
            }

            try {
                if (longitude.equals("")) {
                    return new Response("Longitude must not be empty", Status.BAD_REQUEST);
                }
                if (!longitude.matches("^-?\\d+(\\.\\d{1,4})?$")) {
                    return new Response("Longitude must have at most 4 decimal places", Status.BAD_REQUEST);
                }
                longitudeDouble = Double.parseDouble(longitude);
                if (longitudeDouble < -180 || longitudeDouble > 180) {
                    return new Response("Longitude must be in the range [-180, 180]", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Longitude must be numeric", Status.BAD_REQUEST);
            }

            LocationStorage storage = LocationStorage.getInstance();
            if (!storage.addLocation(new Location(id, name, city, country, latitudeDouble, longitudeDouble))) {
                return new Response("A Location with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Location created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response readLocation(String id) {
        try {

            if (id.equals("")) {
                return new Response("Id must not be empty", Status.BAD_REQUEST);
            }

            LocationStorage storage = LocationStorage.getInstance();
            Location location = storage.getLocation(id);
            
            if (location == null) {
                return new Response("Location not found", Status.NOT_FOUND);
            }
            return new Response("Location found", Status.OK, location);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static ArrayList<Location> getLocations(){
        LocationStorage storage = LocationStorage.getInstance();
        return storage.getLocations();
    }
}
