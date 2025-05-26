/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.factories;

import core.models.Passenger;
import java.time.LocalDate;
import org.json.JSONObject;

/**
 *
 * @author HP
 */
public class PassengerFactory {
    public static Passenger fromJson(JSONObject obj) {

        long id = obj.getLong("id");
        String firstname = obj.getString("firstname");
        String lastname = obj.getString("lastname");
        LocalDate birthDate = LocalDate.parse(obj.getString("birthDate"));
        int countryPhoneCode = obj.getInt("countryPhoneCode");
        long phone = obj.getLong("phone");
        String country = obj.getString("country");
        
        return new Passenger(id, firstname, lastname, birthDate, countryPhoneCode, phone, country);
    }

    public static JSONObject toJson(Passenger passenger) {
        JSONObject obj = new JSONObject();

        obj.put("id", passenger.getId());
        obj.put("firstname", passenger.getFirstname());
        obj.put("lastname", passenger.getLastname());
        obj.put("birthDate", passenger.getBirthDate());
        obj.put("countryPhoneCode", passenger.getCountryPhoneCode());
        obj.put("phone", passenger.getPhone());
        obj.put("country", passenger.getCountry());

        return obj;
    }
}
