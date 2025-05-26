/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Passenger;
import core.models.storages.FlightStorage;
import core.models.storages.PassengerStorage;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class PassengerController {

    public static Response createPassenger(String id, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {
        LocalDate birthDate;
        try {

            int idInt, yearInt, monthInt, dayInt, phoneCodeInt, phoneInt;

            try {
                idInt = Integer.parseInt(id);
                if (idInt < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
                if (id.length() > 15) {
                    return new Response("Id must be a maximum of 15 digits", Status.BAD_REQUEST);
                }
                if (id.equals("")) {
                    return new Response("Id must not be empty", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            if (firstname.equals("")) {
                return new Response("Firstname must not be empty", Status.BAD_REQUEST);
            }

            if (lastname.equals("")) {
                return new Response("Lastname must not be empty", Status.BAD_REQUEST);
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
                birthDate = LocalDate.of(yearInt, monthInt, dayInt);
                if (birthDate.isAfter(LocalDate.now())) {
                    return new Response("Birthdate must be in the past", Status.BAD_REQUEST);
                }
            } catch (DateTimeException e) {
                return new Response("Birthdate must be valid", Status.BAD_REQUEST);
            }

            try {
                phoneCodeInt = Integer.parseInt(phoneCode);
                if (phoneCodeInt < 0) {
                    return new Response("Phone code must be positive", Status.BAD_REQUEST);
                }
                if (phoneCode.length() > 3) {
                    return new Response("Phone code must be a maximum of 3 digits", Status.BAD_REQUEST);
                }
                if (phoneCode.equals("")) {
                    return new Response("Phone code must not be empty", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Phone code must be numeric", Status.BAD_REQUEST);
            }

            try {
                phoneInt = Integer.parseInt(phone);
                if (phoneInt < 0) {
                    return new Response("Phone must be positive", Status.BAD_REQUEST);
                }
                if (phone.length() > 11) {
                    return new Response("Phone must be a maximum of 11 digits", Status.BAD_REQUEST);
                }
                if (phone.equals("")) {
                    return new Response("Phone must not be empty", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Phone must be numeric", Status.BAD_REQUEST);
            }

            if (country.equals("")) {
                return new Response("Country must not be empty", Status.BAD_REQUEST);
            }

            PassengerStorage storage = PassengerStorage.getInstance();
            if (!storage.addPassenger(new Passenger(idInt, firstname, lastname, birthDate, phoneCodeInt, phoneInt, country))) {
                return new Response("A passenger with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Passenger created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response updatePassenger(String id, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {
        LocalDate birthDate;
        try {

            int idInt, yearInt, monthInt, dayInt, phoneCodeInt, phoneInt;

            try {
                idInt = Integer.parseInt(id);
                if (idInt < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
                if (id.length() > 15) {
                    return new Response("Id must be a maximum of 15 digits", Status.BAD_REQUEST);
                }
                if (id.equals("")) {
                    return new Response("Id must not be empty", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            PassengerStorage storage = PassengerStorage.getInstance();
            Passenger passenger = storage.getPassenger(idInt);
            if (passenger == null) {
                return new Response("Passenger not found", Status.NOT_FOUND);
            }

            if (firstname.equals("")) {
                return new Response("Firstname must not be empty", Status.BAD_REQUEST);
            }

            if (lastname.equals("")) {
                return new Response("Lastname must not be empty", Status.BAD_REQUEST);
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
                birthDate = LocalDate.of(yearInt, monthInt, dayInt);
                if (birthDate.isAfter(LocalDate.now())) {
                    return new Response("Birthdate must be in the past", Status.BAD_REQUEST);
                }
            } catch (DateTimeException e) {
                return new Response("Birthdate must be valid", Status.BAD_REQUEST);
            }

            try {
                phoneCodeInt = Integer.parseInt(phoneCode);
                if (phoneCodeInt < 0) {
                    return new Response("Phone code must be positive", Status.BAD_REQUEST);
                }
                if (phoneCode.length() > 3) {
                    return new Response("Phone code must be a maximum of 3 digits", Status.BAD_REQUEST);
                }
                if (phoneCode.equals("")) {
                    return new Response("Phone code must not be empty", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Phone code must be numeric", Status.BAD_REQUEST);
            }

            try {
                phoneInt = Integer.parseInt(phone);
                if (phoneInt < 0) {
                    return new Response("Phone must be positive", Status.BAD_REQUEST);
                }
                if (phone.length() > 11) {
                    return new Response("Phone must be a maximum of 11 digits", Status.BAD_REQUEST);
                }
                if (phone.equals("")) {
                    return new Response("Phone must not be empty", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Phone must be numeric", Status.BAD_REQUEST);
            }

            if (country.equals("")) {
                return new Response("Country must not be empty", Status.BAD_REQUEST);
            }

            passenger.setFirstname(firstname);
            passenger.setLastname(lastname);
            passenger.setBirthDate(birthDate);
            passenger.setCountryPhoneCode(phoneCodeInt);
            passenger.setPhone(phoneInt);
            passenger.setCountry(country);

            return new Response("Passenger updated successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response addFlight(String passengerId, String flightId) {
        try {
            int passengerIdInt;

            try {
                passengerIdInt = Integer.parseInt(passengerId);
            } catch (NumberFormatException ex) {
                return new Response("Id must not be empty", Status.BAD_REQUEST);
            }

            if (!flightId.matches("^[A-Z]{3}\\d{3}$")) {
                return new Response("Flight must not be empty", Status.BAD_REQUEST);
            }

            PassengerStorage Pstorage = PassengerStorage.getInstance();
            FlightStorage Fstorage = FlightStorage.getInstance();

            Pstorage.getPassenger(passengerIdInt).addFlight(Fstorage.getFlight(flightId));
            return new Response("Flight added successfully", Status.OK);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response readPassenger(String id) {
        try {
            long idLong;

            try {
                idLong = Long.parseLong(id);
            } catch (NumberFormatException ex) {
                return new Response("Id must not be empty", Status.BAD_REQUEST);
            }

            PassengerStorage storage = PassengerStorage.getInstance();
            Passenger passenger = storage.getPassenger(idLong);
            
            if (passenger == null) {
                return new Response("Passenger not found", Status.NOT_FOUND);
            }
            return new Response("Succesfully refreshed", Status.OK, passenger.clone());
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response readPassengers() {
        try {

            PassengerStorage storage = PassengerStorage.getInstance();
            ArrayList<Passenger> passengers = storage.getPassengers();
            
            if (passengers.getFirst()==null) {
                return new Response("Passengers not found", Status.NOT_FOUND);
            }
            return new Response("Succesfully refreshed", Status.OK, passengers.clone());
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static ArrayList<Passenger> getPassengers() throws IOException {
        PassengerStorage storage = PassengerStorage.getInstance();
        return storage.getPassengers();
    }

    public static Passenger getPassenger(long id) throws IOException {
        PassengerStorage storage = PassengerStorage.getInstance();
        return storage.getPassenger(id);
    }
}
