/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Plane;
import core.models.Location;
import core.models.storages.FlightStorage;
import core.models.storages.PlaneStorage;
import core.models.storages.LocationStorage;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class FlightController {

    public static Response createFlight(String id, String planeId, String departureId, String arrivalId, String scaleId, String year, String month, String day, String hour, String minutes, String hoursDurationsArrival, String minutesDurationsArrival, String hoursDurationsScale, String minutesDurationsScale) {
        LocalDateTime departureDate;
        Flight flight;
        try {

            int yearInt, monthInt, dayInt, hourInt, minutesInt, hoursDurationsArrivalInt, minutesDurationsArrivalInt, hoursDurationsScaleInt, minutesDurationsScaleInt;

            if (id.equals("")) {
                return new Response("Id must not be empty", Status.BAD_REQUEST);
            }
            if (!id.matches("^[A-Z]{3}\\d{3}$")) {
                return new Response("Flight id must follow the format XXXYYY (3 uppercase letters followed by 3 digits)", Status.BAD_REQUEST);
            }

            PlaneStorage planeStorage = PlaneStorage.getInstance();
            Plane plane = planeStorage.getPlane(planeId);
            if (plane == null) {
                return new Response("Plane not found", Status.NOT_FOUND);
            }

            LocationStorage locationStorage = LocationStorage.getInstance();

            Location departure = locationStorage.getLocation(departureId);
            if (departure == null) {
                return new Response("Departure not found", Status.NOT_FOUND);
            }

            Location arrival = locationStorage.getLocation(arrivalId);
            if (arrival == null) {
                return new Response("Arrival not found", Status.NOT_FOUND);
            }

            try {
                if (year.equals("")) {
                    return new Response("Year must not be empty", Status.BAD_REQUEST);
                }
                yearInt = Integer.parseInt(year);
            } catch (NumberFormatException ex) {
                return new Response("Year must be numeric", Status.BAD_REQUEST);
            }

            try {
                monthInt = Integer.parseInt(month);
            } catch (NumberFormatException ex) {
                return new Response("Month must not be empty", Status.BAD_REQUEST);
            }

            try {
                dayInt = Integer.parseInt(day);
            } catch (NumberFormatException ex) {
                return new Response("Day must not be empty", Status.BAD_REQUEST);
            }

            try {
                hourInt = Integer.parseInt(hour);
            } catch (NumberFormatException ex) {
                return new Response("Hour must not be empty", Status.BAD_REQUEST);
            }

            try {
                minutesInt = Integer.parseInt(minutes);
            } catch (NumberFormatException ex) {
                return new Response("Minutes must not be empty", Status.BAD_REQUEST);
            }

            try {
                departureDate = LocalDateTime.of(yearInt, monthInt, dayInt, hourInt, minutesInt);
                if (departureDate.isBefore(LocalDateTime.now())) {
                    return new Response("Departure date must be in the future", Status.BAD_REQUEST);
                }
            } catch (DateTimeException e) {
                return new Response("Departure date must be valid", Status.BAD_REQUEST);
            }

            try {
                hoursDurationsArrivalInt = Integer.parseInt(hoursDurationsArrival);
            } catch (NumberFormatException ex) {
                return new Response("Duration hours must not be empty", Status.BAD_REQUEST);
            }

            try {
                minutesDurationsArrivalInt = Integer.parseInt(minutesDurationsArrival);
            } catch (NumberFormatException ex) {
                return new Response("Duration Minutes must not be empty", Status.BAD_REQUEST);
            }

            if (hoursDurationsArrivalInt == 0 && minutesDurationsArrivalInt == 0) {
                return new Response("Flight duration must be longer than 00:00", Status.BAD_REQUEST);
            }

            Location scale = locationStorage.getLocation(scaleId);
            if (scale == null) {
                flight = new Flight(id, plane, departure, arrival, departureDate, hoursDurationsArrivalInt, minutesDurationsArrivalInt);
            } else {

                try {
                    hoursDurationsScaleInt = Integer.parseInt(hoursDurationsScale);
                } catch (NumberFormatException ex) {
                    return new Response("Scale hours must not be empty", Status.BAD_REQUEST);
                }

                try {
                    minutesDurationsScaleInt = Integer.parseInt(minutesDurationsScale);
                } catch (NumberFormatException ex) {
                    return new Response("Scale Minutes must not be empty", Status.BAD_REQUEST);
                }

                if (hoursDurationsScaleInt == 0 && minutesDurationsScaleInt == 0) {
                    return new Response("Scale duration must be longer than 00:00", Status.BAD_REQUEST);
                }

                flight = new Flight(id, plane, departure, scale, arrival, departureDate, hoursDurationsArrivalInt, minutesDurationsArrivalInt, hoursDurationsScaleInt, minutesDurationsScaleInt);
            }

            FlightStorage storage = FlightStorage.getInstance();
            if (!storage.addFlight(flight)) {
                return new Response("A Flight with that id already exists", Status.BAD_REQUEST);
            }

            return new Response("Flight created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response delayFlight(String id, String hours, String minutes) {
        try {
            int hoursInt, minutesInt;

            if (!id.matches("^[A-Z]{3}\\d{3}$")) {
                return new Response("Flight must not be empty", Status.BAD_REQUEST);
            }
            
            try {
                hoursInt = Integer.parseInt(hours);
            } catch (NumberFormatException ex) {
                return new Response("Hour must not be empty", Status.BAD_REQUEST);
            }

            try {
                minutesInt = Integer.parseInt(minutes);
            } catch (NumberFormatException ex) {
                return new Response("Minutes must not be empty", Status.BAD_REQUEST);
            }
            
            if (hoursInt == 0 && minutesInt == 0) {
                return new Response("Delay duration must be longer than 00:00", Status.BAD_REQUEST);
            }

            FlightStorage storage = FlightStorage.getInstance();
            storage.getFlight(id).delay(hoursInt, minutesInt);

            return new Response("Flight delayed successfully", Status.OK);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response readFlight(String id) {
        try {

            if (id.equals("")) {
                return new Response("Id must not be empty", Status.BAD_REQUEST);
            }

            FlightStorage storage = FlightStorage.getInstance();
            Flight flight = storage.getFlight(id);

            if (flight == null) {
                return new Response("Flight not found", Status.NOT_FOUND);
            }
            return new Response("Succesfully refreshed", Status.OK, flight.clone());
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response readFlights() {
        try {

            FlightStorage storage = FlightStorage.getInstance();
            ArrayList<Flight> flights = storage.getFlights();

            if (flights.getFirst() == null) {
                return new Response("Flights not found", Status.NOT_FOUND);
            }
            return new Response("Succesfully refreshed", Status.OK, flights.clone());
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static ArrayList<Flight> getFlights() throws IOException {
        FlightStorage storage = FlightStorage.getInstance();
        return storage.getFlights();
    }
}
