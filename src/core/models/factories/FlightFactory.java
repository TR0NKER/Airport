/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.factories;

import core.models.Flight;
import core.models.Location;
import core.models.Plane;
import core.models.storages.LocationStorage;
import core.models.storages.PassengerStorage;
import core.models.storages.PlaneStorage;
import java.time.LocalDateTime;
import org.json.JSONObject;

/**
 *
 * @author HP
 */
public class FlightFactory {
    
    public static Flight fromJson(JSONObject obj, PlaneStorage planeStorage, LocationStorage locationStorage) {

        String id = obj.getString("id");
        String planeId = obj.getString("plane");
        String departureLocationId = obj.getString("departureLocation");
        String arrivalLocationId = obj.getString("arrivalLocation");
        LocalDateTime departureDate = LocalDateTime.parse(obj.getString("departureDate"));
        int hoursArrival = obj.getInt("hoursDurationArrival");
        int minutesArrival = obj.getInt("minutesDurationArrival");
        
        Plane plane = planeStorage.getPlane(planeId);
        Location departureLocation = locationStorage.getLocation(departureLocationId);
        Location arrivalLocation = locationStorage.getLocation(arrivalLocationId);
        
        Flight flight;

        if (obj.has("scaleLocation") && !obj.isNull("scaleLocation")) {
            String scaleLocationId = obj.getString("scaleLocation");
            int hoursScale = obj.getInt("hoursDurationScale");
            int minutesScale = obj.getInt("minutesDurationScale");
            
            Location scaleLocation = locationStorage.getLocation(scaleLocationId);

            flight = new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation, departureDate, hoursArrival, minutesArrival, hoursScale, minutesScale);
        } else {
            flight = new Flight(id, plane, departureLocation, arrivalLocation, departureDate, hoursArrival, minutesArrival);
        }
        
        plane.addFlight(flight);
        
        return flight;
    }

    public static JSONObject toJson(Flight flight) {
        JSONObject obj = new JSONObject();

        obj.put("id", flight.getId());
        obj.put("plane", flight.getPlane().getId());
        obj.put("departureLocation", flight.getDepartureLocation().getAirportId());
        obj.put("arrivalLocation", flight.getArrivalLocation().getAirportId());
        obj.put("departureDate", flight.getDepartureDate().toString());
        obj.put("hoursDurationArrival", flight.getHoursDurationArrival());
        obj.put("minutesDurationArrival", flight.getMinutesDurationArrival());

        if (flight.getScaleLocation() != null) {
            obj.put("scaleLocation", flight.getScaleLocation().getAirportId());
            obj.put("hoursDurationScale", flight.getHoursDurationScale());
            obj.put("minutesDurationScale", flight.getMinutesDurationScale());
        } else {
            obj.put("scaleLocation", JSONObject.NULL);
        }

        return obj;
    }
}
