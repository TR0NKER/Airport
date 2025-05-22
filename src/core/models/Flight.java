/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.models.storages.LocationStorage;
import core.models.storages.PassengerStorage;
import core.models.storages.PlaneStorage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author edangulo
 */
public class Flight {
private final String id;
    private ArrayList<Passenger> passengers;
    private Plane plane;
    private Location departureLocation;
    private Location scaleLocation;
    private Location arrivalLocation;
    private LocalDateTime departureDate;
    private int hoursDurationArrival;
    private int minutesDurationArrival;
    private int hoursDurationScale;
    private int minutesDurationScale;

    public Flight(String id, Plane plane, Location departureLocation, Location arrivalLocation, LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival) {
        this.id = id;
        this.passengers = new ArrayList<>();
        this.plane = plane;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureDate = departureDate;
        this.hoursDurationArrival = hoursDurationArrival;
        this.minutesDurationArrival = minutesDurationArrival;

        this.plane.addFlight(this);
    }

    public Flight(String id, Plane plane, Location departureLocation, Location scaleLocation, Location arrivalLocation, LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival, int hoursDurationScale, int minutesDurationScale) {
        this.id = id;
        this.passengers = new ArrayList<>();
        this.plane = plane;
        this.departureLocation = departureLocation;
        this.scaleLocation = scaleLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureDate = departureDate;
        this.hoursDurationArrival = hoursDurationArrival;
        this.minutesDurationArrival = minutesDurationArrival;
        this.hoursDurationScale = hoursDurationScale;
        this.minutesDurationScale = minutesDurationScale;

        this.plane.addFlight(this);
    }

    public static Flight fromJson(JSONObject obj, PlaneStorage planeStorage, LocationStorage locationStorage, PassengerStorage passengerStorage) {

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
        
        if (obj.has("passengers")) {
            JSONArray passengerArray = obj.getJSONArray("passengers");
            for (int i = 0; i < passengerArray.length() ; i++) {
                long passengerId = passengerArray.getLong(i);
                Passenger passenger = passengerStorage.getPassenger(passengerId);
                passenger.addFlight(flight);
                flight.addPassenger(passenger);
            }
        }
        
        return flight;
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();

        obj.put("id", id);
        obj.put("plane", plane.getId());
        obj.put("departureLocation", departureLocation.getAirportId());
        obj.put("arrivalLocation", arrivalLocation.getAirportId());
        obj.put("departureDate", departureDate.toString());
        obj.put("hoursDurationArrival", hoursDurationArrival);
        obj.put("minutesDurationArrival", minutesDurationArrival);

        if (scaleLocation != null) {
            obj.put("scaleLocation", scaleLocation.getAirportId());
            obj.put("hoursDurationScale", hoursDurationScale);
            obj.put("minutesDurationScale", minutesDurationScale);
        } else {
            obj.put("scaleLocation", JSONObject.NULL);
        }

        JSONArray passengerArray = new JSONArray();
        for (Passenger p : passengers) {
            passengerArray.put(p.getId());
        }
        obj.put("passengers", passengerArray);

        return obj;
    }

    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }

    public String getId() {
        return id;
    }

    public Location getDepartureLocation() {
        return departureLocation;
    }

    public Location getScaleLocation() {
        return scaleLocation;
    }

    public Location getArrivalLocation() {
        return arrivalLocation;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public int getHoursDurationArrival() {
        return hoursDurationArrival;
    }

    public int getMinutesDurationArrival() {
        return minutesDurationArrival;
    }

    public int getHoursDurationScale() {
        return hoursDurationScale;
    }

    public int getMinutesDurationScale() {
        return minutesDurationScale;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime calculateArrivalDate() {
        return departureDate.plusHours(hoursDurationScale).plusHours(hoursDurationArrival).plusMinutes(minutesDurationScale).plusMinutes(minutesDurationArrival);
    }

    public void delay(int hours, int minutes) {
        this.departureDate = this.departureDate.plusHours(hours).plusMinutes(minutes);
    }

    public int getNumPassengers() {
        return passengers.size();
    }
}